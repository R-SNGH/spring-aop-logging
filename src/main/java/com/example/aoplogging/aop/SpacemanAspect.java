package com.example.aoplogging.aop;

import com.example.aoplogging.entities.Spaceman;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
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

    @After(value = "execution(* com.example.aoplogging.controller.SpacemanController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("Request to " +joinPoint.getSignature() + "ended at "+ new Date());
    }

    @Before(value = "execution(* com.example.aoplogging.service.SpacemanService.*(..))")
    public void beforeAdviceForService(JoinPoint joinPoint){
        System.out.println("Request to service " +joinPoint.getSignature() + "started at "+ new Date());
    }

    @After(value = "execution(* com.example.aoplogging.service.SpacemanService.*(..))")
    public void afterAdviceForService(JoinPoint joinPoint){
        System.out.println("Request to service " +joinPoint.getSignature() + "ended at "+ new Date());
    }

    //more advices
    @AfterReturning(value = "execution(* com.example.aoplogging.service.SpacemanService.addSpaceman(..))", returning = "spaceman")
    public void afterReturningAdviceForService(JoinPoint joinPoint, Spaceman spaceman){
        System.out.println("Business logic to add a Spaceman executed successfully. Spaceman is saved with ID :" + spaceman.getId());
    }

    @AfterThrowing(value = "execution(* com.example.aoplogging.service.SpacemanService.addSpaceman(..))", throwing = "exception")
    public void afterThrowingAdviceForService(JoinPoint joinPoint, Exception exception){
        System.out.println("Business logic to add Spaceman threw an exception:  " +exception.getMessage());
    }

}
