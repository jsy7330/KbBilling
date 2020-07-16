package com.ntels.ifg.httpRest.service.distribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ifg.common.exception.InterfaceException;
import com.ntels.ifg.httpRest.domain.DataSetVO;
import com.ntels.ifg.httpRest.domain.DataVO;
import com.ntels.ifg.httpRest.domain.RequestVO;
import com.ntels.ifg.httpRest.domain.ResponseVO;
import com.ntels.ifg.httpRest.mapper.distribute.DistributeMapper;
import com.ntels.ifg.httpRest.service.InterfaceProcessService;

@Service
public class DistributeDN0001ServiceImpl implements InterfaceProcessService {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private DistributeMapper distributeMapper;
	
	private static final String SVC_CODE = "DN";
	private static final String OP_CODE = "DN0001";
	

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
		
		if(requestDataList.get(0).getMap() == null){
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		List<Map<String, String>> deviceList = null;
		try{
			Map<String, String> parameter = requestDataList.get(0).getMap();
			String orgId = distributeMapper.getDealerOrgId(parameter.get("org_id"));
			deviceList = distributeMapper.getDeviceList(orgId, parameter.get("item_id"), parameter.get("imei"), parameter.get("lng"));
			
			
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		data.setMapList(deviceList);
		datas.add(data);
		dataSet.setDatas(datas);
		response.setDataSet(dataSet);
		return response;
	}
}

