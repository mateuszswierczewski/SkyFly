package pl.mswierczewski.skyfly.validation.constraints;

import pl.mswierczewski.skyfly.services.common.ValueExists;
import pl.mswierczewski.skyfly.validation.validators.PasswordValidator;
import pl.mswierczewski.skyfly.validation.validators.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ METHOD, FIELD})
@Retention(RUNTIME)
public @interface Unique {
    String message() default "{pl.mswierczewski.skyfly.validation.constraints.Unique.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends ValueExists> serviceClass();
    String columnName();
}
