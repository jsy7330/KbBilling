package com.ntels.ccbs.system.service.common.common.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;
import com.ntels.ccbs.system.mapper.common.common.ChangePasswordMngMapper;
import com.ntels.ccbs.system.service.common.common.ChangePasswordMngService;

@Service
public class ChangePasswordMngServiceImpl implements ChangePasswordMngService {

	@Autowired
	private ChangePasswordMngMapper changePasswordMngMapper;

	@Override
	public void updatePassword(String userId, String password, String newPassword1, String newPassword2) {
		try {
			UserMngVO userVo = changePasswordMngMapper.getCheckIdPw(userId,AES256Cipher.AES_Encode(password, Consts.ENCODE_KEY));
			if(userVo == null){
				throw new ServiceException("MSG.M06.MSG00035");  
			}else{
				String currentPw;
				String password1;
				String password2;
				if("".equals(userVo.getPassword1()) || userVo.getPassword1() == null){
					currentPw = AES256Cipher.AES_Encode(newPassword1, Consts.ENCODE_KEY);
					password1 = AES256Cipher.AES_Encode(password, Consts.ENCODE_KEY);
					password2 = "";
				}else{
					currentPw = AES256Cipher.AES_Encode(newPassword1, Consts.ENCODE_KEY);
					password1 = AES256Cipher.AES_Encode(password, Consts.ENCODE_KEY);
					password2 = userVo.getPassword1();
				}
				changePasswordMngMapper.updatePassword(userId, currentPw, password1,password2);
				
			}
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}