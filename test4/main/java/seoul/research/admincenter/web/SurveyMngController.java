package seoul.research.admincenter.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

import seoul.research.admincenter.service.SurveyMngSearchVO;
import seoul.research.admincenter.service.SurveyMngService;
import seoul.research.domain.PollMng;
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
@SessionAttributes("surveyMng")
public class SurveyMngController {
	@Resource
	private SurveyMngService surveyMngService = null;

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
	 * @param surveyMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listSurveyMng.do")
	public ModelAndView list(SurveyMngSearchVO surveyMngSearchVO) throws Exception {
		// System.out.println(surveyMngSearchVO.getSearchRegDatestart() + "," + surveyMngSearchVO.getSearchRegDateend() + ","
		// + surveyMngSearchVO.getSearchPollStop() + "," + surveyMngSearchVO.getSearchPollAsk() + ","
		// + surveyMngSearchVO.getSearchCondition() + "," + surveyMngSearchVO.getSearchKeyword());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);

		if (surveyMngSearchVO.getSearchRegDatestart() == null) {
			if (!today.substring(5, 7).equals("01")) {
				surveyMngSearchVO.setSearchRegDatestart(today.substring(0, 4)
						+ "-"
						+ ((Integer.parseInt(today.substring(5, 7)) - 1 >= 10) ? ""
								+ (Integer.parseInt(today.substring(5, 7)) - 1) : "0"
								+ (Integer.parseInt(today.substring(5, 7)) - 1)) + "-01");
			}
			else {
				surveyMngSearchVO.setSearchRegDatestart((Integer.parseInt(today.substring(0, 4)) - 1) + "-12-01");
			}
		}
		if (surveyMngSearchVO.getSearchRegDateend() == null) {
			surveyMngSearchVO.setSearchRegDateend(today);
		}

		Page resultPage = surveyMngService.findSurveyMngList(surveyMngSearchVO);

		Collection askCodeList = codeService.findAskCodeList();

		ModelAndView mav = new ModelAndView("listSurveyMng");
		mav.addObject("surveyMngSearchVO", surveyMngSearchVO);
		mav.addObject("resultPage", resultPage);

		mav.addObject("askCodeList", askCodeList);
		return mav;
	}

