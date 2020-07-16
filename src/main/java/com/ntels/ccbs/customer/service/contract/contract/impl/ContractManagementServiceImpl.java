package com.ntels.ccbs.customer.service.contract.contract.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.customer.domain.contract.contract.CtrtInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.CustInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderMastInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptProcInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptTransInfoVO;
import com.ntels.ccbs.customer.mapper.contract.contract.ContractManagementMapper;
import com.ntels.ccbs.customer.service.contract.contract.ContractManagementService;
import com.ntels.ccbs.system.service.common.service.SequenceService;


@Service
public class ContractManagementServiceImpl implements ContractManagementService{
	
	@Autowired
	private ContractManagementMapper contractManagementMapper;
	
	@Autowired
	private SequenceService sequenceService;


	@Override
	public List<Map<String, Object>> getCustInfoForSearching(
	        List<Map<String, Object>> soAuthList, String condCustSoId,
	        String condCustNm, String condCustId, String condSearchTp,
	        String condSearchKey) {
		
		List<Map<String,Object>> custList = new ArrayList<Map<String,Object>>();
		/*
		 * 검색 조건 계약정보 포함 계약ID(B), 서비스번호(C) 
		 */
		if("B".equals(condSearchTp) || "C".equals(condSearchTp)){
			custList = contractManagementMapper.getCustInfoWithCtrtInfo(soAuthList, condCustSoId, condCustNm, condCustId, condSearchTp, condSearchKey);
		}else{
			//고객정보만 검색
			custList = contractManagementMapper.getCustInfoOnlyCustInfo(soAuthList, condCustSoId, condCustNm, condCustId, condSearchTp, condSearchKey);
		}
		return custList;
	}

	@Override
	public CustInfoVO getCustInfo(String soId, String custId, String lng,
	        String today) {
		return contractManagementMapper.getCustInfo(soId, custId, lng, today);
	}

	@Override
	public List<CtrtInfoVO> getCtrtInfoList(String soId, String custId,
	        String isIncludeTermYn, String lng, String today, String sidx,
	        String sord) {
		List<CtrtInfoVO> ctrtInfoList = contractManagementMapper.getCtrtInfoList(soId, custId, isIncludeTermYn, lng, today, sidx, sord);
		return ctrtInfoList;
	}

	@Override
	public RcptInfoVO saveRcptInfo(RcptInfoVO rcptInfo, String lng, String today) {
		
		String rcptId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_RCPT_ID, "R", 10);
		rcptInfo.setRcptId(rcptId);
		
		int saveCnt = contractManagementMapper.saveRcptInfo(rcptInfo);
		
		RcptProcInfoVO rcptProcInfo = new RcptProcInfoVO();
		rcptProcInfo.setRcptId(rcptId);
		rcptProcInfo.setProcDate(rcptInfo.getRegDate());
		rcptProcInfo.setProcSeq(1);
		rcptProcInfo.setProcSoId(rcptInfo.getSoId());
		rcptProcInfo.setProcOrgId(rcptInfo.getProcOrgId());
		rcptProcInfo.setProcUsrId(rcptInfo.getProcUsrId());
		rcptProcInfo.setProcDesc(rcptInfo.getProcDesc());
		rcptProcInfo.setCnslStat(rcptInfo.getCnslStat());
		rcptProcInfo.setRegrId(rcptInfo.getRegrId());
		rcptProcInfo.setRegDate(rcptInfo.getRegDate());
		rcptProcInfo.setChgrId(rcptInfo.getChgrId());
		rcptProcInfo.setChgDate(rcptInfo.getRegDate());
		
		int saveRcptProcCnt = contractManagementMapper.saveRcptProcInfo(rcptProcInfo);
		RcptInfoVO resRcptInfo = contractManagementMapper.getRcptInfo(rcptId, lng, today);
		
		if(!"1".equals(resRcptInfo.getCnslStat())){
			List<RcptTransInfoVO> resRcptTran = contractManagementMapper.getRcptTranInfoList(rcptId, lng, today);
			if(resRcptTran.size() > 0){
				RcptTransInfoVO rcptTran = resRcptTran.get(0);
				resRcptInfo.setTranStat(rcptTran.getTransStat());
				resRcptInfo.setTranStatNm(rcptTran.getTransStatNm());
			}else{
				resRcptInfo.setTranStat("");
				resRcptInfo.setTranStatNm("");
			}
		}else{
			resRcptInfo.setTranStat("");
			resRcptInfo.setTranStatNm("");
		}
		
