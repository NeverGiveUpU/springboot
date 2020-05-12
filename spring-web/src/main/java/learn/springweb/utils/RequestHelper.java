package learn.springweb.utils;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * HttpServletRequest工具类
 */
public class RequestHelper {
    /**
     * 读request body
     * @param request
     * @return
     * @throws IOException
     */
    public static String readBodyByReader(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String readBodyByInputStream(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = request.getInputStream()) {
            byte[] bytes = new byte[4096];
            for (int n; (n = is.read(bytes)) != -1;) {
                sb.append(new String(bytes, 0, n));
            }
        }
        return sb.toString();
    }

    public static byte[] readBody(HttpServletRequest request) throws IOException {
        int len = request.getContentLength();
        byte[] bytes = new byte[len];
        try (InputStream is = request.getInputStream()) {
            is.read(bytes, 0, len);
        }
        return bytes;
    }

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};

    public static String getClientIPAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
