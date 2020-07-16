package com.ntels.ccbs.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.consts.WorkTypeDefinition;
import com.ntels.ccbs.common.filter.CommandInjectionWrapper;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.MenuAccessHistory;
import com.ntels.ccbs.system.domain.common.service.SelectedMenu;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.HistoryInterceptorService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

/**
 * History 데이터 입력을 위한 Interceptor.
 * 
 * @author smyun@ntels.com
 */
public class HistoryInterceptor extends HandlerInterceptorAdapter {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** HistoryInterceptorService Autowired. */
	@Autowired
	private HistoryInterceptorService historyInterceptorService;
	
	@Autowired
	private SequenceService sequenceSevice;
	
	/**
	 *  인터페이스 URL
	 */
	private List<String>  listAppUrl;
	
	public List<String> getListAppUrl() {
		return listAppUrl;
	}

	public void setListAppUrl(List<String> listAppUrl) {
		this.listAppUrl = listAppUrl;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		/**
		 * 인터페이스 URL은 제외 처리
		 */
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				return true;
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		/**
		 * 인터페이스 URL은 제외 처리
		 */
		for(String path : listAppUrl){
			if(request.getPathInfo().startsWith(path)){
				return;
			}
		}
		
		String user_id = "";
		SelectedMenu selectedMenu = null;

		HttpSession session = request.getSession(false);
		Object obj = session == null ? null : session.getAttribute("session_user");

		try {
			// 세션 검사 후 없다면 redirectPage로 이동
			if (obj != null) {
				user_id = ((SessionUser) obj).getUserId();
				if (user_id != null && !user_id.isEmpty()) {

					String pathInfo = request.getPathInfo();

					MenuAccessHistory menuAccessHistory = new MenuAccessHistory();
					/*
					 * 작업유형 세팅
					 */
					if ((pathInfo.contains("list") || pathInfo.contains("List") || pathInfo.contains("get")) && pathInfo.contains("Action")) {
						menuAccessHistory.setWorkType(WorkTypeDefinition.WORK_TYPE_SEARCH);
					} else if ((pathInfo.contains("insert") || pathInfo.contains("Insert")) && pathInfo.contains("Action")) {
						menuAccessHistory.setWorkType(WorkTypeDefinition.WORK_TYPE_INSERT);
					} else if ((pathInfo.contains("update") || pathInfo.contains("Update")) && pathInfo.contains("Action")) {
						menuAccessHistory.setWorkType(WorkTypeDefinition.WORK_TYPE_UPDATE);
					} else if ((pathInfo.contains("delete") || pathInfo.contains("Delete")) && pathInfo.contains("Action")) {
						menuAccessHistory.setWorkType(WorkTypeDefinition.WORK_TYPE_DELETE);
					} else {
						menuAccessHistory.setWorkType(WorkTypeDefinition.WORK_TYPE_MOVE_PAGE);
					} 
					
					menuAccessHistory.setWorkDt(DateUtil.getDateStringYYYYMMDD(0));
					menuAccessHistory.setSeq(sequenceSevice.createNewSequenceDaily(Consts.SEQ_CODE.SEQ_TSYCO_WORK_HIST));
					menuAccessHistory.setUserId(user_id);
					menuAccessHistory.setWorkDate(DateUtil.sysdate());
					menuAccessHistory.setServerName(request.getServerName() == null ?
							"" : request.getServerName().length() > 128 ?
									request.getServerName().substring(0, 128) : request.getServerName());
					menuAccessHistory.setServerPort(request.getServerPort());
					menuAccessHistory.setAccept(request.getHeader("accept") == null ?
							"" : request.getHeader("accept").length() > 128 ? 
									request.getHeader("accept").substring(0, 128) : request.getHeader("accept"));
					menuAccessHistory.setUserAgent(request.getHeader("user-agent") == null ?
							"" : request.getHeader("user-agent").length() > 128 ?
									request.getHeader("user-agent").substring(0, 128) : request.getHeader("user-agent"));
					menuAccessHistory.setContentType(request.getHeader("content-type") == null ?
							"" : request.getHeader("content-type").length() > 128 ?
									request.getHeader("content-type").substring(0, 128) : request.getHeader("content-type"));
					menuAccessHistory.setRemoteAddr(request.getRemoteAddr() == null ?
							"" : request.getRemoteAddr().length() > 128 ?
									request.getRemoteAddr().substring(0, 128) : request.getRemoteAddr());
					menuAccessHistory.setSessionId(request.getRequestedSessionId() == null ?
							"" : request.getRequestedSessionId().length() > 128 ?
									request.getRequestedSessionId().substring(0, 128) : request.getRequestedSessionId());
					menuAccessHistory.setRequestPath(request.getPathInfo() == null ?
							"" : request.getPathInfo().length() > 256 ?
									request.getPathInfo().substring(0, 256) : request.getPathInfo());
					menuAccessHistory.setRequestMethod(request.getMethod() == null ? 
							"" : request.getMethod().length() > 32 ?
									request.getMethod().substring(0, 32) : request.getMethod());
					
					Object oSelectedMenu = request.getSession().getAttribute("selectedMenu");
					if (oSelectedMenu != null) {
						selectedMenu = (SelectedMenu) oSelectedMenu;
						if(StringUtils.isEmpty(selectedMenu.getSelectMenuNo())){
							menuAccessHistory.setMenuNo("");
						}else{
							menuAccessHistory.setMenuNo(selectedMenu.getSelectMenuNo() == null ? "" : selectedMenu.getSelectMenuNo());
						}
					}else{
						menuAccessHistory.setMenuNo("");
					}
					String payload = logRequest(request);
					menuAccessHistory.setPayload(payload == null ?
							"" : payload.length() > 2000 ? payload.substring(0, 2000) : payload);
					historyInterceptorService.save(menuAccessHistory);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		super.postHandle(request, response, handler, modelAndView);
	}
	
  private String logRequest(final HttpServletRequest request) {
        StringBuilder msg = new StringBuilder();
        if ("POST".equalsIgnoreCase(request.getMethod()) && !isMultipart(request) && !isBinaryContent(request)) {
            try {
            	CommandInjectionWrapper wrapper = (CommandInjectionWrapper) request;
            	Map<String,String> parameters = wrapper.parameterMap();
                msg.append(parameters.toString());
            } catch (Exception e) {
                logger.warn("Failed to parse request payload", e);
            }
        }
        return msg.toString();
    }
	  
    private boolean isBinaryContent(final HttpServletRequest request) {
        if (request.getContentType() == null) {
            return false;
        }
        return request.getContentType().startsWith("image") || request.getContentType().startsWith("video") || request.getContentType().startsWith("audio");
    }

    private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }
}
