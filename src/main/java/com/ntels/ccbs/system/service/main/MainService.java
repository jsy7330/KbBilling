package com.ntels.ccbs.system.service.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.domain.main.InquiryHistVO;

public interface MainService {

	public HashMap<String, String> cntResult();

	public List<HashMap<String, String>> joinMemberChart();

	public List<HashMap<String, String>> regiVoucherChart();

	public List<InquiryHistVO> noticeList();

	public InquiryHistVO noticeView(InquiryHistVO inquiryHistVO);

	/**
	 * <PRE>
	 * 1. MethodName: mainBulletinList
	 * 2. ClassName : MainService
	 * 3. Comment   : 해당USER_ID의 사용자그룹에 속하는 게시판 조회
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:34:37
	 * </PRE>
	 *   @return Map<String,Object> 게시판리스트 정보
	 *   @param sidx Sort 대상 키
	 *   @param sord Sort 유형(DESC, ASC)
	 *   @param page 페이지수
	 *   @param rows 화면에 보여질 행의 수
	 *   @param bulletin 게시판VO
	 *   @param lng 언어코드
	 */
	public Map<String, Object> mainBulletinList(String sidx, String sord, int page, int rows, BulletinMngVO bulletin, String lng);

	/**
	 * <PRE>
	 * 1. MethodName: detailBulletin
	 * 2. ClassName : MainService
	 * 3. Comment   : 게시판 상세정보
	 * 4. 작성자    : Kim Hye Won
	 * 5. 작성일    : 2016. 6. 29. 오후 3:30:40
	 * </PRE>
	 *   @return BulletinMngVO 선택한 게시판 정보
	 *   @param noticeId 게시물ID
	 *   @param lng 언어코드
	 */
	public BulletinMngVO updateDetailBulletin(String noticeId, String lng);

	public Map<String, Object> billingMainChart1( BulletinMngVO bulletin, String lng);
	public Map<String, Object> billingMainChart2( BulletinMngVO bulletin, String lng);	
	public Map<String, Object> customerMainChart1( BulletinMngVO bulletin, String lng);
	public Map<String, Object> customerMainChart2( BulletinMngVO bulletin, String lng);	
}
