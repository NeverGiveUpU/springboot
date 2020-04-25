package learn.springweb.controller.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyAsyncHandlerInterceptor implements AsyncHandlerInterceptor {

    //Controller return后开始执行（还是controller线程）
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request,
                                               HttpServletResponse response,
                                               Object handler) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        return true;
    }

    /**
     * Callable返回后执行
     * WebAsyncTask返回后执行
     * DeferredResult.setResult()后执行
     * 在这里返回response
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info(Thread.currentThread().getName()+ "服务调用完成，返回结果给客户端");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        if (ex != null) {
            log.info("发生异常:" + ex.getMessage());
        }
    }
}
