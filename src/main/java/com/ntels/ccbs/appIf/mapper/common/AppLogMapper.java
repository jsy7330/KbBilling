package com.ntels.ccbs.appIf.mapper.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.appIf.domain.common.IfLogVO;

@Component
public interface AppLogMapper {
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: insertAppLog
	 * 2. ClassName : AppLogMapper
	 * 3. Comment   : 연동 로그를 기록한다.
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 3. 15. 오전 10:03:04
	 * </PRE>
	 *   @return int
	 *   @param ifLog
	 *   @return
	 */
	int insertAppLog(@Param(value="ifLog")IfLogVO ifLog);

}
