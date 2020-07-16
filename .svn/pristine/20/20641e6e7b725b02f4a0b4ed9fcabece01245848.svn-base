package com.ntels.ccbs.product.mapper.service.serviceMgt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.product.domain.product.productDev.ProductDevMgt;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.product.domain.service.serviceMgt.ServiceMgt;

public interface ServiceMgtMapper {

	List<ServiceMgt> list(@Param(value ="sessionLanguage") String sessionLanguage,
						@Param(value ="soAuthList") List<Map<String, Object>> soAuthList
						,@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int serviceMgtComListCount(@Param(value = "serviceMgt")ServiceMgt serviceMgt,
						@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);
	
	List<ServiceMgt> serviceMgtComList(@Param(value = "serviceMgt")ServiceMgt serviceMgt,
					@Param(value ="soAuthList") List<Map<String, Object>> soAuthList
					, @Param(value="start")int start
					, @Param(value="end") int end);	
	
	String getNextDispPriNo(@Param(value = "serviceMgt") ServiceMgt serviceMgt);

	int getDualSvcNmCnt (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	int insertServiceMgt (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	ServiceMgt getService (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	int updateServiceMgt (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	int updateSeriveMgtInactDt (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	Map<String, String> getConTableDtCnt (@Param(value = "conTableMap") Map<String, String> conTableMap);
	
	int updateServiceInactDt (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	int getServiceMgtSaleItmListCount(@Param(value = "serviceMgt")ServiceMgt serviceMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);

	List<ServiceMgt> getServiceMgtSaleItmList(@Param(value = "serviceMgt")ServiceMgt serviceMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList
			, @Param(value="start")int start
			, @Param(value="end") int end);
	
	List<ServiceMgt> getRateItmTypComboList(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int getDualSaleItmNmCnt (@Param(value= "serviceMgt") ServiceMgt serviceMgt);
	
	int insertServiceMgtSaleItm (@Param(value= "serviceMgt") ServiceMgt serviceMgt);
	
	ServiceMgt getSaleItm (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	int updateSaleItm (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	int updateSaleItmInactDt (@Param(value = "serviceMgt") ServiceMgt serviceMgt);
	
	int updateServiceMgtSaleItmInactDt (@Param(value = "serviceMgt") ServiceMgt serviceMgt);

	int getServiceMgtSvcRateItmTypListCount(@Param(value = "serviceMgt")ServiceMgt serviceMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList);

	List<ServiceMgt> getServiceMgtSvcRateItmTypList(@Param(value = "serviceMgt")ServiceMgt serviceMgt,
			@Param(value ="soAuthList") List<Map<String, Object>> soAuthList
			, @Param(value="start")int start
			, @Param(value="end") int end);
	
	List<ServiceMgt> getRateItmTypComboListBySvc (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	List<ServiceMgt> getInvItmComboList (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	List<ServiceMgt> getSaleItmComboList (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	List<ServiceMgt> getSvcRateItmTypAttrList (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	List<ServiceMgt> getSvcRateItmTypAttrList2 (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	List<ServiceMgt> getSvcRateItmTypFctrList (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	Map<String, String> getTableData (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	List<ServiceMgt> getTotalFactorListMergedeSvc (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int addSvcRateItmTypFctr(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int addSvcRateItmTyp(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int getSvcRateItmTypNmDupCnt(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	ServiceMgt getSvcRateItmTyp(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int modSvcRateItmTyp(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int modSvcRateItmTypInactDt(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int modSvcRateItmTypInactDtion (@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	ServiceMgt getModSvcRateItmTypFctrInit(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int modSvcRateItmTypFctr(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int modSvcRateItmTypFctrInactDt(@Param(value = "serviceMgt")ServiceMgt serviceMgt);
	
	int getProductAttrCnt(@Param(value ="serviceMgt") ServiceMgt serviceMgt);
	int addSvcRateItmTypAttr(@Param(value ="serviceMgt") ServiceMgt serviceMgt);
	int modSvcRateItmTypAttr(@Param(value ="serviceMgt") ServiceMgt serviceMgt);
	int modSvcRateItmTypAttrInactDt(@Param(value ="serviceMgt") ServiceMgt serviceMgt);
	int modSvcRateItmTypAttrInaction(@Param(value ="serviceMgt") ServiceMgt serviceMgt);
}



