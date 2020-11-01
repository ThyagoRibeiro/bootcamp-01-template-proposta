package br.com.thyagoribeiro.proposta.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Payload;
import java.lang.annotation.Annotation;

public class DocumentValidatorTest {

    @Test
    @DisplayName("Testa validação de documentos CPF e CNPJ")
    void validateCPFTest() {
        DocumentValidator documentValidator = new DocumentValidator();
        documentValidator.initialize(new DocumentTest());
        Assertions.assertTrue(documentValidator.isValid("338.313.130-08", null));
        Assertions.assertFalse(documentValidator.isValid("338.313.130-00", null));
        Assertions.assertTrue(documentValidator.isValid("18.981.081/0001-13", null));
        Assertions.assertFalse(documentValidator.isValid("18.981.081/0001-10", null));
    }

    private class DocumentTest implements Document {

        @Override
        public String message() {
            return "Test Message";
        }

        @Override
        public Class<?>[] groups() {
            return new Class[]{};
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return new Class[]{};
        }

        @Override
        public DocumentType[] documentTypes() {
            return new DocumentType[]{DocumentType.CPF, DocumentType.CNPJ};
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Document.class;
        }
    }


}
