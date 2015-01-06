package com.sds.emp.security.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.sds.emp.common.AuthenticationException;
import com.sds.emp.common.EmpException;
import com.sds.emp.security.service.AuthenticationService;
import com.sds.emp.security.service.Credential;
import com.tagish.auth.TypedPrincipal;

/**
 * DB에 저장된 정보를 이용하여 사용자 인증 기능을 제공하는 서비스이다.
 * 
 * @author Jeryeon Kim
 */
@Service("authenticationService")
public class DBAuthenticationService implements AuthenticationService {

	@Resource
	protected DataSource dataSource = null;

	// private String sqlQuery =
	// "SELECT u.USER_ID,u.USER_NAME,u.PASSWORD,u.AUTHORITY FROM USERS u WHERE u.USER_ID=? and u.PASSWORD=?";
	private String sqlQuery = "SELECT USER_ID, USER_NAME, USER_PW, USER_LEVEL FROM USER_GOV WHERE USER_ID = ? AND USER_PW = ?";

	// well-known "resolvable dependencies" using @Resource
	@Resource
	private MessageSource messageSource = null;

	/**
	 * Use this method to return a populated Subject. It is populated with the principals which represent the identity of the
	 * user as well as any other principal for which permissions may be associated. For instance, if the configured Realm
	 * implementation has <code>GroupSupport</code> then the Authentication Manager may choose to add a Principal for each Group
	 * the user is a member of. Additional Principals may be added to represent security related attributes that may be
	 * reflected in Policy definition, for Realms with <code>AttributeSupport</code>.
	 * 
	 * @param credential
	 *            a collection of credential objects used provided as proof of identity
	 * @return a Subject populated with appropriate Principals
	 * @throws EmpException
	 *             thrown if there is a problem during authentication
	 */
	public Subject authenticate(Credential credential) throws AuthenticationException {

		Subject subject = null;
		ResultSet rsu = null;
		PreparedStatement pstmt = null;
		Connection conn = null;

		String userid = credential.getProperty("userid");
		String password = credential.getProperty("password").trim();

		try {

			conn = dataSource.getConnection();

			if (logger.isInfoEnabled())
				logger.info(messageSource.getMessage("info.security.data.sql", new String[] { sqlQuery }, Locale.getDefault()));
			pstmt = conn.prepareStatement(sqlQuery);

			if (logger.isDebugEnabled()) {
				logger.debug(messageSource.getMessage("debug.security.data.userid", new String[] { userid }, Locale
						.getDefault()));
				logger.debug(messageSource.getMessage("debug.security.data.password", new String[] { password }, Locale
						.getDefault()));
			}

			pstmt.setString(1, userid);
			pstmt.setString(2, password);

			rsu = pstmt.executeQuery();

			if (rsu.next()) {
				userid = rsu.getString(1);
				String username = rsu.getString(2);
				password = rsu.getString(3);
				String grade = rsu.getString(4);

				Set principals = new HashSet();
				Set credentials = new HashSet();

				principals.add(new TypedPrincipal(username, TypedPrincipal.USER));

				StringTokenizer tokens = new StringTokenizer(grade, ",");
				while (tokens.hasMoreTokens()) {
					principals.add(new TypedPrincipal(tokens.nextToken(), TypedPrincipal.GROUP));
				}

				subject = new Subject(false, principals, credentials, credentials);
			}
			else {
				throw new AuthenticationException(messageSource, "error.security.login");
			}

		}
		catch (Exception e) {
			if (e instanceof AuthenticationException)
				throw (AuthenticationException) e;
			else
				throw new AuthenticationException(messageSource, "error.security.check.userid", e);
		}
		finally {
			try {
				if (rsu != null)
					rsu.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}
			catch (Exception e) {
			}
		}
		return subject;
	}
}
