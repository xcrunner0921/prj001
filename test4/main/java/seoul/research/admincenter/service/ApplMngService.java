package seoul.research.admincenter.service;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import anyframe.common.Page;

/**
 * This ApplMngService interface class contains declaration of createApplMng, updateApplMng, removeApplMng, findApplMngList,
 * getDropDownApplMngList and findApplMng method.
 * 
 * @author Jeryeon Kim
 */
public interface ApplMngService {

	/**
	 * @param searchVO
	 *            This findApplMngList method is used to get list of PollMng with propertiesService Services
	 * @return page
	 * @throws Exception
	 *             Exception will raised, when findApplMngList failed
	 */
	Page findApplMngList(ApplMngSearchVO searchVO) throws Exception;

	/**
	 * @param pollMng
	 *            This createApplMng is used to add new PollMng with applMngNo,applMngName,applMngDesc,useYn,regId
	 * @return String
	 * @throws Exception
	 *             Exception will raise, when createApplMng method failed
	 */
	void createApplMng(PollMng pollMng) throws Exception;

	/**
	 * @param applMngNo
	 *            This findApplMng method is used to the PollMng details of specified applMngNo
	 * @return PollMng
	 * @throws Exception
	 *             Exception will raised, when findApplMngList failed
	 */
	PollMng findApplMng(int pollIdx) throws Exception;

	PollMng findApplMng2(int pollIdx) throws Exception;

	/**
	 * @param pollMng
	 *            This updateApplMng method is used to update the pollMng data
	 * @throws Exception
	 *             Exception will be raise, when updateApplMng failed
	 */
	void updateApplMng(PollMng pollMng) throws Exception;

	/**
	 * @param pollMng
	 *            This removeApplMng method is used to remove the pollMng data
	 * @throws Exception
	 *             Exception will be raise, when removeApplMng failed
	 */
	void removeApplMng(PollMng pollMng) throws Exception;

	// Collection findPollPartMfList(int pollIdx) throws Exception;

	// Collection findPollPartAgeList(int pollIdx) throws Exception;

	// Collection findPollPartRegionList(int pollIdx) throws Exception;

	Collection findPollAddfileList(int pollIdx) throws Exception;

	Collection findPollAddfile2List(int pollIdx) throws Exception;

	Collection findQuestMngList(int pollIdx) throws Exception;

	Collection findQuestMng2List(int pollIdx) throws Exception;

	Collection findQuestMng3List(int pollIdx) throws Exception;

	QuestMng findQuestMng(int pollIdx) throws Exception;

	Collection findPanelStateList(int pollIdx) throws Exception;

	// void createPollPartMf(PollMng pollMng) throws Exception;

	// void createPollPartAge(PollMng pollMng) throws Exception;

	// void createPollPartRegion(PollMng pollMng) throws Exception;

	void createPollAddfile(PollMng pollMng, MultipartFile multipartFile) throws Exception;

	void createPollAddfile(PollMng pollMng) throws Exception;

	void createPollAddfile2(PollMng pollMng) throws Exception;

	// void removePollPart(PollMng pollMng) throws Exception;

	void removePollAddfile(PollMng pollMng) throws Exception;

	// void removePollAddfile2(PollMng pollMng) throws Exception;
}
