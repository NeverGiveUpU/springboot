package learn.springcommon.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 会替换默认的BeanPostProcessor bean，对全局的bean初始化都有影响
 */
//@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    /**
     * bean初始化前
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(
                "初始化前，beanName: "
                        + beanName);
        return bean;
    }

    /**
     * bean初始化后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(
                "初始化后，beanName: "
                        + beanName);
        return bean;
    }
}
