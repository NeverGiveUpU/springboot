package learn.springweb;

import learn.springweb.service.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties(StorageProperties.class)
@EnableAsync //启动异步方法
@ServletComponentScan //扫描Servlet，例如@WebFilter注解
@SpringBootApplication
public class SpringWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}