		return resRcptInfo;
	}

	@Override
	public RcptInfoVO updateRcptInfo(RcptInfoVO rcptInfo, String lng,
	        String today) {
		RcptInfoVO preRcptInfo = contractManagementMapper.getRcptInfo(rcptInfo.getRcptId(), lng, today);
		if(!preRcptInfo.getProcUsrId().equals(rcptInfo.getChgrId())){
			throw new ServiceException( "MSG.M07.MSG00060" );
		}
		if("4".equals(preRcptInfo.getCnslStat())){
			throw new ServiceException( "MSG.M07.MSG00060" );
		}
		
		
		int saveCnt = contractManagementMapper.updateRcptInfo(rcptInfo);
		
		RcptProcInfoVO rcptProcInfo = new RcptProcInfoVO();
		rcptProcInfo.setRcptId(rcptInfo.getRcptId());
		rcptProcInfo.setProcDate(rcptInfo.getChgDate());
		rcptProcInfo.setProcSeq(getMaxSeqRcptProc(rcptInfo.getRcptId()) + 1);
		rcptProcInfo.setProcSoId(rcptInfo.getSoId());
		rcptProcInfo.setProcOrgId(rcptInfo.getProcOrgId());
		rcptProcInfo.setProcUsrId(rcptInfo.getProcUsrId());
		rcptProcInfo.setProcDesc(rcptInfo.getProcDesc());
		rcptProcInfo.setCnslStat(rcptInfo.getCnslStat());
		rcptProcInfo.setRegrId(rcptInfo.getChgrId());
		rcptProcInfo.setRegDate(rcptInfo.getChgDate());
		rcptProcInfo.setChgrId(rcptInfo.getChgrId());
		rcptProcInfo.setChgDate(rcptInfo.getChgDate());
		
		int saveRcptProcCnt = contractManagementMapper.saveRcptProcInfo(rcptProcInfo);
		RcptInfoVO resRcptInfo = contractManagementMapper.getRcptInfo(rcptInfo.getRcptId(), lng, today);
		
		if(!"1".equals(resRcptInfo.getCnslStat())){
			List<RcptTransInfoVO> resRcptTran = contractManagementMapper.getRcptTranInfoList(rcptInfo.getRcptId(), lng, today);
			if(resRcptTran.size() > 0){
				RcptTransInfoVO rcptTran = resRcptTran.get(0);
				resRcptInfo.setTranStat(rcptTran.getTransStat());
				resRcptInfo.setTranStatNm(rcptTran.getTransStatNm());
			}else{
				resRcptInfo.setTranStat("");
				resRcptInfo.setTranStatNm("");
			}
		}else{
			resRcptInfo.setTranStat("");
			resRcptInfo.setTranStatNm("");
		}
		
		return resRcptInfo;
	}
	
	@Override
	public RcptInfoVO updateRcptInfoCmpl(RcptInfoVO rcptInfo, String lng,
	        String today) {
		RcptInfoVO preRcptInfo = contractManagementMapper.getRcptInfo(rcptInfo.getRcptId(), lng, today);
		if("4".equals(preRcptInfo.getCnslStat())){
			throw new ServiceException( "MSG.M07.MSG00060" );
		}
		
		int saveCnt = contractManagementMapper.updateRcptInfoCmpl(rcptInfo);
				
		RcptProcInfoVO rcptProcInfo = new RcptProcInfoVO();
		rcptProcInfo.setRcptId(rcptInfo.getRcptId());
		rcptProcInfo.setProcDate(rcptInfo.getChgDate());
		rcptProcInfo.setProcSeq(getMaxSeqRcptProc(rcptInfo.getRcptId()) + 1);
		rcptProcInfo.setProcSoId(rcptInfo.getSoId());
		rcptProcInfo.setProcOrgId(rcptInfo.getCmplOrgId());
		rcptProcInfo.setProcUsrId(rcptInfo.getCmplUsrId());
		rcptProcInfo.setProcDesc(rcptInfo.getProcDesc());
		rcptProcInfo.setCnslStat(rcptInfo.getCnslStat());
		rcptProcInfo.setRegrId(rcptInfo.getChgrId());
		rcptProcInfo.setRegDate(rcptInfo.getChgDate());
		rcptProcInfo.setChgrId(rcptInfo.getChgrId());
		rcptProcInfo.setChgDate(rcptInfo.getChgDate());
		
		int saveRcptProcCnt = contractManagementMapper.saveRcptProcInfo(rcptProcInfo);
		
		RcptInfoVO resRcptInfo = contractManagementMapper.getRcptInfo(rcptInfo.getRcptId(), lng, today);
		if(!"1".equals(resRcptInfo.getCnslStat())){
			List<RcptTransInfoVO> resRcptTran = contractManagementMapper.getRcptTranInfoList(rcptInfo.getRcptId(), lng, today);
			if(resRcptTran.size() > 0){
				RcptTransInfoVO rcptTran = resRcptTran.get(0);
				resRcptInfo.setTranStat(rcptTran.getTransStat());
				resRcptInfo.setTranStatNm(rcptTran.getTransStatNm());
			}else{
				resRcptInfo.setTranStat("");
				resRcptInfo.setTranStatNm("");
			}
		}else{
			resRcptInfo.setTranStat("");
			resRcptInfo.setTranStatNm("");
		}
		
		return resRcptInfo;
	}
	
	

	@Override
	public RcptInfoVO getRcptInfo(String orderId, String lng, String today) {
		return contractManagementMapper.getRcptInfo(orderId, lng, today);
	}

	@Override
	public CtrtInfoVO getCtrtInfo(String soId, String custId, String ctrtId,
	        String lng, String today) {
		return contractManagementMapper.getCtrtInfo(soId, custId, ctrtId, lng, today);
	}

	@Override
	public List<OrderInfoVO> getOrderInfoList(String soId, String custId, String ctrtId, String lng, String today) {
		List<OrderInfoVO> orderInfoList = contractManagementMapper.getOrderInfoList(soId, custId, ctrtId, lng, today);
		return orderInfoList;
	}
	
	private int getMaxSeqRcptTrans(String rcptId){
		return contractManagementMapper.getMaxSeqRcptTrans(rcptId);
	}
	
	private int getMaxSeqRcptProc(String rcptId){
		return contractManagementMapper.getMaxSeqRcptProc(rcptId);
	}

	@Override
	public List<OrderMastInfoVO> getOrderListBySoId(String soId, String custTp,  String lng) {
		return contractManagementMapper.getOrderListBySoId(soId, custTp, lng);
	}
	
	


}
