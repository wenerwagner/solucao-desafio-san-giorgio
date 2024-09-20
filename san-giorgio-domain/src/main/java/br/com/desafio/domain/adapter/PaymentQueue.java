package br.com.desafio.domain.adapter;

import br.com.desafio.domain.model.PaymentItemModel;

public interface PaymentQueue {
    void sendMessage(PaymentItemModel paymentItemModel);
}
