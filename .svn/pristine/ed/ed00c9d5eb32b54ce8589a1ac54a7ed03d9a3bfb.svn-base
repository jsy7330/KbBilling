package com.ntels.ccbs.system.service.main.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.bulletin.bulletinMng.BulletinMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.main.InquiryHistVO;
import com.ntels.ccbs.system.mapper.main.MainMapper;
import com.ntels.ccbs.system.service.main.MainService;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mainMapper; 
	
	@Override
	public List<HashMap<String, String>> joinMemberChart() {
		
		return mainMapper.joinMemberChart();
	}

	@Override
	public List<HashMap<String, String>> regiVoucherChart() {
		
		return mainMapper.regiVoucherChart();
	}

	@Override
	public List<InquiryHistVO> noticeList() {
		
		return mainMapper.noticeList();
	}

	@Override
	public InquiryHistVO noticeView(InquiryHistVO inquiryHistVO) {
		
		return mainMapper.noticeView(inquiryHistVO);
	}

	@Override
	public HashMap<String, String> cntResult() {
		
		return mainMapper.cntResult();
	}

	@Override
	public Map<String, Object> mainBulletinList(String sidx, String sord, int page, int rows, BulletinMngVO bulletin,String lng) {
		Map<String,Object> bulleintInfo = new HashMap<String,Object>();
		bulletin.setRegDate(DateUtil.getDateStringYYYYMMDD(0));
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String userId=sessionUser.getUserId();
		Integer totalCount= mainMapper.count(bulletin,userId);
		
		if(totalCount.intValue() == 0){
			bulleintInfo.put("totalCount", totalCount);
			bulleintInfo.put("totalPages", new Integer(0));
			bulleintInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<BulletinMngVO> bulletinList = mainMapper.mainBulletinList(sidx, sord, start, end,bulletin,lng,userId);
			
			bulleintInfo.put("bulletinList", bulletinList);
			bulleintInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			bulleintInfo.put("totalPages", totalPages);
			bulleintInfo.put("page", new Integer(page));
		}

		return bulleintInfo;
	}

	@Override
	public BulletinMngVO updateDetailBulletin(String noticeId, String lng) {
		BulletinMngVO detail = mainMapper.detailBulletin(noticeId,lng);
		List<BulletinMngVO> auth = mainMapper.authBulletin(noticeId);
		
		StringBuilder authGrpId = new StringBuilder();
		StringBuilder authGrpNm = new StringBuilder();
		for(int i=0;i<auth.size();i++){
			authGrpId.append(auth.get(i).getAuthGroup());
			authGrpNm.append(auth.get(i).getUserGroupName());
			if(i < auth.size()-1){
				authGrpId.append(",");
				authGrpNm.append(",");
			}
		}
		detail.setUserGroupId(authGrpId.toString());
		detail.setUserGroupName(authGrpNm.toString());
		StringBuilder fileInfo = new StringBuilder();
		//첨부파일 정보 조회
		List<Map<String,Object>> fileList =  mainMapper.getFileList(noticeId);
		for(int k = 0; k < fileList.size();k++){
			fileInfo.append(fileList.get(k).get("FILE_NM"));
			fileInfo.append(":");
			fileInfo.append(fileList.get(k).get("FILE_UUID"));
			if(k < fileList.size() -1){
				fileInfo.append(",");
			}
		}
		detail.setFileNm(fileInfo.toString());
		
		mainMapper.updateViewCnt(noticeId);
		return detail;
	}
	
	@Override
	public Map<String, Object> billingMainChart1(BulletinMngVO bulletin,String lng) {
		Map<String,Object> bulleintInfo = new HashMap<String,Object>();
		bulletin.setRegDate(DateUtil.getDateStringYYYYMMDD(0));
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String userId=sessionUser.getUserId();		
		/*
		System.out.println("to_month0=======>"+DateUtil.getDateStringYYYYMM(0).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(0).substring(4,6));
		System.out.println("to_month1=======>"+DateUtil.getDateStringYYYYMM(1));
		System.out.println("to_month2=======>"+DateUtil.getDateStringYYYYMM(2));
		System.out.println("to_month3=======>"+DateUtil.getDateStringYYYYMM(3));
		*/
		bulletin.setStartMonth(DateUtil.getDateStringYYYYMM(2).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(2).substring(4,6));
		bulletin.setMiddleMonth(DateUtil.getDateStringYYYYMM(1).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(1).substring(4,6));
		bulletin.setEndMonth(DateUtil.getDateStringYYYYMM(0).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(0).substring(4,6));
		
		bulletin.setSearchStartMonth(DateUtil.getDateStringYYYYMM(2));
		bulletin.setSearchEndMonth(DateUtil.getDateStringYYYYMM(0));
		bulletin.setSoId(sessionUser.getSoId());
		
		List<BulletinMngVO> billingMainChart1 = mainMapper.billingMainChart1(bulletin,lng);			
		bulleintInfo.put("billingMainChart1", billingMainChart1);
		
		return bulleintInfo;
	}
	@Override
	public Map<String, Object> billingMainChart2(BulletinMngVO bulletin,String lng) {
		Map<String,Object> bulleintInfo = new HashMap<String,Object>();
		bulletin.setRegDate(DateUtil.getDateStringYYYYMMDD(0));
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String userId=sessionUser.getUserId();		
		
		bulletin.setStartMonth(DateUtil.getDateStringYYYYMM(2).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(2).substring(4,6));
		bulletin.setMiddleMonth(DateUtil.getDateStringYYYYMM(1).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(1).substring(4,6));
		bulletin.setEndMonth(DateUtil.getDateStringYYYYMM(0).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(0).substring(4,6));
		
		bulletin.setSearchStartMonth(DateUtil.getDateStringYYYYMM(2));
		bulletin.setSearchEndMonth(DateUtil.getDateStringYYYYMM(0));
		bulletin.setSoId(sessionUser.getSoId());
		
		List<BulletinMngVO> billingMainChart2 = mainMapper.billingMainChart2(bulletin,lng);			
		bulleintInfo.put("billingMainChart2", billingMainChart2);
		
		return bulleintInfo;
	}	
	
	@Override
	public Map<String, Object> customerMainChart1(BulletinMngVO bulletin,String lng) {
		Map<String,Object> bulleintInfo = new HashMap<String,Object>();
		bulletin.setRegDate(DateUtil.getDateStringYYYYMMDD(0));
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String userId=sessionUser.getUserId();		
		/*
		System.out.println("to_month0=======>"+DateUtil.getDateStringYYYYMM(0).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(0).substring(4,6));
		System.out.println("to_month1=======>"+DateUtil.getDateStringYYYYMM(1));
		System.out.println("to_month2=======>"+DateUtil.getDateStringYYYYMM(2));
		System.out.println("to_month3=======>"+DateUtil.getDateStringYYYYMM(3));
		*/
		bulletin.setStartMonth(DateUtil.getDateStringYYYYMM(-2).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(-2).substring(4,6));
		bulletin.setMiddleMonth(DateUtil.getDateStringYYYYMM(-1).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(-1).substring(4,6));
		bulletin.setEndMonth(DateUtil.getDateStringYYYYMM(0).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(0).substring(4,6));
		
		bulletin.setSearchStartMonth(DateUtil.getDateStringYYYYMM(-2));
		bulletin.setSearchMiddleMonth(DateUtil.getDateStringYYYYMM(-1));
		bulletin.setSearchEndMonth(DateUtil.getDateStringYYYYMM(0));
		bulletin.setSoId(sessionUser.getSoId());
		
		List<BulletinMngVO> customerMainChart1 = mainMapper.customerMainChart1(bulletin,lng);			
		bulleintInfo.put("customerMainChart1", customerMainChart1);
		
		return bulleintInfo;
	}
	@Override
	public Map<String, Object> customerMainChart2(BulletinMngVO bulletin,String lng) {
		Map<String,Object> bulleintInfo = new HashMap<String,Object>();
		bulletin.setRegDate(DateUtil.getDateStringYYYYMMDD(0));
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String userId=sessionUser.getUserId();		
		
		bulletin.setStartMonth(DateUtil.getDateStringYYYYMM(-2).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(-2).substring(4,6));
		bulletin.setMiddleMonth(DateUtil.getDateStringYYYYMM(-1).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(-1).substring(4,6));
		bulletin.setEndMonth(DateUtil.getDateStringYYYYMM(0).substring(0,4)+"/"+DateUtil.getDateStringYYYYMM(0).substring(4,6));
		
		bulletin.setSearchStartMonth(DateUtil.getDateStringYYYYMM(-2));
		bulletin.setSearchMiddleMonth(DateUtil.getDateStringYYYYMM(-1));
		bulletin.setSearchEndMonth(DateUtil.getDateStringYYYYMM(0));
		bulletin.setSoId(sessionUser.getSoId());
		
		List<BulletinMngVO> customerMainChart2 = mainMapper.customerMainChart2(bulletin,lng);			
		bulleintInfo.put("customerMainChart2", customerMainChart2);
		
		return bulleintInfo;
	}		
}
