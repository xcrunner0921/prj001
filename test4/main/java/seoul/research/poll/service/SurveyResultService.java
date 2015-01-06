package seoul.research.poll.service;

import java.util.List;

import seoul.research.domain.SurveyResult;
import anyframe.common.Page;


/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : SurveyResultController.java
 * 설명 : 조사결과 
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.09.11.  하현숙    최초작성  
 * 
 */

public interface SurveyResultService {

	/**
	 * @param searchVO
	 *            This findSurveyWebList method is used to get list of SurveyWeb with propertiesService Services
	 * @return page
	 * @throws Exception
	 *             Exception will raised, when findSurveyWebList failed
	 */
	Page findSurveyResultList(SurveyResultSearchVO searchVO) throws Exception;

	/**
	 * @param SurveyWebNo
	 *            This findSurveyWeb method is used to the SurveyWeb details of specified SurveyWebNo
	 * @return SurveyWeb
	 * @throws Exception
	 *             Exception will raised, when findSurveyWebList failed
	 */
	SurveyResult findSurveyResult(int pollIdx) throws Exception;

	/**
	 * 메인 여론조사 결과 조회
	 * @param surveyResultSearchVO
	 * @return
	 */
	Page findSurveyResultMain(SurveyResultSearchVO surveyResultSearchVO) throws Exception;

	/**
	 * 여론조사 결과 조회-팝업
	 * @param pollIdx
	 * @return
	 * @throws Exception
	 */
	SurveyResult findSurveyResultPop(SurveyResultSearchVO surveyResultSearchVO) throws Exception;
		 
}
