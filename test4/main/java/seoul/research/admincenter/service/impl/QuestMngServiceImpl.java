package seoul.research.admincenter.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import seoul.research.admincenter.dao.QuestMngDAO;
import seoul.research.admincenter.service.QuestMngSearchVO;
import seoul.research.admincenter.service.QuestMngService;
import seoul.research.domain.ExampleMng;
import seoul.research.domain.FilterMng;
import seoul.research.domain.ImgMng;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestAddfile;
import seoul.research.domain.QuestMng;
import seoul.research.domain.QuestTypeMng;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;

import com.sds.emp.common.EmpException;

/**
 * This QuestMngServiceImpl is implementing from QuestMngService, this class contains
 * QuestMngDAO,ProductService,IIdGenerationService setter methods and createQuestMng,
 * updateQuestMng,findQuestMngList,getDropDownQuestMngList and findQuestMng method.
 * 
 * @author Jeryeon Kim
 */

@Service("questMngService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class QuestMngServiceImpl implements QuestMngService {

	private static Log log = LogFactory.getLog(QuestMngServiceImpl.class);

	/**
	 * questMngDAO is used to access the QuestMngDAO methods createQuestMng, updateQuestMng, removeQuestMng,
	 * findQuestMng,findQuestMngList and getDropDownQuestMngList.
	 */
	@Resource(name = "questMngDAOQuery")
	// @Resource(name="questMngDAOHibernate")
	private QuestMngDAO questMngDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	// @Resource
	// private ProductService productService;

	/**
	 * IIdGenerationService Object is used to get idGenerationService services.
	 */
	// @Resource(name = "idGenerationServiceQuestMng")
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

	public Page findPollMngList(QuestMngSearchVO searchVO) throws Exception {
		return questMngDAO.findPollMngList(searchVO);
	}

	public PollMng findPollMng(int pollIdx) throws Exception {

		PollMng pollMng = questMngDAO.findPollMng(pollIdx);

		if (pollMng == null) {

			throw new EmpException(messageSource.getMessage("error.questMng.get.notexists", new String[] { Integer
					.toString(pollIdx) }, Locale.getDefault()));

		}

		return pollMng;
	}

	public Collection findQuestMngList(int pollIdx) throws Exception {
		ArrayList questMngList = (ArrayList) questMngDAO.findQuestMngList(pollIdx);

		for (int i = 0; i < questMngList.size(); i++) {
			QuestMng questMng = (QuestMng) questMngList.get(i);

			questMng.setExampleMngList(questMngDAO.findExampleMngList(questMng.getPollIdx(), questMng.getQuestNo()));
			questMng.setImgMngList(questMngDAO.findImgMngList(questMng.getPollIdx(), questMng.getQuestNo()));
			questMng.setQuestAddfileList(questMngDAO.findQuestAddfileList(questMng.getPollIdx(), questMng.getQuestNo()));
		}

		return questMngList;
	}

	public void createQuestMng(QuestMng questMng) throws Exception {

		// Id generation Service
		// The related file is
		// [src/main/resource/spring/common/context-idgen.xml]
		// int pollIdx = idGenerationService.getNextIntegerId();
		questMngDAO.createQuestMng(questMng);
		log.info("One questMng is created.");
	}

	public QuestMng findQuestMng(int pollIdx, int questNo) throws Exception {

		QuestMng questMng = questMngDAO.findQuestMng(pollIdx, questNo);

		if (questMng == null) {

			throw new EmpException(messageSource.getMessage("error.questMng.get.notexists", new String[] { Integer
					.toString(pollIdx) }, Locale.getDefault()));

		}

		questMng.setExampleMngList(questMngDAO.findExampleMngList(questMng.getPollIdx(), questMng.getQuestNo()));

		if (!questMng.getMergeType().equals("9")) {
			ArrayList exampleMng2List = (ArrayList) questMngDAO.findExampleMng2List(questMng.getPollIdx(), questMng
					.getQuestNo());

			for (int i = 0; i < exampleMng2List.size(); i++) {
				ExampleMng exampleMng = (ExampleMng) exampleMng2List.get(i);
				switch (i) {
				case 0:
					questMng.setMergeTitle(exampleMng.getMergeTitle());
					break;
				case 1:
					questMng.setMergeTitle2(exampleMng.getMergeTitle());
					break;
				case 2:
					questMng.setMergeTitle3(exampleMng.getMergeTitle());
					break;
				}
			}
		}

		questMng.setImgMngList(questMngDAO.findImgMngList(questMng.getPollIdx(), questMng.getQuestNo()));

		questMng.setQuestAddfileList(questMngDAO.findQuestAddfileList(questMng.getPollIdx(), questMng.getQuestNo()));
		return questMng;
	}

	public void updateQuestMng(QuestMng questMng) throws Exception {

		// if ("Y".equals(questMng.getUseYn())) {
		// && productService.countProductListByQuestMng(questMng
		// .getQuestMngNo()) > 0) {

		// throw new EmpException(messageSource.getMessage("error.questMng.update.if", new String[] { questMng.getQuestMngNo()
		// },
		// Locale.getDefault()));
		// }
		questMngDAO.updateQuestMng(questMng);
		log.info("QuestMng that has no. '" + questMng.getPollIdx() + "' is updated.");
	}

	public void removeQuestMng(QuestMng questMng) throws Exception {

		// int pollIdx = questMng.getPollIdx();

		// if (productService.countProductListByQuestMng(questMngNo) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.questMng.remove.if", new String[] {questMngNo }, Locale
		// .getDefault()));
		// }
		questMngDAO.removeQuestMng(questMng);
		log.info("QuestMng that has no. '" + questMng.getPollIdx() + "' is removed.");
	}

	public int getQuestNo(int pollIdx) throws Exception {
		return questMngDAO.getNextQuestNo(pollIdx);
	}

	public void setPollQuestStatus(int pollIdx, String pollQuestStatus) throws Exception {
		questMngDAO.setPollQuestStatus(pollIdx, pollQuestStatus);
	}

	public void createQuestTypeMng(QuestMng questMng) throws Exception {
		QuestTypeMng questTypeMng = new QuestTypeMng();
		questTypeMng.setPollIdx(questMng.getPollIdx());
		questTypeMng.setQuestNo(questMng.getQuestNo());
		questTypeMng.setQuestType(questMng.getQuestType());

		questTypeMng.setChoiceCnt(Integer.parseInt(questMng.getChoiceCnt()));
		questTypeMng.setChoiceRange(questMng.getChoiceRange());

		questMngDAO.createQuestTypeMng(questTypeMng);
	}

	public void createFilterMng(QuestMng questMng) throws Exception {
		FilterMng filterMng = new FilterMng();
		filterMng.setPollIdx(questMng.getPollIdx());
		filterMng.setQuestNo(questMng.getQuestNo());

		filterMng.setFilterAnswer(questMng.getFilterAnswer());

		questMngDAO.createFilterMng(filterMng);
	}

	public void createExampleMng(QuestMng questMng, HttpServletRequest request) throws Exception {
		// int pollIdx = questMng.getPollIdx();
		// int questNo = questMng.getQuestNo();
		// String questType = questMng.getQuestType();
		// String examType = questMng.getExamType();
		String[] examNos = questMng.getExamNo().split(",");
		// String branchYn = questMng.getBranchYn();
		String[] branchNos = null;
		if (questMng.getBranchNo() != null) {
			branchNos = questMng.getBranchNo().split(",");
		}
		// String mergeType = questMng.getMergeType();
		// String mergeTitle = questMng.getMergeTitle();
		// String mergeTitle2 = questMng.getMergeTitle2();
		// String mergeTitle3 = questMng.getMergeTitle3();

		for (int i = 0; i < examNos.length; i++) {
			ExampleMng exampleMng = new ExampleMng();
			exampleMng.setPollIdx(questMng.getPollIdx());
			exampleMng.setQuestNo(questMng.getQuestNo());
			exampleMng.setExamNo(Integer.parseInt(examNos[i]));

			if (questMng.getExamType().equals("II101")) {
				String examTitle = null;
				if (questMng.getQuestType().equals("HH101")) {
					examTitle = request.getParameter("examTitle11_" + (i + 1));
				}
				else if (questMng.getQuestType().equals("HH102")) {
					examTitle = request.getParameter("examTitle21_" + (i + 1));
				}
				else if (questMng.getQuestType().equals("HH104")) {
					examTitle = request.getParameter("examTitle41_" + (i + 1));
				}
				exampleMng.setExamTitle(examTitle);
			}
			if (questMng.getBranchYn().equals("Y")) {
				exampleMng.setBranchNo(Integer.parseInt(branchNos[i]));
				exampleMng.setBranchYn("Y");
			}
			if (questMng.getMergeType().equals("1")) {
				if (i == 0 || i == 1) {
					exampleMng.setMergeNo("1,2");
					exampleMng.setMergeTitle(questMng.getMergeTitle());
				}
				else if (i == 2 || i == 3) {
					exampleMng.setMergeNo("3,4");
					exampleMng.setMergeTitle(questMng.getMergeTitle2());
				}
			}
			else if (questMng.getMergeType().equals("2")) {
				if (i == 0 || i == 1) {
					exampleMng.setMergeNo("1,2");
					exampleMng.setMergeTitle(questMng.getMergeTitle());
				}
				else if (i == 2) {
					exampleMng.setMergeNo("3");
					exampleMng.setMergeTitle(questMng.getMergeTitle2());
				}
				else if (i == 3 || i == 4) {
					exampleMng.setMergeNo("4,5");
					exampleMng.setMergeTitle(questMng.getMergeTitle3());
				}
			}
			else if (questMng.getMergeType().equals("3")) {
				if (i == 0 || i == 1 || i == 2) {
					exampleMng.setMergeNo("1,2,3");
					exampleMng.setMergeTitle(questMng.getMergeTitle());
				}
				else if (i == 3) {
					exampleMng.setMergeNo("4");
					exampleMng.setMergeTitle(questMng.getMergeTitle2());
				}
			}

			questMngDAO.createExampleMng(exampleMng);
		}

		log.info("One exampleMng is created.");
	}

	public void createImgMng(QuestMng questMng, MultipartFile multipartFile) throws Exception {
		// String originalFileName = multipartFile.getOriginalFilename();
		String survmngDir = propertiesService.getString("SURVMNG_DIR");
		File directory = new File(survmngDir);
		File tempFile = File.createTempFile("example", multipartFile.getOriginalFilename(), directory);
		FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

		ImgMng imgMng = new ImgMng();
		imgMng.setPollIdx(questMng.getPollIdx());
		imgMng.setQuestNo(questMng.getQuestNo());
		imgMng.setExamNo(Integer.parseInt(questMng.getExamNo()));

		imgMng.setOrgFname(multipartFile.getOriginalFilename());
		imgMng.setSavFname(tempFile.getName());
		imgMng.setFilePath("/upload/survmng/");
		imgMng.setFileSize((int) multipartFile.getSize());
		imgMng.setFileDesc(questMng.getFileDesc());

		questMngDAO.createImgMng(imgMng);
	}

	public void createImgMng(QuestMng questMng) throws Exception {
		ImgMng imgMng = new ImgMng();
		imgMng.setPollIdx(questMng.getPollIdx());
		imgMng.setQuestNo(questMng.getQuestNo());
		imgMng.setExamNo(Integer.parseInt(questMng.getExamNo()));

		imgMng.setOrgFname(questMng.getOrgFname());
		imgMng.setSavFname(questMng.getSavFname());
		imgMng.setFilePath(questMng.getFilePath());
		imgMng.setFileSize(Integer.parseInt(questMng.getFileSize()));
		imgMng.setFileDesc(questMng.getFileDesc());

		questMngDAO.createImgMng(imgMng);
	}

	public void createQuestAddfile(QuestMng questMng, MultipartFile multipartFile) throws Exception {
		// String originalFileName = multipartFile.getOriginalFilename();
		String survmngDir = propertiesService.getString("SURVMNG_DIR");
		File directory = new File(survmngDir);
		File tempFile = File.createTempFile("quest", multipartFile.getOriginalFilename(), directory);
		FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

		QuestAddfile questAddfile = new QuestAddfile();
		questAddfile.setPollIdx(questMng.getPollIdx());
		questAddfile.setQuestNo(questMng.getQuestNo());
		questAddfile.setPosition(questMng.getPosition());

		questAddfile.setOrgFname(multipartFile.getOriginalFilename());
		questAddfile.setSavFname(tempFile.getName());
		questAddfile.setFilePath("/upload/survmng/");
		questAddfile.setFileSize((int) multipartFile.getSize());

		questMngDAO.createQuestAddfile(questAddfile);
	}

	public void createQuestAddfile(QuestMng questMng) throws Exception {
		QuestAddfile questAddfile = new QuestAddfile();
		questAddfile.setPollIdx(questMng.getPollIdx());
		questAddfile.setQuestNo(questMng.getQuestNo());
		questAddfile.setPosition(questMng.getPosition());

		questAddfile.setOrgFname(questMng.getOrgFname());
		questAddfile.setSavFname(questMng.getSavFname());
		questAddfile.setFilePath(questMng.getFilePath());
		questAddfile.setFileSize(Integer.parseInt(questMng.getFileSize()));

		questMngDAO.createQuestAddfile(questAddfile);
	}

	public void removeQuestTypeMng(QuestMng questMng) throws Exception {
		QuestTypeMng questTypeMng = new QuestTypeMng();
		questTypeMng.setPollIdx(questMng.getPollIdx());
		questTypeMng.setQuestNo(questMng.getQuestNo());

		questMngDAO.removeQuestTypeMng(questTypeMng);
	}

	public void removeFilterMng(QuestMng questMng) throws Exception {
		FilterMng filterMng = new FilterMng();
		filterMng.setPollIdx(questMng.getPollIdx());
		filterMng.setQuestNo(questMng.getQuestNo());

		questMngDAO.removeFilterMng(filterMng);
	}

	public void removeExampleMng(QuestMng questMng) throws Exception {
		ExampleMng exampleMng = new ExampleMng();
		exampleMng.setPollIdx(questMng.getPollIdx());
		exampleMng.setQuestNo(questMng.getQuestNo());

		questMngDAO.removeExampleMng(exampleMng);
	}

	public void removeImgMng(QuestMng questMng) throws Exception {
		ImgMng imgMng = new ImgMng();
		imgMng.setPollIdx(questMng.getPollIdx());
		imgMng.setQuestNo(questMng.getQuestNo());

		questMngDAO.removeImgMng(imgMng);
	}

	public void removeQuestAddfile(QuestMng questMng) throws Exception {
		QuestAddfile questAddfile = new QuestAddfile();
		questAddfile.setPollIdx(questMng.getPollIdx());
		questAddfile.setQuestNo(questMng.getQuestNo());

		questMngDAO.removeQuestAddfile(questAddfile);
	}
}
