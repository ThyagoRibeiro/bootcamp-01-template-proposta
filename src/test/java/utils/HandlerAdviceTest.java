package utils;

import br.com.thyagoribeiro.proposta.utils.ErroPadronizado;
import br.com.thyagoribeiro.proposta.utils.HandlerAdvice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class HandlerAdviceTest {

    HandlerAdvice handlerAdvice = new HandlerAdvice();

    @Test
    @DisplayName("Testa método handleMethodArgumentNotValidException da classe HandlerAdvice")
    void handleMethodArgumentNotValidExceptionTest(){

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(this, "handlerAdviceTest");
        bindingResult.addError(new FieldError("objeto", "campo", "mensagem"));

        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<ErroPadronizado> responseEntity = handlerAdvice.handleMethodArgumentNotValidException(methodArgumentNotValidException);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("Campo campo mensagem", ((ArrayList) responseEntity.getBody().getMensagens()).get(0));

    }

    @Test
    @DisplayName("Testa método handleResponseStatusException da classe HandlerAdvice")
    void handleResponseStatusExceptionTest(){

        ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<ErroPadronizado> responseEntity = handlerAdvice.handleResponseStatusException(responseStatusException);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
