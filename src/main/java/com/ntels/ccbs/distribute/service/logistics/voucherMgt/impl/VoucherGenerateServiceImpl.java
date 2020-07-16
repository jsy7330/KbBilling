package com.ntels.ccbs.distribute.service.logistics.voucherMgt.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.LogisticsCenterVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.PoVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueDtlVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherItemVO;
import com.ntels.ccbs.distribute.mapper.logistics.voucherMgt.VoucherGenerateMapper;
import com.ntels.ccbs.distribute.service.logistics.voucherMgt.VoucherGenerateService;
import com.ntels.ccbs.system.domain.common.common.ProductMngVO;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class VoucherGenerateServiceImpl implements VoucherGenerateService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VoucherGenerateMapper voucherGenerateMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	private void removeDateHyphen(VissueVO vissueVO) {
		if (vissueVO == null) {
			return;
		}
		
		if (vissueVO.getVoIssueDt() != null) {
			vissueVO.setVoIssueDt(vissueVO.getVoIssueDt().replaceAll("-", ""));
		}
		
		if (vissueVO.getVoExpiredDt() != null) {
			vissueVO.setVoExpiredDt(vissueVO.getVoExpiredDt().replaceAll("-", ""));
		}
		
		if (vissueVO.getPoDt() != null) {
			vissueVO.setPoDt(vissueVO.getPoDt().replaceAll("-", ""));
		}
		
		if (vissueVO.getDlgdAgreeDt() != null) {
			vissueVO.setDlgdAgreeDt(vissueVO.getDlgdAgreeDt().replace("-", ""));
		}
	}
	
	@Override
	public Integer vissueListCount(VissueVO vissueVO) {
		
		if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
			throw new ServiceException("MSG.M07.MSG00004");
		}
		
		if (StringUtils.isEmpty(vissueVO.getLngTyp()) == true) {
			throw new ServiceException("MSG.M08.MSG00005");
		}
		
		return voucherGenerateMapper.vissueListCount(vissueVO);
	}
	
	@Override
	public List<VissueVO> vissueList(VissueVO vissueVO) {
		
		if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
			throw new ServiceException("MSG.M07.MSG00004");
		}
		
		if (StringUtils.isEmpty(vissueVO.getLngTyp()) == true) {
			throw new ServiceException("MSG.M08.MSG00005");
		}
		
		int start = (vissueVO.getPage() -1 ) * vissueVO.getPerPage();
		int end = vissueVO.getPerPage();
		
		return voucherGenerateMapper.vissueList(vissueVO, start, end);
		
	}
	
	@Override
	public Integer vissueDtlListCount(VissueDtlVO vissueDtlVO) {
		
		if (StringUtils.isEmpty(vissueDtlVO.getSoId()) == true) {
			throw new ServiceException("MSG.M07.MSG00004");
		}
		
		// 발행일련번호
		if (StringUtils.isEmpty(vissueDtlVO.getVissueSeqNo()) == true) {
			throw new ServiceException("MSG.M06.MSG00018");
		}
		
		if (StringUtils.isEmpty(vissueDtlVO.getLngTyp()) == true) {
			throw new ServiceException("MSG.M08.MSG00005");
		}
		
		return voucherGenerateMapper.vissueDtlListCount(vissueDtlVO);
	}
	
	@Override
	public List<VissueDtlVO> vissueDtlList(VissueDtlVO vissueDtlVO) {
		
		if (StringUtils.isEmpty(vissueDtlVO.getSoId()) == true) {
			throw new ServiceException("MSG.M07.MSG00004");
		}
		
		// 발행일련번호
		if (StringUtils.isEmpty(vissueDtlVO.getVissueSeqNo()) == true) {
			throw new ServiceException("MSG.M06.MSG00018");
		}
		
		if (StringUtils.isEmpty(vissueDtlVO.getLngTyp()) == true) {
			throw new ServiceException("MSG.M08.MSG00005");
		}
		
		int start = (vissueDtlVO.getPage() -1 ) * vissueDtlVO.getPerPage();
		int end = vissueDtlVO.getPerPage();
		
		return voucherGenerateMapper.vissueDtlList(vissueDtlVO, start, end);
	}
	
	@Override
	public Integer logisticsCenterCount(LogisticsCenterVO logisticsCenterVO) {
		if (StringUtils.isEmpty(logisticsCenterVO.getLngTyp()) == true) {
			throw new ServiceException("MSG.M08.MSG00005");
		}
		
		return voucherGenerateMapper.logisticsCenterCount(logisticsCenterVO);
	}
	
	@Override
	public List<LogisticsCenterVO> searchLogisticsCenter(LogisticsCenterVO logisticsCenterVO) {

		if (StringUtils.isEmpty(logisticsCenterVO.getLngTyp()) == true) {
			throw new ServiceException("MSG.M08.MSG00005");
		}
		
		int start = (logisticsCenterVO.getPage() -1 ) * logisticsCenterVO.getPerPage();
		int end = logisticsCenterVO.getPerPage();
		
		return voucherGenerateMapper.searchLogisticsCenter(logisticsCenterVO, start, end);
	}
	
	@Override
	public void addIssueVoucherBulkInfo(VissueVO vissueVO) {
		
		List<String> orgIdList = voucherGenerateMapper.getPoOrgId(vissueVO.getSoId());
		
		if (orgIdList == null || orgIdList.isEmpty() == true) {
			throw new ServiceException("MSG.M09.MSG00038");
		}
		
		String orgId = orgIdList.get(0);
		vissueVO.setVoIssueOrgId(orgId);
		
		Date now = new Date();
		String yyyyMM = new SimpleDateFormat("yyyyMM").format(now);
		String yMM = yyyyMM.substring(3, 6);
		String soIdSubs = vissueVO.getSoId().substring(1,  2);
		
		for (int i = 0; i < vissueVO.getVoIssueCnt(); i++) {
			VissueDtlVO vissueDtlVO = new VissueDtlVO();
			vissueDtlVO.setSoId(vissueVO.getSoId());
			
			String randomValue = String.format("%05d", (int) (Math.random() * 10000));
			String seq = String.format("%05d", sequenceService.createNewSequence("DT012"));
			
			vissueDtlVO.setVoSeqNo(yMM + randomValue + soIdSubs + seq);
			vissueDtlVO.setVissueSeqNo(vissueVO.getVissueSeqNo());
			vissueDtlVO.setVoBarCd(yyyyMM + randomValue);
			vissueDtlVO.setVoStatCd("00");
			vissueDtlVO.setVoIssueAmt(vissueVO.getVoIssueAmt());
			vissueDtlVO.setVoProdCd(vissueVO.getVoProdCd());
			
			vissueDtlVO.setVoIssueDt(vissueVO.getVoIssueDt().replaceAll("-", ""));
			vissueDtlVO.setVoExpiredDt(vissueVO.getVoExpiredDt().replaceAll("-", ""));

			vissueDtlVO.setVoIssueOrgId(orgId);
			
			vissueDtlVO.setRegrId(vissueVO.getRegrId());
			vissueDtlVO.setRegDate(now);
			vissueDtlVO.setChgrId(vissueVO.getRegrId());
			vissueDtlVO.setChgDate(now);
			
			voucherGenerateMapper.insertVissueDtl(vissueDtlVO);

		}
		
		vissueVO.setPoStat("00");
		vissueVO.setChgrId(vissueVO.getRegrId());
		vissueVO.setChgDate(now);
		
		voucherGenerateMapper.updateVissue(vissueVO);
		
	}
	
	@Override
	public int voucherItemCount(VoucherItemVO voucherItemVO) {
		if (StringUtils.isEmpty(voucherItemVO.getSoId()) == true) {
			throw new ServiceException("MSG.M07.MSG00004");
		}
		
		return voucherGenerateMapper.voucherItemCount(voucherItemVO);
	}
	
	@Override
	public List<VoucherItemVO> searchVoucherItem(VoucherItemVO voucherItemVO) {
		
		if (StringUtils.isEmpty(voucherItemVO.getSoId()) == true) {
			throw new ServiceException("MSG.M07.MSG00004");
		}
		
		String todayDt = new SimpleDateFormat("yyyyMM").format(new Date());
		voucherItemVO.setTodayDt(todayDt);
		
		int start = (voucherItemVO.getPage() -1 ) * voucherItemVO.getPerPage();
		int end = voucherItemVO.getPerPage();
		
		return voucherGenerateMapper.searchVoucherItem(voucherItemVO, start, end);
	}
	
	@Override
	public void saveVissueInfo(VissueVO vissueVO) {

		if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
			throw new ServiceException("MSG.M07.MSG00004");
		}
		
		if (StringUtils.isEmpty(vissueVO.getItemId()) == true) {
			throw new ServiceException("MSG.M09.MSG00027");
		}
		
		if (StringUtils.isEmpty(vissueVO.getVoTp()) == true) {
			throw new ServiceException("MSG.M06.MSG00004");
		}
		
		if (StringUtils.isEmpty(vissueVO.getVoProdCd()) == true) {
			throw new ServiceException("MSG.M06.MSG00003");
		}
		
		if (vissueVO.getItemId().length() > 10) {
			// 제품ID의 길이를 초과했습니다.
			throw new ServiceException("MSG.M09.MSG00025");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Integer newSeq = voucherGenerateMapper.newVoIssueNo(cal.getTime());
		newSeq = newSeq == null ? 1 : newSeq;
		
		Date now = new Date();
		
		String newVissueSeqNo = new SimpleDateFormat("yyyyMMdd").format(now) + String.format("%04d", newSeq);
		vissueVO.setVissueSeqNo(newVissueSeqNo);
		vissueVO.setVoExpiredDt("99991231");
		
		vissueVO.setRegDate(now);
		vissueVO.setChgDate(now);
		
		removeDateHyphen(vissueVO);
		
		voucherGenerateMapper.insertVissue(vissueVO);
		
	}
	
	@Override
	public void addOrder(VissueVO vissueVO) {
		if (StringUtils.isEmpty(vissueVO.getPoDt()) == true) {
			throw new ServiceException("MSG.M06.MSG00011");
		}
		
		if (StringUtils.isEmpty(vissueVO.getDlgdAgreeDt()) == true) {
			throw new ServiceException("message.distributor.logistics.voucherMgt.alert.emptyLgstAgreeDt");
		}
		
		if (StringUtils.isEmpty(vissueVO.getLgstCentId()) == true) {
			throw new ServiceException("MSG.M05.MSG00012");
		}
		
		List<String> orgIdList = voucherGenerateMapper.getPoOrgId(vissueVO.getSoId());
		
		if (orgIdList == null || orgIdList.isEmpty() == true) {
			throw new ServiceException("MSG.M09.MSG00038");
		}
		
		vissueVO.setPoDt(vissueVO.getPoDt().replaceAll("-", ""));
		vissueVO.setDlgdAgreeDt(vissueVO.getDlgdAgreeDt().replaceAll("-", ""));
		
		// 세금정보 조회
		VissueVO tax = voucherGenerateMapper.getTaxMast(new SimpleDateFormat("yyyyMM").format(new Date()));
		
		// 세금 계산
		int vatAmt = 0;
		double amt = vissueVO.getPoAmt() == null ? 0.0 : vissueVO.getPoAmt();
		
		vatAmt = (int) (amt * tax.getTaxRate() / 100.0);
		
        double totPrchsAmt = amt + vatAmt;
        // 발주총금액 보정
        totPrchsAmt = totPrchsAmt / 1000.0;
        totPrchsAmt = Double.parseDouble(String.format("%.0f", totPrchsAmt));
        totPrchsAmt = totPrchsAmt * 1000.0;
		
        String maxPoNo = voucherGenerateMapper.getMaxPoNo();
        int maxNo = Integer.parseInt(maxPoNo.substring(2, maxPoNo.length()));
        String newPoNo = "PO" + String.format("%08d", maxNo + 1);
        
        PoVO poVO = new PoVO();
        
        poVO.setSoId(vissueVO.getSoId());
        poVO.setPoNo(newPoNo);
        poVO.setPoPrgrStatCd("10");
        poVO.setAddTx((double) vatAmt);
        poVO.setTotPrchsAmt(totPrchsAmt);
        poVO.setDlvZipCd(vissueVO.getDlvZipCd());
        poVO.setDlvBssAddr(vissueVO.getDlvBssAddr());
        poVO.setDlvDtlAddr(vissueVO.getDlvDtlAddr());
        poVO.setStatProcId(vissueVO.getRegrId());
        poVO.setPoOrgId(orgIdList.get(0));
        poVO.setLgstCentId(vissueVO.getLgstCentId());
        poVO.setActncInchrg(vissueVO.getRegrId());
        poVO.setDlgdAgreeDt(vissueVO.getDlgdAgreeDt());
        poVO.setPoConfDt(vissueVO.getPoDt());
        poVO.setRegrId(vissueVO.getRegrId());
        poVO.setRegDate(new Date());
        poVO.setChgDate(new Date());
        
        poVO.setItemId(vissueVO.getItemId());
        poVO.setMncoId(vissueVO.getMncoId());
        
        voucherGenerateMapper.insertPo(poVO);
        voucherGenerateMapper.insertPoStatProcHist(poVO);
        
        vissueVO.setPoNo(newPoNo);
        vissueVO.setChgrId(vissueVO.getRegrId());
        vissueVO.setChgDate(new Date());
        
        voucherGenerateMapper.updateVissuePoNo(vissueVO);
        
        vissueVO.setPoStat("10");
        
        voucherGenerateMapper.updateVissuePoStat(vissueVO);
	}
	
	@Override
	public Integer vissuePoInfoListCount(VissueVO vissueVO) {

		if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getPoNo()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getItemId()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getMncoId()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getPoStat()) == true) {
			
		}
		
		return voucherGenerateMapper.vissuePoInfoListCount(vissueVO);
	}
	
	@Override
	public List<VissueVO> vissuePoInfoList(VissueVO vissueVO) {
		
		if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getPoNo()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getItemId()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getMncoId()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getPoStat()) == true) {
			
		}
		
		return voucherGenerateMapper.vissuePoInfoList(vissueVO);
	}
	
	@Override
	public void saveConfirmOrder(VissueVO vissueVO) {
		
		if (StringUtils.isEmpty(vissueVO.getSoId()) == true) {
			
		}
		
		if (StringUtils.isEmpty(vissueVO.getVissueSeqNo()) == true) {
			
		}
		
		Date now = new Date();
		String nowDt = new SimpleDateFormat("yyyyMMdd").format(now);
		String nowDttm = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		vissueVO.setRegDate(now);
		vissueVO.setChgDate(now);
		vissueVO.setLgstProcDttm(nowDttm);
		vissueVO.setPoDt(vissueVO.getPoDt().replaceAll("-", ""));
		
        /* 바우쳐 마스터 초기 생성 */
        // 입고대기 상태의 바우쳐발행상세 데이터를 초기 데이터로서, 바우쳐마스터에 INSERT.
		voucherGenerateMapper.insertVeqtInfo(vissueVO);
		
		// 이력생성일련번호 조회
		String maxCrtSeqNo = voucherGenerateMapper.getMaxCrtSeqNo(vissueVO); 
		String newCrtSeqNo = String.format("%010d", Integer.parseInt(maxCrtSeqNo) + 1);
		vissueVO.setCrtSeqNo(newCrtSeqNo);
		
		// 해당 발행일련번호 바우쳐이력에 일괄 INSERT.
		voucherGenerateMapper.insertVeqtTransBulfInfo(vissueVO);
		
		// 발주상태 : 발주확정으로 UPDATE
		vissueVO.setPoStat("20");
		voucherGenerateMapper.updateVissuePoStat(vissueVO);
		
		// 바우쳐상태 : 입고대기로 UPDATE
		VissueDtlVO vissueDtlVO = new VissueDtlVO();
		vissueDtlVO.setVoStatCd("10");
		vissueDtlVO.setChgrId(vissueVO.getChgrId());
		vissueDtlVO.setChgDate(now);
		vissueDtlVO.setSoId(vissueVO.getSoId());
		vissueDtlVO.setVissueSeqNo(vissueVO.getVissueSeqNo());
		
		voucherGenerateMapper.updateVissueDtlVoStatCd(vissueDtlVO);
		
		// 발주일시 : SYSDATE로 UPDATE
		vissueDtlVO.setVoPoDttm(nowDttm);
		voucherGenerateMapper.updateVissueDtlVoPoDttm(vissueDtlVO);

		// 제조사 발주진행상태를 저장
		PoVO poVO = new PoVO();
		poVO.setPoPrgrStatCd("20");
		poVO.setPoConfDt(nowDt);
		poVO.setStatProcId(vissueVO.getRegrId());
		poVO.setRegDate(now);
		poVO.setRegrId(vissueVO.getRegrId());
		poVO.setChgDate(now);
		poVO.setChgrId(vissueVO.getChgrId());
		poVO.setSoId(vissueVO.getSoId());
		poVO.setPoNo(vissueVO.getPoNo());
		
		voucherGenerateMapper.updatePoStatInfo(poVO);
		
		// 제조사 발주이력을 등록
		voucherGenerateMapper.insertPoStatProcHist(poVO);
		
		// 발주처리대기상태정보를 저장
		voucherGenerateMapper.insertPoIdlDtl(poVO);
		
	}
	
	@Override
	public void saveCancelOrder(VissueVO vissueVO) {
//        mapParam.put("PO_PRGR_STAT_CD", "25");
//        mapParam.put("STAT_PROC_ID", mapParam.get("REGR_ID"));
//        mapParam.put("REG_ID", mapParam.get("REGR_ID"));
//        mapParam.put("CHG_ID", mapParam.get("CHGR_ID"));
		
		Date now = new Date();
		String nowDt = new SimpleDateFormat("yyyyMMdd").format(now);
		String nowDttm = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		vissueVO.setRegDate(now);
		vissueVO.setChgDate(now);
		vissueVO.setPoDt(vissueVO.getPoDt().replaceAll("-", ""));
		
		PoVO poVO = new PoVO();
		poVO.setSoId(vissueVO.getSoId());
		poVO.setPoNo(vissueVO.getPoNo());
		poVO.setPoPrgrStatCd("25");
		poVO.setStatProcId(vissueVO.getRegrId());
		poVO.setStatProcDttm(nowDttm);
		poVO.setPoConfDt(nowDt);
		poVO.setRegrId(vissueVO.getRegrId());
		poVO.setRegDate(now);
		poVO.setChgrId(vissueVO.getChgrId());
		poVO.setChgDate(now);
		
		// 제조사 발주진행상태를 저장
		voucherGenerateMapper.updatePoStatInfo(poVO);
		// 제조사 발주이력을 등록
		voucherGenerateMapper.insertPoStatProcHist(poVO);
		// 발주처리대기상태정보를 삭제
		voucherGenerateMapper.deletePoIdlDtl(poVO);
		// 발주상태 : 발주취소로 UPDATE
		vissueVO.setPoStat("25");
		voucherGenerateMapper.updateVissuePoStat(vissueVO);
		// 바우쳐상태 : 비가용으로 UPDATE
		VissueDtlVO vissueDtlVO = new VissueDtlVO();
		vissueDtlVO.setVoStatCd("40");
		vissueDtlVO.setChgDate(now);
		vissueDtlVO.setChgrId(vissueVO.getChgrId());
		vissueDtlVO.setSoId(vissueVO.getSoId());
		vissueDtlVO.setVissueSeqNo(vissueVO.getVissueSeqNo());
		
		voucherGenerateMapper.updateVissueDtlVoStatCd(vissueDtlVO);
		
		// 바우쳐마스터 정보를 저장(발주취소)
		voucherGenerateMapper.updateVeqtInfoOrderCancle(vissueDtlVO);
		
		// 이력생성일련번호 조회
		String maxCrtSeqNo = voucherGenerateMapper.getMaxCrtSeqNo(vissueVO); 
		String newCrtSeqNo = String.format("%010d", Integer.parseInt(maxCrtSeqNo) + 1);
		vissueVO.setCrtSeqNo(newCrtSeqNo);
		
		// 해당 발행일련번호 바우쳐이력에 일괄 INSERT.
		voucherGenerateMapper.insertVeqtTransBulfInfo(vissueVO);
	}
	
	@Override
	public void saveVoucherTransfer(VissueVO vissueVO) {
		
		Date now = new Date();
		String nowDt = new SimpleDateFormat("yyyyMMdd").format(now);
		String nowDttm = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		vissueVO.setRegDate(now);
		vissueVO.setChgDate(now);
		
		// 사업자 이관할 바우쳐 갯수
		int transferVoucherCount = voucherGenerateMapper.transferVoucherCount(vissueVO);
		// 바우쳐종류, 바우쳐금액
		List<VissueVO> transferVissueInfoList= voucherGenerateMapper.getTransferVissueInfo(vissueVO);
		
		// 제조사ID
		String mncoId = voucherGenerateMapper.getMncoId(vissueVO);
		// 물류센터조직ID
		String orgId = voucherGenerateMapper.getOrgId(vissueVO);
		
		if (transferVissueInfoList == null || transferVissueInfoList.isEmpty() == true) {
			// 이관 할 데이터 없음
			throw new ServiceException("MSG.M08.MSG00044");
		}
		
		if (transferVissueInfoList.size() > 1) {
			// 많은 데이터가 검색되었음
			throw new ServiceException("MSG.M01.MSG00042");
		}
		
		VissueVO transferVissueInfo = transferVissueInfoList.get(0);
		
		// 발주번호
        String maxPoNo = voucherGenerateMapper.getMaxPoNo();
        int maxNo = Integer.parseInt(maxPoNo.substring(2, maxPoNo.length()));
        String newPoNo = "PO" + String.format("%08d", maxNo + 1);
        // 바우쳐발행번호
        String maxVissueSeqNo = voucherGenerateMapper.getMaxVissueSeqNo(nowDt);
        String newVissueSeqNo = nowDt + String.format("%04d", Integer.parseInt(maxVissueSeqNo) + 1);
        
        // 기존 TDNDT_VISSUE 변경
        vissueVO.setTransferCnt(transferVoucherCount);
        vissueVO.setVoIssueCnt(transferVissueInfo.getVoIssueCnt());
        voucherGenerateMapper.updateVoIssueCnt(vissueVO);
        
        // TDNDT_VISSUE 생성
        VissueVO newVissueVO = new VissueVO();
        newVissueVO.setSoId(vissueVO.getSoId());
        newVissueVO.setVissueSeqNo(newVissueSeqNo);
        newVissueVO.setItemId(vissueVO.getItemId());
        newVissueVO.setMncoId(mncoId);
        newVissueVO.setVoTp(transferVissueInfo.getVoTp());
        newVissueVO.setVoIssueAmt(transferVissueInfo.getVoIssueAmt());
        newVissueVO.setVoProdCd(vissueVO.getVoProdCd());
        newVissueVO.setVoIssueCnt(transferVoucherCount);
        newVissueVO.setVoIssueDt(nowDt);
        newVissueVO.setVoExpiredDt("99991231");
        newVissueVO.setPoNo(newPoNo);
        newVissueVO.setPoDt(nowDt);
        newVissueVO.setPoStat("20");
        
        voucherGenerateMapper.insertVissue(newVissueVO);
        
        // TDNDT_VISSUE_DTL 변경
        newVissueVO.setVoIssueOrgId(orgId);
        newVissueVO.setVoPoDttm(nowDttm);
        
        voucherGenerateMapper.updateVissueDtl(newVissueVO);
        
        // TDNDT_VEQT 변경
        newVissueVO.setLgstProcDttm(nowDttm);
        
        voucherGenerateMapper.updateVeqt(newVissueVO);
        
        // TDNDT_VEQT_TRANS 변경
        voucherGenerateMapper.updateVeqtTrans(newVissueVO);
        
        // TDNDT_PO 생성
        PoVO poVO = new PoVO();
        poVO.setSoId(vissueVO.getSoId());
        poVO.setPoNo(newPoNo);
        poVO.setPoOrgId(orgId);
        poVO.setLgstCentId(orgId);
        poVO.setMncoId(mncoId);
        poVO.setPoPrgrStatCd("20");
        poVO.setItemId(vissueVO.getItemId());
        poVO.setItemTpCd("V");
        poVO.setDlgdAgreeDt(nowDt);
        poVO.setPoQty((long) transferVoucherCount);
        poVO.setPoUtPrc(transferVissueInfo.getVoIssueAmt());
        poVO.setPoAmt(poVO.getPoQty() * poVO.getPoUtPrc());
        poVO.setAddTx(poVO.getPoAmt() * 0.18);
        poVO.setTotPrchsAmt(poVO.getPoAmt() * 1.18);
        poVO.setPoRegDt(nowDt);
        poVO.setPoConfDt(nowDt);
        poVO.setActncInchrg(vissueVO.getRegrId());
        poVO.setRegrId(vissueVO.getRegrId());
        poVO.setRegDate(now);
        poVO.setChgrId(vissueVO.getRegrId());
        poVO.setChgDate(now);
        
        voucherGenerateMapper.insertPo(poVO);
        
        // TDNDT_PO_IDL_DTL 생성
        poVO.setChgrId(null);
        poVO.setChgDate(null);
        
        voucherGenerateMapper.insertPoIdlDtl(poVO);
        
        // TDNDT_PO_STAT_PROC_HIST 생성
        poVO.setPoPrgrStatCd("10");
        poVO.setStatProcId(vissueVO.getRegrId());
        poVO.setStatProcDttm(nowDttm);
        
        voucherGenerateMapper.insertPoStatProcHist(poVO);
        
        poVO.setPoPrgrStatCd("20");
        
        voucherGenerateMapper.insertPoStatProcHist(poVO);
		
	}
	
	@Override
	public List<ProductMngVO> productList(
			ProductMngVO productMngVO, int page,	int perPage) {
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return voucherGenerateMapper.productList(productMngVO, start, end);
	}

	@Override
	public int productCount(ProductMngVO productMngVO) {
		return voucherGenerateMapper.productCount(productMngVO);
	}
	
}
