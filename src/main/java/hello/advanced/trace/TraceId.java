package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

    private String id; // 하나의 http 요청 처리 과정에서 공유되는 트랜잭션 id
    private int level; // 메서드 깊이

    public TraceId() {
        this.id = creatId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String creatId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private TraceId createNextId() { // 메서드 깊이 증가 시 사용할 메서드
        return new TraceId(id, level+1);
    }

    private TraceId createPreviousId() { // 메서드 깊이 증가 시 사용할 메서드
        return new TraceId(id, level-1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
