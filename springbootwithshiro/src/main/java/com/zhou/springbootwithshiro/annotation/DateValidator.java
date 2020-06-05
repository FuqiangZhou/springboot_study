package com.zhou.springbootwithshiro.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019-08-02 12:36
 */
public class DateValidator implements ConstraintValidator<CheckDateFormat, String> {

    private CheckDateFormat checkDateFormat;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        System.out.println("===========> initialize");
        this.checkDateFormat = constraintAnnotation;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("===========> isValid");
        if (s == null){
            return false;
        }
        String format = checkDateFormat.format();
        if (s.length() != format.length()){
            return false;
        }
        try {
            new SimpleDateFormat(format).parse(s);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
