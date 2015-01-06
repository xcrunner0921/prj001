package seoul.research.poll.dao;

import java.util.List;

import anyframe.common.Page;

import seoul.research.domain.PollMng;
import seoul.research.domain.SurveyWeb;
import seoul.research.poll.service.SurveyWebSearchVO;

/**
 * This DAO interface contains declaration of
 * addSurveyWeb, updateSurveyWeb, findSurveyWeb,
 * findSurveyWebList and getDropDownSurveyWebList methods
 * for different functionality on SurveyWeb.
 * 
 * @author Jeryeon Kim
 */
public interface SurveyWebDAO {

    /**
     * This createSurveyWeb is used to add new SurveyWeb
     * to the database with
     * surveyWebNo,surveyWebName,surveyWebDesc,useYn,regId
     * @param surveyWeb
     *        surveyWeb parameter is used to get the
     *        surveyWeb details
     * @throws Exception
     *         Exception will raise, when
     *         createSurveyWeb method failed
     */
    void createSurveyWeb(SurveyWeb surveyWeb) throws Exception;

    /**
     * This updateSurveyWeb method is used to update the
     * surveyWeb data to database
     * @param surveyWeb
     *        surveyWeb is used to get the surveyWeb
     *        details
     * @throws Exception
     *         Exception will be raise, when
     *         updateSurveyWeb failed
     */
    void updateSurveyWeb(SurveyWeb surveyWeb) throws Exception;

    /**
     * This removeSurveyWeb method is used to remove the
     * surveyWeb data from database
     * @param surveyWeb
     *        surveyWeb is used to get the surveyWeb
     *        details
     * @throws Exception
     *         Exception will be raise, when
     *         removeSurveyWeb failed
     */
    void removeSurveyWeb(SurveyWeb surveyWeb) throws Exception;

    /**
     * This findSurveyWebList method is used to get list
     * of SurveyWeb with propertiesService Services from
     * database
     * @param searchVO
     *        searchVO is used to get the details
     * @return page page contains the SurveyWebList
     * @throws Exception
     *         Exception will raised, when
     *         findSurveyWebList failed
     */
    Page findSurveyWebList(SurveyWebSearchVO searchVO) throws Exception;
    
    Page findSurveyQuestList(SurveyWebSearchVO searchVO) throws Exception;
/*    
    Page addQuestNextView(SurveyWebSearchVO searchVO) throws Exception;
*/   
    /**
     * This findSurveyWeb method is used to the SurveyWeb
     * details of specified surveyWebNo from database
     * @param surveyWebNo
     *        surveyWebNo is used to get the details of
     *        specified surveyWeb Number
     * @return SurveyWeb SurveyWeb is contains surveyWeb
     *         details
     * @throws Exception
     *         Exception will raised, when
     *         findgetSurveyWeb failed
     */
    SurveyWeb findSurveyWeb(int pollIdx) throws Exception;

    /**
     * This getDropDownSurveyWebList method is used to
     * generate list based on surveyWeb id or surveyWeb
     * name from database
     * @return List Drop down list of surveyWeb values
     * @throws Exception
     *         Exception will raised, when
     *         getDropDownSurveyWebList failed
     */
    List getDropDownSurveyWebList() throws Exception;
    
    //설문조사 질문타이틀 가져오기
    SurveyWeb findQuestTitle(int pollIdx, int questNo) throws Exception;
    
    //설문조사 타이틀 가져오기 
    SurveyWeb findQuestExitTitle(SurveyWeb surveyWeb) throws Exception;
    
    //스크리닝 타이틀 가져오기 
    SurveyWeb findQuestPassTitle(SurveyWeb surveyWeb) throws Exception;
    
	//설문조사 panel 확인 
    SurveyWeb findSurveyUserChk(SurveyWeb surveyWeb) throws Exception;
}
