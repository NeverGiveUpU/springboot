package learn.springweb.controller.async;

import lombok.Data;
import org.springframework.web.context.request.async.DeferredResult;

@Data
public class AsyncVO<I, O> {
    //请求参数
    private I params;
    //响应结果
    private DeferredResult<O> result;
}
