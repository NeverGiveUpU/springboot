package learn.springcommon.aop;

import learn.springcommon.aop.log.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class LogTest {
    public void test() {
        System.out.println("test");
    }
}
