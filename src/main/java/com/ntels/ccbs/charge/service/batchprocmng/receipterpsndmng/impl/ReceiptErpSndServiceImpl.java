package com.ntels.ccbs.charge.service.batchprocmng.receipterpsndmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng.ErpZmis11VO;
import com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng.ErpZmis14VO;
import com.ntels.ccbs.charge.domain.batchprocmng.receipterpsndmng.ReceiptErpIntrVO;
import com.ntels.ccbs.charge.mapper.batchprocmng.receipterpsndmng.ReceiptErpSndMapper;
import com.ntels.ccbs.charge.service.batchprocmng.receipterpsndmng.ReceiptErpSndService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ReceiptErpSndServiceImpl implements ReceiptErpSndService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReceiptErpSndMapper receiptErpSndMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	/**
	 * 수납 관련 ERP 데이터를 전송한다.
	 * @param dataOccDt, pgmId, workId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int processReceiptErpSndData(String dataOccDt, String pgmId, String workId) throws Exception {
		int resultFlag = 1;
		
		// 수납 관련 ERP 자료를 조회한다.
		logger.debug("====================================");
		logger.debug("  getErpZmis11List Process...  ");
		logger.debug("====================================");
		
		List<ErpZmis11VO> erp11ListVO = new ArrayList<ErpZmis11VO>();
		
		erp11ListVO = receiptErpSndMapper.getErpZmis11List(dataOccDt);
				
		int loopCnt1 = erp11ListVO.size();
		
		for(int i=0; i<loopCnt1; i++) {
			ErpZmis11VO erp11InfoVO = new ErpZmis11VO();
			
			erp11InfoVO = erp11ListVO.get(i);
			String interIdex1 = sequenceService.createNewSequenceDaily(Consts.SEQ_CODE.BL_RERP_NO, Consts.SEQ_DATE_FORMAT.FORMAT_YYYYMMDD, 12);
			erp11InfoVO.setInterIdex(interIdex1);
			
			// 수납 관련 ERP 자료를 등록한다.
			logger.debug("====================================");
			logger.debug("  insertErpZmis11 Process...  ");
			logger.debug("====================================");
			
			int insCnt1 = receiptErpSndMapper.insertErpZmis11(erp11InfoVO);
			if (insCnt1 <= 0) {
				logger.debug("FAIL INSERT ERP_ZMIS11");
				logger.debug("수납 관련 ERP 자료 등록 실패");
				throw new Exception("FAIL INSERT ERP_ZMIS11");
			}
		}
		
		// 대체 관련 ERP 자료를 조회한다.
		logger.debug("====================================");
		logger.debug("  getErpZmis14List Process...  ");
		logger.debug("====================================");
				
		List<ErpZmis14VO> erp14ListVO = new ArrayList<ErpZmis14VO>();
				
		erp14ListVO = receiptErpSndMapper.getErpZmis14List(dataOccDt);
						
		int loopCnt2 = erp14ListVO.size();
				
		for(int i=0; i<loopCnt2; i++) {
			ErpZmis14VO erp14InfoVO = new ErpZmis14VO();
					
			erp14InfoVO = erp14ListVO.get(i);
			String interIdex2 = sequenceService.createNewSequenceDaily(Consts.SEQ_CODE.BL_RERP_NO, Consts.SEQ_DATE_FORMAT.FORMAT_YYYYMMDD, 20);
			erp14InfoVO.setInterIdex(interIdex2);
					
			// 대체 관련 ERP 자료를 등록한다.
			logger.debug("====================================");
			logger.debug("  insertErpZmis14 Process...  ");
			logger.debug("====================================");
					
			int insCnt2 = receiptErpSndMapper.insertErpZmis14(erp14InfoVO);
			if (insCnt2 <= 0) {
				logger.debug("FAIL INSERT ERP_ZMIS14");
				logger.debug("대체 관련 ERP 자료 등록 실패");
				throw new Exception("FAIL INSERT ERP_ZMIS14");
			}
		}
			
		// ERP 전송여부, 전송일자 UPDATE
		logger.debug("====================================");
		logger.debug("  updateErpSendInfo Process...  ");
		logger.debug("====================================");
		
		ReceiptErpIntrVO receiptErpIntrVO = new ReceiptErpIntrVO();
		
		receiptErpIntrVO.setDataOccDt(dataOccDt);
		receiptErpIntrVO.setErpSndYn("Y");
		receiptErpIntrVO.setErpSndDt(DateUtil.getDateStringYYYYMMDD(0));
		receiptErpIntrVO.setChgDate(DateUtil.sysdate());
		
		int updCnt = receiptErpSndMapper.updateErpSysSndYn(receiptErpIntrVO);
			                                                                          
		if (updCnt <= 0) {
			logger.debug("FAIL UPDATE ERP SEND INFO");
			logger.debug("ERP 전송여부, 전송일자 변경 실패");
			throw new Exception("FAIL UPDATE ERP SEND INFO");
		}

		return resultFlag;
	}
	
}
