package learn.springcommon;

import learn.springcommon.aop.LogTest;
import learn.springcommon.aop.log.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCommonApplicationTests {

    @Autowired
    LogTest logTest;

    @Test
    void contextLoads() {
        logTest.test();
    }

}
