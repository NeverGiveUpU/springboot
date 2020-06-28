package learn.springweb.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Object> exception(Exception e) {
        log.error("Exception: " + e.getMessage(), e);
        return Result.getFailResult(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result<Object> nullPointerException(NullPointerException e) {
        log.error("NullPointerException: " + e.getMessage());
        return Result.getFailResult(e.getMessage());
    }
}
