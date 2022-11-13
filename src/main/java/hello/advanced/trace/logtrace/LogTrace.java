package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;

// 로그 추적기의 최소한의 기능을 인터페이스 화
public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
