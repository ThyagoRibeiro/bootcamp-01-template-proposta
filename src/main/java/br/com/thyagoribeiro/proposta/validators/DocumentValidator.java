package br.com.thyagoribeiro.proposta.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

// CDD - total 3

public class DocumentValidator implements ConstraintValidator<Document, String> {

    List<DocumentType> documentTypes; // CDD 1 - ENUM DocumentType

    @Override
    public void initialize(Document constraintAnnotation) {
        documentTypes = Arrays.asList(constraintAnnotation.documentTypes());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        for(DocumentType documentType : documentTypes) { // CDD 1 - branch for
            ConstraintValidator validator = documentType.getConstraintValidator();
            validator.initialize(null);

            if(validator.isValid(value, null)) // CDD 1 - branch if
                return true;
        }

        return false;
    }
}
