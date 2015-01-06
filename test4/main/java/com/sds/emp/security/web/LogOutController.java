package com.sds.emp.security.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import anyframe.web.springmvc.controller.AnyframeFormController;

/**
 * controller class for logout.
 * @author Heewon Jung
 */
@Controller
public class LogOutController extends AnyframeFormController {
    
    @RequestMapping("/logout.do")
    public ModelAndView process(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        session.removeAttribute("role");
        session.removeAttribute("subject");
        session.removeAttribute("userId");
        
        return new ModelAndView("logout");
    }
}
