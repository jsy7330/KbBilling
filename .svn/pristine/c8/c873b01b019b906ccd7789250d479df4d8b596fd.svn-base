package com.ntels.ccbs.product.mapper.service.serviceMgt;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ntels.ccbs.product.domain.service.serviceMgt.ProductWorkMngVO;
import com.ntels.ccbs.product.domain.service.serviceMgt.WorkDefMngVO;

public interface ProductWorkMngMapper {

	List<ProductWorkMngVO> getProdListAction(@Param(value = "soId") String soId,@Param(value="prodGrp") String prodGrp, @Param(value = "lng")String lng);
	
	List<ProductWorkMngVO> prodWorkListAction(
			@Param(value ="productWorkMngVO") ProductWorkMngVO productWorkMngVO
		  , @Param(value="start")int start
		  , @Param(value="end") int end
		  );

	int prodWorkListCount(@Param(value ="productWorkMngVO") ProductWorkMngVO productWorkMngVO);
	List<ProductWorkMngVO> getWrkDefList(@Param(value = "wrkTp") String wrkTp);	
	List<ProductWorkMngVO> openWrkDefSearchAction(
			@Param(value ="productWorkMngVO") ProductWorkMngVO productWorkMngVO
		  , @Param(value="start")int start
		  , @Param(value="end") int end
		  );

	int openWrkDefSearchActionCnt(@Param(value ="productWorkMngVO") ProductWorkMngVO productWorkMngVO);
	
	int insertProdWrk(@Param(value = "productWorkMngVO") ProductWorkMngVO productWorkMngVO);
	
	String getWrkSeqNo(@Param(value = "productWorkMngVO") ProductWorkMngVO productWorkMngVO);	
	int updateProdWrk(@Param(value = "productWorkMngVO") ProductWorkMngVO productWorkMngVO);	
	int deleteProdWrk(@Param(value = "productWorkMngVO") ProductWorkMngVO productWorkMngVO);	
	
}



