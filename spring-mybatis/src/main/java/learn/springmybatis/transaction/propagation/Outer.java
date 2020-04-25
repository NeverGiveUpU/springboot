package learn.springmybatis.transaction.propagation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class Outer {

    @Autowired
    private Inner inner;

    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequired() {
        /*
        数据库操作
         */
        //inner发生了回滚，out也回滚，所以没有插入
        try {
            inner.testRequired();
        } catch (RuntimeException e) {
            log.info(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredNew() {
        /*
        数据库操作
         */
        //inner事务发生了回滚，不影响outer的事务
        try {
            inner.testRequiredNew();
        } catch (RuntimeException e) {
            log.info(e.getMessage());
        }
    }
}
