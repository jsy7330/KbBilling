package com.ntels.ccbs.distribute.service.logistics.voucherMgt.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.UpdProcHistVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VeqtVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VissueVO;
import com.ntels.ccbs.distribute.domain.logistics.voucherMgt.VoucherMasterVO;
import com.ntels.ccbs.distribute.mapper.logistics.voucherMgt.VoucherMasterMngMapper;
import com.ntels.ccbs.distribute.service.logistics.voucherMgt.VoucherMasterMngService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

/**
 * 
 * <PRE>
 * 1. ClassName: VoucherMasterMngServiceImpl
 * 2. FileName : VoucherMasterMngServiceImpl.java
 * 3. Package  : com.ntels.ccbs.distribute.service.logistics.voucherMgt.impl
 * 4. Comment  : 바우처마스터관리 서비스
 * 5. 작성자   : Cashyalla
 * 6. 작성일   : 2016. 8. 19. 오후 1:29:26
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     Cashyalla :    2016. 8. 19.    : 신규개발
 * </PRE>
 */
@Service
public class VoucherMasterMngServiceImpl implements VoucherMasterMngService {

	@Autowired
	private VoucherMasterMngMapper voucherMasterMngMapper;
	
	@Autowired
	private SequenceService sequenceService;

	@Override
	public Integer vissueListCount(VissueVO vissueVO) {
		Integer count = voucherMasterMngMapper.vissueListCount(vissueVO);
		return count == null ? 0 : count;
	}
	
	@Override
	public List<VissueVO> getVissueList(VissueVO vissueVO) {
		
		int start = (vissueVO.getPage() -1 ) * vissueVO.getPerPage();
		int end = vissueVO.getPerPage();
		
		return voucherMasterMngMapper.getVissueList(vissueVO, start, end);
	}
	
	@Override
	public Integer getVeqtInfoCount(VissueVO vissueVO) {
		Integer count = voucherMasterMngMapper.getVeqtInfoCount(vissueVO);
		return count == null ? 0 : count;
	}
	
	@Override
	public List<VoucherMasterVO> getVeqtInfoList(VissueVO vissueVO) {
		int start = (vissueVO.getPage() -1 ) * vissueVO.getPerPage();
		int end = vissueVO.getPerPage();
		
		return voucherMasterMngMapper.getVeqtInfoList(vissueVO, start, end);
	}
	
	@Override
	public Integer getSaleProcessPopVeqtCount(VoucherMasterVO voucherMaster) {
		Integer count = voucherMasterMngMapper.getSaleProcessPopVeqtCount(voucherMaster);
		return count == null ? 0 : count;
	}
	
	@Override
	public List<VoucherMasterVO> getSalesProcessPopVeqtList(VoucherMasterVO voucherMaster) {
		int start = (voucherMaster.getPage() -1 ) *voucherMaster.getPerPage();
		int end = voucherMaster.getPerPage();
		
		return voucherMasterMngMapper.getSalesProcessPopVeqtList(voucherMaster, start, end);
	}
	
	private int addVeqtTransInfoInit(VoucherMasterVO voucherMasterVO) {
		VeqtVO searchVO = new VeqtVO();
		searchVO.setSoId(voucherMasterVO.getSoId());
		searchVO.setVoSeqNo(voucherMasterVO.getVoSeqNo());
		
		List<VeqtVO> veqtTransInfoList = voucherMasterMngMapper.getVeqtTransInfoInit_voSeqNo(searchVO);
		
		int insertCount = 0;
		
		for (VeqtVO veqtVO : veqtTransInfoList) {
			String maxCrtSeqNo = voucherMasterMngMapper.getMaxCrtSeqNo(searchVO);
			String newCrtSeqNo = String.format("%010d", Integer.parseInt(maxCrtSeqNo) + 1);
			
			veqtVO.setCrtSeqNo(newCrtSeqNo);
			
			voucherMasterMngMapper.addVeqtTransInfoInit_voSeqNo(veqtVO);
		}
		
		return insertCount;
	}
	
	@Override
	public void saveSalesProcessVeqtInfo(List<VoucherMasterVO> voucherMasterList) {
		
		Date now = new Date();
		String nowDttm = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		for (VoucherMasterVO voucherMasterVO : voucherMasterList) {
			if (StringUtils.isEmpty(voucherMasterVO.getLgstProcStatCd()) == false) {
				voucherMasterVO.setLgstProcDttm(nowDttm);
			}
			
			voucherMasterVO.setChgDate(now);
			voucherMasterVO.setRegDate(now);
			voucherMasterVO.setActTp("003");
			
			voucherMasterMngMapper.updateVeqtInfoVoStatLgst(voucherMasterVO);
			
			addVeqtTransInfoInit(voucherMasterVO);
			
			String histSeq = sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, 10);
			
			UpdProcHistVO updProcHistVO = new UpdProcHistVO();
            
			updProcHistVO.setHistSeq(histSeq);
            updProcHistVO.setSoId(voucherMasterVO.getSoId());
            updProcHistVO.setUpdProcClCd("20");
            updProcHistVO.setItemId(voucherMasterVO.getItemId());
            updProcHistVO.setEqtSeq(voucherMasterVO.getVoSeqNo());
            updProcHistVO.setUsimSeq("");
            updProcHistVO.setUpdBfrCd(voucherMasterVO.getVoStatCd());
            updProcHistVO.setAftrUpdCd(voucherMasterVO.getAftrUpdCd());
            updProcHistVO.setUpdProcId(voucherMasterVO.getCtChgId());
            updProcHistVO.setRegrId(voucherMasterVO.getRegrId());
            updProcHistVO.setRegDate(now);
            updProcHistVO.setChgrId(voucherMasterVO.getCtChgId());
            updProcHistVO.setChgDate(now);
            
            voucherMasterMngMapper.insertUpdProcHistInfo(updProcHistVO);
		}
	}

}
