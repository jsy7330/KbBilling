package com.ntels.ccbs.distribute.service.distributor.distributorMgt.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.PointContactVO;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.OrganizationMapper;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.PointContactMapper;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.PointContactService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class PointContactServiceImpl implements  PointContactService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PointContactMapper pointContactMapper;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<PointContactVO> pointContactList(PointContactVO pointContactVO,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return pointContactMapper.pointContactList(pointContactVO, start, end);
	}

	@Override
	public int pointContactCount(PointContactVO pointContactVO) {
		return pointContactMapper.pointContactCount(pointContactVO);
	}

	@Override
	public int updatePointContact(PointContactVO pointContactVO) {
		
		int count = pointContactMapper.updateDistInfo(pointContactVO);
		
		pointContactMapper.updateOrgInfo(pointContactVO);
		
		//TDNDI_ORG_HIST 테이블의 ORG_ID 아이디에 따라서 시퀀스 채번
		OrganizationVO organizationVO = new OrganizationVO();
		organizationVO.setOrgId(pointContactVO.getOrgId());
		Integer seq = sequenceService.createNewSubSequence("TDNDI_ORG_HIST", "ORG_ID", organizationVO.getOrgId());
		organizationVO.setOrgHistOrd(seq.toString());
		
		organizationMapper.insertTdndiOrgHist(organizationVO);		//조직정보이력 Insert
		
		pointContactMapper.updateOrgRelHist(pointContactVO);
		
		return count;
	}

	
}
