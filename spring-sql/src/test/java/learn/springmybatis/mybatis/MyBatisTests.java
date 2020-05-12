package learn.springmybatis.mybatis;

import learn.springmybatis.mybatis.dao.BookDAO;
import learn.springmybatis.mybatis.model.BookDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

@Slf4j
@SpringBootTest
class MyBatisTests {

	@Autowired
	BookDAO bookDAO;
	@Autowired
	BookService bookService;

	@Test
	void batchInsert() {
		BookDO bookDO1 = new BookDO("aa", 1);
		BookDO bookDO2 = new BookDO("bb", 1);
		bookDAO.batchInsert(Arrays.asList(bookDO1, bookDO2));
	}

	@Test
	void testTransaction() {
		bookService.transactionInsert(new BookDO("cc", 1));
	}

	@Test
	void testReturnId() {
		BookDO book = new BookDO("dd", 1);
		bookDAO.insert(book);
		long id = book.getId();
		assertThat(id).isNotNull();
		System.out.println(id);
	}

	//测试一对一关联查询
	@Test
	void testAssociate() {
		BookDO bookDO = bookDAO.selectAssociation(1000);
		assertThat(bookDO.getAuthor()).isNotNull();
		assertThat(bookDO.getId()).isNotNull().isNotZero();
		log.info("{}", bookDO.getAuthorId());
		log.info("{}", bookDO.getAuthor());
	}
}
