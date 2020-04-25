package learn.springmybatis.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "custom.datasource")
public class DataSourceProperties {
    private boolean enable;
    private List<DataSourceProperty> properties;
}
