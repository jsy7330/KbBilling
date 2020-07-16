package com.ntels.ccbs.product.service.service.serviceMgt.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;

import com.ntels.ccbs.product.domain.service.serviceMgt.ProductWorkMngVO;
import com.ntels.ccbs.product.mapper.service.serviceMgt.ProductWorkMngMapper;
import com.ntels.ccbs.product.service.service.serviceMgt.ProductWorkMngService;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ProductWorkMngServiceImpl implements ProductWorkMngService {
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** the mapper. */
	@Autowired
	private ProductWorkMngMapper productWorkMngMapper;		
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<ProductWorkMngVO> getProdListAction(String soId, String prodGrp, String lng) {
		return productWorkMngMapper.getProdListAction(soId, prodGrp, lng);
	}
	
	@Override
	public List<ProductWorkMngVO> prodWorkListAction(ProductWorkMngVO productWorkMngVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return productWorkMngMapper.prodWorkListAction(productWorkMngVO, start, end);
	}
	
	@Override
	public int prodWorkListCount(ProductWorkMngVO productWorkMngVO) {
		return productWorkMngMapper.prodWorkListCount(productWorkMngVO);
	}	
	
	@Override
	public List<ProductWorkMngVO> getWrkDefList(String wrkTp) {
		return productWorkMngMapper.getWrkDefList(wrkTp);
	}	

	@Override
	public List<ProductWorkMngVO> openWrkDefSearchAction(ProductWorkMngVO productWorkMngVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return productWorkMngMapper.openWrkDefSearchAction(productWorkMngVO, start, end);
	}
	
	@Override
	public int openWrkDefSearchActionCnt(ProductWorkMngVO productWorkMngVO) {
		return productWorkMngMapper.openWrkDefSearchActionCnt(productWorkMngVO);
	}		
	
	@Override
	public int insertProdWrk(ProductWorkMngVO productWorkMngVO) {
		return productWorkMngMapper.insertProdWrk(productWorkMngVO);
	}	
	
	@Override
	public String getWrkSeqNo(ProductWorkMngVO productWorkMngVO){
		return productWorkMngMapper.getWrkSeqNo(productWorkMngVO);
	}	
	
	@Override
	public int updateProdWrk(ProductWorkMngVO productWorkMngVO) {
		return productWorkMngMapper.updateProdWrk(productWorkMngVO);
	}	
	@Override
	public int deleteProdWrk(ProductWorkMngVO productWorkMngVO) {
		return productWorkMngMapper.deleteProdWrk(productWorkMngVO);
	}	
	
}
