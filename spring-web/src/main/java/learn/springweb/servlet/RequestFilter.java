package learn.springweb.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 对request进行包装的Filter
 */
@Slf4j
@WebFilter(filterName = "httpServletRequestWrapperFilter",
        urlPatterns = {"/filter/**"},
        asyncSupported = true)
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest wrappedRequest = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            if (httpServletRequest.getMethod().equals("POST")) {
                //将request包装成可重复读的request
                wrappedRequest = new RepeatableReadRequest(httpServletRequest);
            }
        }
        if (wrappedRequest == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(wrappedRequest, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
