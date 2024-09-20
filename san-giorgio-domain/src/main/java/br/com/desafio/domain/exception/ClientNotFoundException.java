package br.com.desafio.domain.exception;

public class ClientNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Client with id [%s] not found";

    public ClientNotFoundException(String clientId) {
        super(String.format(ERROR_MESSAGE, clientId));
    }
}
