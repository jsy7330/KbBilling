package com.ntels.ccbs.distribute.service.distributor.distributorMgt.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.DistributionInfoVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.DistributionInfoMapper;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.OrganizationMapper;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.DistributionInfoService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class DistributionInfoServiceImpl implements DistributionInfoService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DistributionInfoMapper distributionInfoMapper;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<DistributionInfoVO> distributionInfoList(
			DistributionInfoVO distributionInfoVO, int page, int perPage) {
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return distributionInfoMapper.distributionInfoList(distributionInfoVO, start, end);
	}

	@Override
	public int distributionInfoCount(DistributionInfoVO distributionInfoVO) {
		return distributionInfoMapper.distributionInfoCount(distributionInfoVO);
	}

	@Override
	public DistributionInfoVO selectDistributionInfo(
			DistributionInfoVO distributionInfoVO) {
		return distributionInfoMapper.selectDistributionInfo(distributionInfoVO);
	}

	@Override
	public int insertDistributionInfo(DistributionInfoVO distributionInfoVO) {

		int count = distributionInfoMapper.insertDistInfo(distributionInfoVO);
		
		distributionInfoMapper.updateOrgInfo(distributionInfoVO);
		
		//TDNDI_ORG_HIST 테이블의 ORG_ID 아이디에 따라서 시퀀스 채번
		OrganizationVO organizationVO = new OrganizationVO();
		organizationVO.setOrgId(distributionInfoVO.getOrgId());
		Integer seq = sequenceService.createNewSubSequence("TDNDI_ORG_HIST", "ORG_ID", organizationVO.getOrgId());
		organizationVO.setOrgHistOrd(seq.toString());
		
		organizationMapper.insertTdndiOrgHist(organizationVO);		//조직정보이력 Insert
		
		distributionInfoMapper.updateOrgRelHist(distributionInfoVO);
		
		return count;
	}

	@Override
	public int updateDistributionInfo(DistributionInfoVO distributionInfoVO) {
		
		int count = distributionInfoMapper.updateDistInfo(distributionInfoVO);
		
		distributionInfoMapper.updateOrgInfo(distributionInfoVO);
		
		//TDNDI_ORG_HIST 테이블의 ORG_ID 아이디에 따라서 시퀀스 채번
		OrganizationVO organizationVO = new OrganizationVO();
		organizationVO.setOrgId(distributionInfoVO.getOrgId());
		Integer seq = sequenceService.createNewSubSequence("TDNDI_ORG_HIST", "ORG_ID", organizationVO.getOrgId());
		organizationVO.setOrgHistOrd(seq.toString());
		
		organizationMapper.insertTdndiOrgHist(organizationVO);		//조직정보이력 Insert
		
		distributionInfoMapper.updateOrgRelHist(distributionInfoVO);
		
		return count;
	}

	
}
