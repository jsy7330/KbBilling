package com.ntels.ifg.httpRest.service.charge;

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
import com.ntels.ifg.httpRest.mapper.charge.ChargeMapper;
import com.ntels.ifg.httpRest.service.InterfaceProcessService;

@Service
public class ChargeBL0001ServiceImpl implements InterfaceProcessService {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ChargeMapper chargeMapper;
	
	private static final String SVC_CODE = "BL";
	private static final String OP_CODE = "BL0001";
	
	

	@Override
	public ResponseVO processInterface(String svcCode, String opCode, RequestVO request) {
		if(request != null && (SVC_CODE.equals(svcCode) && OP_CODE.equals(opCode)) == false){
			return null;
		}
		
		ResponseVO response = new ResponseVO();
		DataSetVO dataSet = new DataSetVO();
		List<DataVO> datas = new ArrayList<DataVO>();
		DataVO data = new DataVO();
		double unpaidAmout = 0;
		List<DataVO> requestDataList =  request.getDataSet().getDatas();
		
		if(requestDataList == null || requestDataList.size() == 0){
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		if(requestDataList.get(0).getMapList() == null || requestDataList.get(0).getMapList().size() == 0){
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		
		List<Map<String, String>> requestPymInfo = requestDataList.get(0).getMapList();
		

		try{
			for(Map<String, String> pym : requestPymInfo){
				Double amt = chargeMapper.getUnpaidAmount((String)pym.get("so_id"), (String)pym.get("pym_acnt_id"));
				
				if(amt == null || Double.compare(amt.doubleValue(), Double.parseDouble("0")) == 0){
					continue;
				}
				unpaidAmout = unpaidAmout + amt.longValue();
			}	
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		
		data.setName("UNPAID_AMT");
		data.setValue(String.valueOf(unpaidAmout));
		datas.add(data);
		dataSet.setDatas(datas);
		response.setDataSet(dataSet);
		return response;
	}



}

