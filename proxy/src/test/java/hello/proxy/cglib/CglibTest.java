package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib_인터페이스없는_구체클래스일때() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class); // ConcreteService 를 부모클래스로 지정하는 설정 -> 만들어질 프록시는 이 클래스를 부모클래스로 하여 상속받음 -> 상속이 가지는 단점이 그대로 발생함
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass={}", target.getClass()); //targetClass=class hello.proxy.common.service.ConcreteService
        log.info("proxyClass={}", proxy.getClass()); //proxyClass=class hello.proxy.common.service.ConcreteService$$EnhancerByCGLIB$$25d6b0e3

        proxy.call();
        //22:10:46.134 [main] INFO hello.proxy.cglib.code.TimeMethodInterceptor - TimeProxy 실행
        //22:10:46.139 [main] INFO hello.proxy.common.service.ConcreteService - ConcreteService call 호출
        //22:10:46.140 [main] INFO hello.proxy.cglib.code.TimeMethodInterceptor - TimeProxy 종료 resultTime=-6
    }
}
