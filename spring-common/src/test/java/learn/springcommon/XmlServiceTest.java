package learn.springcommon;

import learn.springcommon.misc.XmlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XmlServiceTest {
    @Autowired
    XmlService xmlService;

    @Test
    public void test() {
        System.out.println(xmlService.getPattern("one").trim());
    }
}
