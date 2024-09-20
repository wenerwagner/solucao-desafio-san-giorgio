package br.com.desafio.exception;

import br.com.desafio.domain.exception.ClientNotFoundException;
import br.com.desafio.domain.exception.PaymentNotFoundException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GENERIC_ERROR_MESSAGE = "Something wrong happened";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorMessage> handleAllExceptions(Exception ex) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value(), GENERIC_ERROR_MESSAGE);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleClientNotFoundException(ClientNotFoundException ex) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        CustomErrorMessage errorMessage = new CustomErrorMessage(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ProblemDetail problemDetail = (ProblemDetail) body;
        CustomErrorMessage newBody = new CustomErrorMessage(problemDetail.getTitle(), problemDetail.getStatus(), problemDetail.getDetail());
        return super.handleExceptionInternal(ex, newBody, headers, statusCode, request);
    }
}
