package com.ntels.ccbs.common.interceptor;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.domain.common.AppResponseVO;
import com.ntels.ccbs.appIf.domain.common.IfLogVO;
import com.ntels.ccbs.appIf.service.common.AppLogService;
import com.ntels.ccbs.common.exception.AppException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

/**
 * 
 * <PRE>
 * 1. ClassName: IfInterceptor
 * 2. FileName : IfInterceptor.java
 * 3. Package  : com.ntels.ccbs.common.interceptor
 * 4. Comment  : 인터페이스 연동 인터셉터
 * 5. 작성자   : jhkim
 * 6. 작성일   : 2017. 3. 14. 오후 4:00:47
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     jhkim :    2017. 3. 14.    : 신규개발
 * </PRE>
 */
public class AppIfInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 인터페이스 url
	 */
	private List<String>  listAppUrl;
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppLogService appLogService;

	public List<String> getListAppUrl() {
		return listAppUrl;
	}

	public void setListAppUrl(List<String> listAppUrl) {
		this.listAppUrl = listAppUrl;
	}


	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {
		
		/**
		 * 인터페이스 url만 처리
		 */
		boolean isAppUrl = false;
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				isAppUrl = true;
				break;
			}
		}
		if(isAppUrl == false) return true;
		
		
		/**
		 * 연동 로그 정보 세팅
		 */
		IfLogVO ifLog = new IfLogVO();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			   ipAddress = request.getRemoteAddr();
		}
		
		boolean isMulipart = isMultipart(request);

		SessionUser su = CommonUtil.getSessionManager();
		ifLog.setRemoteAddr(ipAddress.length() > 100 ? ipAddress.substring(0, 100) : ipAddress);
		ifLog.setServerName(request.getServerName().length() > 128 ? request.getServerName().substring(0, 128) : request.getServerName());
		ifLog.setServerPort(String.valueOf(request.getServerPort()));
		ifLog.setSessionId(request.getRequestedSessionId() != null && request.getRequestedSessionId().length() > 32 ? request.getRequestedSessionId().substring(0, 32) : request.getSession().getId());
		ifLog.setReqPath(request.getPathInfo() != null && request.getPathInfo().length() > 256 ? request.getPathInfo().substring(0, 256) : request.getPathInfo());
		ifLog.setReqMethod(request.getMethod() != null && request.getMethod().length() > 32 ? request.getMethod().substring(0, 32) : request.getMethod());
		if(su != null){
			ifLog.setUserId(su.getUserId());
		}
		ifLog.setReqDate(DateUtil.sysdate());
		request.setAttribute("IF_LOG", ifLog);
		
		String line = null;
		StringBuffer jb = new StringBuffer();
		BufferedReader requestReader = request.getReader();
		while ((line = requestReader.readLine()) != null && isMulipart == false)
			jb.append(line);
		
		if(isMulipart){
			jb.append((String)request.getParameter("JSON"));
		}
		
		logger.debug("APP REQUEST : " + jb.toString());
		
		/**
		 * 헤더 필수값 체크
		 */
		ObjectMapper mapper = new ObjectMapper();
		
		AppRequestVO req = mapper.readValue(jb.toString(), AppRequestVO.class);
		
		if(StringUtils.isEmpty(req.getHeader().getSvcode())){
			Object[] arguments = {"서비스코드(SVCODE)" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); //서비스 코드 누락
		}
		if(StringUtils.isEmpty(req.getHeader().getOpcode())){
			Object[] arguments = {"서비스코드(OPCODE)" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); //오퍼레이션 코드 누락
		}
		if(StringUtils.isEmpty(req.getHeader().getChcode())){
			Object[] arguments = {"채널코드(CHCODE)" };
			throw new AppException("MSG.IF.E0005", "E0005",arguments); //채널코드 코드 누락
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		/**
		 * APP 연동 URL만 처리
		 */
		boolean isAppUrl = false;
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				isAppUrl = true;
				break;
			}
		}
		if(isAppUrl == false) return;
		
		boolean isMulipart = isMultipart(request);
		
		
		/**
		 *  APP 연동 로그 기록
		 */
		
		IfLogVO ifLog = (IfLogVO)request.getAttribute("IF_LOG");
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		String svcode = null;
		String opcode = null;
		String chcode = null;
		
		if(ifLog != null){
			try{
				ifLog.setResDate(DateUtil.sysdate());
				
				BufferedReader requestReader = request.getReader();
				
				while ((line = requestReader.readLine()) != null && isMulipart == false)
					jb.append(line);
				
				if(isMulipart){
					jb.append((String)request.getParameter("JSON"));
				}
				ObjectMapper mapper = new ObjectMapper();
				AppRequestVO req = mapper.readValue(jb.toString(), AppRequestVO.class);
				ifLog.setReqMsg(StringUtils.isEmpty(jb.toString()) ? "" : jb.toString()  );
				svcode = req.getHeader().getSvcode();
				opcode = req.getHeader().getOpcode();
				chcode = req.getHeader().getChcode();
				ifLog.setSvCode(svcode);
				ifLog.setOpCode(opcode);
				ifLog.setChCode(chcode);
    			
    			
    			AppResponseVO res = (AppResponseVO)modelAndView.getModel().get("RESPONSE");
    			ifLog.setMsgcode(res.getHeader().getMsgcode());
    			ifLog.setMessage(res.getHeader().getMessage());
    			
    			String responseStr = mapper.writeValueAsString(modelAndView.getModel().get("RESPONSE"));
    			ifLog.setResMsg(responseStr);
    			logger.debug("APP RESPONSE : " + responseStr);
			}catch(Exception loge){
				logger.error(loge.getLocalizedMessage());
			}
			
			appLogService.nsSaveInsertLog(ifLog);
		}
	}
	
	private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }

}
