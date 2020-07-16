package com.ntels.ccbs.distribute.mapper.distributor.distributorMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.PointContactVO;

@Component
public interface PointContactMapper {

	List<PointContactVO> pointContactList(
			@Param(value = "pointContactVO") PointContactVO pointContactVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int pointContactCount(@Param(value = "pointContactVO") PointContactVO pointContactVO);
	
	int updateDistInfo(@Param(value = "pointContactVO") PointContactVO pointContactVO);
	
	int updateOrgInfo(@Param(value = "pointContactVO") PointContactVO pointContactVO);
	
	int updateOrgRelHist(@Param(value = "pointContactVO") PointContactVO pointContactVO);
	
}
