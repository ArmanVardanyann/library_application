package common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MailFormatValidator.class})
@Documented
public @interface ValidEmail {
    String message() default "invalid.email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}