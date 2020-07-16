package com.ntels.ccbs.customer.service.contract.contract.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.customer.mapper.contract.contract.InvoiceDetailMapper;
import com.ntels.ccbs.customer.service.contract.contract.InvoiceDetailService;
import com.ntels.nisf.util.message.MessageUtil;


@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService{

	@Autowired
	private InvoiceDetailMapper invoiceDetailMapper;

	@Override
	public List<Map<String, Object>> getBillList(String soId, String custId, String ctrtId, String pymAcntId) {
		
		if(StringUtils.isEmpty(soId)){
			String[] arg = {MessageUtil.getMessage("LAB.M07.LAB00004")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(custId)){
			String[] arg = {MessageUtil.getMessage("LAB.M01.LAB00046")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(ctrtId)){
			String[] arg = {MessageUtil.getMessage("LAB.M01.LAB00032")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(pymAcntId)){
			String[] arg = {MessageUtil.getMessage("LAB.M02.LAB00006")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		//납부계정 리스트 조회
		List<Map<String,Object>> pymList = invoiceDetailMapper.getPymList(soId, custId, ctrtId);
		
		
		return invoiceDetailMapper.getBillList(soId, pymList);
	}

	@Override
	public List<Map<String, Object>> getBillCtrtList(String billSeqNo, String billYymm, String billDt,
			String pymAcntId) {
		if(StringUtils.isEmpty(billSeqNo)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00039")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(billYymm)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00033")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(billDt)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00040")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(pymAcntId)){
			String[] arg = {MessageUtil.getMessage("LAB.M02.LAB00006")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		
		return invoiceDetailMapper.getBillCtrtList(billSeqNo, billYymm, billDt, pymAcntId);
	}

	@Override
	public List<Map<String, Object>> getBillCtrtDtlList(String billSeqNo, String billYymm, String billDt, String soId,
			String ctrtId, String pymAcntId) {
		if(StringUtils.isEmpty(billSeqNo)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00039")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(billYymm)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00033")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(billDt)){
			String[] arg = {MessageUtil.getMessage("LAB.M10.LAB00040")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(soId)){
			String[] arg = {MessageUtil.getMessage("LAB.M07.LAB00004")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(ctrtId)){
			String[] arg = {MessageUtil.getMessage("LAB.M01.LAB00032")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		if(StringUtils.isEmpty(pymAcntId)){
			String[] arg = {MessageUtil.getMessage("LAB.M02.LAB00006")};
			throw new ServiceException("MSG.M13.MSG00027", arg); // 필수값 체크
		}
		
		return invoiceDetailMapper.getBillCtrtDtlList(billSeqNo, billYymm, billDt, soId, ctrtId, pymAcntId);
	}


}