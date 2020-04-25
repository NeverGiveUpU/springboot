package learn.springweb.controller.support;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Object> exception(Exception e) {
        System.out.println("Exception: " + e.getMessage());
        return Result.getFailResult(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result<Object> nullPointerException(NullPointerException e) {
        System.out.println("NullPointerException: " + e.getMessage());
        return Result.getFailResult(e.getMessage());
    }
}
