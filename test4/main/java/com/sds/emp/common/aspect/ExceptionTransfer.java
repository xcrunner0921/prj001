package com.sds.emp.common.aspect;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import com.sds.emp.common.EmpException;

/**
 * This ExceptionTransfer class contains messageSource
 * and transfer method for catching and transferring
 * exception through aspect service
 * @author Taeho Kim
 */
@Aspect
public class ExceptionTransfer {

    @Pointcut("execution(* com.sds.emp..*ServiceImpl.*(..))")
    public void serviceMethod() {
    }

    /**
     * messageSource is used to call getMessage method.
     */
    // well-known "resolvable dependencies" using
    // @Resource
    @Resource
    private MessageSource messageSource;

    /**
     * catch and transfer exception through aspect
     * service ( setting :
     * [src/main/resources/spring/common/context-aspect.xml] )
     * @param thisJoinPoint
     *        aspect service JoinPoint
     * @param exception
     *        aspect service exception
     */
    @AfterThrowing(pointcut = "serviceMethod()", throwing = "exception")
    public void transfer(JoinPoint thisJoinPoint, Exception exception)
            throws EmpException {
        String pkgName =
            thisJoinPoint.getTarget().getClass().getName().toLowerCase();
        int lastIndex = pkgName.lastIndexOf(".");
        String className = pkgName.substring(lastIndex + 1);
        String opName = (thisJoinPoint.getSignature().getName()).toLowerCase();

        Log logger = LogFactory.getLog(thisJoinPoint.getTarget().getClass());

        if (exception instanceof EmpException) {
            EmpException empEx = (EmpException) exception;
            logger.error(empEx.getMessage(), empEx);
            throw empEx;
        }

        logger.error(messageSource.getMessage("error." + className + "."
            + opName, new String[] {}, Locale.getDefault()), exception);
        throw new EmpException(messageSource, "error." + className + "."
            + opName, new Object[] {}, exception);

    }

    /**
     * This method is used to set messageSource bean
     * @param applicationContext
     *        <code>ApplicationContext</code> object.
     * @throws BeansException
     *         if bean is not found.
     * @see org.springframework.context.ApplicationContextAware
     *      #setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Pointcut("execution(* com.sds.emp..*ServiceImpl.setApplicationContext(..))")
    public void setApplicationContextMethod() {
    }

    @After("setApplicationContextMethod() && args(applicationContext)")
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.messageSource =
            (MessageSource) applicationContext.getBean("messageSource");
    }
}
