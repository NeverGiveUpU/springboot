package learn.springcommon.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Lazy
@Component
public class LifeCircleBean {
    @PostConstruct
    public void postConstructor() {
        System.out.println("bean构造后");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("bean销毁前");
    }
}
