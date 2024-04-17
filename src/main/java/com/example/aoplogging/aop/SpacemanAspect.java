package com.example.aoplogging.aop;

import com.example.aoplogging.entities.Spaceman;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.data.jpa.repository.query.Procedure;
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

    //Around is the MOST POWERFUL Advice
    // version 1 : basics
//    @Around(value = "execution(* com.example.aoplogging.service.SpacemanService.addSpaceman(..))")
//    public void aroundAdviceForService(ProceedingJoinPoint joinPoint){
//        System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman started at: "+ new Date());
//        try{
//            //if we don't place proceed here, the actual method won't execute at all
//            joinPoint.proceed();
//        }catch(Throwable e){
//            System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman failed terribly! "+ e.getMessage());
//        }
//        System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman ended at: "+ new Date());
//    }

    //Around version2 : returning something useful to Client
//    @Around(value = "execution(* com.example.aoplogging.service.SpacemanService.addSpaceman(..))")
//    public Spaceman aroundAdviceForService(ProceedingJoinPoint joinPoint){
//        System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman started at: "+ new Date());
//        try{
//            Spaceman spaceman = (Spaceman) joinPoint.proceed();
//            return spaceman;
//        }catch(Throwable e){
//            System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman failed terribly! "+ e.getMessage());
//        }
//        System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman ended at: "+ new Date());
//        return null;
//    }

    //Around version 3: intercepting input arguments
    @Around(value = "execution(* com.example.aoplogging.service.SpacemanService.addSpaceman(..))")
    public Spaceman aroundAdviceForService(ProceedingJoinPoint joinPoint){
        System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman started at: "+ new Date());
        try{
            Spaceman[] spacemen = new Spaceman[1];
            Spaceman dummySpaceman = new Spaceman();
            dummySpaceman.setName("Random Name Assigned");
            spacemen[0]=dummySpaceman;
            Spaceman spaceman = (Spaceman) joinPoint.proceed(spacemen);
            return spaceman;
        }catch(Throwable e){
            System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman failed terribly! "+ e.getMessage());
        }
        System.out.println("Inside Around advice in Aspect: Business Logic to save Spaceman ended at: "+ new Date());
        return null;
    }

    /*
    Notes:
    1) without joinPoint.proceed(); the actual method is not executed
    2) the return type of this advice is the response to client; with void -> zero response; test on postman
    3) we can write joinPoint.proceed(); multiple times to execute the method multiple times
    4) WE can catch exception here and do our logging
    5) We can stop method execution by not writing joinPoint.proceed(); and then return something that we wish to return to Client
    6) We can transform the input as well here.
    */

}
