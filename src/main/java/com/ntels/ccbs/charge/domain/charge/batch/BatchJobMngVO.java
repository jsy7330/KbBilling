package com.ntels.ccbs.charge.domain.charge.batch;

import java.io.Serializable;
import java.util.Date;
import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class BatchJobMngVO implements Serializable,CommonVO{

	private String soId; /* 사업구분 */
	private String clsTskCl;
	private String billYymm;
	private String billCycl;
	private String clsYn;
	private String clsDt;
	private String clsTskClNm;
	private String regrId; /* 등록자ID */
	private String regrNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자ID */
	private String chgrNm; /* 수정자명 */
	private Date chgDate; /* 변경일시 */
	
	private String commonCd;
	private String commonCdNm;
	private String comdSoId;
	private String condBillYymm;
	private String condEndCd;
	private String today;
	
	private String lng;
	private int page;
	private int rows;
	private int totalCount;
	private String lang;
	private int start;
	private int end;
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
	}
	public String getClsTskCl() {
		return clsTskCl;
	}
	public void setClsTskCl(String clsTskCl) {
		this.clsTskCl = clsTskCl;
	}
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getBillCycl() {
		return billCycl;
	}
	public void setBillCycl(String billCycl) {
		this.billCycl = billCycl;
	}
	public String getClsYn() {
		return clsYn;
	}
	public void setClsYn(String clsYn) {
		this.clsYn = clsYn;
	}
	public String getClsDt() {
		return clsDt;
	}
	public void setClsDt(String clsDt) {
		this.clsDt = clsDt;
	}
	public String getClsTskClNm() {
		return clsTskClNm;
	}
	public void setClsTskClNm(String clsTskClNm) {
		this.clsTskClNm = clsTskClNm;
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
	public String getComdSoId() {
		return comdSoId;
	}
	public void setComdSoId(String comdSoId) {
		this.comdSoId = comdSoId;
	}
	public String getCondBillYymm() {
		return condBillYymm;
	}
	public void setCondBillYymm(String condBillYymm) {
		this.condBillYymm = condBillYymm;
	}
	public String getCondEndCd() {
		return condEndCd;
	}
	public void setCondEndCd(String condEndCd) {
		this.condEndCd = condEndCd;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
}
