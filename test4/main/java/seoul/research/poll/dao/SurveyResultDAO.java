package seoul.research.poll.dao;

import java.util.List;

import seoul.research.poll.service.SurveyResultSearchVO;
import seoul.research.domain.SurveyResult;
import anyframe.common.Page;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : SurveyResultDAO.java
 * 설명 : 조사결과 
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.09.11.  하현숙    최초작성  
 * 
 */

public interface SurveyResultDAO {

	/**
	 * This findSurveyWebList method is used to get list of SurveyWeb with propertiesService Services from database
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the SurveyWebList
	 * @throws Exception
	 *             Exception will raised, when findSurveyWebList failed
	 */
	Page findSurveyResultList(SurveyResultSearchVO searchVO) throws Exception;

	/**
	 * This findSurveyWeb method is used to the SurveyWeb details of specified SurveyWebNo from database
	 * 
	 * @param SurveyWebNo
	 *            SurveyWebNo is used to get the details of specified SurveyWeb Number
	 * @return SurveyWeb SurveyWeb is contains SurveyWeb details
	 * @throws Exception
	 *             Exception will raised, when findgetSurveyWeb failed
	 */
	SurveyResult findSurveyResult(int pollIdx) throws Exception;

	/**
	 * This getDropDownSurveyWebList method is used to generate list based on SurveyWeb id or SurveyWeb name from database
	 * 
	 * @return List Drop down list of SurveyWeb values
	 * @throws Exception
	 *             Exception will raised, when getDropDownSurveyWebList failed
	 */
	List getDropDownSurveyResultList() throws Exception;

	int getNextPollIdx() throws Exception;
	
	
	/**
	 * 메인 여론조사 결과 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	Page findSurveyResultMain(SurveyResultSearchVO searchVO) throws Exception;
	
	/**
	 * 여론조사 결과조회 팝업 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	SurveyResult findSurveyResultPop(SurveyResultSearchVO searchVO) throws Exception;
}
