package com.ntels.ifg.httpRest.service;


import org.apache.log4j.Logger;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.ntels.ifg.common.exception.InterfaceException;
import com.ntels.ifg.httpRest.domain.ErrorVO;
import com.ntels.ifg.httpRest.domain.ResponseVO;


@Component
public class HttpRestErrorService{
  
	private static Logger logger = Logger.getLogger(HttpRestErrorService.class);
	
	
	private ResponseVO res = new ResponseVO();
	public ResponseVO createErrorResponse(MessagingException me){
		logger.error("* HttpRestErrorService  : " + me.getMessage());
		ErrorVO err = new ErrorVO();
		err.setCode(((InterfaceException)me.getCause()).getCode());
		err.setMessage(((InterfaceException)me.getCause()).getMessage());
		res.setError(err);
		return res;
	}
}


