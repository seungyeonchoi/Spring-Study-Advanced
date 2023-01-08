package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test // 리플렉션 미적용
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직1  시작
        log.info("start");
        String result1 = target.callA(); //호출하는 메서드가 다름 -> 공통로직 1,2를 메서드로 추출 할 수 없음.
        log.info("result={}", result1);

        // 공통 로직2  시작
        log.info("start");
        String result2 = target.callB(); //호출하는 메서드가 다름
        log.info("result={}", result2);
    }

    @Test // 리플렉션 적용
    void reflection1() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        //클래스 정보
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello"); //클래스 메타정보 획득(내부 클래스 구분을 위해 $사용)

        Hello target = new Hello();

        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target); //호출하는 메서드가 같음 -> 클래스나 메서드 정보를 동적으로 변경 가능 == 추상화 가능
        log.info("result={}", result1);

        //callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB"); //호출하는 메서드가 같음
        Object result2 = methodCallB.invoke(target);
        log.info("result={}", result2);
    }

    @Test // 리플렉션 적용
    void reflection2() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        //클래스 정보
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello"); //클래스 메타정보 획득(내부 클래스 구분을 위해 $사용)

        Hello target = new Hello();

        //callA 메서드 정보
        log.info("start");
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        //callA 메서드 정보
        log.info("start");
        Method methodCallB = classHello.getMethod("callB"); //호출하는 메서드가 같음
        dynamicCall(methodCallB, target);
    }

    //공통로직 1,2를 한꺼번에 처리할 수 있는 통합된 공통 처리 로직
    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
