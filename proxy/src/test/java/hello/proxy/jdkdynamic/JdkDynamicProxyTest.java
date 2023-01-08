package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

//        Object proxy = Proxy.newProxyInstance(AInterface.class.getClassLoader(),
//                new Class[]{AInterface.class}, // 어떤 인터페이스를 기반으로 프록시를 만들지? == 프록시가 사용하는 로직
//                handler);

        AInterface proxy = (AInterface) Proxy.newProxyInstance( // 동적프록시 생성
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class}, // 어떤 인터페이스를 기반으로 프록시를 만들지? == 프록시가 사용하는 로직
                handler // proxy는 handler의 로직을 실행함
        );

        String call = proxy.call();// 동적프록시 호출 -> handler 의 invoke가 호출됨
        //21:14:06.961 [main] INFO hello.proxy.jdkdynamic.code.TimeInvocationHandler - TimeProxy 실행
        //21:14:06.963 [main] INFO hello.proxy.jdkdynamic.code.AImpl - A 호출
        //21:14:06.963 [main] INFO hello.proxy.jdkdynamic.code.TimeInvocationHandler - TimeProxy 종료 resultTime=0
        log.info("call result={}", call); // call result=A

        log.info("targetClass={}", target.getClass()); //targetClass=class hello.proxy.jdkdynamic.code.AImpl
        log.info("targetClass={}", proxy.getClass()); //targetClass=class com.sun.proxy.$Proxy9
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
                new Class[]{BInterface.class}, // 어떤 인터페이스를 기반으로 프록시를 만들지? == 프록시가 사용하는 로직
                handler // proxy는 handler의 로직을 실행함
        );

        String call = proxy.call();// handler 의 invoke가 호출됨
        //21:15:20.080 [main] INFO hello.proxy.jdkdynamic.code.TimeInvocationHandler - TimeProxy 실행
        //21:15:20.081 [main] INFO hello.proxy.jdkdynamic.code.BImpl - B 호출
        //21:15:20.081 [main] INFO hello.proxy.jdkdynamic.code.TimeInvocationHandler - TimeProxy 종료 resultTime=0
        log.info("call result={}", call); // call result=B

        log.info("targetClass={}", target.getClass()); //targetClass=class hello.proxy.jdkdynamic.code.BImpl
        log.info("targetClass={}", proxy.getClass()); //targetClass=class com.sun.proxy.$Proxy10 // 테스트 코드를 동시에 실행하면, 프록시 객체가 동시에 만들어지기 때문에 다른 값을 가짐
    }

}
