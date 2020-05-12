package learn.springmybatis.mybatis.dao;

import learn.springmybatis.mybatis.model.BookDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO {

    String TABLE = "book";

    //同时查询多个可选条件
    @Select({
            "<script>",
            "select * from", TABLE,
                "<where>",
                    "<if test='book.id!=null'>",
                        "id=#{book.id}",
                    "</if>",
                    "<if test='book.name!=null'>",
                        "and name=#{book.name}",
                    "</if>",
                    "<if test='book.number!=null'>",
                        "and number=#{book.number}",
                    "</if>",
                "</where>",
            "</script>"
    })
    BookDO selectOptional(@Param("book") BookDO book);

    //in语句
    @Select({
            "<script>",
            "select * from", TABLE, "where id in",
                "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
                    "#{item}",
                "</foreach>",
            "</script>"
    })
    List<BookDO> selectIdIn(@Param("ids") List<Integer> ids);

    //关联查询
    @Select({"select * from", TABLE, "where id=#{id}"})
    @Results(id = "associate_author", value = {
            @Result(column = "author_id", property = "authorId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "author_id", property = "author", one = @One(select = "learn.springmybatis.mybatis.dao.AuthorDAO.select"))
    })
    BookDO selectAssociation(@Param("id") long id);

    //插入后返回id
    @Insert({"insert into", TABLE, "(name, number) values (#{book.name}, #{book.number})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long insert(@Param("book") BookDO book);

    //batch insert
    @Insert({
            "<script>",
            "insert into", TABLE, "(name, number) values",
            "<foreach collections='books' index='index' item='item' separator=','>",
                "(#{item.name}, #{item.number})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("books") List<BookDO> books);

    //可选更新
    @Update({
            "<script>",
            "update", TABLE,
                "<set>",
                    "<if test='book.name != null'>name=#{book.name},</if>",
                    "<if test='book.number != null'>number=#{book.number},</if>",
                "</set>",
            "where id=#{book.id}",
            "</script>"
    })
    void updateNecessary(@Param("book") BookDO book);
}
