package com.ntels.ccbs.product.service.service.serviceMgt.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.mapper.service.serviceMgt.WorkGrpMngMapper;
import com.ntels.ccbs.product.service.service.serviceMgt.WorkGrpMngService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class WorkGrpMngServiceImpl implements WorkGrpMngService {
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private WorkGrpMngMapper workGrpMngMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	
	@Override
	public Map<String, Object> getWorkGrpList(String soId,
	        List<Map<String, Object>> soAuthList, String workGrpNm, String sidx,
	        String sord, int page, int rows, String today, String lng) {
		Map<String,Object> workGrpInfo = new HashMap<String,Object>();
		Integer totalCount = workGrpMngMapper.getWorkGrpListTotalCnt(soId, soAuthList, workGrpNm);
		
		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			workGrpInfo.put("workGrpList", new ArrayList<Map<String,Object>>());
			workGrpInfo.put("totalCount", totalCount);
			workGrpInfo.put("totalPages", new Integer(0));
			workGrpInfo.put("page", new Integer(1));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			
			List<Map<String,Object>> workGrpList = workGrpMngMapper.getWorkGrpList(soId, soAuthList, workGrpNm,sidx, sord, start, end, today, lng);
			workGrpInfo.put("workGrpList", workGrpList);
			workGrpInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			workGrpInfo.put("totalPages", totalPages);
			workGrpInfo.put("page", new Integer(page));
		}
		
		return workGrpInfo;
	}


	@Override
	public List<Map<String, Object>> getUserList(String svcWrkGrpId,
	        String today, String lng) {
		return workGrpMngMapper.getUserList(svcWrkGrpId, today, lng);
	}
	
	@Override
	public List<Map<String, Object>> getWorkGrpUserList(String svcWrkGrpId,
	        String today, String lng) {
		return workGrpMngMapper.getWorkGrpUserList(svcWrkGrpId, today, lng);
	}


	@Override
	public int insertWrokGrp(String workGrpNm,
	        String soId, String useYn, String userId) {
		
		Date today = DateUtil.sysdate();
		String seq = sequenceService.createNewSequence(Consts.SEQ_CODE.CM_WRK_GRP_ID, "WG", 10);
		
		return workGrpMngMapper.insertWorkGrp(seq, workGrpNm, soId, useYn, userId, today);
		
	}


	@Override
	public int updateWorkGrp(String workGrpId, String workGrpNm, String useYn,
	        String userId) {
		Date today = DateUtil.sysdate();
		return workGrpMngMapper.updateWorkGrp(workGrpId, workGrpNm, useYn, userId, today);
	}


	@Override
	public int insertWorkUser(List<Map<String,Object>> addUserList, String userId) {
		if(addUserList == null || addUserList.size() == 0){
			return 0;
		}

		Date today = DateUtil.sysdate();
		int addCount = 0;
		for(Map<String,Object> addUserInfo : addUserList){
			try{
				
				int addUser = workGrpMngMapper.insertWorkUser((String)addUserInfo.get("WORK_GRP_ID"),(String)addUserInfo.get("ADD_USER_ID"),  userId, today);
				if(addUser == 0){
					String[] arg = {(String)addUserInfo.get("ADD_USER_ID")};
					throw new ServiceException("MSG.M07.MSG00040", arg);
				}
				addCount = addCount + addUser;
			}catch(Exception e){
				String[] arg = {(String)addUserInfo.get("ADD_USER_ID")};
				throw new ServiceException("MSG.M07.MSG00040", arg);
			}
		}
		return addCount;
	}


	@Override
	public int updateWorkUser(List<Map<String, Object>> updateDataList,
	        String userId) {
		if(updateDataList == null || updateDataList.size() == 0){
			return 0;
		}

		Date today = DateUtil.sysdate();
		int updateCount = 0;
		for(Map<String,Object> updateUserInfo : updateDataList){
			try{
				
				int updateUser = workGrpMngMapper.updateWorkUser((String)updateUserInfo.get("WORK_GRP_ID"),(String)updateUserInfo.get("USER_ID"), (String)updateUserInfo.get("USE_YN"),(String)updateUserInfo.get("SMS_YN"),  userId, today);
				if(updateUser == 0){
					String[] arg = {(String)updateUserInfo.get("USER_ID")};
					throw new ServiceException("MSG.M07.MSG00038", arg);
				}
				updateCount = updateCount + updateUser;
			}catch(Exception e){
				String[] arg = {(String)updateUserInfo.get("USER_ID")};
				throw new ServiceException("MSG.M07.MSG00038", arg);
			}
		}
		return updateCount;
	}


	@Override
	public int deleteWorkUser(List<Map<String, Object>> deleteDataList,
	        String userId) {
		if(deleteDataList == null || deleteDataList.size() == 0){
			return 0;
		}
		int deleteCount = 0;
		for(Map<String,Object> deleteUserInfo : deleteDataList){
			try{
				
				int deleteUser = workGrpMngMapper.deleteWorkUser((String)deleteUserInfo.get("WORK_GRP_ID"),(String)deleteUserInfo.get("USER_ID"));
				if(deleteUser == 0){
					String[] arg = {(String)deleteUserInfo.get("USER_ID")};
					throw new ServiceException("MSG.M07.MSG00037", arg);
				}
				deleteCount = deleteCount + deleteUser;
			}catch(Exception e){
				String[] arg = {(String)deleteUserInfo.get("USER_ID")};
				throw new ServiceException("MSG.M07.MSG00037", arg);
			}
		}
		return deleteCount;
	}


	@Override
	public int deleteWorkGrp(String workGrpId) {
		int deleteWorkCnt = workGrpMngMapper.deleteWorkGrp(workGrpId);
		int deleteWorkDtlCnt = workGrpMngMapper.deleteWorkGrpDtl(workGrpId);
		return deleteWorkCnt;
	}
	
}
