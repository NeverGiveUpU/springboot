package learn.springmybatis.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDO {
    private long id;
    private String name;

}
