package learn.springweb.http;

import learn.springweb.http.asyncmethod.GitHubLookupService;
import learn.springweb.http.asyncmethod.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 陈濛
 * @date 2020/5/8 4:09 下午
 */
@Slf4j
@SpringBootTest
public class AsyncMethodTest {

    @Autowired
    private GitHubLookupService gitHubLookupService;


    @Test
    void test() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        //多次异步查询（每个查询启动新线程）
        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");

        //异步查询的线程先执行
        CompletableFuture.allOf(page1, page2, page3).join();

        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }
}
