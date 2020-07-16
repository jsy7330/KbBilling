package com.ntels.ccbs.charge.controller.charge.batch;

import java.util.List;

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

import com.ntels.ccbs.charge.domain.charge.batch.BatchGroupVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchJobMngVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchProgramVO;
import com.ntels.ccbs.charge.domain.charge.batch.BatchWorkMapVO;
import com.ntels.ccbs.charge.service.charge.batch.BatchGroupService;
import com.ntels.ccbs.charge.service.charge.batch.BatchProgramService;
import com.ntels.ccbs.charge.service.charge.batch.BatchWorkMapService;
import com.ntels.ccbs.common.util.CommonUtil;
import com.ntels.ccbs.system.service.common.service.CommonDataService;


@Controller
@RequestMapping(value = "/charge/charge/batch/batchjobMng")
public class BatchJobMngController {
	
	private static String URL = "charge/charge/batch/batchjobMng";
	
	@Autowired
	private CommonDataService commonDataService;
	
	@Autowired
	private BatchGroupService batchGroupService;
	
	@Autowired
	private BatchProgramService batchProgramService;
	
	@Autowired
	private BatchWorkMapService batchWorkMapService;
/*	
	@Autowired
	private CustomerDocumentService customerDocumentService;
*/	
	/** the logger. */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String getLang(HttpServletRequest request) {
		return (String)request.getSession().getAttribute("sessionLanguage");
	}
	
	@RequestMapping(value = "batchGroupIns", method = RequestMethod.POST)
	public String batchGroupIns(Model model, BatchJobMngVO batchJobMngVO, HttpServletRequest request) throws Exception {

		String lng = getLang(request);
		model.addAttribute("batchGroupTp", commonDataService.getCommonCodeList("BL00031", lng));
		model.addAttribute("batchTrtTp", commonDataService.getCommonCodeList("BL00032", lng));
		
		return URL + "/batchGroupIns";
	}
	
