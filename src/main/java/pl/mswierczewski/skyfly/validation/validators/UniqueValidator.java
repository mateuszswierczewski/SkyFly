package pl.mswierczewski.skyfly.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import pl.mswierczewski.skyfly.services.common.ValueExists;
import pl.mswierczewski.skyfly.validation.constraints.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private ApplicationContext context;

    private ValueExists service;
    private String columnName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.service = context.getBean(constraintAnnotation.serviceClass());
        this.columnName = constraintAnnotation.columnName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return !service.isValueExists(value, columnName);
    }
}
