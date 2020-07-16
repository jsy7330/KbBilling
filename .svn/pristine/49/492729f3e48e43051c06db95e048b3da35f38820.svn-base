package com.ntels.ccbs.distribute.mapper.logistics.assignmentOrderMgt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.assignmentOrderMgt.OrderDistributorVO;

@Component
public interface OrderDistributorMapper {

	List<OrderDistributorVO> orderMngOrgList(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	List<OrderDistributorVO> asgnList(
			@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int asgnCount(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	List<OrderDistributorVO> ordList(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);	
	
	List<OrderDistributorVO> ordPreList(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int insertOrd(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int insertOrdStatProcHist(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int updateOrd(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int updateOrd2(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int insertOrdIdlDtl(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	OrderDistributorVO ordLoanInfo(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int insertBondRcptTr(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int insertLoanUseHist(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	int updateLoanInfo(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
	
	List<OrderDistributorVO> ordList2(
			@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int ordCount2(@Param(value = "orderDistributorVO") OrderDistributorVO orderDistributorVO);
}
