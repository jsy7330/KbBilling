package com.ntels.ccbs.system.service.configuration.codeMng.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeLngVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonCodeVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpLngVO;
import com.ntels.ccbs.system.domain.configuration.codeMng.CommonGrpVO;
import com.ntels.ccbs.system.mapper.configuration.codeMng.CommonCodeMngMapper;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.configuration.codeMng.CommonCodeMngService;

@Service
public class CommonCodeMngServiceImpl implements CommonCodeMngService {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private CommonCodeMngMapper commonCodeMngMapper;

	@Override
	public List<Map<String, Object>> getCodeGrpTreeList(String lng) {
		
		List<Map<String,Object>> codeGrpTypList = new ArrayList<Map<String,Object>>();
		List<CommonCodeVO> codeTypList = commonDataService.getCommonCodeList("SY00006", lng);
		int parentIndex = 0;
		for(CommonCodeVO codeTyp : codeTypList){
			parentIndex++;
			Map<String,Object> codeGrpTyp = new HashMap<String,Object>();
			codeGrpTyp.put("title", codeTyp.getCommonCdNm());
			codeGrpTyp.put("isFolder", true);
			codeGrpTyp.put("key", codeTyp.getCommonCd());
			codeGrpTyp.put("name", codeTyp.getCommonCdNm());
			codeGrpTyp.put("systemId", codeTyp.getCommonCd());
			codeGrpTyp.put("systemNm", codeTyp.getCommonCdNm());
			codeGrpTyp.put("expand", false);
			codeGrpTyp.put("order", parentIndex);
			
			List<CommonGrpVO> codeGrpList = commonCodeMngMapper.getCodeGrpList(codeTyp.getCommonCd(), lng);
			List<Map<String,Object>> childList = new ArrayList<Map<String,Object>>();
			int childIndex = 0;
			for(CommonGrpVO commonGrp : codeGrpList){
				childIndex++;
				
				Map<String,Object> codeGrp = new HashMap<String,Object>();
				StringBuilder sb = new StringBuilder();
				sb.append(commonGrp.getCommonGrp());
				sb.append("_");
				sb.append(commonGrp.getCommonGrpNm());
				codeGrp.put("title", sb);
				codeGrp.put("isFolder", false);
				codeGrp.put("key", commonGrp.getCommonGrp());
				codeGrp.put("name", commonGrp.getCommonGrpNm());
				codeGrp.put("systemId", codeTyp.getCommonCd());
				codeGrp.put("systemNm", codeTyp.getCommonCdNm());
				codeGrp.put("order", childIndex);
				
				childList.add(codeGrp);
			}
			codeGrpTyp.put("children", childList);
			codeGrpTypList.add(codeGrpTyp);
		}
		return codeGrpTypList;
	}

	@Override
	public List<CommonCodeVO> getCommonCodeList(String condGroupId,
			String sidx,
			String sord,
	        String lng) {
		
		List<CommonCodeVO> commonCodeList = commonCodeMngMapper.getCommonCodeList(condGroupId, sidx, sord, lng);
		for(CommonCodeVO code:commonCodeList){
			code.setCodeLngList(commonCodeMngMapper.getCodeDetailLngList(code.getCommonGrp(), code.getCommonCd()));
		}
		return commonCodeList;
	}

	@Override
	public List<Map<String, Object>> getLngList() {
		return commonCodeMngMapper.getLngList();
	}

	@Override
	public void insertCodeDetailInfo(CommonCodeVO insertCodeDetal) {
		//코드 중복 체크
		int codeCnt = commonCodeMngMapper.getCodeDetailCnt(insertCodeDetal.getCommonGrp(), insertCodeDetal.getCommonCd());
		
		if(codeCnt > 0){
			throw new ServiceException( "MSG.M03.MSG00018" );
		}
		int insCnt = commonCodeMngMapper.insertCodeDetail(insertCodeDetal);
		
		if(insCnt == 1){
			List<Map<String,Object>> lngList = insertCodeDetal.getCodeLngList();
			
			for(Map<String,Object> lngInfo : lngList){
				CommonCodeLngVO lng = new CommonCodeLngVO();
				lng.setCommonGrp(insertCodeDetal.getCommonGrp());
				lng.setCommonCd(insertCodeDetal.getCommonCd());
				lng.setLngTyp((String)lngInfo.get("language_code"));
				lng.setCodeNm((String)lngInfo.get("code_nm"));
				lng.setRegrId(insertCodeDetal.getRegrId());
				lng.setRegDate(insertCodeDetal.getRegDate());
				lng.setChgrId(insertCodeDetal.getChgrId());
				lng.setChgDate(insertCodeDetal.getChgDate());
				
				int insLngCnt = commonCodeMngMapper.insertCodeDetailLng(lng);
				
			}
		}
		
	}