	/**
	 * display add pollMng view
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addSurveyMngView.do")
	public ModelAndView addView() throws Exception {
		Collection fieldCodeList = codeService.findFieldCodeList();
		Collection askCodeList = codeService.findAskCodeList();
		Collection gradeCodeList = codeService.findGradeCodeList();
		Collection methodCodeList = codeService.findMethodCodeList();
		Collection mfCodeList = codeService.findMfCodeList();
		Collection ageCodeList = codeService.findAgeCodeList();
		Collection regionCodeList = codeService.findRegionCodeList();
		Collection subjectCodeList = codeService.findSubjectCodeList();

		ModelAndView mav = new ModelAndView("addSurveyMngView", "pollMng", new PollMng());

		mav.addObject("fieldCodeList", fieldCodeList);
		mav.addObject("askCodeList", askCodeList);
		mav.addObject("gradeCodeList", gradeCodeList);
		mav.addObject("methodCodeList", methodCodeList);
		mav.addObject("mfCodeList", mfCodeList);
		mav.addObject("ageCodeList", ageCodeList);
		mav.addObject("regionCodeList", regionCodeList);
		mav.addObject("subjectCodeList", subjectCodeList);
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
	@RequestMapping("/addSurveyMng.do")
	public ModelAndView add(HttpServletRequest request, PollMng pollMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			pollMng.setRegId(userId);
			pollMng.setModId(userId);

			surveyMngService.createSurveyMng(pollMng);

			if (pollMng.getPollMfyn().equals("Y")) {
				// pollMng.setPorMfCode(porMfCode);
				// pollMng.setPartNumberMf(partNumberMf);

				surveyMngService.createPollPartMf(pollMng);
			}
			if (pollMng.getPollAgeyn().equals("Y")) {
				// pollMng.setPorAgeCode(porAgeCode);
				// pollMng.setPartNumberAge(partNumberAge);

				surveyMngService.createPollPartAge(pollMng);
			}
			if (pollMng.getPollRegionyn().equals("Y")) {
				// pollMng.setPorRegionCode(porRegionCode);
				// pollMng.setPartNumberRegion(partNumberRegion);

				surveyMngService.createPollPartRegion(pollMng);
			}

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = null;

			for (int i = 0; i < 5; i++) {
				multipartFile = multipartRequest.getFile("pollAddfile" + (i + 1));

				if (!multipartFile.isEmpty()) {
					surveyMngService.createPollAddfile(pollMng, multipartFile);
				}
			}

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.surveymngcontroller.add");
		}

		status.setComplete();

		return new ModelAndView("listSurveyMngAction");
	}

	@RequestMapping("/detailSurveyMng.do")
	public ModelAndView detail(@RequestParam("pollIdx") int pollIdx, SurveyMngSearchVO surveyMngSearchVO) throws Exception {
		PollMng pollMng = surveyMngService.findSurveyMng2(pollIdx);

		Collection pollPartMfList = surveyMngService.findPollPartMfList(pollIdx);
		Collection pollPartAgeList = surveyMngService.findPollPartAgeList(pollIdx);
		Collection pollPartRegionList = surveyMngService.findPollPartRegionList(pollIdx);

		Collection pollAddfileList = surveyMngService.findPollAddfileList(pollIdx);

		Collection subjectCodeList = codeService.findSubjectCodeList();

		ModelAndView mav = new ModelAndView("detailSurveyMng");
		// mav.addObject("surveyMngSearchVO", surveyMngSearchVO);
		mav.addObject("pollMng", pollMng);

		// mav.addObject("mfCodeList", mfCodeList);
		// mav.addObject("ageCodeList", ageCodeList);
		// mav.addObject("regionCodeList", regionCodeList);
		mav.addObject("pollPartMfList", pollPartMfList);
		mav.addObject("pollPartAgeList", pollPartAgeList);
		mav.addObject("pollPartRegionList", pollPartRegionList);

		mav.addObject("pollAddfileList", pollAddfileList);

		mav.addObject("subjectCodeList", subjectCodeList);
		return mav;
	}

	/**
	 * get detail of pollMng
	 * 
	 * @param surveyMngNo
	 * @param surveyMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateSurveyMngView.do")
	public ModelAndView updateView(@RequestParam("pollIdx") int pollIdx, SurveyMngSearchVO surveyMngSearchVO) throws Exception {
		PollMng pollMng = surveyMngService.findSurveyMng(pollIdx);

		Collection pollPartMfList = surveyMngService.findPollPartMfList(pollIdx);
		Collection pollPartAgeList = surveyMngService.findPollPartAgeList(pollIdx);
		Collection pollPartRegionList = surveyMngService.findPollPartRegionList(pollIdx);

		Collection pollAddfileList = surveyMngService.findPollAddfileList(pollIdx);

		Collection fieldCodeList = codeService.findFieldCodeList();
		Collection askCodeList = codeService.findAskCodeList();
		Collection gradeCodeList = codeService.findGradeCodeList();
		Collection methodCodeList = codeService.findMethodCodeList();
		Collection mfCodeList = codeService.findMfCodeList();
		Collection ageCodeList = codeService.findAgeCodeList();
		Collection regionCodeList = codeService.findRegionCodeList();
		Collection subjectCodeList = codeService.findSubjectCodeList();

		ModelAndView mav = new ModelAndView("updateSurveyMngView");
		// mav.addObject("surveyMngSearchVO", surveyMngSearchVO);
		mav.addObject("pollMng", pollMng);

		mav.addObject("pollPartMfList", pollPartMfList);
		mav.addObject("pollPartAgeList", pollPartAgeList);
		mav.addObject("pollPartRegionList", pollPartRegionList);

		mav.addObject("pollAddfileList", pollAddfileList);

		mav.addObject("fieldCodeList", fieldCodeList);
		mav.addObject("askCodeList", askCodeList);
		mav.addObject("gradeCodeList", gradeCodeList);
		mav.addObject("methodCodeList", methodCodeList);
		mav.addObject("mfCodeList", mfCodeList);
		mav.addObject("ageCodeList", ageCodeList);
		mav.addObject("regionCodeList", regionCodeList);
		mav.addObject("subjectCodeList", subjectCodeList);
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
	@RequestMapping("/updateSurveyMng.do")
	public ModelAndView update(HttpServletRequest request, PollMng pollMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			// pollMng.setRegId(userId);
			pollMng.setModId(userId);

			surveyMngService.updateSurveyMng(pollMng);

			surveyMngService.removePollPart(pollMng);

			if (pollMng.getPollMfyn().equals("Y")) {
				// pollMng.setPorMfCode(porMfCode);
				// pollMng.setPartNumberMf(partNumberMf);

				surveyMngService.createPollPartMf(pollMng);
			}
			if (pollMng.getPollAgeyn().equals("Y")) {
				// pollMng.setPorAgeCode(porAgeCode);
				// pollMng.setPartNumberAge(partNumberAge);

				surveyMngService.createPollPartAge(pollMng);
			}
			if (pollMng.getPollRegionyn().equals("Y")) {
				// pollMng.setPorRegionCode(porRegionCode);
				// pollMng.setPartNumberRegion(partNumberRegion);

				surveyMngService.createPollPartRegion(pollMng);
			}

			surveyMngService.removePollAddfile(pollMng);
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
					surveyMngService.createPollAddfile(pollMng, multipartFile);
				}
				else if (pollMng.getOrgFname() != null && !pollMng.getDelYn().equals("Y")) {
					surveyMngService.createPollAddfile(pollMng);
				}
			}

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.surveymngcontroller.update");
		}

		status.setComplete();

		return new ModelAndView("detailSurveyMngAction");
	}

	/**
	 * delete pollMng
	 * 
	 * @param pollMng
	 * @param surveyMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteSurveyMng.do")
	public ModelAndView delete(@RequestParam("pollIdx") int pollIdx, SurveyMngSearchVO surveyMngSearchVO) throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			PollMng pollMng = new PollMng();
			pollMng.setPollIdx(pollIdx);

			surveyMngService.removePollPart(pollMng);

			surveyMngService.removePollAddfile(pollMng);

			surveyMngService.removeSurveyMng(pollMng);

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.surveymngcontroller.delete");
		}

		return new ModelAndView("listSurveyMngAction", "surveyMngSearchVO", surveyMngSearchVO);
	}

	@RequestMapping("/downloadSurveyMng.do")
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
