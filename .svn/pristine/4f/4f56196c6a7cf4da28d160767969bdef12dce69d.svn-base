package com.ntels.ccbs.system.domain.common.service;

import java.util.List;

import com.ntels.nisf.util.PropertiesUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Paging을 위한 클래스 .
 * <br>
 * List lists; 해당 오브젝트 리스트를 담는다.<br>
 * int totalCount; 전체 갯수<br>
 * 
 * @author smyun@ntels.com
 * @version 1.0.0
 */
@XStreamAlias("paging")
@SuppressWarnings("rawtypes")
public class Paging extends PagingValue {
	
	/** 목록. */
	private List lists;
	
	/**
	 * 총 갯수.
	 */
	private int totalCount;
	

    /**
     * 생성자.
     */
	public Paging() {
		super();
		setInit(1, Integer.parseInt(PropertiesUtil.get("config", "paging.per_page")));
	}    
    
	/**
	 * 생성자.
	 * 
	 * @param page 현재페이지
	 * @param perPage 페이지목록수
	 */
	public Paging(int page, Integer perPage) {
		super();
		setInit(page, perPage);
	}
	
	/**
	 * 초기화.
	 * 
	 * @param page 페이지
	 * @param perPage 페이지당리시트
	 */
	private void setInit(int page, Integer perPage){
		setPage(page);
	    setPerPage(perPage);
	    setPerSize(Integer.parseInt(PropertiesUtil.get("config", "paging.per_size")));
	}

	public List getLists() {
		return lists;
	}
	public void setLists(List lists) {
		this.lists = lists;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
