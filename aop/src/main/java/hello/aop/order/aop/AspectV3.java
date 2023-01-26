package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    /**
     * OrderService: doLog -> doTransaction 적용
     * OrderRepository: doLog 적용
     */

    //hello.aop.order 패키지와 그 하위 패키지를 지칭하고 있음.
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){ //pointcut signature
        // 코드내용은 비워둠
    }
    
    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { // 어드바이스
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
    
    //hello.aop.order 패키지와 하위패키지 && 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[트랜잭션 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
