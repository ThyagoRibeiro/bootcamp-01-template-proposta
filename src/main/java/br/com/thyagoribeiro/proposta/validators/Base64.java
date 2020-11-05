package br.com.thyagoribeiro.proposta.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {Base64Validator.class}) // CDD 1 - Classe DocumentValidator
public @interface Base64 {

    String message() default "não está no formato Base64";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
