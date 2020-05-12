package learn.springweb.http;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class RestTemplateHelper {
    public static String getUrl(String url, Map<String, Object> map) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        map.forEach((k, v) -> {
            if (v instanceof List) {
                builder.queryParam(k, (List) v);
            } else {
                builder.queryParam(k, v);
            }
        });
        return builder.build().encode().toString();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("param1", "gg");
        List<Object> param2 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        map.put("param2", param2);
        String url = getUrl("http://localhost:8080", map);
        System.out.println(url);
    }
}
