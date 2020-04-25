package learn.springcommon.bean.qualifier;

import learn.springcommon.bean.qualifier.custom.Service1;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Service1
public class MyServiceImpl1 implements MyService {
}
