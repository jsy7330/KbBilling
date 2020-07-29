package com.ntels.ccbs.charge.service.billing.billingAdjust.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAdjustVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingAfterAdjustSearchVO;
import com.ntels.ccbs.charge.domain.billing.billingAdjust.BillingBeforeAdjustSearchVO;
import com.ntels.ccbs.charge.mapper.billing.billingAdjust.BillingBeforeAdjustSearchMapper;
import com.ntels.ccbs.charge.service.billing.billingAdjust.BillingBeforeAdjustSearchService;

@Service
public class BillingBeforeAdjustSearchServiceImpl implements BillingBeforeAdjustSearchService{

	@Autowired
	private BillingBeforeAdjustSearchMapper billingBeforeAdjustSearchMapper;

	@Override
	public Map<String, Object> getBillChargeAdjustReportList(BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO,
			String sidx, String sord, int page, int rows, String lng) {
		// TODO Auto-generated method stub
		
		Map<String, Object> billChargeAdjustReport = new HashMap<String, Object>();
		Integer totalCount = billingBeforeAdjustSearchMapper.totalCount(billingBeforeAdjustSearchVO);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount.intValue() == 0){
			billChargeAdjustReport.put("billChargeAdjustReportList", new ArrayList<BillingBeforeAdjustSearchVO>());
			billChargeAdjustReport.put("totalCount", totalCount);
			billChargeAdjustReport.put("totalPages", new Integer(0));
			billChargeAdjustReport.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<BillingBeforeAdjustSearchVO> billChargeAdjustReportList = billingBeforeAdjustSearchMapper.getBillChargeAdjustReportList(billingBeforeAdjustSearchVO, sidx, sord, start, end, lng);
			
			billChargeAdjustReport.put("billChargeAdjustReportList", billChargeAdjustReportList);
			billChargeAdjustReport.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			billChargeAdjustReport.put("totalPages", totalPages);
			billChargeAdjustReport.put("page", new Integer(page));
		}
		
		return billChargeAdjustReport;
	}

	@Override
	public Map<String, Object> billingBeforeSearchPopupDtlList(BillingAdjustVO billingAdVO, String sidx, String sord,
			int page, int rows, String lng) {
		// TODO Auto-generated method stub
		
		Map<String, Object> billChrgAdjReport = new HashMap<String, Object>();
		Integer totalCount = billingBeforeAdjustSearchMapper.dtTotalCount(billingAdVO);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		
		if(totalCount.intValue() == 0){
			billChrgAdjReport.put("billChargeAdjustReportList", new ArrayList<BillingBeforeAdjustSearchVO>());
			billChrgAdjReport.put("totalCount", totalCount);
			billChrgAdjReport.put("totalPages", new Integer(0));
			billChrgAdjReport.put("page", new Integer(1));
		}else {
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<BillingAdjustVO> billChargeAdjustReportList = billingBeforeAdjustSearchMapper.billingBeforeSearchPopupDtlList(billingAdVO, sidx, sord, start, end, lng);
			
			billChrgAdjReport.put("billChargeAdjustReportList", billChargeAdjustReportList);
			billChrgAdjReport.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			billChrgAdjReport.put("totalPages", totalPages);
			billChrgAdjReport.put("page", new Integer(page));
		}
		
		return billChrgAdjReport;
	}

	@Override
	public List<Map<String, Object>> listExcel(BillingBeforeAdjustSearchVO billingBeforeAdjustSearchVO) {
		// TODO Auto-generated method stub
		return billingBeforeAdjustSearchMapper.listExcel(billingBeforeAdjustSearchVO);
	}

	@Override
	public List<Map<String, Object>> popUpListExcel(BillingAdjustVO billingAdVO) {
		// TODO Auto-generated method stub
		return billingBeforeAdjustSearchMapper.popUpListExcel(billingAdVO);
	}
	
	
}
