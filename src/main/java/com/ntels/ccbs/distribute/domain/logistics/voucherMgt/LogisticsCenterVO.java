package com.ntels.ccbs.distribute.domain.logistics.voucherMgt;

import java.io.Serializable;

import com.ntels.ccbs.system.domain.common.service.CommonVO;
import com.ntels.ccbs.system.domain.common.service.Paging;

public class LogisticsCenterVO extends Paging implements Serializable, CommonVO {

	private static final long serialVersionUID = 1L;

	private String soId;
	private String orgId;
	private String orgNm;
	private String usrId;
	private String usrNm;
	private String empNo;
	private String empNm;
	private String tpCd;
	private String tpNm;
	private String tpDtlCd;
	private String tpDtlNm;
	private String arTpCd;
	private String arTpNm;
	private String arClCd;
	private String arClNm;
	private String inqPermCd;
	private String inqPermNm;
	private String orgLvCd;
	private String orgLvNm;
	private String cellNo;
	private String telNo;
	private String zipCd;
	private String addr1;
	private String addr2;
	private String usrEml;

	private String lngTyp;

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

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpNm() {
		return empNm;
	}

	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

	public String getTpCd() {
		return tpCd;
	}

	public void setTpCd(String tpCd) {
		this.tpCd = tpCd;
	}

	public String getTpNm() {
		return tpNm;
	}

	public void setTpNm(String tpNm) {
		this.tpNm = tpNm;
	}

	public String getTpDtlCd() {
		return tpDtlCd;
	}

	public void setTpDtlCd(String tpDtlCd) {
		this.tpDtlCd = tpDtlCd;
	}

	public String getTpDtlNm() {
		return tpDtlNm;
	}

	public void setTpDtlNm(String tpDtlNm) {
		this.tpDtlNm = tpDtlNm;
	}

	public String getArTpCd() {
		return arTpCd;
	}

	public void setArTpCd(String arTpCd) {
		this.arTpCd = arTpCd;
	}

	public String getArTpNm() {
		return arTpNm;
	}

	public void setArTpNm(String arTpNm) {
		this.arTpNm = arTpNm;
	}

	public String getArClCd() {
		return arClCd;
	}

	public void setArClCd(String arClCd) {
		this.arClCd = arClCd;
	}

	public String getArClNm() {
		return arClNm;
	}

	public void setArClNm(String arClNm) {
		this.arClNm = arClNm;
	}

	public String getInqPermCd() {
		return inqPermCd;
	}

	public void setInqPermCd(String inqPermCd) {
		this.inqPermCd = inqPermCd;
	}

	public String getInqPermNm() {
		return inqPermNm;
	}

	public void setInqPermNm(String inqPermNm) {
		this.inqPermNm = inqPermNm;
	}

	public String getOrgLvCd() {
		return orgLvCd;
	}

	public void setOrgLvCd(String orgLvCd) {
		this.orgLvCd = orgLvCd;
	}

	public String getOrgLvNm() {
		return orgLvNm;
	}

	public void setOrgLvNm(String orgLvNm) {
		this.orgLvNm = orgLvNm;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getZipCd() {
		return zipCd;
	}

	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getUsrEml() {
		return usrEml;
	}

	public void setUsrEml(String usrEml) {
		this.usrEml = usrEml;
	}

	public String getLngTyp() {
		return lngTyp;
	}

	public void setLngTyp(String lngTyp) {
		this.lngTyp = lngTyp;
	}

}
