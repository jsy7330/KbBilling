package com.ntels.ccbs.customer.service.customer.customer.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.customer.domain.customer.customer.CustomerInfoVO;
import com.ntels.ccbs.customer.domain.customer.payment.PaymentAccountInfoVO;
import com.ntels.ccbs.customer.mapper.customer.customer.CustomerManagementMapper;
import com.ntels.ccbs.customer.service.customer.customer.CustomerManagementService;
import com.ntels.ccbs.customer.service.customer.payment.PaymentAccountManagementService;
import com.ntels.ccbs.system.service.common.service.SequenceService;




@Service
public class CustomerManagementServiceImpl implements CustomerManagementService{
	
	@Autowired
	private CustomerManagementMapper customerManagementMapper;
	
	@Autowired
	private PaymentAccountManagementService paymentAccountManagementService;

	@Autowired
	private SequenceService sequenceService;
	
	
	@Override
	public List<CustomerInfoVO> getCustInfoList(String soId,
			String custId,
	        String custName,
	        List<Map<String, Object>> soAuthList,
	        String today,
	        String lng) {
		return customerManagementMapper.getCustomerInfoList(soId, custId, custName, soAuthList, today, lng);
	}

	@Override
	public CustomerInfoVO insertNewCustomerInfo(CustomerInfoVO customerInfo, String today,
	        String lng) {
		//고객ID채번
		String custId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_CUST_ID, "C", 10);
		String soId = customerInfo.getSoId();
		customerInfo.setCustId(custId);
		//확장ID채번
		String extId = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_CUST_EXT_ID, 14);
		customerInfo.setExtId(extId);
		
		//법인, 공공기관의 경우 사업자 번호 중복 체크
		if("D".equals(customerInfo.getCustTp()) || "E".equals(customerInfo.getCustTp())){
			int custCnt = customerManagementMapper.checkBizRegNo(customerInfo.getSoId(), customerInfo.getBizRegNo());
		}
		
//		if("A".equals(customerInfo.getCustTp()) || "B".equals(customerInfo.getCustTp()) || "C".equals(customerInfo.getCustTp())){
//			customerInfo.setCorpRegNo(customerInfo.getCorpRegNo().replaceAll("[*]", "0"));
//		}
		customerInfo.setCorpRegNo(customerInfo.getCorpRegNo().replaceAll("[*]", "0"));
		
		//고객정보저장
		customerManagementMapper.insertCustInfo(customerInfo);
		//고객정보 히스토리 저장
		customerManagementMapper.insertCustHistInfo(customerInfo, today);
		//고객 담당자정보 저장
		customerManagementMapper.insertCustInfoExt(customerInfo);
		//고객 담당자정보 히스토리 저장
		customerManagementMapper.insertCustHistInfoExt(customerInfo, today);
		
		List<CustomerInfoVO>  result = customerManagementMapper.getCustomerInfoList(soId, custId, "", null,today, lng);
		
		
		//납부계정 자동 저장
		PaymentAccountInfoVO pymAcntInfo = new PaymentAccountInfoVO();
		
		pymAcntInfo.setSoId(customerInfo.getSoId());
		pymAcntInfo.setCustId(custId);
		pymAcntInfo.setAcntNm(customerInfo.getCustNm());
		pymAcntInfo.setZipCd(customerInfo.getZipCd());
		pymAcntInfo.setBaseAddr(customerInfo.getBaseAddr());
		pymAcntInfo.setAddrDtl(customerInfo.getAddrDtl());
		pymAcntInfo.setEml(customerInfo.getEml());
		pymAcntInfo.setTelNo(customerInfo.getTelNo());
		pymAcntInfo.setMtelNo(customerInfo.getMtelNo());
		pymAcntInfo.setFaxNo(customerInfo.getFaxNo());
		pymAcntInfo.setPymMthd("01"); //납부방법 고정
		pymAcntInfo.setPmcResn("01"); //고정
		pymAcntInfo.setBillMdmEmlYn("A"); //이메일
		pymAcntInfo.setBillMdmGiroYn("N");
		pymAcntInfo.setBillMdmSmsYn("N");
		pymAcntInfo.setBnkCd("");
		pymAcntInfo.setAcntOwnerNm("");
		pymAcntInfo.setAcntNo("");
		pymAcntInfo.setCdtcdExpDt("");
		pymAcntInfo.setUseAcntNmYn("Y");
		pymAcntInfo.setBillCyclCd("01");
		pymAcntInfo.setOrgId(customerInfo.getOrgId());
		pymAcntInfo.setUsrId(customerInfo.getUsrId());
		pymAcntInfo.setRegDate(customerInfo.getRegDate());
		pymAcntInfo.setChgDate(customerInfo.getRegDate());
		pymAcntInfo.setRegrId(customerInfo.getUsrId());
		pymAcntInfo.setChgrId(customerInfo.getUsrId());
		
		paymentAccountManagementService.insertPymAcntInfo(pymAcntInfo, today, lng);
		
		if(result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
	}

	@Override
	public CustomerInfoVO updateCustInfo(CustomerInfoVO customerInfo, String today,
	        String todayYYYYMMDD, String lng) {
		//고객정보 변경분 세팅
		customerManagementMapper.updateCustInfo(customerInfo);
		//고객정보 담당자정보 세팅
		customerManagementMapper.updateCustInfoExt(customerInfo);
		//변경 내용 조회
		
		List<CustomerInfoVO>  updatedCustInfoList = customerManagementMapper.getCustomerInfoList(customerInfo.getSoId(), customerInfo.getCustId(),"",null, todayYYYYMMDD, lng);
		
		//납부계정히스토리 저장
		customerManagementMapper.insertCustHistInfo(updatedCustInfoList.get(0), today);
		//고객 담당자정보 히스토리 저장
		customerManagementMapper.insertCustHistInfoExt(updatedCustInfoList.get(0), today);
		
		return updatedCustInfoList.get(0);
		
	}

}
