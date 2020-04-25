package learn.springcommon.aop.log;

import lombok.Data;
import lombok.ToString;

@Data
public class LogInfo {
    private String operateType;
    private boolean isSuccess;
    private String error;
}
