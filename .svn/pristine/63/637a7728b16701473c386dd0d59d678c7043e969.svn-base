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

import com.ntels.ccbs.product.mapper.refInfo.commonInfo.AttributeMapper;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;
import com.ntels.ccbs.product.service.refInfo.commonInfo.AttributeService;
import com.ntels.nisf.util.StringUtil;


/**
 * 상품속성 서비스 Service.
 *
 * @author ekyun@ntels.com
 */

@Service
public class AttributeServiceImpl implements AttributeService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** AttributeMapper Autowired. */
	@Autowired
	private AttributeMapper attributeMapper;

	@Override
	public List<Attribute> list(Attribute attribute, int page, int perPage) {		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return attributeMapper.list(attribute, start,  end);
	}
	
	@Override
	public int count(Attribute attribute) {
		return attributeMapper.count(attribute);
	}
	
	@Override
	public Attribute getAttribute(String attrCd, String lngTyp) {
		System.out.println("ServiceImpl lngTyp==>"+lngTyp);
		return attributeMapper.getAttribute(attrCd, lngTyp);
	}	
	
	@Override
	public int commCnt(Attribute attribute) {
			return attributeMapper.commCnt(attribute);
	}
	
	@Override
	public List<Attribute>  commList(Attribute attribute, int page, int perPage){
		int start = 0;
		int end = 0;
		start = (page-1)*perPage;
		end = perPage;
		return attributeMapper.commList(attribute, start,  end);	
	}
	
	@Override
	public String getAttrCd() {
		return attributeMapper.getAttrCd();
	}
	
	@Override
	public List<Map<String, Object>> commomCdList(String commonGrp, String lngTyp){
		return attributeMapper.commomCdList(commonGrp, lngTyp);
	}	
	
	@Override
	public boolean insert(Attribute attribute){
		return (attributeMapper.insert(attribute) > 0);
	}	
	
	@Override
	public boolean update(Attribute attribute){
		return (attributeMapper.update(attribute) > 0);
	}
	
	@Override
	public boolean update2(Attribute attribute){
		return (attributeMapper.update2(attribute) > 0);
	}
}