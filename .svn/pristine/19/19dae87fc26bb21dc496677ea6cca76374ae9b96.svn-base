package com.ntels.ccbs.system.service.so.soMng.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.domain.so.soMng.SoMngVO;
import com.ntels.ccbs.system.mapper.so.soMng.SoMngMapper;
import com.ntels.ccbs.system.service.so.soMng.SoMngService;

@Service
public class SoMngServiceImpl implements SoMngService {
	/** InquiryHistMapper Autowired. */
	@Autowired
	private SoMngMapper soMngMapper;
	/** InquiryHist Service  */
	SoMngService soMngService;

	@Override
	public Map<String, Object> list(String sidx, String sord, int page, int rows,
			String lng,SoMngVO so) {
		Map<String,Object> soInfo = new HashMap<String,Object>();
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		Integer totalCount= soMngMapper.count(so);

		/*
		 *  page : 몇번째의 페이지를 요청했는지.
			rows : 페이지 당 몇개의 행이 보여질건지. 
			sidx : 소팅하는 기준이 되는 인덱스
			sord : 내림차순 or 오름차순
		 */
		if(totalCount.intValue() == 0){
			soInfo.put("totalCount", totalCount);
			soInfo.put("totalPages", new Integer(0));
			soInfo.put("page", new Integer(1));
			Integer maxSo = soMngMapper.getMaxSo() + 1;
			soInfo.put("max", StringUtils.leftPad(maxSo.toString(), 2, "0"));
		}else{
			int endIndex = rows;
			int startIndex = (page-1) * rows;
			
			String end = Integer.toString(endIndex);
			String start = Integer.toString(startIndex);
			
			List<SoMngVO> soList = soMngMapper.list(sidx, sord, start, end, lng,so);
			soList.get(soList.size()-1).getSoId();
			soInfo.put("soList", soList);
			soInfo.put("totalCount", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue() / (float)rows));
			soInfo.put("totalPages", totalPages);
			soInfo.put("page", new Integer(page));
			Integer maxSo = soMngMapper.getMaxSo() + 1;
			soInfo.put("max", StringUtils.leftPad(maxSo.toString(), 2, "0"));
		}
		return soInfo;
	}

	@Override
	public int insert(SoMngVO so,HttpServletRequest request) {
		int checkSo = soMngMapper.checkSo(so);
		if(checkSo>0){
			throw new ServiceException( "MSG.M07.MSG00005" );
		}else{
			SessionUser session_user = (SessionUser) request.getSession().getAttribute("session_user");
			so.setRegrId(session_user.getUserId());
			so.setSysToDate(DateUtil.sysdate());
			
			return soMngMapper.insert(so);
		}
		
	}

	@Override
	public int update(SoMngVO so, HttpServletRequest request) {
		SessionUser session_user = (SessionUser) request.getSession().getAttribute("session_user");
		so.setChgrId(session_user.getUserId());
		so.setSysToDate(DateUtil.sysdate());
		
		return soMngMapper.update(so);
	}

	@Override
	public List<SoMngVO> soAuthList(SoMngVO so,HttpServletRequest request) {
		List<SoMngVO> soAuthList=soMngMapper.soAuthList(so);
		return soAuthList;
	}
}
