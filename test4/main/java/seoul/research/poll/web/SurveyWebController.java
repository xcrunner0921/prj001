package seoul.research.poll.web;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sds.emp.security.service.AuthenticationService;

import anyframe.common.Page;
import anyframe.common.exception.BaseException;

import seoul.research.domain.SurveyWeb;
import seoul.research.poll.service.SurveyWebSearchVO;
import seoul.research.poll.service.SurveyWebService;

/**
 * controller class for CRUD(create, read, update,
 * delete) a surveyWeb. this class apply to prevent
 * double form submit about add, update, delete.
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("surveyWeb")
public class SurveyWebController {
    
    @Resource
    private SurveyWebService surveyWebService = null;

	@Resource
	private AuthenticationService authenticationService = null;
	
	@Resource
	private MessageSource messageSource;
	
	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;
	
	public TransactionStatus getTxStatus() {
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return txManager.getTransaction(txDefinition);
	}
	
    /**
     * display add surveyWeb view
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSurveyWebView.do")
    public ModelAndView addView() throws Exception {
        ModelAndView mnv = new ModelAndView("addSurveyWebView", "surveyWeb", new SurveyWeb());
        return mnv;
    }

    /**
     * add surveyWeb
     * @param surveyWeb
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSurveyWebPop.do")
    public ModelAndView addSurveyWebPop(SurveyWeb surveyWeb, SessionStatus status, HttpServletRequest request) throws Exception {
	    	
    	TransactionStatus txStatus = getTxStatus();
		ModelAndView mav = null;
		
		try {
    		HttpSession session = request.getSession();    	
	    	String panelId 			= (String) session.getAttribute("userId");
	    	String mf 				= (String) session.getAttribute("userMf");
	    	String region 			= (String) session.getAttribute("userSido");
	    	if((panelId.toUpperCase()).equals("ADMIN")){
	    		mf = "AM";
	    		region = "AM";
	    	}
	    	String userMf	= "";
	    	if((mf.equals("1")) || (mf.equals("3")) || (mf.equals("5")) || (mf.equals("7")) || (mf.equals("9"))){
	    		userMf = "남";
	    	}else if((mf.equals("0")) || (mf.equals("2")) || (mf.equals("4")) || (mf.equals("6")) || (mf.equals("8"))){
	    		userMf = "여";
	    	}else{
	    		userMf = "기타";
	    	}
	    	surveyWeb.setPanelId(panelId);
	    	surveyWeb.setMf(userMf);
	    	surveyWeb.setRegion(region);
	    	
	    	String questNo = Integer.toString(surveyWeb.getQuestNo());
	    	String questCount = Integer.toString(surveyWeb.getQuestCount());
	    	String filterGubun = surveyWeb.getFilterGubun();//답변에서 필터됨
			
			String SNoAnswer = surveyWeb.getSNoAnswer();
			
			SNoAnswer = SNoAnswer.replace(',', ' ');
			SNoAnswer = SNoAnswer.replaceAll("\\p{Space}", "");
	
			if(!"0".equals(SNoAnswer)){
				surveyWeb.setAnswer(SNoAnswer);
			}
			if(questNo.equals(questCount)){
				surveyWeb.setNextQuestNo(0);
				surveyWeb.setProgYn("Y");
				surveyWeb.setFilterYn("Y");
			}
			if("N".equals(filterGubun)){
				surveyWeb.setProgYn("Y");
	        	surveyWeb.setFilterYn("N");
				surveyWeb.setNextQuestNo(0);
			}else{
				surveyWeb.setFilterYn("Y");
			}

			SurveyWeb surveyUserChk = surveyWebService.findSurveyUserChk(surveyWeb);
			System.out.println("=======>>>  Controller PanelCount surveyWeb : " + surveyUserChk.getPanelCount());
			System.out.println("=======>>>  panelId : " +  panelId);
			
			//관리자인 경우 테스트를 하기때문에    panel table에 아이디가 없어도 등록을 해야함. count를 1 로 설정함.  
			if((panelId.toUpperCase()).equals("ADMIN")){				
				surveyUserChk.setPanelCount("1");
			}
			
			if("1".equals(surveyUserChk.getPanelCount())){
				surveyWebService.createSurveyWeb(surveyWeb);
				
		        if("N".equals(filterGubun)){
		        	mav = new ModelAndView("popupTemplatePass", "surveyWeb", surveyWeb);
		        }else{
		        	if(questNo.equals(questCount)){
		        		mav = new ModelAndView("popupTemplateE", "surveyWeb", surveyWeb);
					}else{
						mav = new ModelAndView("addQuestViewAction", "surveyWeb", surveyWeb);
					}
		        }
			}else{
				throw new BaseException(messageSource, "error.surveywebcontroller.panel");
			}
			
	    	txManager.commit(txStatus);
		}catch (Exception ex) {
			txManager.rollback(txStatus);
			throw new BaseException(messageSource, "error.surveywebcontroller.add");
		}
		return mav;
    }
    
    /**
     * get list of surveyWeb
     * @param surveyWebSearchVO
     * @return
     * @throws Exception
     */
    
    @RequestMapping("/addQuestView.do")
    public ModelAndView addQuestView(SurveyWebSearchVO surveyWebSearchVO, @RequestParam("pollIdx") int pollIdx, @RequestParam("questNo") int questNo, @RequestParam("nextQuestNo") int nextQuestNo) throws Exception {
    	//System.out.println("pollIdx : " + pollIdx);
    	//System.out.println("questNo : " + questNo);
    	//System.out.println("nextQuestNo : " + nextQuestNo);
    	surveyWebSearchVO.setPollIdx(pollIdx);
    	
    	Page resultPage = surveyWebService.findSurveyQuestList(surveyWebSearchVO);
		ModelAndView mav = new ModelAndView("listSurveyQuest");//저장후 설문조사 읽어오기 재실행
		mav.addObject("SurveyWebSearchVO", surveyWebSearchVO);
		mav.addObject("resultPage", resultPage);
		return mav;
    }

    /**
     * get detail of surveyWeb
     * @param PollIdx
     * @param surveyWebSearchVO
     * @return
     * @throws Exception
     */

    @RequestMapping("/getSurveyWeb.do")
    public ModelAndView getSurveyWeb(@RequestParam("pollIdx") int pollIdx, SurveyWebSearchVO surveyWebSearchVO) throws Exception {
        SurveyWeb surveyWeb = surveyWebService.findSurveyWeb(pollIdx);
        ModelAndView mav = new ModelAndView("getSurveyWeb");
        mav.addObject("surveyWeb", surveyWeb);
        mav.addObject("surveyWebSearchVO", surveyWebSearchVO);
        return mav;

    }

    /**
     * update surveyWeb
     * @param session
     * @param surveyWeb
     * @param status
     * @return
     * @throws Exception
     */
