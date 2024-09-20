package br.com.desafio.domain.usecase;


import br.com.desafio.domain.exception.ClientNotFoundException;
import br.com.desafio.domain.exception.PaymentNotFoundException;
import br.com.desafio.domain.model.PaymentModel;

public interface ConfirmPaymentUseCase {
    PaymentModel confirm(PaymentModel paymentModel) throws ClientNotFoundException, PaymentNotFoundException;
}
