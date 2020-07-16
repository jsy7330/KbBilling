package com.ntels.ccbs.distribute.mapper.logistics.inventoryMoveChangeMng;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ContactProductVO;

@Component
public interface ContactProductMapper {

	List<ContactProductVO> ownOrgEqtInfoList(
			@Param(value = "contactProductVO") ContactProductVO contactProductVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int ownOrgEqtInfoCount(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	List<ContactProductVO> ownOrgUsimInfoList(
			@Param(value = "contactProductVO") ContactProductVO contactProductVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int ownOrgUsimInfoCount(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	List<ContactProductVO> ownOrgVeqtInfoList(
			@Param(value = "contactProductVO") ContactProductVO contactProductVO,
			@Param(value = "start") int start, @Param(value = "end") int end);	
	
	int ownOrgVeqtInfoCount(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	List<ContactProductVO> orgList(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	List<ContactProductVO> ctOrgEqtInfoList(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	List<ContactProductVO> ctOrgUsimInfoList(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	List<ContactProductVO> ctOrgVeqtInfoList(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	
	int updateEqtCtOrg(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	int updateUsimCtOrg(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
	int updateVeqtCtOrg(@Param(value = "contactProductVO") ContactProductVO contactProductVO);
	
}
