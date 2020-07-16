package com.ntels.ifg.httpRest.service;


import com.ntels.ifg.httpRest.domain.RequestVO;
import com.ntels.ifg.httpRest.domain.ResponseVO;


public interface InterfaceProcessService{
  
	ResponseVO processInterface(String svcCode, String opCode, RequestVO request);
}


