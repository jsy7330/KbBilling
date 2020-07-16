package com.ntels.ccbs.product.mapper.refInfo.commonInfo;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;


/**
 * The Interface AttributeMapper.
 */
@Component
public interface AttributeMapper {

	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attribute
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	List<Attribute> list(
			@Param(value = "attribute") Attribute attribute
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );		
	
	/**
	 * Count.
	 *
	 * @param attribute
	 * @return the int
	 * 
	 * 설명 : 속성 목록수
	 */
	int count(@Param(value = "attribute") Attribute attribute);
	
	/**
	 * Gets the attribute.
	 * 
	 * @param attrCd
	 * @return the attribute
	 * 
	 * 설명 : 속성 상세 정보 조회
	 */
	Attribute getAttribute(@Param("attrCd") String attrCd, @Param("lngTyp") String lngTyp);

	/**
	 * Update.
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 수정 
	 */
	int update(Attribute attribute);
	
	/**
	 * Update.
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 기존에 등록된 속성 값 ACT_DT가 오늘 혹은 미래 날짜 일 때 ACT_DT, INACT_DT, MSTR_FL을 제외 한 나머지 값만 업데이트 
	 */
	int update2(Attribute attribute);	
	
	/**
	 * insert.
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int insert(Attribute attribute);	
	
	/**
	 * commList.
	 *
	 * @param  commonGrpNm
	 * @return the list
	 * 
	 * 설명 : 공통코드 조회
	 */
	List<Attribute> commList(
			@Param(value = "attribute") Attribute attribute
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );	
	
	/**
	 * Count.
	 *
	 * @param attribute
	 * @return the int
	 * 
	 * 설명 : 공통코드 목록수
	 */
	int commCnt(@Param(value = "attribute") Attribute attribute);
	
	/**
	 * getAttrCd.
	 *
	 * @param  
	 * @return the String
	 * 
	 * 설명 : 속성 ATTR_CD sequence 조회
	 */
	String getAttrCd();
	
	/**
	 * commomCdList.
	 *
	 * @param common_grp 
	 * @return the list
	 * 
	 * 설명 : 공통코드 조회
	 */
	List<Map<String, Object>> commomCdList(@Param("commonGrp") String commonGrp, @Param("lngTyp") String lngTyp);	
}