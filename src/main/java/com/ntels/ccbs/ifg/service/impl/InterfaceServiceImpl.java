package com.ntels.ccbs.ifg.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ntels.ccbs.appIf.domain.common.AppReqHeaderVO;
import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.domain.common.AppResponseVO;
import com.ntels.ccbs.appIf.domain.common.IfLogVO;
import com.ntels.ccbs.appIf.service.common.AppLogService;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.ifg.service.InterfaceService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;


@Service
public class InterfaceServiceImpl implements InterfaceService{
	
	
	/* The attribute management. */
    private final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
	private AppLogService appLogService;
    
    @Value("${service.platform.protocol}")
	private String protocol;
	
	@Value("${service.platform.host}")
	private String host;
	
	@Value("${service.platform.port}")
	private String port;
	
	/**
	 * <PRE>
	 * 1. MethodName: createUrl
	 * 2. ClassName : InterfaceServiceImpl
	 * 3. Comment   : Interface를 위한 URL작성
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 7. 31. 오전 8:46:27
	 * </PRE>
	 *   @return String URL
	 *   @param protocol 프로토콜
	 *   @param hostname 호스트명
	 *   @param port 포트
	 *   @param serviceName 서비스명
	 */
    private String createUrl(String protocol, String hostname, String port, String serviceName){
    	StringBuilder sb = new StringBuilder();
    	sb.append(protocol);
    	sb.append("://");
    	sb.append(hostname);
    	sb.append(":");
    	sb.append(port);
    	sb.append(serviceName);
    	return sb.toString();
    }

