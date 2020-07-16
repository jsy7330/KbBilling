package com.ntels.ccbs.common.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
 
@Aspect
public class LoggerAspect {
    protected Log log = LogFactory.getLog(LoggerAspect.class);
    static String name = "";
    static String type = "";
     
    public LoggerAspect() {
		log.info("LoggerAspect Created");
	}

	@Around("execution(* com.ntels.ccbs..controller..*Controller.*(..)) or execution(* com.ntels.ccbs..service..*ServiceImpl.*(..)) or execution(* com.ntels.ccbs..mapper..*Mapper.*(..))")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        type = joinPoint.getSignature().getDeclaringTypeName();
         
        if (type.indexOf("Controller") > -1) {
            name = "Controller  \t:  ";
            log.debug(name + type + "." + joinPoint.getSignature().getName() + "()");
        }
        else if (type.indexOf("Service") > -1) {
            name = "ServiceImpl  \t:  ";
            log.debug(name + type + "." + joinPoint.getSignature().getName() + "()");
        }
        else if (type.indexOf("Mapper") > -1) {
            name = "SQL ID  \t:  ";
            log.debug(name + type + "." + joinPoint.getSignature().getName());
        }
        return joinPoint.proceed();
    }
}