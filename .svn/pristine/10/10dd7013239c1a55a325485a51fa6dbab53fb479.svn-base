package com.ntels.ccbs.system.service.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.system.domain.common.service.MenuAccessHistory;
import com.ntels.ccbs.system.mapper.common.service.HistoryInterceptorMapper;
import com.ntels.ccbs.system.service.common.service.HistoryInterceptorService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

/**
 * 이력 인터셉터 Service.
 *
 * @author smyun@ntels.com
 */
@Service
public class HistoryInterceptorServiceImpl implements HistoryInterceptorService {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HistoryInterceptorMapper historyInterceptorMapper;
	
	/**
	 * 이력 저장.
	 *
	 * @param history 메뉴접속이력
	 */
	@SuppressWarnings("unchecked")
	public void save(MenuAccessHistory history) {
		try{
			historyInterceptorMapper.insert(history);
		}catch(Exception e) {
			logger.info("historyInterceptorMapper.insert fail \n {}", e);
		}
	}
}
