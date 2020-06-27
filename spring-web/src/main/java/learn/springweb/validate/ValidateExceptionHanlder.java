package learn.springweb.validate;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

@Order(1)
@Slf4j
@RestControllerAdvice
public class ValidateExceptionHanlder {
    //get方法参数自动映射到javaBean，校验错误产生BindException
    @ExceptionHandler(BindException.class)
    public String handleBindException(HttpServletRequest request, BindException exception) {
        List<FieldError> allErrors = exception.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError errorMessage : allErrors) {
            sb.append(errorMessage.getField()).append(": ").append(errorMessage.getDefaultMessage()).append(", ");
        }
        log.error(sb.toString());
        return sb.toString();
    }

    //对get方法传递平铺参数进行校验，校验失败产生ConstraintViolationException
    //ConstraintViolationException 是 ValidationException的子类
    @ExceptionHandler(ValidationException.class)
    public Object badArgumentHandler(ValidationException e) {
        log.error(e.getMessage(), e);
        StringBuilder sb = new StringBuilder();
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                String message = ((PathImpl) item.getPropertyPath()).getLeafNode().getName() + item.getMessage();
                sb.append(message).append("\n");
            }
        }
        return sb.toString();
    }
}