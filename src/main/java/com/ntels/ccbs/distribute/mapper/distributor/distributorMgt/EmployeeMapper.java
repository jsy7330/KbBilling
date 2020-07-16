package com.ntels.ccbs.distribute.mapper.distributor.distributorMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.EmployeeVO;

@Component
public interface EmployeeMapper {

	List<EmployeeVO> employeeList(
			@Param(value = "employeeVO") EmployeeVO employeeVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int employeeCount(@Param(value = "employeeVO") EmployeeVO employeeVO);
	
	int checkUserId(@Param(value = "employeeVO") EmployeeVO employeeVO);
	
	int insertEmployee(@Param(value = "employeeVO") EmployeeVO employeeVO);
	
	int insertSoAuthInfo(@Param(value = "employeeVO") EmployeeVO employeeVO);
	
	int updateEmployee(@Param(value = "employeeVO") EmployeeVO employeeVO);
	
	List<EmployeeVO> userGroupList(@Param(value = "employeeVO") EmployeeVO employeeVO);
	
	
}
