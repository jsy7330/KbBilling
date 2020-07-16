package com.ntels.ccbs.distribute.mapper.distributor.distributorMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationHichStruVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;

@Component
public interface OrganizationMapper {

	
	List<OrganizationVO> recursionOrganizationList(@Param(value = "organization") List<OrganizationVO> organization );
	
	/**
	 * List.
	 *
	 * @param page the page
	 * @param perPage the per page
	 * @param organization
	 * @return the list
	 * 
	 * 설명 : 속성 목록
	 */
	List<OrganizationVO> organizationList(
			@Param(value = "organization") OrganizationVO organization
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );	
	
	/**
	 * Count.
	 *
	 * @param organization
	 * @return the int
	 * 
	 * 설명 : 조직 목록수
	 */
	int organizationCount(@Param(value = "organization") OrganizationVO organization);
	
	
	OrganizationVO getOrganization(@Param(value = "organization") OrganizationVO organization);
	
	List<OrganizationVO> organizationRelList(@Param(value = "organization") OrganizationVO organization );
	
	List<OrganizationVO> organizationHisList(
			@Param(value = "organization") OrganizationVO organization
	  , @Param(value="start")int start
	  , @Param(value="end") int end
	  );
	
	
	int organizationHisCount(@Param(value = "organization") OrganizationVO organization);
	
	int getExistOrganizationInfo(@Param(value = "organization") OrganizationVO organization);
	
	OrganizationVO getOrganizationKey(@Param(value = "organization") OrganizationVO organization);
	
	int updateOrganizationKey(@Param(value = "organization") OrganizationVO organization);
	
	int insertTdndiDistInfo(@Param(value = "organization") OrganizationVO organization); 
	
	int insertTdndiOrgInfo(@Param(value = "organization") OrganizationVO organization);
	
	int insertTdndiOrgRelHist(@Param(value = "organization") OrganizationVO organization);
	
	int insertTdndiOrgHist(@Param(value = "organization") OrganizationVO organization);
	
	OrganizationHichStruVO getOrganizationHichStru(@Param(value = "organizationHichStru") OrganizationHichStruVO organizationHichStru);
	
	int insertTsycoOrgHichStru(@Param(value = "organizationHichStru") OrganizationHichStruVO organizationHichStru);
	
	int insertTsycoOrgInfo(@Param(value = "organization") OrganizationVO organization);
	
	int insertTsycoOrgBizAuth(@Param(value = "organization") OrganizationVO organization);
	
	int updateTdndiOrgInfo(@Param(value = "organization") OrganizationVO organization);
	
	int updateTsycoOrgInfo(@Param(value = "organization") OrganizationVO organization);
	
	int updateTsycoOrgBizAuth(@Param(value = "organization") OrganizationVO organization);
	
	int updatetdndiOrgRelHist(@Param(value = "organization") OrganizationVO organization);
	
	int updateTsycoOrgHichStru(@Param(value = "organizationHichStruVO") OrganizationHichStruVO organizationHichStruVO);
	
	int updateTsycoOrgInfo2(@Param(value = "organizationVO") OrganizationVO organizationVO);
	
	
	
}
