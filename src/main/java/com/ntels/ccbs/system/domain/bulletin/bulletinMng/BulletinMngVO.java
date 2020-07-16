package com.ntels.ccbs.system.domain.bulletin.bulletinMng;

import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <PRE>
 * 1. ClassName: BulletinMngVO
 * 2. FileName : BulletinMngVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.bulletin.bulletinMng
 * 4. Comment  :
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 4:24:08
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
/**
 * <PRE>
 * 1. ClassName: BulletinMngVO
 * 2. FileName : BulletinMngVO.java
 * 3. Package  : com.ntels.ccbs.system.domain.bulletin.bulletinMng
 * 4. Comment  :
 * 5. 작성자   : Kim Hye Won
 * 6. 작성일   : 2016. 6. 24. 오후 4:24:53
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Kim Hye Won :    2016. 6. 24.    : 신규개발
 * </PRE>
 */
@XStreamAlias("bulletin")
public class BulletinMngVO extends PagingValue {
	/**
	 * 게시물ID
	 */
	private String noticeId;
	/**
	 * 게시물구분
	 */
	private String noticeCl;
	/**
	 * 게시물 제목
	 */
	private String title;
	/**
	 * 게시물내용
	 */
	private String content;
	/**
	 * 조회수
	 */
	private String viewCnt;
	/**
	 * 상위게시물순번
	 */
	private String upNoticeId;
	/**
	 * 적용시작일자
	 */
	private String effectStartDt;
	/**
	 * 적용종료일자
	 */
	private String effectEndDt;
	/**
	 * 팝업여부
	 */
	private String popupYn;
	/**
	 * 등록자
	 */
	private String regrId;
	/**
	 * 게시일시
	 */
	private String regDate;
	/**
	 * 수정자
	 */
	private String chgrId;
	/**
	 * 변경일시
	 */
	private String chgDate;

	/**
	 * 출력순서
	 */
	private String displayOrder;
	/**
	 * 
	 */
	private String userGroupId;
	/**
	 * 사용자그룹ID
	 */
	private String userGroupName;
	/**
	 * 사용자그룹레벨
	 */
	private String userGroupLevel;
	
	/**
	 * 첨부순서
	 */
	private int fileSeq;
	/**
	 * 파일Path
	 */
	private String filePath;
	/**
	 * 파일명
	 */
	private String fileNm;
	/**
	 * 파일UUID
	 */
	private String fileUuid;

	/**
	 * 언어
	 */
	private String lngTyp;

	/**
	 * 검색조건(제목/내용 선택)
	 */
	private String srchTyp;
	/**
	 * 제목/내용 검색
	 */
	private String srchNm;
	
	/**
	 * 권한ID
	 */
	private String authId;
	/**
	 * 권한대상그룹
	 */
	private String authGroup;
	
	/**
	 * 등록/수정여부
	 */
	private String flag;
	
	/**
	 * 현재날짜 
	 */
	private Date sysToDate;

	/**
	 * 팝업종류
	 */
	private String popType;

	//main page 그래프
	private String billYymm;
	private String crncyCd;
	private String billAmt;
	private String payYymm;
	private String rcptAmt;
	private String startMonth;
	private String middleMonth;
	private String endMonth;
	private String searchStartMonth;
	private String searchEndMonth;
	private String searchMiddleMonth;
	private String soId;

	private String mM;
	private String tCnt;
	private String aCnt;
	private String dCnt;
	private String bCnt;
	private String vCnt;
	private String eCnt;
	private String pCnt;
	
	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeCl() {
		return noticeCl;
	}

