package com.ntels.ifg.httpRest.service.charge;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
public class ChargeBL0002ServiceImpl implements InterfaceProcessService {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ChargeMapper chargeMapper;
	
	private static final String SVC_CODE = "BL";
	private static final String OP_CODE = "BL0002";
	

	@Override
	public ResponseVO processInterface(String svcCode, String opCode, RequestVO request) {
		if(request != null && (SVC_CODE.equals(svcCode) && OP_CODE.equals(opCode)) == false){
			return null;
		}
		
		ResponseVO response = new ResponseVO();
		DataSetVO dataSet = new DataSetVO();
		List<DataVO> datas = new ArrayList<DataVO>();
		DataVO data = new DataVO();
		long unpaidMonthCount = 0;
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
				String minMonth = chargeMapper.getUnpaidMinMonth((String)pym.get("so_id"), (String)pym.get("pym_acnt_id"));
				
				if(StringUtils.isEmpty(minMonth)){
					continue;
				}
				
			    LocalDate startDate = LocalDate.of(Integer.parseInt(minMonth.substring(0, 4)), Month.of(Integer.parseInt(minMonth.substring(4))), 1);
			    LocalDate endDate = LocalDate.now();
			    long monthsDiff = ChronoUnit.MONTHS.between(startDate, endDate) - 1; 
				unpaidMonthCount = unpaidMonthCount + monthsDiff;
			}	
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new InterfaceException("MSG.M08.MSG00030");
		}
		
		
		data.setName("UNPAID_MONTH_COUNT");
		data.setValue(String.valueOf(unpaidMonthCount));
		datas.add(data);
		dataSet.setDatas(datas);
		response.setDataSet(dataSet);
		return response;
	}
}

