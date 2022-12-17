package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
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

    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();

        //00:17:08.998 [main] INFO hello.proxy.pureproxy.proxy.code.CacheProxy - 프록시 호출
        //00:17:08.999 [main] INFO hello.proxy.pureproxy.proxy.code.RealSubject - 실제 객체 호출
        //00:17:10.004 [main] INFO hello.proxy.pureproxy.proxy.code.CacheProxy - 프록시 호출
        //00:17:10.005 [main] INFO hello.proxy.pureproxy.proxy.code.CacheProxy - 프록시 호출
    }
}
