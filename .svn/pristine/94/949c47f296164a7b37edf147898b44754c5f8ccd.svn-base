package com.ntels.ccbs.product.service.usageProduct.usageProductMgt.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.product.domain.usageProduct.usageProductMgt.NewProductDtl;
import com.ntels.ccbs.product.mapper.usageProduct.usageProductMgt.NewProductDtlMapper;
import com.ntels.ccbs.product.service.usageProduct.usageProductMgt.NewProductDtlService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
	
@Service
public class NewProductDtlServiceImpl implements NewProductDtlService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** RatingFactorUnitMapper Autowired. */
	@Autowired
	private NewProductDtlMapper newProductDtlMapper;

	@Override
	public List<NewProductDtl> getNewProductDtlList(
			NewProductDtl newProductDtl, int page, int perPage) {
	
		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getNewProductDtlList(newProductDtl, start, end);
	}
	
	@Override
	public int addProdUsgTyp(NewProductDtl newProductDtl) {

		return newProductDtlMapper.addProdUsgTyp(newProductDtl);
	}
	
	@Override
	public int modNewProductDtl(NewProductDtl newProductDtl) {

		return newProductDtlMapper.modNewProductDtl(newProductDtl);
	}

	@Override
	public List<NewProductDtl> getAllUsgTypList(NewProductDtl newProductDtl,
			int page, int perPage) {
		
		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getAllUsgTypList(newProductDtl, start, end);
	}

	@Override
	public List<NewProductDtl> getUsgTypList(NewProductDtl newProductDtl,
			int page, int perPage) {
		
		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getUsgTypList(newProductDtl, start, end);
	}

	@Override
	public List<NewProductDtl> getProdUsgTypList(NewProductDtl newProductDtl,
			int page, int perPage) {

		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getProdUsgTypList(newProductDtl, start, end);
	}
	
	@Override
	public List<NewProductDtl> getRatingCdList(NewProductDtl newProductDtl,
			int page, int perPage) {

		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getRatingCdList(newProductDtl, start, end);
	}
	
	@Override
	public int modProdUsgTyp(NewProductDtl newProductDtl) {
		for( Map<String, Object> updateInfo : newProductDtl.getUpdateUsgTypValList() ) {
			newProductDtl.setProdId((String)updateInfo.get("prodId"));
			newProductDtl.setUsgTyp((String)updateInfo.get("usgTyp"));
			newProductDtl.setChrgCd((String)updateInfo.get("chrgCd"));
			newProductDtl.setCalendarDefId((String)updateInfo.get("calendarDefId"));
			newProductDtl.setPeriodApplMethod((String)updateInfo.get("periodApplMethod"));
			newProductDtl.setTierSetId((String)updateInfo.get("tierSetId"));
			newProductDtl.setTierStepFlag((String)updateInfo.get("tierStepFlag"));
			newProductDtl.setCommRateFlag((String)updateInfo.get("commRateFlag"));
			newProductDtl.setUsgMeasureUnit((String)updateInfo.get("usgMeasureUnit"));
			newProductDtl.setCurrencyCd((String)updateInfo.get("currencyCd"));
			newProductDtl.setTmDivisionFlag((String)updateInfo.get("tmDivisionFlag"));
			newProductDtl.setUsgFeeRoundingMethod((String)updateInfo.get("usgFeeRoundingMethod"));
			newProductDtl.setUsgFeeRoundingOffset((String)updateInfo.get("usgFeeRoundingOffset"));
			newProductDtl.setUsgRoundingMethod((String)updateInfo.get("usgRoundingMethod"));
			newProductDtl.setDiscFlag((String)updateInfo.get("discFlag"));
			newProductDtl.setDedtFlag((String)updateInfo.get("dedtFlag"));
			newProductDtl.setTmDivisionFlag((String)updateInfo.get("tmDivisionFlag"));
			newProductDtl.setLmtFlag((String)updateInfo.get("lmtFlag"));
			newProductDtl.setCrossDiscFlag((String)updateInfo.get("crossDiscFlag"));
			newProductDtl.setMultipleRateInd((String)updateInfo.get("multipleRateInd"));
			newProductDtl.setMultipleDiscFlag((String)updateInfo.get("multipleDiscFlag"));
			newProductDtl.setMaxReserveAmt((String)updateInfo.get("maxReserveAmt"));
			newProductDtl.setEffDt(((String)updateInfo.get("effDt")).replaceAll("-", "") + "000000");
			newProductDtl.setExpDt(((String)updateInfo.get("expDt")).replaceAll("-", "") + "235959" );
			newProductDtl.setRealTmAccFlag((String)updateInfo.get("realTmAccFlag"));
			newProductDtl.setUnitSvcCd((String)updateInfo.get("unitSvcCd"));
			
			if( ((String)updateInfo.get("regYn")).equals("Y") ) {
				newProductDtlMapper.modProdUsgTyp(newProductDtl);	
			}
			else {
				newProductDtlMapper.addProdUsgTyp(newProductDtl);
			}
		}
		return 1;
	}

	@Override
	public int delProdUsgTyp(NewProductDtl newProductDtl) {

		return newProductDtlMapper.delProdUsgTyp(newProductDtl);
	}
	
	@Override
	public List<NewProductDtl> getProdUsgRatingFacList(
			NewProductDtl newProductDtl, int page, int perPage) {

		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getProdUsgRatingFacList(newProductDtl, start, end);
	}
	
	@Override
	public List<NewProductDtl> getProdRatingFacList(
			NewProductDtl newProductDtl, int page, int perPage) {

		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getProdRatingFacList(newProductDtl, start, end);
	}
	
	@Override
	public int addProdUsgRatingFac(NewProductDtl newProductDtl) {
		for( Map<String, Object> updateInfo : newProductDtl.getUpdateRateFacValList() ) {
			newProductDtl.setProdId((String)updateInfo.get("prodId"));
			newProductDtl.setUsgTyp((String)updateInfo.get("usgTyp"));
			newProductDtl.setRateFac((String)updateInfo.get("rateFac"));
			newProductDtl.setEffDt((String)updateInfo.get("effDt"));
			newProductDtl.setExpDt((String)updateInfo.get("expDt"));
			newProductDtl.setRateFacFlag((String)updateInfo.get("rateFacFlag"));
			
			if( ((String)updateInfo.get("changedTag")).equals("1") ) {
				if( newProductDtlMapper.getProdRatingFacCnt(newProductDtl) == 0 ) {
					newProductDtlMapper.addProdUsgRatingFac(newProductDtl);
				}
				else {
					newProductDtlMapper.modProdUsgRatingFac(newProductDtl);
				}
			}
		}
		return 1;
	}
	

	@Override
	public int delProdUsgRatingFac(NewProductDtl newProductDtl) {

		return newProductDtlMapper.delProdUsgRatingFac(newProductDtl);
	}

	@Override
	public List<NewProductDtl> getProdUsgTypPeriodList(
			NewProductDtl newProductDtl, int page, int perPage) {
		
		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getProdUsgTypPeriodList(newProductDtl, start, end);
	}
	
	@Override
	public List<NewProductDtl> getPeriodUsgTypList(
			NewProductDtl newProductDtl, int page, int perPage) {
		
		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getPeriodUsgTypList(newProductDtl, start, end);
	}
	
	@Override
	public int getProdUsgTypPeriodCnt(NewProductDtl newProductDtl) {

		return newProductDtlMapper.getProdUsgTypPeriodCnt(newProductDtl);
	}
	
	@Override
	public int addProdUsgTypPeriod(NewProductDtl newProductDtl) {
		for( Map<String, Object> updateInfo : newProductDtl.getUpdatePeriodValList() ) {
			newProductDtl.setProdId((String)updateInfo.get("prodId"));
			newProductDtl.setUsgTyp((String)updateInfo.get("usgTyp"));
			newProductDtl.setEffDt((String)updateInfo.get("effDt"));
			newProductDtl.setExpDt((String)updateInfo.get("expDt"));
			newProductDtl.setActDay(((String)updateInfo.get("actDay")).replace("-", ""));
			newProductDtl.setDeactDay(((String)updateInfo.get("deactDay")).replace("-", ""));
			newProductDtl.setPeriodDefId((String)updateInfo.get("periodDefId"));
			
			if( ((String)updateInfo.get("changedTag")).equals("1") ) {
				if( newProductDtlMapper.getProdUsgTypPeriodCnt(newProductDtl) == 0 ) {
					newProductDtlMapper.addProdUsgTypPeriod(newProductDtl);
				}
				else {
					newProductDtlMapper.modProdUsgTypPeriod(newProductDtl);
				}
			}
		}
		return 1;
	}
		
	@Override
	public int delProdUsgTypPeriod(NewProductDtl newProductDtl) {

		return newProductDtlMapper.delProdUsgTypPeriod(newProductDtl);
	}
	
	@Override
	public List<NewProductDtl> getRateInfoList(NewProductDtl newProductDtl,
			int page, int perPage) {

		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getRateInfoList(newProductDtl, start, end);
	}
	
	@Override
	public List<NewProductDtl> getRateInfoUsgTypList(NewProductDtl newProductDtl,
			int page, int perPage) {

		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getRateInfoUsgTypList(newProductDtl, start, end);
	}
	
	@Override
	public int getRateInfoCnt(NewProductDtl newProductDtl) {

		return newProductDtlMapper.getRateInfoCnt(newProductDtl);
	}	
	
	@Override
	public int addRateInfo(NewProductDtl newProductDtl) {
		for( Map<String, Object> updateInfo : newProductDtl.getUpdateRateInfoValList() ) {
			newProductDtl.setProdId((String)updateInfo.get("prodId"));
			newProductDtl.setUsgTyp((String)updateInfo.get("usgTyp"));
			newProductDtl.setPeriodCd((String)updateInfo.get("periodCd"));
			newProductDtl.setTierSegmentId((String)updateInfo.get("tierSegmentId"));
			newProductDtl.setCorrId((String)updateInfo.get("corrId"));
			newProductDtl.setChrgCd((String)updateInfo.get("chrgCd"));
			newProductDtl.setEffDt(((String)updateInfo.get("effDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setExpDt(((String)updateInfo.get("expDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setRatePerUnit((String)updateInfo.get("ratePerUnit"));
			newProductDtl.setUsgBillingIncrement((String)updateInfo.get("usgBillingIncrement"));
			
			if( newProductDtlMapper.getRateInfoCnt(newProductDtl) == 0 ) {
				newProductDtlMapper.addRateInfo(newProductDtl);
			}
			else {
				newProductDtlMapper.modRateInfo(newProductDtl);
			}
		}
		return 1;
	}
	
	@Override
	public int modRateInfo(NewProductDtl newProductDtl) {

		return newProductDtlMapper.modRateInfo(newProductDtl);
	}	

	@Override
	public int delRateInfo(NewProductDtl newProductDtl) {

		return newProductDtlMapper.delRateInfo(newProductDtl);
	}
	
	@Override
	public List<NewProductDtl> getDiscDedtDefList(NewProductDtl newProductDtl,
			int page, int perPage) {
		
		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;

		return newProductDtlMapper.getDiscDedtDefList(newProductDtl, start, end);
	}
	
	@Override
	public int modDiscDef(NewProductDtl newProductDtl) {
		for( Map<String, Object>updateInfo : newProductDtl.getUpdateDiscDefValList() ) {
			newProductDtl.setMapDiscDedtCd((String)updateInfo.get("mapDiscDedtCd"));
			newProductDtl.setMapDiscDedtPrty((String)updateInfo.get("mapDiscDedtPrty"));
			newProductDtl.setMultipleDiscMethod((String)updateInfo.get("multipleDiscMethod"));
			newProductDtl.setMapExpDt(((String)updateInfo.get("mapExpDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setMapEffDt(((String)updateInfo.get("mapEffDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setUsgTypLvl((String)updateInfo.get("usgTypLvl"));
			newProductDtl.setUsgTypGrpCd((String)updateInfo.get("usgTypGrpCd"));
			newProductDtl.setUsgItmTyp((String)updateInfo.get("usgItmTyp"));
			newProductDtl.setUsgItmCd((String)updateInfo.get("usgItmCd"));
			newProductDtl.setCondRateFac1((String)updateInfo.get("condRateFac1"));
			newProductDtl.setCondOperator1((String)updateInfo.get("condOperator1"));
			newProductDtl.setCondVal1((String)updateInfo.get("condVal1"));
			newProductDtl.setLogicalOperator12((String)updateInfo.get("logicalOperator12"));
			newProductDtl.setCondRateFac2((String)updateInfo.get("condRateFac2"));
			newProductDtl.setCondOperator2((String)updateInfo.get("condOperator2"));
			newProductDtl.setCondVal2((String)updateInfo.get("condVal2"));
			newProductDtl.setTmDivisionFlag((String)updateInfo.get("tmDivisionFlag"));
			newProductDtl.setSegmentFlag((String)updateInfo.get("segmentFlag"));
			newProductDtl.setQuantity((String)updateInfo.get("quantity"));
			newProductDtl.setMapCd((String)updateInfo.get("mapCd"));
			newProductDtl.setChangedTag((String)updateInfo.get("changedTag"));
			newProductDtl.setMapProdId((String)updateInfo.get("mapProdId"));
			
			if( newProductDtl.getChangedTag().equals("1") ) {
				if( newProductDtl.getMapCd().equals("") ) {
					System.out.println("########### " + newProductDtl.getUpdateDiscDefValList());
					newProductDtlMapper.addDedtDef(newProductDtl);
				}
				else {
					newProductDtlMapper.modDiscDef(newProductDtl);
					newProductDtlMapper.modProdDiscDef(newProductDtl);
				}
			}
		}
		return 1;
	}

	@Override
	public int modDedtDef(NewProductDtl newProductDtl) {
		for( Map<String, Object>updateInfo : newProductDtl.getUpdateDedtDefValList() ) {
			newProductDtl.setMapExpDt(((String)updateInfo.get("mapExpDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setUsgTypLvl((String)updateInfo.get("usgTypLvl"));
			newProductDtl.setUsgTypGrpCd((String)updateInfo.get("usgTypGrpCd"));
			newProductDtl.setUsgItmTyp((String)updateInfo.get("usgItmTyp"));
			newProductDtl.setUsgItmCd((String)updateInfo.get("usgItmCd"));
			newProductDtl.setCondRateFac1((String)updateInfo.get("condRateFac1"));
			newProductDtl.setCondOperator1((String)updateInfo.get("condOperator1"));
			newProductDtl.setCondVal1((String)updateInfo.get("condVal1"));
			newProductDtl.setLogicalOperator12((String)updateInfo.get("logicalOperator12"));
			newProductDtl.setCondRateFac2((String)updateInfo.get("condRateFac2"));
			newProductDtl.setCondOperator2((String)updateInfo.get("condOperator2"));
			newProductDtl.setCondVal2((String)updateInfo.get("condVal2"));
			newProductDtl.setSubscProrateFlag((String)updateInfo.get("subscProrateFlag"));
			newProductDtl.setTermProrateFlag((String)updateInfo.get("termProrateFlag"));
			newProductDtl.setAmtFlag((String)updateInfo.get("amtFlag"));
			newProductDtl.setAmtUnit((String)updateInfo.get("amtUnit"));
			newProductDtl.setQuantity((String)updateInfo.get("quantity"));
			newProductDtl.setReplenishCycl((String)updateInfo.get("replenishCycl"));
			newProductDtl.setMapCd((String)updateInfo.get("mapCd"));
			newProductDtl.setMapDiscDedtPrty((String)updateInfo.get("mapDiscDedtPrty"));
			newProductDtl.setMapEffDt(((String)updateInfo.get("mapEffDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setMapDiscDedtCd((String)updateInfo.get("mapDiscDedtCd"));
			newProductDtl.setChangedTag((String)updateInfo.get("changedTag"));
			newProductDtl.setMapProdId((String)updateInfo.get("mapProdId"));
			newProductDtl.setMultipleDiscMethod((String)updateInfo.get("multipleDiscMethod"));
			
			if( newProductDtl.getChangedTag().equals("1") ) {
				if( newProductDtl.getMapCd().equals("") ) {
					newProductDtlMapper.addDedtDef(newProductDtl);
				}
				else {
					newProductDtlMapper.modDedtDef(newProductDtl);
					newProductDtlMapper.modProdDedtDef(newProductDtl);
				}
			}
		}
		return 1;
	}
		
	@Override
	public int delProdDiscDedtDef(NewProductDtl newProductDtl) {
		
		return newProductDtlMapper.delProdDiscDedtDef(newProductDtl);
	}
	
	@Override
	public List<CommonCodeVO> getAmtUnitList() {

		return newProductDtlMapper.getAmtUnitList();
	}

	@Override
	public List<NewProductDtl> getLimitDefList(NewProductDtl newProductDtl,
			int page, int perPage) {

		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		
		return newProductDtlMapper.getLimitDefList(newProductDtl, start, end);
	}

	public int addLimitDef(NewProductDtl newProductDtl) {
		for( Map<String, Object>updateInfo : newProductDtl.getUpdateLmtValList() ) {
			newProductDtl.setProdId((String)updateInfo.get("prodId"));
			newProductDtl.setEffDt(((String)updateInfo.get("effDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setExpDt(((String)updateInfo.get("expDt")).replace("-", "").replace(":", "").replace(" ", ""));
			newProductDtl.setUsgTypLvl((String)updateInfo.get("usgTypLvl"));
			newProductDtl.setUsgTypGrpCd((String)updateInfo.get("usgTypGrpCd"));
			newProductDtl.setUsgItmTyp((String)updateInfo.get("usgItmTyp"));
			newProductDtl.setUsgItmCd((String)updateInfo.get("usgItmCd"));
			newProductDtl.setUsgTypCtrlLvl((String)updateInfo.get("usgTypCtrlLvl"));
			newProductDtl.setCtrlUsgTypGrpCd((String)updateInfo.get("ctrlUsgTypGrpCd"));
			newProductDtl.setAmtFlag((String)updateInfo.get("amtFlag"));
			newProductDtl.setQuantity((String)updateInfo.get("quantity"));
			newProductDtl.setRechrgLmtTypCd((String)updateInfo.get("rechrgLmtTypCd"));
			newProductDtl.setLmtCd((String)updateInfo.get("lmtCd"));
			
			if( newProductDtlMapper.getLimitDefCnt(newProductDtl) == 0 ) {
				newProductDtlMapper.addLimitDef(newProductDtl);
			}
			else {
				newProductDtlMapper.modLimitDef(newProductDtl);
			}
		}
		
		return 1;
	}

	public int modLimitDef(NewProductDtl newProductDtl) {

		return newProductDtlMapper.modLimitDef(newProductDtl);
	}
	
	public int delLimitDef(NewProductDtl newProductDtl) {

		return newProductDtlMapper.delLimitDef(newProductDtl);
	}	
		
	@Override
	public List<CommonCodeVO> getChrgCdList() {

		return newProductDtlMapper.getChrgCdList();
	}

	@Override
	public List<CommonCodeVO> getBillingChrgCdList() {
		
		return newProductDtlMapper.getBillingChrgCdList();
	}

	@Override
	public List<CommonCodeVO> getUsgMeasureUnitList() {
		
		return newProductDtlMapper.getUsgMeasureUnitList();
	}
	
	@Override
	public List<CommonCodeVO> getCtrlUsgTypGrpCdList() {
	
		return newProductDtlMapper.getCtrlUsgTypGrpCdList();
	}

	@Override
	public int getProdRatingFacCnt(NewProductDtl newProductDtl) {
		return newProductDtlMapper.getProdRatingFacCnt(newProductDtl);
	}

	@Override
	public List<CommonCodeVO> getSoInfoList() {
		return newProductDtlMapper.getSoInfoList();
	}
}
