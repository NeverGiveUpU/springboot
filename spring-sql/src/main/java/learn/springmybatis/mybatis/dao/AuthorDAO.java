package learn.springmybatis.mybatis.dao;

import learn.springmybatis.mybatis.model.AuthorDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 陈濛
 * @date 2020/5/12 7:46 下午
 */
@Repository
public interface AuthorDAO {

    String TABLE = "author";

    @Select({"select * from", TABLE, "where id=#{id}"})
    AuthorDO select(@Param("id") long id);
}
