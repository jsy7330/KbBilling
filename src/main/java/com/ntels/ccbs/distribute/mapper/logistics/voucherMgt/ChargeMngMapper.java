package com.ntels.ccbs.distribute.mapper.logistics.voucherMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.ChargeMngVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.RchrgHistVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VeqtVO;

public interface ChargeMngMapper {

	ChargeMngVO getCustInfoView(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	Integer getCtrtInfoListCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getCtrtInfoList(@Param("chargeMngVO") ChargeMngVO chargeMngVO, @Param("start") int start, @Param("end") int end);
	
	Integer getCustInfoListCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getCustInfoList(@Param("chargeMngVO") ChargeMngVO chargeMngVO, @Param("start") int start, @Param("end") int end);
	
	String getCustIdBySvcTelNo(@Param("svcTelNo") String svcTelNo);
	
	String getCustIdByCtrtId(@Param("ctrtId") String ctrtId);
	
	Integer getChrgHistListCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getChrgHistList(@Param("chargeMngVO") ChargeMngVO chargeMngVO, @Param("start") int start, @Param("end") int end);
	
	Integer getRmnAmt(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getPreBasicProdInfo(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	String getPreBasicRemainInfo(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	Integer getPreBundleProdCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getPreBundleProdInfo(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	String getPreBundleRemainInfo(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getLatestBundleProdInfo(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	String getPromoDtInfo(@Param("todayYm") String todayYm);
	
	int getPromoPurCntInfo(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	int getNotResPVCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	Integer getChrgVeqtListCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getChrgVeqtList(@Param("chargeMngVO") ChargeMngVO chargeMngVO, @Param("start") int start, @Param("end") int end);
	
	Integer getChrgCtrtListCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getChrgCtrtList(@Param("chargeMngVO") ChargeMngVO chargeMngVO, @Param("start") int start, @Param("end") int end);
	
	Integer getChrgPopProdListCount(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	List<ChargeMngVO> getChrgPopProdList(@Param("chargeMngVO") ChargeMngVO chargeMngVO, @Param("start") int start, @Param("end") int end);
	
	int insertRchrgHist(@Param("rchrgHistVO") RchrgHistVO rchrgHistVO);
	
	int updateVeqtInfoVoStatLgst(@Param("veqtVO") VeqtVO veqtVO);
	
	String getMaxCrtSeqNo(@Param("veqtVO") VeqtVO veqtVO);
	
	int addVeqtTransInfoInit_voSeqNo(@Param("veqtVO") VeqtVO veqtVO);

	List<ChargeMngVO> getCtrtChrgCntList(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	String getOcsCancelYn(@Param("chargeMngVO") ChargeMngVO chargeMngVO);
	
	VeqtVO getVeqtTransInfo(@Param("veqtVO") VeqtVO veqtVO);
	
}
