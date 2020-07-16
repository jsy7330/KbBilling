package com.ntels.ccbs.distribute.service.logistics.inventoryMoveChangeMng;

import java.util.List;

import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ContactProductVO;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public interface ContactProductService {

	List<ContactProductVO> ownOrgEqtInfoList(ContactProductVO contactProductVO, int page, int perPage);

	int ownOrgEqtInfoCount(ContactProductVO contactProductVO);
	
	List<ContactProductVO> orgList(ContactProductVO contactProductVO);
	
	List<ContactProductVO> ctOrgEqtInfoList(ContactProductVO contactProductVO);
	
	int updateEqtCtOrg(List<ContactProductVO> contactProductVOs, SessionUser session);
}
