package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        client.execute();
        client.execute();
        client.execute();

        //00:17:34.546 [main] INFO hello.proxy.pureproxy.proxy.code.RealSubject - 실제 객체 호출
        //00:17:35.549 [main] INFO hello.proxy.pureproxy.proxy.code.RealSubject - 실제 객체 호출
        //00:17:36.554 [main] INFO hello.proxy.pureproxy.proxy.code.RealSubject - 실제 객체 호출
    }
}
