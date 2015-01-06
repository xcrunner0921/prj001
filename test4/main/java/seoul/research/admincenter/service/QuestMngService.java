package seoul.research.admincenter.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import anyframe.common.Page;

/**
 * This QuestMngService interface class contains declaration of createQuestMng, updateQuestMng, removeQuestMng,
 * findQuestMngList, getDropDownQuestMngList and findQuestMng method.
 * 
 * @author Jeryeon Kim
 */
public interface QuestMngService {

	/**
	 * @param searchVO
	 *            This findQuestMngList method is used to get list of QuestMng with propertiesService Services
	 * @return page
	 * @throws Exception
	 *             Exception will raised, when findQuestMngList failed
	 */
	Page findPollMngList(QuestMngSearchVO searchVO) throws Exception;

	PollMng findPollMng(int pollIdx) throws Exception;

	Collection findQuestMngList(int pollIdx) throws Exception;

	/**
	 * @param questMng
	 *            This createQuestMng is used to add new QuestMng with questMngNo,questMngName,questMngDesc,useYn,regId
	 * @return String
	 * @throws Exception
	 *             Exception will raise, when createQuestMng method failed
	 */
	void createQuestMng(QuestMng questMng) throws Exception;

	/**
	 * @param questMngNo
	 *            This findQuestMng method is used to the QuestMng details of specified questMngNo
	 * @return QuestMng
	 * @throws Exception
	 *             Exception will raised, when findQuestMngList failed
	 */
	QuestMng findQuestMng(int pollIdx, int questNo) throws Exception;

	/**
	 * @param questMng
	 *            This updateQuestMng method is used to update the questMng data
	 * @throws Exception
	 *             Exception will be raise, when updateQuestMng failed
	 */
	void updateQuestMng(QuestMng questMng) throws Exception;

	/**
	 * @param questMng
	 *            This removeQuestMng method is used to remove the questMng data
	 * @throws Exception
	 *             Exception will be raise, when removeQuestMng failed
	 */
	void removeQuestMng(QuestMng questMng) throws Exception;

	int getQuestNo(int pollIdx) throws Exception;

	void setPollQuestStatus(int pollIdx, String pollQuestStatus) throws Exception;

	void createQuestTypeMng(QuestMng questMng) throws Exception;

	void createFilterMng(QuestMng questMng) throws Exception;

	void createExampleMng(QuestMng questMng, HttpServletRequest request) throws Exception;

	void createImgMng(QuestMng questMng, MultipartFile multipartFile) throws Exception;

	void createImgMng(QuestMng questMng) throws Exception;

	void createQuestAddfile(QuestMng questMng, MultipartFile multipartFile) throws Exception;

	void createQuestAddfile(QuestMng questMng) throws Exception;

	void removeQuestTypeMng(QuestMng questMng) throws Exception;

	void removeFilterMng(QuestMng questMng) throws Exception;

	void removeExampleMng(QuestMng questMng) throws Exception;

	void removeImgMng(QuestMng questMng) throws Exception;

	void removeQuestAddfile(QuestMng questMng) throws Exception;
}
