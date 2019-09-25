package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

/**
 * @Author: Liu Zhendong
 * @Description 切面异常处理
 * @createTime 2019年09月18日 10:17:00
 */

@Component
public class ExceptionAspect {
    //方法的切点
    @Pointcut("execution(* *(..))")
    private void cutMethod(){}

    //某个class 或interface中所有的方法
    @Pointcut("within(*.com.example..*)")
    private void cutMethoInClass(){}

    //某个特定的的被调用的方法,应该是固定改类的实实例对象方法
    @Pointcut("this(com.example.demo.service.DemoService)")
    private void cutAopProxyObj(){}

    @Pointcut("target(com.example.demo.mapper.DemoMapper)")
    private void cutInstance(){}

    @Pointcut("args()")
    private void cutArgs(){}

    @Pointcut("@annotation(com.example.demo.annotated.Constraints)")
    private void cutAnnotation(){}

    //匹配注解有AdminOnly注解的方法
    @Pointcut("@annotation(com.sample.security.AdminOnly)")
    public void demo1(){}

    //匹配标注有admin的类中的方法，要求RetentionPolicy级别为CLASS
    @Pointcut("@within(com.sample.annotation.admin)")
    public void demo2(){}

    //匹配传入参数的类标注有Repository注解的方法
    @Pointcut("args(org.springframework.stereotype.Repository)")
    public void demo3(){}

    //注解标注有Repository的类中的方法，要求RetentionPolicy级别为RUNTIME
    @Pointcut("target(org.springframework.stereotype.Repository)")
    public void demo4(){}



    @Before(value = "@annotation(com.example.demo.annotated.Constraints)",argNames = "")
    public void before(JoinPoint joinPoint){

    }

    @Around(value = "cutMethod()",argNames = "args")
    public Object doAround(ProceedingJoinPoint pjp,String[] args){
        return null;
    }

    @After(value = "",argNames = "")
    public void after(){}

    @AfterReturning(pointcut = "execution()",returning = "",value = "",argNames = "")
    public void afterReturning(){}

    @AfterThrowing(value = "",pointcut = "",throwing = "",argNames = "")
    public void afterThrowing(){}

}
