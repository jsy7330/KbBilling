package com.ntels.ccbs.system.service.common.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.system.domain.common.common.OrganizationMngVO;
import com.ntels.ccbs.system.mapper.common.common.OrganizationMngMapper;
import com.ntels.ccbs.system.service.common.common.OrganizationMngService;

@Service
public class OrganizationMngServiceImpl implements OrganizationMngService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** OrganizationMapper Autowired. */
	@Autowired
	private OrganizationMngMapper organizationMngMapper;
	
	@Override
	public List<String> recursionOrganizationList(
			List<OrganizationMngVO> organizationMngVO) {
		
		List<String> returnList =  new ArrayList<String>();
		List<OrganizationMngVO> returnList1 =  new ArrayList<OrganizationMngVO>();
		returnList1 = organizationMngVO;
		int index = 0;
		
		do{
			for(OrganizationMngVO data : returnList1){
				returnList.add(data.getOrgId());
			}
			returnList1 = recursionOrganizationList1(returnList1);
			index++;
		}while((returnList1 != null && returnList1.size() > 0) && index < 9);
		
		return returnList;
	}
	
	
	public List<OrganizationMngVO> recursionOrganizationList1(List<OrganizationMngVO> organizationMngVO){
		
		if(organizationMngVO == null || organizationMngVO.size() < 1){
			return null;
		}else{
			return organizationMngMapper.recursionOrganizationList(organizationMngVO);
		}
		
	}

	@Override
	public List<OrganizationMngVO> organizationList(OrganizationMngVO organizationMngVO, int page,	int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return organizationMngMapper.organizationList(organizationMngVO, start,  end);
		
	}
	
	@Override
	public int organizationCount(OrganizationMngVO organizationMngVO) {
		return organizationMngMapper.organizationCount(organizationMngVO);
	}
	
	@Override
	public List<OrganizationMngVO> organizationList2(OrganizationMngVO organizationMngVO, int page,	int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return organizationMngMapper.organizationList2(organizationMngVO, start,  end);
		
	}
	
	@Override
	public int organizationCount2(OrganizationMngVO organizationMngVO) {
		return organizationMngMapper.organizationCount2(organizationMngVO);
	}
}
