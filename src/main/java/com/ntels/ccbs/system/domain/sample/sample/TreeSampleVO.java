package com.ntels.ccbs.system.domain.sample.sample;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("treeSample")
public class TreeSampleVO {
	/**
	 * 메뉴번호
	 */
	private String menuNo;
	/**
	 * 메뉴명
	 */
	private String menuName;
	/**
	 * 상위메뉴번호
	 */
	private String upMenuNo;
	/**
	 * URL
	 */
	private String viewPath;
	/**
	 * 출력순서
	 */
	private String displayOrder;
	/**
	 * 설명
	 */
	private String description;
	/**
	 * 사용여부
	 */
	private String useYn;
	/**
	 * 상위메뉴명
	 */
	private String upMenuName;
	/**
	 * 레벨
	 */
	private String stepNo;
	/**
	 * 최상위(ROOT)확인 여부
	 */
	private String topMenu;
	/**
	 * 다국어명 리스트
	 */
	private List<Map<String,Object>> menuLngList;
	/**
	 * 다국어별 메뉴명
	 */
	private String menuNm;
	/**
	 * 다국어명
	 */
	private String lngNm;
	/**
	 * 지역코드
	 */
	private String cntryCd;
	/**
	 * 다국어코드
	 */
	private String lngCd; 
	
	/**
	 * 서비스코드
	 */
	private String svcCd; 	

	private int cnt;
	

	private String code;
	
	private String rootYn;

	private String folder;

	private String title;
	private String grpCode;
	private String prodList;

	private String prodCd;
	private String prodNm;

	private String seq;
	private String colTp;
	private String colSize;
	private String commonGrp;
	private String tableId;
	private String columnId; 
	private String soId; 
	private String prodGrp; 
	private String qry;
	private String StartDt;
	private String orderId;
	private String seqNo;		
	private String rateItmCd;								
	private String unitPrice;	
	private String billAmt;								
	private String chrgCtgry;								
	private String disTp;								
	private String disUnit;								
	private String disTarget;								
	private String ratePriNo;
	private String retV;
	private String rateItmNm;
	
	private String basicYn;
	

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getRateItmCd() {
		return rateItmCd;
	}

	public void setRateItmCd(String rateItmCd) {
		this.rateItmCd = rateItmCd;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}

	public String getChrgCtgry() {
		return chrgCtgry;
	}

	public void setChrgCtgry(String chrgCtgry) {
		this.chrgCtgry = chrgCtgry;
	}

	public String getDisTp() {
		return disTp;
	}

	public void setDisTp(String disTp) {
		this.disTp = disTp;
	}

	public String getDisUnit() {
		return disUnit;
	}

	public void setDisUnit(String disUnit) {
		this.disUnit = disUnit;
	}

	public String getDisTarget() {
		return disTarget;
	}

	public void setDisTarget(String disTarget) {
		this.disTarget = disTarget;
	}

	public String getRatePriNo() {
		return ratePriNo;
	}

	public void setRatePriNo(String ratePriNo) {
		this.ratePriNo = ratePriNo;
	}

	/**
	 * 권한을 가진 SO 리스트
	 */
	private List<Map<String,Object>> stepList;
	
	/**
	 * grid에서 보여질 menu_name
	 */
	private String cdMenuName;
	
	private List<CommonCodeVO> commonCodeList;

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUpMenuNo() {
		return upMenuNo;
	}

