package com.sds.emp.common.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * This LoggingAspect class contains beforeLogging
 * method for logging through aspect service
 * 
 * @author Jeryeon Kim
 */
@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.sds.emp..*ServiceImpl.*(..))")
    public void loggingMethod() {
    }

    /**
     * catch and logging through aspect service (
     * setting :
     * 
     * @param thisJoinPoint
     *        aspect service JoinPoint
     */
    @Before("loggingMethod()")
    public void beforeLogging(JoinPoint thisJoinPoint) {
        Class clazz = thisJoinPoint.getTarget().getClass();
        String className =
            (thisJoinPoint.getTarget().getClass().getName()).toLowerCase();
        String methodName = thisJoinPoint.getSignature().getName();

        StringBuffer buf = new StringBuffer();
        buf.append("\n** EmpTemplate Logging Aspect : executed " + methodName
            + "() in " + className + " Class.");
        Object[] arguments = thisJoinPoint.getArgs();
        if (arguments.length > 0) {
            for (int i = 0; i < arguments.length; i++) {
                buf.append("\n*************"
                    + arguments[i].getClass().getName() + "*************\n");
                buf.append(arguments[i].toString());
                buf.append("\n*******************************************\n");
            }
        } else
            buf.append("\nNo arguments\n");

        Log logger = LogFactory.getLog(clazz);
        if (logger.isDebugEnabled())
            logger.debug(buf.toString());
    }
}
