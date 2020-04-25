package learn.springcommon.context;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CommonValue {
    @Value("${common.app-name}")
    private String appName;
    @Value("this is test")
    private String test;
}
