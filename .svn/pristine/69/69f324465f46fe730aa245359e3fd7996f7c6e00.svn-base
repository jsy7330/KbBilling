package com.ntels.ccbs.distribute.service.distributor.distributorMgt.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationHichStruVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationRelHistVO;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.OrganizationVO;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.OrganizationMapper;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.OrganizationRelHistMapper;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.OrganizationRelHistService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class OrganizationRelHistServiceImpl implements OrganizationRelHistService {

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrganizationRelHistMapper organizationRelHistMapper;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public OrganizationRelHistVO getOrganizationDetail(
			OrganizationRelHistVO organizationRelHistVO) {
		return organizationRelHistMapper.getOrganizationDetail(organizationRelHistVO);
	}

	@Override
	public String insertOrganizationRelHist(OrganizationVO organizationVO) {
		
		String appyEndDt = organizationVO.getAppyEndDt();
		String bDt = organizationVO.getAppyStrtDt();
		
		long chStart = 0;  
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		    
		try {
			chStart = df.parse(bDt).getTime();		//스트링형 date를 long형의 함수로 컨버트하고
			chStart -= 86400000;					//24*60*60*1000 하루치의 숫자를 빼준다
                                   
			Date date = new Date(chStart);			//이것을 다시 날짜형태로 바꿔주고
			bDt = df.format(date);					//바꿔준 날짜를 yyyyMMdd형으로 바꾼후 스트링으로 다시 형변환을해서 date에 대입
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		organizationVO.setAppyEndDt(bDt);	//어제날짜로 셋팅
		organizationRelHistMapper.updateTdndiOrgRelHist(organizationVO);
		organizationVO.setAppyEndDt(appyEndDt);	//업데이트 후 날짜 원복
		
		//TDNDI_ORG_REL_HIST 테이블의 ORG_ID 아이디에 따라서 시퀀스 채번
		Integer seq = sequenceService.createNewSubSequence("TDNDI_ORG_REL_HIST", "ORG_ID", organizationVO.getOrgId());
		organizationVO.setOrgRelOrd(seq.toString());
				
		int count = organizationMapper.insertTdndiOrgRelHist(organizationVO);
		
		//조직계층구조 업데이트
		OrganizationHichStruVO paramOrganizationHichStru = new OrganizationHichStruVO();
		paramOrganizationHichStru.setSoId(organizationVO.getSoId());
		paramOrganizationHichStru.setOrgId(organizationVO.getPartOrgId());
		
		OrganizationHichStruVO dataOrganizationHichStru = organizationMapper.getOrganizationHichStru(paramOrganizationHichStru);
		String orgLv = dataOrganizationHichStru.getOrgLvCd().substring(0, 1);
		
		if(orgLv.equals("1")){
			dataOrganizationHichStru.setOrgIdLv2(organizationVO.getOrgId());
		}else if(orgLv.equals("2")){
			dataOrganizationHichStru.setOrgIdLv3(organizationVO.getOrgId());
		}else if(orgLv.equals("3")){
			dataOrganizationHichStru.setOrgIdLv4(organizationVO.getOrgId());
		}else if(orgLv.equals("4")){
			dataOrganizationHichStru.setOrgIdLv5(organizationVO.getOrgId());
		}else if(orgLv.equals("5")){
			dataOrganizationHichStru.setOrgIdLv6(organizationVO.getOrgId());
		}else if(orgLv.equals("6")){
			dataOrganizationHichStru.setOrgIdLv7(organizationVO.getOrgId());
		}else if(orgLv.equals("7")){
			dataOrganizationHichStru.setOrgIdLv8(organizationVO.getOrgId());
		}else if(orgLv.equals("8")){
			dataOrganizationHichStru.setOrgIdLv9(organizationVO.getOrgId());
		}
		
		dataOrganizationHichStru.setOrgId(organizationVO.getOrgId());
		//dataOrganizationHichStru.setOrgLvCd(organizationVO.getOrgLvCd());
		
		dataOrganizationHichStru.setChgDate(organizationVO.getChgDate());
		dataOrganizationHichStru.setChgrId(organizationVO.getChgrId());
		
		organizationMapper.updateTsycoOrgHichStru(dataOrganizationHichStru);	//조직계층구조 업데이트
		
		
		//조직정보
		//조회권한 처리
		if(organizationVO.getInqPermCd().equals("101")){	//기본 
			organizationVO.setInqPermLvCd(organizationVO.getOrgLvCd());		
			organizationVO.setInqPermOrgId(organizationVO.getOrgId());
		}else if(organizationVO.getInqPermCd().equals("102")){	//전체
			
			OrganizationVO paramOrg = new OrganizationVO();
			paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv1()); //최상위조직 아이디
			paramOrg.setLngTyp(organizationVO.getLngTyp());
			OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
			organizationVO.setInqPermLvCd(dataOrg.getOrgLvCd());					//최상위 조직 레벨 코드
			organizationVO.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv1());	//최상위조직 아이디
			
		}else if(organizationVO.getInqPermCd().equals("103")){	//실/총괄
			
			if(dataOrganizationHichStru.getOrgIdLv2() == null || dataOrganizationHichStru.getOrgIdLv2().equals("")){
				organizationVO.setInqPermLvCd(organizationVO.getOrgLvCd());		
				organizationVO.setInqPermOrgId(organizationVO.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv2()); 
				paramOrg.setLngTyp(organizationVO.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organizationVO.setInqPermLvCd(dataOrg.getOrgLvCd());
				organizationVO.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv2());
			}
		}else if(organizationVO.getInqPermCd().equals("104")){	//영업본부
			
			if(dataOrganizationHichStru.getOrgIdLv3() == null || dataOrganizationHichStru.getOrgIdLv3().equals("")){
				organizationVO.setInqPermLvCd(organizationVO.getOrgLvCd());		
				organizationVO.setInqPermOrgId(organizationVO.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv3()); 
				paramOrg.setLngTyp(organizationVO.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organizationVO.setInqPermLvCd(dataOrg.getOrgLvCd());
				organizationVO.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv3());
			}
			
		}
		
		organizationMapper.updateTsycoOrgInfo2(organizationVO);		//조직정보 Update
				
		return String.valueOf(count);
	}

	@Override
	public OrganizationVO getOrganizationDetail2(OrganizationVO organizationVO) {
		return organizationRelHistMapper.getOrganizationDetail2(organizationVO);
	}

	@Override
	public String updateOrganizationRelHist(OrganizationVO organizationVO) {
		
		int count = organizationRelHistMapper.updateTdndiOrgRelHist(organizationVO);
		
		//조직계층구조 업데이트
		OrganizationHichStruVO paramOrganizationHichStru = new OrganizationHichStruVO();
		paramOrganizationHichStru.setSoId(organizationVO.getSoId());
		paramOrganizationHichStru.setOrgId(organizationVO.getPartOrgId());
		
		OrganizationHichStruVO dataOrganizationHichStru = organizationMapper.getOrganizationHichStru(paramOrganizationHichStru);
		String orgLv = dataOrganizationHichStru.getOrgLvCd().substring(0, 1);
		
		if(orgLv.equals("1")){
			dataOrganizationHichStru.setOrgIdLv2(organizationVO.getOrgId());
		}else if(orgLv.equals("2")){
			dataOrganizationHichStru.setOrgIdLv3(organizationVO.getOrgId());
		}else if(orgLv.equals("3")){
			dataOrganizationHichStru.setOrgIdLv4(organizationVO.getOrgId());
		}else if(orgLv.equals("4")){
			dataOrganizationHichStru.setOrgIdLv5(organizationVO.getOrgId());
		}else if(orgLv.equals("5")){
			dataOrganizationHichStru.setOrgIdLv6(organizationVO.getOrgId());
		}else if(orgLv.equals("6")){
			dataOrganizationHichStru.setOrgIdLv7(organizationVO.getOrgId());
		}else if(orgLv.equals("7")){
			dataOrganizationHichStru.setOrgIdLv8(organizationVO.getOrgId());
		}else if(orgLv.equals("8")){
			dataOrganizationHichStru.setOrgIdLv9(organizationVO.getOrgId());
		}
		
		dataOrganizationHichStru.setOrgId(organizationVO.getOrgId());
		//dataOrganizationHichStru.setOrgLvCd(organizationVO.getOrgLvCd());
		
		dataOrganizationHichStru.setChgDate(organizationVO.getChgDate());
		dataOrganizationHichStru.setChgrId(organizationVO.getChgrId());
		dataOrganizationHichStru.setSoId(organizationVO.getSoId());
		organizationMapper.updateTsycoOrgHichStru(dataOrganizationHichStru);	//조직계층구조 업데이트
		
		
		//조직정보
		//조회권한 처리
		if(organizationVO.getInqPermCd().equals("101")){	//기본 
			organizationVO.setInqPermLvCd(organizationVO.getOrgLvCd());		
			organizationVO.setInqPermOrgId(organizationVO.getOrgId());
		}else if(organizationVO.getInqPermCd().equals("102")){	//전체
			
			OrganizationVO paramOrg = new OrganizationVO();
			paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv1()); //최상위조직 아이디
			paramOrg.setLngTyp(organizationVO.getLngTyp());
			OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
			organizationVO.setInqPermLvCd(dataOrg.getOrgLvCd());					//최상위 조직 레벨 코드
			organizationVO.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv1());	//최상위조직 아이디
			
		}else if(organizationVO.getInqPermCd().equals("103")){	//실/총괄
			
			if(dataOrganizationHichStru.getOrgIdLv2() == null || dataOrganizationHichStru.getOrgIdLv2().equals("")){
				organizationVO.setInqPermLvCd(organizationVO.getOrgLvCd());		
				organizationVO.setInqPermOrgId(organizationVO.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv2()); 
				paramOrg.setLngTyp(organizationVO.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organizationVO.setInqPermLvCd(dataOrg.getOrgLvCd());
				organizationVO.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv2());
			}
		}else if(organizationVO.getInqPermCd().equals("104")){	//영업본부
			
			if(dataOrganizationHichStru.getOrgIdLv3() == null || dataOrganizationHichStru.getOrgIdLv3().equals("")){
				organizationVO.setInqPermLvCd(organizationVO.getOrgLvCd());		
				organizationVO.setInqPermOrgId(organizationVO.getOrgId());
			}else{
				OrganizationVO paramOrg = new OrganizationVO();
				paramOrg.setOrgId(dataOrganizationHichStru.getOrgIdLv3()); 
				paramOrg.setLngTyp(organizationVO.getLngTyp());
				OrganizationVO dataOrg = organizationMapper.getOrganization(paramOrg);
				organizationVO.setInqPermLvCd(dataOrg.getOrgLvCd());
				organizationVO.setInqPermOrgId(dataOrganizationHichStru.getOrgIdLv3());
			}
			
		}
		
		organizationMapper.updateTsycoOrgInfo2(organizationVO);		//조직정보 Update
		
		return String.valueOf(count);
	}

	@Override
	public String deleteOrganizationRelHist(OrganizationVO organizationVO) {
		
		
		long chStart = 0;  
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date currentTime = new Date ( );
		String dt = df.format (currentTime);
		
		try {
			chStart = df.parse(dt).getTime();		//스트링형 date를 long형의 함수로 컨버트하고
			chStart -= 86400000;					//24*60*60*1000 하루치의 숫자를 빼준다
                                   
			Date date = new Date(chStart);			//이것을 다시 날짜형태로 바꿔주고
			dt = df.format(date);					//바꿔준 날짜를 yyyyMMdd형으로 바꾼후 스트링으로 다시 형변환을해서 date에 대입
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		organizationVO.setAppyEndDt(dt);	//어제날짜로 셋팅
		int count = organizationRelHistMapper.updateTdndiOrgRelHist(organizationVO);
		
		return String.valueOf(count);
		
	}

	
}
