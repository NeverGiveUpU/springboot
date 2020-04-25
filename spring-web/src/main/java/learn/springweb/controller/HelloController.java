package learn.springweb.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String get() {
        return "get hello";
    }
    @PostMapping("/hello")
    public String post() {
        return "post hello";
    }
    @RequestMapping(value = "/hi", method = {RequestMethod.GET, RequestMethod.POST})
    public String hi() {
        return "hi";
    }
}
