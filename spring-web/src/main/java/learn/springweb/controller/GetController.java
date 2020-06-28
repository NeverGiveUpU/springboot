package learn.springweb.controller;

import learn.springweb.support.Result;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/get")
@RestController
public class GetController {
    @GetMapping("/params")
    public Result<Object> get(@RequestParam("param1") String param1,
                              @RequestParam("param2") String param2) {
        System.out.println(param1);
        System.out.println(param2);
        return Result.SUCCESS_RESULT;
    }

    @GetMapping("/params/required-false")
    public Result<Object> get1(@RequestParam("param1") String param1,
                               @RequestParam(value = "param2", required = false) String param2) {
        System.out.println(param1);
        System.out.println(param2);
        return Result.SUCCESS_RESULT;
    }

    @GetMapping("/params/default")
    public Result<Object> get2(@RequestParam("param1") String param1,
                               @RequestParam(value = "param2", defaultValue = "33") String param2) {
        System.out.println(param1);
        System.out.println(param2);
        return Result.SUCCESS_RESULT;
    }

    @GetMapping("/params/{path1}")
    public Result<Object> get3(@PathVariable String path1) {
        System.out.println(path1);
        return Result.SUCCESS_RESULT;
    }

    @GetMapping("/params/long")
    public Result<Object> get4() throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
        Thread.sleep(2000);
        return Result.SUCCESS_RESULT;
    }

    @GetMapping("/params/short")
    public Result<Object> get5() throws InterruptedException {
        System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
//        Thread.sleep(2000);
        return Result.SUCCESS_RESULT;
    }
}
