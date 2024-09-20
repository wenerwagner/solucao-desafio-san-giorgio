package br.com.desafio.domain.model;

import java.math.BigDecimal;

public class PaymentItemModel {
    private String paymentId;
    private BigDecimal paymentValue;
    private String paymentStatus;

    public PaymentItemModel(String paymentId, BigDecimal paymentValue, String paymentStatus) {
        this.paymentId = paymentId;
        this.paymentValue = paymentValue;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(BigDecimal paymentValue) {
        this.paymentValue = paymentValue;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentItemModel{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentValue=" + paymentValue +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
