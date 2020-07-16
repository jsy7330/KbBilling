package com.ntels.ccbs.system.service.user.userMng.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.common.util.OtpUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;
import com.ntels.ccbs.system.mapper.user.userMng.UserMngMapper;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.user.userMng.UserMngService;

@Service
public class UserMngServiceImpl implements UserMngService {
	@Autowired
	private UserMngMapper userMngMapper;
	
	UserMngService userMngService;

	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, UserMngVO user) {
		Map<String,Object> soInfo = new HashMap<String,Object>();
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String today = DateUtil.getDateStringYYYYMMDD(0);
		Integer totalCount= userMngMapper.count(user,sessionUser.getSoAuthList(),today);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			soInfo.put("totalCount", totalCount);
			soInfo.put("totalPages", new Integer(0));
			soInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			List<UserMngVO> userList = userMngMapper.list(sidx, sord, start, end, lng,user,sessionUser.getSoAuthList(),today);
			
			soInfo.put("userList", userList);
			soInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			soInfo.put("totalPages", totalPages);
			soInfo.put("page", new Integer(page));
		}
		
		return soInfo;
	}
	@Override
	public int insert(UserMngVO user, HttpServletRequest request) throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		int check=userMngMapper.checkUserId(user);
		
		if(check>0){
			throw new ServiceException("MSG.M14.MSG00018"); 
		}else{
			SessionUser session_user = (SessionUser) request.getSession().getAttribute("session_user");
			String today = DateUtil.getDateStringYYYYMMDD(0);
			
			user.setRegrId(session_user.getUserId());
			user.setSysToDate(DateUtil.sysdate());
			user.setPassword(AES256Cipher.AES_Encode(user.geteMail(), Consts.ENCODE_KEY));
			
			if(userMngMapper.userAuthCount(user)==0){	//해당하는 사용자그룹id와 사용자id가 없을때 insert
				userMngMapper.insertUserAuth(user);
			}
			String soId=userMngMapper.selectSoId(user,today);	//조직id에 해당하는 사업아이디를 조회해 so권한에 insert
			user.setSoId(soId);
			userMngMapper.insertSoAuth(user);
			
			return userMngMapper.insert(user);
		}
		
	}
	@Override
	public int update(UserMngVO user, HttpServletRequest request) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		SessionUser session_user = (SessionUser) request.getSession().getAttribute("session_user");
		user.setChgrId(session_user.getUserId());
		user.setSysToDate(DateUtil.sysdate());
		
		//현재 Password 조회
		UserMngVO password = userMngMapper.getUserInfo(user.getUserId());
		String otpStr = OtpUtil.getRandomPassword();
		if("PASSWORD_INIT".equals(user.getPassword())){
			user.setPassword(AES256Cipher.AES_Encode(otpStr, Consts.ENCODE_KEY));
			user.setPassword1(password.getPassword());
			user.setPassword2(password.getPassword1());
			
			SimpleMailMessage message = new SimpleMailMessage();
		    message.setTo(user.geteMail());
		    message.setSubject("Reset Password");
		    StringBuffer sb = new StringBuffer();
		    sb.append("Dear "+user.getUserName()+",");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("You have requested a temporary password for your Login ID.");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("Login ID : "+user.getUserId());
		    sb.append("\n");
		    sb.append("Password : "+otpStr);
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("Once you have logged in the portal with the password, you will be prompted to change your password.");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("Best Regards,");
		    sb.append("\n");
		    sb.append("VADS Team");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("NOTE: Information email only - PLEASE DO NOT REPLY");
		    
		    message.setText(sb.toString());
		    javaMailSender.send(message);
		}else{
			user.setPassword(password.getPassword());
			user.setPassword1(password.getPassword1());
			user.setPassword2(password.getPassword2());
		}
		if(userMngMapper.userAuthCount(user)==0){	//해당하는 사용자그룹id와 사용자id가 없을때 insert
			userMngMapper.insertUserAuth(user);
		}
		
		
		return userMngMapper.update(user);
	}
	//user_id 중복체크
	@Override
	public int checkUserId(UserMngVO user) {
		int check=userMngMapper.checkUserId(user);
		return check;
	}

}