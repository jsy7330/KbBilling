package com.ntels.ccbs.product.service.refInfo.commonInfo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.product.domain.refInfo.commonInfo.Factor;
import com.ntels.ccbs.product.mapper.refInfo.commonInfo.FactorMapper;
import com.ntels.ccbs.product.service.refInfo.commonInfo.FactorService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class FactorServiceImpl implements FactorService {
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FactorMapper factorMapper;

	@Autowired
	private SequenceService sequenceSevice;	
	
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
    String currentDay =  DateUtil.getDateStringYYYYMMDD(0);
    
    public static Date getPreviousDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, -1);  
          
        return cal.getTime();  
    }   
    
    public static Date getNextDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
          
        cal.add(Calendar.DATE, +1);  
          
        return cal.getTime();  
    }	
	@Override
	public int totalFactorListCount(Factor factor) {
		return factorMapper.totalFactorListCount(factor);
	}

	@Override
	public List<Factor> totalFactorList(Factor factor, int page, int perPage) {
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return factorMapper.totalFactorList(factor, start, end);
	}

	@Override
	public String fctrNameCheck(Factor factor) {
		int count = -1;
		
		count = factorMapper.fctrNameCheck(factor);
		
		return String.valueOf(count);
	}

	@Override
	public List<Factor> getRefColmnIdComboList(Factor factor) {
		return factorMapper.getRefColmnIdComboList(factor);
	}

	@Override
	public String addTotalFactor(Factor factor) {
		int count = -1;
		int dataCnt = factorMapper.getTotalFactorDupCnt(factor);
		
		if(dataCnt == 0) {
			String fctrCd = sequenceSevice.createNewSequence(Consts.SEQ_CODE.PD_TPMBI_FCTR, "FT", 5);
			factor.setFctrCd(fctrCd);
			count = factorMapper.addTotalFactor(factor);
		}
		return String.valueOf(count);
	}

	@Override
	public List<Factor> getCommonGrpPopupList(Factor factor) {
		return factorMapper.getCommonGrpPopupList(factor);
	}

	@Override
	public Factor getTotalFactor(Factor factor) {
		return factorMapper.getTotalFactor(factor);
	}

	@Override
	public String modTotalFactor(Factor factor) {
		int count = -1;
		
		int totalFactorCnt = factorMapper.getTotalFactorDupExpCnt(factor);
		
		count = factorMapper.allmodTotalFactor(factor); //TPMPD_SVC_RATE_ITM_TYP_FCTR
		//count = factorMapper.allmodTotalFactor1(factor); //TP_PROD_SUBSCRIP_FCTR
		count = factorMapper.allmodTotalFactor2(factor); //TPMPD_RATE_ITM_FCTR 
		count = factorMapper.allmodTotalFactor3(factor); //TPMPD_D_RATE_ITM_FCTR
		
		//종료 일자를 유효시작일자 전 날로 설정
		Date tmpInactDt = null;
		try {
			tmpInactDt = transFormat.parse(factor.getActDt());
		} catch (ParseException e) {
			
			logger.error("error", e);
		}
		
		if (totalFactorCnt == 0) {
			if (factor.getInActYn().equals(Consts.SVC_MGT_CODE.EXPIRE_NO)) //EXPIRE_NO : 1 => 변경 / 2 -> 종료
			{
				if (factor.getActDt().equals(factor.getBaseActDt())) {
					count = factorMapper.modTotalFactor(factor);
				} else {
					factor.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
				
					String inactDt = factor.getInactDt();
					
					factor.setInactDt(transFormat.format(getPreviousDate(tmpInactDt)));
					count = factorMapper.modTotalFactorInactDt(factor);
					factor.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_YES);
					factor.setInactDt(inactDt);
					count = factorMapper.addTotalFactor(factor);
				}
				
			} else {
				factor.setInactDt(transFormat.format(getNextDate(tmpInactDt)));
				factor.setMstrFl(Consts.SVC_MGT_CODE.MSTR_FL_NO);
				count = factorMapper.modTotalFactorInactDtation(factor);
			}
			
		} else {
			count = -1;
		}
		
		return String.valueOf(count);
	}
	
	
	
	
	
	
	
}