	@Override
	public void deleteCodeDetail(String codeGrp, String code) {
		int deleteCodeCnt = commonCodeMngMapper.deleteCodeDetail(codeGrp, code);
		int deleteCodeLngCnt = commonCodeMngMapper.deleteCodeDetailLng(codeGrp, code);
	}

	@Override
	public void updateCodeDetailInfo(CommonCodeVO updateCodeDetail) {
		int updateCnt = commonCodeMngMapper.updateCodeDetail(updateCodeDetail);
		
		if(updateCnt == 1){
			for(Map<String,Object> lngInfo : updateCodeDetail.getCodeLngList()){
				
				CommonCodeLngVO lng = new CommonCodeLngVO();
				lng.setCommonGrp(updateCodeDetail.getCommonGrp());
				lng.setCommonCd(updateCodeDetail.getCommonCd());
				lng.setLngTyp((String)lngInfo.get("language_code"));
				lng.setCodeNm((String)lngInfo.get("code_nm"));
				lng.setRegrId(updateCodeDetail.getChgrId());
				lng.setRegDate(updateCodeDetail.getChgDate());
				lng.setChgrId(updateCodeDetail.getChgrId());
				lng.setChgDate(updateCodeDetail.getChgDate());
				int updateLngCnt = commonCodeMngMapper.updateCodeDetailLng(lng);
				if(updateLngCnt == 0){
					int insLngCnt = commonCodeMngMapper.insertCodeDetailLng(lng);
				}
			}
		}
	}

	@Override
	public void insertCodeGrpInfo(CommonGrpVO insertCodeGrpInfo) {
		//그룹 중복 체크
		int codeCnt = commonCodeMngMapper.getCodeGrpCnt(insertCodeGrpInfo.getCommonGrp());
		
		if(codeCnt > 0){
			throw new ServiceException( "MSG.M03.MSG00018" );
		}
		int insCnt = commonCodeMngMapper.insertCodeGrp(insertCodeGrpInfo);
		
		if(insCnt == 1){
			List<Map<String,Object>> lngList = insertCodeGrpInfo.getCodeLngList();
			
			for(Map<String,Object> lngInfo : lngList){
				CommonGrpLngVO lng = new CommonGrpLngVO();
				lng.setLngTyp((String)lngInfo.get("language_code"));
				lng.setCommonGrp(insertCodeGrpInfo.getCommonGrp());
				lng.setCommonGrpNm((String)lngInfo.get("code_nm"));
				lng.setRegrId(insertCodeGrpInfo.getRegrId());
				lng.setRegDate(insertCodeGrpInfo.getRegDate());
				lng.setChgrId(insertCodeGrpInfo.getChgrId());
				lng.setChgDate(insertCodeGrpInfo.getChgDate());
				
				int insLngCnt = commonCodeMngMapper.insertCodeGrpLng(lng);
				
			}
		}
		
	}

	@Override
	public CommonGrpVO getCodeGrpInfo(String codeGrp) {
		CommonGrpVO commonGrp = commonCodeMngMapper.getCodeGrpInfo(codeGrp);
		commonGrp.setCodeLngList(commonCodeMngMapper.getCodeGrpLngList(codeGrp));
		return commonGrp;
	}

	@Override
	public void updateCodeGrpInfo(CommonGrpVO updateCodeGrpInfo) {
		int uptCnt = commonCodeMngMapper.updateCodeGrp(updateCodeGrpInfo);
		
		if(uptCnt == 1){
			List<Map<String,Object>> lngList = updateCodeGrpInfo.getCodeLngList();
			
			for(Map<String,Object> lngInfo : lngList){
				CommonGrpLngVO lng = new CommonGrpLngVO();
				lng.setLngTyp((String)lngInfo.get("language_code"));
				lng.setCommonGrp(updateCodeGrpInfo.getCommonGrp());
				lng.setCommonGrpNm((String)lngInfo.get("code_nm"));
				lng.setRegrId(updateCodeGrpInfo.getRegrId());
				lng.setRegDate(updateCodeGrpInfo.getRegDate());
				lng.setChgrId(updateCodeGrpInfo.getChgrId());
				lng.setChgDate(updateCodeGrpInfo.getChgDate());
				
				int uptLngCnt = commonCodeMngMapper.updateCodeGrpLng(lng);
				
				if(uptLngCnt == 0){
					int insLngCnt = commonCodeMngMapper.insertCodeGrpLng(lng);
				}
				
			}
		}
		
	}

	@Override
	public void deleteCodeGrp(String codeGrp) {
		int deleteCodeGrpCnt = commonCodeMngMapper.deleteCodeGrp(codeGrp);
		int deleteCodeGrpLngCnt = commonCodeMngMapper.deleteCodeGrpLng(codeGrp);
		
		int deleteCodeCnt = commonCodeMngMapper.deleteCode(codeGrp);
		int deleteCodeLngCnt = commonCodeMngMapper.deleteCodeLng(codeGrp);
	}
}