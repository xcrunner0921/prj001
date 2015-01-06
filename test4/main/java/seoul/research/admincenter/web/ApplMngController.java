package seoul.research.admincenter.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.admincenter.service.ApplMngSearchVO;
import seoul.research.admincenter.service.ApplMngService;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import anyframe.common.Page;
import anyframe.common.exception.BaseException;
import anyframe.core.properties.IPropertiesService;

import com.sds.emp.code.service.CodeService;

/**
 * controller class for CRUD(create, read, update, delete) a pollMng. this class apply to prevent double form submit about add,
 * update, delete.
 * 
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("applMng")
public class ApplMngController {
	@Resource
	private ApplMngService applMngService = null;

	@Resource
	private CodeService codeService = null;

	@Resource
	private MessageSource messageSource;

	@Resource
	private IPropertiesService propertiesService;

	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;

	public TransactionStatus getTxStatus() {
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return txManager.getTransaction(txDefinition);
	}

	/**
	 * get list of pollMng
	 * 
	 * @param applMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listApplMng.do")
	public ModelAndView list(ApplMngSearchVO applMngSearchVO) throws Exception {
		// System.out.println(applMngSearchVO.getSearchRegDatestart() + "," + applMngSearchVO.getSearchRegDateend() + ","
		// + applMngSearchVO.getSearchPollStop() + "," + applMngSearchVO.getSearchPollAsk() + ","
		// + applMngSearchVO.getSearchCondition() + "," + applMngSearchVO.getSearchKeyword());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);

		System.out.println("listApplMng --> searchRegDatestart : " 	+ applMngSearchVO.getSearchRegDatestart());
		System.out.println("listApplMng --> searchRegDateend : " 	+ applMngSearchVO.getSearchRegDateend());
		System.out.println("listApplMng --> searchKeyword : " 		+ applMngSearchVO.getSearchKeyword());
		
		if (applMngSearchVO.getSearchRegDatestart() == null) {
			if (!today.substring(5, 7).equals("01")) {
				applMngSearchVO.setSearchRegDatestart(today.substring(0, 4)
						+ "-"
						+ ((Integer.parseInt(today.substring(5, 7)) - 1 >= 10) ? ""
								+ (Integer.parseInt(today.substring(5, 7)) - 1) : "0"
								+ (Integer.parseInt(today.substring(5, 7)) - 1)) + "-01");
			}
			else {
				applMngSearchVO.setSearchRegDatestart((Integer.parseInt(today.substring(0, 4)) - 1) + "-12-01");
			}
		}
		if (applMngSearchVO.getSearchRegDateend() == null) {
			applMngSearchVO.setSearchRegDateend(today);
		}

		Page resultPage = applMngService.findApplMngList(applMngSearchVO);

		// Collection askCodeList = codeService.findAskCodeList();

		ModelAndView mav = new ModelAndView("listApplMng");
		mav.addObject("applMngSearchVO", applMngSearchVO);
		mav.addObject("resultPage", resultPage);

		// mav.addObject("askCodeList", askCodeList);
		return mav;
	}

	/**
	 * display add pollMng view
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addApplMngView.do")
	public ModelAndView addView() throws Exception {
		Collection fieldCodeList = codeService.findFieldCodeList();
		Collection askCodeList = codeService.findAskCodeList();
		// Collection departmentCodeList = codeService.findDepartmentCodeList();
		Collection companyCodeList = codeService.findCompanyCodeList();
		Collection methodCodeList = codeService.findMethodCodeList();
		Collection gradeCodeList = codeService.findGradeCodeList();
		Collection contractCodeList = codeService.findContractCodeList();

		ModelAndView mav = new ModelAndView("addApplMngView", "pollMng", new PollMng());

		mav.addObject("fieldCodeList", fieldCodeList);
		mav.addObject("askCodeList", askCodeList);
		// mav.addObject("departmentCodeList", departmentCodeList);
		mav.addObject("companyCodeList", companyCodeList);
		mav.addObject("methodCodeList", methodCodeList);
		mav.addObject("gradeCodeList", gradeCodeList);
		mav.addObject("contractCodeList", contractCodeList);
		return mav;
	}

	/**
	 * add pollMng
	 * 
	 * @param pollMng
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addApplMng.do")
	public ModelAndView add(HttpServletRequest request, PollMng pollMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			pollMng.setRegId(userId);
			pollMng.setModId(userId);

			applMngService.createApplMng(pollMng);

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = null;

			for (int i = 0; i < 5; i++) {
				multipartFile = multipartRequest.getFile("pollAddfile" + (i + 1));

				if (!multipartFile.isEmpty()) {
					applMngService.createPollAddfile(pollMng, multipartFile);
				}
			}

			if (!pollMng.getPollAddfilea().equals("")) {
				pollMng.setSavFname(pollMng.getPollAddfilea());
				applMngService.createPollAddfile2(pollMng);
			}
			if (!pollMng.getPollAddfileb().equals("")) {
				pollMng.setSavFname(pollMng.getPollAddfileb());
				applMngService.createPollAddfile2(pollMng);
			}
			if (!pollMng.getPollAddfilec().equals("")) {
				pollMng.setSavFname(pollMng.getPollAddfilec());
				applMngService.createPollAddfile2(pollMng);
			}

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.applmngcontroller.add");
		}

		status.setComplete();

		return new ModelAndView("listApplMngAction");
	}

	@RequestMapping("/detailApplMng.do")
	public ModelAndView detail(@RequestParam("pollIdx") int pollIdx, ApplMngSearchVO applMngSearchVO) throws Exception {
		PollMng pollMng = applMngService.findApplMng2(pollIdx);
		
		
		System.out.println("detailApplMng --> searchRegDatestart : " 	+ applMngSearchVO.getSearchRegDatestart());
		System.out.println("detailApplMng --> searchRegDateend : " 		+ applMngSearchVO.getSearchRegDateend());
		System.out.println("detailApplMng --> searchKeyword : " 		+ applMngSearchVO.getSearchKeyword());
		
		
		// Collection pollPartMfList = applMngService.findPollPartMfList(pollIdx);
		// Collection pollPartAgeList = applMngService.findPollPartAgeList(pollIdx);
		// Collection pollPartRegionList = applMngService.findPollPartRegionList(pollIdx);

		Collection pollAddfileList = applMngService.findPollAddfileList(pollIdx);
		Collection pollAddfile2List = applMngService.findPollAddfile2List(pollIdx);

		// Collection subjectCodeList = codeService.findSubjectCodeList();

		ModelAndView mav = new ModelAndView("detailApplMng");
		// mav.addObject("applMngSearchVO", applMngSearchVO);
		mav.addObject("pollMng", pollMng);

		Page resultPage = applMngService.findApplMngList(applMngSearchVO);
		// mav.addObject("mfCodeList", mfCodeList);
		// mav.addObject("ageCodeList", ageCodeList);
		// mav.addObject("regionCodeList", regionCodeList);
		// mav.addObject("pollPartMfList", pollPartMfList);
		// mav.addObject("pollPartAgeList", pollPartAgeList);
		// mav.addObject("pollPartRegionList", pollPartRegionList);

		mav.addObject("pollAddfileList", pollAddfileList);
		mav.addObject("pollAddfile2List", pollAddfile2List);
		mav.addObject("applMngSearchVO", applMngSearchVO);
		mav.addObject("resultPage", resultPage);

		// mav.addObject("subjectCodeList", subjectCodeList);
		return mav;
	}

	/**
	 * get detail of pollMng
	 * 
	 * @param applMngNo
	 * @param applMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateApplMngView.do")
	public ModelAndView updateView(@RequestParam("pollIdx") int pollIdx, ApplMngSearchVO applMngSearchVO) throws Exception {
		PollMng pollMng = applMngService.findApplMng(pollIdx);

		// Collection pollPartMfList = applMngService.findPollPartMfList(pollIdx);
		// Collection pollPartAgeList = applMngService.findPollPartAgeList(pollIdx);
		// Collection pollPartRegionList = applMngService.findPollPartRegionList(pollIdx);

		Collection pollAddfileList = applMngService.findPollAddfileList(pollIdx);
		Collection pollAddfile2List = applMngService.findPollAddfile2List(pollIdx);

		Collection fieldCodeList = codeService.findFieldCodeList();
		Collection askCodeList = codeService.findAskCodeList();
		// Collection departmentCodeList = codeService.findDepartmentCodeList();
		Collection companyCodeList = codeService.findCompanyCodeList();
		Collection methodCodeList = codeService.findMethodCodeList();
		Collection gradeCodeList = codeService.findGradeCodeList();
		Collection contractCodeList = codeService.findContractCodeList();

		ModelAndView mav = new ModelAndView("updateApplMngView");
		// mav.addObject("applMngSearchVO", applMngSearchVO);
		mav.addObject("pollMng", pollMng);

		// mav.addObject("pollPartMfList", pollPartMfList);
		// mav.addObject("pollPartAgeList", pollPartAgeList);
		// mav.addObject("pollPartRegionList", pollPartRegionList);

		mav.addObject("pollAddfileList", pollAddfileList);
		mav.addObject("pollAddfile2List", pollAddfile2List);

		mav.addObject("fieldCodeList", fieldCodeList);
		mav.addObject("askCodeList", askCodeList);
		// mav.addObject("departmentCodeList", departmentCodeList);
		mav.addObject("companyCodeList", companyCodeList);
		mav.addObject("methodCodeList", methodCodeList);
		mav.addObject("gradeCodeList", gradeCodeList);
		mav.addObject("contractCodeList", contractCodeList);
		return mav;
	}

	/**
	 * update pollMng
	 * 
	 * @param session
	 * @param pollMng
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateApplMng.do")
	public ModelAndView update(HttpServletRequest request, PollMng pollMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			// pollMng.setRegId(userId);
			pollMng.setModId(userId);

			applMngService.updateApplMng(pollMng);

			// applMngService.removePollPart(pollMng);

			// if (pollMng.getPollMfyn().equals("Y")) {
			// pollMng.setPorMfCode(porMfCode);
			// pollMng.setPartNumberMf(partNumberMf);

			// applMngService.createPollPartMf(pollMng);
			// }
			// if (pollMng.getPollAgeyn().equals("Y")) {
			// pollMng.setPorAgeCode(porAgeCode);
			// pollMng.setPartNumberAge(partNumberAge);

			// applMngService.createPollPartAge(pollMng);
			// }
			// if (pollMng.getPollRegionyn().equals("Y")) {
			// pollMng.setPorRegionCode(porRegionCode);
			// pollMng.setPartNumberRegion(partNumberRegion);

			// applMngService.createPollPartRegion(pollMng);
			// }

			applMngService.removePollAddfile(pollMng);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = null;

			for (int i = 0; i < 5; i++) {
				multipartFile = multipartRequest.getFile("pollAddfile" + (i + 1));

				if (i == 0) {
					pollMng.setOrgFname(pollMng.getOrgFname1());
					pollMng.setSavFname(pollMng.getSavFname1());
					pollMng.setFilePath(pollMng.getFilePath1());
					pollMng.setFileSize(pollMng.getFileSize1());
					pollMng.setDelYn(pollMng.getDelYn1());
				}
				else if (i == 1) {
					pollMng.setOrgFname(pollMng.getOrgFname2());
					pollMng.setSavFname(pollMng.getSavFname2());
					pollMng.setFilePath(pollMng.getFilePath2());
					pollMng.setFileSize(pollMng.getFileSize2());
					pollMng.setDelYn(pollMng.getDelYn2());
				}
				else if (i == 2) {
					pollMng.setOrgFname(pollMng.getOrgFname3());
					pollMng.setSavFname(pollMng.getSavFname3());
					pollMng.setFilePath(pollMng.getFilePath3());
					pollMng.setFileSize(pollMng.getFileSize3());
					pollMng.setDelYn(pollMng.getDelYn3());
				}
				else if (i == 3) {
					pollMng.setOrgFname(pollMng.getOrgFname4());
					pollMng.setSavFname(pollMng.getSavFname4());
					pollMng.setFilePath(pollMng.getFilePath4());
					pollMng.setFileSize(pollMng.getFileSize4());
					pollMng.setDelYn(pollMng.getDelYn4());
				}
				else if (i == 4) {
					pollMng.setOrgFname(pollMng.getOrgFname5());
					pollMng.setSavFname(pollMng.getSavFname5());
					pollMng.setFilePath(pollMng.getFilePath5());
					pollMng.setFileSize(pollMng.getFileSize5());
					pollMng.setDelYn(pollMng.getDelYn5());
				}

				if (!multipartFile.isEmpty()) {
					applMngService.createPollAddfile(pollMng, multipartFile);
				}
				else if (pollMng.getOrgFname() != null && !pollMng.getDelYn().equals("Y")) {
					applMngService.createPollAddfile(pollMng);
				}
			}

			// applMngService.removePollAddfile2(pollMng);
			if (!pollMng.getPollAddfilea().equals("")) {
				pollMng.setSavFname(pollMng.getPollAddfilea());
				applMngService.createPollAddfile2(pollMng);
			}
			if (!pollMng.getPollAddfileb().equals("")) {
				pollMng.setSavFname(pollMng.getPollAddfileb());
				applMngService.createPollAddfile2(pollMng);
			}
			if (!pollMng.getPollAddfilec().equals("")) {
				pollMng.setSavFname(pollMng.getPollAddfilec());
				applMngService.createPollAddfile2(pollMng);
			}

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.applmngcontroller.update");
		}

		status.setComplete();

		return new ModelAndView("detailApplMngAction");
	}

	/**
	 * delete pollMng
	 * 
	 * @param pollMng
	 * @param applMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteApplMng.do")
	public ModelAndView delete(@RequestParam("pollIdx") int pollIdx, ApplMngSearchVO applMngSearchVO) throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			PollMng pollMng = new PollMng();
			pollMng.setPollIdx(pollIdx);

			// applMngService.removePollPart(pollMng);

			applMngService.removePollAddfile(pollMng);

			applMngService.removeApplMng(pollMng);

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.applmngcontroller.delete");
		}

		return new ModelAndView("listApplMngAction", "applMngSearchVO", applMngSearchVO);
	}

	@RequestMapping("/excelApplMng.do")
	public String excel(@RequestParam int pollIdx, @RequestParam String target, Map map) throws Exception {
		PollMng pollMng = applMngService.findApplMng2(pollIdx);

		Collection questMngList = null;
		QuestMng questMng = null;
		Collection panelStateList = null;

		if (target.equals("S")) {
			questMngList = applMngService.findQuestMngList(pollIdx);
		}
		else if (target.equals("E")) {
			questMngList = applMngService.findQuestMng2List(pollIdx);
		}
		else if (target.equals("R")) {
			questMngList = applMngService.findQuestMng3List(pollIdx);
			questMng = applMngService.findQuestMng(pollIdx);
			panelStateList = applMngService.findPanelStateList(pollIdx);
		}

		map.put("target", target);
		map.put("pollMng", pollMng);

		map.put("questMngList", questMngList);
		map.put("questMng", questMng);
		map.put("panelStateList", panelStateList);
		return "excelApplMngView";
	}

	@RequestMapping("/downloadApplMng.do")
	public void download(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		String survmngDir = propertiesService.getString("SURVMNG_DIR");

		String filePath = request.getParameter("filePath") != null ? request.getParameter("filePath").trim() : "";
		String savFname = request.getParameter("savFname") != null ? request.getParameter("savFname").trim() : "";
		String orgFname = request.getParameter("orgFname") != null ? request.getParameter("orgFname").trim() : "";

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + orgFname + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		FileCopyUtils.copy(new FileInputStream(new File(survmngDir, savFname)), response.getOutputStream());
	}
}
