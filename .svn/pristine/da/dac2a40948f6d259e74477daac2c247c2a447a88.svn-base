package com.ntels.ccbs.product.service.refInfo.commonInfo.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.product.mapper.refInfo.commonInfo.AttrTypMapMapper;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.AttrTypMap;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.product.service.refInfo.commonInfo.AttrTypMapService;
import com.ntels.nisf.util.StringUtil;


/**
 * 상품속성 서비스 Service.
 *
 * @author ekyun@ntels.com
 */

@Service
public class AttrTypMapServiceImpl implements AttrTypMapService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private AttrTypMapMapper attrTypMapMapper;

	
	public List<AttrTypMap> list(AttrTypMap attrTypMap, int page, int perPage) {		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return attrTypMapMapper.list(attrTypMap, start,  end);
	}

	public int count(AttrTypMap attrTypMap) {
		return attrTypMapMapper.count(attrTypMap);
	}
	
	public List<Map<String, Object>> attrListAction(AttrTypMap attrTypMap){
		return attrTypMapMapper.attrListAction(attrTypMap);
	}	
	public List<AttrTypMap> list2(AttrTypMap attrTypMap, int page, int perPage) {		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return attrTypMapMapper.list2(attrTypMap, start,  end);
	}	
	public int count2(AttrTypMap attrTypMap) {
		return attrTypMapMapper.count2(attrTypMap);
	}
	public int attrCnt(String attrCd, String attrTyp, String currentDay) {
		return attrTypMapMapper.attrCnt(attrCd, attrTyp, currentDay);
	}
	public List<Map<String, Object>> attrDate(String attrCd){
		return attrTypMapMapper.attrDate(attrCd);
	}	
	public boolean insert(AttrTypMap attrTypMap) {
		return (attrTypMapMapper.insert(attrTypMap) > 0);
	}
	public AttrTypMap getAttrTypMap(String attrCd, String attrTyp, String currentDay, String lngTyp) {
		return attrTypMapMapper.getAttrTypMap(attrCd, attrTyp, currentDay, lngTyp);
	}	
	public boolean update(AttrTypMap attrTypMap){
		return (attrTypMapMapper.update(attrTypMap) > 0);
	}
}