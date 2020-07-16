package com.ntels.ccbs.customer.service.contract.contract;

import com.ntels.ccbs.customer.domain.contract.contract.OrderAttrVO;
import com.ntels.ccbs.customer.domain.contract.contract.OrderCommonVO;
import com.ntels.ccbs.customer.domain.contract.contract.PrecheckInfoVO;

public interface OrderPrecheckService {

	PrecheckInfoVO savePrecheckResult(OrderCommonVO orderCommonInfo, OrderAttrVO orderAttrInfo, String usrId, String lng, String today);

}
