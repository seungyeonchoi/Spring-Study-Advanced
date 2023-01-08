package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor { // MethodInterceptor를 구현해서, CGLIB 프록시의 실행 로직 정의

    private final Object target; // 프록시는 항상 실제 호출할 대상이 필요함

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = methodProxy.invoke(target, args); // CGLIB에서 method보다 methodProxy를 더 권장함

        long endTime = System.currentTimeMillis();
        log.info("TimeProxy 종료 resultTime={}", startTime - endTime);

        return result;
    }
}
