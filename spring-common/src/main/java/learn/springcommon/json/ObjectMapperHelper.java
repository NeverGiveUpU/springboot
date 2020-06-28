package learn.springcommon.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class ObjectMapperHelper {

    /**
     * 工厂方法，得到自定义ObjectMapper
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper
                //序列化 去除默认值
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                //下划线风格
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                //反序列化 遇到未知属性忽略
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                //允许单引号出现
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        //字段保留, null值转为""
        objectMapper
                .getSerializerProvider()
                .setNullValueSerializer(new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                        gen.writeString("");
                    }
                });
        return objectMapper;
    }
}
