package com.ntels.ccbs.charge.controller.charge.charge;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.ccbs.charge.domain.charge.charge.ChargeCalculationVO;
import com.ntels.ccbs.charge.domain.charge.charge.RegularChargeJobVO;
import com.ntels.ccbs.charge.service.charge.charge.ChargeCalculationService;
import com.ntels.ccbs.common.consts.Consts;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.common.util.DateUtil;
import com.ntels.ccbs.system.domain.common.service.SessionUser;
import com.ntels.ccbs.system.service.common.service.CommonDataService;
import com.ntels.ccbs.system.service.common.service.SequenceService;


@Controller
@RequestMapping(value = "/charge/charge/charge/chargeMng")
public class ChargeCalculationController {
	
	private static String URL = "charge/charge/charge/chargeMng";
	
	@Autowired
	private CommonDataService commonDataService;

	@Autowired
	private ChargeCalculationService chargeCalculationService;
	
	@Autowired
	private SequenceService sequenceService;
	
	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "regularChargeJob", method = RequestMethod.POST)
	public String regularChargeJob(Model model, RegularChargeJobVO regularChargeJobVO, HttpServletRequest request) throws Exception {

		return URL + "/regularChargeJob";
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: regularChargeJobAction
	 * 2. ClassName : ChargeCalculationController
	 * 3. Comment   : 배치작업리스트 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 3. 29. 오후 1:08:44
	 * </PRE>
	 *   @return ModelMap
	 *   @param regularChargeJobVO
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "regularChargeJobAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap regularChargeJobAction(RegularChargeJobVO regularChargeJobVO, HttpServletRequest request) {
		
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		regularChargeJobVO.setLang(lng);
		
		
		ModelMap modelMap = new ModelMap();
		
		List<RegularChargeJobVO> regularChargeJobList = null;
		int totalCount = chargeCalculationService.getRegularChargeJobCount(regularChargeJobVO);
		if(totalCount > 0){

			regularChargeJobVO.setStart((regularChargeJobVO.getPage()-1)*regularChargeJobVO.getRows());
			regularChargeJobVO.setEnd(regularChargeJobVO.getRows());
			
			regularChargeJobList = chargeCalculationService.getRegularChargeJobList(regularChargeJobVO);
		}
		
		modelMap.addAttribute("regularChargeJobList", regularChargeJobList);
		modelMap.addAttribute("totalCount", totalCount);
		modelMap.addAttribute("totalPages", (int) Math.ceil((float) totalCount / (float) regularChargeJobVO.getRows()));
		modelMap.addAttribute("page", regularChargeJobVO.getPage());

		return modelMap;

	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: pgmExeSeqNoAction
	 * 2. ClassName : ChargeCalculationController
	 * 3. Comment   : 배치 로그 시퀀스
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 3. 29. 오후 1:18:45
	 * </PRE>
	 *   @return ModelMap
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "pgmExeSeqNoAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap pgmExeSeqNoAction(HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		
		String pgmExeSeqNo = sequenceService.createNewSequence(Consts.SEQ_CODE.BL_PGM_EXE_SEQ_NO, 10);
		modelMap.addAttribute("pgmExeSeqNo", pgmExeSeqNo);
		
		return modelMap;
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getClcWrkNo
	 * 2. ClassName : ChargeCalculationController
	 * 3. Comment   : TBLCH_CLC_MAIN의 CLC_WRK_NO 셀렉트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 4. 5. 오전 11:25:58
	 * </PRE>
	 *   @return ModelMap
	 *   @param regularChargeJobVO
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "getClcWrkNo", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap getClcWrkNo(RegularChargeJobVO regularChargeJobVO, HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		
		SessionUser session = (SessionUser) request.getSession().getAttribute("session_user");
		String userId = session.getUserId();
		RegularChargeJobVO returnVo = chargeCalculationService.getClcWrkNo(regularChargeJobVO, userId);
		modelMap.addAttribute("clcWrkNo", returnVo.getClcWrkNo());
		
		return modelMap;
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: getBatchLogAction
	 * 2. ClassName : ChargeCalculationController
	 * 3. Comment   : 배치 로그 조회
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 3. 29. 오후 4:11:25
	 * </PRE>
	 *   @return ModelMap
	 *   @param regularChargeJobVO
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "getBatchLogAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap getBatchLogAction(RegularChargeJobVO regularChargeJobVO, HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		
		List<RegularChargeJobVO> batchLogList = chargeCalculationService.getBatchLog(regularChargeJobVO);
		modelMap.addAttribute("batchLogList", batchLogList);
		
		return modelMap;
	}
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName: setBatPgmLogAction
	 * 2. ClassName : ChargeCalculationController
	 * 3. Comment   : 로그 상태값 업데이트
	 * 4. 작성자    : jhkim
	 * 5. 작성일    : 2017. 4. 10. 오전 9:25:56
	 * </PRE>
	 *   @return ModelMap
	 *   @param regularChargeJobVO
	 *   @param request
	 *   @return
	 */
	@RequestMapping(value = "setBatPgmLogAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap setBatPgmLogAction(RegularChargeJobVO regularChargeJobVO, HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		
		int resultValue = chargeCalculationService.updateBatPgmLog(regularChargeJobVO);
		modelMap.addAttribute("count", resultValue);
		
		return modelMap;
	}
	
	/*
	@RequestMapping(value = "setBatchAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap setBatchAction(RegularChargeJobVO regularChargeJobVO, HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://192.168.10.214:18080/executeJob");
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("packageName", regularChargeJobVO.getPackageName()));
		params.add(new BasicNameValuePair("className", regularChargeJobVO.getClassName()));
		params.add(new BasicNameValuePair("args", regularChargeJobVO.getArgs()));
		
		String result = "";
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(post);
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		
		return modelMap;
	}
	*/
	
	
	@RequestMapping(value = "getChargeListAction", method = RequestMethod.POST)
	public String getWorkGrpList(Model model,HttpServletRequest request,String soId,String sidx,String sord,int page,int rows
		,String condBillYymm
		,String condClc
		,String condPymAcntId
		,String condCustId) {
		
		SessionUser sessionUser = CommonUtil.getSessionManager();
		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		String today = DateUtil.getDateStringYYYYMMDD(0);
		
		Map<String,Object> chargeInfo = chargeCalculationService.getChargeList(soId,sessionUser.getSoAuthList(),sidx,sord, page, rows, today, lng
																				,condBillYymm
																				,condClc
																				,condPymAcntId
																				,condCustId);
		
		model.addAttribute("chargeList", chargeInfo.get("chargeList"));
		model.addAttribute("totalCount", chargeInfo.get("totalCount"));
		model.addAttribute("totalPages", chargeInfo.get("totalPages"));
		model.addAttribute("page", chargeInfo.get("page"));
		 
		
		return URL + "/chargeCalculationResult";
	}

	@RequestMapping(value = "chargeCalculationSearch", method = RequestMethod.POST)
	public String chargeCalculationSearch(Model model, ChargeCalculationVO chargeCalculationVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("chargeJobTp", commonDataService.getCommonCodeList("BL00002", lng));
		model.addAttribute("chargeTp", commonDataService.getCommonCodeList("BL00003", lng));
		model.addAttribute("chargeTrtTp", commonDataService.getCommonCodeList("BL00004", lng));	
		return URL + "/chargeCalculationSearch";
	}
	
	@RequestMapping(value = "chargeCalculationResult", method = RequestMethod.POST)
	public String chargeCalculationResult(Model model, ChargeCalculationVO chargeCalculationVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("chargeTrtTp", commonDataService.getCommonCodeList("BL00004", lng));
		
		return URL + "/chargeCalculationResult";
	}
}
