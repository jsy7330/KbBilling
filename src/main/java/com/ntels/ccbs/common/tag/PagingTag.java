package com.ntels.ccbs.common.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ntels.nisf.util.message.MessageUtil;

/**
 * 페이징 처리에 사용되는 Tag Lib Class.
 * 
 * @author smyun@ntels.com
 */
@Component
public class PagingTag extends TagSupport {
	/** Logger for this class and subclasses. */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** TagSupport serialVersionUID. */
	private static final long serialVersionUID = -1794041546244821417L;

	/** ajax 여부. */
	private boolean is_ajax=false;

	/** ajax 사용일 경우 javascript. */ 
	private String ajax_method="goPostPage";
	/** ajax 사용일 경우 요청 url. */
	private String ajax_url;
	/** ajax 사용일 경우 결과를 받을 div 아이디 지정. */
	private String ajax_target;
	
	/** 페이지 클릭 시 이동 경. */
	private String href;
	
	/** 현재 페이지. */
	private int active=1;
	
	
	/** Key 리스트. */
	private String parameterKeys;
	/** Value 리스트. */
	private String parameterValues;
	
	/** 한 페이지 안에 몇 줄을 보여줄 것인지 기술. */
	private @Value("${paging.per_page}") Integer per_page;
	/** 한 화면에 몇 개의 페이지이동 버튼을 보여줄 것인지 기술. */
    private @Value("${paging.per_size}") Integer per_size;
	/** 전체 갯. */
	private int total_count;
	
