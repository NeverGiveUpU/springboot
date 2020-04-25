package learn.springcommon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 根据TransformType动态的决定使用哪一个TransformService，而不是使用Qualifier决定。
 */
@Component
public class TransformServiceFactory {
    @Autowired
    private ApplicationContext applicationContext;

    protected AtomicBoolean created = new AtomicBoolean(false);
    protected Map<TransformType, List<TransformService>> map = new HashMap<>();


    public TransformService getTransformService(TransformType transformType) {
        List<TransformService> supportedTransformServices = initAndGet(transformType);
        if (supportedTransformServices == null || supportedTransformServices.size() == 0) {
            return null;
        }
        //从满足转换类型的转换服务中，选第一个
        return supportedTransformServices.get(0);
    }

    protected List<TransformService> initAndGet(TransformType transformType) {
        if (created.get()) {
            return map.get(transformType);
        }

        synchronized (TransformService.class) {
            if (created.get()) {
                return map.get(transformType);
            }
            List<TransformService> beanList = getBeansByType(TransformService.class);
            for (TransformService bean: beanList) {
                List<TransformType> supportTransformTypes = bean.getSupportType();
                if (supportTransformTypes.contains(transformType)) {
                    List<TransformService> list = map.get(transformType);
                    if (list == null) {
                        list = new ArrayList<>();
                        list.add(bean);
                        map.put(transformType, list);
                    } else {
                        list.add(bean);
                    }
                }
            }
            created.set(true);
        }
        return map.get(transformType);
    }

    protected <T> List<T> getBeansByType(Class<T> clazz) {
        List<T> beanList = new ArrayList<>();
        Map<String, T> name2BeanMap = applicationContext.getBeansOfType(clazz);
        if (!CollectionUtils.isEmpty(name2BeanMap)) {
            beanList = new ArrayList<>(name2BeanMap.values());
        }
        return beanList;
    }
}
