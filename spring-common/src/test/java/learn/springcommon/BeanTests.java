package learn.springcommon;

import learn.springcommon.bean.qualifier.MyService;
import learn.springcommon.bean.qualifier.custom.Service2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeanTests {

    @Service2
    @Autowired
    MyService myService;

    @Test
    void contextLoads() {
        System.out.println(myService.getClass().getName());
    }

}
