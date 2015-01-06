package seoul.research.poll.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.admincenter.service.PopupzoneMngService;
import seoul.research.poll.service.SurveyResultSearchVO;
import seoul.research.poll.service.SurveyResultService;
import anyframe.common.Page;

/**
 *  
 *  MainController.java
 * 
 *  
 * -----------------------------------------
 *    
 * -----------------------------------------
 * 2013.10.17.  
 * 
 */
@Controller
@SessionAttributes("main")
public class MainController {
	
	private static Log log = LogFactory.getLog(MainController.class);

	@Resource
	private SurveyResultService surveyResultService = null;

	@Resource
	private PopupzoneMngService popupzoneMngService = null; //팝업존 
	
	
	/**
	 *  
	 * 메인 (여론조사결과 조회 , 팝업존)
	 * @param 
	 * @param  
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/main.do")
	public ModelAndView get(SurveyResultSearchVO surveyResultSearchVO) throws Exception {

		log.debug("=====>>  /main.do  Controller  시작 ");
		log.debug("=====>>  /main.do  Controller  surveyResultSearchVO..getSearchPollTitle() : " + surveyResultSearchVO.getSearchPollTitle());
		Page resultPage = surveyResultService.findSurveyResultMain(surveyResultSearchVO); //여론조사결과
		Page resultPopup = popupzoneMngService.listMain(); //팝업존  
		ModelAndView mav = new ModelAndView("getSurveyResutlMain");
		mav.addObject("resultPage", resultPage);
		mav.addObject("resultPopup", resultPopup);
		mav.addObject("surveyResultSearchVO", surveyResultSearchVO);
		return mav;

	}
	
}
