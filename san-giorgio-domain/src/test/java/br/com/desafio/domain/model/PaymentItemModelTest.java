package br.com.desafio.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentItemModelTest {

    @Test
    void testConstructorAndGetters() {
        String expectedId = "12345";
        BigDecimal expectedValue = new BigDecimal("99.99");
        String expectedStatus = "COMPLETED";

        PaymentItemModel paymentItem = new PaymentItemModel(expectedId, expectedValue, expectedStatus);

        assertEquals(expectedId, paymentItem.getPaymentId());
        assertEquals(expectedValue, paymentItem.getPaymentValue());
        assertEquals(expectedStatus, paymentItem.getPaymentStatus());
    }

    @Test
    void testSetters() {
        PaymentItemModel paymentItem = new PaymentItemModel("1", BigDecimal.ZERO, "PENDING");

        paymentItem.setPaymentId("54321");
        paymentItem.setPaymentValue(new BigDecimal("49.99"));
        paymentItem.setPaymentStatus("FAILED");

        assertEquals("54321", paymentItem.getPaymentId());
        assertEquals(new BigDecimal("49.99"), paymentItem.getPaymentValue());
        assertEquals("FAILED", paymentItem.getPaymentStatus());
    }

    @Test
    void testToString() {
        PaymentItemModel paymentItem = new PaymentItemModel("1", BigDecimal.TEN, "COMPLETED");

        String result = paymentItem.toString();

        String expectedString = "PaymentItemModel{paymentId='1', paymentValue=10, paymentStatus='COMPLETED'}";
        assertEquals(expectedString, result);
    }
}