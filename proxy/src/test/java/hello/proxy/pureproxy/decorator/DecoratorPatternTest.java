package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        RealComponent realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);

        client.execute();
        //00:30:22.714 [main] INFO hello.proxy.pureproxy.decorator.code.RealComponent - RealComponent 실행
        //00:30:22.715 [main] INFO hello.proxy.pureproxy.decorator.code.DecoratorPatternClient - result=data
    }
}
