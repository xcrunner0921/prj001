package seoul.research.poll.service;

import java.util.List;

import anyframe.common.Page;

import seoul.research.domain.PollMng;
import seoul.research.domain.SurveyWeb;


/**
 * This SurveyWebService interface class contains
 * declaration of createSurveyWeb, updateSurveyWeb,
 * removeSurveyWeb, findSurveyWebList,
 * getDropDownSurveyWebList and findSurveyWeb method.
 * 
 * @author Jeryeon Kim
 */
public interface SurveyWebService {

    /**
     * @param surveyWeb
     *        This createSurveyWeb is used to add new
     *        SurveyWeb with
     *        PollIdx,surveyWebName,surveyWebDesc,useYn,regId
     * @return String
     * @throws Exception
     *         Exception will raise, when
     *         createSurveyWeb method failed
     */
    void createSurveyWeb(SurveyWeb surveyWeb) throws Exception;

    /**
     * @param surveyWeb
     *        This updateSurveyWeb method is used to
     *        update the surveyWeb data
     * @throws Exception
     *         Exception will be raise, when
     *         updateSurveyWeb failed
     */
    void updateSurveyWeb(SurveyWeb surveyWeb) throws Exception;

    /**
     * @param surveyWeb
     *        This removeSurveyWeb method is used to
     *        remove the surveyWeb data
     * @throws Exception
     *         Exception will be raise, when
     *         removeSurveyWeb failed
     */
    void removeSurveyWeb(SurveyWeb surveyWeb) throws Exception;

    /**
     * @param searchVO
     *        This findSurveyWebList method is used to
     *        get list of SurveyWeb with
     *        propertiesService Services
     * @return page
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
     * @param PollIdx
     *        This findSurveyWeb method is used to the
     *        SurveyWeb details of specified PollIdx
     * @return SurveyWeb
     * @throws Exception
     *         Exception will raised, when
     *         findSurveyWebList failed
     */
    SurveyWeb findSurveyWeb(int PollIdx) throws Exception;

    /**
     * This getDropDownSurveyWebList method is used to
     * generate list based on surveyWeb id or surveyWeb
     * name.
     * @return List
     * @throws Exception
     *         Exception will raised, when
     *         getDropDownSurveyWebList failed
     */
    List getDropDownSurveyWebList() throws Exception;

    /**
     * This forTransactionTest method is used to test
     * for transaction function
     * @param surveyWeb
     *        This forTransactionTest method is used to
     *        create the surveyWeb data
     * @throws Exception
     *         Exception will raised
     */
    void forTransactionTest(SurveyWeb surveyWeb) throws Exception;
    
    //설문조사 질문타이틀 가져오기
    SurveyWeb findQuestTitle(int pollIdx, int questNo) throws Exception;
    
    //설문조사 타이틀 가져오기 
    SurveyWeb findQuestExitTitle(SurveyWeb surveyWeb) throws Exception;
    
    //스크리닝 타이틀 가져오기
    SurveyWeb findQuestPassTitle(SurveyWeb surveyWeb) throws Exception;
    
  //설문조사 panel 확인 
    SurveyWeb findSurveyUserChk(SurveyWeb surveyWeb) throws Exception;
}
