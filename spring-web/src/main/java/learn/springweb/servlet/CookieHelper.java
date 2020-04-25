package learn.springweb.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 */
public class CookieHelper {

    /**
     * request中获取指定名称的Cookie值
     * @param httpServletRequest
     * @param cookieKey
     * @return
     */
    public static Cookie getCookie(HttpServletRequest httpServletRequest, String cookieKey) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieKey)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * Response中添加Cookie
     * @param httpServletResponse
     * @param cookieKey cookie名
     * @param cookieValue cookie值
     * @param path cookie可见范围
     */
    public static void addCookie(HttpServletResponse httpServletResponse,
                                 String cookieKey,
                                 String cookieValue,
                                 String path) {
        Cookie cookie = new Cookie(cookieKey, cookieValue);
        cookie.setPath(path);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 添加带时间限制的Cookie
     * @param httpServletResponse
     * @param cookieKey
     * @param cookieValue
     * @param path
     * @param maxAge
     */
    public static void addCookieWithMaxAge(HttpServletResponse httpServletResponse,
                                           String cookieKey,
                                           String cookieValue,
                                           String path,
                                           int maxAge) {
        Cookie cookie = new Cookie(cookieKey, cookieValue);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * response删除cookie。做法是将Cookie的有效期设为0
     * @param request
     * @param response
     * @param cookieKey
     */
    public static void removeCookie(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String cookieKey) {

        Cookie cookie = getCookie(request, cookieKey);
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setMaxAge(0);
            cookie.setValue("");
            response.addCookie(cookie);
        }
    }
}
