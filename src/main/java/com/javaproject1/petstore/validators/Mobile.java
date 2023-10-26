package com.javaproject1.petstore.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MobileValidator.class)
@Target(ElementType.FIELD) 
//this annotation can be applied on the attributes of the class
@Retention(RetentionPolicy.RUNTIME)
public @interface Mobile {
    String message() default "Invalid Mobile Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
