package learn.springmybatis.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, UseDataSource ds) throws Throwable {
        String dsId = ds.value();
        if (DataSourceContextHolder.containsDataSource(dsId)) {
            log.debug("Use DataSource :{} > {}", dsId, point.getSignature());
        } else {
            log.info("数据源[{}]不存在，使用默认数据源 >{}", dsId, point.getSignature());
            DataSourceContextHolder.setDataSourceRouterKey(dsId);
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, UseDataSource ds) {
        log.debug("Revert DataSource : " + ds.value() + " > " + point.getSignature());
        DataSourceContextHolder.removeDataSourceRouterKey();
    }
}
