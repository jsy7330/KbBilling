package com.ntels.ccbs.distribute.domain.distributor.distributorMgt;

import java.util.Date;
import java.util.List;

import com.ntels.ccbs.system.domain.common.service.PagingValue;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Organization")
public class OrganizationVO extends PagingValue {
	
	private String soId;			//사업ID
	private String orgId;			//조직ID
	private String orgNm;			//조직명
	private String tpCd;			//유형코드
	private String tpDtlCd;			//유형상세코드
	private String inqPermCd;		//조회권한코드
	private String permLogin;		//업무권한-로그인
	private String permSubs;		//업무권한-가입
	private String permChg;			//업무권한-기변
	private String permTerm;		//업무권한-해지
	private String permPym;			//업무권한-수납
	private String arClCd;			//지역분류코드
	private String arTpCd;			//지역구분코드
	private String orgLvCd;			//조직레벨코드
	private String orgStatCd;		//조직상태코드
	private String orgStatChgDt;	//조직상태변경일
	private String appyStrtDt;		//적용시작일
	private String appyEndDt;		//적용종료일
	private String loanGvFlg;		//여신부여여부
	private String tbiFlg;			//세금계산서발행여부
	private String feePvFlg;		//수수료지급여부
	private String privTpCd;		//자사구분코드
	private String onlineClCd;		//온라인구분코드
	private String regrId;			//등록자
	private Date regDate;			//등록일시
	private String chgrId;			//수정자
	private Date chgDate;			//수정일시
	
	private String orgRelOrd; 		//조직관계순번
	private String orgHistOrd; 		//조직이력순번
	private String uppOrgId; 		//상위조직ID
	private String inqPermLvCd; 	//조회권한레벨코드
	private String inqPermOrgId; 	//조회권한조직ID
	private String relTpCd; 		//관계유형코드
	
	private String soNm;			//사업명
	private String tpNm;			//유형코드명 TP_NM
	private String tpDtlNm;			//유형상세코드명 TP_DTL_NM
	private String arClNm;			//지역분류코드명 AR_CL_NM
	private String arTpNm;			//지역구분코드명 AR_TP_NM
	private String orgLvNm;			//조직레벨코드명 ORG_LV_NM
	private String orgStatNm;		//조직상태코드명 ORG_STAT_NM
	private String privTpNm;		//자사구분코드명 PRIV_TP_NM
	private String inqPermNm;		//조회권한명
	private String relTpNm;			//관계유형코드명
	private String partOrgId;		//상대조직 ID
	private String partOrgNm;		//상대조직 명
	private String partOrgLvNm;		//상대조직 레벨코드명
	
	private String partTpNm;		//상대조직 유형명
	private String partTpDtlNm;		//상대조직 상세유형명
	
	private boolean blLoanGvFlg = false;		//여신부여여부
	private boolean blTbiFlg = false;			//세금계산서발행여부
	private boolean blFeePvFlg = false;			//수수료지급여부
	
	private String perm;			//업무권한-전체
	
	private String commonGrp;			//공통코드
	private String commonCd;			//상세코드
	private String commonCdNm;			//코드명
	private String refCode;				//코드참조
	private String refCode2;			//코드참조2
	private String refCode3;			//코드참조3
	private String refCode4;			//코드참조4
	
	
	private String lngTyp;
	private String sidx;
	private String sord;
	private String popType;				//팝업타입구분
	private String popType2;				//팝업타입구분
	private String returnId1;			
	private String returnId2;
	
