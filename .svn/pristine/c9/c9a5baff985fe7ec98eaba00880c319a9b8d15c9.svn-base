package com.ntels.ifg.httpRest.mapper.charge;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface ChargeMapper {

	Double getUnpaidAmount(@Param("soId")String soId, @Param("pymAcntId")String pymAcntId);

	String getUnpaidMinMonth(@Param("soId")String soId, @Param("pymAcntId")String pymAcntId);
}