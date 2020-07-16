package com.ntels.ccbs.charge.service.nonpayment.nonpayment;

import java.util.List;

import com.ntels.ccbs.charge.domain.nonpayment.nonpayment.DunningExemptionVO;

public interface DunningExemptionService {

	public List<DunningExemptionVO> dunningExemptionList(DunningExemptionVO dunningInfo, int page, int perPage);
	
	public int dunningExemptionCount(DunningExemptionVO dunningInfo);
	
	public DunningExemptionVO getDunningExemption(DunningExemptionVO dunningInfo);
	
	public int insertDunningExemption(DunningExemptionVO dunningInfo);
	
	public int updateDunningExemption(DunningExemptionVO dunningInfo);
	
	public int updateDunningExemptionList(DunningExemptionVO dunningInfo);
	
	public int deleteDunningExemptionList(DunningExemptionVO dunningInfo);
}