/*
    @RequestMapping("/updateSurveyWeb.do")
    public ModelAndView update(HttpSession session, SurveyWeb surveyWeb, SessionStatus status) throws Exception {
        String modifyId = (String) session.getAttribute("userId");
        surveyWeb.setModifyId(modifyId);
        surveyWebService.updateSurveyWeb(surveyWeb);
        status.setComplete();
        return new ModelAndView("listSurveyWebAction");
    }
*/
    /**
     * get list of surveyWeb
     * @param surveyWebSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/listSurveyWeb.do")
    public ModelAndView listSurveyWeb(SurveyWebSearchVO surveyWebSearchVO, HttpServletRequest request) throws Exception {
    	
    	HttpSession session = request.getSession();  
    	String userId = (String) session.getAttribute("userId");
    	
    	System.out.println("");
    	
    	surveyWebSearchVO.setUserId(userId);
        Page resultPage = surveyWebService.findSurveyWebList(surveyWebSearchVO);
        ModelAndView mav = new ModelAndView("listSurveyWeb");
        mav.addObject("resultPage", resultPage);
        return mav;
    }
    
    /**
     * get list of surveyWeb
     * @param surveyWebSearchVO
     * @return
     * @throws Exception
     * @RequestMapping("/getSurveyWeb.do")
    public ModelAndView getSurveyWeb(@RequestParam("pollIdx") int pollIdx, SurveyWebSearchVO surveyWebSearchVO) throws Exception {
        SurveyWeb surveyWeb = surveyWebService.findSurveyWeb(pollIdx);
        ModelAndView mav = new ModelAndView("getSurveyWeb");
        mav.addObject("surveyWeb", surveyWeb);
        mav.addObject("surveyWebSearchVO", surveyWebSearchVO);
        return mav;

    }
     */
    /* List방식 수정예정 */
    @RequestMapping("/getSurveyQuest.do")
    public ModelAndView getSurveyQuest(SurveyWebSearchVO surveyWebSearchVO, @RequestParam("pollIdx") int pollIdx, @RequestParam("questNo") int questNo, @RequestParam("nextQuestNo") int nextQuestNo) throws Exception {
    	//System.out.println("pollIdx : " + pollIdx);
    	//System.out.println("questNo : " + questNo);
    	//System.out.println("nextQuestNo : " + nextQuestNo);
    	surveyWebSearchVO.setPollIdx(pollIdx);
    	
    	if("0".equals(nextQuestNo)){
    		surveyWebSearchVO.setQuestNo(questNo);
    	}else{
    		surveyWebSearchVO.setQuestNo(nextQuestNo);
    		questNo = nextQuestNo;
    	}
    	
    	SurveyWeb surveyWeb = surveyWebService.findSurveyWeb(pollIdx);
    	
    	//설문조사 질문타이틀 가져오기
    	SurveyWeb questTitle = surveyWebService.findQuestTitle(pollIdx, questNo);
    	String ExamRotateyn = questTitle.getExamRotateyn();
    	if(ExamRotateyn.equals("Y")){
    		surveyWebSearchVO.setExamRotateyn(ExamRotateyn);
    	}else{
    		surveyWebSearchVO.setExamRotateyn(null);
    	}
    	
    	
    	//설문조사 보기리스트 가져오기
    	Page resultPage = surveyWebService.findSurveyQuestList(surveyWebSearchVO);
    	
		ModelAndView mav = new ModelAndView("listSurveyQuest");	//설문조사 출제 호출(호출 -> 저장 반복로직)
		mav.addObject("SurveyWebSearchVO", surveyWebSearchVO);
		
		mav.addObject("questTitle", questTitle);
		mav.addObject("surveyWeb", surveyWeb);
		mav.addObject("resultPage", resultPage);
		return mav;
    }

    /**
     * delete surveyWeb
     * @param surveyWeb
     * @param surveyWebSearchVO
     * @return
     * @throws Exception
     */
