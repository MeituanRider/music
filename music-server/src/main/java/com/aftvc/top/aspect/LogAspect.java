package com.aftvc.top.aspect;

import com.aftvc.top.annotation.Log;
import com.aftvc.top.dao.SystemLogMapper;
import com.aftvc.top.domain.SystemLog;
import com.aftvc.top.utils.HttpContextUtils;
import com.aftvc.top.utils.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 19:51 2020/7/19
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@SuppressWarnings("ALL")
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Pointcut("@annotation(com.aftvc.top.annotation.Log)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result=null;
        long beginTime = System.currentTimeMillis();
        try {
            result=point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long time=System.currentTimeMillis()-beginTime;

        saveLog(point,time);
        return result;
    }

    /**
     * 保存日志
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint,long time){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        SystemLog systemLog=new SystemLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if(logAnnotation!=null){
            systemLog.setOperation(logAnnotation.value());
        }

        String className=joinPoint.getTarget().getClass().getName();
        String methodName=signature.getName();
        systemLog.setMethod(className+"."+methodName+"()");

        Object[] args = joinPoint.getArgs();

        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = u.getParameterNames(method);
        if(args!=null&&parameterNames!=null){
            String params="";
            for (int i=0;i<args.length;i++){
                params+= "  " + parameterNames[i] + ": " + args[i];
            }
            systemLog.setParams(params);
        }

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        systemLog.setIp(IPUtils.getIpAddr(request));

        String username="测试人员";
        systemLog.setUsername(username);
        systemLog.setTime((int) time);

        systemLog.setCreatetime(new Date());

        systemLogMapper.saveSysLog(systemLog);
    }
}
