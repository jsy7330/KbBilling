package com.ntels.ccbs.system.service.login.impl;

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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.common.util.OtpUtil;
import com.ntels.ccbs.system.domain.common.service.CountryLanguage;
import com.ntels.ccbs.system.domain.common.service.LoginHistory;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;
import com.ntels.ccbs.system.mapper.login.LoginMapper;
import com.ntels.ccbs.system.service.login.LoginService;
import com.ntels.nisf.util.NumberUtil;

/**
 * 로그인 처리 Service.
 *
 * @author smyun@ntels.com
 */
@Service
public class LoginServiceImpl implements LoginService {

	/** 로그 출력. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public Map<String, Object> updateLogin(String text_id, String text_nm, HttpServletRequest request){

		Map<String,Object> resultMap= new HashMap<String, Object>();

		// input data null check
		if ("".equals(text_id) || "".equals(text_nm)) {
			resultMap.put("RESULT", "ERROR_INPUT_NULL");
			resultMap.put("MAIN_URL", "");
			return resultMap;
		}

		// 로그인 실패 횟수 초과로 계정이 잠겨있는지 검사
		if (isAccountLock(text_id)) {
			logger.info("==>> account lock!!! : {}", text_id);

			resultMap.put("RESULT", "LOCK_ACCOUNT");
			resultMap.put("MAIN_URL", "");
			return resultMap;
		}

		String remoteAddress = request.getRemoteAddr();
		String sessionId = request.getRequestedSessionId();
		//login check
		SessionUser sessionUser = getSessionUser(text_id, text_nm, remoteAddress,sessionId);

		if (sessionUser != null) { //로그인 성공
			logger.info("==>> login success!!! : {}", text_id);

			// IP 접속 확인
			if (!isPassIP_Bandwidth(sessionUser, remoteAddress)) {
				resultMap.put("RESULT", "FAIL_PASS_IP_BANDWIDTH");
				resultMap.put("MAIN_URL", "");
				return resultMap;
			}

			//session create
			HttpSession session = request.getSession(true);
			session.removeAttribute("session_user");
			session.setAttribute("session_user", sessionUser);

			//login 만료기간 확인
/*			if (authService.isOverPasswordDueDate(text_id)) {
				logger.info("==>> over, password due date : " + text_id);

				//model.addAttribute("returnMsg", MessageUtil.getMessage("MSG.M06.MSG00030"));

				//resultUrl = "system/authorization/passwd/changePassword";
				resultMsg = "GO_CHANGE_PASSWORD";
			} else {
				//resultUrl = "system/main/main";
				resultMsg = "GO_MAIN";
			}*/
			resultMap.put("RESULT", "GO_MAIN");
			resultMap.put("MAIN_URL", sessionUser.getMainView());
			return resultMap;
		} else { //로그인 실패
			logger.info("==>> login fail!!! : " + text_id);

			if (isOverLoginFailCount(text_id)) {
				logger.info("==>> over login fail count!!! : {}", text_id);

				setAccountLock(text_id);

				resultMap.put("RESULT", "OVER_LOGIN_FAIL_COUNT");
				resultMap.put("MAIN_URL", "");
				return resultMap;
			}

			resultMap.put("RESULT", "LOGIN_FAIL");
			resultMap.put("MAIN_URL", "");
		}

		return resultMap;
	}



	
	/**
	 * <PRE>
	 * 1. MethodName: getSessionUser
	 * 2. ClassName : LoginServiceImpl
	 * 3. Comment   : 로그인 검증 및 세션 정보 생성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 23. 오후 2:05:39
	 * </PRE>
	 *   @return SessionUser 세션정보
	 *   @param user_id 로그인ID
	 *   @param password 패스워드
	 *   @param login_gateway_ip 접속IP
	 *   @param sessionId Session ID
	 */
	@Override
	public SessionUser getSessionUser(String user_id, String password, String login_gateway_ip, String sessionId) {
		SessionUser sessionUser = loginMapper.login(user_id, password);
		if (sessionUser != null) {
			String toDay = DateUtil.getDateStringYYYYMMDD(0);
			
			/*
			 * 사용자 Role정보 
			 */
			sessionUser.setUserRoleList(loginMapper.getUserRole(user_id));
			
			/*
			 * 사용자 조직정보
			 */
			Map<String,Object> orgInfo = loginMapper.getOrgId(user_id, toDay);
			if(orgInfo != null){
				sessionUser.setSoId((String)orgInfo.get("SO_ID"));
				sessionUser.setSoNm((String)orgInfo.get("SO_NM"));
				sessionUser.setOrgId((String)orgInfo.get("ORG_ID"));
				sessionUser.setOrgNm((String)orgInfo.get("ORG_NM"));
			}
			
			/*
			 * 사용자SO 권한 
			 */
			sessionUser.setSoAuthList(loginMapper.getSoAuthList(user_id));
			
			/*
			 * 최종 로그인 시간
			 */
			
			if (sessionUser.getLastLoginDate() != null && sessionUser.getLastLoginDate().indexOf("|") > -1) {
				sessionUser.setLastLoginDate(sessionUser.getLastLoginDate().substring(sessionUser.getLastLoginDate().indexOf("|")+1));
			}
			if (sessionUser.getLastLoginTime() != null && sessionUser.getLastLoginTime().indexOf("|") > -1) {
				sessionUser.setLastLoginTime(sessionUser.getLastLoginTime().substring(sessionUser.getLastLoginTime().indexOf("|")+1));
			}
			
			/*
			 * 접속 IP
			 */
			sessionUser.setLoginGatewayIp(login_gateway_ip);
			
			/*
			 * 세션 ID
			 */
			sessionUser.setSessionId(sessionId);

			logger.debug("{}", sessionUser);

			/*
			 * 로그인 시간 기록
			 */
			loginMapper.updateLastLoginDateTime(sessionUser);

			/*
			 * 로그인 이력 처리
			 */
			loginMapper.insertLoginHistory(setLoginHistory(sessionUser));
		} else {
			/*
			 * 로그인 실패 기록
			 */
			loginMapper.updateLoginFailCount(user_id);
		}

		return sessionUser;
	}

	/**
	 * 로그아웃 처리.
	 *
	 * @param sessionUser 세션정보
	 */
	@Override
	public void updateLogout(SessionUser sessionUser, String status) {
		String date = DateUtil.getDateStringYYYYMMDDHH24MISS(0);
		
		String logoutDate = date.substring(0, 8);
		String logoutTime = date.substring(8);
		
		loginMapper.updateLogoutHistory(sessionUser, logoutDate, logoutTime, status);
	}

	/**
	 * 로그인 이력 저장.
	 *
	 * @param sessionUser 세션정보
	 * @return LoginHistory
	 */
	@Override
	public LoginHistory setLoginHistory(SessionUser sessionUser) {
		
		LoginHistory loginhistory = new LoginHistory();

		loginhistory.setUserId(sessionUser.getUserId());
		loginhistory.setSessionId(sessionUser.getSessionId());
		loginhistory.setRemoteAddr(sessionUser.getLoginGatewayIp() == null ?
				"" : sessionUser.getLoginGatewayIp().length() > 100 ? sessionUser.getLoginGatewayIp().substring(0, 100) : sessionUser.getLoginGatewayIp());
		
		

		/*
		 * 로그인 시간 조회
		 */
		Map<String, String> mapLoginDate = loginMapper.getLoginDate(sessionUser.getUserId());
		if (mapLoginDate == null) {
			loginhistory.setLoginDate("");
			loginhistory.setLoginTime("");
		} else {
			loginhistory.setLoginDate(mapLoginDate.get("LOGIN_DATE") == null ? "" : mapLoginDate.get("LOGIN_DATE"));
			loginhistory.setLoginTime(mapLoginDate.get("LOGIN_TIME") == null ? "" : mapLoginDate.get("LOGIN_TIME"));

			if (loginhistory.getLoginDate().indexOf("|") > -1) {
				loginhistory.setLoginDate(loginhistory.getLoginDate().substring(loginhistory.getLoginDate().indexOf("|") + 1));
			}
			if (loginhistory.getLoginTime().indexOf("|") > -1) {
				loginhistory.setLoginTime(loginhistory.getLoginTime().substring(loginhistory.getLoginTime().indexOf("|") + 1));
			}
		}
		return loginhistory;
	}

	/**
	 * 비밀번호 만료 여부 확인.
	 *
	 * @param user_id 사용자ID
	 * @return boolean
	 */
