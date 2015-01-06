package seoul.research.poll.web;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sds.emp.security.service.AuthenticationService;

import anyframe.common.Page;

import seoul.research.domain.MobileResult;
import seoul.research.domain.PollMng;
import seoul.research.poll.service.MobileResultSearchVO;
import seoul.research.poll.service.MobileResultService;

/**
 * controller class for CRUD(create, read, update,
 * delete) a mobileResult. this class apply to prevent
 * double form submit about add, update, delete.
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("mobileResult")
public class MobileResultController {
    
    @Resource
    private MobileResultService mobileResultService = null;

	@Resource
	private AuthenticationService authenticationService = null;


    /**
     * get list of mobileResult
     * @param mobileResultSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/MobileResult.do")
    public ModelAndView get(MobileResultSearchVO mobileResultSearchVO, @RequestParam("pollIdx") int pollIdx)throws Exception {
    	MobileResult mobileResultTitle = mobileResultService.findMobileResult(pollIdx);
    	
    	Collection mobileResultList = mobileResultService.findMobileResultList(mobileResultSearchVO);
    	//mobileResultSearchVO.setPollIdx(pollIdx);
    	
        ModelAndView mav = new ModelAndView("MobileResult");
        mav.addObject("mobileResultTitle", mobileResultTitle);
        mav.addObject("mobileResultList", mobileResultList);
        return mav;
    } 
}






















