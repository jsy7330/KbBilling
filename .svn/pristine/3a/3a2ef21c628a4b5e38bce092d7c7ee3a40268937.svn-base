package com.ntels.ccbs.system.mapper.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.domain.main.InquiryHistVO;

@Component
public interface MainMapper {

	HashMap<String, String> cntResult();

	List<HashMap<String, String>> joinMemberChart();

	List<HashMap<String, String>> regiVoucherChart();

	List<InquiryHistVO> noticeList();

	InquiryHistVO noticeView(@Param(value = "vo") InquiryHistVO inquiryHistVO);

	/**
	 * <PRE>
	 * 1. MethodName: count
	 * 2. ClassName : MainMapper
	 * 3. Comment   :해당USER_ID의 사용자그룹에 속하는 게시판 수 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:39:34
	 * </PRE>
	 *   @return Integer 해당USER_ID의 사용자그룹에 속하는 게시판 수
	 *   @param bulletin 게시판VO
	 *   @param userId 로그인 사용자ID
	 */
	Integer count(@Param(value = "bulletin")BulletinMngVO bulletin, @Param(value = "userId")String userId);

	/**
	 * <PRE>
	 * 1. MethodName: mainBulletinList
	 * 2. ClassName : MainMapper
	 * 3. Comment   : 해당USER_ID의 사용자그룹에 속하는 게시판 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:39:27
	 * </PRE>
	 *   @return List<BulletinMngVO>해당USER_ID의 사용자그룹에 속하는 게시판 리스트
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param start  페이징 첫번째 index
	 *   @param end 페이징 마지막 index
	 *   @param bulletin 게시판VO
	 *   @param lng 언어코드
	 *   @param userId 로그인 사용자ID
	 */
	List<BulletinMngVO> mainBulletinList( @Param(value = "sidx")String sidx,  @Param(value = "sord")String sord, 
			 @Param(value = "start")String start, @Param(value = "end") String end, 
			 @Param(value = "bulletin")BulletinMngVO bulletin,  @Param(value = "lng")String lng, 
			 @Param(value = "userId")String userId);

	/**
	 * <PRE>
	 * 1. MethodName: detailBulletin
	 * 2. ClassName : MainMapper
	 * 3. Comment   : 게시판 상세정보
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:31:45
	 * </PRE>
	 *   @return BulletinMngVO 선택한 게시판 정보
	 *   @param noticeId 게시물ID
	 *   @param lng 언어코드
	 */
	BulletinMngVO detailBulletin(@Param(value = "noticeId")String noticeId,@Param(value = "lng") String lng);

	/**
	 * <PRE>
	 * 1. MethodName: authBulletin
	 * 2. ClassName : MainMapper
	 * 3. Comment   : 세션아이디에 해당하는 사용자별그룹관리
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:32:14
	 * </PRE>
	 *   @return List<BulletinMngVO> 세션아이디에 해당하는 사용자별그룹관리 리스트
	 *   @param noticeId 게시물ID
	 */
	List<BulletinMngVO> authBulletin(@Param(value = "noticeId")String noticeId);

	/**
	 * <PRE>
	 * 1. MethodName: updateViewCnt
	 * 2. ClassName : MainMapper
	 * 3. Comment   : 해당게시물의 조회수 증가
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:46:50
	 * </PRE>
	 *   @return void
	 *   @param noticeId 게시물ID
	 */
	void updateViewCnt(@Param(value = "noticeId")String noticeId);

	/**
	 * <PRE>
	 * 1. MethodName: getFileList
	 * 2. ClassName : MainMapper
	 * 3. Comment   : 해당게시물에 해당하는 첨부파일 리스트
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 30. 오전 10:04:12
	 * </PRE>
	 *   @return List<Map<String,Object>> 첨부파일 리스트
	 *   @param noticeId 게시물ID
	 */
	List<Map<String, Object>> getFileList(@Param(value = "noticeId")String noticeId);


	List<BulletinMngVO> billingMainChart1(@Param(value = "bulletin")BulletinMngVO bulletin,  @Param(value = "lng")String lng);
	List<BulletinMngVO> billingMainChart2(@Param(value = "bulletin")BulletinMngVO bulletin,  @Param(value = "lng")String lng);
	List<BulletinMngVO> customerMainChart1(@Param(value = "bulletin")BulletinMngVO bulletin,  @Param(value = "lng")String lng);
	List<BulletinMngVO> customerMainChart2(@Param(value = "bulletin")BulletinMngVO bulletin,  @Param(value = "lng")String lng);	
}