	@Override
	public List<Map<String, Object>> getHomeServiceList(String soId, String custId) throws ServiceException {
		if(StringUtils.isEmpty(soId)){
			String[] arg = {"SO_ID"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		if(StringUtils.isEmpty(custId)){
			String[] arg = {"CUST_ID"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		
		//헤더 정보
		AppReqHeaderVO header = new AppReqHeaderVO();
		header.setSvcode("APP0001");
		header.setOpcode("APPOP03");
		header.setChcode("03"); //CCBS
		AppRequestVO request = new AppRequestVO();
		request.setHeader(header);
		
		//바디정보
		LinkedHashMap<String,Object> body = new LinkedHashMap<String,Object>();
		body.put("SO_ID", soId);
		body.put("CUST_ID", custId);
		request.setBody(body);
		
		String method = "/ocs/v1/ccbs/service/member";
		
		AppResponseVO response = sendPost(request, method);
		if(response == null){
			throw new ServiceException("MSG.M08.MSG00013");
		}else if(!"N0000".equals(response.getHeader().getMsgcode())){
			String[] arg = {response.getHeader().getMsgcode(), response.getHeader().getMessage()};
			throw new ServiceException("MSG.M15.MSG00054", arg);
		}
		
		List<Map<String,Object>> memList = (List<Map<String, Object>>) response.getBody().get("MEM_INFO");
		return memList;
	}
	
	@Override
	public boolean sendCtrtChange(Map<String, Object> requestInfo) {
		if(StringUtils.isEmpty((String)requestInfo.get("SO_ID"))){
			String[] arg = {"SO_ID"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		
		if(StringUtils.isEmpty((String)requestInfo.get("CUST_ID"))){
			String[] arg = {"CUST_ID"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		if(StringUtils.isEmpty((String)requestInfo.get("ORDER_TP"))){
			String[] arg = {"ORDER_TP"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		
		if(StringUtils.isEmpty((String)requestInfo.get("ORDER_ID"))){
			String[] arg = {"ORDER_ID"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		
		if(StringUtils.isEmpty((String)requestInfo.get("CTRT_ID"))){
			String[] arg = {"CTRT_ID"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		
		if(StringUtils.isEmpty((String)requestInfo.get("PROD_CD"))){
			String[] arg = {"PROD_CD"};
			throw new ServiceException("MSG.M13.MSG00027", arg);
		}
		

		//헤더 정보
		AppReqHeaderVO header = new AppReqHeaderVO();
		header.setSvcode("APP0002");
		header.setOpcode("APPOP03");
		header.setChcode("03"); //CCBS
		AppRequestVO request = new AppRequestVO();
		request.setHeader(header);
		
		//바디정보
		request.setBody(requestInfo);
		
		String method = "/ocs/v1/ccbs/service/modify";
		
		AppResponseVO response = sendPost(request, method);
		if(response == null){
			throw new ServiceException("MSG.M08.MSG00013");
		}else if(!"N0000".equals(response.getHeader().getMsgcode())){
			String[] arg = {response.getHeader().getMsgcode(), response.getHeader().getMessage()};
			throw new ServiceException("MSG.M15.MSG00054", arg);
		}
		return true;
	}

	/**
	 * <PRE>
	 * 1. MethodName: sendPost
	 * 2. ClassName : InterfaceServiceImpl
	 * 3. Comment   : Post 전송
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 4:19:49
	 * </PRE>
	 *   @return AppResponseVO 응답내용
	 *   @param request 요청내용
	 *   @param method Method
	 */
	private AppResponseVO sendPost(AppRequestVO request, String method) {
		SessionUser su = CommonUtil.getSessionManager();
		
		AppResponseVO res = null;
		// 1. 로그 정보 세팅
		IfLogVO ifLog = new IfLogVO();
		ifLog.setRemoteAddr(host);
		ifLog.setServerName("ServicePlatform");
		ifLog.setServerPort(port);
		if(su != null){
			ifLog.setSessionId(su.getSessionId());
		}else{
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			ifLog.setSessionId(req.getSession().getId());
		}
		ifLog.setReqPath(method);
		ifLog.setReqMethod("POST");
		ifLog.setReqDate(DateUtil.sysdate());
		ifLog.setSvCode(request.getHeader().getSvcode());
		ifLog.setOpCode(request.getHeader().getOpcode());
		ifLog.setChCode(request.getHeader().getChcode());
		
		ObjectMapper mapper = new ObjectMapper();
		String requestStr = null;
		try {
			requestStr = mapper.writeValueAsString(request);
			ifLog.setReqMsg(requestStr);
		} catch (Exception e) {
			throw new ServiceException("MSG.M08.MSG00087");
		}		
		
		String url = createUrl(protocol,host,port,method);
		
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		
		if(requestStr == null){
        	logger.error("############ Request JSON Error : " + request.toString());
        	return null;
        }
		logger.debug("############ Request JSON : \n" + requestStr);
		logger.debug("############ URL \t : " + url);
		
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(requestStr);
			
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json; charst=UTF-8");
			httpPost.setHeader("Accept-Charset", "UTF-8");
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity responseEntiry = response.getEntity();
			String responseString = EntityUtils.toString(responseEntiry, "UTF-8");
			ifLog.setResDate(DateUtil.sysdate());
			ifLog.setResMsg(responseString);
			logger.debug("############ Response JSON : \n" + responseString);
			res = mapper.readValue(responseString, AppResponseVO.class);
			ifLog.setMsgcode(res.getHeader().getMsgcode());
			ifLog.setMessage(res.getHeader().getMessage());
		} catch (Exception e){
			throw new ServiceException("MSG.M08.MSG00013");
		} finally{
			appLogService.nsSaveInsertLog(ifLog);
			
		}
		return res;
	}
	

	/**
	 * <PRE>
	 * 1. MethodName: sendPut
	 * 2. ClassName : InterfaceServiceImpl
	 * 3. Comment   : PUT 전송
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2017. 5. 31. 오후 4:20:26
	 * </PRE>
	 *   @return AppResponseVO 응답내용
	 *   @param request 요청내용
	 *   @param method Method
	 */
	private AppResponseVO sendPut(AppRequestVO request, String method) {
		SessionUser su = CommonUtil.getSessionManager();
		
		AppResponseVO res = null;
		// 1. 로그 정보 세팅
		IfLogVO ifLog = new IfLogVO();
		ifLog.setRemoteAddr(host);
		ifLog.setServerName("ServicePlatform");
		ifLog.setServerPort(port);
		ifLog.setSessionId(su.getSessionId());
		ifLog.setReqPath(method);
		ifLog.setReqMethod("PUT");
		ifLog.setReqDate(DateUtil.sysdate());
		ifLog.setSvCode(request.getHeader().getSvcode());
		ifLog.setOpCode(request.getHeader().getOpcode());
		ifLog.setChCode(request.getHeader().getChcode());
		
		ObjectMapper mapper = new ObjectMapper();
		String requestStr = null;
		try {
			requestStr = mapper.writeValueAsString(request);
			ifLog.setReqMsg(requestStr);
		} catch (Exception e) {
			throw new ServiceException("MSG.M08.MSG00087");
		}		
		
		String url = createUrl(protocol,host,port,method);
		
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		
		if(requestStr == null){
        	logger.error("############ Request JSON Error : " + request.toString());
        	return null;
        }
		logger.debug("############ Request JSON : \n" + requestStr);
		logger.debug("############ URL \t : " + url);
		
		try {
			HttpPut httpPut = new HttpPut(url);
			StringEntity entity = new StringEntity(requestStr);
			
			httpPut.setEntity(entity);
			httpPut.setHeader("Accept", "application/json");
			httpPut.setHeader("Content-type", "application/json; charst=UTF-8");
			httpPut.setHeader("Accept-Charset", "UTF-8");
			CloseableHttpResponse response = client.execute(httpPut);
			HttpEntity responseEntiry = response.getEntity();
			String responseString = EntityUtils.toString(responseEntiry, "UTF-8");
			ifLog.setResDate(DateUtil.sysdate());
			ifLog.setResMsg(responseString);
			logger.debug("############ Response JSON : \n" + responseString);
			res = mapper.readValue(responseString, AppResponseVO.class);
			ifLog.setMsgcode(res.getHeader().getMsgcode());
			ifLog.setMessage(res.getHeader().getMessage());
		} catch (Exception e){
			throw new ServiceException("MSG.M08.MSG00013");
		} finally{
			appLogService.nsSaveInsertLog(ifLog);
			
		}
		return res;
	}

	
}
