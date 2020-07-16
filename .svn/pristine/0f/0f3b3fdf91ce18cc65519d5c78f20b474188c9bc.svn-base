package com.ntels.ccbs.distribute.service.distributor.distributorMgt.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.crypto.AES256Cipher;
import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.EmployeeVO;
import com.ntels.ccbs.distribute.mapper.distributor.distributorMgt.EmployeeMapper;
import com.ntels.ccbs.distribute.service.distributor.distributorMgt.EmployeeService;
import com.ntels.ccbs.system.service.common.service.SequenceService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	/** 로그 출력. */
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<EmployeeVO> employeeList(EmployeeVO employeeVO, int page, int perPage) {
		
		int start = 0;
		int end = 0;
		
		start = (page-1)*perPage;
		end = perPage;
		
		return employeeMapper.employeeList(employeeVO, start, end);
	}

	@Override
	public int employeeCount(EmployeeVO employeeVO) {
		return employeeMapper.employeeCount(employeeVO);
	}

	@Override
	public int checkUserId(EmployeeVO employeeVO) {
		return employeeMapper.checkUserId(employeeVO);
	}

	@Override
	public int insertEmployee(EmployeeVO employeeVO) throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		employeeVO.setPassword(AES256Cipher.AES_Encode(employeeVO.geteMail(), Consts.ENCODE_KEY));
		
		int count = employeeMapper.insertEmployee(employeeVO);
		employeeMapper.insertSoAuthInfo(employeeVO);
		return count;
	}

	@Override
	public int updateEmployee(EmployeeVO employeeVO) {
		
		int count = employeeMapper.updateEmployee(employeeVO);
		return count;
	}

	@Override
	public List<EmployeeVO> userGroupList(EmployeeVO employeeVO) {
		return employeeMapper.userGroupList(employeeVO);
	}
	
	
}
