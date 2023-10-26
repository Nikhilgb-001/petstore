package com.javaproject1.petstore.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class MobileValidator implements ConstraintValidator<Mobile, String> {
    
    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {

        boolean condition1 = arg0.length() == 10;
        // return true;

        boolean condition2 = arg0.matches("[0-9]+");

        boolean result =  condition1 && condition2;
        return result;
    }
}
