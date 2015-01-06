package seoul.research.admincenter.service;

import anyframe.common.Page;

/**
 * This PollMngService interface class contains declaration of createPollMng, updatePollMng, removePollMng, findPollMngList,
 * getDropDownPollMngList and findPollMng method.
 * 
 * @author Jeryeon Kim
 */
public interface AnswerMngService {

	/**
	 * @param searchVO
	 *            This findPollMngList method is used to get list of PollMng with propertiesService Services
	 * @return page
	 * @throws Exception
	 *             Exception will raised, when findPollMngList failed
	 */
	Page findAnswerMngList(AnswerMngSearchVO searchVO) throws Exception;
}
