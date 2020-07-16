package com.ntels.ccbs.product.mapper.refInfo.commonInfo;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.product.domain.refInfo.commonInfo.AttrTypMap;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Attribute;

/**
 * The Interface AttrTypMapMapper.
 */
@Component
public interface AttrTypMapMapper {
	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attrTypMap
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	List<AttrTypMap> list(
			@Param(value = "attrTypMap") AttrTypMap attrTypMap
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );		
	
	/**
	 * Count.
	 *
	 * @param attrTypMap
	 * @return the int
	 * 
	 * 설명 : 속성 목록수
	 */
	int count(@Param(value = "attrTypMap") AttrTypMap attrTypMap);
	
	/**
	 * attrListAction.
	 *
	 * @param  
	 * @return the list
	 * 
	 * 설명 : 속성 조회
	 */
	List<Map<String, Object>> attrListAction(@Param(value = "attrTypMap") AttrTypMap attrTypMap);		
	
	/**
	 * List2.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param attrTypMap
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	List<AttrTypMap> list2(
			@Param(value = "attrTypMap") AttrTypMap attrTypMap
		  , @Param(value="start")int start
		  , @Param(value="end") int end
	);
	
	/**
	 * Count2.
	 *
	 * @param attrTypMap
	 * @return the int
	 * 
	 * 설명 : 속성 목록수
	 */
	int count2(@Param(value = "attrTypMap") AttrTypMap attrTypMap);
	
	/**
	 * Count.
	 *
	 * @param attrTypMap
	 * @return the int
	 * 
	 * 설명 : 속성 갯수
	 */
	int attrCnt(@Param("attrCd") String attrCd, @Param("attrTyp") String attrTyp, @Param("currentDay") String currentDay);
	
	/**
	 * attrListAction.
	 *
	 * @param  
	 * @return the list
	 * 
	 * 설명 : 속성 ACT_DT, INACT_DT 값 구하기
	 */
	List<Map<String, Object>> attrDate(@Param("attrCd") String attrCd);
	
	/**
	 * insert.
	 *
	 * @param attribute the attribute
	 * @return the int
	 * 
	 * 설명 : 속성 등록 
	 */
	int insert(AttrTypMap attrTypMap);	
	
	/**
	 * Gets the AttrTypMap.
	 * 
	 * @param attrCd, attrTyp
	 * @return the AttrTypMap
	 * 
	 * 설명 : 속성유형 상세 정보 조회
	 */
	AttrTypMap getAttrTypMap(@Param("attrCd") String attrCd, @Param("attrTyp") String attrTyp, @Param("currentDay") String currentDay, @Param("lngTyp") String lngTyp);
	
	/**
	 * Update.
	 *
	 * @param attrTypMap the attrTypMap
	 * @return the int
	 * 
	 * 설명 : 기존에 등록된 속성 값 ACT_DT가 오늘 혹은 미래 날짜 일 때 ACT_DT, INACT_DT, MSTR_FL을 제외 한 나머지 값만 업데이트 
	 */
	int update(AttrTypMap attrTypMap);	
}