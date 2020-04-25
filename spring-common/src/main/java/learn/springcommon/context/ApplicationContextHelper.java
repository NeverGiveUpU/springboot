package learn.springcommon.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    //上下文对象
    private static ApplicationContext context = null;

    //spring会把applicationContext传到这个方法里
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    public static Environment getEnvironment() {
        return context.getEnvironment();
    }

    public static Resource getResource(String location) {
        return context.getResource(location);
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> beanType) {
        return context.getBean(beanType);
    }

    //bean factory是否包含这个bean
    public static boolean containsBean(String beanName) {
        return context.containsBean(beanName);
    }

    //激活的profile属性
    public static String[] getActiveProfile() {
        return getEnvironment().getActiveProfiles();
    }
}
