package com.ntels.ccbs.system.mapper.common.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;

@Component
public interface ChangePasswordMngMapper {

	UserMngVO getCheckIdPw(@Param(value = "userId")String userId, @Param(value = "password")String password);

	void updatePassword(@Param(value = "userId")String userId,  @Param(value = "currentPw")String currentPw, 
			@Param(value = "password1")String password1, @Param(value = "password2")String password2);
	
}