package com.ntels.ccbs.distribute.service.logistics.logisticsCenterReceIssue.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.logisticsCenterReceIssue.LogisticsCenterIssueInspVO;
import com.ntels.ccbs.distribute.mapper.logistics.logisticsCenterReceIssue.LogisticsCenterIssueInspMapper;
import com.ntels.ccbs.distribute.service.logistics.logisticsCenterReceIssue.LogisticsCenterIssueInspService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class LogisticsCenterIssueInspServiceImpl implements LogisticsCenterIssueInspService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogisticsCenterIssueInspMapper logisticsCenterIssueInspMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<LogisticsCenterIssueInspVO> lgstCntrOrdList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO, int page,
			int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return logisticsCenterIssueInspMapper.lgstCntrOrdList(logisticsCenterIssueInspVO, start, end);
	}

	@Override
	public int lgstCntrOrdCount(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO) {
		return logisticsCenterIssueInspMapper.lgstCntrOrdCount(logisticsCenterIssueInspVO);
	}

	@Override
	public List<LogisticsCenterIssueInspVO> lgstCntrOutList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO) {
		return logisticsCenterIssueInspMapper.lgstCntrOutList(logisticsCenterIssueInspVO);
	}

	@Override
	public List<LogisticsCenterIssueInspVO> lgstCntrOutEqtList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO) {
		return logisticsCenterIssueInspMapper.lgstCntrOutEqtList(logisticsCenterIssueInspVO);
	}

	@Override
	public List<LogisticsCenterIssueInspVO> lgstCntrOutUsimList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO) {
		return logisticsCenterIssueInspMapper.lgstCntrOutUsimList(logisticsCenterIssueInspVO);
	}

	@Override
	public List<LogisticsCenterIssueInspVO> lgstCntrOutVeqtList(
			LogisticsCenterIssueInspVO logisticsCenterIssueInspVO) {
		return logisticsCenterIssueInspMapper.lgstCntrOutVeqtList(logisticsCenterIssueInspVO);
	}

	@Override
	public int insertInoutList(
			List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs,
			SessionUser session) {
		
		Date sysdata = DateUtil.sysdate();
		String inoutId = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_INOUT_ID, "IO", 10);
		
		for(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO : logisticsCenterIssueInspVOs){
			
			logisticsCenterIssueInspVO.setRegrId(session.getUserId());
			logisticsCenterIssueInspVO.setChgrId(session.getUserId());
			logisticsCenterIssueInspVO.setRegDate(sysdata);
			logisticsCenterIssueInspVO.setChgDate(sysdata);
			
			logisticsCenterIssueInspVO.setInoutId(inoutId);
		}
		
		logisticsCenterIssueInspVOs.get(0).setStatProcId(session.getUserId());
		logisticsCenterIssueInspVOs.get(0).setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
		
		int count = logisticsCenterIssueInspMapper.insertInout(logisticsCenterIssueInspVOs.get(0));
		
		int inoutQty = Integer.parseInt(logisticsCenterIssueInspVOs.get(0).getInoutQty() );
		int inoutUtPrc = Integer.parseInt(logisticsCenterIssueInspVOs.get(0).getInoutUtPrc() );
		int inoutAmt = inoutQty * inoutUtPrc;
		
		int tx = 0;	//부가세
		int inoutAddTx = inoutAmt * tx;
		int inoutTotAmt = inoutAmt + inoutAddTx;
		
		logisticsCenterIssueInspVOs.get(0).setInoutAmt(String.valueOf(inoutAmt));
		logisticsCenterIssueInspVOs.get(0).setInoutAddTx(String.valueOf(inoutAddTx));
		logisticsCenterIssueInspVOs.get(0).setInoutTotAmt(String.valueOf(inoutTotAmt));
		
		logisticsCenterIssueInspMapper.insertInoutDtl(logisticsCenterIssueInspVOs.get(0));
		
		logisticsCenterIssueInspMapper.insertInoutStatProcHist(logisticsCenterIssueInspVOs.get(0));
		
		logisticsCenterIssueInspMapper.insertInoutEqt(logisticsCenterIssueInspVOs);
		
		if(logisticsCenterIssueInspVOs.get(0).getItemTpCd().equals("C")){
			

		}else if(logisticsCenterIssueInspVOs.get(0).getItemTpCd().equals("U")){
			
			logisticsCenterIssueInspVOs.get(0).setEqtSeqList(logisticsCenterIssueInspVOs);
			logisticsCenterIssueInspMapper.updateUsim(logisticsCenterIssueInspVOs.get(0));
			
		}
		
		return count;
		
	}

	@Override
	public int deleteOut(List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs, SessionUser session) {

		int count = 0;
		
		for(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO : logisticsCenterIssueInspVOs){
			
			count = count + logisticsCenterIssueInspMapper.deleteInout(logisticsCenterIssueInspVO);
			
			logisticsCenterIssueInspMapper.deleteInoutDtl(logisticsCenterIssueInspVO);
			
			logisticsCenterIssueInspMapper.deleteInoutStatProcHist(logisticsCenterIssueInspVO);
			
			logisticsCenterIssueInspMapper.updateUsim2(logisticsCenterIssueInspVO);
			
			logisticsCenterIssueInspMapper.deleteInoutEqt(logisticsCenterIssueInspVO);
		}
		
		return count;
	}

	@Override
	public int insertOutConf(
			List<LogisticsCenterIssueInspVO> logisticsCenterIssueInspVOs,
			SessionUser session) {
		
		int count = 0;
		Date sysdata = DateUtil.sysdate();
		String checkYn = "Y";
		
		List<LogisticsCenterIssueInspVO> list = new ArrayList<LogisticsCenterIssueInspVO>();
		
		for(LogisticsCenterIssueInspVO logisticsCenterIssueInspVO : logisticsCenterIssueInspVOs){
			
			List<LogisticsCenterIssueInspVO> eqtUsimList = new ArrayList<LogisticsCenterIssueInspVO>();
			
			//단말기일 경우
			if(logisticsCenterIssueInspVO.getItemTpCd().equals("C")){
				
				List<LogisticsCenterIssueInspVO> listData = logisticsCenterIssueInspMapper.lgstCntrInOutEqtList(logisticsCenterIssueInspVO);
				
				
				for(int i=0; i<listData.size(); i++){
					
					if(listData.get(i).getEqtSeq().equals(listData.get(i).getEqtBarCd() )){
						
						listData.get(i).setInoutOrgId(logisticsCenterIssueInspVO.getInoutOrgId());
						
						List<LogisticsCenterIssueInspVO> eqtList = logisticsCenterIssueInspMapper.lgstCntrOutEqtCheckList(listData.get(i));
						String itemId2 = null;
						LogisticsCenterIssueInspVO param4 = new LogisticsCenterIssueInspVO();
						
						if(eqtList != null && eqtList.size() > 0){
							param4 = eqtList.get(0);
							itemId2 = param4.getItemId();
							listData.get(i).setEqtSeq(param4.getEqtSeq());
						}else{
							itemId2 = "ABC";
						}
						
						
						if (listData.get(i).getItemId().equals(itemId2)) {
                            // 제품일련번호 업데이트
							param4.setInoutId(logisticsCenterIssueInspVO.getInoutId());
							param4.setEqtBarCd(listData.get(i).getEqtBarCd());
							
							param4.setChgDate(sysdata);
							param4.setChgrId(session.getUserId());
							
							logisticsCenterIssueInspMapper.updateLgstCntrInOutEqtInfo(param4);

                        }else{
                        	
                        	
                        	if ("ABC".equals(itemId2)) {
                                //mapParam3.put("BIGO", "The product is not at the logistics center.");
                            } else {
                                //mapParam3.put("BIGO", "The barcode is different from the one to be issued.");
                            }
                        	
                        	list.add(listData.get(i));
                        	
                        	logisticsCenterIssueInspMapper.deleteLgstCntrInOutEqtInfo(listData.get(i));
                        	
                        }
						
					}
					
				}
				
				
			}		//단말기일 경우
			
			
			// 출고수량과 검수수량이 같으면
            if (logisticsCenterIssueInspMapper.countMinusInOutQty(logisticsCenterIssueInspVO) < 1) {
            	
            	eqtUsimList = logisticsCenterIssueInspMapper.lgstCntrInOutEqtList(logisticsCenterIssueInspVO);
            	
            	if(eqtUsimList != null && eqtUsimList.size() > 0){
            		
            		List<String> histSeqList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10, eqtUsimList.size());
            		int histCount = 0;
            		
            		for(LogisticsCenterIssueInspVO eqtUsim : eqtUsimList){
            			
            			eqtUsim.setHistSeq(histSeqList.get(histCount));
            			histCount++;
            			eqtUsim.setLgstProcStatCd("402");	//물류진행상태
            			eqtUsim.setLgstProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
            			eqtUsim.setUpdBfrCd("001");			//이전물류진행상태
            			eqtUsim.setAftrUpdCd("402");		//이후물류진행상태
            			eqtUsim.setInoutOrgId(logisticsCenterIssueInspVO.getInoutOrgId());
            			eqtUsim.setUpdProcClCd("30");		//변경처리구분
            			eqtUsim.setUpdProcId(session.getUserId());	//변경처리자ID
            			eqtUsim.setUpdProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
            			eqtUsim.setDstrbUtPrc(logisticsCenterIssueInspVO.getInoutUtPrc());
            			
            			eqtUsim.setUsimSeq(eqtUsim.getEqtSeq());
            			eqtUsim.setIdlStatCd("00");
            			
            			eqtUsim.setVoStatCd("30");
            			eqtUsim.setVoSeqNo(eqtUsim.getEqtSeq());
            			
            			eqtUsim.setRegrId(session.getUserId());
            			eqtUsim.setChgrId(session.getUserId());
            			eqtUsim.setRegDate(sysdata);
            			eqtUsim.setChgDate(sysdata);
            			
            		}
            		
            		//단말기일 경우
        			if(logisticsCenterIssueInspVO.getItemTpCd().equals("C")){
        				
        				eqtUsimList.get(0).setEqtSeqList(eqtUsimList);
        				logisticsCenterIssueInspMapper.updateEqtInfo(eqtUsimList.get(0));
        				
        				logisticsCenterIssueInspMapper.insertUpdProcHist(eqtUsimList);
        				
        			}else if(logisticsCenterIssueInspVO.getItemTpCd().equals("U")){
        				
        				eqtUsimList.get(0).setEqtSeqList(eqtUsimList);
        				logisticsCenterIssueInspMapper.updateUsim2(eqtUsimList.get(0));
        				
        				logisticsCenterIssueInspMapper.insertUpdProcHist(eqtUsimList);
        				
        			}else if(logisticsCenterIssueInspVO.getItemTpCd().equals("V")){
        				
        				eqtUsimList.get(0).setEqtSeqList(eqtUsimList);
        				logisticsCenterIssueInspMapper.updateVeqtInfoVoStatLgst(eqtUsimList.get(0));
        				
        				List<LogisticsCenterIssueInspVO> veqtList = logisticsCenterIssueInspMapper.selectVeqtList(eqtUsimList.get(0));
        				
        				List<String> crtSeqNoList = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_CRT_SEQ_NO, "1", 10, veqtList.size());
        				
        				for(int i=0; i<veqtList.size(); i++){
        					veqtList.get(i).setCrtSeqNo(crtSeqNoList.get(i));
        					
        					veqtList.get(i).setRegrId(session.getUserId());
        					veqtList.get(i).setChgrId(session.getUserId());
        					veqtList.get(i).setRegDate(sysdata);
        					veqtList.get(i).setChgDate(sysdata);
        				}
        				
        				logisticsCenterIssueInspMapper.insertVeqtTrans(veqtList);
        				
        			}
            		
        			if(checkYn.equals("Y")){
        				
        				logisticsCenterIssueInspVO.setRegrId(session.getUserId());
        				logisticsCenterIssueInspVO.setChgrId(session.getUserId());
        				logisticsCenterIssueInspVO.setRegDate(sysdata);
        				logisticsCenterIssueInspVO.setChgDate(sysdata);
        				logisticsCenterIssueInspVO.setInoutPrgrStatCd("20");	// 출고승인
        				logisticsCenterIssueInspVO.setStatProcId(session.getUserId());
        				logisticsCenterIssueInspVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
        				logisticsCenterIssueInspVO.setIdlStatCd("20");		//배송대기
        				
        				count = logisticsCenterIssueInspMapper.updateInOutInfo(logisticsCenterIssueInspVO);
        				
        				logisticsCenterIssueInspMapper.insertProcIdlDtl(logisticsCenterIssueInspVO);
        				
        				logisticsCenterIssueInspMapper.insertInoutStatProcHist(logisticsCenterIssueInspVO);
        				
        				// 주문처리대기상태정보를 삭제
        				if(logisticsCenterIssueInspMapper.countOrdMinusInOutQty(logisticsCenterIssueInspVO) < 1){
        					//generalDao.delete("dn.dt.delOrdIdlDtl", mapParam);
        					
        					logisticsCenterIssueInspMapper.deleteOrdIdlDtl(logisticsCenterIssueInspVO);
        				}
        				
        				
        			}else{
        				
        			}
        			
            	}
            	
            }else{	// 출고수량과 검수수량이 같으면X
            	
            	logisticsCenterIssueInspVO.setInoutPrgrStatCd("20");	// 출고승인
				logisticsCenterIssueInspVO.setStatProcId(session.getUserId());
				logisticsCenterIssueInspVO.setStatProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
				logisticsCenterIssueInspVO.setIdlStatCd("20");		//배송대기
				
            	count = logisticsCenterIssueInspMapper.updateInOutInfo(logisticsCenterIssueInspVO);
            	
            }
			
		}
		
		return count;
	}
	
	
}
