package seoul.research.admincenter.dao;

import java.util.Collection;

import seoul.research.admincenter.service.QuestMngSearchVO;
import seoul.research.domain.ExampleMng;
import seoul.research.domain.FilterMng;
import seoul.research.domain.ImgMng;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestAddfile;
import seoul.research.domain.QuestMng;
import seoul.research.domain.QuestTypeMng;
import anyframe.common.Page;

/**
 * This DAO interface contains declaration of addQuestMng, updateQuestMng, findQuestMng, findQuestMngList and
 * getDropDownQuestMngList methods for different functionality on QuestMng.
 * 
 * @author Jeryeon Kim
 */
public interface QuestMngDAO {

	/**
	 * This findQuestMngList method is used to get list of QuestMng with propertiesService Services from database
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the QuestMngList
	 * @throws Exception
	 *             Exception will raised, when findQuestMngList failed
	 */
	Page findPollMngList(QuestMngSearchVO searchVO) throws Exception;

	PollMng findPollMng(int pollIdx) throws Exception;

	Collection findQuestMngList(int pollIdx) throws Exception;

	/**
	 * This createQuestMng is used to add new QuestMng to the database with questMngNo,questMngName,questMngDesc,useYn,regId
	 * 
	 * @param questMng
	 *            questMng parameter is used to get the questMng details
	 * @throws Exception
	 *             Exception will raise, when createQuestMng method failed
	 */
	void createQuestMng(QuestMng questMng) throws Exception;

	/**
	 * This findQuestMng method is used to the QuestMng details of specified questMngNo from database
	 * 
	 * @param questMngNo
	 *            questMngNo is used to get the details of specified questMng Number
	 * @return QuestMng QuestMng is contains questMng details
	 * @throws Exception
	 *             Exception will raised, when findgetQuestMng failed
	 */
	QuestMng findQuestMng(int pollIdx, int questNo) throws Exception;

	/**
	 * This updateQuestMng method is used to update the questMng data to database
	 * 
	 * @param questMng
	 *            questMng is used to get the questMng details
	 * @throws Exception
	 *             Exception will be raise, when updateQuestMng failed
	 */
	void updateQuestMng(QuestMng questMng) throws Exception;

	/**
	 * This removeQuestMng method is used to remove the questMng data from database
	 * 
	 * @param questMng
	 *            questMng is used to get the questMng details
	 * @throws Exception
	 *             Exception will be raise, when removeQuestMng failed
	 */
	void removeQuestMng(QuestMng questMng) throws Exception;

	int getNextQuestNo(int pollIdx) throws Exception;

	void setPollQuestStatus(int pollIdx, String pollQuestStatus) throws Exception;

	Collection findExampleMngList(int pollIdx, int questNo) throws Exception;

	Collection findExampleMng2List(int pollIdx, int questNo) throws Exception;

	Collection findImgMngList(int pollIdx, int questNo) throws Exception;

	Collection findQuestAddfileList(int pollIdx, int questNo) throws Exception;

	void createQuestTypeMng(QuestTypeMng questTypeMng) throws Exception;

	void createFilterMng(FilterMng filterMng) throws Exception;

	void createExampleMng(ExampleMng exampleMng) throws Exception;

	void createImgMng(ImgMng imgMng) throws Exception;

	void createQuestAddfile(QuestAddfile questAddfile) throws Exception;

	void removeQuestTypeMng(QuestTypeMng questTypeMng) throws Exception;

	void removeFilterMng(FilterMng filterMng) throws Exception;

	void removeExampleMng(ExampleMng exampleMng) throws Exception;

	void removeImgMng(ImgMng imgMng) throws Exception;

	void removeQuestAddfile(QuestAddfile questAddfile) throws Exception;
}
