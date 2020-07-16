package com.ntels.ccbs.system.service.common.service;

import com.ntels.ccbs.system.domain.common.service.MenuAccessHistory;

/**
 * 이력 인터셉터 Service.
 *
 * @author smyun@ntels.com
 */
public interface HistoryInterceptorService {

	/**
	 * 이력 저장.
	 *
	 * @param history 메뉴접속이력
	 */
	public void save(MenuAccessHistory history);
}
