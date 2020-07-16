package com.ntels.ccbs.distribute.service.logistics.referenceInfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.ProductInfoVO;
import com.ntels.ccbs.distribute.mapper.logistics.referenceInfo.ProductInfoMapper;
import com.ntels.ccbs.distribute.service.logistics.referenceInfo.ProductInfoService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ProductInfoServiceImpl implements ProductInfoService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<ProductInfoVO> productInfoList(ProductInfoVO productInfoVO,	int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return productInfoMapper.productInfoList(productInfoVO, start, end);
	}

	@Override
	public int productInfoCount(ProductInfoVO productInfoVO) {
		return productInfoMapper.productInfoCount(productInfoVO);
	}

	@Override
	public int checkItemId(ProductInfoVO productInfoVO) {
		return productInfoMapper.checkItemId(productInfoVO);
	}

	@Override
	public int insertProductInfo(ProductInfoVO productInfoVO) {
		
		String eqtClCd = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_EQT_CL_ID, "1", 6);
		
		productInfoVO.setEqtClCd(eqtClCd);	//장비유형코드 시퀀스
		productInfoVO.setLoanUsgAmt("0");	//여신사용 금액 디폴트
		
		int count = productInfoMapper.insertProductInfo(productInfoVO);
		
		int prcDtlSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_PRC_DTL_SEQ);
		productInfoVO.setPrcDtlSeq(String.valueOf(prcDtlSeq));
		
		productInfoMapper.insertTdndtMncoUtPrcDtl(productInfoVO);
		
		return count;
	}

	@Override
	public int updateProductInfo(ProductInfoVO productInfoVO) {
		
		int count = productInfoMapper.updateProductInfo(productInfoVO);
		
		if(productInfoVO.getCheckMncoOtptUtPrc().equals("N")){	//매입단가 수정
			
			String eftStrtDt = productInfoVO.getEftStrtDt();
			String eftEndDt = productInfoVO.getEftEndDt();
			
			eftStrtDt = DateUtil.getDateStringYYYYMMDD(-1);
			
			productInfoVO.setEftEndDt(eftStrtDt);
			
			productInfoMapper.updateMncoUtPrcDtlEndDt(productInfoVO);
					
			//효력시작일 원복
			productInfoVO.setEftEndDt(eftEndDt);
			
			int prcDtlSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_PRC_DTL_SEQ);
			productInfoVO.setPrcDtlSeq(String.valueOf(prcDtlSeq));
			
			productInfoMapper.insertTdndtMncoUtPrcDtl(productInfoVO);
		}
		
		
		return count;
	}

	@Override
	public int deleteProductInfo(ProductInfoVO productInfoVO) {
		
		productInfoMapper.deleteTdndtDstrbUtPrcDtl(productInfoVO);
		productInfoMapper.deleteTdndtMncoUtPrcDtl(productInfoVO);
		productInfoMapper.deleteTdndtItemAttrMapp(productInfoVO);
		productInfoMapper.deleteTdndtItemDtl(productInfoVO);
		productInfoMapper.deleteTdndtPckgAcceMapp(productInfoVO);
		
		int count = productInfoMapper.deleteTdndtItem(productInfoVO);
		
		return count;
	}

	
	@Override
	public List<ProductInfoVO> itemAttrList(ProductInfoVO productInfoVO,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return productInfoMapper.itemAttrList(productInfoVO, start, end);
	}

	@Override
	public int itemAttrCount(ProductInfoVO productInfoVO) {
		return productInfoMapper.itemAttrCount(productInfoVO);
	}

	@Override
	public int checkAttrCd(ProductInfoVO productInfoVO) {
		return productInfoMapper.checkAttrCd(productInfoVO);
	}
	
	@Override
	public int insertItemAttr(ProductInfoVO productInfoVO) {
		return productInfoMapper.insertItemAttr(productInfoVO);
	}

	@Override
	public int updateItemAttr(ProductInfoVO productInfoVO) {
		return productInfoMapper.updateItemAttr(productInfoVO);
	}

	@Override
	public int deleteItemAttr(ProductInfoVO productInfoVO) {
		productInfoMapper.deleteItemAttrVal(productInfoVO);
		return productInfoMapper.deleteItemAttr(productInfoVO);
	}

	

	@Override
	public List<ProductInfoVO> itemAttrValList(ProductInfoVO productInfoVO,
		int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return productInfoMapper.itemAttrValList(productInfoVO, start, end);
	}

	@Override
	public int itemAttrValCount(ProductInfoVO productInfoVO) {
		return productInfoMapper.itemAttrValCount(productInfoVO);
	}

	@Override
	public int checkAttrValCd(ProductInfoVO productInfoVO) {
		return productInfoMapper.checkAttrValCd(productInfoVO);
	}
	
	@Override
	public int insertItemAttrVal(ProductInfoVO productInfoVO) {
		return productInfoMapper.insertItemAttrVal(productInfoVO);
	}

	@Override
	public int updateItemAttrVal(ProductInfoVO productInfoVO) {
		return productInfoMapper.updateItemAttrVal(productInfoVO);
	}

	@Override
	public int deleteItemAttrVal(ProductInfoVO productInfoVO) {
		return productInfoMapper.deleteItemAttrVal(productInfoVO);
	}
	

	@Override
	public List<ProductInfoVO> itemAttrMappList(ProductInfoVO productInfoVO,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return productInfoMapper.itemAttrMappList(productInfoVO, start, end);
	}

	@Override
	public int itemAttrMappCount(ProductInfoVO productInfoVO) {
		return productInfoMapper.itemAttrMappCount(productInfoVO);
	}

	@Override
	public List<ProductInfoVO> itemAttrSelectList(ProductInfoVO productInfoVO) {
		return productInfoMapper.itemAttrSelectList(productInfoVO);
	}

	@Override
	public List<ProductInfoVO> itemAttrValSelectList(ProductInfoVO productInfoVO) {
		return productInfoMapper.itemAttrValSelectList(productInfoVO);
	}

	@Override
	public int insertItemAttrMapp(ProductInfoVO productInfoVO) {
		
		Integer seq = sequenceService.createNewSubSequence("TDNDT_ITEM_ATTR_MAPP", "ITEM_ID", productInfoVO.getItemId());
		productInfoVO.setMappOrd(String.valueOf(seq));
		
		return productInfoMapper.insertItemAttrMapp(productInfoVO);
	}

	@Override
	public int updateItemAttrMapp(ProductInfoVO productInfoVO) {
		return productInfoMapper.updateItemAttrMapp(productInfoVO);
	}

	@Override
	public int deleteItemAttrMapp(ProductInfoVO productInfoVO) {
		return productInfoMapper.deleteTdndtItemAttrMapp(productInfoVO);
	}

	@Override
	public List<ProductInfoVO> itemDtlList(ProductInfoVO productInfoVO,
			int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return productInfoMapper.itemDtlList(productInfoVO, start, end);
	}

	@Override
	public int itemDtlCount(ProductInfoVO productInfoVO) {
		return productInfoMapper.itemDtlCount(productInfoVO);
	}

	@Override
	public int insertItemDtl(ProductInfoVO productInfoVO) {
		
		Integer seq = sequenceService.createNewSubSequence("TDNDT_ITEM_DTL", "ITEM_ID", productInfoVO.getItemId());
		productInfoVO.setDtlInfoOrd(String.valueOf(seq));
		
		return productInfoMapper.insertItemDtl(productInfoVO);
	}

	@Override
	public int updateItemDtl(ProductInfoVO productInfoVO) {
		return productInfoMapper.updateItemDtl(productInfoVO);
	}

	@Override
	public int deleteItemDtl(ProductInfoVO productInfoVO) {
		return productInfoMapper.deleteTdndtItemDtl(productInfoVO);
	}
	
	
	
	
}
