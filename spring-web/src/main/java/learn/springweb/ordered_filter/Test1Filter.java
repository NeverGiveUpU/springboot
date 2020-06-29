package learn.springweb.ordered_filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义过滤器
 */
@Slf4j
public class Test1Filter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("高优先级过滤器拦截url：" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
