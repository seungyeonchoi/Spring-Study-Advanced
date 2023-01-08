package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler { //InvocationHandler을 구현해야 JDK 동적 프록시에 적용할 공통로직 개발 가능
// TimeInvocationHandler가 동적 프록시에 적용할 핸들러 로직
    private final Object target; //동적프록시가 호출할 대상

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args); // call이 넘어오게 됨
        long endTime = System.currentTimeMillis();

        log.info("TimeProxy 종료 resultTime={}", startTime - endTime);

        return result;
    }
}
