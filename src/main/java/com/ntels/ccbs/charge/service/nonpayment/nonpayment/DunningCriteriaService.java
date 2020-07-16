package com.ntels.ccbs.charge.service.nonpayment.nonpayment;

import java.util.List;

import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningInfoItemVO;

public interface DunningCriteriaService {

	public List<DunningInfoItemVO> dunningCriteriaList(DunningInfoItemVO dunningInfo, int page, int perPage);
	
	public int dunningCriteriaCount(DunningInfoItemVO dunningInfo);
	
	public DunningInfoItemVO getDunningCriteria(DunningInfoItemVO dunningInfo);
	
	public int insertDunningInfo(DunningInfoItemVO dunningInfo);
	
	public int updateDunningInfo(DunningInfoItemVO dunningInfo);
}
