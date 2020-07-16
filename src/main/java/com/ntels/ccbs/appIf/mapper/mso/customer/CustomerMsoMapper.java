package com.ntels.ccbs.appIf.mapper.mso.customer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CustomerMsoMapper {

	List<Map<String, Object>> getEquipmentReception(@Param(value="param")Map<String, Object> param);
}
