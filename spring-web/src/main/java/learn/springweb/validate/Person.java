package learn.springweb.validate;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * @author 陈濛
 * @date 2020/6/27 6:35 下午
 */
@Data
public class Person {

    @NameValidation
    @NotEmpty(message = "name不能为空")
    private String name;

    @Range(min = 0, max = 100, message = "age不能大于100小于0")
    private int age;

}