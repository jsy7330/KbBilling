package com.ntels.ccbs.product.service.service.serviceMgt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ntels.ccbs.product.domain.service.serviceMgt.ServiceMgt;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface ServiceMgtService {

	public List<ServiceMgt> getListMenu(String sessionLanguage, List<Map<String,Object>> soAuthList, ServiceMgt serviceMgt);
	public int getServiceMgtComListCount(ServiceMgt serviceMgt, List<Map<String,Object>> soAuthList);
	public List<ServiceMgt> getServiceMgtComList(ServiceMgt serviceMgt, List<Map<String,Object>> soAuthList, int page, int perPage);
	public String getNextDispPriNo(ServiceMgt serviceMgt);
	public int getDualSvcNmCnt(ServiceMgt serviceMgt);
	public int insertServiceMgt(ServiceMgt serviceMgt);
	public ServiceMgt getService(ServiceMgt serviceMgt);
	public String updateServiceMgt(ServiceMgt serviceMgt);
	public int updateSeriveMgtInactDt(ServiceMgt serviceMgt);
	public Map<String,String> getConTableDtCnt(Map<String,String> conTableMap);
	public int updateServiceInactDt(ServiceMgt serviceMgt);
	public int getServiceMgtSaleItmListCount(ServiceMgt serviceMgt, List<Map<String,Object>> soAuthList);
	public List<ServiceMgt> getServiceMgtSaleItmList(ServiceMgt serviceMgt, List<Map<String,Object>> soAuthList, int page, int perPage);	
	public List<ServiceMgt> getRateItmTypComboList(ServiceMgt serviceMgt);
	public int getDualSaleItmNmCnt(ServiceMgt serviceMgt);
	public int insertServiceMgtSaleItm(ServiceMgt serviceMgt);
	public ServiceMgt getSaleItm (ServiceMgt serviceMgt);
	public int updateSaleItm(ServiceMgt serviceMgt);
	public int updateSaleItmInactDt(ServiceMgt serviceMgt);
	public int updateServiceMgtSaleItmInactDt (ServiceMgt serviceMgt);
	public int getServiceMgtSvcRateItmTypListCount(ServiceMgt serviceMgt, List<Map<String,Object>> soAuthList);
	public List<ServiceMgt> getServiceMgtSvcRateItmTypList(ServiceMgt serviceMgt, List<Map<String,Object>> soAuthList, int page, int perPage);	
	public List<ServiceMgt> getRateItmTypComboListBySvc(ServiceMgt serviceMgt);
	public List<ServiceMgt> getInvItmComboList(ServiceMgt serviceMgt);
	public List<ServiceMgt> getSaleItmComboList(ServiceMgt serviceMgt);
	public List<ServiceMgt> getSvcRateItmTypAttrList(ServiceMgt serviceMgt);
	public List<ServiceMgt> getSvcRateItmTypAttrList2(ServiceMgt serviceMgt);
	public List<ServiceMgt> getSvcRateItmTypFctrList(ServiceMgt serviceMgt);
	public Map<String,String> getTableData(ServiceMgt serviceMgt);
	public List<ServiceMgt> getTotalFactorListMergedeSvc(ServiceMgt serviceMgt);
	public int addSvcRateItmTypFctr (ServiceMgt serviceMgt);
	public String addSvcRateItmTyp(ServiceMgt serviceMgt);
	public ServiceMgt getSvcRateItmTyp(ServiceMgt serviceMgt);
	public String modSvcRateItmTyp(ServiceMgt serviceMgt);
	public ServiceMgt getModSvcRateItmTypFctrInit(ServiceMgt serviceMgt);
	public String modSvcRateItmTypFctr(ServiceMgt serviceMgt);
	public String updateServiceMgtSaleItmListUpdate(ServiceMgt serviceMgt);
	public String treatSvcRateItmTypAttr (HashMap<String, String> map, SessionUser sessionUser);
}