	@RequestMapping(value = "batchGroupList", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap batchGroupList(BatchGroupVO batchGroupVO, HttpServletRequest request) {
		
		String lng = getLang(request);
		batchGroupVO.setLang(lng);
		
		ModelMap modelMap = new ModelMap();
		
		List<BatchGroupVO> batchGroupList = batchGroupService.getBatchGroupList(batchGroupVO);
		batchGroupVO = batchGroupService.getBatchGroupPaging(batchGroupVO);
		
		modelMap.addAttribute("batchGroupList", batchGroupList);
		modelMap.addAttribute("totalCount", batchGroupVO.getTotalCount());
		modelMap.addAttribute("totalPages", batchGroupVO.getTotalPages());
		modelMap.addAttribute("page", batchGroupVO.getPage());

		return modelMap;
	}
	
	@RequestMapping(value = "batchWorkMapList", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap batchWorkMapList(BatchGroupVO batchGroupVO) {
		ModelMap modelMap = new ModelMap();
		try {
			List<BatchWorkMapVO> batchWorkMapList = batchWorkMapService.getBatchWorkMapByBatchGroup(batchGroupVO);
			modelMap.addAttribute("batchWorkMapList", batchWorkMapList);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "batchProgramForBatchGroup", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap batchProgramForBatchGroup(BatchGroupVO batchGroupVO) {
		ModelMap modelMap = new ModelMap();
		try {
			List<BatchProgramVO> batchProgramList = batchProgramService.getBatchProgramForBatGrp(batchGroupVO);
			modelMap.addAttribute("batchProgramList", batchProgramList);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "insertBatchGroup", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap insertBatchGroup(BatchGroupVO batchGroupVO) {
		ModelMap modelMap = new ModelMap();
		try {
			batchGroupVO.setChgrId(CommonUtil.getSessionManager().getUserId());
			batchGroupVO.setRegrId(CommonUtil.getSessionManager().getUserId());
			batchGroupService.insertBatchGroup(batchGroupVO);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "updateBatchGroup", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap updateBatchGroup(BatchGroupVO batchGroupVO) {
		ModelMap modelMap = new ModelMap();
		try {
			batchGroupVO.setChgrId(CommonUtil.getSessionManager().getUserId());
			batchGroupService.updateBatchGroup(batchGroupVO);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "deleteBatchGroup", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap deleteBatchGroup(BatchGroupVO batchGroupVO) {
		ModelMap modelMap = new ModelMap();
		try {
			batchGroupVO.setChgrId(CommonUtil.getSessionManager().getUserId());
			batchGroupService.deleteBatchGroup(batchGroupVO);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}

	@RequestMapping(value = "batchProgramIns", method = RequestMethod.POST)
	public String batchProgramIns(Model model, BatchJobMngVO batchJobMngVO, HttpServletRequest request) throws Exception {

		String lng = getLang(request);
		model.addAttribute("clsStpClList", commonDataService.getCommonCodeList("BL00018", lng));
		model.addAttribute("clsTskClList", commonDataService.getCommonCodeList("BL00039", lng));
		
		return URL + "/batchProgramIns";
	}
	
	@RequestMapping(value = "getBatchProgramList", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap getBatchProgramList(BatchProgramVO batchProgramVO, HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		
		String lng = getLang(request);
		batchProgramVO.setLang(lng);
		
		List<BatchProgramVO> batchProgramList = batchProgramService.getBatchProgramList(batchProgramVO);
		batchProgramVO = batchProgramService.getBatchProgramPaging(batchProgramVO);
		
		modelMap.addAttribute("batchProgramList", batchProgramList);
		modelMap.addAttribute("totalCount", batchProgramVO.getTotalCount());
		modelMap.addAttribute("totalPages", batchProgramVO.getTotalPages());
		modelMap.addAttribute("page", batchProgramVO.getPage());
		
		return modelMap;
	}
	
	@RequestMapping(value = "insertBatchProgram", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap insertBatchProgram(BatchProgramVO batchProgramVO) {
		ModelMap modelMap = new ModelMap();
		
		try {
			batchProgramVO.setChgrId(CommonUtil.getSessionManager().getUserId());
			batchProgramVO.setRegrId(CommonUtil.getSessionManager().getUserId());
			batchProgramService.insertBatchProgram(batchProgramVO);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "updateBatchProgram", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap updateBatchProgram(BatchProgramVO batchProgramVO) {
		ModelMap modelMap = new ModelMap();
		
		try {
			batchProgramVO.setChgrId(CommonUtil.getSessionManager().getUserId());
			batchProgramService.updateBatchProgram(batchProgramVO);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "deleteBatchProgram", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap deleteBatchProgram(BatchProgramVO batchProgramVO) {
		ModelMap modelMap = new ModelMap();
		
		try {
			batchProgramService.deleteBatchProgram(batchProgramVO);
			modelMap.addAttribute("success", true);
		} catch (Exception e) {
			modelMap.addAttribute("success", false);
			modelMap.addAttribute("message", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "batchJobSearch", method = RequestMethod.POST)
	public String batchJobSearch(Model model, BatchJobMngVO batchJobMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("batchGroupTp", commonDataService.getCommonCodeList("BL00031", lng));
		model.addAttribute("batchJopStat", commonDataService.getCommonCodeList("BL00033", lng));
		
		return URL + "/batchJobSearch";
	}
	
	@RequestMapping(value = "finishInfoMng", method = RequestMethod.POST)
	public String finishInfoMng(Model model, BatchJobMngVO batchJobMngVO, HttpServletRequest request) throws Exception {

		String lng = (String)request.getSession().getAttribute("sessionLanguage");
		model.addAttribute("finishTp", commonDataService.getCommonCodeList("BL00039", lng));
		
		return URL + "/finishInfoMng";
	}	
}
