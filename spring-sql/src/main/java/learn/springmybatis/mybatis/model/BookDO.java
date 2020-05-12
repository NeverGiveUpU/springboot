package learn.springmybatis.mybatis.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookDO {
    private long id;
    private String name;
    private int number;
    private long authorId;
    private AuthorDO author;

    public BookDO(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
