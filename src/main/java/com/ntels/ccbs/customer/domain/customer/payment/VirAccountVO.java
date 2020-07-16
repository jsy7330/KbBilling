package com.ntels.ccbs.customer.domain.customer.payment;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class VirAccountVO implements Serializable,CommonVO{

	
	private static final long serialVersionUID = -4988858033800553926L;
	private String soId; /* 사업구분 */
	private String bnkCd; /* 은행코드 */
	private String bnkCdNm; /* 은행명 */
	private String vrAcntNo; /* 가상계좌번호 */
	private String vrAcntNoStat; /* 가상계좌상태*/
	private String vrAcntNoStatNm; /* 가상계좌상태*/
	private String pymAcntId; /* 납부계정 */
	private String qtaDt; /* 할당일자 */
	private String regrId; /* 등록자 */
	private String regrNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자 */
	private String chgrNm; /* 수정자 */
	private Date chgDate; /* 변경일시 */
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getBnkCd() {
		return bnkCd;
	}
	public void setBnkCd(String bnkCd) {
		this.bnkCd = bnkCd;
	}
	public String getBnkCdNm() {
		return bnkCdNm;
	}
	public void setBnkCdNm(String bnkCdNm) {
		this.bnkCdNm = bnkCdNm;
	}
	public String getVrAcntNo() {
		return vrAcntNo;
	}
	public void setVrAcntNo(String vrAcntNo) {
		this.vrAcntNo = vrAcntNo;
	}
	public String getVrAcntNoStat() {
		return vrAcntNoStat;
	}
	public void setVrAcntNoStat(String vrAcntNoStat) {
		this.vrAcntNoStat = vrAcntNoStat;
	}
	public String getVrAcntNoStatNm() {
		return vrAcntNoStatNm;
	}
	public void setVrAcntNoStatNm(String vrAcntNoStatNm) {
		this.vrAcntNoStatNm = vrAcntNoStatNm;
	}
	public String getPymAcntId() {
		return pymAcntId;
	}
	public void setPymAcntId(String pymAcntId) {
		this.pymAcntId = pymAcntId;
	}
	public String getQtaDt() {
		return qtaDt;
	}
	public void setQtaDt(String qtaDt) {
		this.qtaDt = qtaDt;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VirAccountVO [soId=");
		builder.append(soId);
		builder.append(", bnkCd=");
		builder.append(bnkCd);
		builder.append(", bnkCdNm=");
		builder.append(bnkCdNm);
		builder.append(", vrAcntNo=");
		builder.append(vrAcntNo);
		builder.append(", vrAcntNoStat=");
		builder.append(vrAcntNoStat);
		builder.append(", vrAcntNoStatNm=");
		builder.append(vrAcntNoStatNm);
		builder.append(", pymAcntId=");
		builder.append(pymAcntId);
		builder.append(", qtaDt=");
		builder.append(qtaDt);
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
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
