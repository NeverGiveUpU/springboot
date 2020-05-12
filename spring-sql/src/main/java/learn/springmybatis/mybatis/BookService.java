package learn.springmybatis.mybatis;

import learn.springmybatis.mybatis.dao.BookDAO;
import learn.springmybatis.mybatis.model.BookDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 陈濛
 * @date 2020/5/8 9:23 下午
 */
@Slf4j
@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    @Transactional
    public void transactionInsert(BookDO bookDO) {
        bookDAO.insert(bookDO);
        log.info("111");
        throw new RuntimeException();
    }
}
