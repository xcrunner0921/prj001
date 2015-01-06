package com.sds.emp.common;

import org.springframework.context.MessageSource;

import anyframe.common.exception.BaseException;

/**
 * This SecurityException class contains various constructor
 * for different functionality on this project.
 * This class for AuthenticationService
 * 
 * @author Jeryeon Kim
 */
public class AuthenticationException extends BaseException {
    private static final long serialVersionUID = 1L;
    
    /**
     * The constructor with a message key, with
     * parameters, and with a wrapped exception (with
     * all the formal parameters).
     * @param messageSource
     *        message management service to extract a
     *        message
     * @param messageKey
     *        the message key of this exception
     */
    public AuthenticationException(final MessageSource messageSource,
            final String messageKey) {
        super(messageSource, messageKey);
        super.getMessageKey();
    }
    
    /**
     * The constructor with a message key, with
     * parameters, and with a wrapped exception (with
     * all the formal parameters).
     * @param messageSource
     *        message management service to extract a
     *        message
     * @param messageKey
     *        the message key of this exception
     * @param wrappedException
     *        the exception that is wrapped in this
     *        exception
     */
    public AuthenticationException(final MessageSource messageSource,
            final String messageKey, final Throwable wrappedException) {
        super(messageSource, messageKey, wrappedException);
    }
}
