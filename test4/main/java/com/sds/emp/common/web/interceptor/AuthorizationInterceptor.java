package com.sds.emp.common.web.interceptor;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * interceptor for authorization check.
 * @author Heewon Jung
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private Properties permissions = null;

    private String defaultPermission = null;
    
    /*
     * well-known "resolvable dependencies"
     */
    @Resource
    private MessageSource messageSource = null;

    public void setPermissions(Properties permissions) {
        this.permissions = permissions;
    }

    public void setDefaultPermission(String defaultPermission) {
        this.defaultPermission = defaultPermission;
    }

    /**
     * override preHandle() method.
     */
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        String requestPath = request.getServletPath();

        // find request path's role list.
        String roleList = ((String) permissions.get(requestPath));
        if (roleList == null) {
            roleList = defaultPermission;
        }
        // find a role of current user
        String currentUserRole =
            (String) request.getSession().getAttribute("role");

        // when current user's role is null
        if (request.getSession().getAttribute("role") == null) {
            response.sendRedirect("login.jsp");
            return false;
        }
        // when role list is null
        if (roleList.equals("*")) {
            return true;
        }

        String[] roles = roleList.split(",");

        // compare role
        for (int i = 0; i < roles.length; i++) {
            if (roles[i].trim().equals(currentUserRole)) {
                return true;
            }
        }

        // throw exception
        throw new Exception(messageSource.getMessage(
            "error.security.check.role", new String[] {}, Locale.getDefault()));
    }
}
