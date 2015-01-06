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

import seoul.research.domain.MyPageJoin;
import seoul.research.poll.service.MyPageJoinSearchVO;
import seoul.research.poll.service.MyPageJoinService;
import seoul.research.poll.service.MyPageJoinSearchVO;

/**
 * controller class for CRUD(create, read, update,
 * delete) a myPageJoin. this class apply to prevent
 * double form submit about add, update, delete.
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("myPageJoin")
public class MyPageJoinController {
    
    @Resource
    private MyPageJoinService myPageJoinService = null;

	@Resource
	private AuthenticationService authenticationService = null;


    /**
     * get list of myPageJoin
     * @param myPageJoinSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/listMyPageJoin.do")
    public ModelAndView list(MyPageJoinSearchVO myPageJoinSearchVO, HttpServletRequest request) throws Exception {
    	
    	HttpSession session = request.getSession();  
    	String userId = (String) session.getAttribute("userId");
    	
    	System.out.println("@@@ myPage userId : " + userId);
    	
    	myPageJoinSearchVO.setUserId(userId);
        Page resultPage = myPageJoinService.findMyPageJoinList(myPageJoinSearchVO);
        ModelAndView mav = new ModelAndView("listMyPageJoin");
        mav.addObject("resultPage", resultPage);
        return mav;
    } 
}
