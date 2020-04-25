package learn.springmybatis.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class TransactionConfig implements TransactionManagementConfigurer {

    @Resource(name = "transactionManager1")
    private PlatformTransactionManager defaultTransactionManager;

    //返回默认的事务管理器
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return defaultTransactionManager;
    }

    //事务管理器1
    @Bean(name = "transactionManager1")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //事务管理器2
    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager2(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