	public void setNoticeCl(String noticeCl) {
		this.noticeCl = noticeCl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getUpNoticeId() {
		return upNoticeId;
	}

	public void setUpNoticeId(String upNoticeId) {
		this.upNoticeId = upNoticeId;
	}

	public String getEffectStartDt() {
		return effectStartDt;
	}

	public void setEffectStartDt(String effectStartDt) {
		this.effectStartDt = effectStartDt;
	}

	public String getEffectEndDt() {
		return effectEndDt;
	}

	public void setEffectEndDt(String effectEndDt) {
		this.effectEndDt = effectEndDt;
	}

	public String getPopupYn() {
		return popupYn;
	}

	public void setPopupYn(String popupYn) {
		this.popupYn = popupYn;
	}

	public String getRegrId() {
		return regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getChgrId() {
		return chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public String getChgDate() {
		return chgDate;
	}

	public void setChgDate(String chgDate) {
		this.chgDate = chgDate;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getUserGroupLevel() {
		return userGroupLevel;
	}

	public void setUserGroupLevel(String userGroupLevel) {
		this.userGroupLevel = userGroupLevel;
	}

	public int getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	public String getLngTyp() {
		return lngTyp;
	}

	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSrchTyp() {
		return srchTyp;
	}

	public void setSrchTyp(String srchTyp) {
		this.srchTyp = srchTyp;
	}

	public String getSrchNm() {
		return srchNm;
	}

	public void setSrchNm(String srchNm) {
		this.srchNm = srchNm;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthGroup() {
		return authGroup;
	}

	public void setAuthGroup(String authGroup) {
		this.authGroup = authGroup;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getSysToDate() {
		return sysToDate;
	}

	public void setSysToDate(Date sysToDate) {
		this.sysToDate = sysToDate;
	}
	
	public String getPopType() {
		return popType;
	}

	public void setPopType(String popType) {
		this.popType = popType;
	}
	public String getBillYymm() {
		return billYymm;
	}

	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}

	public String getCrncyCd() {
		return crncyCd;
	}

	public void setCrncyCd(String crncyCd) {
		this.crncyCd = crncyCd;
	}

	public String getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}

	public String getPayYymm() {
		return payYymm;
	}

	public void setPayYymm(String payYymm) {
		this.payYymm = payYymm;
	}

	public String getRcptAmt() {
		return rcptAmt;
	}

	public void setRcptAmt(String rcptAmt) {
		this.rcptAmt = rcptAmt;
	}
	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getMiddleMonth() {
		return middleMonth;
	}

	public void setMiddleMonth(String middleMonth) {
		this.middleMonth = middleMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public String getSearchStartMonth() {
		return searchStartMonth;
	}

	public void setSearchStartMonth(String searchStartMonth) {
		this.searchStartMonth = searchStartMonth;
	}

	public String getSearchEndMonth() {
		return searchEndMonth;
	}

	public void setSearchEndMonth(String searchEndMonth) {
		this.searchEndMonth = searchEndMonth;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}
	
	public String getmM() {
		return mM;
	}

	public void setmM(String mM) {
		this.mM = mM;
	}

	public String gettCnt() {
		return tCnt;
	}

	public void settCnt(String tCnt) {
		this.tCnt = tCnt;
	}

	public String getaCnt() {
		return aCnt;
	}

	public void setaCnt(String aCnt) {
		this.aCnt = aCnt;
	}

	public String getdCnt() {
		return dCnt;
	}

	public void setdCnt(String dCnt) {
		this.dCnt = dCnt;
	}

	public String getbCnt() {
		return bCnt;
	}

	public void setbCnt(String bCnt) {
		this.bCnt = bCnt;
	}

	public String getvCnt() {
		return vCnt;
	}

	public void setvCnt(String vCnt) {
		this.vCnt = vCnt;
	}

	public String geteCnt() {
		return eCnt;
	}

	public void seteCnt(String eCnt) {
		this.eCnt = eCnt;
	}

	public String getpCnt() {
		return pCnt;
	}

	public void setpCnt(String pCnt) {
		this.pCnt = pCnt;
	}
	public String getSearchMiddleMonth() {
		return searchMiddleMonth;
	}

	public void setSearchMiddleMonth(String searchMiddleMonth) {
		this.searchMiddleMonth = searchMiddleMonth;
	}
}
