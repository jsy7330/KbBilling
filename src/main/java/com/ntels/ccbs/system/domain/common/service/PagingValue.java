package com.ntels.ccbs.system.domain.common.service;

import javax.annotation.Resource;

/**
 * 페이징 변수.
 * int page=1; 현재 페이지<br>
 * int perPage=10; 페이지당 보일 오브젝트 갯수.<br>
 * int perSize=5; 페이지 블록 크기 [예]  << < 12345 > >>
 * 
 * @author smyun@ntels.com
 */
@Resource
public class PagingValue {
	
	/** 페이지. */
	private Integer page;
	
	/** 페이지당 리스트. */
	private Integer perPage;
	
	/** 페이지당 블럭. */
	private Integer perSize;

	public Integer getPerSize() {
		return perSize;
	}

	public void setPerSize(Integer perSize) {
		this.perSize = perSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPerPage() {
		return perPage;
	}

	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}	
}
