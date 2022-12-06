package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
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

    @Test
    void decorator1() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
        //00:30:11.564 [main] INFO hello.proxy.pureproxy.decorator.code.MessageDecorator - MessageDecorator 실행
        //00:30:11.565 [main] INFO hello.proxy.pureproxy.decorator.code.RealComponent - RealComponent 실행
        //00:30:11.565 [main] INFO hello.proxy.pureproxy.decorator.code.MessageDecorator - MessageDecorator 꾸미기 적용 전=data, 적용 후=****data****
        //00:30:11.566 [main] INFO hello.proxy.pureproxy.decorator.code.DecoratorPatternClient - result=****data****
    }
}
