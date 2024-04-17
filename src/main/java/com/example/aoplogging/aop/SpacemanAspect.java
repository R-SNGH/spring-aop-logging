package com.example.aoplogging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component // to make this a bean
public class SpacemanAspect {

    //Do this before starting EVERY method of SpacemanController (fully qualified classname):
    //having any (..) number of params
    //this is called a pointCut expression
    //JoinPoint is the place where this pointCut executes
    //JoinPoint is the businessLogic where advice is implemented
    //Here, it is the two methods in the SpacemanController
    @Before(value = "execution(* com.example.aoplogging.controller.SpacemanController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Request to " +joinPoint.getSignature() + "started at "+ new Date());
    }
}
