package com.ntels.ccbs.distribute.service.distributor.distributorMgt.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationHichStruVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.OrganizationMapper;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.OrganizationService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** OrganizationMapper Autowired. */
	@Autowired
	private OrganizationMapper organizationMapper;
	
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<String> recursionOrganizationList(
			List<OrganizationVO> organization) {
		
		List<String> returnList =  new ArrayList<String>();
		List<OrganizationVO> returnList1 =  new ArrayList<OrganizationVO>();
		returnList1 = organization;
		int index = 0;
		
		do{
			for(OrganizationVO data : returnList1){
				returnList.add(data.getOrgId());
			}
			returnList1 = recursionOrganizationList1(returnList1);
			index++;
		}while((returnList1 != null && returnList1.size() > 0) && index < 9);
		
		return returnList;
	}
	
	
	public List<OrganizationVO> recursionOrganizationList1(List<OrganizationVO> organization){
		
		if(organization == null || organization.size() < 1){
			return null;
		}else{
			return organizationMapper.recursionOrganizationList(organization);
		}
		
	}

	@Override
	public List<OrganizationVO> organizationList(OrganizationVO organization, int page,
			int perPage) {

		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;

		return organizationMapper.organizationList(organization, start,  end);
		
	}
	
	@Override
	public int organizationCount(OrganizationVO organization) {
		return organizationMapper.organizationCount(organization);
	}

	@Override
	public OrganizationVO getOrganization(OrganizationVO organization) {
		return organizationMapper.getOrganization(organization);
	}

	@Override
	public List<OrganizationVO> organizationRelList(OrganizationVO organization) {
		return organizationMapper.organizationRelList(organization);
	}

	@Override
	public List<OrganizationVO> organizationHisList(OrganizationVO organization, int page, int perPage) {
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return organizationMapper.organizationHisList(organization, start,  end);
	}


	@Override
	public int organizationHisCount(OrganizationVO organization) {
		return organizationMapper.organizationHisCount(organization);
	}


	@Override
	public int getExistOrganizationInfo(OrganizationVO organization) {
		return organizationMapper.getExistOrganizationInfo(organization);
	}


	@Override
	public String insertOrganization(OrganizationVO organization) {
		
		////조직유형코드가  직영점,대리점,접점 인 경우 조직순번을 취득한다.
		if(organization.getTpCd().equals("4000") || organization.getTpCd().equals("5000") || organization.getTpCd().equals("6000") ){
			
			if(organization.getTpCd().equals("4000") || organization.getTpCd().equals("5000")){
				organization.setCommonCd("4000");
			}else{
				organization.setCommonCd(organization.getTpCd());
			}
			
			//orgId 순번 채번
			OrganizationVO code = organizationMapper.getOrganizationKey(organization);
			Integer index = Integer.parseInt( code.getRefCode3() ) + 1;
			String orgId = code.getRefCode() + StringUtils.leftPad(index.toString(), 10 - code.getRefCode().length(), "0");
			organization.setOrgId(orgId);
			
			code.setRefCode3(index.toString());
			organizationMapper.updateOrganizationKey(code);

			organizationMapper.insertTdndiDistInfo(organization);	//유통망정보 내역 Insert
			
		}
		
		int count = organizationMapper.insertTdndiOrgInfo(organization);	//조직정보 Insert
		
		//TDNDI_ORG_REL_HIST 테이블의 ORG_ID 아이디에 따라서 시퀀스 채번
		Integer seq = sequenceService.createNewSubSequence("TDNDI_ORG_REL_HIST", "ORG_ID", organization.getOrgId());
		organization.setOrgRelOrd(seq.toString());
		
		organization.setRelTpCd("101");		//관계유형코드 101 고정값 등록
		organizationMapper.insertTdndiOrgRelHist(organization);		//조직관계이력 Insert
		
		//TDNDI_ORG_HIST 테이블의 ORG_ID 아이디에 따라서 시퀀스 채번
		Integer seq2 = sequenceService.createNewSubSequence("TDNDI_ORG_HIST", "ORG_ID", organization.getOrgId());
		organization.setOrgHistOrd(seq2.toString());
		
		organizationMapper.insertTdndiOrgHist(organization);		//조직정보이력 Insert
		
		
		
		OrganizationHichStruVO paramOrganizationHichStru = new OrganizationHichStruVO();
		paramOrganizationHichStru.setSoId(organization.getSoId());
		paramOrganizationHichStru.setOrgId(organization.getPartOrgId());
		
		OrganizationHichStruVO dataOrganizationHichStru = organizationMapper.getOrganizationHichStru(paramOrganizationHichStru);
		String orgLv = dataOrganizationHichStru.getOrgLvCd().substring(0, 1);
		
		if(orgLv.equals("1")){
			dataOrganizationHichStru.setOrgIdLv2(organization.getOrgId());
		}else if(orgLv.equals("2")){
			dataOrganizationHichStru.setOrgIdLv3(organization.getOrgId());
		}else if(orgLv.equals("3")){
			dataOrganizationHichStru.setOrgIdLv4(organization.getOrgId());
		}else if(orgLv.equals("4")){
			dataOrganizationHichStru.setOrgIdLv5(organization.getOrgId());
		}else if(orgLv.equals("5")){
			dataOrganizationHichStru.setOrgIdLv6(organization.getOrgId());
		}else if(orgLv.equals("6")){
			dataOrganizationHichStru.setOrgIdLv7(organization.getOrgId());
		}else if(orgLv.equals("7")){
			dataOrganizationHichStru.setOrgIdLv8(organization.getOrgId());
		}else if(orgLv.equals("8")){
			dataOrganizationHichStru.setOrgIdLv9(organization.getOrgId());
		}
		
		dataOrganizationHichStru.setOrgId(organization.getOrgId());
		dataOrganizationHichStru.setOrgLvCd(organization.getOrgLvCd());
		
		dataOrganizationHichStru.setRegrId(organization.getRegrId());
		dataOrganizationHichStru.setChgrId(organization.getChgrId());
		dataOrganizationHichStru.setRegDate(organization.getRegDate());
		dataOrganizationHichStru.setChgDate(organization.getChgDate());
		
		organizationMapper.insertTsycoOrgHichStru(dataOrganizationHichStru);	//조직계층구조 Insert
		
		//조회권한 처리
		if(organization.getInqPermCd().equals("101")){	//기본 
			organization.setInqPermLvCd(organization.getOrgLvCd());		
			organization.setInqPermOrgId(organization.getOrgId());
		}else if(organization.getInqPermCd().equals("102")){	//전체
			
			OrganizationVO paramOrg = new OrganizationVO();
			paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv1()); //최상위조직 아이디
			paramOrg.setLngTyp(organization.getLngTyp());
			OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
			organization.setInqPermLvCd(dataOrg.getOrgLvCd());					//최상위 조직 레벨 코드
			organization.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv1());	//최상위조직 아이디
			
		}else if(organization.getInqPermCd().equals("103")){	//실/총괄
			
			if(dataOrganizationHichStru.getOrgIdLv2() == null || dataOrganizationHichStru.getOrgIdLv2().equals("")){
				organization.setInqPermLvCd(organization.getOrgLvCd());		
				organization.setInqPermOrgId(organization.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv2()); 
				paramOrg.setLngTyp(organization.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organization.setInqPermLvCd(dataOrg.getOrgLvCd());
				organization.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv2());
			}
		}else if(organization.getInqPermCd().equals("104")){	//영업본부
			
			if(dataOrganizationHichStru.getOrgIdLv3() == null || dataOrganizationHichStru.getOrgIdLv3().equals("")){
				organization.setInqPermLvCd(organization.getOrgLvCd());		
				organization.setInqPermOrgId(organization.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv3()); 
				paramOrg.setLngTyp(organization.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organization.setInqPermLvCd(dataOrg.getOrgLvCd());
				organization.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv3());
			}
			
		}
		
		organizationMapper.insertTsycoOrgInfo(organization);		//조직정보 Insert
				
		organizationMapper.insertTsycoOrgBizAuth(organization);		//조직업무권한 Insert
		
		
		return String.valueOf(count);
	}


	@Override
	public String updateOrganization(OrganizationVO organization) {
		
		int count = organizationMapper.updateTdndiOrgInfo(organization);	//조직정보 Update

		//TDNDI_ORG_HIST 테이블의 ORG_ID 아이디에 따라서 시퀀스 채번
		Integer seq2 = sequenceService.createNewSubSequence("TDNDI_ORG_HIST", "ORG_ID", organization.getOrgId());
		organization.setOrgHistOrd(seq2.toString());
		
		organizationMapper.insertTdndiOrgHist(organization);		//조직정보이력 Insert
		
		//조직관계이력 Update - 적용시작일, 적용종료일 이 수정되었을 경우만.
		if(organization.getCheckAppyDt() != null && organization.getCheckAppyDt().equals("Y")){
			organizationMapper.updatetdndiOrgRelHist(organization);		//조직관계이력 update
		}
		
		//조직계층구조
		
		OrganizationHichStruVO paramOrganizationHichStru = new OrganizationHichStruVO();
		paramOrganizationHichStru.setSoId(organization.getSoId());
		paramOrganizationHichStru.setOrgId(organization.getOrgId());
		
		OrganizationHichStruVO dataOrganizationHichStru = organizationMapper.getOrganizationHichStru(paramOrganizationHichStru);
		
		
		//조직정보
		//조회권한 처리
		if(organization.getInqPermCd().equals("101")){	//기본 
			organization.setInqPermLvCd(organization.getOrgLvCd());		
			organization.setInqPermOrgId(organization.getOrgId());
		}else if(organization.getInqPermCd().equals("102")){	//전체
			
			OrganizationVO paramOrg = new OrganizationVO();
			paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv1()); //최상위조직 아이디
			paramOrg.setLngTyp(organization.getLngTyp());
			OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
			organization.setInqPermLvCd(dataOrg.getOrgLvCd());					//최상위 조직 레벨 코드
			organization.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv1());	//최상위조직 아이디
			
		}else if(organization.getInqPermCd().equals("103")){	//실/총괄
			
			if(dataOrganizationHichStru.getOrgIdLv2() == null || dataOrganizationHichStru.getOrgIdLv2().equals("")){
				organization.setInqPermLvCd(organization.getOrgLvCd());		
				organization.setInqPermOrgId(organization.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv2()); 
				paramOrg.setLngTyp(organization.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organization.setInqPermLvCd(dataOrg.getOrgLvCd());
				organization.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv2());
			}
		}else if(organization.getInqPermCd().equals("104")){	//영업본부
			
			if(dataOrganizationHichStru.getOrgIdLv3() == null || dataOrganizationHichStru.getOrgIdLv3().equals("")){
				organization.setInqPermLvCd(organization.getOrgLvCd());		
				organization.setInqPermOrgId(organization.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv3()); 
				paramOrg.setLngTyp(organization.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organization.setInqPermLvCd(dataOrg.getOrgLvCd());
				organization.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv3());
			}
			
		}
		
		organizationMapper.updateTsycoOrgInfo(organization);		//조직정보 Update
		
		//조직업무권한
		organizationMapper.updateTsycoOrgBizAuth(organization);		//조직업무권한 Update
		
		
		return String.valueOf(count);
	}


	
	
	
}
