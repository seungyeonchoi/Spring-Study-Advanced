package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 특정 메서드 이름이 매칭 되는 경우에만 LogTrace 기능이 실행되는 부가기능
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target; // 프록시가 호출할 대상
    private final LogTrace logTrace;
    private final String[] patterns; // 메서드 이름 매칭 조건

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 메서드 이름 필터
        String methodName = method.getName();
        // save, request, requ*, *est
        if(false == PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "."
                    + method.getName() + "()"; // ex. OrderController.request()
            status = logTrace.begin(message);

            // 실제 로직 호출
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
