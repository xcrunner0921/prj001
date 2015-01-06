package seoul.research.admincenter.dao;

import seoul.research.admincenter.service.AnswerMngSearchVO;
import anyframe.common.Page;

/**
 * This DAO interface contains declaration of addPollMng, updatePollMng, findPollMng, findPollMngList and getDropDownPollMngList
 * methods for different functionality on PollMng.
 * 
 * @author Jeryeon Kim
 */
public interface AnswerMngDAO {

	/**
	 * This findPollMngList method is used to get list of PollMng with propertiesService Services from database
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the PollMngList
	 * @throws Exception
	 *             Exception will raised, when findPollMngList failed
	 */
	Page findAnswerMngList(AnswerMngSearchVO searchVO) throws Exception;
}
