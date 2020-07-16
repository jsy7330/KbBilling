package com.ntels.ifg.httpRest.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import com.ntels.ifg.common.exception.InterfaceException;
import com.ntels.ifg.httpRest.domain.RequestVO;
import com.ntels.ifg.httpRest.domain.ResponseVO;


@Service
public class HttpRestInboundService{
  
	private static Logger logger = Logger.getLogger(HttpRestInboundService.class);
	
	@Autowired
	private InterfaceProcessService[] interfaceProcessService;
	
	
	
	public ResponseVO handleRqeust(Message<?> inMessage){
		MessageHeaders header = inMessage.getHeaders();
		
		String svcCode = (String)header.get("svc_code");
		String opCode = (String)header.get("op_code");
		
		
		RequestVO request = (RequestVO)inMessage.getPayload();
		
		logger.debug("#### CCBS Interface Request : " + request.toString());
		ResponseVO response = null;
		for (int index = 0; response == null && index < interfaceProcessService.length; index++) {
            try {
            	response = interfaceProcessService[index].processInterface(svcCode, opCode, request);
            } catch (Exception e) {
                throw new InterfaceException("MSG.M08.MSG00014");
            }
        }
		return response;
		
	}
}


