package learn.springweb.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = NameValidationValidator.class)
public @interface NameValidation {
    String message() default "不是合法的名字";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    @Target({PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NameValidation[] value();
    }
}