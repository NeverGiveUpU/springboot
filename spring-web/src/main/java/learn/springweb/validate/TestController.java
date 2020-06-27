package learn.springweb.validate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author 陈濛
 * @date 2020/6/27 6:37 下午
 */
@Validated
@Slf4j
@RestController
public class TestController {

    //比较复杂的校验结果处理，不用这种
    @RequestMapping("/validate")
    public String valid(@Validated Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.toString());
            }
            return "fail";
        }
        return "success";
    }

    /*全局统一处理校验异常*/

    //get请求的平铺参数自动映射到person对象，产生BindException异常，类上注释@Validated没用
    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public String valid(@Validated Person person) {
        System.out.println(person);
        return "success";
    }

    //get请求的平铺参数，类上注释@Validated有用
    @RequestMapping(value = "/valid1", method = RequestMethod.GET)
    public String valid1(@NotEmpty @RequestParam("name") String name,
                         @Range(min = 0, max = 100, message = "age不能大于100小于0")
                         @RequestParam("age") int age) {
        return "success";
    }
}
