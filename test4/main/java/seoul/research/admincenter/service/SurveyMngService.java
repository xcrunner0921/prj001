package seoul.research.admincenter.service;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import seoul.research.domain.PollMng;
import anyframe.common.Page;

/**
 * This SurveyMngService interface class contains declaration of createSurveyMng, updateSurveyMng, removeSurveyMng,
 * findSurveyMngList, getDropDownSurveyMngList and findSurveyMng method.
 * 
 * @author Jeryeon Kim
 */
public interface SurveyMngService {

	/**
	 * @param searchVO
	 *            This findSurveyMngList method is used to get list of PollMng with propertiesService Services
	 * @return page
	 * @throws Exception
	 *             Exception will raised, when findSurveyMngList failed
	 */
	Page findSurveyMngList(SurveyMngSearchVO searchVO) throws Exception;

	/**
	 * @param pollMng
	 *            This createSurveyMng is used to add new PollMng with surveyMngNo,surveyMngName,surveyMngDesc,useYn,regId
	 * @return String
	 * @throws Exception
	 *             Exception will raise, when createSurveyMng method failed
	 */
	void createSurveyMng(PollMng pollMng) throws Exception;

	/**
	 * @param surveyMngNo
	 *            This findSurveyMng method is used to the PollMng details of specified surveyMngNo
	 * @return PollMng
	 * @throws Exception
	 *             Exception will raised, when findSurveyMngList failed
	 */
	PollMng findSurveyMng(int pollIdx) throws Exception;

	PollMng findSurveyMng2(int pollIdx) throws Exception;

	/**
	 * @param pollMng
	 *            This updateSurveyMng method is used to update the pollMng data
	 * @throws Exception
	 *             Exception will be raise, when updateSurveyMng failed
	 */
	void updateSurveyMng(PollMng pollMng) throws Exception;

	/**
	 * @param pollMng
	 *            This removeSurveyMng method is used to remove the pollMng data
	 * @throws Exception
	 *             Exception will be raise, when removeSurveyMng failed
	 */
	void removeSurveyMng(PollMng pollMng) throws Exception;

	Collection findPollPartMfList(int pollIdx) throws Exception;

	Collection findPollPartAgeList(int pollIdx) throws Exception;

	Collection findPollPartRegionList(int pollIdx) throws Exception;

	Collection findPollAddfileList(int pollIdx) throws Exception;

	void createPollPartMf(PollMng pollMng) throws Exception;

	void createPollPartAge(PollMng pollMng) throws Exception;

	void createPollPartRegion(PollMng pollMng) throws Exception;

	void createPollAddfile(PollMng pollMng, MultipartFile multipartFile) throws Exception;

	void createPollAddfile(PollMng pollMng) throws Exception;

	void removePollPart(PollMng pollMng) throws Exception;

	void removePollAddfile(PollMng pollMng) throws Exception;
}
