package learn.springweb.ordered_filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈濛
 * @date 2020/6/28 4:37 下午
 */

/**
 * 测试可定制顺序的过滤器
 */
@Slf4j
@RestController("orderdFilterTestController")
@RequestMapping("/ordered_filter")
public class TestController {
    @GetMapping("")
    public String get() {
        return "orderd_filter";
    }
}
