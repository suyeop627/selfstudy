package com.study.firstlecture.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component 컴포넌트스캔 or 빈 등록을 해서 컨테이너에 올려 사용.(직접 빈을 등록할 경우, 순환참조 문제가 발생할 수 있으므로,
//AOP의 대상에서 SpringConfig를 제외시킨다. -> !target ~ 부분
@Aspect//AOP로 사용하기 위한 어노테이션
public class TimeTraceAop {

  @Around("execution(* com.study.firstlecture..*(..)) && !target(com.study.firstlecture.SpringConfig)") //공통 관심사항을 어느 핵심 관심사항에 적용할지 설정
                    //패키지의 모든 하위 클래스에 적용(클래스, 패러미터 등을 지정할 수 도 있다)
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();  //시작 시간 측정

    System.out.println("START: " + joinPoint.toString());
    try {
        Object result=joinPoint.proceed();
        return result;

    } finally {

      long finish = System.currentTimeMillis(); //종료시간 측정
      long timeMs = finish - start; //로직 실행시, 걸린 시간 계산

      System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
    }
  }
}