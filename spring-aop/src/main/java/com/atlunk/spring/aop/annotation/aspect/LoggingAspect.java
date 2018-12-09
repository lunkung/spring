package com.atlunk.spring.aop.annotation.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//把这个类加入到IOC容器中去，然后在声明该类为一个切面
@Order(2)//定义多个切面的执行顺序，值越小优先级越高
@Aspect
@Component
public class LoggingAspect {
	/*
	 * AOP术语
	 * 切面（Aspect）:横切关注点，跨越应用程序多个模块，被模块化的特殊对象
	 * 通知(Advice) : 切面必须要完成的工作，即切面里面的每一个方法即称之为通知（ 要执行的代码）
	 * 目标（target）：被通知的对象，就是你的业务逻辑代码，我们期望不写日志，由"通知"去完成写日志
	 * 代理（proxy）: 向目标应用通知后创建的对象
	 * 连接点(JoinPoint): 程序执行的某个特定位置，比如类的某个方法调用前，调用后，方法跑出异常后等。
	 * 切点（pointcut）： 每个类都有多个连接点，比如ArithmeticCalculator中的所有方法实际上都是连接点，
	 * 即连接点是程序类中客观存在的事物。AOP通过切点定位到特定的连接点。类比：连接点相当于数据库中的记录，切点相当于
	 * 查询条件。一个切点有多个连接点
	 */
	
	//1 定义一个方法，声明切面的表达式
	//2使用@Pointcut 声明切面表达式
	@Pointcut("execution(* com.atlunk.spring.aop.annotation.impl.*.*(..))")
	public void declarJoinPoint() {}
	
	//声明该方法是一个前置通知： 在目标方法开始执行之前执行
	//如果要调用的方法和@Before所声明的方法一致的时候，AOP框架会自动的为这个方法所在的类创建一个代理对象，然后在方法调用之前执行sysout的内容
	//只作用于add方法
	//@Before("execution(public int com.atlunk.spring.aop.annotation.impl.ArithmeticCalculatorImpl.add(int, int))")
	//作用于所有方法
	//@Before("execution(public int com.atlunk.spring.aop.annotation.impl.ArithmeticCalculatorImpl.*(int, int))")
	//任意修饰符，任意返回值， 这个包下的任意类
	//@Before("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))")
	
	//引用切面表达式所定义的方法
	@Before("declarJoinPoint()")
	public void beforeMethod(JoinPoint joinPoint) {
		//JoinPoint： 访问一些连接细节
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("[@Before]The method "+ methodName +" begins with" + args);
	}
	
	//后置通知：在目标方法执行完成后通知（无论是否发生异常）
	//在后置通知中不能访问目标方法执行的结果
	//@After("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))")
	
	@After("declarJoinPoint()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@After]The method "+ methodName +" ends");
	}
	
	
	/*
	 * 	返回通知：在目标方法正常结束后执行后的代码，如果发生异常将不会执行
	 * 	可以访问到目标方法执行的返回值
	 */
	//@AfterReturning(value=("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))"), returning="result")
	
	@AfterReturning(value=("declarJoinPoint()"), returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@AfterReturning]The method "+ methodName +" ends, result is " + result);
	}
	
	/*
	 * 	异常通知
	 * 	当目标方法出现异常的时候执行,可以指定出现的异常
	 */
	//@AfterThrowing(value=("execution(* com.atlunk.spring.aop.annotation.impl.*.*(int, int))"), throwing="ex")
	@AfterThrowing(value=("declarJoinPoint()"), throwing="ex")
	//public void afterThrowing(JoinPoint joinPoint, NullPointException ex) {
	public void afterThrowing(JoinPoint joinPoint, Object ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[@AfterThrowing]The method "+ methodName +" occurs exception " + ex);
	}
	
	
	/*
	 * 	环绕通知,就相当于com.atlunk.spring.aop.proxy.ArithmeticCalculatorLoggingProxy这个动态代理的全过程
	 * 	环绕通知:需要携带ProceedingJoinPoint类型的参数
	 * 	环绕通知必须有返回值，返回值即为目标方法的返回值
	 * 
	 */
	@Around("execution(public int com.atlunk.spring.aop.annotation.impl.ArithmeticCalculatorImpl.*(int, int))")
	public Object around(ProceedingJoinPoint pjd) {
		Object obj = null;
		String methodName = pjd.getSignature().getName();
		
		//1 执行目标方法
		try {
			//前置通知
			System.out.println("[@Around]The method "+ methodName +" begins with" + Arrays.asList(pjd.getArgs()));
			obj = pjd.proceed();
			//返回通知
			System.out.println("[@Around]The method "+ methodName +" ends, result is " + obj);
			
		} catch (Throwable e) {
			e.printStackTrace();
			//前置通知
			System.out.println("[@Around]The method "+ methodName +" occurs exception: " + e);
			throw new RuntimeException(e);
		}
		//后置通知
		System.out.println("[@Around]The method "+ methodName +" ends");
		return obj;
	}
	
}
