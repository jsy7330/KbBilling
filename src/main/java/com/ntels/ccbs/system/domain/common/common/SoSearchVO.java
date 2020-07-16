package com.ntels.ccbs.system.domain.common.common;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: SoSearchVO
 * 2. FileName : SoSearchVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.common.common
 * 4. Comment  :
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오전 10:37:52
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("soSearch")
public class SoSearchVO extends PagingValue {
	/**
	 * 사업ID
	 */
	private String soId;
	/**
	 * 사업명
	 */
	private String soNm;
	/**
	 * 사용자 ID
	 */
	private String userId;
	
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}