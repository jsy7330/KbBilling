package com.ntels.ccbs.common.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ntels.nisf.util.message.MessageUtil;

/**
 * 리스트 당 보여질 갯수 설정에 사용되는  Tag Lib Class.
 * 
 * @author smyun@ntels.com
 */
public class PerPageControlTag extends BodyTagSupport {

	/** BodyTagSupport serialVersionUID. */
	private static final long serialVersionUID = 6762304057183908836L;

	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 현재 페이지. */
	private String page;
	
	/** 페이지당 보여질 리스트 수. */
	private String perPage;
	
	/** 총 갯수. */
	private Integer totalCount;
	
	/** 설정 앞부분. */
	private final static String HEADER 
		= "  	<div class=\"fr btnArea small position_re\"> "
		+ "			<input type=\"hidden\" id=\"perPage\" name=\"perPage\" value=\"@perPage@\"/>"
		+ "			<span class=\"inline\"><i class=\"iconSet icon_pagego\" title=\"pagego\"></i></span>"
		+ "			<input type=\"text\" id=\"page\" name=\"page\" class=\"inp\" style=\"width:40px;\" maxlength=\"10\" value=\"@page@\"> <a href=\"javascript:goSearch();\" class=\"atem_btnstyle white\" title=\"go\">GO</a>"
		+ " 				&nbsp;&nbsp;&nbsp;"		
		+ "			<span class=\"inline\">@totalCount@ row(s)</span>&nbsp;&nbsp;"
		+ "			<span class=\"inline\"><i class=\"iconSet icon_list\"></i></span>"
		+ "				<a id=\"currentPerPage\" href=\"#\" class=\"arr\">@perPage@ @line@<i class=\"mimiArrowIcon arrow_down_b mgL08\"></i></a><!--hidden or show-->"
		+ "				<ul id=\"selectPerPage\" class=\"select_list position_ab\">";
	
	/** 설정 뒷부분. */
	private final static String FOOTER 	
		= "				</ul>"
		+ "		    </span>"
		+ "		</div>";
	
	@Override
	public int doEndTag() throws JspException {
		
		try {
			String line=MessageUtil.getMessage("LAB.M15.LAB00054");
			StringBuilder body = new StringBuilder();
			body.append(HEADER.replaceAll("@page@",page)
						 	  .replaceAll("@perPage@",perPage)
						 	  .replaceAll("@totalCount@",String.valueOf((totalCount < 0) ? 0: totalCount))
						 	  .replaceAll("@line@",line));

	    	BodyContent bodyContent = getBodyContent();
	    	StringBuilder listString = new StringBuilder();
	    	if (bodyContent!=null) {
	    		String[] list = bodyContent.getString().split(",");
	    		for(int i=0;i<list.length;i++) {
	    			listString.append("					<li><a href=\"javascript:setPerPage('")
	    					  .append(list[i].trim())
	    					  .append("');\">")
	    					  .append(list[i].trim())
	    					  .append(" "+line)
	    					  .append("</a></li>\n");
	    		}
	    	}
	    	body.append(listString);
			body.append(FOOTER);
			
			pageContext.getOut().print(body.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    	
		return SKIP_BODY;
	}

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPerPage() {
		return perPage;
	}
	public void setPerPage(String perPage) {
		this.perPage = perPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(final Integer totalCount) {
		this.totalCount = totalCount;
	}
}
