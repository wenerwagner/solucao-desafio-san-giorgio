package br.com.desafio.domain.usecase.implementation;

import br.com.desafio.domain.adapter.PaymentQueue;
import br.com.desafio.domain.exception.ClientNotFoundException;
import br.com.desafio.domain.exception.PaymentNotFoundException;
import br.com.desafio.domain.repository.PaymentRepository;
import br.com.desafio.domain.model.PaymentItemModel;
import br.com.desafio.domain.model.PaymentModel;
import br.com.desafio.domain.usecase.ConfirmPaymentUseCase;

import java.math.BigDecimal;
import java.util.List;

public class ConfirmPaymentUseCaseImpl implements ConfirmPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentQueue paymentTotalQueue;
    private final PaymentQueue paymentPartialQueue;
    private final PaymentQueue paymentSurplusQueue;

    public ConfirmPaymentUseCaseImpl(PaymentRepository paymentRepository, PaymentQueue paymentTotalQueue, PaymentQueue paymentPartialQueue, PaymentQueue paymentSurplusQueue) {
        this.paymentRepository = paymentRepository;
        this.paymentTotalQueue = paymentTotalQueue;
        this.paymentPartialQueue = paymentPartialQueue;
        this.paymentSurplusQueue = paymentSurplusQueue;
    }

    @Override
    public PaymentModel confirm(PaymentModel paymentData) throws ClientNotFoundException, PaymentNotFoundException {

        String clientIdData = paymentData.getClientId();

        PaymentModel payment = paymentRepository.getPaymentByCliendId(clientIdData);
        if (payment == null) {
            throw new ClientNotFoundException(clientIdData);
        }

        List<PaymentItemModel> paymentItems = payment.getPaymentItems();
        List<String> paymentItemsIds = paymentItems.stream().map(PaymentItemModel::getPaymentId).toList();

        for (PaymentItemModel paymentItemData: paymentData.getPaymentItems()) {
            String paymentIdData = paymentItemData.getPaymentId();
            BigDecimal paymentValueData = paymentItemData.getPaymentValue();

            PaymentItemModel paymentItem = paymentRepository.getPaymentItemByPaymentId(paymentIdData);
            if (paymentItem == null || !paymentItemsIds.contains(paymentIdData)) {
                throw new PaymentNotFoundException(paymentIdData);
            }

            if(paymentItem.getPaymentValue().compareTo(paymentValueData) == 0) {
                paymentItemData.setPaymentStatus("TOTAL");
                paymentTotalQueue.sendMessage(paymentItemData);
            } else if (paymentItem.getPaymentValue().compareTo(paymentValueData) > 0) {
                paymentItemData.setPaymentStatus("PARTIAL");
                paymentPartialQueue.sendMessage(paymentItemData);
            } else {
                paymentItemData.setPaymentStatus("SURPLUS");
                paymentSurplusQueue.sendMessage(paymentItemData);
            }
        }

        return paymentData;
    }
}
