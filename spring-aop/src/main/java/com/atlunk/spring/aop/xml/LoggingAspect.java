package com.atlunk.spring.aop.xml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//把这个类加入到IOC容器中去，然后在声明该类为一个切面

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

	//声明该方法是一个前置通知： 在目标方法开始执行之前执行
	//如果要调用的方法和@Before所声明的方法一致的时候，AOP框架会自动的为这个方法所在的类创建一个代理对象，然后在方法调用之前执行sysout的内容
	
	//只作用于add方法
	//@Before("execution(public int com.atlunk.spring.aop.xml.impl.ArithmeticCalculatorImpl.add(int, int))")
	//作用于所有方法
	//@Before("execution(public int com.atlunk.spring.aop.xml.impl.ArithmeticCalculatorImpl.*(int, int))")
	//任意修饰符，任意返回值， 这个包下的任意类
	@Before("execution(* com.atlunk.spring.aop.xml.impl.*.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint) {
		//JoinPoint： 访问一些连接细节
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method "+ methodName +" begins with" + args);
	}
	
}
