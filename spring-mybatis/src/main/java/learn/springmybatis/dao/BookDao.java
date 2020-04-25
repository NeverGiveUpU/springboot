package learn.springmybatis.dao;

import learn.springmybatis.BookDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao {
    @Insert("insert into book (name, number) values (#{book.name}, #{book.number})")
    void insert(@Param("book") BookDo book);
}
