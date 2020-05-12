package learn.springcommon;

import learn.springcommon.context.CommonProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties(CommonProperties.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class SpringCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCommonApplication.class, args);
    }

}
