package com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.DistributorReceiptInspVO;
import com.ntels.ccbs.distribute.mapper.logistics.assignmentOrderMgt.DistributorReceiptInspMapper;
import com.ntels.ccbs.distribute.service.logistics.assignmentOrderMgt.DistributorReceiptInspService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class DistributorReceiptInspServiceImpl implements DistributorReceiptInspService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DistributorReceiptInspMapper distributorReceiptInspMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<DistributorReceiptInspVO> inoutDtlList(
			DistributorReceiptInspVO distributorReceiptInspVO, int page,
			int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return distributorReceiptInspMapper.inoutDtlList(distributorReceiptInspVO, start, end);
	}

	@Override
	public int inoutDtlCount(DistributorReceiptInspVO distributorReceiptInspVO) {
		return distributorReceiptInspMapper.inoutDtlCount(distributorReceiptInspVO);
	}

	@Override
	public List<DistributorReceiptInspVO> inoutDtlList2(
			DistributorReceiptInspVO distributorReceiptInspVO) {

		List<DistributorReceiptInspVO> returnVO = distributorReceiptInspMapper.inoutDtlList2(distributorReceiptInspVO);
		
		if(returnVO == null || returnVO.size() < 1){
			
			returnVO = distributorReceiptInspMapper.inoutDtlList3(distributorReceiptInspVO);
			
		}
		
		
		return returnVO;
	}

	@Override
	public List<DistributorReceiptInspVO> inoutDtlList3(
			DistributorReceiptInspVO distributorReceiptInspVO) {

		List<DistributorReceiptInspVO> returnVO = new ArrayList<DistributorReceiptInspVO>(); 
		
		if(distributorReceiptInspVO.getItemTpCd().equals("C")){
			
			returnVO = distributorReceiptInspMapper.inoutEqtList(distributorReceiptInspVO);
		}else if(distributorReceiptInspVO.getItemTpCd().equals("U")){
			
			returnVO = distributorReceiptInspMapper.inoutUsimList(distributorReceiptInspVO);
		}else if(distributorReceiptInspVO.getItemTpCd().equals("V")){
			
			returnVO = distributorReceiptInspMapper.inoutVeqtList(distributorReceiptInspVO);
		}
		
		return returnVO;
	}

	@Override
	public int insertInsp(DistributorReceiptInspVO distributorReceiptInspVO,
			SessionUser session) {
		
		int count = 0;
		String inoutPrgrStatCd = "40"; // 입고검수
		String inoutChk = "Y";
		String inoutId = "";
		
		for(DistributorReceiptInspVO data : distributorReceiptInspVO.getVoList()){
			if (data.getApprYn().equals("N")) {
                inoutPrgrStatCd = "30"; // 입고확정
                break;
            }
		}
		
		distributorReceiptInspVO.setRegrId(session.getUserId());
		distributorReceiptInspVO.setChgrId(session.getUserId());
		distributorReceiptInspVO.setRegDate(DateUtil.sysdate());
		distributorReceiptInspVO.setChgDate(DateUtil.sysdate());
		distributorReceiptInspVO.setStatProcId(session.getUserId());
		distributorReceiptInspVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		
		if(distributorReceiptInspVO.getInoutId() == null || distributorReceiptInspVO.getInoutId().equals("")){
			inoutChk = "N";
			inoutId= sequenceService.createNewSequence(Consts.SEQ_CODE.DT_INOUT_ID, "IO", 10);
			
			distributorReceiptInspVO.setInoutId(inoutId);
			distributorReceiptInspVO.setInoutPrgrStatCd(inoutPrgrStatCd);
			distributorReceiptInspVO.setInoutClCd("1");
			
			count = distributorReceiptInspMapper.insertInout(distributorReceiptInspVO);
			
			distributorReceiptInspMapper.insertInoutStatProcHist(distributorReceiptInspVO);
			
			
			int inoutQty = Integer.parseInt(distributorReceiptInspVO.getInoutQty());
			int inoutUtPrc = Integer.parseInt(distributorReceiptInspVO.getInoutUtPrc());
			
			int tx = 0;
			int inoutAddTx = inoutQty * (tx*inoutUtPrc); 		//부가세
			int inoutAmt = inoutQty * inoutUtPrc;
			int inoutTotAmt = inoutAddTx + inoutAmt;
			
			distributorReceiptInspVO.setInoutAddTx(String.valueOf(inoutAddTx));
			distributorReceiptInspVO.setInoutAmt(String.valueOf(inoutAmt));
			distributorReceiptInspVO.setInoutTotAmt(String.valueOf(inoutTotAmt));
			
			distributorReceiptInspMapper.insertInoutDtl(distributorReceiptInspVO);
			
		}else{
			
			count = 1;
			
			if(!inoutPrgrStatCd.equals(distributorReceiptInspVO.getInoutPrgrStatCd())){
				
				distributorReceiptInspVO.setInoutPrgrStatCd(inoutPrgrStatCd);
				distributorReceiptInspVO.setInoutClCd("1");
				
                distributorReceiptInspMapper.updateInout(distributorReceiptInspVO);
                
                //distributorReceiptInspMapper.insertInoutStatProcHist(distributorReceiptInspVO);
                
			}
			
		}
		
		
		if(inoutChk.equals("N")){
			
			List<DistributorReceiptInspVO> paramVO = distributorReceiptInspVO.getVoList();
			for(DistributorReceiptInspVO data : paramVO){
				data.setInoutId(inoutId);
				
				data.setRegrId(session.getUserId());
				data.setChgrId(session.getUserId());
				data.setRegDate(DateUtil.sysdate());
				data.setChgDate(DateUtil.sysdate());
				
			}
			
			distributorReceiptInspMapper.insertInoutEqt(paramVO);
			
		}else{
			
			List<DistributorReceiptInspVO> paramVO = distributorReceiptInspVO.getVoList();
			for(DistributorReceiptInspVO data : paramVO){
				data.setRegrId(session.getUserId());
				data.setChgrId(session.getUserId());
				data.setRegDate(DateUtil.sysdate());
				data.setChgDate(DateUtil.sysdate());
				distributorReceiptInspMapper.updateInoutEqt(data);
			}
			
		}
		
		return count;
	}

	@Override
	public int insertInConf(DistributorReceiptInspVO distributorReceiptInspVO,
			SessionUser session) {
		int count = 1;
		
		distributorReceiptInspVO.setRegrId(session.getUserId());
		distributorReceiptInspVO.setChgrId(session.getUserId());
		distributorReceiptInspVO.setRegDate(DateUtil.sysdate());
		distributorReceiptInspVO.setChgDate(DateUtil.sysdate());
		distributorReceiptInspVO.setStatProcId(session.getUserId());
		distributorReceiptInspVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
        distributorReceiptInspVO.setInoutPrgrStatCd("20");	// 상태: 입고확정
        distributorReceiptInspVO.setInoutClCd("1");			// 입출고구분; 입고
        	
        distributorReceiptInspMapper.updateInout(distributorReceiptInspVO);
        
        String ownLocCd = distributorReceiptInspVO.getInoutOrgId(); // 소유위치코드
        String bfrLocCd = distributorReceiptInspVO.getOwnOrgId(); // 이전소유위치코드
        
        distributorReceiptInspMapper.insertInoutStatProcHist(distributorReceiptInspVO);
        
        String inoutId = distributorReceiptInspVO.getInoutId();
        
        distributorReceiptInspVO.setInoutId(distributorReceiptInspVO.getRelInoutId());
        distributorReceiptInspMapper.deleteProcIdlDtl(distributorReceiptInspVO);
        
        String itemTpCd = distributorReceiptInspVO.getItemTpCd(); // 제품구분
        String inoutResnCd = distributorReceiptInspVO.getInoutResnCd(); // 입출고사유
        
        
        List<DistributorReceiptInspVO> voList = distributorReceiptInspVO.getVoList();
        List<DistributorReceiptInspVO> paramVOs = new ArrayList<DistributorReceiptInspVO>();
        List<String> histSeqList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, voList.size());
        
        for(int i=0; i<voList.size(); i++){
        	
        	DistributorReceiptInspVO setVO = voList.get(i);
        	
        	setVO.setRegrId(session.getUserId());
        	setVO.setChgrId(session.getUserId());
        	setVO.setRegDate(DateUtil.sysdate());
        	setVO.setChgDate(DateUtil.sysdate());
    		
        	setVO.setHistSeq(histSeqList.get(i));
        	setVO.setInoutId(inoutId);
        	setVO.setUpdProcClCd("40");	// 소유위치 변경 이력
        	setVO.setUpdBfrCd(setVO.getOwnLocCd());
        	setVO.setAftrUpdCd(ownLocCd);
        	setVO.setOwnLocCd(ownLocCd);
        	setVO.setLgstProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
        	setVO.setAgcInDt(DateUtil.getDateStringYYYYMMDD(0));
        	
        	setVO.setUpdProcId(session.getUserId());
        	setVO.setUpdProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
    		
        	
        	if (inoutResnCd.equals("60")) { // 착하반품거절
        		setVO.setLgstProcStatCd("601");
        		setVO.setEqtStatCd("001");
        		setVO.setUsimStatCd("101");
        		
        	}else{
        		setVO.setLgstProcStatCd("401");
        		
        		setVO.setUsimStatCd(setVO.getEqtStatCd());
        	}
            
        	
        	paramVOs.add(setVO);
        }
        
        DistributorReceiptInspVO pramVO = paramVOs.get(0);
        pramVO.setVoList(paramVOs);
        
        
        if (itemTpCd.equals("C")) {
        	
        	distributorReceiptInspMapper.updateEqt(pramVO);
        	
        } else if (itemTpCd.equals("U")) {
        	
        	distributorReceiptInspMapper.updateUsim(pramVO);
            
        } else if (itemTpCd.equals("V")) {
        	
        	distributorReceiptInspMapper.updateVeqt(pramVO);
        	
        	List<DistributorReceiptInspVO> veqtList = distributorReceiptInspMapper.selectVeqtList(pramVO);
			
			List<String> crtSeqNoList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_CRT_SEQ_NO, "1", 10, veqtList.size());
			
			for(int i=0; i<veqtList.size(); i++){
				veqtList.get(i).setCrtSeqNo(crtSeqNoList.get(i));
				
				veqtList.get(i).setRegrId(session.getUserId());
				veqtList.get(i).setChgrId(session.getUserId());
				veqtList.get(i).setRegDate(DateUtil.sysdate());
				veqtList.get(i).setChgDate(DateUtil.sysdate());
			}
			
			distributorReceiptInspMapper.insertVeqtTrans(veqtList);
			
        }
        
        distributorReceiptInspMapper.insertUpdProcHist(paramVOs);
        
        
        List<DistributorReceiptInspVO> paramVOs2 = new ArrayList<DistributorReceiptInspVO>();
        List<String> histSeqList2 = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, voList.size());
        for(int i=0; i<paramVOs.size(); i++){
        	
        	
        	paramVOs.get(i).setHistSeq(histSeqList2.get(i));
        	paramVOs.get(i).setUpdProcClCd("30");	// 물류처리 상태 변경 이력
        	
        	if (inoutResnCd.equals("60")) { // 착하반품거절
        		paramVOs.get(i).setAftrUpdCd("601");
        	}else{
        		paramVOs.get(i).setAftrUpdCd("401");
        	}
            
        	
        	paramVOs2.add(paramVOs.get(i));
        }

        distributorReceiptInspMapper.insertUpdProcHist(paramVOs2);
        
        
        if (inoutResnCd.equals("60")) {
        	
        	List<DistributorReceiptInspVO> paramVOs3 = new ArrayList<DistributorReceiptInspVO>();
            List<String> histSeqList3 = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, voList.size());
            
            for(int i=0; i<paramVOs2.size(); i++){
            	paramVOs2.get(i).setHistSeq(histSeqList3.get(i));
            	paramVOs2.get(i).setUpdProcClCd("20");	// 제품상태 변경 이력
            	if (itemTpCd.equals("C")) {
            		
            		paramVOs2.get(i).setUpdBfrCd(paramVOs2.get(i).getEqtStatCd());
            		paramVOs2.get(i).setAftrUpdCd("001");
            		
            	}else{
            		
            		paramVOs2.get(i).setUpdBfrCd(paramVOs2.get(i).getUsimStatCd());
            		paramVOs2.get(i).setAftrUpdCd("001");
            		
            	}
            	
            	paramVOs3.add(paramVOs2.get(i));
            }
            
            distributorReceiptInspMapper.insertUpdProcHist(paramVOs3);
            
        }
        
		return count;
	}

	@Override
	public int insertInRefuse(
			DistributorReceiptInspVO distributorReceiptInspVO,
			SessionUser session) {
		
        
		int count = 0;
		String inoutChk = "Y";
		String inoutId = "";
		String itemTpCd = distributorReceiptInspVO.getItemTpCd();
		
		distributorReceiptInspVO.setRegrId(session.getUserId());
		distributorReceiptInspVO.setChgrId(session.getUserId());
		distributorReceiptInspVO.setRegDate(DateUtil.sysdate());
		distributorReceiptInspVO.setChgDate(DateUtil.sysdate());
		distributorReceiptInspVO.setStatProcId(session.getUserId());
		distributorReceiptInspVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		distributorReceiptInspVO.setInoutPrgrStatCd("20");
		distributorReceiptInspVO.setInoutResnCd("50");
		distributorReceiptInspVO.setInoutClCd("1");
		
		if(distributorReceiptInspVO.getInoutId() == null || distributorReceiptInspVO.getInoutId().equals("")){
			
			inoutChk = "N";
			inoutId= sequenceService.createNewSequence(Consts.SEQ_CODE.DT_INOUT_ID, "IO", 10);
			
			distributorReceiptInspVO.setInoutId(inoutId);
			
			count = distributorReceiptInspMapper.insertInout(distributorReceiptInspVO);
			
			distributorReceiptInspMapper.insertInoutStatProcHist(distributorReceiptInspVO);
			
			
			int inoutQty = Integer.parseInt(distributorReceiptInspVO.getInoutQty());
			int inoutUtPrc = Integer.parseInt(distributorReceiptInspVO.getInoutUtPrc());
			
			int tx = 0;
			int inoutAddTx = inoutQty * (tx*inoutUtPrc); 		//부가세
			int inoutAmt = inoutQty * inoutUtPrc;
			int inoutTotAmt = inoutAddTx + inoutAmt;
			
			distributorReceiptInspVO.setInoutAddTx(String.valueOf(inoutAddTx));
			distributorReceiptInspVO.setInoutAmt(String.valueOf(inoutAmt));
			distributorReceiptInspVO.setInoutTotAmt(String.valueOf(inoutTotAmt));
			
			distributorReceiptInspMapper.insertInoutDtl(distributorReceiptInspVO);
			
		}else{
			
			inoutId = distributorReceiptInspVO.getInoutId();
			count = distributorReceiptInspMapper.updateInout(distributorReceiptInspVO);
	        
	        //distributorReceiptInspMapper.insertInoutStatProcHist(distributorReceiptInspVO);
			
		}
        
        distributorReceiptInspVO.setInoutId(distributorReceiptInspVO.getRelInoutId());
        distributorReceiptInspVO.setInoutClCd("1");
        
        distributorReceiptInspMapper.deleteProcIdlDtl(distributorReceiptInspVO);
        
        
        String inoutId2 = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_INOUT_ID, "IO", 10);
		
		distributorReceiptInspVO.setInoutId(inoutId2);
		distributorReceiptInspVO.setInoutClCd("2");
		distributorReceiptInspVO.setRelInoutId(inoutId);
		
		
		count = distributorReceiptInspMapper.insertInout(distributorReceiptInspVO);
		
		distributorReceiptInspMapper.insertInoutStatProcHist(distributorReceiptInspVO);
		
		
		int inoutQty = Integer.parseInt(distributorReceiptInspVO.getInoutQty());
		int inoutUtPrc = Integer.parseInt(distributorReceiptInspVO.getInoutUtPrc());
		
		int tx = 0;
		int inoutAddTx = inoutQty * (tx*inoutUtPrc); 		//부가세
		int inoutAmt = inoutQty * inoutUtPrc;
		int inoutTotAmt = inoutAddTx + inoutAmt;
		
		distributorReceiptInspVO.setInoutAddTx(String.valueOf(inoutAddTx));
		distributorReceiptInspVO.setInoutAmt(String.valueOf(inoutAmt));
		distributorReceiptInspVO.setInoutTotAmt(String.valueOf(inoutTotAmt));
		
		distributorReceiptInspMapper.insertInoutDtl(distributorReceiptInspVO);
		
		
		distributorReceiptInspVO.setIdlStatCd("10");
		
		distributorReceiptInspMapper.insertProcIdlDtl(distributorReceiptInspVO);
		
		
		List<DistributorReceiptInspVO> voList = distributorReceiptInspVO.getVoList();
		List<String> histSeqList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, voList.size());
		int histCount = 0;
		
		for(DistributorReceiptInspVO paramVO : voList){
			
			paramVO.setRegrId(session.getUserId());
			paramVO.setChgrId(session.getUserId());
			paramVO.setRegDate(DateUtil.sysdate());
			paramVO.setChgDate(DateUtil.sysdate());
			paramVO.setHistSeq(histSeqList.get(histCount++));
			paramVO.setUpdProcId(session.getUserId());
			paramVO.setUpdProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			
			if (inoutChk.equals("Y")) {
				
				paramVO.setInoutId(inoutId);
				//distributorReceiptInspMapper.insertInoutEqt2(paramVO);
				
	        }
			
			paramVO.setInoutId(inoutId2);
			distributorReceiptInspMapper.insertInoutEqt2(paramVO);
			
			
			paramVO.setLgstProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			paramVO.setLgstProcStatCd("502");
			
			DistributorReceiptInspVO data = new DistributorReceiptInspVO();
			
			if (itemTpCd.equals("C")) {
				
				data = distributorReceiptInspMapper.selectEqt(paramVO);
				paramVO.setOwnLocCd(data.getOwnLocCd());
				
				distributorReceiptInspMapper.updateEqt2(paramVO);
                
            } else if (itemTpCd.equals("U")) {
            	
            	data = distributorReceiptInspMapper.selectUsim(paramVO);
            	paramVO.setOwnLocCd(data.getOwnLocCd());
            	
            	distributorReceiptInspMapper.updateUsim2(paramVO);
            	
            } else if (itemTpCd.equals("V")) {
            	
            	paramVO.setVoSeqNo(paramVO.getEqtSeq());  	// 바우쳐일련번호
            	
            	data = distributorReceiptInspMapper.selectVeqt(paramVO);
            	
            	paramVO.setVoStatCd("40");	// 상태 : 비가용
            	paramVO.setOwnLocCd(data.getOwnLocCd());
            	
            	distributorReceiptInspMapper.updateVeqt2(paramVO);
            	
            }
			
			paramVO.setUpdProcClCd("30");
			paramVO.setUpdBfrCd(data.getLgstProcStatCd());
			paramVO.setAftrUpdCd("502");
			
			
			distributorReceiptInspMapper.insertUpdProcHist2(paramVO);
			
		}
		
		return count;
	}
	
	
	
	
	
}
