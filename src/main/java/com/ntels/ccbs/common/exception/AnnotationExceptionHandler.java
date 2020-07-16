package com.ntels.ccbs.common.exception;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ntels.ccbs.appIf.domain.common.AppRequestVO;
import com.ntels.ccbs.appIf.domain.common.AppResHeaderVO;
import com.ntels.ccbs.appIf.domain.common.AppResponseVO;
import com.ntels.ccbs.appIf.domain.common.IfLogVO;
import com.ntels.ccbs.appIf.service.common.AppLogService;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.nisf.util.message.MessageUtil;

@ControllerAdvice
public class AnnotationExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "exception/exception";
	
	/**
	 * APP 연동 URL
	 */
	public static final String APP_IF_URL = "/appIf";

	@Autowired
	private AppLogService appLogService;
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        HttpSession s = req.getSession();
        // Otherwise setup and send the user to a default error-view.
        
        logger.error(e.getMessage(), e);
        
        /*
         * APP 요청 여부 판단 
         */
        boolean isAppRequest = false;
        if(req.getPathInfo().startsWith(APP_IF_URL)){
        	isAppRequest = true;
		}
        
        /*
         * APP 요청에 대한 에러 메세지 세팅
         */
        if(isAppRequest){
        	
        	ModelAndView mav = new ModelAndView();
        	
        	boolean isMulipart = isMultipart(req);
        	
        	
    		StringBuffer jb = new StringBuffer();
			String line = null;
			
			String svcode = null;
			String opcode = null;
			String chcode = null;
				
			try{
				BufferedReader reader = req.getReader();
				while ((line = reader.readLine()) != null && isMulipart == false)
					jb.append(line);
				
				if(isMulipart){
					jb.append((String)req.getParameter("JSON"));
				}
				ObjectMapper mapper = new ObjectMapper();
				AppRequestVO request = mapper.readValue(jb.toString(), AppRequestVO.class);
				
				svcode = request.getHeader().getSvcode();
				opcode = request.getHeader().getOpcode();
				chcode = request.getHeader().getChcode();
				
			} catch (Exception bfe) {
				logger.error(bfe.getMessage());
			}
			
			
			AppResponseVO response = new AppResponseVO();
        	AppResHeaderVO appResHeader = new AppResHeaderVO();
        	
        	appResHeader.setSvcode(StringUtils.isEmpty(svcode) == true ? "" : svcode);
        	appResHeader.setOpcode(StringUtils.isEmpty(opcode) == true ? "" : opcode);
        	appResHeader.setChcode(StringUtils.isEmpty(chcode) == true ? "" : chcode);
    		if(e instanceof AppException){
            	appResHeader.setMsgcode(((AppException) e).getMessageId());
            	appResHeader.setMessage(e.getMessage());
            	res.setStatus(HttpServletResponse.SC_OK);
    		}else{
    			appResHeader.setMsgcode("E0003");
            	appResHeader.setMessage(MessageUtil.getMessage("MSG.IF.E0003"));
            	res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    		}
        	
        	response.setHeader(appResHeader);
        	mav.addObject("RESPONSE",response);
        	
        	
    		/**
    		 * 연동 로그 기록
    		 */
    		
    		IfLogVO appLog = (IfLogVO)req.getAttribute("IF_LOG");
    		if(appLog != null){
    			try{
        			ObjectMapper mapper = new ObjectMapper();
        			
        			appLog.setSvCode(svcode);
        			appLog.setOpCode(opcode);
        			appLog.setChCode(chcode);
        			appLog.setReqMsg(StringUtils.isEmpty(jb.toString()) ? "" : jb.toString()  );
        			
        			
        			String responseStr = mapper.writeValueAsString(mav.getModel().get("RESPONSE"));
        			appLog.setMsgcode(response.getHeader().getMsgcode());
        			appLog.setMessage(response.getHeader().getMessage());
        			appLog.setResMsg(responseStr);
        			appLog.setResDate(DateUtil.sysdate());
        			
            		logger.debug("APP RESPONSE : " + responseStr);
        			
    			}catch(Exception loge){
    				logger.error(loge.getMessage());
    			}
    			
    			appLogService.nsSaveInsertLog(appLog);
    		}
        	
        	return mav;
        	
        }else{
        	
        	ModelAndView mav = new ModelAndView();
            if(e != null && !StringUtils.isEmpty(e.getMessage())){
            	if(e instanceof ServiceException){
            		mav.addObject("exceptionMsg", e.getMessage());
            	}else{
            		String msg = MessageUtil.getMessage("MSG.M15.MSG00001");
            		mav.addObject("exceptionMsg", msg);
            	}
            	
            }else{
            	mav.addObject("exceptionMsg", MessageUtil.getMessage("MSG.M15.MSG00001"));
            }
            
            mav.addObject("requestUrl", req.getRequestURL());
            mav.addObject("pageUrl", req.getSession().getAttribute("viewPath"));
            mav.setViewName(DEFAULT_ERROR_VIEW);
            
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return mav;
            
        }
        
        
    }
    
    
    private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }
    
}