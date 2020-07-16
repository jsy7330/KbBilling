package com.ntels.ccbs.customer.service.contract.receipt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.customer.domain.contract.receipt.ReceiptInfoVO;
import com.ntels.ccbs.customer.mapper.contract.receipt.ReceiptInfoMapper;
import com.ntels.ccbs.customer.service.contract.receipt.ReceiptInfoService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

@Service
public class ReceiptInfoServiceImpl implements ReceiptInfoService{
	
	@Autowired
	private ReceiptInfoMapper receiptInfolMapper;

	@Override
	public List<ReceiptInfoVO> getReceiptInfoList(ReceiptInfoVO rcptInfo, String lng, String today) {
		SessionUser sessionUser = CommonUtil.getSessionManager();

		List<ReceiptInfoVO> recieptInfoList = receiptInfolMapper.getReceiptInfoList(rcptInfo, lng, today,
				sessionUser.getSoAuthList());
		return recieptInfoList;
	}

	@Override
	public ReceiptInfoVO getMenuNo(String menuNo) {
		ReceiptInfoVO menuInfo = receiptInfolMapper.getMenuNo(menuNo);
		return menuInfo;
	}
}