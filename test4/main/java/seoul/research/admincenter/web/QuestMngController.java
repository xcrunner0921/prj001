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

import seoul.research.admincenter.service.QuestMngSearchVO;
import seoul.research.admincenter.service.QuestMngService;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import anyframe.common.Page;
import anyframe.common.exception.BaseException;
import anyframe.core.properties.IPropertiesService;

import com.sds.emp.code.service.CodeService;

/**
 * controller class for CRUD(create, read, update, delete) a questMng. this class apply to prevent double form submit about add,
 * update, delete.
 * 
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("questMng")
public class QuestMngController {
	@Resource
	private QuestMngService questMngService = null;

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
	 * get list of questMng
	 * 
	 * @param questMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listQuestMng.do")
	public ModelAndView list(QuestMngSearchVO questMngSearchVO) throws Exception {
		// System.out.println(questMngSearchVO.getSearchRegDatestart() + "," + questMngSearchVO.getSearchRegDateend() + ","
		// + questMngSearchVO.getSearchPollQuest() + "," + questMngSearchVO.getSearchPollAsk() + ","
		// + questMngSearchVO.getSearchCondition() + "," + questMngSearchVO.getSearchKeyword());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);

		if (questMngSearchVO.getSearchRegDatestart() == null) {
			if (!today.substring(5, 7).equals("01")) {
				questMngSearchVO.setSearchRegDatestart(today.substring(0, 4)
						+ "-"
						+ ((Integer.parseInt(today.substring(5, 7)) - 1 >= 10) ? ""
								+ (Integer.parseInt(today.substring(5, 7)) - 1) : "0"
								+ (Integer.parseInt(today.substring(5, 7)) - 1)) + "-01");
			}
			else {
				questMngSearchVO.setSearchRegDatestart((Integer.parseInt(today.substring(0, 4)) - 1) + "-12-01");
			}
		}
		if (questMngSearchVO.getSearchRegDateend() == null) {
			questMngSearchVO.setSearchRegDateend(today);
		}

		Page resultPage = questMngService.findPollMngList(questMngSearchVO);

		Collection askCodeList = codeService.findAskCodeList();

		ModelAndView mav = new ModelAndView("listQuestMng");
		mav.addObject("questMngSearchVO", questMngSearchVO);
		mav.addObject("resultPage", resultPage);

		mav.addObject("askCodeList", askCodeList);
		return mav;
	}

	/**
	 * display add questMng view
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addQuestMngView.do")
	public ModelAndView addView(@RequestParam("pollIdx") int pollIdx) throws Exception {
		PollMng pollMng = questMngService.findPollMng(pollIdx);

		int questNo = questMngService.getQuestNo(pollIdx);

		Collection graphCodeList = codeService.findGraphCodeList();
		Collection questCodeList = codeService.findQuestCodeList();
		Collection examCodeList = codeService.findExamCodeList();
		Collection choiceCodeList = codeService.findChoiceCodeList();

		ModelAndView mav = new ModelAndView("addQuestMngView", "questMng", new QuestMng());

		mav.addObject("pollMng", pollMng);

		mav.addObject("questNo", Integer.toString(questNo));

		mav.addObject("graphCodeList", graphCodeList);
		mav.addObject("questCodeList", questCodeList);
		mav.addObject("examCodeList", examCodeList);
		mav.addObject("choiceCodeList", choiceCodeList);
		return mav;
	}

	/**
	 * add questMng
	 * 
	 * @param questMng
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addQuestMng.do")
	public ModelAndView add(HttpServletRequest request, QuestMng questMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			questMng.setRegId(userId);
			questMng.setModId(userId);

			questMngService.createQuestMng(questMng);

			if (questMng.getQuestType().equals("HH102")) {
				questMng.setChoiceCnt(questMng.getChoiceCnt21());
				questMng.setChoiceRange(questMng.getChoiceRange21());
			}
			else if (questMng.getQuestType().equals("HH104")) {
				questMng.setChoiceCnt(questMng.getChoiceCnt41());
				questMng.setChoiceRange(questMng.getChoiceRange41());
			}
			// else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II102")) {
			// questMng.setChoiceCnt(questMng.getChoiceCnt22());
			// questMng.setChoiceRange(questMng.getChoiceRange22());
			// }
			// else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II102")) {
			// questMng.setChoiceCnt(questMng.getChoiceCnt42());
			// questMng.setChoiceRange(questMng.getChoiceRange42());
			// }

			if (questMng.getQuestType().equals("HH102") || questMng.getQuestType().equals("HH104")) {
				questMngService.createQuestTypeMng(questMng);
			}

			if (questMng.getFilterYn().equals("Y")) {
				questMngService.createFilterMng(questMng);
			}

			if (questMng.getQuestType().equals("HH101") && questMng.getExamType().equals("II101")) {
				questMng.setExamTitle(questMng.getExamTitle11());
				questMng.setExamNo(questMng.getExamNo11());
			}
			else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II101")) {
				questMng.setExamTitle(questMng.getExamTitle21());
				questMng.setExamNo(questMng.getExamNo21());
			}
			else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II101")) {
				questMng.setExamTitle(questMng.getExamTitle41());
				questMng.setExamNo(questMng.getExamNo41());
			}
			else if (questMng.getQuestType().equals("HH101") && questMng.getExamType().equals("II102")) {
				questMng.setFileDesc(questMng.getFileDesc12());
				questMng.setExamNo(questMng.getExamNo12());
			}
			else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II102")) {
				questMng.setFileDesc(questMng.getFileDesc22());
				questMng.setExamNo(questMng.getExamNo22());
			}
			else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II102")) {
				questMng.setFileDesc(questMng.getFileDesc42());
				questMng.setExamNo(questMng.getExamNo42());
			}

			// if (questMng.getExamType().equals("II102")) {
			// questMng.setBranchNo(questMng.getBranchNo2());
			// }

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = null;

			if (!questMng.getQuestType().equals("HH103")) {
				questMngService.createExampleMng(questMng, request);

				if (questMng.getExamType().equals("II102")) {
					String fileDesc = null;
					String[] examNos = questMng.getExamNo().split(",");
					for (int i = 0; i < examNos.length; i++) {
						if (questMng.getQuestType().equals("HH101")) {
							multipartFile = multipartRequest.getFile("imgMng12_" + (i + 1));

							fileDesc = multipartRequest.getParameter("fileDesc12_" + (i + 1));
						}
						else if (questMng.getQuestType().equals("HH102")) {
							multipartFile = multipartRequest.getFile("imgMng22_" + (i + 1));

							fileDesc = multipartRequest.getParameter("fileDesc22_" + (i + 1));
						}
						else if (questMng.getQuestType().equals("HH104")) {
							multipartFile = multipartRequest.getFile("imgMng42_" + (i + 1));

							fileDesc = multipartRequest.getParameter("fileDesc42_" + (i + 1));
						}

						questMng.setFileDesc(fileDesc);
						questMng.setExamNo(examNos[i]);

						if (!multipartFile.isEmpty()) {
							questMngService.createImgMng(questMng, multipartFile);
						}
					}
				}
			}

			multipartFile = multipartRequest.getFile("questAddfileu");
			if (!multipartFile.isEmpty()) {
				questMng.setPosition("U");

				questMngService.createQuestAddfile(questMng, multipartFile);
			}

			multipartFile = multipartRequest.getFile("questAddfiled");
			if (!multipartFile.isEmpty()) {
				questMng.setPosition("D");

				questMngService.createQuestAddfile(questMng, multipartFile);
			}

			// int pollIdx = questMng.getPollIdx();
			questMngService.setPollQuestStatus(questMng.getPollIdx(), questMng.getPollQuestStatus());

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.questmngcontroller.add");
		}

		status.setComplete();

		ModelAndView mav;
		if (questMng.getPollQuestStatus().equals("Y")) {
			mav = new ModelAndView("listQuestMngAction");
		}
		else {
			mav = new ModelAndView("addQuestMngViewAction");
		}

		return mav;
	}

	/**
	 * get detail of questMng
	 * 
	 * @param questMngNo
	 * @param questMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detailQuestMng.do")
	public ModelAndView detail(@RequestParam("pollIdx") int pollIdx, QuestMngSearchVO questMngSearchVO) throws Exception {
		PollMng pollMng = questMngService.findPollMng(pollIdx);

		Collection questMngList = questMngService.findQuestMngList(pollIdx);

		ModelAndView mav = new ModelAndView("detailQuestMng");
		mav.addObject("pollMng", pollMng);

		mav.addObject("questMngList", questMngList);
		return mav;
	}

	/**
	 * get detail of questMng
	 * 
	 * @param questMngNo
	 * @param questMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateQuestMngView.do")
	public ModelAndView updateView(@RequestParam("pollIdx") int pollIdx, @RequestParam("questNo") int questNo,
			QuestMngSearchVO questMngSearchVO) throws Exception {
		PollMng pollMng = questMngService.findPollMng(pollIdx);

		QuestMng questMng = questMngService.findQuestMng(pollIdx, questNo);

		Collection graphCodeList = codeService.findGraphCodeList();
		Collection questCodeList = codeService.findQuestCodeList();
		Collection examCodeList = codeService.findExamCodeList();
		Collection choiceCodeList = codeService.findChoiceCodeList();

		ModelAndView mav = new ModelAndView("updateQuestMngView");

		mav.addObject("pollMng", pollMng);

		mav.addObject("questMng", questMng);

		mav.addObject("graphCodeList", graphCodeList);
		mav.addObject("questCodeList", questCodeList);
		mav.addObject("examCodeList", examCodeList);
		mav.addObject("choiceCodeList", choiceCodeList);
		return mav;
	}

	/**
	 * update questMng
	 * 
	 * @param session
	 * @param questMng
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateQuestMng.do")
	public ModelAndView update(HttpServletRequest request, QuestMng questMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			// questMng.setRegId(userId);
			questMng.setModId(userId);

			// String branchNo2 = questMng.getBranchNo2();

			questMngService.updateQuestMng(questMng);

			questMngService.removeQuestTypeMng(questMng);
			if (questMng.getQuestType().equals("HH102")) {
				questMng.setChoiceCnt(questMng.getChoiceCnt21());
				questMng.setChoiceRange(questMng.getChoiceRange21());
			}
			else if (questMng.getQuestType().equals("HH104")) {
				questMng.setChoiceCnt(questMng.getChoiceCnt41());
				questMng.setChoiceRange(questMng.getChoiceRange41());
			}
			// else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II102")) {
			// questMng.setChoiceCnt(questMng.getChoiceCnt22());
			// questMng.setChoiceRange(questMng.getChoiceRange22());
			// }
			// else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II102")) {
			// questMng.setChoiceCnt(questMng.getChoiceCnt42());
			// questMng.setChoiceRange(questMng.getChoiceRange42());
			// }

			if (questMng.getQuestType().equals("HH102") || questMng.getQuestType().equals("HH104")) {
				questMngService.createQuestTypeMng(questMng);
			}

			questMngService.removeFilterMng(questMng);
			if (questMng.getFilterYn().equals("Y")) {
				questMngService.createFilterMng(questMng);
			}

			questMngService.removeImgMng(questMng);
			questMngService.removeExampleMng(questMng);
			if (questMng.getQuestType().equals("HH101") && questMng.getExamType().equals("II101")) {
				questMng.setExamTitle(questMng.getExamTitle11());
				questMng.setExamNo(questMng.getExamNo11());
			}
			else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II101")) {
				questMng.setExamTitle(questMng.getExamTitle21());
				questMng.setExamNo(questMng.getExamNo21());
			}
			else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II101")) {
				questMng.setExamTitle(questMng.getExamTitle41());
				questMng.setExamNo(questMng.getExamNo41());
			}
			else if (questMng.getQuestType().equals("HH101") && questMng.getExamType().equals("II102")) {
				questMng.setFileDesc(questMng.getFileDesc12());
				questMng.setExamNo(questMng.getExamNo12());

				questMng.setOrgFname(questMng.getOrgFname12());
				questMng.setSavFname(questMng.getSavFname12());
				questMng.setFilePath(questMng.getFilePath12());
				questMng.setFileSize(questMng.getFileSize12());
			}
			else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II102")) {
				questMng.setFileDesc(questMng.getFileDesc22());
				questMng.setExamNo(questMng.getExamNo22());

				questMng.setOrgFname(questMng.getOrgFname22());
				questMng.setSavFname(questMng.getSavFname22());
				questMng.setFilePath(questMng.getFilePath22());
				questMng.setFileSize(questMng.getFileSize22());
			}
			else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II102")) {
				questMng.setFileDesc(questMng.getFileDesc42());
				questMng.setExamNo(questMng.getExamNo42());

				questMng.setOrgFname(questMng.getOrgFname42());
				questMng.setSavFname(questMng.getSavFname42());
				questMng.setFilePath(questMng.getFilePath42());
				questMng.setFileSize(questMng.getFileSize42());
			}

			// if (questMng.getExamType().equals("II102")) {
			// questMng.setBranchNo(questMng.getBranchNo2());
			// }

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = null;

			if (!questMng.getQuestType().equals("HH103")) {
				questMngService.createExampleMng(questMng, request);

				if (questMng.getExamType().equals("II102")) {
					String fileDesc = null;
					String[] examNos = questMng.getExamNo().split(",");

					String orgFname = null;
					String savFname = null;
					String filePath = null;
					String[] fileSizes = questMng.getFileSize().split(",");
					for (int i = 0; i < examNos.length; i++) {
						if (questMng.getQuestType().equals("HH101")) {
							multipartFile = multipartRequest.getFile("imgMng12_" + (i + 1));

							fileDesc = multipartRequest.getParameter("fileDesc12_" + (i + 1));

							orgFname = multipartRequest.getParameter("orgFname12_" + (i + 1));
							savFname = multipartRequest.getParameter("savFname12_" + (i + 1));
							filePath = multipartRequest.getParameter("filePath12_" + (i + 1));
						}
						else if (questMng.getQuestType().equals("HH102")) {
							multipartFile = multipartRequest.getFile("imgMng22_" + (i + 1));

							fileDesc = multipartRequest.getParameter("fileDesc22_" + (i + 1));

							orgFname = multipartRequest.getParameter("orgFname22_" + (i + 1));
							savFname = multipartRequest.getParameter("savFname22_" + (i + 1));
							filePath = multipartRequest.getParameter("filePath22_" + (i + 1));
						}
						else if (questMng.getQuestType().equals("HH104")) {
							multipartFile = multipartRequest.getFile("imgMng42_" + (i + 1));

							fileDesc = multipartRequest.getParameter("fileDesc42_" + (i + 1));

							orgFname = multipartRequest.getParameter("orgFname42_" + (i + 1));
							savFname = multipartRequest.getParameter("savFname42_" + (i + 1));
							filePath = multipartRequest.getParameter("filePath42_" + (i + 1));
						}

						questMng.setFileDesc(fileDesc);
						questMng.setExamNo(examNos[i]);

						questMng.setOrgFname(orgFname);
						questMng.setSavFname(savFname);
						questMng.setFilePath(filePath);
						questMng.setFileSize(fileSizes[i]);

						if (!multipartFile.isEmpty()) {
							questMngService.createImgMng(questMng, multipartFile);
						}
						else {
							questMngService.createImgMng(questMng);
						}
					}
				}
			}

			questMngService.removeQuestAddfile(questMng);
			questMng.setPosition("U");

			multipartFile = multipartRequest.getFile("questAddfileu");

			questMng.setOrgFname(multipartRequest.getParameter("orgFnameu"));
			questMng.setSavFname(multipartRequest.getParameter("savFnameu"));
			questMng.setFilePath(multipartRequest.getParameter("filePathu"));
			questMng.setFileSize(multipartRequest.getParameter("fileSizeu"));
			questMng.setDelYn(questMng.getDelYnu());

			if (!multipartFile.isEmpty()) {
				questMngService.createQuestAddfile(questMng, multipartFile);
			}
			else if (questMng.getOrgFname() != null && !questMng.getDelYn().equals("Y")) {
				questMngService.createQuestAddfile(questMng);
			}

			questMng.setPosition("D");

			multipartFile = multipartRequest.getFile("questAddfiled");

			questMng.setOrgFname(multipartRequest.getParameter("orgFnamed"));
			questMng.setSavFname(multipartRequest.getParameter("savFnamed"));
			questMng.setFilePath(multipartRequest.getParameter("filePathd"));
			questMng.setFileSize(multipartRequest.getParameter("fileSized"));
			questMng.setDelYn(questMng.getDelYnd());

			if (!multipartFile.isEmpty()) {
				questMngService.createQuestAddfile(questMng, multipartFile);
			}
			else if (questMng.getOrgFname() != null && !questMng.getDelYn().equals("Y")) {
				questMngService.createQuestAddfile(questMng);
			}

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.questmngcontroller.update");
		}

		status.setComplete();

		return new ModelAndView("detailQuestMngAction");
	}

	/**
	 * delete questMng
	 * 
	 * @param questMng
	 * @param questMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteQuestMng.do")
	public ModelAndView delete(@RequestParam("pollIdx") int pollIdx, @RequestParam("questNo") int questNo,
			QuestMngSearchVO questMngSearchVO) throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			QuestMng questMng = new QuestMng();
			questMng.setPollIdx(pollIdx);
			questMng.setQuestNo(questNo);

			questMngService.removeQuestTypeMng(questMng);

			questMngService.removeFilterMng(questMng);

			questMngService.removeImgMng(questMng);
			questMngService.removeExampleMng(questMng);

			questMngService.removeQuestAddfile(questMng);

			questMngService.removeQuestMng(questMng);

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.questmngcontroller.delete");
		}

		return new ModelAndView("detailQuestMngAction", "questMngSearchVO", questMngSearchVO);
	}

	@RequestMapping("/setPollQuestStatus.do")
	public ModelAndView setPollQuestStatus(@RequestParam("pollIdx") int pollIdx,
			@RequestParam("pollQuestStatus") String pollQuestStatus, QuestMngSearchVO questMngSearchVO) throws Exception {
		try {
			questMngService.setPollQuestStatus(pollIdx, pollQuestStatus);
		}
		catch (Exception ex) {
			throw new BaseException(messageSource, "error.questmngcontroller.set");
		}
		return new ModelAndView("listQuestMngAction");
	}

	@RequestMapping("/downloadQuestMng.do")
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
