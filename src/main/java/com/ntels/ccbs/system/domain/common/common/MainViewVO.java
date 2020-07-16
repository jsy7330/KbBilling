package com.ntels.ccbs.system.domain.common.common;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: MainViewVO
 * 2. FileName : MainViewVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.common.common
 * 4. Comment  : 메인뷰VO
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 11:17:52
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("mainView")
public class MainViewVO extends PagingValue {
	/**
	 * 페이지명
	 */
	private String commonCdNm;
	/**
	 * 페이지URL
	 */
	private String refCode;
	public String getCommonCdNm() {
		return commonCdNm;
	}
	public void setCommonCdNm(String commonCdNm) {
		this.commonCdNm = commonCdNm;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	
}