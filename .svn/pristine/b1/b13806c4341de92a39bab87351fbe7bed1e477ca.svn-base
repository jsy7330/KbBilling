package com.ntels.ccbs.system.service.common.service;

import java.util.List;

/**
 * <PRE>
 * 1. ClassName: SequenceService
 * 2. FileName : SequenceService.java
 * 3. Package  : com.ntels.ccbs.system.service.common
 * 4. Comment  : Sequence 서비스 인터페이스
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 2. 25. 오후 1:17:47
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 2. 25.    : 신규개발
 * </PRE>
 */
/**
 * <PRE>
 * 1. ClassName: SequenceService
 * 2. FileName : SequenceService.java
 * 3. Package  : com.ntels.ccbs.system.service.common
 * 4. Comment  :
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 2. 26. 오전 9:40:00
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 2. 26.    : 신규개발
 * </PRE>
 */
public interface SequenceService {
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSequence
	 * 2. ClassName : SequenceService
	 * 3. Comment   : 새로운 Sequence 채번
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 25. 오후 2:12:49
	 * </PRE>
	 *   @return Integer 채번된 Sequence
	 *   @param modCd Consts.SEQ_CODE Type
	 */
	public Integer createNewSequence(String modCd); 
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSequence
	 * 2. ClassName : SequenceService
	 * 3. Comment   :새로운 Sequence 채번(자리만큼 0패딩)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 8. 17. 오후 9:03:28
	 * </PRE>
	 *   @return String
	 *   @param modCd
	 *   @param size
	 *   @return
	 */
	public String createNewSequence(String modCd, int size); 
	
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSequence
	 * 2. ClassName : SequenceService
	 * 3. Comment   : 새로운 Sequence 채번해서 Prefix문자 + 0 Padding
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 25. 오후 2:12:46
	 * </PRE>
	 *   @return String  Prefix + 0 Padding + Sequence
	 *   @param modCd Consts.SEQ_CODE Type
	 *   @param prefix Prefix 문자
	 *   @param size 사이즈
	 */
	public String createNewSequence(String modCd, String prefix, int size);
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSequence
	 * 2. ClassName : SequenceService
	 * 3. Comment   :새로운 Sequence 채번해서 Prefix문자 + 0 Padding(Count만큼 반환)
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 7. 12. 오전 10:24:30
	 * </PRE>
	 *   @return List<String> String  Prefix + 0 Padding + Sequence
	 *   @param modCd Consts.SEQ_CODE Type
	 *   @param prefix Prefix 문자
	 *   @param size size 사이즈
	 *   @param count 필요한 Seq수
	 *   @return
	 */
	public List<String> createNewSequence(String modCd, String prefix, int size, int count);
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSequenceDaily
	 * 2. ClassName : SequenceService
	 * 3. Comment   : 일 갱신 주기의 Sequence 채번
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오전 9:38:43
	 * </PRE>
	 *   @return String YYYYMMDD or YYMMDD Prefix + 0 Padding + Sequence
	 *   @param modCd Consts.SEQ_CODE Type
	 *   @param dateFormat YYYYMMDD or YYMMDD
	 *   @param size 사이즈
	 */
	public String createNewSequenceDaily(String modCd, String dateFormat, int size);
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSequenceDaily
	 * 2. ClassName : SequenceService
	 * 3. Comment   :일 갱신 주기의 Sequence 채번
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 6. 22. 오후 5:26:46
	 * </PRE>
	 *   @return Integer Seq
	 *   @param modCd Consts.SEQ_CODE Type
	 */
	public Integer createNewSequenceDaily(String modCd);
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSequenceMonthly
	 * 2. ClassName : SequenceService
	 * 3. Comment   : 월 갱신 주기의 Sequence 채번
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오전 9:39:04
	 * </PRE>
	 *   @return String YYYYMM or YYMM Prefix + 0 Padding + Sequence
	 *   @param modCd Consts.SEQ_CODE Type
	 *   @param dateFormat YYYYMM or YYMM
	 *   @param size 사이즈
	 */
	public String createNewSequenceMonthly(String modCd, String dateFormat,  int size);

	/**
	 * <PRE>
	 * 1. MethodName: createNewSequenceYealy
	 * 2. ClassName : SequenceService
	 * 3. Comment   : 년 갱신 주기의 Sequence 채번
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 2. 26. 오전 9:40:01
	 * </PRE>
	 *   @return String YYYY or YY Prefix + 0 Padding + Sequence
	 *   @param modCd Consts.SEQ_CODE Type
	 *   @param dateFormat YYYY or YY
	 *   @param size 사이즈
	 */
	public String createNewSequenceYealy(String modCd, String dateFormat, int size);
	
	/**
	 * <PRE>
	 * 1. MethodName: createNewSubSequence
	 * 2. ClassName : SequenceService
	 * 3. Comment   : 서브 시퀀스용 채번
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 5. 11. 오후 3:14:34
	 * </PRE>
	 *   @return Integer 채번된 Sub Sequence
	 *   @param targetTblNm 대상 테이블명
	 *   @param targetColNm 대상 컬럼명
	 *   @param keyCode 서브 Sequence의 메인 Key Value
	 */
	public Integer createNewSubSequence(String targetTblNm, String targetColNm, String keyCode); 
}
 

