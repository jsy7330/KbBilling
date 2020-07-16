package com.ntels.ccbs.product.service.refInfo.commonInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.AttrTypMap;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;

public interface AttrTypMapService {
	public List<AttrTypMap> list(AttrTypMap attrTypMap, int page, int perPage);
	public int count(AttrTypMap attrTypMap);
	public List<Map<String, Object>> attrListAction(AttrTypMap attrTypMap);	
	public List<AttrTypMap> list2(AttrTypMap attrTypMap, int page, int perPage) ;
	public int count2(AttrTypMap attrTypMap);
	public int attrCnt(String attrCd, String attrTyp, String currentDay);
	public List<Map<String, Object>> attrDate(String attrCd);
	public boolean insert(AttrTypMap attrTypMap);
	public AttrTypMap getAttrTypMap(String attrCd, String attrTyp, String currentDay, String lngTyp);
	public boolean update(AttrTypMap attrTypMap);
}