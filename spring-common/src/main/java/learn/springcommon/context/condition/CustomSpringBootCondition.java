package learn.springcommon.context.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

public class CustomSpringBootCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(
            ConditionContext context, AnnotatedTypeMetadata metadata) {
        //得到指定条件注解的属性值
        Map<String, Object> propertyMap = metadata.getAnnotationAttributes(CustomConditional.class.getName());
        Objects.requireNonNull(propertyMap);
        Object value = propertyMap.get("value");
        //该属性在environment是否存在
        if (value != null) {
            String property = context.getEnvironment().getProperty(value.toString());
            return property != null ?
                    new ConditionOutcome(true, value.toString() + "属性存在")
                    : new ConditionOutcome(false, value.toString() + "属性不存在");
        }
        return new ConditionOutcome(false, "注解参数为空");
    }
}
