package com.sds.emp.security.web;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.sds.emp.security.service.AuthenticationService;
import com.sds.emp.security.service.Credential;
import com.tagish.auth.TypedPrincipal;

/**
 * controller class for login.
 * 
 * @author Heewon Jung
 */
@Controller
public class LogInController {
	@Resource
	private AuthenticationService authenticationService = null;

	@Resource
	private LocaleResolver localeResolver = null;

	@RequestMapping("/login.do")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Subject subject = new Subject();
		HttpSession session = request.getSession();

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		Credential c = new Credential();
		c.setProperty("userid", userId);
		c.setProperty("password", password);

		subject = authenticationService.authenticate(c);

		String currentUserRole = "";

		// let's see what Principals we have
		Iterator principalIterator = subject.getPrincipals().iterator();

		// search login user's role.
		while (principalIterator.hasNext()) {
			TypedPrincipal principal = (TypedPrincipal) principalIterator.next();

			if (principal.getType() == TypedPrincipal.GROUP)
				currentUserRole = principal.getName();
		}

		session.setAttribute("role", currentUserRole);
		session.setAttribute("subject", subject);
		session.setAttribute("userId", userId);

		if (session.getAttribute("AfterAuthentication") != null) {
			String url = session.getAttribute("AfterAuthentication").toString();
			session.removeAttribute("AfterAuthentication");
			response.sendRedirect(url);

			return null;
		}
		else {
			if (currentUserRole.equals("GG101") || currentUserRole.equals("GG102")) {
				return new ModelAndView("listSurveyMngAction");
			}
			else {
				return new ModelAndView("listApplMngAction");
			}
		}
	}
}
