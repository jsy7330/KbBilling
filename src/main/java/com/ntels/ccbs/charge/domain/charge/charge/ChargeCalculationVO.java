package com.ntels.ccbs.charge.domain.charge.charge;

import java.io.Serializable;
import java.util.Date;
import com.ntels.ccbs.system.domain.common.service.CommonVO;

public class ChargeCalculationVO implements Serializable,CommonVO{

	private String soId; /* 사업구분 */
	private String regrId; /* 등록자ID */
	private String regrNm; /* 등록자 */
	private Date regDate; /* 등록일시 */
	private String chgrId; /* 수정자ID */
	private String chgrNm; /* 수정자명 */
	private Date chgDate; /* 변경일시 */
	private String commonCd;
	private String commonCdNm;
	
	
	private String sidx;
	private String sord;
	private int page;
	private int rows;
	private String lng;
	private String today;
	
	private String condBillYymm;
	private String condPymAcntId;
	private String condCustId;
	private String condCtrtId;
	private String condWorkGrpId;
	private String condProdCd;
	
	
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
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
	public String getCondBillYymm() {
		return condBillYymm;
	}
	public void setCondBillYymm(String condBillYymm) {
		this.condBillYymm = condBillYymm;
	}
	public String getCondPymAcntId() {
		return condPymAcntId;
	}
	public void setCondPymAcntId(String condPymAcntId) {
		this.condPymAcntId = condPymAcntId;
	}
	public String getCondCustId() {
		return condCustId;
	}
	public void setCondCustId(String condCustId) {
		this.condCustId = condCustId;
	}
	public String getCondCtrtId() {
		return condCtrtId;
	}
	public void setCondCtrtId(String condCtrtId) {
		this.condCtrtId = condCtrtId;
	}
	public String getCondWorkGrpId() {
		return condWorkGrpId;
	}
	public void setCondWorkGrpId(String condWorkGrpId) {
		this.condWorkGrpId = condWorkGrpId;
	}
	public String getCondProdCd() {
		return condProdCd;
	}
	public void setCondProdCd(String condProdCd) {
		this.condProdCd = condProdCd;
	}
	public String getSoId() {
		return soId;
	}
	public void setSoId(String soId) {
		this.soId = soId;
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
	
}
