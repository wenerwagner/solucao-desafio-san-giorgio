package br.com.desafio.domain.model;

import java.util.List;

public class PaymentModel {

    private String clientId;
    private List<PaymentItemModel> paymentItems;

    public PaymentModel(String clientId, List<PaymentItemModel> paymentItems) {
        this.clientId = clientId;
        this.paymentItems = paymentItems;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<PaymentItemModel> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<PaymentItemModel> paymentItems) {
        this.paymentItems = paymentItems;
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "clientId='" + clientId + '\'' +
                ", paymentItems=" + paymentItems +
                '}';
    }
}
