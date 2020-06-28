package learn.springweb.repeatable;

import learn.springweb.session.SessionContext;
import learn.springweb.session.SessionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author 陈濛
 * @date 2020/6/28 4:37 下午
 */
@Slf4j
@RestController("repeatableTestController")
@RequestMapping("/repeatable")
public class TestController {
    @PostMapping("/post")
    public String get() {
        return "repeatable";
    }
}
