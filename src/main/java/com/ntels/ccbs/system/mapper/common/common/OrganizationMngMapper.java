package com.ntels.ccbs.system.mapper.common.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;

@Component
public interface OrganizationMngMapper {

	List<OrganizationMngVO> recursionOrganizationList(@Param(value = "organizationMngVO") List<OrganizationMngVO> organizationMngVO);

	List<OrganizationMngVO> organizationList(
			@Param(value = "organizationMngVO") OrganizationMngVO organizationMngVO,
			@Param(value = "start") int start, @Param(value = "end") int end);

	int organizationCount(@Param(value = "organizationMngVO") OrganizationMngVO organizationMngVO);
	
	List<OrganizationMngVO> organizationList2(
			@Param(value = "organizationMngVO") OrganizationMngVO organizationMngVO,
			@Param(value = "start") int start, @Param(value = "end") int end);

	int organizationCount2(@Param(value = "organizationMngVO") OrganizationMngVO organizationMngVO);
}
