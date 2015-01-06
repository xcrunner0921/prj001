package seoul.research.admincenter.service.impl;

import java.io.File;
import java.io.FileOutputStream;
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

import seoul.research.admincenter.dao.SurveyMngDAO;
import seoul.research.admincenter.service.SurveyMngSearchVO;
import seoul.research.admincenter.service.SurveyMngService;
import seoul.research.domain.PollAddfile;
import seoul.research.domain.PollMng;
import seoul.research.domain.PollPart;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;

import com.sds.emp.common.EmpException;

/**
 * This SurveyMngServiceImpl is implementing from SurveyMngService, this class contains
 * SurveyMngDAO,ProductService,IIdGenerationService setter methods and createSurveyMng,
 * updateSurveyMng,findSurveyMngList,getDropDownSurveyMngList and findSurveyMng method.
 * 
 * @author Jeryeon Kim
 */

@Service("surveyMngService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class SurveyMngServiceImpl implements SurveyMngService {

	private static Log log = LogFactory.getLog(SurveyMngServiceImpl.class);

	/**
	 * surveyMngDAO is used to access the SurveyMngDAO methods createSurveyMng, updateSurveyMng, removeSurveyMng,
	 * findSurveyMng,findSurveyMngList and getDropDownSurveyMngList.
	 */
	@Resource(name = "surveyMngDAOQuery")
	// @Resource(name="surveyMngDAOHibernate")
	private SurveyMngDAO surveyMngDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	// @Resource
	// private ProductService productService;

	/**
	 * IIdGenerationService Object is used to get idGenerationService services.
	 */
	// @Resource(name = "idGenerationServiceSurveyMng")
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

	public Page findSurveyMngList(SurveyMngSearchVO searchVO) throws Exception {
		return surveyMngDAO.findPollMngList(searchVO);
	}

	public void createSurveyMng(PollMng pollMng) throws Exception {

		// Id generation Service
		// The related file is
		// [src/main/resource/spring/common/context-idgen.xml]
		// int pollIdx = idGenerationService.getNextIntegerId();
		int pollIdx = surveyMngDAO.getNextPollIdx();
		pollMng.setPollIdx(pollIdx);

		surveyMngDAO.createPollMng(pollMng);
		log.info("One pollMng is created.");
	}

	public PollMng findSurveyMng(int pollIdx) throws Exception {

		PollMng pollMng = surveyMngDAO.findPollMng(pollIdx);

		if (pollMng == null) {

			throw new EmpException(messageSource.getMessage("error.pollMng.get.notexists", new String[] { Integer
					.toString(pollIdx) }, Locale.getDefault()));

		}

		return pollMng;
	}

	public PollMng findSurveyMng2(int pollIdx) throws Exception {

		PollMng pollMng = surveyMngDAO.findPollMng2(pollIdx);

		if (pollMng == null) {

			throw new EmpException(messageSource.getMessage("error.pollMng.get.notexists", new String[] { Integer
					.toString(pollIdx) }, Locale.getDefault()));

		}

		return pollMng;
	}

	public void updateSurveyMng(PollMng pollMng) throws Exception {

		// if ("Y".equals(pollMng.getUseYn())) {
		// && productService.countProductListBySurveyMng(pollMng
		// .getSurveyMngNo()) > 0) {

		// throw new EmpException(messageSource.getMessage("error.pollMng.update.if", new String[] { pollMng.getSurveyMngNo() },
		// Locale.getDefault()));
		// }
		surveyMngDAO.updatePollMng(pollMng);
		log.info("PollMng that has no. '" + pollMng.getPollIdx() + "' is updated.");
	}

	public void removeSurveyMng(PollMng pollMng) throws Exception {

		// int pollIdx = pollMng.getPollIdx();

		// if (productService.countProductListBySurveyMng(surveyMngNo) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.pollMng.remove.if", new String[] {surveyMngNo }, Locale
		// .getDefault()));
		// }
		surveyMngDAO.removePollMng(pollMng);
		log.info("PollMng that has no. '" + pollMng.getPollIdx() + "' is removed.");
	}

	public Collection findPollPartMfList(int pollIdx) throws Exception {
		return surveyMngDAO.findPollPartList(pollIdx, "1");
	}

	public Collection findPollPartAgeList(int pollIdx) throws Exception {
		return surveyMngDAO.findPollPartList(pollIdx, "2");
	}

	public Collection findPollPartRegionList(int pollIdx) throws Exception {
		return surveyMngDAO.findPollPartList(pollIdx, "3");
	}

	public Collection findPollAddfileList(int pollIdx) throws Exception {
		return surveyMngDAO.findPollAddfileList(pollIdx, "1");
	}

	public void createPollPartMf(PollMng pollMng) throws Exception {
		// int pollIdx = pollMng.getPollIdx();
		String[] porMfCodes = pollMng.getPorMfCode().split(",");
		String[] partNumberMfs = pollMng.getPartNumberMf().split(",");

		for (int i = 0; i < porMfCodes.length; i++) {
			PollPart pollPart = new PollPart();
			pollPart.setPollIdx(pollMng.getPollIdx());
			pollPart.setPartGubun("1");
			pollPart.setPorCode(porMfCodes[i]);

			pollPart.setPartNumber(Integer.parseInt(partNumberMfs[i]));

			surveyMngDAO.createPollPart(pollPart);
		}

		log.info("One pollPartMf is created.");
	}

	public void createPollPartAge(PollMng pollMng) throws Exception {
		// int pollIdx = pollMng.getPollIdx();
		String[] porAgeCodes = pollMng.getPorAgeCode().split(",");
		String[] partNumberAges = pollMng.getPartNumberAge().split(",");

		for (int i = 0; i < porAgeCodes.length; i++) {
			PollPart pollPart = new PollPart();
			pollPart.setPollIdx(pollMng.getPollIdx());
			pollPart.setPartGubun("2");
			pollPart.setPorCode(porAgeCodes[i]);

			pollPart.setPartNumber(Integer.parseInt(partNumberAges[i]));

			surveyMngDAO.createPollPart(pollPart);
		}

		log.info("One pollPartAge is created.");
	}

	public void createPollPartRegion(PollMng pollMng) throws Exception {
		// int pollIdx = pollMng.getPollIdx();
		String[] porRegionCodes = pollMng.getPorRegionCode().split(",");
		String[] partNumberRegions = pollMng.getPartNumberRegion().split(",");

		for (int i = 0; i < porRegionCodes.length; i++) {
			PollPart pollPart = new PollPart();
			pollPart.setPollIdx(pollMng.getPollIdx());
			pollPart.setPartGubun("3");
			pollPart.setPorCode(porRegionCodes[i]);

			pollPart.setPartNumber(Integer.parseInt(partNumberRegions[i]));

			surveyMngDAO.createPollPart(pollPart);
		}

		log.info("One pollPartRegion is created.");
	}

	public void createPollAddfile(PollMng pollMng, MultipartFile multipartFile) throws Exception {
		// String originalFileName = multipartFile.getOriginalFilename();
		String survmngDir = propertiesService.getString("SURVMNG_DIR");
		File directory = new File(survmngDir);
		File tempFile = File.createTempFile("poll", multipartFile.getOriginalFilename(), directory);
		FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollMng.getPollIdx());
		int sNo = surveyMngDAO.getNextSNo(pollMng.getPollIdx());
		pollAddfile.setSNo(sNo);

		pollAddfile.setFileGubun("1");
		pollAddfile.setOrgFname(multipartFile.getOriginalFilename());
		pollAddfile.setSavFname(tempFile.getName());
		pollAddfile.setFilePath("/upload/survmng/");
		pollAddfile.setFileSize((int) multipartFile.getSize());

		surveyMngDAO.createPollAddfile(pollAddfile);
	}

	public void createPollAddfile(PollMng pollMng) throws Exception {
		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollMng.getPollIdx());
		int sNo = surveyMngDAO.getNextSNo(pollMng.getPollIdx());
		pollAddfile.setSNo(sNo);

		pollAddfile.setFileGubun("1");
		pollAddfile.setOrgFname(pollMng.getOrgFname());
		pollAddfile.setSavFname(pollMng.getSavFname());
		pollAddfile.setFilePath("/upload/survmng/");
		pollAddfile.setFileSize(pollMng.getFileSize());

		surveyMngDAO.createPollAddfile(pollAddfile);
	}

	public void removePollPart(PollMng pollMng) throws Exception {
		// int pollIdx = pollMng.getPollIdx();

		PollPart pollPart = new PollPart();
		pollPart.setPollIdx(pollMng.getPollIdx());
		// if (productService.countProductListBySurveyMng(surveyMngNo) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.pollMng.remove.if", new String[] {surveyMngNo }, Locale
		// .getDefault()));
		// }
		surveyMngDAO.removePollPart(pollPart);
	}

	public void removePollAddfile(PollMng pollMng) throws Exception {
		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollMng.getPollIdx());
		// pollAddfile.setFileGubun("1");

		surveyMngDAO.removePollAddfile(pollAddfile);
	}
}
