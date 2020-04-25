package learn.springcommon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RestTemplateTests {

    @Test
    void test() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> values = new HashMap<>();
        values.put("param1", 1);
        values.put("param2", 2);
//        String url = RestTemplateHelper.getUrl("http://localhost:8080/get/params/" , values);
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/get/params/{}", Map.class, 20);
        //http状态的一些判断
        HttpStatus httpStatus = responseEntity.getStatusCode();
        //状态码
        int statusCodeValue = responseEntity.getStatusCodeValue();
        //header
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        //body
        Map body = responseEntity.getBody();
    }

    @Test
    void test1() {
        RestTemplate restTemplate = new RestTemplate();
        Map body = restTemplate.getForObject("http://localhost:8080/get/params?param1=11&param2=22", Map.class);
    }

    @Test
    void test2() {
        RestTemplate restTemplate = new RestTemplate();
        //构造headers
        HttpHeaders headers = new HttpHeaders();
        //content-type
        headers.setContentType(MediaType.APPLICATION_JSON);
        //构造body
        Map<String, Object> body = new HashMap<>();
        body.put("id", 1);
        body.put("userName", "陈濛");
        //构造request实体
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        String url = "http://localhost:8080/post/json";
        //post请求，并接收返回结果
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        System.out.println(response.getBody());
    }

}
