package com.ntels.ccbs.system.domain.configuration.codeMng;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommonGrpVO implements Serializable {
	
	private static final long serialVersionUID = -5252225744944733509L;
	
	/**
	 * 공통코드 
	 */
	private String commonGrp;
	
	/**
	 * 공통코드명 
	 */
	private String commonGrpNm;
	
	/**
	 * 시스템 ID 
	 */
	private String systemId; 
	
	/**
	 * 길이 
	 */
	private String length;
	
	/**
	 * 참조1
	 */
	private String refCode;
	
	/**
	 * 참조2
	 */
	private String refCode2;
	
	/**
	 * 참조3 
	 */
	private String refCode3;
	
	/**
	 * 비고 
	 */
	private String rmrk;
	
	/**
	 * 사용유무 
	 */
	private String useYn;
	
	/**
	 * 시스템사용여부 
	 */
	private String sysFlg;
	
	/**
	 * 등록자ID 
	 */
	private String regrId;
	
	/**
	 * 등록자명 
	 */
	private String regrNm;
	
	/**
	 * 등록일 
	 */
	private Date regDate;
	
	/**
	 * 변경자ID 
	 */
	private String chgrId;
	
	/**
	 * 변경자명
	 */
	private String chgrNm;
	
	/**
	 * 변경일 
	 */
	private Date chgDate;
	
	/**
	 * 언어리스트 
	 */
	private List<Map<String,Object>> codeLngList;
	public String getCommonGrp() {
		return commonGrp;
	}
	public void setCommonGrp(String commonGrp) {
		this.commonGrp = commonGrp;
	}
	public String getCommonGrpNm() {
		return commonGrpNm;
	}
	public void setCommonGrpNm(String commonGrpNm) {
		this.commonGrpNm = commonGrpNm;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
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
	public String getRmrk() {
		return rmrk;
	}
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSysFlg() {
		return sysFlg;
	}
	public void setSysFlg(String sysFlg) {
		this.sysFlg = sysFlg;
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
	public String getChgrNm() {
		return chgrNm;
	}
	public void setChgrNm(String chgrNm) {
		this.chgrNm = chgrNm;
	}
	public Date getChgDate() {
		return chgDate;
	}
	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}
	public List<Map<String, Object>> getCodeLngList() {
		return codeLngList;
	}
	public void setCodeLngList(List<Map<String, Object>> codeLngList) {
		this.codeLngList = codeLngList;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonGrpVO [commonGrp=");
		builder.append(commonGrp);
		builder.append(", commonGrpNm=");
		builder.append(commonGrpNm);
		builder.append(", systemId=");
		builder.append(systemId);
		builder.append(", length=");
		builder.append(length);
		builder.append(", refCode=");
		builder.append(refCode);
		builder.append(", refCode2=");
		builder.append(refCode2);
		builder.append(", refCode3=");
		builder.append(refCode3);
		builder.append(", rmrk=");
		builder.append(rmrk);
		builder.append(", useYn=");
		builder.append(useYn);
		builder.append(", sysFlg=");
		builder.append(sysFlg);
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
		builder.append(", chgDate=");
		builder.append(chgDate);
		builder.append(", codeLngList=");
		builder.append(codeLngList);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
