package com.ntels.ifg.httpRest.mapper.distribute;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface DistributeMapper {
	
	String getDealerOrgId(@Param("orgId")String orgId);

	List<Map<String, String>> getDeviceList(@Param("orgId")String orgId,
	        @Param("itemId")String itemId,
	        @Param("imei")String imei, 
	        @Param("lng")String lng);

	List<Map<String, String>> getUsimList(@Param("orgId")String orgId,
			@Param("itemId")String itemId,
	        @Param("imsi")String imsi, 
	        @Param("lng")String lng);
	
	Map<String, String> getDtItemSale(@Param("item_id")String item_id,
			@Param("eqt_seq")String eqt_seq,
			@Param("org_id")String org_id);

	Map<String, String> getUsimItemSale(@Param("item_id")String item_id,
			@Param("eqt_seq")String eqt_seq,
			@Param("org_id")String org_id);

	int updateProc(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);

	int insertProcHist(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);
	
	int insertUsimProcHist(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);
	
	int updateUsimProc(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);

	Map<String, String> getDtItemCancle(@Param("item_id")String item_id, @Param("eqt_seq")String eqt_seq);

	int updateItemCancel(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);

	int insertProcHistCancel(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);
	
	int insertUsimProcHistCancel(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);

	Map<String, String> getDtUsimCancle(@Param("item_id")String item_id, @Param("eqt_seq")String eqt_seq);

	int updateUsimCancel(@Param("parameter")Map<String, String> parameter,
			@Param("nowDate")Date nowDate,
			@Param("nowDttm")String dateStringYYYYMMDDHH24MISS);
	
	

	

}