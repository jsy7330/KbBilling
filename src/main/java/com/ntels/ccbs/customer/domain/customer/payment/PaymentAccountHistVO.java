package com.ntels.ccbs.customer.domain.customer.payment;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class PaymentAccountHistVO implements Serializable,CommonVO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1726622355068519997L;
	private String soId; /* 사업구분 */
	private String soNm; /*사업명*/
	private String pymAcntId; /* 납부자번호 */
	private String actDttm;   /* 시작일자 */
	private String custId; /* 고객번호 */
	private String custNm; /* 고객명*/
	private String acntNm; /* 납부자명 */
	private String zipCd; /* 우편번호 */
	private String baseAddr; /* 기본주소 */
	private String addrDtlAsMask; /* 상세주소 */
	private String addrDtl; /* 상세주소 */
	private String city;	//도시
	private String state;	//주
	private String stateNm;	// 주 코드명
	private String emlAsMask; /* 이메일 */
	private String eml; /* 이메일 */
	private String telNo; /* 전화번호 */
	private String mtelNoAsMask; /* 휴대폰번호 */
	private String mtelNo; /* 휴대폰번호 */
	private String faxNo; /* 팩스번호 */
	private String pymMthd; /* 납부방법 */
	private String pymMthdNm; /* 납부방법명 */
	private String pmcResn; /* 납부방법변경사유 */
	private String pmcResnNm; /* 납부방법변경사유명 */
	private String billMdmGiroYn; /* 청구매체지로여부 */
	private String billMdmGiroYnNm; /* 청구매체지로여부명 */
	private String billMdmEmlYn; /* 청구매체이메일여부 */
	private String billMdmEmlYnNm; /* 청구매체이메일여부명 */
	private String billMdmSmsYn; /* 청구매체SMS여부명 */
	private String billMdmSmsYnNm; /* 청구매체SMS여부명 */
	private String bnkCd; /* 은행코드 */
	private String bnkCdNm; /* 은행코드명 */
	private String acntOwnerNm; /* 예금주명 */
	private String acntNoAsMask; /* 계좌카드번호 */
	private String acntNo; /* 계좌카드번호 */
	private String cdtcdExpDt; /* 신용카드유효일자 */
	private String pymDt; /* 결제일자 */
	private String cmsCl; /* CMS구분 */
	private String tbrFlg; /* 세금계산서요청구분 */
	private String arrsNobillYn; /* 연체료미부과대상여부 */
	private String useAcntNmYn; /* 납부자명사용여부 */
	private String rcptId; /* 접수번호 */
	private String mstBnkCd; /* 주거래은행코드 */
	private String vrAcntNo; /* 가상계좌번호 */
	private String orgId; /* 등록조직ID */
	private String orgNm; /* 등록조직명 */
	private String usrId; /* 등록자ID */
	private String usrNm; /* 등록자명 */
	private String extId; /* 확장ID */
	private String regrId; /* 등록자 */
	private String regrNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자 */
	private String chgrNm; /* 수정자 */
	private String chgrOrgId; /* 수정조직ID */
	private String chgrOrgNm; /* 수정조직명 */
	private Date chgDate; /* 변경일시 */
	private String billCyclCd; /*납부주기*/
	private String billCyclCdNm; /*납부주기*/
	/**
	 * @return the soId
	 */
	public String getSoId() {
		return soId;
	}
	/**
	 * @param soId the soId to set
	 */
	public void setSoId(String soId) {
		this.soId = soId;
	}
	
	/**
	 * @return the soNm
	 */
	public String getSoNm() {
		return soNm;
	}
	/**
	 * @param soNm the soNm to set
	 */
	public void setSoNm(String soNm) {
		this.soNm = soNm;
	}
	/**
	 * @return the pymAcntId
	 */
	public String getPymAcntId() {
		return pymAcntId;
	}
	/**
	 * @param pymAcntId the pymAcntId to set
	 */
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	/**
	 * @return the actDttm
	 */
	public String getActDttm() {
		return actDttm;
	}
	/**
	 * @param actDttm the actDttm to set
	 */
	public void setActDttm(String actDttm) {
		this.actDttm = actDttm;
	}
	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return the custNm
	 */
	public String getCustNm() {
		return custNm;
	}
	/**
	 * @param custNm the custNm to set
	 */
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	/**
	 * @return the acntNm
	 */
	public String getAcntNm() {
		return acntNm;
	}
	/**
	 * @param acntNm the acntNm to set
	 */
	public void setAcntNm(String acntNm) {
		this.acntNm = acntNm;
	}
	/**
	 * @return the zipCd
	 */
	public String getZipCd() {
		return zipCd;
	}
	/**
	 * @param zipCd the zipCd to set
	 */
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	/**
	 * @return the baseAddr
	 */
	public String getBaseAddr() {
		return baseAddr;
	}
	/**
	 * @param baseAddr the baseAddr to set
	 */
	public void setBaseAddr(String baseAddr) {
		this.baseAddr = baseAddr;
	}
	/**
	 * @return the addrDtlAsMask
	 */
	public String getAddrDtlAsMask() {
		return addrDtlAsMask;
	}
	/**
	 * @param addrDtlAsMask the addrDtlAsMask to set
	 */
	public void setAddrDtlAsMask(String addrDtlAsMask) {
		this.addrDtlAsMask = addrDtlAsMask;
	}
	/**
	 * @return the addrDtl
	 */
	public String getAddrDtl() {
		return addrDtl;
	}
	/**
	 * @param addrDtl the addrDtl to set
	 */
	public void setAddrDtl(String addrDtl) {
		this.addrDtl = addrDtl;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the stateNm
	 */
	public String getStateNm() {
		return stateNm;
	}
	/**
	 * @param stateNm the stateNm to set
	 */
	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
	}
	/**
	 * @return the emlAsMask
	 */
	public String getEmlAsMask() {
		return emlAsMask;
	}
	/**
	 * @param emlAsMask the emlAsMask to set
	 */
	public void setEmlAsMask(String emlAsMask) {
		this.emlAsMask = emlAsMask;
	}
	/**
	 * @return the eml
	 */
	public String getEml() {
		return eml;
	}
	/**
	 * @param eml the eml to set
	 */
	public void setEml(String eml) {
		this.eml = eml;
	}
	/**
	 * @return the telNo
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * @param telNo the telNo to set
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * @return the mtelNoAsMask
	 */
	public String getMtelNoAsMask() {
		return mtelNoAsMask;
	}
	/**
	 * @param mtelNoAsMask the mtelNoAsMask to set
	 */
	public void setMtelNoAsMask(String mtelNoAsMask) {
		this.mtelNoAsMask = mtelNoAsMask;
	}
	/**
	 * @return the mtelNo
	 */
	public String getMtelNo() {
		return mtelNo;
	}
	/**
	 * @param mtelNo the mtelNo to set
	 */
	public void setMtelNo(String mtelNo) {
		this.mtelNo = mtelNo;
	}
	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}
	/**
	 * @param faxNo the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	/**
	 * @return the pymMthd
	 */
	public String getPymMthd() {
		return pymMthd;
	}
	/**
	 * @param pymMthd the pymMthd to set
	 */
	public void setPymMthd(String pymMthd) {
		this.pymMthd = pymMthd;
	}
	/**
	 * @return the pymMthdNm
	 */
	public String getPymMthdNm() {
		return pymMthdNm;
	}
	/**
	 * @param pymMthdNm the pymMthdNm to set
	 */
	public void setPymMthdNm(String pymMthdNm) {
		this.pymMthdNm = pymMthdNm;
	}
	/**
	 * @return the pmcResn
	 */
	public String getPmcResn() {
		return pmcResn;
	}
	/**
	 * @param pmcResn the pmcResn to set
	 */
	public void setPmcResn(String pmcResn) {
		this.pmcResn = pmcResn;
	}
	/**
	 * @return the pmcResnNm
	 */
	public String getPmcResnNm() {
		return pmcResnNm;
	}
	/**
	 * @param pmcResnNm the pmcResnNm to set
	 */
	public void setPmcResnNm(String pmcResnNm) {
		this.pmcResnNm = pmcResnNm;
	}
	/**
	 * @return the billMdmGiroYn
	 */
	public String getBillMdmGiroYn() {
		return billMdmGiroYn;
	}
	/**
	 * @param billMdmGiroYn the billMdmGiroYn to set
	 */
	public void setBillMdmGiroYn(String billMdmGiroYn) {
		this.billMdmGiroYn = billMdmGiroYn;
	}
	/**
	 * @return the billMdmGiroYnNm
	 */
	public String getBillMdmGiroYnNm() {
		return billMdmGiroYnNm;
	}
	/**
	 * @param billMdmGiroYnNm the billMdmGiroYnNm to set
	 */
	public void setBillMdmGiroYnNm(String billMdmGiroYnNm) {
		this.billMdmGiroYnNm = billMdmGiroYnNm;
	}
	/**
	 * @return the billMdmEmlYn
	 */
	public String getBillMdmEmlYn() {
		return billMdmEmlYn;
	}
	/**
	 * @param billMdmEmlYn the billMdmEmlYn to set
	 */
	public void setBillMdmEmlYn(String billMdmEmlYn) {
		this.billMdmEmlYn = billMdmEmlYn;
	}
	/**
	 * @return the billMdmEmlYnNm
	 */
	public String getBillMdmEmlYnNm() {
		return billMdmEmlYnNm;
	}
	/**
	 * @param billMdmEmlYnNm the billMdmEmlYnNm to set
	 */
	public void setBillMdmEmlYnNm(String billMdmEmlYnNm) {
		this.billMdmEmlYnNm = billMdmEmlYnNm;
	}
	/**
	 * @return the billMdmSmsYn
	 */
	public String getBillMdmSmsYn() {
		return billMdmSmsYn;
	}
	/**
	 * @param billMdmSmsYn the billMdmSmsYn to set
	 */
	public void setBillMdmSmsYn(String billMdmSmsYn) {
		this.billMdmSmsYn = billMdmSmsYn;
	}
	/**
	 * @return the billMdmSmsYnNm
	 */
	public String getBillMdmSmsYnNm() {
		return billMdmSmsYnNm;
	}
	/**
	 * @param billMdmSmsYnNm the billMdmSmsYnNm to set
	 */
	public void setBillMdmSmsYnNm(String billMdmSmsYnNm) {
		this.billMdmSmsYnNm = billMdmSmsYnNm;
	}
	/**
	 * @return the bnkCd
	 */
	public String getBnkCd() {
		return bnkCd;
	}
	/**
	 * @param bnkCd the bnkCd to set
	 */
	public void setBnkCd(String bnkCd) {
		this.bnkCd = bnkCd;
	}
	/**
	 * @return the bnkCdNm
	 */
	public String getBnkCdNm() {
		return bnkCdNm;
	}
	/**
	 * @param bnkCdNm the bnkCdNm to set
	 */
	public void setBnkCdNm(String bnkCdNm) {
		this.bnkCdNm = bnkCdNm;
	}
	/**
	 * @return the acntOwnerNm
	 */
	public String getAcntOwnerNm() {
		return acntOwnerNm;
	}
	/**
	 * @param acntOwnerNm the acntOwnerNm to set
	 */
	public void setAcntOwnerNm(String acntOwnerNm) {
		this.acntOwnerNm = acntOwnerNm;
	}
	/**
	 * @return the acntNoAsMask
	 */
	public String getAcntNoAsMask() {
		return acntNoAsMask;
	}
	/**
	 * @param acntNoAsMask the acntNoAsMask to set
	 */
	public void setAcntNoAsMask(String acntNoAsMask) {
		this.acntNoAsMask = acntNoAsMask;
	}
	/**
	 * @return the acntNo
	 */
	public String getAcntNo() {
		return acntNo;
	}
	/**
	 * @param acntNo the acntNo to set
	 */
	public void setAcntNo(String acntNo) {
		this.acntNo = acntNo;
	}
	/**
	 * @return the cdtcdExpDt
	 */
	public String getCdtcdExpDt() {
		return cdtcdExpDt;
	}
	/**
	 * @param cdtcdExpDt the cdtcdExpDt to set
	 */
	public void setCdtcdExpDt(String cdtcdExpDt) {
		this.cdtcdExpDt = cdtcdExpDt;
	}
	/**
	 * @return the pymDt
	 */
	public String getPymDt() {
		return pymDt;
	}
	/**
	 * @param pymDt the pymDt to set
	 */
	public void setPymDt(String pymDt) {
		this.pymDt = pymDt;
	}
	/**
	 * @return the cmsCl
	 */
	public String getCmsCl() {
		return cmsCl;
	}
	/**
	 * @param cmsCl the cmsCl to set
	 */
	public void setCmsCl(String cmsCl) {
		this.cmsCl = cmsCl;
	}
	/**
	 * @return the tbrFlg
	 */
	public String getTbrFlg() {
		return tbrFlg;
	}
	/**
	 * @param tbrFlg the tbrFlg to set
	 */
	public void setTbrFlg(String tbrFlg) {
		this.tbrFlg = tbrFlg;
	}
	/**
	 * @return the arrsNobillYn
	 */
	public String getArrsNobillYn() {
		return arrsNobillYn;
	}
	/**
	 * @param arrsNobillYn the arrsNobillYn to set
	 */
	public void setArrsNobillYn(String arrsNobillYn) {
		this.arrsNobillYn = arrsNobillYn;
	}
	/**
	 * @return the useAcntNmYn
	 */
	public String getUseAcntNmYn() {
		return useAcntNmYn;
	}
	/**
	 * @param useAcntNmYn the useAcntNmYn to set
	 */
	public void setUseAcntNmYn(String useAcntNmYn) {
		this.useAcntNmYn = useAcntNmYn;
	}
	/**
	 * @return the rcptId
	 */
	public String getRcptId() {
		return rcptId;
	}
	/**
	 * @param rcptId the rcptId to set
	 */
	public void setRcptId(String rcptId) {
		this.rcptId = rcptId;
	}
	/**
	 * @return the mstBnkCd
	 */
	public String getMstBnkCd() {
		return mstBnkCd;
	}
	/**
	 * @param mstBnkCd the mstBnkCd to set
	 */
	public void setMstBnkCd(String mstBnkCd) {
		this.mstBnkCd = mstBnkCd;
	}
	/**
	 * @return the vrAcntNo
	 */
	public String getVrAcntNo() {
		return vrAcntNo;
	}
	/**
	 * @param vrAcntNo the vrAcntNo to set
	 */
	public void setVrAcntNo(String vrAcntNo) {
		this.vrAcntNo = vrAcntNo;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the orgNm
	 */
	public String getOrgNm() {
		return orgNm;
	}
	/**
	 * @param orgNm the orgNm to set
	 */
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	/**
	 * @return the usrId
	 */
	public String getUsrId() {
		return usrId;
	}
	/**
	 * @param usrId the usrId to set
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	/**
	 * @return the usrNm
	 */
	public String getUsrNm() {
		return usrNm;
	}
	/**
	 * @param usrNm the usrNm to set
	 */
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	/**
	 * @return the extId
	 */
	public String getExtId() {
		return extId;
	}
	/**
	 * @param extId the extId to set
	 */
	public void setExtId(String extId) {
		this.extId = extId;
	}
	/**
	 * @return the regrId
	 */
	public String getRegrId() {
		return regrId;
	}
	/**
	 * @param regrId the regrId to set
	 */
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	/**
	 * @return the regrNm
	 */
	public String getRegrNm() {
		return regrNm;
	}
	/**
	 * @param regrNm the regrNm to set
	 */
	public void setRegrNm(String regrNm) {
		this.regrNm = regrNm;
	}
	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the chgrId
	 */
	public String getChgrId() {
		return chgrId;
	}
	/**
	 * @param chgrId the chgrId to set
	 */
	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}
	/**
	 * @return the chgrNm
	 */
	public String getChgrNm() {
		return chgrNm;
	}
	/**
	 * @param chgrNm the chgrNm to set
	 */
	public void setChgrNm(String chgrNm) {
		this.chgrNm = chgrNm;
	}
	/**
	 * @return the chgrOrgId
	 */
	public String getChgrOrgId() {
		return chgrOrgId;
	}
	/**
	 * @param chgrOrgId the chgrOrgId to set
	 */
	public void setChgrOrgId(String chgrOrgId) {
		this.chgrOrgId = chgrOrgId;
	}
	/**
	 * @return the chgrOrgNm
	 */
	public String getChgrOrgNm() {
		return chgrOrgNm;
	}
	/**
	 * @param chgrOrgNm the chgrOrgNm to set
	 */
	public void setChgrOrgNm(String chgrOrgNm) {
		this.chgrOrgNm = chgrOrgNm;
	}
	/**
	 * @return the chgDate
	 */
	public Date getChgDate() {
		return chgDate;
	}
	/**
	 * @param chgDate the chgDate to set
	 */
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	/**
	 * @return the billCyclCd
	 */
	public String getBillCyclCd() {
		return billCyclCd;
	}
	/**
	 * @param billCyclCd the billCyclCd to set
	 */
	public void setBillCyclCd(String billCyclCd) {
		this.billCyclCd = billCyclCd;
	}
	/**
	 * @return the billCyclCdNm
	 */
	public String getBillCyclCdNm() {
		return billCyclCdNm;
	}
	/**
	 * @param billCyclCdNm the billCyclCdNm to set
	 */
	public void setBillCyclCdNm(String billCyclCdNm) {
		this.billCyclCdNm = billCyclCdNm;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PymAcntSearchVO [soId=");
		builder.append(soId);
		builder.append(", pymAcntId=");
		builder.append(pymAcntId);
		builder.append(", actDttm=");
		builder.append(actDttm);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", custNm=");
		builder.append(custNm);
		builder.append(", acntNm=");
		builder.append(acntNm);
		builder.append(", zipCd=");
		builder.append(zipCd);
		builder.append(", baseAddr=");
		builder.append(baseAddr);
		builder.append(", addrDtlAsMask=");
		builder.append(addrDtlAsMask);
		builder.append(", addrDtl=");
		builder.append(addrDtl);
		builder.append(", emlAsMask=");
		builder.append(emlAsMask);
		builder.append(", eml=");
		builder.append(eml);
		builder.append(", telNo=");
		builder.append(telNo);
		builder.append(", mtelNoAsMask=");
		builder.append(mtelNoAsMask);
		builder.append(", mtelNo=");
		builder.append(mtelNo);
		builder.append(", faxNo=");
		builder.append(faxNo);
		builder.append(", pymMthd=");
		builder.append(pymMthd);
		builder.append(", pymMthdNm=");
		builder.append(pymMthdNm);
		builder.append(", pmcResn=");
		builder.append(pmcResn);
		builder.append(", pmcResnNm=");
		builder.append(pmcResnNm);
		builder.append(", billMdmGiroYn=");
		builder.append(billMdmGiroYn);
		builder.append(", billMdmGiroYnNm=");
		builder.append(billMdmGiroYnNm);
		builder.append(", billMdmEmlYn=");
		builder.append(billMdmEmlYn);
		builder.append(", billMdmEmlYnNm=");
		builder.append(billMdmEmlYnNm);
		builder.append(", billMdmSmsYn=");
		builder.append(billMdmSmsYn);
		builder.append(", billMdmSmsYnNm=");
		builder.append(billMdmSmsYnNm);
		builder.append(", bnkCd=");
		builder.append(bnkCd);
		builder.append(", bnkCdNm=");
		builder.append(bnkCdNm);
		builder.append(", acntOwnerNm=");
		builder.append(acntOwnerNm);
		builder.append(", acntNoAsMask=");
		builder.append(acntNoAsMask);
		builder.append(", acntNo=");
		builder.append(acntNo);
		builder.append(", cdtcdExpDt=");
		builder.append(cdtcdExpDt);
		builder.append(", pymDt=");
		builder.append(pymDt);
		builder.append(", cmsCl=");
		builder.append(cmsCl);
		builder.append(", tbrFlg=");
		builder.append(tbrFlg);
		builder.append(", arrsNobillYn=");
		builder.append(arrsNobillYn);
		builder.append(", useAcntNmYn=");
		builder.append(useAcntNmYn);
		builder.append(", rcptId=");
		builder.append(rcptId);
		builder.append(", mstBnkCd=");
		builder.append(mstBnkCd);
		builder.append(", vrAcntNo=");
		builder.append(vrAcntNo);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", orgNm=");
		builder.append(orgNm);
		builder.append(", usrId=");
		builder.append(usrId);
		builder.append(", usrNm=");
		builder.append(usrNm);
		builder.append(", extId=");
		builder.append(extId);
		builder.append(", regrId=");
		builder.append(regrId);
		builder.append(", regrNm=");
		builder.append(regrNm);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", chgrId=");
		builder.append(chgrId);
		builder.append(", chgrNm=");
		builder.append(chgrNm);
		builder.append(", chgrOrgId=");
		builder.append(chgrOrgId);
		builder.append(", chgrOrgNm=");
		builder.append(chgrOrgNm);
		builder.append(", chgDate=");
		builder.append(chgDate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