/*	필요시 주석해제 PJH
    @RequestMapping("/deleteSurveyWeb.do")
    public ModelAndView delete(@RequestParam("PollIdx")
    String PollIdx, SurveyWebSearchVO surveyWebSearchVO) throws Exception {
        SurveyWeb surveyWeb = new SurveyWeb();
        surveyWeb.setPollIdx(PollIdx);
        surveyWebService.removeSurveyWeb(surveyWeb);
        
        return new ModelAndView("listSurveyWebAction", "surveyWebSearchVO", surveyWebSearchVO);
    }
*/
    
    /** end page view **/
    
    /**
     * display add surveyWeb view
     * @return
     * @throws Exception
     */
    @RequestMapping("/popupTemplateE.do")
    public ModelAndView popupTemplateE(SurveyWeb surveyWeb) throws Exception {
    	SurveyWeb questExitTitle = surveyWebService.findQuestExitTitle(surveyWeb);
    	ModelAndView mav = new ModelAndView("getSurveyWebEnd");
    	mav.addObject("questExitTitle", questExitTitle);
        return mav;
    }
    
    @RequestMapping("/popupTemplatePass.do")
    public ModelAndView popupTemplatePass(SurveyWeb surveyWeb, HttpServletRequest request) throws Exception {
    	SurveyWeb questPassTitle = surveyWebService.findQuestPassTitle(surveyWeb);
    	ModelAndView mav = new ModelAndView("getSurveyWebPass");
    	mav.addObject("questPassTitle", questPassTitle);
        return mav;
    }
}
