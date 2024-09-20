package br.com.desafio.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentModelTest {

    @Test
    void testConstructorAndGetters() {
        String expectedClientId = "client123";
        List<PaymentItemModel> expectedItems = new ArrayList<>();
        expectedItems.add(new PaymentItemModel("pay1", new BigDecimal("99.99"), "COMPLETED"));
        expectedItems.add(new PaymentItemModel("pay2", new BigDecimal("49.99"), "PENDING"));

        PaymentModel payment = new PaymentModel(expectedClientId, expectedItems);

        assertEquals(expectedClientId, payment.getClientId());
        assertEquals(expectedItems, payment.getPaymentItems());
    }

    @Test
    void testSetters() {
        PaymentModel payment = new PaymentModel("client1", new ArrayList<>());

        payment.setClientId("client456");
        List<PaymentItemModel> newItems = new ArrayList<>();
        newItems.add(new PaymentItemModel("pay3", new BigDecimal("29.99"), "FAILED"));
        payment.setPaymentItems(newItems);

        assertEquals("client456", payment.getClientId());
        assertEquals(newItems, payment.getPaymentItems());
    }

    @Test
    void testToString() {
        List<PaymentItemModel> items = new ArrayList<>();
        items.add(new PaymentItemModel("pay1", BigDecimal.TEN, "COMPLETED"));
        PaymentModel payment = new PaymentModel("client1", items);

        String result = payment.toString();

        String expectedString = "PaymentModel{clientId='client1', paymentItems=" + items + '}';
        assertEquals(expectedString, result);
    }
}