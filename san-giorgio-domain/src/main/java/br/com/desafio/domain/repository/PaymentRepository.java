package br.com.desafio.domain.repository;

import br.com.desafio.domain.model.PaymentItemModel;
import br.com.desafio.domain.model.PaymentModel;

public interface PaymentRepository {
    PaymentModel getPaymentByCliendId(String clientId);

    PaymentItemModel getPaymentItemByPaymentId(String paymentId);
}
