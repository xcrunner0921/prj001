package seoul.research.admincenter.dao;

import java.util.Collection;

import seoul.research.admincenter.service.ApplMngSearchVO;
import seoul.research.domain.PollAddfile;
import seoul.research.domain.PollMng;
import seoul.research.domain.PollPart;
import seoul.research.domain.QuestMng;
import anyframe.common.Page;

/**
 * This DAO interface contains declaration of addApplMng, updateApplMng, findApplMng, findApplMngList and getDropDownApplMngList
 * methods for different functionality on PollMng.
 * 
 * @author Jeryeon Kim
 */
public interface ApplMngDAO {

	/**
	 * This findApplMngList method is used to get list of PollMng with propertiesService Services from database
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the ApplMngList
	 * @throws Exception
	 *             Exception will raised, when findApplMngList failed
	 */
	Page findPollMngList(ApplMngSearchVO searchVO) throws Exception;

	/**
	 * This createApplMng is used to add new PollMng to the database with applMngNo,applMngName,applMngDesc,useYn,regId
	 * 
	 * @param pollMng
	 *            pollMng parameter is used to get the pollMng details
	 * @throws Exception
	 *             Exception will raise, when createApplMng method failed
	 */
	void createPollMng(PollMng pollMng) throws Exception;

	/**
	 * This findApplMng method is used to the PollMng details of specified applMngNo from database
	 * 
	 * @param applMngNo
	 *            applMngNo is used to get the details of specified pollMng Number
	 * @return PollMng PollMng is contains pollMng details
	 * @throws Exception
	 *             Exception will raised, when findgetApplMng failed
	 */
	PollMng findPollMng(int pollIdx) throws Exception;

	PollMng findPollMng2(int pollIdx) throws Exception;

	/**
	 * This updateApplMng method is used to update the pollMng data to database
	 * 
	 * @param pollMng
	 *            pollMng is used to get the pollMng details
	 * @throws Exception
	 *             Exception will be raise, when updateApplMng failed
	 */
	void updatePollMng(PollMng pollMng) throws Exception;

	/**
	 * This removeApplMng method is used to remove the pollMng data from database
	 * 
	 * @param pollMng
	 *            pollMng is used to get the pollMng details
	 * @throws Exception
	 *             Exception will be raise, when removeApplMng failed
	 */
	void removePollMng(PollMng pollMng) throws Exception;

	int getNextPollIdx() throws Exception;

	int getNextSNo(int iPollIdx) throws Exception;

	// Collection findPollPartList(int pollIdx, String partGubun) throws Exception;

	Collection findPollAddfileList(int pollIdx, String fileGubun) throws Exception;

	Collection findQuestMngList(int pollIdx) throws Exception;

	Collection findQuestMng2List(int pollIdx) throws Exception;

	Collection findQuestMng3List(int pollIdx) throws Exception;

	QuestMng findQuestMng(int pollIdx) throws Exception;

	Collection findAnswerMngList(int pollIdx, int questNo) throws Exception;

	Collection findAnswerMng2List(int pollIdx, int questNo, String questType) throws Exception;

	Collection findPanelStateList(int pollIdx) throws Exception;

	void createPollPart(PollPart pollPart) throws Exception;

	void createPollAddfile(PollAddfile pollAddfile) throws Exception;

	void removePollPart(PollPart pollPart) throws Exception;

	void removePollAddfile(PollAddfile pollAddfile) throws Exception;
}
