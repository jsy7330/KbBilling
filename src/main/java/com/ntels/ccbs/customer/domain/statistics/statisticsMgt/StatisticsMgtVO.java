package com.ntels.ccbs.customer.domain.statistics.statisticsMgt;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class StatisticsMgtVO implements Serializable,CommonVO{

	private String stmtTp;		//Sql 타입 
	private String stmtCd;		//통계관리 코드
	private String inactDt;		//적용 종료일
	private String actDt;		//적용 시작일
	private String soId;		//사업ID	
	private String stmtNm;		//sql 명
	private String stmt;		//sql 내용
	private String varDefn;		//선택한 변수 정의
	private String tmout;		//질의제한시간
	private String vldYn;		//
	private String rmrkDesc;	//비고
	private String regrId;		//등록자
	private String regrNm;		//등록자명
	private String regDate;		//등록일
	private String chgrId;		//수정자
	private String chgrNm;		//수정자명
	private String chgDate;		//수정일
	private String searchStsCd;	//검색통계명
	private String commonCd;
	private String commonCdNm;
	private String searchSoId;
	private String searchStmtTp;	//검색sql 타입
	
	//검색 조건
	private String acntId;
	private String strtDd;
	private String endDd;
	private String svcTelNo;
	private String billBgnMth;
	private String billEndMth;
	private String ctrtStat;
	private String custId;
	private String openBgnDd;
	private String openEndDd;
	private String searchDd;
	
	public String getStmtTp() {
		return stmtTp;
	}
	public void setStmtTp(String stmtTp) {
		this.stmtTp = stmtTp;
	}
	public String getStmtCd() {
		return stmtCd;
	}
	public void setStmtCd(String stmtCd) {
		this.stmtCd = stmtCd;
	}
	public String getInactDt() {
		return inactDt;
	}
	public void setInactDt(String inactDt) {
		this.inactDt = inactDt;
	}
	public String getActDt() {
		return actDt;
	}
	public void setActDt(String actDt) {
		this.actDt = actDt;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getStmtNm() {
		return stmtNm;
	}
	public void setStmtNm(String stmtNm) {
		this.stmtNm = stmtNm;
	}
	public String getStmt() {
		return stmt;
	}
	public void setStmt(String stmt) {
		this.stmt = stmt;
	}
	public String getVarDefn() {
		return varDefn;
	}
	public void setVarDefn(String varDefn) {
		this.varDefn = varDefn;
	}
	public String getTmout() {
		return tmout;
	}
	public void setTmout(String tmout) {
		this.tmout = tmout;
	}
	public String getVldYn() {
		return vldYn;
	}
	public void setVldYn(String vldYn) {
		this.vldYn = vldYn;
	}
	public String getRmrkDesc() {
		return rmrkDesc;
	}
	public void setRmrkDesc(String rmrkDesc) {
		this.rmrkDesc = rmrkDesc;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getRegrNm() {
		return regrNm;
	}
	public void setRegrNm(String regrNm) {
		this.regrNm = regrNm;
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
	public String getChgrNm() {
		return chgrNm;
	}
	public void setChgrNm(String chgrNm) {
		this.chgrNm = chgrNm;
	}
	public String getChgDate() {
		return chgDate;
	}
	public void setChgDate(String chgDate) {
		this.chgDate = chgDate;
	}
	public String getSearchStsCd() {
		return searchStsCd;
	}
	public void setSearchStsCd(String searchStsCd) {
		this.searchStsCd = searchStsCd;
	}
	public String getSearchStmtTp() {
		return searchStmtTp;
	}
	public void setSearchStmtTp(String searchStmtTp) {
		this.searchStmtTp = searchStmtTp;
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
	public String getSearchSoId() {
		return searchSoId;
	}
	public void setSearchSoId(String searchSoId) {
		this.searchSoId = searchSoId;
	}
	public String getAcntId() {
		return acntId;
	}
	public void setAcntId(String acntId) {
		this.acntId = acntId;
	}
	public String getStrtDd() {
		return strtDd;
	}
	public void setStrtDd(String strtDd) {
		this.strtDd = strtDd;
	}
	public String getEndDd() {
		return endDd;
	}
	public void setEndDd(String endDd) {
		this.endDd = endDd;
	}
	public String getSvcTelNo() {
		return svcTelNo;
	}
	public void setSvcTelNo(String svcTelNo) {
		this.svcTelNo = svcTelNo;
	}
	public String getBillBgnMth() {
		return billBgnMth;
	}
	public void setBillBgnMth(String billBgnMth) {
		this.billBgnMth = billBgnMth;
	}
	public String getBillEndMth() {
		return billEndMth;
	}
	public void setBillEndMth(String billEndMth) {
		this.billEndMth = billEndMth;
	}
	public String getCtrtStat() {
		return ctrtStat;
	}
	public void setCtrtStat(String ctrtStat) {
		this.ctrtStat = ctrtStat;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getOpenBgnDd() {
		return openBgnDd;
	}
	public void setOpenBgnDd(String openBgnDd) {
		this.openBgnDd = openBgnDd;
	}
	public String getOpenEndDd() {
		return openEndDd;
	}
	public void setOpenEndDd(String openEndDd) {
		this.openEndDd = openEndDd;
	}
	public String getSearchDd() {
		return searchDd;
	}
	public void setSearchDd(String searchDd) {
		this.searchDd = searchDd;
	}
}
