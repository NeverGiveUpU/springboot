package learn.springmybatis.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
public class DataSourceProperty {
    private String name;
    private String url;
    private String username;
    private String password;
    private String type;
    private String driverClassName;
}
