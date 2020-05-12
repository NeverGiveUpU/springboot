package learn.springweb.controller;

import learn.springweb.controller.support.Result;
import learn.springweb.controller.model.UserInfo;
import learn.springweb.utils.RequestHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/post")
public class PostController {


    /**
     * 接收json数据
     * @param userInfo
     * @return
     */
    @PostMapping("/json")
    public Result<Object> json(@RequestBody UserInfo userInfo) {
        Result<Object> result = new Result<>(Result.SUCCESS_RESULT);
        result.setData(userInfo);
        return result;
    }

    /**
     * 接收request数据
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/json/origin")
    public Result<Object> jsonOrigin(HttpServletRequest request) throws Exception {
        Result<Object> result = new Result<>(Result.SUCCESS_RESULT);
        String bodyStr = RequestHelper.readBodyByInputStream(request);
        result.setData(bodyStr);
        return result;
    }

    /**
     * 接收表单
     * @param request
     * @return
     */
    @PostMapping("/form-data")
    public Result<Object> formData(MultipartHttpServletRequest request) {
        String text = request.getParameter("textKey");
        MultipartFile file = request.getFile("fileKey");

        Objects.requireNonNull(file, "文件为空");

        System.out.println(text);
        System.out.println(file.getOriginalFilename());
        return Result.SUCCESS_RESULT;
    }
}
