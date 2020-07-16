package com.ntels.ccbs.customer.controller.contract.contract;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.customer.domain.contract.contract.PaymentDetailVO;
import com.ntels.ccbs.customer.service.contract.contract.PaymentDetailService;

/**
 * <PRE>
 * 1. ClassName: PaymentDetailController
 * 2. FileName : PaymentDetailController.java
 * 3. Package  : com.ntels.ccbs.customer.controller.contract.contract
 * 4. Comment  : 상세납부 Controller
 * 5. 작성자   : hyewon
 * 6. 작성일   : 2017. 3. 10. 오후 1:42:34
 * 7. 변경이력
 *     이름    :    일자       : 변경내용
 * -------------------------------------------------------
 *     hyewon :    2017. 3. 10.    : 신규개발
 * </PRE>
 */
@Controller
@RequestMapping(value = "/customer/contract/contract/paymentDetail")
public class PaymentDetailController {
	
	private static String URL = "customer/contract/contract/paymentDetail";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * 상세납부 Service
	 */
	@Autowired PaymentDetailService paymentDetailService;
	
	@RequestMapping(value = "openPaymentDetail", method = RequestMethod.POST)
	public String openPaymentDetail(Model model
			,HttpServletRequest request
			,String soId
			,String custId
			,String ctrtId
			,String pymAcntId
			) {
		String today = DateUtil.getDateStringYYYYMMDD(0);
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		PaymentDetailVO pymDtlInfo = paymentDetailService.paymentDetailInfo(soId, pymAcntId, today, lng);
		model.addAttribute("pymDtlInfo", pymDtlInfo);
		return URL + "/ajax/paymentDetailPopup";
	}
	
}
