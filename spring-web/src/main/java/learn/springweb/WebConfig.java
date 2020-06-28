package learn.springweb;

import learn.springweb.async.MyAsyncHandlerInterceptor;
import learn.springweb.repeatable.RepeatableInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.unit.DataSize;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    RepeatableInterceptor repeatableInterceptor;

    @Autowired
    MyAsyncHandlerInterceptor myAsyncHandlerInterceptor;


    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatableInterceptor).addPathPatterns("/repeatable/*");
        registry.addInterceptor(myAsyncHandlerInterceptor).addPathPatterns("/async/*");
    }

    /**
     * 表单配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(DataSize.ofMegabytes(50));
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));
        return factory.createMultipartConfig();
    }

    /**
     * 异步请求的默认配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //超时时间
        configurer.setDefaultTimeout(60 * 1000);
        //处理的线程池
        configurer.setTaskExecutor(taskExecutor());
        //超时后拦截器处理设置
        configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }

    /**
     * 线程池
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }
}
