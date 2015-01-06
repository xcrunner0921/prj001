package com.sds.emp.common.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * interceptor for search login user
 * 
 * @author Heewon Jung
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (request.getSession().getAttribute("userId") == null) {
			if (request.getRequestURI().indexOf("/login.do") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("SurveyWeb") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("popupTemplate") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("MileageChk") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("/getPanel.do") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("/panelDelflag.do") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("SurveyResult") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("/main.do") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("MyPage") != -1) {
				return true;
			}
			else if (request.getRequestURI().indexOf("Mobile") != -1) {
				return true;
			}
			else {
				response.sendRedirect("/admincenter/login.jsp");
				return false;
			}
		}
		else
			return true;
	}

}
