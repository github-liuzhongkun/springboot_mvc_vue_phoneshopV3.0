package com.city.shopmybatis.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component("businessServiceAdvice")
@Aspect
public class BusinessServiceAdvice {
	
	@Before(value="execution(* com.city.shopmybatis.service.impl.*.*(..))")
	public void methodBefore(JoinPoint jp) throws Exception{
		System.out.println("业务层方法前（before）切面代码执行。。。。。");
		System.out.println("切入类："+jp.getTarget().getClass().getName()+" 方法:"+jp.getSignature().getName());
		
		
	}
	
	@AfterReturning(value="execution(* com.city.shopmybatis.service.impl.*.*(..))",returning="result")
	public void methodAfterReturing(JoinPoint jp,Object result) throws Exception{
		System.out.println("业务层方法返回后（after returing）切面代码执行。。。。。");
		System.out.println("切入类："+jp.getTarget().getClass().getName()+" 方法:"+jp.getSignature().getName());
	}
	
	@After(value="execution(* com.city.shopmybatis.service.impl.*.*(..))")
	public void methodAfter(JoinPoint jp) throws Exception{
		System.out.println("业务层方法后（after）切面代码执行。。。。。");
		System.out.println("切入类："+jp.getTarget().getClass().getName()+" 方法:"+jp.getSignature().getName());
	}
	
	@Around(value="execution(* com.city.shopmybatis.service.impl.*.*(..))")
	public Object methodAround(ProceedingJoinPoint pjp) throws Throwable {
		
		Date start=new Date();
		//方法前代码区
		System.out.println("业务层方法环绕（Around）方法前（before)代码执行。。。。。");
		
		Object result=pjp.proceed();  //目标对象方法
		//方法后代码区
		System.out.println("业务层方法环绕（Around）方法前（after)代码执行。。。。。");
		Date end=new Date();
		System.out.println("切入类："+pjp.getTarget().getClass().getName()+" 方法:"+pjp.getSignature().getName()+"的执行时间是："+(end.getTime()-start.getTime())+"毫秒" );
		
		return result;
		
	}
	//异常抛出后切面
	
	@AfterThrowing(value="execution(* com.city.shopmybatis.service.impl.*.*(..))",throwing="ex")
	public void afterThrowException(JoinPoint jp,Exception ex) throws Exception{
		String date=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
		System.out.println("类:"+jp.getTarget().getClass().getName()+",方法:"+jp.getSignature().getName()+"时间:"+date+"异常:"+ex.getMessage());
	}
	
	

}
