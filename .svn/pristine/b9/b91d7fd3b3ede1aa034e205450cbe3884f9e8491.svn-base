package com.ntels.ccbs.distribute.service.logistics.inventoryMoveChangeMng.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ContactProductVO;
import com.ntels.ccbs.distribute.mapper.logistics.inventoryMoveChangeMng.ContactProductMapper;
import com.ntels.ccbs.distribute.service.logistics.inventoryMoveChangeMng.ContactProductService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ContactProductServiceImpl implements ContactProductService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContactProductMapper contactProductMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<ContactProductVO> ownOrgEqtInfoList(
			ContactProductVO contactProductVO, int page, int perPage) {

		List<ContactProductVO> returnList = new ArrayList<ContactProductVO>();
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		if(contactProductVO.getItemTpCd().equals("C")){
			returnList = contactProductMapper.ownOrgEqtInfoList(contactProductVO, start, end);
		}else if(contactProductVO.getItemTpCd().equals("U")){
			returnList = contactProductMapper.ownOrgUsimInfoList(contactProductVO, start, end);
		}else if(contactProductVO.getItemTpCd().equals("V")){
			returnList = contactProductMapper.ownOrgVeqtInfoList(contactProductVO, start, end);
		}
		
		return returnList;
	}

	@Override
	public int ownOrgEqtInfoCount(ContactProductVO contactProductVO) {

		int count = 0;
		
		if(contactProductVO.getItemTpCd().equals("C")){
			count = contactProductMapper.ownOrgEqtInfoCount(contactProductVO);
		}else if(contactProductVO.getItemTpCd().equals("U")){
			count = contactProductMapper.ownOrgUsimInfoCount(contactProductVO);
		}else if(contactProductVO.getItemTpCd().equals("V")){
			count = contactProductMapper.ownOrgVeqtInfoCount(contactProductVO);
		}
		
		return count;
	}

	@Override
	public List<ContactProductVO> orgList(ContactProductVO contactProductVO) {
		return contactProductMapper.orgList(contactProductVO);
	}

	@Override
	public List<ContactProductVO> ctOrgEqtInfoList(
			ContactProductVO contactProductVO) {

		List<ContactProductVO> returnList = new ArrayList<ContactProductVO>();
		
		if(contactProductVO.getItemTpCd().equals("C")){
			returnList = contactProductMapper.ctOrgEqtInfoList(contactProductVO);
		}else if(contactProductVO.getItemTpCd().equals("U")){
			returnList = contactProductMapper.ctOrgUsimInfoList(contactProductVO);
		}else if(contactProductVO.getItemTpCd().equals("V")){
			returnList = contactProductMapper.ctOrgVeqtInfoList(contactProductVO);
		}
		
		return returnList;
	}

	@Override
	public int updateEqtCtOrg(List<ContactProductVO> contactProductVOs,
			SessionUser session) {

		int count = 0;
		String itemTpCd = contactProductVOs.get(0).getItemTpCd();
		Date sysdata = DateUtil.sysdate();
		
		for(ContactProductVO contactProductVO : contactProductVOs){
			
			contactProductVO.setCtChgDt(sysdata);
			contactProductVO.setCtChgId(session.getUserId());
			
			if(itemTpCd.equals("C")){
				count += contactProductMapper.updateEqtCtOrg(contactProductVO);
			}else if(itemTpCd.equals("U")){
				count += contactProductMapper.updateUsimCtOrg(contactProductVO);
			}else if(itemTpCd.equals("V")){
				count += contactProductMapper.updateVeqtCtOrg(contactProductVO);
			}
			
		}
		
		return count;
	}

	
	
	
	
}
