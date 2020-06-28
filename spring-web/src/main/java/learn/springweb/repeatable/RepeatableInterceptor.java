package learn.springweb.repeatable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器，读取包装后的request
 */
@Slf4j
@Component
public class RepeatableInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        handleRequestBody(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void handleRequestBody(HttpServletRequest request) {
        //如果是可重复读的request，处理requestBody
        if (request instanceof RepeatableReadRequest) {
            log.info(((RepeatableReadRequest) request).getBodyStr());
        }
    }
}
