package learn.springweb.ordered_filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册自定义Filter的bean
 */
@Configuration
public class Config {

    @Bean
    public FilterRegistrationBean<Test1Filter> test1Filter() {
        //通过FilterRegistrationBean实例设置优先级可以生效
        //通过@WebFilter无效
        FilterRegistrationBean<Test1Filter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new Test1Filter());//注册自定义过滤器
        bean.setName("test1Filter");//过滤器名称
        bean.addUrlPatterns("/ordered_filter/*");//拦截/ordered_filter开头的路径
        bean.setOrder(1);//优先级最高

        return bean;
    }

    @Bean
    public FilterRegistrationBean<Test2Filter> test2Filter() {
        //通过FilterRegistrationBean实例设置优先级可以生效
        //通过@WebFilter无效
        FilterRegistrationBean<Test2Filter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new Test2Filter());//注册自定义过滤器
        bean.setName("test2Filter");//过滤器名称
        bean.addUrlPatterns("/ordered_filter/*");//拦截所有路径
        bean.setOrder(2);//优先级低

        return bean;
    }

}
