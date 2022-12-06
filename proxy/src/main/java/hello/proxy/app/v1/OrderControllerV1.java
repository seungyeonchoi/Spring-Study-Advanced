package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping // 스프링은 @Controller or @RequestMapping 이 있어야, 스프링 컨트롤러로 인식 -> http url이 매핑되고 동작함
@ResponseBody // http 메시지 컨버터를 사용해서 응답
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId); // 인터페이스에는 @RequestParm 어노테이션을 분명히 명시해야 함

    @GetMapping("v1/no-log")
    String noLog();
}
