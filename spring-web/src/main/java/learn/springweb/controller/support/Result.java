package learn.springweb.controller.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code = 200;
    private String message;
    private T data;

    public Result(Result<T> result) {
        this.code = result.getCode();
        this.message = result.getMessage();
        this.data = result.getData();
    }

    public static Result<Object> getFailResult(String message) {
        Result<Object> result = new Result<>(FAIL_RESULT);
        result.setMessage(message);
        return result;
    }

    public static final Result<Object> SUCCESS_RESULT = new Result<>(200, "success", null);
    public static final Result<Object> FAIL_RESULT = new Result<>(400, "fail", null);
}
