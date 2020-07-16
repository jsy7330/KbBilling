package com.ntels.ccbs.distribute.mapper.logistics.voucherMgt;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.LogisticsCenterVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.PoVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueDtlVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherItemVO;
import com.ntels.ccbs.system.domain.common.common.ProductMngVO;

public interface VoucherGenerateMapper {

	Integer vissueListCount(@Param("vissueVO") VissueVO vissueVO);
	
	List<VissueVO> vissueList(@Param("vissueVO") VissueVO vissueVO, @Param("start") int start, @Param("end") int end);
	
	Integer vissueDtlListCount(@Param("vissueDtlVO") VissueDtlVO vissueDtlVO);
	
	List<VissueDtlVO> vissueDtlList(@Param("vissueDtlVO") VissueDtlVO vissueDtlVO, @Param("start") int start, @Param("end") int end);
	
	Integer logisticsCenterCount(@Param("logisticsCenterVO") LogisticsCenterVO logisticsCenterVO);
	
	List<LogisticsCenterVO> searchLogisticsCenter(@Param("logisticsCenterVO") LogisticsCenterVO logisticsCenterVO, @Param("start") int start, @Param("end") int end);
	
	List<String> getPoOrgId(@Param("soId") String soId);
	
	int insertVissue(@Param("vissueVO") VissueVO vissueVO);
	
	int insertVissueDtl(@Param("vissueDtlVO") VissueDtlVO vissueDtlVO);
	
	int updateVissue(@Param("vissueVO") VissueVO vissueVO);
	
	int voucherItemCount(@Param("voucherItemVO") VoucherItemVO voucherItemVO);
	
	List<VoucherItemVO> searchVoucherItem(@Param("voucherItemVO") VoucherItemVO voucherItemVO, @Param("start") int start, @Param("end") int end);
	
	Integer newVoIssueNo(@Param("todayDttm") Date todayDttm);
	
	VissueVO getTaxMast(@Param("todayDt") String todayDt);
	
	String getMaxPoNo();
	
	int insertPo(@Param("poVO") PoVO poVO);
	
	int insertPoStatProcHist(@Param("poVO") PoVO poVO);
	
	int updateVissuePoNo(@Param("vissueVO") VissueVO vissueVO);
	
	int updateVissuePoStat(@Param("vissueVO") VissueVO vissueVO);
	
	Integer vissuePoInfoListCount(@Param("vissueVO") VissueVO vissueVO);
	
	List<VissueVO> vissuePoInfoList(@Param("vissueVO") VissueVO vissueVO);
	
	int insertVeqtInfo(@Param("vissueVO") VissueVO vissueVO);
	
	String getMaxCrtSeqNo(@Param("vissueVO") VissueVO vissueVO);
	
	int insertVeqtTransBulfInfo(@Param("vissueVO") VissueVO vissueVO);
	
	int updateVissueDtlVoStatCd(@Param("vissueDtlVO") VissueDtlVO vissueDtlVO);
	
	int updateVissueDtlVoPoDttm(@Param("vissueDtlVO") VissueDtlVO vissueDtlVO);
	
	int updatePoStatInfo(@Param("poVO") PoVO poVO);
	
	int insertPoIdlDtl(@Param("poVO") PoVO poVO);
	
	int deletePoIdlDtl(@Param("poVO") PoVO poVO);
	
	int updateVeqtInfoOrderCancle(@Param("vissueDtlVO") VissueDtlVO vissueDtlVO);
	
	Integer transferVoucherCount(@Param("vissueVO") VissueVO vissueVO);
	
	List<VissueVO> getTransferVissueInfo(@Param("vissueVO") VissueVO vissueVO);
	
	String getMncoId(@Param("vissueVO") VissueVO vissueVO);
	
	String getOrgId(@Param("vissueVO") VissueVO vissueVO);
	
	String getMaxVissueSeqNo(@Param("todayDt") String todayDt);
	
	int updateVoIssueCnt(@Param("vissueVO") VissueVO vissueVO);
	
	int updateVissueDtl(@Param("vissueVO") VissueVO vissueVO);
	
	int updateVeqt(@Param("vissueVO") VissueVO vissueVO);
	
	int updateVeqtTrans(@Param("vissueVO") VissueVO vissueVO);
	
	List<ProductMngVO> productList(@Param("productMngVO") ProductMngVO productMngVO, @Param("start") int start, @Param("end") int end);

	int productCount(@Param("productMngVO") ProductMngVO productMngVO);

}
