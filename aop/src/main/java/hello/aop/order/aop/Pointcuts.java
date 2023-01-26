package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Import;

public class Pointcuts {

    //hello.aop.order 패키지와 그 하위 패키지를 지칭하고 있음.
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){ //pointcut signature
        // 코드내용은 비워둠
    }

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