	private List<String> orgIdList;
	private String checkAppyDt;			//적용일 변경 체크
	
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getTpCd() {
		return tpCd;
	}
	public void setTpCd(String tpCd) {
		this.tpCd = tpCd;
	}
	public String getTpDtlCd() {
		return tpDtlCd;
	}
	public void setTpDtlCd(String tpDtlCd) {
		this.tpDtlCd = tpDtlCd;
	}
	public String getInqPermCd() {
		return inqPermCd;
	}
	public void setInqPermCd(String inqPermCd) {
		this.inqPermCd = inqPermCd;
	}
	public String getPermLogin() {
		return permLogin;
	}
	public void setPermLogin(String permLogin) {
		this.permLogin = permLogin;
	}
	public String getPermSubs() {
		return permSubs;
	}
	public void setPermSubs(String permSubs) {
		this.permSubs = permSubs;
	}
	public String getPermChg() {
		return permChg;
	}
	public void setPermChg(String permChg) {
		this.permChg = permChg;
	}
	public String getPermTerm() {
		return permTerm;
	}
	public void setPermTerm(String permTerm) {
		this.permTerm = permTerm;
	}
	public String getPermPym() {
		return permPym;
	}
	public void setPermPym(String permPym) {
		this.permPym = permPym;
	}
	public String getArClCd() {
		return arClCd;
	}
	public void setArClCd(String arClCd) {
		this.arClCd = arClCd;
	}
	public String getArTpCd() {
		return arTpCd;
	}
	public void setArTpCd(String arTpCd) {
		this.arTpCd = arTpCd;
	}
	public String getOrgLvCd() {
		return orgLvCd;
	}
	public void setOrgLvCd(String orgLvCd) {
		this.orgLvCd = orgLvCd;
	}
	public String getOrgStatCd() {
		return orgStatCd;
	}
	public void setOrgStatCd(String orgStatCd) {
		this.orgStatCd = orgStatCd;
	}
	public String getOrgStatChgDt() {
		return orgStatChgDt;
	}
	public void setOrgStatChgDt(String orgStatChgDt) {
		this.orgStatChgDt = orgStatChgDt;
	}
	public String getAppyStrtDt() {
		return appyStrtDt;
	}
	public void setAppyStrtDt(String appyStrtDt) {
		this.appyStrtDt = appyStrtDt;
	}
	public String getAppyEndDt() {
		return appyEndDt;
	}
	public void setAppyEndDt(String appyEndDt) {
		this.appyEndDt = appyEndDt;
	}
	public String getLoanGvFlg() {
		return loanGvFlg;
	}
	public void setLoanGvFlg(String loanGvFlg) {
		this.loanGvFlg = loanGvFlg;
	}
	public String getTbiFlg() {
		return tbiFlg;
	}
	public void setTbiFlg(String tbiFlg) {
		this.tbiFlg = tbiFlg;
	}
	public String getFeePvFlg() {
		return feePvFlg;
	}
	public void setFeePvFlg(String feePvFlg) {
		this.feePvFlg = feePvFlg;
	}
	public String getPrivTpCd() {
		return privTpCd;
	}
	public void setPrivTpCd(String privTpCd) {
		this.privTpCd = privTpCd;
	}
	public String getOnlineClCd() {
		return onlineClCd;
	}
	public void setOnlineClCd(String onlineClCd) {
		this.onlineClCd = onlineClCd;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getChgrId() {
		return chgrId;
	}
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	public Date getChgDate() {
		return chgDate;
	}
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	public String getOrgRelOrd() {
		return orgRelOrd;
	}
	public void setOrgRelOrd(String orgRelOrd) {
		this.orgRelOrd = orgRelOrd;
	}
	public String getOrgHistOrd() {
		return orgHistOrd;
	}
	public void setOrgHistOrd(String orgHistOrd) {
		this.orgHistOrd = orgHistOrd;
	}
	public String getUppOrgId() {
		return uppOrgId;
	}
	public void setUppOrgId(String uppOrgId) {
		this.uppOrgId = uppOrgId;
	}
	public String getInqPermLvCd() {
		return inqPermLvCd;
	}
	public void setInqPermLvCd(String inqPermLvCd) {
		this.inqPermLvCd = inqPermLvCd;
	}
	public String getInqPermOrgId() {
		return inqPermOrgId;
	}
	public void setInqPermOrgId(String inqPermOrgId) {
		this.inqPermOrgId = inqPermOrgId;
	}
	public String getRelTpCd() {
		return relTpCd;
	}
	public void setRelTpCd(String relTpCd) {
		this.relTpCd = relTpCd;
	}
	public String getSoNm() {
		return soNm;
	}
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	public String getTpNm() {
		return tpNm;
	}
	public void setTpNm(String tpNm) {
		this.tpNm = tpNm;
	}
	public String getTpDtlNm() {
		return tpDtlNm;
	}
	public void setTpDtlNm(String tpDtlNm) {
		this.tpDtlNm = tpDtlNm;
	}
	public String getArClNm() {
		return arClNm;
	}
	public void setArClNm(String arClNm) {
		this.arClNm = arClNm;
	}
	public String getArTpNm() {
		return arTpNm;
	}
	public void setArTpNm(String arTpNm) {
		this.arTpNm = arTpNm;
	}
	public String getOrgLvNm() {
		return orgLvNm;
	}
	public void setOrgLvNm(String orgLvNm) {
		this.orgLvNm = orgLvNm;
	}
	public String getOrgStatNm() {
		return orgStatNm;
	}
	public void setOrgStatNm(String orgStatNm) {
		this.orgStatNm = orgStatNm;
	}
	public String getPrivTpNm() {
		return privTpNm;
	}
	public void setPrivTpNm(String privTpNm) {
		this.privTpNm = privTpNm;
	}
	public String getInqPermNm() {
		return inqPermNm;
	}
	public void setInqPermNm(String inqPermNm) {
		this.inqPermNm = inqPermNm;
	}
	public String getRelTpNm() {
		return relTpNm;
	}
	public void setRelTpNm(String relTpNm) {
		this.relTpNm = relTpNm;
	}
	public String getPartOrgId() {
		return partOrgId;
	}
	public void setPartOrgId(String partOrgId) {
		this.partOrgId = partOrgId;
	}
	public String getPartOrgNm() {
		return partOrgNm;
	}
	public void setPartOrgNm(String partOrgNm) {
		this.partOrgNm = partOrgNm;
	}
	public String getPartOrgLvNm() {
		return partOrgLvNm;
	}
	public void setPartOrgLvNm(String partOrgLvNm) {
		this.partOrgLvNm = partOrgLvNm;
	}
	public String getPartTpNm() {
		return partTpNm;
	}
	public void setPartTpNm(String partTpNm) {
		this.partTpNm = partTpNm;
	}
	public String getPartTpDtlNm() {
		return partTpDtlNm;
	}
	public void setPartTpDtlNm(String partTpDtlNm) {
		this.partTpDtlNm = partTpDtlNm;
	}
	public boolean isBlLoanGvFlg() {
		return blLoanGvFlg;
	}
	public void setBlLoanGvFlg(boolean blLoanGvFlg) {
		this.blLoanGvFlg = blLoanGvFlg;
	}
	public boolean isBlTbiFlg() {
		return blTbiFlg;
	}
	public void setBlTbiFlg(boolean blTbiFlg) {
		this.blTbiFlg = blTbiFlg;
	}
	public boolean isBlFeePvFlg() {
		return blFeePvFlg;
	}
	public void setBlFeePvFlg(boolean blFeePvFlg) {
		this.blFeePvFlg = blFeePvFlg;
	}
	public String getPerm() {
		return perm;
	}
	public void setPerm(String perm) {
		this.perm = perm;
	}
	public String getCommonGrp() {
		return commonGrp;
	}
	public void setCommonGrp(String commonGrp) {
		this.commonGrp = commonGrp;
	}
	public String getCommonCd() {
		return commonCd;
	}
	public void setCommonCd(String commonCd) {
		this.commonCd = commonCd;
	}
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
	public String getRefCode2() {
		return refCode2;
	}
	public void setRefCode2(String refCode2) {
		this.refCode2 = refCode2;
	}
	public String getRefCode3() {
		return refCode3;
	}
	public void setRefCode3(String refCode3) {
		this.refCode3 = refCode3;
	}
	public String getRefCode4() {
		return refCode4;
	}
	public void setRefCode4(String refCode4) {
		this.refCode4 = refCode4;
	}
	public String getLngTyp() {
		return lngTyp;
	}
	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getPopType() {
		return popType;
	}
	public void setPopType(String popType) {
		this.popType = popType;
	}
	public String getPopType2() {
		return popType2;
	}
	public void setPopType2(String popType2) {
		this.popType2 = popType2;
	}
	public String getReturnId1() {
		return returnId1;
	}
	public void setReturnId1(String returnId1) {
		this.returnId1 = returnId1;
	}
	public String getReturnId2() {
		return returnId2;
	}
	public void setReturnId2(String returnId2) {
		this.returnId2 = returnId2;
	}
	public List<String> getOrgIdList() {
		return orgIdList;
	}
	public void setOrgIdList(List<String> orgIdList) {
		this.orgIdList = orgIdList;
	}
	public String getCheckAppyDt() {
		return checkAppyDt;
	}
	public void setCheckAppyDt(String checkAppyDt) {
		this.checkAppyDt = checkAppyDt;
	}
	

}
