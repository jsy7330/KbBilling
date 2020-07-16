package com.ntels.ccbs.distribute.service.logistics.inventoryMoveChangeMng.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.distribute.domain.logistics.inventoryMoveChangeMng.ProductStatusVO;
import com.ntels.ccbs.distribute.mapper.logistics.inventoryMoveChangeMng.ProductStatusMapper;
import com.ntels.ccbs.distribute.service.logistics.inventoryMoveChangeMng.ProductStatusService;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class ProductStatusServiceImpl implements ProductStatusService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductStatusMapper productStatusMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public List<ProductStatusVO> eqtStatList(ProductStatusVO productStatusVO,
			int page, int perPage) {
		
		List<ProductStatusVO> returnList = new ArrayList<ProductStatusVO>();
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		if(productStatusVO.getItemTpCd().equals("C")){ 
			returnList = productStatusMapper.eqtStatList(productStatusVO, start, end);
		}else if(productStatusVO.getItemTpCd().equals("U")){
			returnList = productStatusMapper.usimStatList(productStatusVO, start, end);
		}else if(productStatusVO.getItemTpCd().equals("V")){
			returnList = productStatusMapper.veqtStatList(productStatusVO, start, end);
		}
		
		return returnList;
	}

	@Override
	public int eqtStatCount(ProductStatusVO productStatusVO) {
		
		int count = 0;
		
		if(productStatusVO.getItemTpCd().equals("C")){
			count = productStatusMapper.eqtStatCount(productStatusVO);
		}else if(productStatusVO.getItemTpCd().equals("U")){
			count = productStatusMapper.usimStatCount(productStatusVO);
		}else if(productStatusVO.getItemTpCd().equals("V")){
			count = productStatusMapper.veqtStatCount(productStatusVO);
		}
		
		return count;
	}

	@Override
	public List<ProductStatusVO> eqtStatInfoList(ProductStatusVO productStatusVO) {
		
		List<ProductStatusVO> returnList = new ArrayList<ProductStatusVO>();
		
		if(productStatusVO.getItemTpCd().equals("C")){ 
			returnList = productStatusMapper.eqtStatInfoList(productStatusVO);
		}else if(productStatusVO.getItemTpCd().equals("U")){
			returnList = productStatusMapper.usimStatInfoList(productStatusVO);
		}else if(productStatusVO.getItemTpCd().equals("V")){
			returnList = productStatusMapper.veqtStatInfoList(productStatusVO);
		}
		
		return returnList;
	}

	@Override
	public int updateEqtStat(List<ProductStatusVO> productStatusVOs, SessionUser session) {
		
		int count = 0;
		String itemTpCd = productStatusVOs.get(0).getItemTpCd();
		Date sysdata = DateUtil.sysdate();
		
		for(ProductStatusVO productStatusVO : productStatusVOs){
			
			productStatusVO.setRegDate(sysdata);
			productStatusVO.setChgDate(sysdata);
			productStatusVO.setUpdProcDttm(DateUtil.getDateStringYYYYMMDDHH24MISS(0));
			
			productStatusVO.setRegrId(session.getUserId());
			productStatusVO.setChgrId(session.getUserId());
			productStatusVO.setUpdProcId(session.getUserId());
			
			if(itemTpCd.equals("C")){
				count += productStatusMapper.updateEqtStat(productStatusVO);
			}else if(itemTpCd.equals("U")){
				count += productStatusMapper.updateUsimStat(productStatusVO);
			}else if(itemTpCd.equals("V")){
				count += productStatusMapper.updateVeqtStat(productStatusVO);
			}
			
			String histSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10);
			productStatusVO.setHistSeq(histSeq);		
			
			productStatusMapper.insertUpdProcHist(productStatusVO);
			
		}
		
		return count;
	}
	
	
	
}