	@Override
    public int doStartTag()  {

    	String result = "";

		int yardStick = (int) Math.ceil(((double)active / (double)per_size));
		int start = ((yardStick-1) * per_size)+1;
		int end = ((yardStick) * per_size);
		int lastPage = (int) Math.ceil(((double)total_count / (double)per_page));
		
		if (!is_ajax) {
			result = makeUrl(start,end,lastPage);
		} else {
			result = makeAjax(start,end,lastPage);
		}
		try {
			pageContext.getOut().print(result);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
        return SKIP_BODY;
   
		
    }

	/**
	 * java script 호출 ajax 페이징 제작.
	 * 
	 * @param start 시작
	 * @param end 종료
	 * @param lastPage 최종페이지
	 * @return String
	 */
    private String makeAjax(int start, int end, int lastPage) {
    	
    	//첫 페이지 이동 레이블
    	String paging_first = MessageUtil.getMessage("LAB.M05.LAB00017");
    	//이전 10 페이지 레이블
    	String paging_pre = MessageUtil.getMessage("LAB.M08.LAB00123");
    	//다음 10 페이지 레이블
    	String paging_next = MessageUtil.getMessage("LAB.M03.LAB00005");
    	//마지막 페이지 이동 레이블
    	String paging_end = MessageUtil.getMessage("LAB.M05.LAB00002");    	
    	
    	
    	if (ajax_target==null || ajax_target.isEmpty())return "";

    	StringBuilder result = new StringBuilder("<div class=\"paging center\">");
		if (start>1) {
			result.append("<ul><li> <a href=\"javascript:").append(ajax_method)
					.append("('").append(ajax_target).append("','")
					.append(ajax_url).append("',1,").append(per_page)
					.append(',').append(per_size).append(',')
					.append(parameterKeys)
					.append(");\" class=\"arr\" title=\"" )
					.append(paging_first).append("\"><i class=\"icon_first\"></i></a></li></ul> ")
					.append("<ul><li><a href=\"javascript:").append(ajax_method)
					.append("('").append(ajax_target).append("','")
					.append(ajax_url).append("',").append(start - 1)
					.append(',').append(per_page).append(',')
					.append(per_size + ",").append(parameterKeys)
					.append(");\" class=\"arr\" title=\"")
					.append(paging_pre + "\"> <i class=\"icon_pre\"></i></a> </li></ul>");
		}
		
		
		for(int i=start;i<=end;i++) {
			if ( i <=lastPage) {
				if (i==active) {
					result.append(" <ul><li> <a href=\"#\" class=\"active\">").append(i)
							.append(" </a> </li></ul>");
				} else {
					result.append(" <ul><li> <a href=\"javascript:").append(ajax_method)
							.append("('").append(ajax_target).append("','")
							.append(ajax_url).append("',").append(i)
							.append(',').append(per_page).append(',')
							.append(per_size).append(',').append(parameterKeys)
							.append(");\" >").append(i).append("</a> </li></ul>");
				}
			}
		}
		if (end<lastPage) {
			result.append("<ul><li> <a href=\"javascript:").append(ajax_method)
					.append("('").append(ajax_target).append("','")
					.append(ajax_url).append("',").append(end + 1).append(',')
					.append(per_page).append(',').append(per_size).append(',')
					.append(parameterKeys)
					.append(");\" class=\"arr\" title=\"")
					.append(paging_next).append("\"><i class=\"icon_next\"></i></a> </li></ul>");
			result.append("<ul><li> <a href=\"javascript:").append(ajax_method)
					.append("('").append(ajax_target).append("','")
					.append(ajax_url).append("',").append(lastPage).append(',')
					.append(per_page).append(',').append(per_size).append(',')
					.append(parameterKeys)
					.append(");\" class=\"arr\" title=\"")
					.append(paging_end).append("\"><i class=\"icon_end\"></i></a> </li></ul>");
		}
		result.append("</div>");
		
		return result.toString();
    }
    
    /**
     * url 페이지 이동 페이징 제작.
     * 
     * @param start 시작
     * @param end 종료
     * @param lastPage 최종페이지
     * @return String
     */
    private String makeUrl(int start, int end, int lastPage) {
    	
    	//첫 페이지 이동 레이블
    	String paging_first = MessageUtil.getMessage("LAB.M05.LAB00017");
    	//이전 10 페이지 레이블
    	String paging_pre = MessageUtil.getMessage("LAB.M08.LAB00123");
    	//다음 10 페이지 레이블
    	String paging_next = MessageUtil.getMessage("LAB.M03.LAB00005");
    	//마지막 페이지 이동 레이블
    	String paging_end = MessageUtil.getMessage("LAB.M05.LAB00002");      	
    	
    	StringBuilder result = new StringBuilder("<div class=\"paging\">");

		String extraParameter = "";

		if (parameterKeys!=null) {
			String[] splitParameterKeys=parameterKeys.split(",");
			String[] splitParameterValues=parameterValues.split(",");
			for(int i=0;i<splitParameterKeys.length;i++) {
				if (splitParameterKeys[i]!=null && splitParameterValues[i]!=null) {
					String tempKey=splitParameterKeys[i]+"";
					String tempValue=splitParameterValues[i]+"";
					extraParameter+=("&"+tempKey+"="+tempValue);
				}
			}
			if ("&=".equals(extraParameter))extraParameter="";
		}
		
		if (href.contains("?")) {
			href = href +"&";
		} else {
			href = href +"?";
		}
		
		if (start>1) {
			result.append("<ul><li> <a href=\"").append(href).append("page=").append(1)
					.append("&perPage=").append(per_page).append("&perSize=")
					.append(per_size).append(extraParameter)
					.append("\" title=\"")
					.append("<").append("\"></a> ")
					.append("<a href=\"").append(href).append("page=")
					.append(start - 1).append("&perPage=").append(per_page)
					.append("&perSize=").append(per_size)
					.append(extraParameter)
					.append("\" class=\"paging_pre\" title=\"")
					.append(paging_pre).append("\"></a> </li></ul>");
		}
		
		for(int i=start;i<=end;i++) {
			if ( i <=lastPage) {
				if (i==active) {
					result.append(" <ul><li> <a href=\"#\" class=\"active\">").append(i)
							.append("</a> </li></ul>");
				} else {
					result.append(" <ul><li> <a href=\"").append(href).append("page=")
							.append(i).append("&perPage=").append(per_page)
							.append("&perSize=").append(per_size)
							.append(extraParameter).append("\" >").append(i)
							.append("</a> </li></ul>");
				}
			}
		}

		if (end<lastPage) {
			result.append("<ul><li> <a href=\"").append(href).append("page=")
					.append(end + 1).append("&perPage=").append(per_page)
					.append("&perSize=").append(per_size)
					.append(extraParameter)
					.append("\"  title=\"")
					.append(paging_next).append("\">next</a> </li></ul>")
					.append("<a href=\"").append(href).append("page=")
					.append(lastPage).append("&perPage=").append(per_page)
					.append("&perSize=").append(per_size)
					.append(extraParameter)
					.append("\" class=\"paging_end\" title=\"")
					.append(paging_end).append("\"></a> </li></ul>");
		}
		
		result.append("</div>");
		return result.toString();
    }
    
    
	public boolean isIs_ajax() {
		return is_ajax;
	}
	public void setIs_ajax(boolean is_ajax) {
		this.is_ajax = is_ajax;
	}
	public String getAjax_method() {
		return ajax_method;
	}
	public void setAjax_method(String ajax_method) {
		this.ajax_method = ajax_method;
	}
	public String getAjax_url() {
		return ajax_url;
	}
	public void setAjax_url(String ajax_url) {
		this.ajax_url = ajax_url;
	}
	public String getAjax_target() {
		return ajax_target;
	}
	public void setAjax_target(String ajax_target) {
		this.ajax_target = ajax_target;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getPer_page() {
		return per_page;
	}
	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}
	public int getPer_size() {
		return per_size;
	}
	public void setPer_size(int per_size) {
		this.per_size = per_size;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public String getParameterKeys() {
		return parameterKeys;
	}

	public void setParameterKeys(String parameterKeys) {
		this.parameterKeys = parameterKeys;
	}

	public String getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(String parameterValues) {
		this.parameterValues = parameterValues;
	}

}
