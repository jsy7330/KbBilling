package com.ntels.ccbs.customer.mapper.contract.receipt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.domain.contract.receipt.ReceiptCounselVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

@Component
public interface ReceiptCounselMapper {
	
	List<ReceiptCounselVO> getRcptLvlCodeList(@Param(value = "commonGrp") String commonGrp,@Param(value="refCode") String refCode, @Param(value = "lng")String lng);
	
	Integer getRcptTabListTotalCnt(
			@Param("searchStatDt")String searchStatDt			
			,@Param("searchEndDt")String searchEndDt	
			,@Param("cnslStat")String cnslStat		
			,@Param("condCustId")String condCustId			
			,@Param("selRcptLvl1")String selRcptLvl1
			,@Param("selRcptLvl2")String selRcptLvl2
			,@Param("selRcptLvl3")String selRcptLvl3		
			,@Param("rcptYn")String rcptYn			
			,@Param("elapse")String elapse	
			,@Param("elapseDate")String elapseDate				
			,@Param("orgId")String orgId
			);
	
	List<ReceiptCounselVO> getRcptTabList(
			@Param("searchStatDt")String searchStatDt			
			,@Param("searchEndDt")String searchEndDt	
			,@Param("cnslStat")String cnslStat		
			,@Param("condCustId")String condCustId			
			,@Param("selRcptLvl1")String selRcptLvl1
			,@Param("selRcptLvl2")String selRcptLvl2
			,@Param("selRcptLvl3")String selRcptLvl3		
			,@Param("rcptYn")String rcptYn			
			,@Param("elapse")String elapse	
			,@Param("elapseDate")String elapseDate				
			,@Param("orgId")String orgId				
			,@Param("today") String today
			,@Param("lng") String lng
			,@Param("sidx") String sidx
			,@Param("sord") String sord
			,@Param("start") String start
			,@Param("end") String end);
	
	ReceiptCounselVO getRcptInfo(@Param("rcptId") String rcptId, @Param("lng")String lng,@Param("today")String today);	
}

