package learn.springcommon.context;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "common")
public class CommonProperties {
    private String appName;
    private List<String> versions;
    @NestedConfigurationProperty
    private User user;

    @Data
    private class User {
        private String userName;
        private String passWord;
    }
}
