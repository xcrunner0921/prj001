package com.sds.emp.security.service;

import javax.security.auth.Subject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sds.emp.common.AuthenticationException;
import com.sds.emp.common.EmpException;

/**
 * This supports the authentication service for
 * access-control.<br>
 * It keeps strictly the JAAS regulation. So, any
 * modules compatible with JAAS is added on this
 * service easily.<br>
 * This provides DataBase Based of authentication
 * service.<br>
 */
public interface AuthenticationService {
    public static Log logger = LogFactory.getLog(AuthenticationService.class);

    /**
     * Use this method to return a populated Subject.
     * It is populated with the principals which
     * represent the identity of the user as well as
     * any other principal for which permissions may be
     * associated. For instance, if the configured
     * Realm implementation has
     * <code>GroupSupport</code> then the
     * Authentication Manager may choose to add a
     * Principal for each Group the user is a member
     * of. Additional Principals may be added to
     * represent security related attributes that may
     * be reflected in Policy definition, for Realms
     * with <code>AttributeSupport</code>.
     * @param credential
     *        a collection of credential objects used
     *        provided as proof of identity
     * @return a Subject populated with appropriate
     *         Principals
     * @throws EmpException
     *         thrown if there is a problem during
     *         authentication
     */
    public Subject authenticate(Credential credential) throws AuthenticationException;

}
