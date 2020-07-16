package com.ntels.ccbs.product.service.service.serviceMgt;

import java.util.List;

import com.ntels.ccbs.product.domain.service.serviceMgt.ProductWorkMngVO;

public interface ProductWorkMngService {
	
	public List<ProductWorkMngVO> getProdListAction(String soId, String prodGrp, String lng);
	
	public List<ProductWorkMngVO> prodWorkListAction(ProductWorkMngVO productWorkMngVO, int page, int perPage);
	
	public int prodWorkListCount(ProductWorkMngVO productWorkMngVO);
	
	public List<ProductWorkMngVO> getWrkDefList(String wrkTp);	
	
	public List<ProductWorkMngVO> openWrkDefSearchAction(ProductWorkMngVO productWorkMngVO, int page, int perPage);
	
	public int openWrkDefSearchActionCnt(ProductWorkMngVO productWorkMngVO);
	int insertProdWrk(ProductWorkMngVO productWorkMngVO);
	public String getWrkSeqNo(ProductWorkMngVO productWorkMngVO);
	int updateProdWrk(ProductWorkMngVO productWorkMngVO);	
	int deleteProdWrk(ProductWorkMngVO productWorkMngVO);		
}
