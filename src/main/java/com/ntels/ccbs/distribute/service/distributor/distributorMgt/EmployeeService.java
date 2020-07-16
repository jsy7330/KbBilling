package com.ntels.ccbs.distribute.service.distributor.distributorMgt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.ntels.ccbs.distribute.domain.distributor.distributorMgt.EmployeeVO;

public interface EmployeeService {

	public List<EmployeeVO> employeeList(EmployeeVO employeeVO,	int page, int perPage);	
	
	public int employeeCount(EmployeeVO employeeVO);
	
	public int checkUserId(EmployeeVO employeeVO);
	
	public int insertEmployee(EmployeeVO employeeVO) throws InvalidKeyException,UnsupportedEncodingException,NoSuchAlgorithmException,NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	int updateEmployee(EmployeeVO employeeVO);
	
	public List<EmployeeVO> userGroupList(EmployeeVO employeeVO);
}
