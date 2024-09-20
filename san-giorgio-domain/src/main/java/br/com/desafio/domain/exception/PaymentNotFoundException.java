package br.com.desafio.domain.exception;

public class PaymentNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Payment with id [%s] not found";

    public PaymentNotFoundException(String paymentId) {
        super(String.format(ERROR_MESSAGE, paymentId));
    }
}
