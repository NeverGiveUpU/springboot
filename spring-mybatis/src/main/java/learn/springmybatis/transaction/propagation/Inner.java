package learn.springmybatis.transaction.propagation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Inner {

    //和外层共用一个事务
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequired() {
        throw new RuntimeException("inner异常，回滚！");
    }

    //新事务。当 inner 事务开启后，outer 事务会暂停，当 inner 事务结束后，outer 事务恢复。
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testRequiredNew() {
        throw new RuntimeException("inner异常，回滚！");
    }
}
