package com.ntels.ccbs.charge.domain.charge.batch;

import java.util.Date;

public class BatchCommonVO {

	/**
	 * SO_ID or RO_ID
	 */
	private String soId;

	/**
	 * 등록자ID
	 */
	private String regrId;

	/**
	 * 등록일
	 */
	private Date regDate;

	/**
	 * 변경일
	 */
	private Date chgDate;

	/**
	 * 변경자ID
	 */
	private String chgrId;

	private int page;

	private int rows;

	private int totalCount;

	private String lang;

	public String getSoId() {
		return soId == null ? "" : soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getRegrId() {
		return regrId == null ? "" : regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

	public Date getRegDate() {
		return regDate == null ? new Date() : regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getChgDate() {
		return chgDate == null ? new Date() : chgDate;
	}

	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}

	public String getChgrId() {
		return chgrId == null ? "" : chgrId;
	}

	public void setChgrId(String chgrId) {
		this.chgrId = chgrId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStart() {
		return (getPage() - 1) * getRows();
	}

	public int getEnd() {
		return getRows();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		return new Integer((int) Math.ceil((float) totalCount / (float) getRows()));
	}

	public String getLang() {
		return lang == null ? "" : lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
