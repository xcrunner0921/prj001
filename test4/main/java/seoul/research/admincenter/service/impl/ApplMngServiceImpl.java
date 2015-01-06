package seoul.research.admincenter.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import seoul.research.admincenter.dao.ApplMngDAO;
import seoul.research.admincenter.service.ApplMngSearchVO;
import seoul.research.admincenter.service.ApplMngService;
import seoul.research.domain.PollAddfile;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;

import com.sds.emp.common.EmpException;

/**
 * This ApplMngServiceImpl is implementing from ApplMngService, this class contains
 * ApplMngDAO,ProductService,IIdGenerationService setter methods and createApplMng,
 * updateApplMng,findApplMngList,getDropDownApplMngList and findApplMng method.
 * 
 * @author Jeryeon Kim
 */

@Service("applMngService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class ApplMngServiceImpl implements ApplMngService {

	private static Log log = LogFactory.getLog(ApplMngServiceImpl.class);

	/**
	 * applMngDAO is used to access the ApplMngDAO methods createApplMng, updateApplMng, removeApplMng,
	 * findApplMng,findApplMngList and getDropDownApplMngList.
	 */
	@Resource(name = "applMngDAOQuery")
	// @Resource(name="applMngDAOHibernate")
	private ApplMngDAO applMngDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	// @Resource
	// private ProductService productService;

	/**
	 * IIdGenerationService Object is used to get idGenerationService services.
	 */
	// @Resource(name = "idGenerationServiceApplMng")
	// private IIdGenerationService idGenerationService;

	/**
	 * messageSource is used to call getMessage method.
	 */
	// well-known "resolvable dependencies" using
	// @Resource
	@Resource
	private MessageSource messageSource;

	@Resource
	private IPropertiesService propertiesService;

	public Page findApplMngList(ApplMngSearchVO searchVO) throws Exception {
		return applMngDAO.findPollMngList(searchVO);
	}

	public void createApplMng(PollMng pollMng) throws Exception {

		// Id generation Service
		// The related file is
		// [src/main/resource/spring/common/context-idgen.xml]
		// int pollIdx = idGenerationService.getNextIntegerId();
		int pollIdx = applMngDAO.getNextPollIdx();
		pollMng.setPollIdx(pollIdx);

		applMngDAO.createPollMng(pollMng);
		log.info("One pollMng is created.");
	}

	public PollMng findApplMng(int pollIdx) throws Exception {

		PollMng pollMng = applMngDAO.findPollMng(pollIdx);

		if (pollMng == null) {

			throw new EmpException(messageSource.getMessage("error.pollMng.get.notexists", new String[] { Integer
					.toString(pollIdx) }, Locale.getDefault()));

		}

		return pollMng;
	}

	public PollMng findApplMng2(int pollIdx) throws Exception {

		PollMng pollMng = applMngDAO.findPollMng2(pollIdx);

		if (pollMng == null) {

			throw new EmpException(messageSource.getMessage("error.pollMng.get.notexists", new String[] { Integer
					.toString(pollIdx) }, Locale.getDefault()));

		}

		return pollMng;
	}

	public void updateApplMng(PollMng pollMng) throws Exception {

		// if ("Y".equals(pollMng.getUseYn())) {
		// && productService.countProductListByApplMng(pollMng
		// .getApplMngNo()) > 0) {

		// throw new EmpException(messageSource.getMessage("error.pollMng.update.if", new String[] { pollMng.getApplMngNo() },
		// Locale.getDefault()));
		// }
		applMngDAO.updatePollMng(pollMng);
		log.info("PollMng that has no. '" + pollMng.getPollIdx() + "' is updated.");
	}

	public void removeApplMng(PollMng pollMng) throws Exception {

		// int pollIdx = pollMng.getPollIdx();

		// if (productService.countProductListByApplMng(applMngNo) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.pollMng.remove.if", new String[] {applMngNo }, Locale
		// .getDefault()));
		// }
		applMngDAO.removePollMng(pollMng);
		log.info("PollMng that has no. '" + pollMng.getPollIdx() + "' is removed.");
	}

	// public Collection findPollPartMfList(int pollIdx) throws Exception {
	// return applMngDAO.findPollPartList(pollIdx, "1");
	// }

	// public Collection findPollPartAgeList(int pollIdx) throws Exception {
	// return applMngDAO.findPollPartList(pollIdx, "2");
	// }

	// public Collection findPollPartRegionList(int pollIdx) throws Exception {
	// return applMngDAO.findPollPartList(pollIdx, "3");
	// }

	public Collection findPollAddfileList(int pollIdx) throws Exception {
		return applMngDAO.findPollAddfileList(pollIdx, "1");
	}

	public Collection findPollAddfile2List(int pollIdx) throws Exception {
		return applMngDAO.findPollAddfileList(pollIdx, "2");
	}

	public Collection findQuestMngList(int pollIdx) throws Exception {
		ArrayList questMngList = (ArrayList) applMngDAO.findQuestMngList(pollIdx);

		for (int i = 0; i < questMngList.size(); i++) {
			QuestMng questMng = (QuestMng) questMngList.get(i);

			questMng.setAnswerMngList(applMngDAO.findAnswerMngList(pollIdx, questMng.getQuestNo()));
		}

		return questMngList;
	}

	public Collection findQuestMng2List(int pollIdx) throws Exception {
		ArrayList questMngList = (ArrayList) applMngDAO.findQuestMng2List(pollIdx);

		for (int i = 0; i < questMngList.size(); i++) {
			QuestMng questMng = (QuestMng) questMngList.get(i);

			questMng.setAnswerMngList(applMngDAO.findAnswerMng2List(pollIdx, questMng.getQuestNo(), questMng.getQuestType()));
		}

		return questMngList;
	}

	public Collection findQuestMng3List(int pollIdx) throws Exception {
		return applMngDAO.findQuestMng3List(pollIdx);
	}

	public QuestMng findQuestMng(int pollIdx) throws Exception {
		return applMngDAO.findQuestMng(pollIdx);
	}

	public Collection findPanelStateList(int pollIdx) throws Exception {
		return applMngDAO.findPanelStateList(pollIdx);
	}

	// public void createPollPartMf(PollMng pollMng) throws Exception {
	// // int pollIdx = pollMng.getPollIdx();
	// String[] porMfCodes = pollMng.getPorMfCode().split(",");
	// String[] partNumberMfs = pollMng.getPartNumberMf().split(",");
	//
	// for (int i = 0; i < porMfCodes.length; i++) {
	// PollPart pollPart = new PollPart();
	// pollPart.setPollIdx(pollMng.getPollIdx());
	// pollPart.setPartGubun("1");
	// pollPart.setPorCode(porMfCodes[i]);
	//
	// pollPart.setPartNumber(Integer.parseInt(partNumberMfs[i]));
	//
	// applMngDAO.createPollPart(pollPart);
	// }
	//
	// log.info("One pollPartMf is created.");
	// }

	// public void createPollPartAge(PollMng pollMng) throws Exception {
	// // int pollIdx = pollMng.getPollIdx();
	// String[] porAgeCodes = pollMng.getPorAgeCode().split(",");
	// String[] partNumberAges = pollMng.getPartNumberAge().split(",");
	//
	// for (int i = 0; i < porAgeCodes.length; i++) {
	// PollPart pollPart = new PollPart();
	// pollPart.setPollIdx(pollMng.getPollIdx());
	// pollPart.setPartGubun("2");
	// pollPart.setPorCode(porAgeCodes[i]);
	//
	// pollPart.setPartNumber(Integer.parseInt(partNumberAges[i]));
	//
	// applMngDAO.createPollPart(pollPart);
	// }
	//
	// log.info("One pollPartAge is created.");
	// }

	// public void createPollPartRegion(PollMng pollMng) throws Exception {
	// // int pollIdx = pollMng.getPollIdx();
	// String[] porRegionCodes = pollMng.getPorRegionCode().split(",");
	// String[] partNumberRegions = pollMng.getPartNumberRegion().split(",");
	//
	// for (int i = 0; i < porRegionCodes.length; i++) {
	// PollPart pollPart = new PollPart();
	// pollPart.setPollIdx(pollMng.getPollIdx());
	// pollPart.setPartGubun("3");
	// pollPart.setPorCode(porRegionCodes[i]);
	//
	// pollPart.setPartNumber(Integer.parseInt(partNumberRegions[i]));
	//
	// applMngDAO.createPollPart(pollPart);
	// }
	//
	// log.info("One pollPartRegion is created.");
	// }

	public void createPollAddfile(PollMng pollMng, MultipartFile multipartFile) throws Exception {
		// String originalFileName = multipartFile.getOriginalFilename();
		String survmngDir = propertiesService.getString("SURVMNG_DIR");
		File directory = new File(survmngDir);
		File tempFile = File.createTempFile("poll", multipartFile.getOriginalFilename(), directory);
		FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollMng.getPollIdx());
		int sNo = applMngDAO.getNextSNo(pollMng.getPollIdx());
		pollAddfile.setSNo(sNo);

		pollAddfile.setFileGubun("1");
		pollAddfile.setOrgFname(multipartFile.getOriginalFilename());
		pollAddfile.setSavFname(tempFile.getName());
		pollAddfile.setFilePath("/upload/survmng/");
		pollAddfile.setFileSize((int) multipartFile.getSize());

		applMngDAO.createPollAddfile(pollAddfile);
	}

	public void createPollAddfile(PollMng pollMng) throws Exception {
		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollMng.getPollIdx());
		int sNo = applMngDAO.getNextSNo(pollMng.getPollIdx());
		pollAddfile.setSNo(sNo);

		pollAddfile.setFileGubun("1");
		pollAddfile.setOrgFname(pollMng.getOrgFname());
		pollAddfile.setSavFname(pollMng.getSavFname());
		pollAddfile.setFilePath("/upload/survmng/");
		pollAddfile.setFileSize(pollMng.getFileSize());

		applMngDAO.createPollAddfile(pollAddfile);
	}

	public void createPollAddfile2(PollMng pollMng) throws Exception {
		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollMng.getPollIdx());
		int sNo = applMngDAO.getNextSNo(pollMng.getPollIdx());
		pollAddfile.setSNo(sNo);

		pollAddfile.setFileGubun("2");
		// pollAddfile.setOrgFname(multipartFile.getOriginalFilename());
		pollAddfile.setSavFname(pollMng.getSavFname());
		// pollAddfile.setFilePath("/upload/survmng/");
		// pollAddfile.setFileSize((int) multipartFile.getSize());

		applMngDAO.createPollAddfile(pollAddfile);
	}

	// public void removePollPart(PollMng pollMng) throws Exception {
	// // int pollIdx = pollMng.getPollIdx();
	//
	// PollPart pollPart = new PollPart();
	// pollPart.setPollIdx(pollMng.getPollIdx());
	// // if (productService.countProductListByApplMng(applMngNo) > 0) {
	//
	// // throw new EmpException(messageSource.getMessage(
	// // "error.pollMng.remove.if", new String[] {applMngNo }, Locale
	// // .getDefault()));
	// // }
	// applMngDAO.removePollPart(pollPart);
	// }

	public void removePollAddfile(PollMng pollMng) throws Exception {
		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollMng.getPollIdx());
		// pollAddfile.setFileGubun("1");

		applMngDAO.removePollAddfile(pollAddfile);
	}

	// public void removePollAddfile2(PollMng pollMng) throws Exception {
	// PollAddfile pollAddfile = new PollAddfile();
	// pollAddfile.setPollIdx(pollMng.getPollIdx());
	// pollAddfile.setFileGubun("2");
	//
	// applMngDAO.removePollAddfile(pollAddfile);
	// }
}
