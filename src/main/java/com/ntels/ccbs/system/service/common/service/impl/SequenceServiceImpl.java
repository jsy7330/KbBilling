package com.ntels.ccbs.system.service.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.mapper.common.service.SequenceMapper;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private SequenceMapper sequenceMapper;
	
	@Override
	public Integer createNewSequence(String modCd) {
		int updCnt = sequenceMapper.updateNextSequence(modCd);
		Integer sequence = sequenceMapper.getSequence(modCd);
		return sequence;
	}

	@Override
	public String createNewSequence(String modCd, String prefix, int size) {
		if(StringUtils.isEmpty(prefix)){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		if((size - prefix.length()) < 1 ){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		int updCnt = sequenceMapper.updateNextSequence(modCd);
		Integer sequence = sequenceMapper.getSequence(modCd);
		String seq = prefix + StringUtils.leftPad(sequence.toString(), size - prefix.length(), "0");
		return seq;
	}
	
	@Override
	public String createNewSequence(String modCd, int size) {
		int updCnt = sequenceMapper.updateNextSequence(modCd);
		Integer sequence = sequenceMapper.getSequence(modCd);
		String seq = StringUtils.leftPad(sequence.toString(), size, "0");
		return seq;
	}


	@Override
	public String createNewSequenceDaily(String modCd, String dateFormat, int size) {
		if(StringUtils.isEmpty(dateFormat)){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		if(!Consts.SEQ_DATE_FORMAT.FORMAT_YYMMDD.equals(dateFormat.toUpperCase()) && 
				!Consts.SEQ_DATE_FORMAT.FORMAT_YYYYMMDD.equals(dateFormat.toUpperCase())){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		if((size - dateFormat.length()) < 1 ){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		int updCnt = sequenceMapper.updateNextSequence(modCd);
		
		Map<String,Object> dateMap = sequenceMapper.getNowDate(modCd);
		Calendar today = Calendar.getInstance(); 
		if(today.get(Calendar.DATE) != Integer.parseInt(dateMap.get("CURR_DD").toString())){
			Map<String,Object> nextDateMap = new HashMap<String, Object>();
			nextDateMap.put("MOD_CD", modCd);
			nextDateMap.put("CURR_YYYY", today.get(Calendar.YEAR));
			nextDateMap.put("CURR_MM", today.get(Calendar.MONTH) + 1);
			nextDateMap.put("CURR_DD", today.get(Calendar.DATE));
			nextDateMap.put("CURR_NO", 1);
			int updDailyCnt = sequenceMapper.updateCycleSequence(nextDateMap);
		}
		
		Integer sequence = sequenceMapper.getSequence(modCd);
		
		SimpleDateFormat format;
		if(Consts.SEQ_DATE_FORMAT.FORMAT_YYYYMMDD.equals(dateFormat.toUpperCase())){
			format = new SimpleDateFormat("yyyyMMdd");
		}else{
			format = new SimpleDateFormat("yyMMdd");
		}
		String seq = format.format(today.getTime()) + StringUtils.leftPad(sequence.toString(), size - dateFormat.length(), "0");
		return seq;
	}

	@Override
	public String createNewSequenceMonthly(String modCd, String dateFormat,
			int size) {
		if(StringUtils.isEmpty(dateFormat)){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		if(!Consts.SEQ_DATE_FORMAT.FORMAT_YYMM.equals(dateFormat.toUpperCase()) && 
				!Consts.SEQ_DATE_FORMAT.FORMAT_YYYYMM.equals(dateFormat.toUpperCase())){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		if((size - dateFormat.length()) < 1 ){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}

		int updCnt = sequenceMapper.updateNextSequence(modCd);
		
		Map<String,Object> dateMap = sequenceMapper.getNowDate(modCd);
		Calendar today = Calendar.getInstance(); 
		if((today.get(Calendar.MONTH) + 1) != Integer.parseInt(dateMap.get("CURR_MM").toString())){
			Map<String,Object> nextDateMap = new HashMap<String, Object>();
			nextDateMap.put("MOD_CD", modCd);
			nextDateMap.put("CURR_YYYY", today.get(Calendar.YEAR));
			nextDateMap.put("CURR_MM", today.get(Calendar.MONTH) + 1);
			nextDateMap.put("CURR_DD", 0);
			nextDateMap.put("CURR_NO", 1);
			int updDailyCnt = sequenceMapper.updateCycleSequence(nextDateMap);
		}
		
		Integer sequence = sequenceMapper.getSequence(modCd);
		
		SimpleDateFormat format;
		if(Consts.SEQ_DATE_FORMAT.FORMAT_YYYYMM.equals(dateFormat.toUpperCase())){
			format = new SimpleDateFormat("yyyyMM");
		}else{
			format = new SimpleDateFormat("yyMM");
		}
		String seq = format.format(today.getTime()) + StringUtils.leftPad(sequence.toString(), size - dateFormat.length(), "0");
		
		return seq;
	}

	@Override
	public String createNewSequenceYealy(String modCd, String dateFormat,
			int size) {
		if(StringUtils.isEmpty(dateFormat)){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		if(!Consts.SEQ_DATE_FORMAT.FORMAT_YY.equals(dateFormat.toUpperCase()) && 
				!Consts.SEQ_DATE_FORMAT.FORMAT_YYYY.equals(dateFormat.toUpperCase())){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		if((size - dateFormat.length()) < 1 ){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		int updCnt = sequenceMapper.updateNextSequence(modCd);
		
		Map<String,Object> dateMap = sequenceMapper.getNowDate(modCd);
		Calendar today = Calendar.getInstance(); 
		if(today.get(Calendar.YEAR) != Integer.parseInt(dateMap.get("CURR_YYYY").toString())){
			Map<String,Object> nextDateMap = new HashMap<String, Object>();
			nextDateMap.put("MOD_CD", modCd);
			nextDateMap.put("CURR_YYYY", today.get(Calendar.YEAR));
			nextDateMap.put("CURR_MM", 0);
			nextDateMap.put("CURR_DD", 0);
			nextDateMap.put("CURR_NO", 1);
			int updDailyCnt = sequenceMapper.updateCycleSequence(nextDateMap);
		}
		
		Integer sequence = sequenceMapper.getSequence(modCd);
		
		SimpleDateFormat format;
		if(Consts.SEQ_DATE_FORMAT.FORMAT_YYYY.equals(dateFormat.toUpperCase())){
			format = new SimpleDateFormat("yyyy");
		}else{
			format = new SimpleDateFormat("yy");
		}
		String seq = format.format(today.getTime()) + StringUtils.leftPad(sequence.toString(), size - dateFormat.length(), "0");
		
		return seq;
	}

	@Override
	public Integer createNewSubSequence(String targetTblNm, String targetColNm,
	        String keyCode) {
		int updCnt = sequenceMapper.updateNextSubSequence(targetTblNm, targetColNm, keyCode);
		if(updCnt == 0){
			int insCnt = sequenceMapper.insertSubSequence(targetTblNm, targetColNm, keyCode);
		}
		Integer sequence = sequenceMapper.getSubSequence(targetTblNm, targetColNm, keyCode);
		return sequence;
	}

	@Override
	public Integer createNewSequenceDaily(String modCd) {
		int updCnt = sequenceMapper.updateNextSequence(modCd);
		
		Map<String,Object> dateMap = sequenceMapper.getNowDate(modCd);
		Calendar today = Calendar.getInstance(); 
		if(today.get(Calendar.DATE) != Integer.parseInt(dateMap.get("CURR_DD").toString())){
			Map<String,Object> nextDateMap = new HashMap<String, Object>();
			nextDateMap.put("MOD_CD", modCd);
			nextDateMap.put("CURR_YYYY", today.get(Calendar.YEAR));
			nextDateMap.put("CURR_MM", today.get(Calendar.MONTH) + 1);
			nextDateMap.put("CURR_DD", today.get(Calendar.DATE));
			nextDateMap.put("CURR_NO", 1);
			int updDailyCnt = sequenceMapper.updateCycleSequence(nextDateMap);
		}
		
		Integer sequence = sequenceMapper.getSequence(modCd);
		return sequence;
	}

	@Override
	public List<String> createNewSequence(String modCd, String prefix, int size,
	        int count) {
		if(StringUtils.isEmpty(prefix)){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		if((size - prefix.length()) < 1 ){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		if(count == 0){
			throw new ServiceException("error.msg.fail_get_sequence") ;
		}
		
		sequenceMapper.updateNextSequence(modCd);
		Integer startSeq = sequenceMapper.getSequence(modCd);
		
		sequenceMapper.updateNextSequenceMulti(modCd, count-1);
		
		List<String> seqList = new ArrayList<String>();
		for(int i=0; i < count; i++){
			String seq = prefix + StringUtils.leftPad(startSeq.toString(), size - prefix.length(), "0");
			seqList.add(seq);
			startSeq++;
		}
		return seqList;
	}


	
	
}
