package com.ntels.ccbs.distribute.service.distributor.distributorMgt;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationRelHistVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;

public interface OrganizationRelHistService {

	public OrganizationRelHistVO getOrganizationDetail(OrganizationRelHistVO organizationRelHistVO);
	
	public String insertOrganizationRelHist(OrganizationVO organizationVO);
	
	public OrganizationVO getOrganizationDetail2(OrganizationVO organizationVO);
	
	public String updateOrganizationRelHist(OrganizationVO organizationVO);
	
	public String deleteOrganizationRelHist(OrganizationVO organizationVO);
	
}
