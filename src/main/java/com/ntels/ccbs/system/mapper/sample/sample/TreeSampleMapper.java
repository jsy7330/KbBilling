package com.ntels.ccbs.system.mapper.sample.sample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Factor;
import com.ntels.ccbs.system.domain.auth.authMng.AuthMngVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.sample.sample.TreeSampleVO;


@Component
public interface TreeSampleMapper {

	List<TreeSampleVO> getAuthList(@Param(value ="svcCd") String svcCd,@Param(value ="soId") String soId);	
	List<Map<String, Object>> getStepList(@Param(value = "svcCd") String svcCd, @Param(value = "soId") String soId);
	
	List<TreeSampleVO> getComboList(@Param(value = "svcCd") String svcCd);
	List<TreeSampleVO> getPordListAction(@Param(value = "prodCd")String prodCd);
	List<TreeSampleVO> getInputProdList(@Param(value = "prodCd")String prodCd, @Param(value = "StartDt")String StartDt);
	int getInputProdCnt(@Param(value = "prodCd")String prodCd, @Param(value = "StartDt")String StartDt);
	
	List<TreeSampleVO> getInputProdList1(@Param(value = "prodCd")String prodCd, @Param(value = "StartDt")String StartDt);
	int getInputProdCnt1(@Param(value = "prodCd")String prodCd, @Param(value = "StartDt")String StartDt);
	
	List<TreeSampleVO> getCommonCodeList(@Param(value = "grpCd") String grpCd, @Param(value = "lng")String lng);
	List<TreeSampleVO> processEstimateList(@Param(value = "soId")String soId,@Param(value = "orderId")String orderId);
	int processEstimateList1(@Param(value = "soId")String soId,@Param(value = "orderId")String orderId);
	List<TreeSampleVO> getEstimateList(@Param(value = "soId")String soId,@Param(value = "orderId")String orderId);
	
}