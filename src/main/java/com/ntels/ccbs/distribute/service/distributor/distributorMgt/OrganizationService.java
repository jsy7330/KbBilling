package com.ntels.ccbs.distribute.service.distributor.distributorMgt;

import java.util.List;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;

public interface OrganizationService {
	
	public List<String> recursionOrganizationList(List<OrganizationVO> organization );

	public List<OrganizationVO> organizationList(OrganizationVO organization, int page, int perPage);
	
	public int organizationCount(OrganizationVO organization);
	
	public OrganizationVO getOrganization(OrganizationVO organization);
	
	public List<OrganizationVO> organizationRelList(OrganizationVO organization);
	
	public List<OrganizationVO> organizationHisList(OrganizationVO organization, int page, int perPage );
	
	public int organizationHisCount(OrganizationVO organization);
	
	public int getExistOrganizationInfo(OrganizationVO organization);
	
	public String insertOrganization(OrganizationVO organization);
	
	public String updateOrganization(OrganizationVO organization);
	
}
