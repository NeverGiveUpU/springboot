package learn.springcommon.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class LifeCircleBean1 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println(this.getClass().getName() + " destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getName() + " afterPropertiesSet");
    }
}