/*	private boolean isOverPasswordDueDate(String user_id) {
		return (loginMapper.isOverPasswordDueDate(user_id) > 0);
	}*/

/*	public boolean isOverPasswordChangePeriod(String user_id) {
		return (loginMapper.isOverPasswordChangePeriod(user_id) > 0);
	}*/

	/** 로그인 실패 제한 횟수. */
	private @Value("${login.fail.limit}") String limit;

	/**
	 * 로그인 실패 횟수 초과 여부 확인.
	 *
	 * @param user_id 사용자ID
	 * @return boolean
	 */
	@Override
	public boolean isOverLoginFailCount(String user_id) {
		return (Integer.parseInt(NumberUtil.getDefaultZero(loginMapper.getLoginFailCount(user_id)))
					>= Integer.parseInt(limit));
	}

	/**
	 * 사용자 계정 잠금 설정.
	 *
	 * @param user_id 사용자ID
	 */
	@Override
	public void setAccountLock(String user_id) {
		loginMapper.setAccountLock(user_id);
	}

	/**
	 * 사용자 잠김 여부 확인.
	 *
	 * @param user_id 사용자ID
	 * @return boolean
	 */
	@Override
	public boolean isAccountLock(String user_id) {
		String accountLock = loginMapper.getAccountLock(user_id);

		return "Y".equals(accountLock);
	}

	/**
	 * IP 허용 영역 확인.
	 *
	 * @param sessionUser 세션정보
	 * @param remoteAddress 원격주소
	 * @return boolean
	 */
	@Override
	public boolean isPassIP_Bandwidth(SessionUser sessionUser, String remoteAddress) {
		String ip_bandwidth[] = sessionUser.getIpBandwidth().split("\\.");

		logger.debug("ip bandwidth : {}", sessionUser.getIpBandwidth());
		logger.debug("remoteAddress : {}", remoteAddress);
		logger.debug("ip bandwidth : {}", ip_bandwidth.length);

		// localhost 접속이면 건너뜀
		if (remoteAddress.equals("localhost") || remoteAddress.equals("0:0:0:0:0:0:0:1")) {
			return true;
		}else if (remoteAddress.indexOf(".") < 0) {		// ip에 '.'이 있는지 확인
			return false;
		}

		String ip[] = remoteAddress.split("\\.");
		logger.debug("ip : {}", ip.length);
		// '.'이 세개인지 확인
		if (ip.length > 4) {
			return false;
		}

		boolean result = true;
		for(int i = 0; i < ip_bandwidth.length; i++) {
			logger.debug("ip_bandwidth[{}] : {}", i, ip_bandwidth[i]);

			if (!"*".equals(ip_bandwidth[i])) {
				logger.debug("ip_bandwidth[i] : !*", i);

				if (!ip[i].equals(ip_bandwidth[i])) {
					logger.debug("ip not equal ip_bandwidth");

					result = false;
					break;
				}
			}
		}

		return result;
	}



	@Override
	public boolean isOverPasswordDueDate(String user_id) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isOverPasswordChangePeriod(String user_id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<CountryLanguage> listCountryLanguage() {
		return loginMapper.listCountryLanguage();
	}




	@Override
	public int getLoginHistoryRegCnt(SessionUser sessionUser) {
		return loginMapper.getLoginHistoryRegCnt(sessionUser);
	}



	@Override
	public void updateUserInfoConfirm(String userId, String email) {
		UserMngVO emailInfo = loginMapper.getUserInfoConfirm(userId,email);
		if(emailInfo != null){
			String otpStr = OtpUtil.getRandomPassword();
			//전송된 메일 화면
			SimpleMailMessage message = new SimpleMailMessage();
		    message.setTo(email);
		    message.setSubject("사용자 비밀번호 초기화");
		    StringBuffer sb = new StringBuffer();
		    sb.append(emailInfo.getUserName()+"님께,");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("요청하신 임시 비밀번호입니다.");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("로그인 아이디  : "+userId);
		    sb.append("\n");
		    sb.append("임시 비밀번호  : "+otpStr);
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("위의 임시 비밀번호로 로그인 하시면  즉시 비밀번호를 변경하셔야 합니다.");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("감사합니다.");
		    sb.append("\n");
		    sb.append("\n");
		    sb.append("[주의]본 메일은 발신 전용입니다. 회신하지 말아주세요.");
		    
		    message.setText(sb.toString());
		    javaMailSender.send(message);
			
			try {
				loginMapper.updatePassword(userId, email, AES256Cipher.AES_Encode(otpStr, Consts.ENCODE_KEY));
				
			} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
					| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
					| BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
		}else{
			throw new ServiceException("MSG.M15.MSG00050");  
		}
	}
}


