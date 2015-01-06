package seoul.research.poll.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.admincenter.service.ApplMngService;
import seoul.research.domain.SurveyResult;
import seoul.research.poll.service.SurveyResultSearchVO;
import seoul.research.poll.service.SurveyResultService;
import anyframe.common.Page;

import com.sds.emp.code.service.CodeService;

/**
 * 
 *  
 *  SurveyResultController.java
 * 
 *  
 * -----------------------------------------
 *    
 * -----------------------------------------
 * 2013.09.11.  
 * 
 */
@Controller
@SessionAttributes("surveyResult")
public class SurveyResultController {
	

	private static Log log = LogFactory.getLog(SurveyResultController.class);

	@Resource
	private SurveyResultService surveyResultService = null;

	@Resource
	private CodeService codeService = null;

	@Resource
	private ApplMngService applMngService = null; // 결과보기 첨부파일
	
	/**
	 * get list of SurveyWeb
	 * 여론조사 결과 (홈페이지)
	 * @param SurveyWebSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listSurveyResult.do")
	public ModelAndView list(SurveyResultSearchVO surveyResultSearchVO, HttpServletRequest request) throws Exception {
		
		//log.debug("== SurveyResult SearchRegDatestart  : " + surveyResultSearchVO.getSearchRegDatestart());
		//log.debug("== SurveyResult SearchRegDateend    : " + surveyResultSearchVO.getSearchRegDateend());
		//log.debug("== SurveyResult SearchPollFieldName (분야) : " + surveyResultSearchVO.getSearchPollFieldName());
		//log.debug("== SurveyResult SearchCondition (조건)     : " + surveyResultSearchVO.getSearchCondition());
		//log.debug("== SurveyResult searchPollTitle (검색어)   : " + surveyResultSearchVO.getSearchPollTitle());
		
		HttpSession session = request.getSession();    	
		surveyResultSearchVO.setUserId((String) session.getAttribute("userId"));
		surveyResultSearchVO.setUserId((String) session.getAttribute("userSubject"));		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		
		if (surveyResultSearchVO.getSearchRegDatestart() == null || surveyResultSearchVO.getSearchRegDatestart().equals("")) {
			//조회 시작일은 1년 전부터한다
			int yy = Integer.parseInt(today.substring(0, 4)) - 1 ;
			log.debug("== 검색 시작 년도    : " + Integer.toString(yy) + today.substring(4, 10) );
			surveyResultSearchVO.setSearchRegDatestart(Integer.toString(yy) + today.substring(4, 10));
		}
		if (surveyResultSearchVO.getSearchRegDateend() == null || surveyResultSearchVO.getSearchRegDateend().equals("") ) {
			surveyResultSearchVO.setSearchRegDateend(today);
		}
		
		//log.debug("-- (1) surveyResult    : /listSurveyResult.do ");
		Page resultPage = surveyResultService.findSurveyResultList(surveyResultSearchVO);

		Collection fieldCodeList = codeService.findFieldCodeList(); // 

		ModelAndView mav = new ModelAndView("listSurveyResult");
		mav.addObject("SurveyResultSearchVO", surveyResultSearchVO);
		mav.addObject("resultPage", resultPage);

		mav.addObject("fieldCodeList", fieldCodeList);
		return mav;
	}

	/**
	 * 여론조사 결과 조회 팝업 
	 * 
	 */
	@RequestMapping("/popSurveyResult.do")
	public ModelAndView findSurveyResultPop(@RequestParam("pollIdx") int pollIdx, SurveyResultSearchVO surveyResultSearchVO) throws Exception {

		//log.debug("=====>>  /popSurveyResult.do  Controller  시작 ");
		//log.debug("=====>>  /popSurveyResult.do  Controller  surveyResultSearchVO.getSearchPollIdx() : " + surveyResultSearchVO.getSearchPollIdx());

		Collection pollAddfileList = applMngService.findPollAddfileList(pollIdx); //첨부파일
		Collection pollAddfile2List = applMngService.findPollAddfile2List(pollIdx); //도서관정보
		
		surveyResultSearchVO.setSearchPollIdx(pollIdx);
		SurveyResult surveyResult = surveyResultService.findSurveyResultPop(surveyResultSearchVO);
		ModelAndView mav = new ModelAndView("findSurveyResultPopByPk");
		mav.addObject("surveyResult", surveyResult);
		mav.addObject("surveyResultSearchVO", surveyResultSearchVO);
		mav.addObject("pollAddfileList", pollAddfileList); //첨부파일 
		mav.addObject("pollAddfile2List", pollAddfile2List); //도서관정보 
		return mav;

	}
}
