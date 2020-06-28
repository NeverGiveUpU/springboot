package learn.springweb.repeatable;

import learn.springweb.utils.RequestHelper;
import lombok.Getter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * HttpServletRequest的包装类。
 *
 * 功能：
 * - 拦截器可以查看body内容，且不影响contoller获取body数据
 * - 多次查看body内容，getInputStream方法可以多次调用。
 *
 * 相关：
 * 在filter里面进行request的包装。
 * @see RequestFilter
 *
 */
public class RepeatableReadRequest extends HttpServletRequestWrapper {

    @Getter
    private final byte[] body;
    @Getter
    private String bodyStr;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public RepeatableReadRequest(HttpServletRequest request) throws IOException {
        super(request);
        bodyStr = RequestHelper.readBodyByInputStream(request);
        body = bodyStr.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 原始方法只能读取一次（流只能读一次），@ResponseBody自动绑定调用该方法读取body。
     * 所以重写该方法，变成可重复读。
     * 具体实现是构造并返回新的流。
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayIs = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayIs.read();
            }
        };
    }

}
