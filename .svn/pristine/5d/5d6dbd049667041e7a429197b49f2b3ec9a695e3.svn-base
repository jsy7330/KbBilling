package com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.orderDeliveryMgt.DeliveryInspectionVO;
import com.ntels.ccbs.distribute.mapper.logistics.orderDeliveryMgt.DeliveryInspectionMapper;
import com.ntels.ccbs.distribute.service.logistics.orderDeliveryMgt.DeliveryInspectionService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class DeliveryInspectionServiceImpl implements DeliveryInspectionService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeliveryInspectionMapper deliveryInspectionMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<DeliveryInspectionVO> inOutActncList(
			DeliveryInspectionVO deliveryInspectionVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return deliveryInspectionMapper.inOutActncList(deliveryInspectionVO, start, end);
	}

	@Override
	public int inOutActncCount(DeliveryInspectionVO deliveryInspectionVO) {
		return deliveryInspectionMapper.inOutActncCount(deliveryInspectionVO);
	}

	@Override
	public List<DeliveryInspectionVO> inActncList(
			DeliveryInspectionVO deliveryInspectionVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return deliveryInspectionMapper.inActncList(deliveryInspectionVO, start, end);
	}

	@Override
	public int inActncCount(DeliveryInspectionVO deliveryInspectionVO) {
		return deliveryInspectionMapper.inActncCount(deliveryInspectionVO);
	}

	@Override
	public int insertInout(DeliveryInspectionVO deliveryInspectionVO) {
		
		String inoutId = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_INOUT_ID, "IO", 10);
		deliveryInspectionVO.setInoutId(inoutId);
		deliveryInspectionVO.setStatProcId(deliveryInspectionVO.getRegrId());
		deliveryInspectionVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		int count = deliveryInspectionMapper.insertInout(deliveryInspectionVO);
		
		deliveryInspectionMapper.insertInoutDtl(deliveryInspectionVO);
		
		deliveryInspectionMapper.insertInoutStatProcHist(deliveryInspectionVO);
		
		return count;
		
		
	}

	@Override
	public int updateInout(DeliveryInspectionVO deliveryInspectionVO) {
		
		deliveryInspectionVO.setStatProcId(deliveryInspectionVO.getRegrId());
		deliveryInspectionVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		int count = deliveryInspectionMapper.updateInout(deliveryInspectionVO);

		deliveryInspectionMapper.updateInoutDtl(deliveryInspectionVO);
		
		return count;
	}

	@Override
	public int deleteInout(DeliveryInspectionVO deliveryInspectionVO) {
		
		int count = deliveryInspectionMapper.deleteInout(deliveryInspectionVO);

		deliveryInspectionMapper.deleteInoutDtl(deliveryInspectionVO);
		
		return count;
	}

	@Override
	public List<DeliveryInspectionVO> selectInOutEqtList(
			DeliveryInspectionVO deliveryInspectionVO) {
		return deliveryInspectionMapper.selectInOutEqtList(deliveryInspectionVO);
	}

	@Override
	public List<DeliveryInspectionVO> selectInOutUsimList(
			DeliveryInspectionVO deliveryInspectionVO) {
		return deliveryInspectionMapper.selectInOutUsimList(deliveryInspectionVO);
	}

	@Override
	public List<DeliveryInspectionVO> selectInOutVEqtList(
			DeliveryInspectionVO deliveryInspectionVO) {
		return deliveryInspectionMapper.selectInOutVEqtList(deliveryInspectionVO);
	}

	@Override
	public int insertInoutList(
			List<DeliveryInspectionVO> deliveryInspectionVOs,
			SessionUser session) {
		
		Date sysdata = DateUtil.sysdate();
		
		for(DeliveryInspectionVO deliveryInspectionVO : deliveryInspectionVOs){
			
			deliveryInspectionVO.setRegrId(session.getUserId());
			deliveryInspectionVO.setChgrId(session.getUserId());
			deliveryInspectionVO.setRegDate(sysdata);
			deliveryInspectionVO.setChgDate(sysdata);
			
			
			deliveryInspectionVO.setApprYn("N");
		}
		
		int count = 0;
		
		if(deliveryInspectionVOs.get(0).getItemTpCd().equals("C") || deliveryInspectionVOs.get(0).getItemTpCd().equals("I")){
			
			count = deliveryInspectionMapper.insertEqt(deliveryInspectionVOs);
			
			deliveryInspectionMapper.insertEqtOthsAttr(deliveryInspectionVOs);
			
			deliveryInspectionMapper.insertInoutEqt(deliveryInspectionVOs);
		}else if(deliveryInspectionVOs.get(0).getItemTpCd().equals("U")){
			
			List<String> usimSeqs = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_USIM_SEQ, "U", 10, deliveryInspectionVOs.size());
			
			for(int i=-0; i < deliveryInspectionVOs.size(); i++){
				deliveryInspectionVOs.get(i).setUsimSeq(usimSeqs.get(i));
				deliveryInspectionVOs.get(i).setEqtSeq(usimSeqs.get(i));
			}
			
			count = deliveryInspectionMapper.insertUsim(deliveryInspectionVOs);
			
			deliveryInspectionMapper.insertUsimOthsAttr(deliveryInspectionVOs);
			
			deliveryInspectionMapper.insertInoutEqt(deliveryInspectionVOs);
			
		}else if(deliveryInspectionVOs.get(0).getItemTpCd().equals("V")){
			
			DeliveryInspectionVO vissueSeqNoVo = deliveryInspectionMapper.selectVissue(deliveryInspectionVOs.get(0));
			
			deliveryInspectionVOs.get(0).setVissueSeqNo(vissueSeqNoVo.getVissueSeqNo());
			deliveryInspectionVOs.get(0).setApprYn("N");
			
			
			deliveryInspectionMapper.insertInoutEqt2(deliveryInspectionVOs.get(0));
			deliveryInspectionVOs.get(0).setVoStatCd("20"); // 바우쳐상태 : 입고
			
			deliveryInspectionMapper.updateVeqt(deliveryInspectionVOs.get(0));
			
			String crtSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_CRT_SEQ_NO, "1", 10);
			deliveryInspectionVOs.get(0).setCrtSeqNo(crtSeqNo);
			
			deliveryInspectionMapper.insertVeqtTrans(deliveryInspectionVOs.get(0));
			
			
		}
		
		deliveryInspectionVOs.get(0).setStatProcId(session.getUserId());
		deliveryInspectionVOs.get(0).setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		deliveryInspectionMapper.updateInoutPrgrStatCd(deliveryInspectionVOs.get(0));
		
		deliveryInspectionMapper.insertInoutStatProcHist(deliveryInspectionVOs.get(0));
		
		return count;
	}

	@Override
	public int insertInspection(List<DeliveryInspectionVO> deliveryInspectionVOs, SessionUser session) {

		int count = 0;
		Date sysdata = DateUtil.sysdate();
		
		DeliveryInspectionVO paramVO = deliveryInspectionVOs.get(0);
		
		if(paramVO.getItemTpCd().equals("C") || paramVO.getItemTpCd().equals("U") || paramVO.getItemTpCd().equals("I")){
			
			paramVO.setEqtSeqList(deliveryInspectionVOs);
			
			paramVO.setRegrId(session.getUserId());
			paramVO.setChgrId(session.getUserId());
			paramVO.setRegDate(sysdata);
			paramVO.setChgDate(sysdata);
			paramVO.setStatProcId(session.getUserId());
			paramVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			count = deliveryInspectionMapper.updateInoutEqtApprYn(paramVO);
			
			if(paramVO.getInoutPrgrStatCheck().equals("Y")){
				deliveryInspectionMapper.updateInoutPrgrStatCd(paramVO);
				
				deliveryInspectionMapper.insertInoutStatProcHist(paramVO);
			}
			
		}else if(paramVO.getItemTpCd().equals("V")){
			
			
			paramVO.setRegrId(session.getUserId());
			paramVO.setChgrId(session.getUserId());
			paramVO.setRegDate(sysdata);
			paramVO.setChgDate(sysdata);
			paramVO.setStatProcId(session.getUserId());
			paramVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			count = deliveryInspectionMapper.updateInoutEqtApprYn2(paramVO);
			
			
			if(deliveryInspectionMapper.selectEqtCount(paramVO) == 0){
				
				deliveryInspectionMapper.updateInoutPrgrStatCd(paramVO);
				
				deliveryInspectionMapper.insertInoutStatProcHist(paramVO);
				
			}
			
			
		}
		
		return count;
	}

	@Override
	public int insertWearingAc(DeliveryInspectionVO deliveryInspectionVO,
			SessionUser session) {

		int count = 0;
		Date sysdata = DateUtil.sysdate();
		
		List<DeliveryInspectionVO> deliveryInspectionVOs = new ArrayList<DeliveryInspectionVO>();
		
		if(deliveryInspectionVO.getItemTpCd().equals("C") || deliveryInspectionVO.getItemTpCd().equals("I")){
			
			deliveryInspectionVOs = deliveryInspectionMapper.selectInOutEqtList(deliveryInspectionVO);
			
			deliveryInspectionVO.setEqtSeqList(deliveryInspectionVOs);
			deliveryInspectionVO.setOwnLocCd(deliveryInspectionVO.getOwnOrgId());
			deliveryInspectionVO.setLgstProcStatCd("001");	// 물류처리상태코드 (발주입고)
			deliveryInspectionVO.setFrstInDt(DateUtil.getDateStringYYYYMMDD(0));
			
			count = deliveryInspectionMapper.updateEqt(deliveryInspectionVO);
			
			
			List<DeliveryInspectionVO> param2 =  deliveryInspectionMapper.selectInoutEqt(deliveryInspectionVO);
			
			List<String> histSeqList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, param2.size());
			
			for(int i=-0; i < param2.size(); i++){
				
				param2.get(i).setHistSeq(histSeqList.get(i));
				param2.get(i).setUpdProcClCd("30"); 	// 물류처리상태변경:30
				param2.get(i).setAftrUpdCd("001");	//"001"; // 물류처리상태코드 (발주입고)
				param2.get(i).setUpdProcId(session.getUserId());
				param2.get(i).setUpdProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
				
				param2.get(i).setRegrId(session.getUserId());
				param2.get(i).setChgrId(session.getUserId());
				param2.get(i).setRegDate(sysdata);
				param2.get(i).setChgDate(sysdata);
			}
			
			deliveryInspectionMapper.insertUpdProcHist(param2);
			
			
			List<String> histSeqList2 = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, param2.size());
			
			for(int i=-0; i < param2.size(); i++){
				
				param2.get(i).setHistSeq(histSeqList2.get(i));
				param2.get(i).setUpdProcClCd("40"); 	// 물류처리상태변경:30
			}
			
			deliveryInspectionMapper.insertUpdProcHist(param2);
			
		}else if(deliveryInspectionVO.getItemTpCd().equals("U")){
			
			deliveryInspectionVOs = deliveryInspectionMapper.selectInOutUsimList(deliveryInspectionVO);
			
			deliveryInspectionVO.setEqtSeqList(deliveryInspectionVOs);
			deliveryInspectionVO.setOwnLocCd(deliveryInspectionVO.getOwnOrgId());
			deliveryInspectionVO.setLgstProcStatCd("001");	// 물류처리상태코드 (발주입고)
			deliveryInspectionVO.setFrstInDt(DateUtil.getDateStringYYYYMMDD(0));
			
			count = deliveryInspectionMapper.updateUsim(deliveryInspectionVO);
			
			List<DeliveryInspectionVO> param2 =  deliveryInspectionMapper.selectInoutEqt(deliveryInspectionVO);
			
			List<String> histSeqList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, param2.size());
			
			for(int i=-0; i < param2.size(); i++){
				
				param2.get(i).setHistSeq(histSeqList.get(i));
				param2.get(i).setUpdProcClCd("30"); 	// 물류처리상태변경:30
				param2.get(i).setAftrUpdCd("001");	//"001"; // 물류처리상태코드 (발주입고)
				param2.get(i).setUpdProcId(session.getUserId());
				param2.get(i).setUpdProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
				
				param2.get(i).setRegrId(session.getUserId());
				param2.get(i).setChgrId(session.getUserId());
				param2.get(i).setRegDate(sysdata);
				param2.get(i).setChgDate(sysdata);
			}
			
			deliveryInspectionMapper.insertUpdProcHist(param2);
			
		}else if(deliveryInspectionVO.getItemTpCd().equals("V")){
			
			
			DeliveryInspectionVO vissueSeqNoVo = deliveryInspectionMapper.selectVissue(deliveryInspectionVO);
			
			deliveryInspectionVO.setVissueSeqNo(vissueSeqNoVo.getVissueSeqNo());
			deliveryInspectionVO.setLgstProcStatCd("001");	// 물류처리상태코드 (발주입고)
			deliveryInspectionVO.setOwnLocCd(deliveryInspectionVO.getOwnOrgId());
			deliveryInspectionVO.setFrstInDt(DateUtil.getDateStringYYYYMMDD(0));
			deliveryInspectionVO.setVoStatCd("30");	// 바우쳐상태 : 가용입고
			
			
			deliveryInspectionMapper.updateVeqt2(deliveryInspectionVO);
			
			
			String crtSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_CRT_SEQ_NO, "1", 10);
			deliveryInspectionVO.setCrtSeqNo(crtSeqNo);
			
			deliveryInspectionMapper.insertVeqtTrans(deliveryInspectionVO);
			
			
			
		}
		
		deliveryInspectionVO.setInoutPrgrStatCd("20"); // 입고진행상태코드 (입고승인)
		deliveryInspectionVO.setInoutDt(DateUtil.getDateStringYYYYMMDD(0));
		deliveryInspectionVO.setStatProcId(deliveryInspectionVO.getRegrId());
		deliveryInspectionVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		deliveryInspectionMapper.updateInout2(deliveryInspectionVO);
		
		deliveryInspectionMapper.insertInoutStatProcHist(deliveryInspectionVO);
		
		int qty = deliveryInspectionMapper.getActncMinusInOutQty(deliveryInspectionVO);
		if(qty < 1){
			deliveryInspectionMapper.deleteActncIdlDtl(deliveryInspectionVO);
			
			//할당관련
			List<DeliveryInspectionVO> param3 = deliveryInspectionMapper.selectInoutDtlActncNoList(deliveryInspectionVO);
			
			List<String> asgnNoList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_ASGN_NO, "AS", 10, param3.size());
			
			for(int i=-0; i < param3.size(); i++){
			
				param3.get(i).setAsgnNo(asgnNoList.get(i));
				//param3.get(i).setAsgnOrgId("6000000004");		//할당하고자 하는 SALES DIVISION ORG_ID  임의 지정
				param3.get(i).setAsgnOrgId(deliveryInspectionVO.getSalesDivisionId());		//할당하고자 하는 SALES DIVISION ORG_ID  임의 지정
				param3.get(i).setAsgnDt(DateUtil.getDateStringYYYYMMDD(0));
				param3.get(i).setAsgnMngOrgId(session.getOrgId());
				param3.get(i).setAsgnMngInchrgId(session.getUserId());
				
				param3.get(i).setRegrId(session.getUserId());
				param3.get(i).setChgrId(session.getUserId());
				param3.get(i).setRegDate(sysdata);
				param3.get(i).setChgDate(sysdata);
				
				if(param3.get(i).getEqtUseCd() == null){
					param3.get(i).setEqtUseCd("");
				}
				if(param3.get(i).getClorCd() == null){
					param3.get(i).setClorCd("");
				}
				
				param3.get(i).setUppLvAsgnNo("");	 //상위 할당번호(직전 SEQ번호)
				param3.get(i).setUppLvAsgnOrgId("");	//상위 할당번호의 ASGN_ORG_ID (SALES DIVISION ORG_ID)		
			}
				
			//1. SALES ADMIN -> SALES DIVISION
			deliveryInspectionMapper.insertAsgn(param3);
			
				
			List<String> asgnNoList2 = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_ASGN_NO, "AS", 10, param3.size());	
			for(int i=-0; i < param3.size(); i++){
				
				String bAsNo = param3.get(i).getAsgnNo();
				String bOrgId = param3.get(i).getAsgnOrgId();
				
				param3.get(i).setUppLvAsgnNo(bAsNo);	 //상위 할당번호(직전 SEQ번호)
				param3.get(i).setUppLvAsgnOrgId(bOrgId);	//상위 할당번호의 ASGN_ORG_ID (SALES DIVISION ORG_ID)				
				
				param3.get(i).setAsgnNo(asgnNoList2.get(i));
				//param3.get(i).setAsgnOrgId("6000000005");		//할당하고자 하는 SALES TEAM ORG_ID
				param3.get(i).setAsgnOrgId(deliveryInspectionVO.getSalesTeamId());		//할당하고자 하는 SALES TEAM ORG_ID
				param3.get(i).setAsgnDt(DateUtil.getDateStringYYYYMMDD(0));

				param3.get(i).setAsgnQty("0");
				param3.get(i).setNoasgnQty(param3.get(i).getStckOwnQty());
				
			}
			
			//2.SALES DIVISION -> SALES TEAM
			deliveryInspectionMapper.insertAsgn(param3);
			
		}
		
		
		return count;
	}

	@Override
	public List<DeliveryInspectionVO> selectSalesDivision(
			DeliveryInspectionVO deliveryInspectionVO) {
		return deliveryInspectionMapper.selectSalesDivision(deliveryInspectionVO);
	}

	@Override
	public List<DeliveryInspectionVO> selectSalesTeam(
			DeliveryInspectionVO deliveryInspectionVO) {
		return deliveryInspectionMapper.selectSalesTeam(deliveryInspectionVO);
	}
	
	

}

