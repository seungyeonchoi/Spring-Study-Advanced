package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
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

    @Test
    void decorator2() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator); // 체이닝 되어서 데코레이터가 호출됨 ex. 타임 데코레이터 -> 메시지 데코레이터

        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();

        //00:37:10.833 [main] INFO hello.proxy.pureproxy.decorator.code.TimeDecorator - TimeDecorator 실행
        //00:37:10.834 [main] INFO hello.proxy.pureproxy.decorator.code.MessageDecorator - MessageDecorator 실행
        //00:37:10.834 [main] INFO hello.proxy.pureproxy.decorator.code.RealComponent - RealComponent 실행
        //00:37:10.834 [main] INFO hello.proxy.pureproxy.decorator.code.MessageDecorator - MessageDecorator 꾸미기 적용 전=data, 적용 후=****data****
        //00:37:10.835 [main] INFO hello.proxy.pureproxy.decorator.code.TimeDecorator - TimeDecorator 종료 resultTime=1
        //00:37:10.835 [main] INFO hello.proxy.pureproxy.decorator.code.DecoratorPatternClient - result=****data****
    }
}
