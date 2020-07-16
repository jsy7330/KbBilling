package com.ntels.ccbs.system.service.sample.sample;

import java.util.List;
import java.util.Map;

import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Factor;
import com.ntels.ccbs.system.domain.auth.authMng.AuthMngVO;
import com.ntels.ccbs.system.domain.sample.sample.TreeSampleVO;


public interface TreeSampleService {
	//List<Map<String, Object>>getMenuList(String svcCd);
	public List<TreeSampleVO> getMenuList(String svcCd,String soId);	
	public List<TreeSampleVO> getComboList(String svcCd);	
	public List<TreeSampleVO> getPordListAction(String prodCd);
	public List<TreeSampleVO> getInputProdList(String prodCd, String StartDt);
	public List<TreeSampleVO> getInputProdList1(String prodCd, String StartDt);
	public int getInputProdCnt(String prodCd, String StartDt);
	public int getInputProdCnt1(String prodCd, String StartDt);	
	public List<TreeSampleVO> processEstimateList(String soId, String orderId);
	public int processEstimateList1(String soId, String orderId);
	
	public List<TreeSampleVO> getEstimateList(String soId, String orderId);
}