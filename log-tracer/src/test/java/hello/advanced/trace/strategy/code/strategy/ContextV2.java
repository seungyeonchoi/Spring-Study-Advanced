package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) { // 전략을 execute 가 호출될 때 마다 항상 받아옴
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); // 위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
