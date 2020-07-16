package com.ntels.ccbs.system.service.auth.authMng.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.auth.authMng.SoAuthMngVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.mapper.auth.authMng.SoAuthMngMapper;
import com.ntels.ccbs.system.service.auth.authMng.SoAuthMngService;

@Service
public class SoAuthMngServiceImpl implements SoAuthMngService {
	/** InquiryHistMapper Autowired. */
	@Autowired
	private SoAuthMngMapper soAuthMngMapper;
	/** InquiryHist Service  */
	SoAuthMngService soAuthMngService;
	@Override
	public Map<String, Object> userAuthList(SoAuthMngVO soAuth, String lng) {
		Map<String,Object> soAuthInfo = new HashMap<String,Object>();
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		
		Integer totalCount= soAuthMngMapper.soAuthCount(soAuth);
		
		if(totalCount.intValue() == 0){
			soAuthInfo.put("totalCount", totalCount);
			soAuthInfo.put("totalPages", new Integer(0));
			soAuthInfo.put("page", new Integer(1));
		}else{
			
			List<SoAuthMngVO> soAuthList = soAuthMngMapper.soAuthList(soAuth,lng);
			soAuthInfo.put("soAuthList", soAuthList);
			soAuthInfo.put("totalCount2", totalCount);
			Integer totalPages = new Integer((int)Math.ceil(totalCount.floatValue()));
			soAuthInfo.put("totalPages2", totalPages);
			soAuthInfo.put("page2", new Integer(1));
		}
		
		return soAuthInfo;
	}
	@Override
	public int insert(SoAuthMngVO soAuth) {
		int result=0;
		soAuthMngMapper.deleteAll(soAuth);
		String[] arrSoId=soAuth.getSoId().split(",");
		for(int i=0;i<arrSoId.length;i++){
			soAuth.setSoId(arrSoId[i]);
			result=soAuthMngMapper.insert(soAuth);
		}
		
		return result;
	}
	@Override
	public int delete(SoAuthMngVO soAuth) {
		int result=0;
		String[] arrSoId=soAuth.getSoId().split(",");
		
		for(int i=0;i<arrSoId.length;i++){
			soAuth.setSoId(arrSoId[i]);
			result=soAuthMngMapper.delete(soAuth);
		}
		return result;
	}
	@Override
	public String baseSoId(SoAuthMngVO soAuth) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		return soAuthMngMapper.baseSoId(soAuth,today);
	}
}