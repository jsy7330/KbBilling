package com.ntels.ccbs.system.service.user.userMng;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import com.ntels.ccbs.system.domain.user.userMng.UserMngVO;

public interface UserMngService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : UserMngService
	 * 3. Comment   : 사용자관리 리스트
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:58:16
	 * </PRE>
	 *   @return Map<String,Object> 사용자관리리스트 정보
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 언어코드
	 *   @param user 사용자관리VO
	 */
	Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, UserMngVO user);

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : UserMngService
	 * 3. Comment   :사용자 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 3:59:17
	 * </PRE>
	 *   @return int 등록여부
	 *   @param user 사용자VO
	 *   @param request {@link HttpServletRequest}
	 *   @throws InvalidKeyException
	 *   @throws UnsupportedEncodingException
	 *   @throws NoSuchAlgorithmException
	 *   @throws NoSuchPaddingException
	 *   @throws InvalidAlgorithmParameterException
	 *   @throws IllegalBlockSizeException
	 *   @throws BadPaddingException
	 */
	int insert(UserMngVO user, HttpServletRequest request)  throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * <PRE>
	 * 1. MethodName: update
	 * 2. ClassName : UserMngService
	 * 3. Comment   : 사용자 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:00:14
	 * </PRE>
	 *   @return int 수정여부
	 *   @param user 사용자VO
	 *   @param request {@link HttpServletRequest}
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	int update(UserMngVO user, HttpServletRequest request) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * <PRE>
	 * 1. MethodName: checkUserId
	 * 2. ClassName : UserMngService
	 * 3. Comment   : 사용자ID 중복체크
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:00:43
	 * </PRE>
	 *   @return int 사용자ID 중복 여부
	 *   @param user 사용자VO
	 */
	int checkUserId(UserMngVO user);

}