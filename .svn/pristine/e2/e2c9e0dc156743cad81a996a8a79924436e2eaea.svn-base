package com.ntels.ccbs.product.service.refInfo.commonInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;



public interface AttributeService {
	
	public List<Attribute> list(Attribute attribute, int page, int perPage);
	public int count(Attribute attribute);
	public Attribute getAttribute(String attrCd, String lngTyp);
	public int commCnt(Attribute attribute);
	public List<Attribute> commList(Attribute attribute, int page, int perPage);
	public String getAttrCd();
	public List<Map<String, Object>> commomCdList(String commonGrp, String lngTyp);
	public boolean insert(Attribute attribute);
	public boolean update(Attribute attribute);
	public boolean update2(Attribute attribute);
}