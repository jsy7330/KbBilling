package com.ntels.ccbs.system.service.configuration.approvalMng.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.configuration.approvalMng.ApprovalMng;
import com.ntels.ccbs.system.mapper.configuration.approvalMng.ApprovalMngMapper;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.ccbs.system.service.configuration.approvalMng.ApprovalMngService;

@Service
public class ApprovalMngServiceImpl implements ApprovalMngService {

	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApprovalMngMapper approvalMngMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public int getApprovalMngCnt(ApprovalMng approvalMng) {
		return approvalMngMapper.getApprovalMngCnt(approvalMng);
	}
	
	@Override
	public List<ApprovalMng> getApprovalMngList(ApprovalMng approvalMng, int page, int perPage) {		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return approvalMngMapper.getApprovalMngList(approvalMng, start,  end);
	}
	
	@Override
	public int getAddApprovalCnt(ApprovalMng approvalMng) {
		return approvalMngMapper.getAddApprovalCnt(approvalMng);
	}
	
	@Override
	public List<ApprovalMng> getAddApprovalList(ApprovalMng approvalMng, int page, int perPage) {		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return approvalMngMapper.getAddApprovalList(approvalMng, start,  end);
	}	
	
	@Override
	public int getApprovalCnt(ApprovalMng approvalMng) {
		return approvalMngMapper.getApprovalCnt(approvalMng);
	}
	
	@Override
	public List<ApprovalMng> getApprovalList(ApprovalMng approvalMng, int page, int perPage) {		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return approvalMngMapper.getApprovalList(approvalMng, start,  end);
	}

	/**
	 * 결재대상자 정보 저장
	 */
	@Override
	public int saveAprvDtlList(List<ApprovalMng> approvalMngs) {
		
		int result = 0;
		
		//새로 등록 시퀀스 생성
		if(approvalMngs.get(0).getAprvId() == null || approvalMngs.get(0).getAprvId().equals("") ){
			
			String aprvId = sequenceService.createNewSequence(Consts.SEQ_CODE.SEQ_TSYCO_APRVMNG_MAST, 10);
			//aprvId 값 등록
			for(ApprovalMng approvalMng : approvalMngs){
				approvalMng.setAprvId(aprvId);
			}

		}else{	//수정 - 테이블 데이터 삭제 후 새로 인서트
			
			//aprvId 로 데이터 전체 삭제
			approvalMngMapper.deleteAprvmngMast(approvalMngs.get(0));
			
		}
		
		Date regDate = DateUtil.sysdate();
		
		for(ApprovalMng approvalMng : approvalMngs){
			
			approvalMng.setRegDate(regDate);
			result += approvalMngMapper.insertAprvmngMast(approvalMng);
		}
		
		return result;
	}

	/**
	 * 결재상신 저장
	 */
	@Override
	public int saveAprvReport(ApprovalMng approvalMng) {

		int result = 0;
		int checkCount = approvalMngMapper.getAprvMastCount(approvalMng);
		
		
		if(checkCount < 1){
			//전체 결제자 데이터 가지고 오기
			List<ApprovalMng> approvalMngList = approvalMngMapper.getApprovalList(approvalMng, 0,  999);
			
			Date regDate = DateUtil.sysdate();
			
			for(ApprovalMng paramData : approvalMngList){
				
				String aprvMastId = sequenceService.createNewSequence(Consts.SEQ_CODE.SEQ_TSYCO_APRV_MAST, 10);
				
				paramData.setAprvMastId(aprvMastId);
				paramData.setAprvStat("01");		//승인요청:01, 승인:02,반려:03, 결재취소:04
				paramData.setRegrId(approvalMng.getRegrId());
				paramData.setRegDate(regDate);
				
				result += approvalMngMapper.insertAprvMast(paramData);
				
				paramData.setRegDate(DateUtil.sysdate());	//히스토리 데이블 저장시 등록일시 현재로
				approvalMngMapper.insertAprvHist(paramData);
				
			}
			
			
		}else{
			result = -1;
		}
		
		return result;
	}

	/**
	 * 결재승인 리스트
	 */
	@Override
	public List<ApprovalMng> getApprovalOkList(ApprovalMng approvalMng,
			int page, int perPage) {
/*
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
*/
		return approvalMngMapper.getApprovalOkList(approvalMng, 0,  999);
	}

	@Override
	public int saveApproval(ApprovalMng approvalMng) {
		
		int result = 0;
		
		if(approvalMng.getAprvStat().equals("02")){		//승인일 경우
			
			result += approvalMngMapper.updateAprvMast(approvalMng);
			
			approvalMng.setRegDate(DateUtil.sysdate());	//히스토리 데이블 저장시 등록일시 현재로
			approvalMngMapper.insertAprvHist(approvalMng);
			
		}else if(approvalMng.getAprvStat().equals("03")){		//반려일 경우
			
			if(approvalMng.getAprvStep().equals("1")){	//첫번째 대상자가 반려 했을경우 결제 내용 전부 상태갑  취소로 수정
				
				//aprvId 값으로 전부 취소
				approvalMng.setAprvMastId("");
				approvalMng.setAprvStep("");
				approvalMng.setAprvStat("04");		//취소값
				result += approvalMngMapper.updateAprvMast(approvalMng);
				
				//취소내역 전부 히스토리 등록
				List<ApprovalMng> approvalList = approvalMngMapper.getApprovalOkList(approvalMng, 0,  999);
				for(ApprovalMng data : approvalList){
					
					data.setRegDate(DateUtil.sysdate());	//히스토리 데이블 저장시 등록일시 현재로
					data.setAprvStat("04");
					data.setRegrId(approvalMng.getRegrId());
					
					approvalMngMapper.insertAprvHist(data);
					
				}
				
			}else{	//첫번재 대상자가 아닐경우 상태값 업데이트 후 이전 결제자 결제요청 중으로 수정
				
				result += approvalMngMapper.updateAprvMast(approvalMng);
				
				approvalMng.setRegDate(DateUtil.sysdate());	//히스토리 데이블 저장시 등록일시 현재로
				approvalMngMapper.insertAprvHist(approvalMng);
				
				
				//이전 결제 내용 수정
				int step = Integer.parseInt(approvalMng.getAprvStep()) - 1;
				approvalMng.setAprvStep(String.valueOf(step));
				approvalMng.setSearchAprvStep(String.valueOf(step));
				
				List<ApprovalMng> approvalList = approvalMngMapper.getApprovalOkList(approvalMng, 0,  999);
				approvalMng.setAprvMastId(approvalList.get(0).getAprvMastId());
				approvalMng.setAprvStat("01");
				
				result += approvalMngMapper.updateAprvMast(approvalMng);	//이전값 업데이트
				
				approvalMng.setRegDate(DateUtil.sysdate());	//히스토리 데이블 저장시 등록일시 현재로
				approvalMngMapper.insertAprvHist(approvalMng);
				
				
			}
			
			
		}
		
		
		return result;
	}

	@Override
	public List<ApprovalMng> getApprovalHistList(ApprovalMng approvalMng,
			int page, int perPage) {
		return approvalMngMapper.getApprovalHistList(approvalMng, 0,  999);
	}
	
	
	
}