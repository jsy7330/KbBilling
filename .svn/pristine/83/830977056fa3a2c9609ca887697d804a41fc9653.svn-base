package com.ntels.ccbs.distribute.mapper.distributor.distributorMgt;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationRelHistVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;

@Component
public interface OrganizationRelHistMapper {

	OrganizationRelHistVO getOrganizationDetail(@Param(value = "organizationRelHistVO") OrganizationRelHistVO organizationRelHistVO);
	
	int updateTdndiOrgRelHist(@Param(value = "organizationVO") OrganizationVO organizationVO);
	
	OrganizationVO getOrganizationDetail2(@Param(value = "organizationVO") OrganizationVO organizationVO);
	
	int updateTdndiOrgRelHist2(@Param(value = "organizationVO") OrganizationVO organizationVO);
	
	
}
