package learn.springmybatis.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 动态数据源路由配置
 *
 * 前面我们以及新建了数据源上下文，用于存储我们当前线程的数据源key那么怎么通知spring用key当前的数据源呢.
 * 查阅资料可知，spring提供一个接口，名为AbstractRoutingDataSource的抽象类，
 * 我们只需要重写determineCurrentLookupKey方法就可以，
 * 这个方法看名字就知道，就是返回当前线程的数据源的key。
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource
        implements InitializingBean, DisposableBean {

    private List<InitializingBean> initializingBeanDelegates = new ArrayList<>();

    private List<DisposableBean> disposableBeanDelegates = new ArrayList<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DataSourceContextHolder.getDataSourceRouterKey();
        log.info("当前数据源是：{}", dataSourceName);
        return DataSourceContextHolder.getDataSourceRouterKey();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        initializingBeanDelegates = new ArrayList<>();
        disposableBeanDelegates = new ArrayList<>();
    }

    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }

    @Override
    public void destroy() throws Exception {

    }
}