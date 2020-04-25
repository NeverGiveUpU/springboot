package learn.springcommon.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 拦截带有Log注解的方法，代理该方法，打印LogInfo
 */
@Slf4j
@Aspect
@Component
public class LogAdvice {

    //注解在方法上
    @Pointcut("@annotation(learn.springcommon.aop.log.Log)")
    public void methodLog() {}

    //注解在类上
    @Pointcut("@within(learn.springcommon.aop.log.Log)")
    public void classLog() {}

    @Around("methodLog() || classLog()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        /*1.方法执行前*/
        LogInfo logInfo = new LogInfo();
        //获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();
        //获取方法上或类上的注解
        Log logger = getAnnotation(method, Log.class);
        //获取注解的属性值
        String operationType = logger.operateType();
        logInfo.setOperateType(operationType);
        Object result = null;
        try {
            //被代理方法执行
            result = pjp.proceed();
            /*2.方法执行成功后*/
            logInfo.setSuccess(true);
        } catch (Throwable e) {
            /*3.异常通知*/
            logInfo.setSuccess(false);
            logInfo.setError(e.getMessage());
        } finally {
            /*4.最终通知*/
            log.info(logInfo.toString());
        }
        return result;
    }

    private <T extends Annotation> T getAnnotation(Method method, Class<T> cls) {
        T annotation = AnnotationUtils.findAnnotation(method, cls);
        if (annotation == null) {
            annotation = AnnotationUtils.findAnnotation(method.getDeclaringClass(), cls);
        }
        return annotation;
    }
}
