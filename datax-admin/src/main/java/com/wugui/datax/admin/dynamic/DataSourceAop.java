package com.wugui.datax.admin.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author yorke
 * @Description:
 * @date 2021/1/18
 */
@Aspect
@Component
public class DataSourceAop {


    @Pointcut("execution (* com.wugui.datax.admin.service..*.*(..))")
    private void anyMethod() {
    }


    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        DynamicDataSource.setDataSource("dbkey");
        return pjp.proceed();
    }

}
