package com.ntels.ccbs.system.service.auth.authMng;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import com.ntels.ccbs.system.domain.auth.authMng.AuthGroupMngVO;

public interface AuthGroupMngService {

	/**
	 * <PRE>
	 * 1. MethodName: list
	 * 2. ClassName : AuthGroupMngService
	 * 3. Comment   :
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:33:05
	 * </PRE>
	 *   @return Map<String,Object> 그룹관리 리스트정보
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 행의 수
	 *   @param lng 언어코드
	 *   @param authGroup 그룹관리VO
	 */
	Map<String, Object> list(String sidx, String sord, int page, int rows, String lng, AuthGroupMngVO authGroup);

	/**
	 * <PRE>
	 * 1. MethodName: checkUserId
	 * 2. ClassName : AuthGroupMngService
	 * 3. Comment   : 기본그룹ID 중복체크
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:33:50
	 * </PRE>
	 *   @return int 기본그룹ID 중복체크 여부
	 *   @param authGroup 그룹관리VO
	 *   @throws InvalidKeyException
	 *   @throws UnsupportedEncodingException
	 *   @throws NoSuchAlgorithmException
	 *   @throws NoSuchPaddingException
	 *   @throws InvalidAlgorithmParameterException
	 *   @throws IllegalBlockSizeException
	 *   @throws BadPaddingException
	 */
	int checkUserId(AuthGroupMngVO authGroup)throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * <PRE>
	 * 1. MethodName: insert
	 * 2. ClassName : AuthGroupMngService
	 * 3. Comment   : 그룹관리 등록
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:34:41
	 * </PRE>
	 *   @return int 등록여부
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 *   @throws InvalidKeyException
	 *   @throws UnsupportedEncodingException
	 *   @throws NoSuchAlgorithmException
	 *   @throws NoSuchPaddingException
	 *   @throws InvalidAlgorithmParameterException
	 *   @throws IllegalBlockSizeException
	 *   @throws BadPaddingException
	 */
	int insert(AuthGroupMngVO authGroup, HttpServletRequest request)throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * <PRE>
	 * 1. MethodName: update
	 * 2. ClassName : AuthGroupMngService
	 * 3. Comment   : 그룹관리 수정
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:36:05
	 * </PRE>
	 *   @return int 수정여부
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	int update(AuthGroupMngVO authGroup, HttpServletRequest request);

	/**
	 * <PRE>
	 * 1. MethodName: delete
	 * 2. ClassName : AuthGroupMngService
	 * 3. Comment   : 그룹관리 삭제
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 22. 오후 4:37:09
	 * </PRE>
	 *   @return int 삭제여부
	 *   @param authGroup 그룹관리VO
	 *   @param request {@link HttpServletRequest}
	 */
	int delete(AuthGroupMngVO authGroup, HttpServletRequest request);

}