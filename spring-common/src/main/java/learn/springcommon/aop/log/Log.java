package learn.springcommon.aop.log;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE}) //方法、类注解
@Retention(RetentionPolicy.RUNTIME) //运行时可见
@Inherited //类上的注解可以被继承
public @interface Log {
    String operateType() default "默认操作";// 记录日志的操作类型
}
