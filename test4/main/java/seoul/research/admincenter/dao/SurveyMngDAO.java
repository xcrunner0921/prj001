package seoul.research.admincenter.dao;

import java.util.Collection;

import seoul.research.admincenter.service.SurveyMngSearchVO;
import seoul.research.domain.PollAddfile;
import seoul.research.domain.PollMng;
import seoul.research.domain.PollPart;
import anyframe.common.Page;

/**
 * This DAO interface contains declaration of addSurveyMng, updateSurveyMng, findSurveyMng, findSurveyMngList and
 * getDropDownSurveyMngList methods for different functionality on PollMng.
 * 
 * @author Jeryeon Kim
 */
public interface SurveyMngDAO {

	/**
	 * This findSurveyMngList method is used to get list of PollMng with propertiesService Services from database
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the SurveyMngList
	 * @throws Exception
	 *             Exception will raised, when findSurveyMngList failed
	 */
	Page findPollMngList(SurveyMngSearchVO searchVO) throws Exception;

	/**
	 * This createSurveyMng is used to add new PollMng to the database with surveyMngNo,surveyMngName,surveyMngDesc,useYn,regId
	 * 
	 * @param pollMng
	 *            pollMng parameter is used to get the pollMng details
	 * @throws Exception
	 *             Exception will raise, when createSurveyMng method failed
	 */
	void createPollMng(PollMng pollMng) throws Exception;

	/**
	 * This findSurveyMng method is used to the PollMng details of specified surveyMngNo from database
	 * 
	 * @param surveyMngNo
	 *            surveyMngNo is used to get the details of specified pollMng Number
	 * @return PollMng PollMng is contains pollMng details
	 * @throws Exception
	 *             Exception will raised, when findgetSurveyMng failed
	 */
	PollMng findPollMng(int pollIdx) throws Exception;

	PollMng findPollMng2(int pollIdx) throws Exception;

	/**
	 * This updateSurveyMng method is used to update the pollMng data to database
	 * 
	 * @param pollMng
	 *            pollMng is used to get the pollMng details
	 * @throws Exception
	 *             Exception will be raise, when updateSurveyMng failed
	 */
	void updatePollMng(PollMng pollMng) throws Exception;

	/**
	 * This removeSurveyMng method is used to remove the pollMng data from database
	 * 
	 * @param pollMng
	 *            pollMng is used to get the pollMng details
	 * @throws Exception
	 *             Exception will be raise, when removeSurveyMng failed
	 */
	void removePollMng(PollMng pollMng) throws Exception;

	int getNextPollIdx() throws Exception;

	int getNextSNo(int iPollIdx) throws Exception;

	Collection findPollPartList(int pollIdx, String partGubun) throws Exception;

	Collection findPollAddfileList(int pollIdx, String fileGubun) throws Exception;

	void createPollPart(PollPart pollPart) throws Exception;

	void createPollAddfile(PollAddfile pollAddfile) throws Exception;

	void removePollPart(PollPart pollPart) throws Exception;

	void removePollAddfile(PollAddfile pollAddfile) throws Exception;
}
