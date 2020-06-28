package learn.springweb.session;

import learn.springweb.utils.RequestHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器，为当前线程的SessionContext赋值
 */
@WebFilter(filterName = "sessionFilter", urlPatterns = {"/session/*"})
public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        beforeFilter(httpServletRequest);
        try {
            chain.doFilter(request, response);
        } finally {
            afterFilter();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private void beforeFilter(HttpServletRequest request) {
        //记录request中的一些信息到SessionContext
        SessionInfo sessionInfo = SessionContext.getSessionInfo();
        sessionInfo.setClientIp(RequestHelper.getClientIPAddress(request));
    }

    private void afterFilter() {
        //清除当前线程的SessionInfo信息
        SessionContext.clear();
    }
}
