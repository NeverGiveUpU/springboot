package learn.springweb.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈濛
 * @date 2020/6/28 4:37 下午
 */
@Slf4j
@RestController("sessionTestController")
public class TestController {
    @GetMapping("/session")
    public String get() {
        SessionInfo sessionInfo = SessionContext.getSessionInfo();
        String ip = sessionInfo.getClientIp();
        log.info(ip);
        return ip;
    }
}