	public void setUpMenuNo(String upMenuNo) {
		this.upMenuNo = upMenuNo;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getUpMenuName() {
		return upMenuName;
	}

	public void setUpMenuName(String upMenuName) {
		this.upMenuName = upMenuName;
	}

	public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public String getTopMenu() {
		return topMenu;
	}

	public void setTopMenu(String topMenu) {
		this.topMenu = topMenu;
	}

	public List<Map<String, Object>> getMenuLngList() {
		return menuLngList;
	}

	public void setMenuLngList(List<Map<String, Object>> menuLngList) {
		this.menuLngList = menuLngList;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getLngNm() {
		return lngNm;
	}

	public void setLngNm(String lngNm) {
		this.lngNm = lngNm;
	}

	public String getCntryCd() {
		return cntryCd;
	}

	public void setCntryCd(String cntryCd) {
		this.cntryCd = cntryCd;
	}

	public String getLngCd() {
		return lngCd;
	}

	public void setLngCd(String lngCd) {
		this.lngCd = lngCd;
	}

	public String getSvcCd() {
		return svcCd;
	}

	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRootYn() {
		return rootYn;
	}

	public void setRootYn(String rootYn) {
		this.rootYn = rootYn;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGrpCode() {
		return grpCode;
	}

	public void setGrpCode(String grpCode) {
		this.grpCode = grpCode;
	}

	public String getProdList() {
		return prodList;
	}

	public void setProdList(String prodList) {
		this.prodList = prodList;
	}

	public String getProdCd() {
		return prodCd;
	}

	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getColTp() {
		return colTp;
	}

	public void setColTp(String colTp) {
		this.colTp = colTp;
	}

	public String getColSize() {
		return colSize;
	}

	public void setColSize(String colSize) {
		this.colSize = colSize;
	}

	public String getCommonGrp() {
		return commonGrp;
	}

	public void setCommonGrp(String commonGrp) {
		this.commonGrp = commonGrp;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public List<Map<String, Object>> getStepList() {
		return stepList;
	}

	public void setStepList(List<Map<String, Object>> stepList) {
		this.stepList = stepList;
	}

	public String getCdMenuName() {
		return cdMenuName;
	}

	public void setCdMenuName(String cdMenuName) {
		this.cdMenuName = cdMenuName;
	}

	public List<CommonCodeVO> getCommonCodeList() {
		return commonCodeList;
	}

	public void setCommonCodeList(List<CommonCodeVO> commonCodeList) {
		this.commonCodeList = commonCodeList;
	}
	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String getProdGrp() {
		return prodGrp;
	}

	public void setProdGrp(String prodGrp) {
		this.prodGrp = prodGrp;
	}
	public String getQry() {
		return qry;
	}

	public void setQry(String qry) {
		this.qry = qry;
	}
	
	public String getStartDt() {
		return StartDt;
	}

	public void setStartDt(String startDt) {
		StartDt = startDt;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRetV() {
		return retV;
	}

	public void setRetV(String retV) {
		this.retV = retV;
	}
	public String getBasicYn() {
		return basicYn;
	}

	public void setBasicYn(String basicYn) {
		this.basicYn = basicYn;
	}

	public String getRateItmNm() {
		return rateItmNm;
	}

	public void setRateItmNm(String rateItmNm) {
		this.rateItmNm = rateItmNm;
	}
	@Override
	public String toString() {
		return "TreeSampleVO [menuNo=" + menuNo + ", menuName=" + menuName
				+ ", upMenuNo=" + upMenuNo + ", viewPath=" + viewPath
				+ ", displayOrder=" + displayOrder + ", description="
				+ description + ", useYn=" + useYn + ", upMenuName="
				+ upMenuName + ", stepNo=" + stepNo + ", topMenu=" + topMenu
				+ ", menuLngList=" + menuLngList + ", menuNm=" + menuNm
				+ ", lngNm=" + lngNm + ", cntryCd=" + cntryCd + ", lngCd="
				+ lngCd + ", svcCd=" + svcCd + ", cnt=" + cnt + ", code="
				+ code + ", rootYn=" + rootYn + ", folder=" + folder
				+ ", title=" + title + ", grpCode=" + grpCode + ", prodList="
				+ prodList + ", prodCd=" + prodCd + ", prodNm=" + prodNm
				+ ", seq=" + seq + ", colTp=" + colTp + ", colSize=" + colSize
				+ ", commonGrp=" + commonGrp + ", tableId=" + tableId
				+ ", columnId=" + columnId + ", soId=" + soId + ", prodGrp="
				+ prodGrp + ", qry=" + qry + ", StartDt=" + StartDt
				+ ", orderId=" + orderId + ", seqNo=" + seqNo + ", rateItmCd="
				+ rateItmCd + ", unitPrice=" + unitPrice + ", billAmt="
				+ billAmt + ", chrgCtgry=" + chrgCtgry + ", disTp=" + disTp
				+ ", disUnit=" + disUnit + ", disTarget=" + disTarget
				+ ", ratePriNo=" + ratePriNo + ", retV=" + retV
				+ ", rateItmNm=" + rateItmNm + ", basicYn=" + basicYn
				+ ", stepList=" + stepList + ", cdMenuName=" + cdMenuName
				+ ", commonCodeList=" + commonCodeList + "]";
	}
	

}