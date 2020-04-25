package learn.springcommon.bean.qualifier;

import learn.springcommon.bean.qualifier.custom.Service1;
import learn.springcommon.bean.qualifier.custom.Service2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Service2
public class MyServiceImpl2 implements MyService {
}
