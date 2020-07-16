package com.ntels.ccbs.customer.service.contract.receipt.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.RcptInfoVO;
import com.ntels.ccbs.customer.domain.contract.contract.RcptTabListVO;
import com.ntels.ccbs.customer.domain.contract.receipt.ReceiptCounselVO;
import com.ntels.ccbs.customer.mapper.contract.receipt.ReceiptCounselMapper;
import com.ntels.ccbs.customer.service.contract.receipt.ReceiptCounselService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;

@Service
public class ReceiptCounselServiceImpl implements ReceiptCounselService{
	
	@Autowired
	private ReceiptCounselMapper receiptCounselMapper;


	@Override
	public List<ReceiptCounselVO> getRcptLvlCodeList(String commonGrp, String refCode, String lng) {
		return receiptCounselMapper.getRcptLvlCodeList(commonGrp, refCode, lng);
	}

	@Override
	public Map<String, Object> getRcptTabList(String searchStatDt, String searchEndDt, String cnslStat, String condCustId, String selRcptLvl1, String selRcptLvl2,String selRcptLvl3,String rcptYn, String elapse, String elapseDate, String orgId,
	        String sidx, String sord, int page, int rows, String lng,
	        String today) {
		Map<String,Object> response = new HashMap<String,Object>();
		
		Integer totalCount = receiptCounselMapper.getRcptTabListTotalCnt(searchStatDt, searchEndDt, cnslStat, condCustId, selRcptLvl1, selRcptLvl2, selRcptLvl3, rcptYn, elapse, elapseDate, orgId);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			response.put("rcptList", new ArrayList<RcptTabListVO>());
			response.put("totalCount", totalCount);
			response.put("totalPages", new Integer(0));
			response.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			 
			
			List<ReceiptCounselVO> rcptInfoList = receiptCounselMapper.getRcptTabList(searchStatDt, searchEndDt, cnslStat, condCustId, selRcptLvl1, selRcptLvl2, selRcptLvl3, rcptYn, elapse, elapseDate, orgId, today, lng,  sidx, sord, start, end);
			/*
			 * 변환작업
			 */
			List<RcptTabListVO> list = new ArrayList<RcptTabListVO>();
			for(ReceiptCounselVO rcptInfo : rcptInfoList){
				RcptTabListVO rcptTabInfo = new RcptTabListVO();
				rcptTabInfo.setRcptId(rcptInfo.getRcptId());
				rcptTabInfo.setCtrtId(rcptInfo.getCtrtId());
				rcptTabInfo.setRcptTpNm(rcptInfo.getRcptTpNm());
				rcptTabInfo.setCnslStatNm(rcptInfo.getCnslStatNm());
				rcptTabInfo.setTranStatNm(rcptInfo.getTranStatNm());
				if(StringUtils.isEmpty(rcptInfo.getRcptUsrIdNm())){
					rcptTabInfo.setRcptUsrInfo("");
				}else{
					rcptTabInfo.setRcptUsrInfo(rcptInfo.getRcptUsrIdNm() + "(" + rcptInfo.getRcptOrgIdNm()  + ")");
				}
				if(StringUtils.isEmpty(rcptInfo.getRcptDt())){
					rcptTabInfo.setRcptDttm("");
				}else{
					rcptTabInfo.setRcptDttm(rcptInfo.getRcptDt() + rcptInfo.getRcptDm());
				}
				if(StringUtils.isEmpty(rcptInfo.getProcUsrIdNm())){
					rcptTabInfo.setProcUsrInfo("");
				}else{
					rcptTabInfo.setProcUsrInfo(rcptInfo.getProcUsrIdNm() + "(" + rcptInfo.getProcOrgIdNm() + ")");
				}
				if(StringUtils.isEmpty(rcptInfo.getProcDt())){
					rcptTabInfo.setProcDttm("");
				}else{
					rcptTabInfo.setProcDttm(rcptInfo.getProcDt() + rcptInfo.getProcDm());
				}
				if(StringUtils.isEmpty(rcptInfo.getCmplUsrIdNm())){
					rcptTabInfo.setCmplUsrInfo("");
				}else{
					rcptTabInfo.setCmplUsrInfo(rcptInfo.getCmplUsrIdNm() + "(" + rcptInfo.getCmplOrgIdNm() + ")");
				}
				if(StringUtils.isEmpty(rcptInfo.getCmplDt())){
					rcptTabInfo.setCmplDttm("");
				}else{
					rcptTabInfo.setCmplDttm(rcptInfo.getCmplDt() + rcptInfo.getCmplDm());
				}
				rcptTabInfo.setCnslMstClNm(rcptInfo.getCnslMstClNm());
				rcptTabInfo.setCnslMidClNm(rcptInfo.getCnslMidClNm());
				rcptTabInfo.setCnslSlvClNm(rcptInfo.getCnslSlvClNm());
				rcptTabInfo.setReqDesc(rcptInfo.getReqDesc());
				rcptTabInfo.setProcDesc(rcptInfo.getProcDesc());
				rcptTabInfo.setCustRelNm(rcptInfo.getCustRelNm());
				rcptTabInfo.setReqNm(rcptInfo.getReqNm());
				rcptTabInfo.setReqTelNo(rcptInfo.getReqTelNo());
				
				
				 try {
						Date date = new Date();  
					    String startDt = rcptInfo.getRcptDt() + rcptInfo.getRcptDm();
					    String endDt = DateUtil.getDateStringYYYYMMDDHH24MISS(0);				     

				        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				        Date beginDate = formatter.parse(startDt);
				        Date endDate = formatter.parse(endDt);
				         
				        // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
				        long diff = endDate.getTime() - beginDate.getTime();
				        long diffDays = diff / (60 * 60 * 1000);
				        
				        System.out.println("날짜차이1==========>" + rcptInfo.getCnslStat());
				        System.out.println("날짜차이2==========>" + diffDays);
				        if(rcptInfo.getCnslStat().equals("4") || rcptInfo.getCnslStat()=="4"){
				        	rcptTabInfo.setColorFl("N"); 
				        }else{
					        if(diffDays > 168){
					        	rcptTabInfo.setColorFl("R");
					        }else if(diffDays > 24 && diffDays < 168 ){
					        	rcptTabInfo.setColorFl("B");
					        }else if(diffDays > 12 && diffDays < 24 ){
					        	rcptTabInfo.setColorFl("G");	        	
					        }else if(diffDays < 12 ){
					        	rcptTabInfo.setColorFl("N");    	
					        }
				        }				         
				    } catch (ParseException e) {
				        e.printStackTrace();
				    }	
			    System.out.println("getColorFl==========>" + rcptTabInfo.getColorFl());
				list.add(rcptTabInfo);
			}
			
			response.put("rcptList", list);
			response.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			response.put("totalPages", totalPages);
			response.put("page", new Integer(page));
		}
		
		return response;	
	}
	@Override
	public ReceiptCounselVO getRcptInfo(String rcptId, String lng, String today) {
		return receiptCounselMapper.getRcptInfo(rcptId, lng, today);
	}

}
