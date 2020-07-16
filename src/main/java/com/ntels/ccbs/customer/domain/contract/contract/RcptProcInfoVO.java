package com.ntels.ccbs.customer.domain.contract.contract;

import java.io.Serializable;
import java.util.Date;

import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class RcptProcInfoVO implements Serializable,CommonVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2778449211324678562L;
	private String rcptId; /* 접수번호 */
	private Date procDate; /* 처리일자 */
	private int procSeq; /* 처리순서 */
	private String procSoId; /* 처리사업 */
	private String procOrgId; /* 처리조직 */
	private String procUsrId; /* 처리자 */
	private String procDesc; /* 처리내용 */
	private String cnslStat; /* 상담상태 */
	private String regrId; /* 등록자ID */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자ID */
	private Date chgDate; /* 수정일시 */
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
	 * @return the procDate
	 */
	public Date getProcDate() {
		return procDate;
	}
	/**
	 * @param procDate the procDate to set
	 */
	public void setProcDate(Date procDate) {
		this.procDate = procDate;
	}
	/**
	 * @return the procSeq
	 */
	public int getProcSeq() {
		return procSeq;
	}
	/**
	 * @param procSeq the procSeq to set
	 */
	public void setProcSeq(int procSeq) {
		this.procSeq = procSeq;
	}
	/**
	 * @return the procSoId
	 */
	public String getProcSoId() {
		return procSoId;
	}
	/**
	 * @param procSoId the procSoId to set
	 */
	public void setProcSoId(String procSoId) {
		this.procSoId = procSoId;
	}
	/**
	 * @return the procOrgId
	 */
	public String getProcOrgId() {
		return procOrgId;
	}
	/**
	 * @param procOrgId the procOrgId to set
	 */
	public void setProcOrgId(String procOrgId) {
		this.procOrgId = procOrgId;
	}
	/**
	 * @return the procUsrId
	 */
	public String getProcUsrId() {
		return procUsrId;
	}
	/**
	 * @param procUsrId the procUsrId to set
	 */
	public void setProcUsrId(String procUsrId) {
		this.procUsrId = procUsrId;
	}
	/**
	 * @return the procDesc
	 */
	public String getProcDesc() {
		return procDesc;
	}
	/**
	 * @param procDesc the procDesc to set
	 */
	public void setProcDesc(String procDesc) {
		this.procDesc = procDesc;
	}
	/**
	 * @return the cnslStat
	 */
	public String getCnslStat() {
		return cnslStat;
	}
	/**
	 * @param cnslStat the cnslStat to set
	 */
	public void setCnslStat(String cnslStat) {
		this.cnslStat = cnslStat;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcptProcInfoVO [rcptId=");
		builder.append(rcptId);
		builder.append(", procDate=");
		builder.append(procDate);
		builder.append(", procSeq=");
		builder.append(procSeq);
		builder.append(", procSoId=");
		builder.append(procSoId);
		builder.append(", procOrgId=");
		builder.append(procOrgId);
		builder.append(", procUsrId=");
		builder.append(procUsrId);
		builder.append(", procDesc=");
		builder.append(procDesc);
		builder.append(", cnslStat=");
		builder.append(cnslStat);
		builder.append(", regrId=");
		builder.append(regrId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", chgrId=");
		builder.append(chgrId);
		builder.append(", chgDate=");
		builder.append(chgDate);
		builder.append("]");
		return builder.toString();
	}
			
}
