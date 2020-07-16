package com.ntels.ifg.httpRest.service.distribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.service.common.service.SequenceService;
import com.ntels.ifg.common.exception.InterfaceException;
import com.ntels.ifg.httpRest.domain.DataSetVO;
import com.ntels.ifg.httpRest.domain.DataVO;
import com.ntels.ifg.httpRest.domain.RequestVO;
import com.ntels.ifg.httpRest.domain.ResponseVO;
import com.ntels.ifg.httpRest.mapper.distribute.DistributeMapper;
import com.ntels.ifg.httpRest.service.InterfaceProcessService;

@Service
public class DistributeDN0004ServiceImpl implements InterfaceProcessService {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private DistributeMapper distributeMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	private static final String SVC_CODE = "DN";
	private static final String OP_CODE = "DN0004";
	

	@Override
	public ResponseVO processInterface(String svcCode, String opCode, RequestVO request) {
		if(request != null && (SVC_CODE.equals(svcCode) && OP_CODE.equals(opCode)) == false){
			return null;
		}
		
		ResponseVO response = new ResponseVO();
		DataSetVO dataSet = new DataSetVO();
		List<DataVO> datas = new ArrayList<DataVO>();
		DataVO data = new DataVO();
		List<DataVO> requestDataList =  request.getDataSet().getDatas();
		
		if(requestDataList == null || requestDataList.size() == 0){
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		if(requestDataList.get(0).getMapList() == null){
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		Map<String, String> result = new HashMap<String,String>();
		try{
			List<Map<String, String>> list = requestDataList.get(0).getMapList();
			
			
			for(Map<String,String> parameter :list){
				String itemTyp = (String)parameter.get("item_tp_cd");
				if("C".equals(itemTyp)){
					//단말기 이전 상태를 조회
					Map<String,String> status = distributeMapper.getDtItemCancle(parameter.get("item_id"),parameter.get("eqt_seq"));
					
					//이전 상태가 NULL이면 Null
					if(status == null){
						result.put("RESULT", "D");
					}else{
						parameter.put("lgst_proc_stat_cd", status.get("LGST_PROC_STAT_CD"));
						parameter.put("upd_bfr_cd", status.get("UPD_BFR_CD"));
						parameter.put("eqt_seq", parameter.get("eqt_seq"));
						parameter.put("so_id", status.get("SO_ID"));
						parameter.put("seq", sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10));
						
						distributeMapper.updateItemCancel(parameter, DateUtil.sysdate(),DateUtil.getDateStringYYYYMMDDHH24MISS(0));
						distributeMapper.insertProcHistCancel(parameter, DateUtil.sysdate(),DateUtil.getDateStringYYYYMMDDHH24MISS(0));

						result.put("RESULT", "Y");
					}
				}else if("U".equals(itemTyp)){
					//USIM 이전 상태 조회
					Map<String,String> status = distributeMapper.getDtUsimCancle(parameter.get("item_id"),parameter.get("eqt_seq"));
					//이전 상태가 NULL이면 Null
					if(status == null){
						result.put("RESULT", "U");
					}else{
						parameter.put("lgst_proc_stat_cd", status.get("LGST_PROC_STAT_CD"));
						parameter.put("upd_bfr_cd", status.get("UPD_BFR_CD"));
						parameter.put("eqt_seq", parameter.get("eqt_seq"));
						parameter.put("so_id", status.get("SO_ID"));
						parameter.put("seq", sequenceService.createNewSequence(Consts.SEQ_CODE.DT_HIST_SEQ, "1", 10));
						distributeMapper.updateUsimCancel(parameter, DateUtil.sysdate(),DateUtil.getDateStringYYYYMMDDHH24MISS(0));
						distributeMapper.insertUsimProcHistCancel(parameter, DateUtil.sysdate(),DateUtil.getDateStringYYYYMMDDHH24MISS(0));
						result.put("RESULT", "Y");
					}
				}else{
					result.put("RESULT", "F");
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("RESULT", "F");
		}
		
		data.setMap(result);
		datas.add(data);
		dataSet.setDatas(datas);
		response.setDataSet(dataSet);
		return response;
	}
}

