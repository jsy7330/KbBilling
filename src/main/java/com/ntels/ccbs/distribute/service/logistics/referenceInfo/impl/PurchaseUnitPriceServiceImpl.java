package com.ntels.ccbs.distribute.service.logistics.referenceInfo.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.distribute.domain.logistics.referenceInfo.PurchaseUnitPriceVO;
import com.ntels.ccbs.distribute.mapper.logistics.referenceInfo.PurchaseUnitPriceMapper;
import com.ntels.ccbs.distribute.service.logistics.referenceInfo.PurchaseUnitPriceService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class PurchaseUnitPriceServiceImpl implements PurchaseUnitPriceService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PurchaseUnitPriceMapper purchaseUnitPriceMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<PurchaseUnitPriceVO> purchaseUnitPriceList(
			PurchaseUnitPriceVO purchaseUnitPriceVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return purchaseUnitPriceMapper.purchaseUnitPriceList(purchaseUnitPriceVO, start, end);
	}

	@Override
	public int purchaseUnitPriceCount(PurchaseUnitPriceVO purchaseUnitPriceVO) {
		return purchaseUnitPriceMapper.purchaseUnitPriceCount(purchaseUnitPriceVO);
	}

	@Override
	public List<PurchaseUnitPriceVO> mncoUtPrcDtlList(
			PurchaseUnitPriceVO purchaseUnitPriceVO, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return purchaseUnitPriceMapper.mncoUtPrcDtlList(purchaseUnitPriceVO, start, end);
	}

	@Override
	public int mncoUtPrcDtlCount(PurchaseUnitPriceVO purchaseUnitPriceVO) {
		return purchaseUnitPriceMapper.mncoUtPrcDtlCount(purchaseUnitPriceVO);
	}

	@Override
	public int insertMncoUtPrcDtl(PurchaseUnitPriceVO purchaseUnitPriceVO) {
		
		//효력시작일 하루 전날짜로 효력 종료일을 업데이트
		long chStart = 0;  
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		    
		String eftStrtDt = purchaseUnitPriceVO.getEftStrtDt();
		String eftEndDt = purchaseUnitPriceVO.getEftEndDt();
				
		try {
			chStart = df.parse(eftStrtDt).getTime();	//스트링형 date를 long형의 함수로 컨버트하고
			chStart -= 86400000;					//24*60*60*1000 하루치의 숫자를 빼준다
                                   
			Date date = new Date(chStart);			//이것을 다시 날짜형태로 바꿔주고
			eftStrtDt = df.format(date);			//바꿔준 날짜를 yyyyMMdd형으로 바꾼후 스트링으로 다시 형변환을해서 date에 대입
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		purchaseUnitPriceVO.setEftEndDt(eftStrtDt);
		
		purchaseUnitPriceMapper.updateMncoUtPrcDtlEndDt(purchaseUnitPriceVO);
				
		//효력시작일 원복
		purchaseUnitPriceVO.setEftEndDt(eftEndDt);
		
		int prcDtlSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_PRC_DTL_SEQ);
		purchaseUnitPriceVO.setPrcDtlSeq(String.valueOf(prcDtlSeq));
		
		int count = purchaseUnitPriceMapper.insertMncoUtPrcDtl(purchaseUnitPriceVO);
		
		purchaseUnitPriceMapper.updateProductInfoPrc(purchaseUnitPriceVO);
		
		return count;
	}

	@Override
	public int updateMncoUtPrcDtl(PurchaseUnitPriceVO purchaseUnitPriceVO) {
		return purchaseUnitPriceMapper.updateMncoUtPrcDtl(purchaseUnitPriceVO);
	}

	@Override
	public int deleteMncoUtPrcDtl(PurchaseUnitPriceVO purchaseUnitPriceVO) {
		return purchaseUnitPriceMapper.deleteMncoUtPrcDtl(purchaseUnitPriceVO);
	}

	
}
