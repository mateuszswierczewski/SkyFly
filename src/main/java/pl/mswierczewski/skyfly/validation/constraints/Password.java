package pl.mswierczewski.skyfly.validation.constraints;

import pl.mswierczewski.skyfly.validation.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ METHOD, FIELD})
@Retention(RUNTIME)
public @interface Password {

    String message() default "{pl.mswierczewski.skyfly.validation.constraints.Password.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
