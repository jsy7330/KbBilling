package com.ntels.ccbs.common.interceptor;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ntels.ccbs.common.util.MaskUtil;
import com.ntels.ccbs.system.domain.common.service.CommonVO;
import com.ntels.ccbs.system.domain.common.service.MaskRule;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.MaskInterceptorService;

/**
 * <PRE>
 * 1. ClassName: MaskInterceptor
 * 2. FileName : MaskInterceptor.java
 * 3. Package  : com.ntels.ccbs.common.interceptor
 * 4. Comment  : 마스크 처리 공통 모듈
 * 				TSYCO_MASK_RULE에 정의된 형식 대로 Model객체에 담긴 데이터들 마스크 처리 Interceptor
 * 5. 작성자   : JHYun
 * 6. 작성일   : 2016. 3. 10. 오전 10:11:56
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     JHYun :    2016. 3. 10.    : 신규개발
 * </PRE>
 */
public class MaskInterceptor extends HandlerInterceptorAdapter {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private MaskInterceptorService maskService;
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(modelAndView == null){
			super.postHandle(request, response, handler, modelAndView);
			return;
		}
		
		HttpSession httpSession =  request.getSession();
		SessionUser sessionUser = (SessionUser)httpSession.getAttribute("session_user");
		if(sessionUser == null){
			super.postHandle(request, response, handler, modelAndView);
			return;
		}
		
		Map<String,Object> model = modelAndView.getModel();
		if(model == null){
			super.postHandle(request, response, handler, modelAndView);
			return;
		}
		
		
		//마스크 해제 On인경우에 통과
		String isUnMaskYn = request.getParameter("isUnmaskYn");
		if("Y".equals(isUnMaskYn)){
			super.postHandle(request, response, handler, modelAndView);
			return;
		}
		//해당 사업자의 Rule정보가 없으면 통과
		Map<String,MaskRule> maskRuleInfo = maskService.getMaskRuleList(sessionUser.getSoId()); 
		if(maskRuleInfo == null){
			super.postHandle(request, response, handler, modelAndView);
			return;
		}

		//각 Model에서 Object 추출 후 대상 체크
		Set<String> keys = model.keySet();
		for(String key : keys){
			Object object = model.get(key);
            if(object instanceof String){
           		model.put(key, maskString(object, key, maskRuleInfo));
            }else if(object instanceof Collection){
            	maskCollection(object, maskRuleInfo);
            }else if(object instanceof Map){
            	maskMapObject(object, maskRuleInfo);
            }else if(object instanceof CommonVO){
            	maskObject(object, maskRuleInfo);
            }
		}
		super.postHandle(request, response, handler, modelAndView);
	}
	
	private String maskString(Object object, String key, Map<String,MaskRule> maskRuleInfo){
		String str = (String)object;
		if(str == null || str.isEmpty()) return str;
    	//String이면 해당 키에 일치하면 변경
		String camelCaseKey = MaskUtil.convert2CamelCase(key);
		if(maskRuleInfo.containsKey(camelCaseKey)){
			try{
				MaskRule rule = maskRuleInfo.get(camelCaseKey);
				str = MaskUtil.converToMask(object.toString(), rule.getStartMaskIndex(), rule.getEndMaskIndex(), rule.getMaskChar());
				logger.debug("Masked Processing String Type : Key={}, Value={}",key, str);
			}catch(Exception e){
				logger.debug("Masked Processing Error : Key={}, Value={}",key, object);
				return str;
			}
			
		}
		return str;
		
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: maskCollection
	 * 2. ClassName : MaskInterceptor
	 * 3. Comment   : Collection 객체의 마스크 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 10. 오전 10:13:50
	 * </PRE>
	 *   @return void
	 *   @param object Collection 객체
	 *   @param maskRuleInfo 변경 Rule정보
	 */
	private void maskCollection(Object object, Map<String,MaskRule> maskRuleInfo){
		if(object == null) return;
		
		Iterator<?> items = ((Collection)object).iterator();
    	while(items != null && items.hasNext()){
    		try{
        		Object item = items.next();
        		if(item == null) continue;
        		
        		if(item instanceof Map){
        			maskMapObject(item, maskRuleInfo);
        		}else if(item instanceof CommonVO){
        			maskObject(item, maskRuleInfo);
        		}    			
    		}catch(Exception e){
    			logger.error("Masked Processing Error : {}",e.getMessage());
    		}
    	}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: maskMapObject
	 * 2. ClassName : MaskInterceptor
	 * 3. Comment   : Map객체의 마스크 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 10. 오전 10:13:16
	 * </PRE>
	 *   @return void
	 *   @param object Map객체
	 *   @param maskRuleInfo 변경 Rule정보
	 */
	private void maskMapObject(Object object, Map<String, MaskRule> maskRuleInfo){
		if(object == null) return;
		
		Map<String,Object> map = (Map<String,Object>)object;
		Set<String> keys = map.keySet();
		for(String key : keys){
			try{
				Object obj = map.get(key);
				
				if(obj == null) continue;
				
	            if(obj instanceof String){
	           		map.put(key, maskString(obj, key, maskRuleInfo));
	            }else if(obj instanceof Collection){
	            	maskCollection(obj,maskRuleInfo);
	            }else if(obj instanceof Map){
	            	maskMapObject(obj, maskRuleInfo);
	            }else if(obj instanceof CommonVO){
	            	maskObject(obj, maskRuleInfo);
	            }	
			}catch(Exception e){
				logger.error("Masked Processing Error : {}",e.getMessage());
			}
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName: maskObject
	 * 2. ClassName : MaskInterceptor
	 * 3. Comment   : VO 객체의 마스크 처리
	 * 4. 작성자    : JHYun
	 * 5. 작성일    : 2016. 3. 10. 오전 10:12:45
	 * </PRE>
	 *   @return void
	 *   @param object VO객체
	 *   @param maskRuleInfo 변경 Rule정보
	 */
	private void maskObject(Object object, Map<String,MaskRule> maskRuleInfo){
		if(object == null) return;
		
		Class<? extends Object> c = object.getClass();
		Field[] fields = c.getDeclaredFields();
		for(int i=0; i < fields.length;i++){
			try {
				fields[i].setAccessible(true);
	            Object value = fields[i].get(object);
	            
	            if(value == null){
	            	continue;
	            }
	            if(value instanceof String){
	            	fields[i].set(object, maskString(value, fields[i].getName(), maskRuleInfo));
	            }else if(value instanceof Collection){
	            	maskCollection(value, maskRuleInfo);
	            }else if(value instanceof Map){
	            	maskMapObject(value, maskRuleInfo);
	            }else if(value instanceof CommonVO){
	            	maskObject(value, maskRuleInfo);
	            }
	            
            } catch (IllegalArgumentException | IllegalAccessException e) {
            	logger.error("Masked Processing Error : {}",e.getMessage());
            } catch (Exception e){
            	logger.error("Masked Processing Error : {}",e.getMessage());
            }
		}
	}
}
