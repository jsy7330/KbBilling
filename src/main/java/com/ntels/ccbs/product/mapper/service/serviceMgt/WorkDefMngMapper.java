package com.ntels.ccbs.product.mapper.service.serviceMgt;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ntels.ccbs.product.domain.service.serviceMgt.WorkDefMngVO;

public interface WorkDefMngMapper {
	
	List<WorkDefMngVO> workDefMngListAction(
		@Param(value ="workDefMngVO") WorkDefMngVO workDefMngVO
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );

	int workDefMngListCount(@Param(value ="workDefMngVO") WorkDefMngVO workDefMngVO);
	
	int insertWorkDefMngInfo(@Param(value ="workDefMngVO") WorkDefMngVO workDefMngVO);
	
	int updateWorkDefMngInfo(@Param(value ="workDefMngVO") WorkDefMngVO workDefMngVO);
	
	int deleteWorkDefMngInfo(@Param(value ="workDefMngVO") WorkDefMngVO workDefMngVO);
}



