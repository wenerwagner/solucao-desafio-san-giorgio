package br.com.desafio.mapper;

import br.com.desafio.controller.Payment;
import br.com.desafio.controller.PaymentItem;
import br.com.desafio.domain.model.PaymentItemModel;
import br.com.desafio.domain.model.PaymentModel;

import java.util.List;

public class PaymentMapper {

    private PaymentMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PaymentModel toPaymentModel(Payment payment) {
        List<PaymentItemModel> paymentItems = payment.getPaymentItems().stream().map(paymentItem -> new PaymentItemModel(paymentItem.getPaymentId(), paymentItem.getPaymentValue(), paymentItem.getPaymentStatus())).toList();
        return new PaymentModel(payment.getClientId(), paymentItems);
    }

    public static Payment fromPaymentModel(PaymentModel paymentModel) {
        List<PaymentItem> paymentItems = paymentModel.getPaymentItems().stream().map(paymentItemModel -> new PaymentItem(paymentItemModel.getPaymentId(), paymentItemModel.getPaymentValue(), paymentItemModel.getPaymentStatus())).toList();
        return new Payment(paymentModel.getClientId(), paymentItems);
    }
}
