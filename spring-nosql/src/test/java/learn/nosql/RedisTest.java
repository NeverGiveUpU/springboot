package learn.nosql;

import learn.nosql.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 陈濛
 * @date 2020/4/12 8:06 下午
 */
@Slf4j
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisService redisService;

    @Test
    void testGet() {
        String name = (String) redisService.get("name");
        log.info(name);
    }

    @Test
    void testSet(){
        redisService.set("name", "yzy");
        String v = (String) redisService.get("name");
        log.info(v);
    }
}
