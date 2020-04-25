package learn.springcommon.bean.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("request")
@Component
public class RequestBean {

}
