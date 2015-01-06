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

import seoul.research.domain.MileageChk;
import seoul.research.poll.service.MileageChkSearchVO;
import seoul.research.poll.service.MileageChkService;
import seoul.research.poll.service.MileageChkSearchVO;
import seoul.research.poll.service.MyPageJoinSearchVO;

/**
 * controller class for CRUD(create, read, update,
 * delete) a mileageChk. this class apply to prevent
 * double form submit about add, update, delete.
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("mileageChk")
public class MileageChkController {
    
    @Resource
    private MileageChkService mileageChkService = null;

	@Resource
	private AuthenticationService authenticationService = null;


    /**
     * get list of mileageChk
     * @param mileageChkSearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/listMileageChk.do")
    public ModelAndView getCode(MileageChkSearchVO mileageChkSearchVO, @RequestParam("SCHDATE") String schdate, @RequestParam("SCHDATE2") String schdate2)throws Exception {
    	
    	System.out.println("==============schdate=====================" + schdate);
    	System.out.println("==============schdate2=====================" + schdate2);
    	
		mileageChkSearchVO.setSchdate(schdate);
    	mileageChkSearchVO.setSchdate2(schdate2);
    	

    	System.out.println("========mileageChkSearchVO======schdate=====================" + mileageChkSearchVO.getSchdate());
    	System.out.println("========mileageChkSearchVO======schdate2=====================" + mileageChkSearchVO.getSchdate2());
    	
    	
    	
    	Collection mileageChkList = mileageChkService.findMileageChkList(mileageChkSearchVO);
    	
        ModelAndView mav = new ModelAndView("listMileageChk");
        mav.addObject("mileageChkList", mileageChkList);
        return mav;
    } 
}
