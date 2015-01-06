package seoul.research.poll.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.domain.Panel;
import seoul.research.poll.service.PanelService;

/**
 *  
 *  : PanelController.java
 *  
 * -----------------------------------------
 *    
 * -----------------------------------------
 * 2013.09.18.   
 * 
 */
@Controller
@SessionAttributes("panel")
public class PanelController {

	private static Log log = LogFactory.getLog(PanelController.class);
	
	@Resource
	private PanelService panelService = null;
	protected HttpSession session = null;

	/**
	 *  
	 * 
	 * @param 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPanel.do")
	public ModelAndView getPanelInfo(HttpSession session, @RequestParam("userId") String userId) throws Exception {
		log.info("===>>>  (1)  :   getPanelInfo : " + userId);
	
		Panel panel = panelService.getPanelInfo(userId); 

		try{
			session.setAttribute("userId", panel.getPnlId());
			session.setAttribute("userNm", panel.getPnlName());
			session.setAttribute("userSido", panel.getSido());
			session.setAttribute("userMf", panel.getMf());
			session.setAttribute("userDelFlag", panel.getDelflag());
			session.setAttribute("userBirthDay", panel.getBirthday());
	
			log.info("===>>>  panelController  userId :  " + panel.getPnlId());
		
		}catch (Exception ex){
			log.info("===>>>  panelController (2)  Exception :  " + ex.toString());
		}
		
		ModelAndView mav = new ModelAndView("findgetPanelInfoByPk");
		mav.addObject("panel", panel);
		return mav;
	}


    
    /**
     * 패널가입.탈퇴
     * @param 
     * @return
     * @throws Exception
     */
    @RequestMapping("/panelDelflag.do")
    public ModelAndView update(HttpSession session,  @RequestParam("delflag") String delflag, @RequestParam("age") String age, @RequestParam("ageGroup") String ageGroup) throws Exception {
    	
    	Panel panel = new Panel();
    	
    	log.info("===>>   PanelController (85) delflag : " + delflag);
    	String userId = (String)session.getAttribute("userId");
    	String userNm = (String)session.getAttribute("userNm");
    	String userMf = (String)session.getAttribute("userMf"); //성별
    	String userSido = (String)session.getAttribute("userSido"); //시도
    	
    	//성별 
    	if(userMf.equals("1") || userMf.equals("3") || userMf.equals("5") || userMf.equals("7") || userMf.equals("9")){
    		userMf = "남";
    	}else if(userMf.equals("0") || userMf.equals("2") || userMf.equals("4") || userMf.equals("6") || userMf.equals("8")){
    		userMf = "여";
    	}else{
    		userMf = "기타";
    	}

    	log.info("===>>   PanelController (100) userSido : " + userSido);
    	//지역    	
    	if(!"서울".equals(userSido) && !"경기".equals(userSido) && !"인천".equals(userSido) ){
    		userSido = "기타";
    	}
    	log.info("===>>   PanelController (105) userSido : " + userSido);
    	//회원통합정보
    	panel.setAge(age);
    	panel.setAgeGroup(ageGroup);
    	panel.setSido(userSido);
    	panel.setMf(userMf);
    	
    	
    	//1  : delfalg=N  --->  delfalg=Y   
    	if(delflag.equals("Y")){	    	  
	    	panel.setPnlId(userId);	  
	    	panel.setDelflag(delflag);
	    	
	    	panelService.updatePanelDelflagN(panel); 
	    	
	    //2) : delfalg=N  --->   panel  
    	}else if(delflag.equals("N")){
    		int cnt = panelService.getPanelCount(userId); 
	    	log.info("===>>    cnt : " + cnt);
    		
	    	//(1)   delfalg=N  
	    	if(cnt == 1){	    	  
		    	panel.setPnlId(userId);	  
		    	panel.setDelflag(delflag);
		    	
		    	panelService.updatePanelDelflagY(panel); 
	    		
	    	//(2)   insert
	    	}else{	  
		    	panel.setPnlId(userId);	  
		    	panel.setPnlName(userNm);
		    	//panel.setBirthday((String)session.getAttribute("userBirthDay"));
		    	//panel.setMf((String)session.getAttribute("userMf"));
		    	//panel.setEmail((String)session.getAttribute("userEmail"));
		    	//panel.setSido((String)session.getAttribute("userSido"));
		    	//panel.setAgeGroup(ageGroup);
		    	
		    	panelService.createPanel(panel); //패널 정보 등록 
	    	}
	    	

	    	//회원정보확인 
    		int cntTbmem001 = panelService.getTbmem001Count(userId); 
	    	//회원통합정보
	    	if(cntTbmem001==0){
	    		log.debug("==>> age :" + age);
	    		log.debug("==>> ageGroup : " + ageGroup);
	    		log.debug("==>> userSido : " + userSido);
	    		log.debug("==>> userMf : " + userMf);

	    		panelService.createTbmem001(panel);//회원정보 등록 2013.10.10 추가
	    	}
    		
    	}
        ModelAndView mav = new ModelAndView("getPanelCount");
        return mav;
    } 
    
}
