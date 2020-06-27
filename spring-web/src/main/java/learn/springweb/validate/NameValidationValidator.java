package learn.springweb.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidationValidator implements ConstraintValidator<NameValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ("steven".equalsIgnoreCase(value)) {
            return true;
        }
        String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
        System.out.println("default message :" + defaultConstraintMessageTemplate);
        //禁用默认提示信息
        //context.disableDefaultConstraintViolation();
        //设置提示语
        //context.buildConstraintViolationWithTemplate("can not contains blank").addConstraintViolation();
        return false;
    }
}