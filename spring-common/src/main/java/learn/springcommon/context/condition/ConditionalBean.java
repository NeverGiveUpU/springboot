package learn.springcommon.context.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Conditional(CustomCondition.class)
@Component
public class ConditionalBean {
    @PostConstruct
    public void init() {
        System.out.println("ConditionalBean创建成功");
    }
}
