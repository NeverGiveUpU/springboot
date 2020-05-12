package learn.springmybatis.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class DataSourceAutoConfiguration {

    @Autowired
    DataSourceProperties dataSourceProperties;

    /**
     * 存储我们注册的数据源
     */
    private Map<Object, Object> dataSourceMap = new HashMap<>();

    /**
     * 别名
     */
    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();

    /**
     * 由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
     */
    static {
        aliases.addAliases("url", "jdbc-url");
        aliases.addAliases("username", "user");
    }

    @Bean
    public RoutingDataSource dataSource() {
        System.out.println(1111);
        System.out.println(dataSourceProperties);
        List<DataSourceProperty> dataSourcePropertyList = dataSourceProperties.getProperties();
        DataSource defaultDataSource = null;
        for (DataSourceProperty dataSourceProperty : dataSourcePropertyList) {
            String type = dataSourceProperty.getType();
            Class<? extends DataSource> clazz = getDataSourceType(type);
            //绑定DataSource和配置参数
            DataSource dataSource = bind(clazz, toMap(dataSourceProperty));

            String name = dataSourceProperty.getName();
            dataSourceMap.put(name, dataSource);

            if (name.equals("default")) {
                defaultDataSource = dataSource;
            }
            //存入上下文
            DataSourceContextHolder.dataSourceIds.add(name);
            log.info("注册数据源{}成功", name);
        }
        Assert.notNull(defaultDataSource, "没有default数据源");
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(defaultDataSource);
        log.info("注册数据源成功，一共注册{}个数据源", dataSourceMap.size());
        return routingDataSource;
    }

    /**
     * 通过字符串获取数据源class对象
     *
     * @param typeStr
     * @return
     */
    private Class<? extends javax.sql.DataSource> getDataSourceType(String typeStr) {
        Class<? extends javax.sql.DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) {
                // 字符串不为空则通过反射获取class对象
                type = (Class<? extends javax.sql.DataSource>) Class.forName(typeStr);
            } else {
                // 默认为hikariCP数据源，与springboot默认数据源保持一致
                type = HikariDataSource.class;
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
        }
    }

    private <T extends javax.sql.DataSource> T bind(Class<T> clazz, Map properties) {
        //map -> ConfigurationPropertySource
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        //ConfigurationPropertySource -> Binder，并且带别名
        Binder binder = new Binder(source.withAliases(aliases));
        // 通过类型绑定参数并获得实例对象
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get();
    }

    private Map toMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(object, Map.class);
    }
}
