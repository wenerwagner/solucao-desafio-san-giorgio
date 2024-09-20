package br.com.desafio.domain.usecase.implementation;

import br.com.desafio.domain.adapter.PaymentQueue;
import br.com.desafio.domain.exception.ClientNotFoundException;
import br.com.desafio.domain.exception.PaymentNotFoundException;
import br.com.desafio.domain.model.PaymentItemModel;
import br.com.desafio.domain.model.PaymentModel;
import br.com.desafio.domain.repository.PaymentRepository;
import br.com.desafio.domain.usecase.ConfirmPaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmPaymentUseCaseImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentQueue paymentTotalQueue, paymentPartialQueue, paymentSurplusQueue;

    private ConfirmPaymentUseCase confirmPaymentUseCase;

    @BeforeEach
    void initService() {
        confirmPaymentUseCase = new ConfirmPaymentUseCaseImpl(paymentRepository, paymentTotalQueue, paymentPartialQueue, paymentSurplusQueue);
    }

    @Test
    void testConfirmPaymentThrowsExceptionIfClientIdDoesNotExist() {
        String clientId = "123";
        List<PaymentItemModel> paymentItems = new ArrayList<>();

        PaymentModel paymentData = new PaymentModel(clientId, paymentItems);

        when(paymentRepository.getPaymentByCliendId(clientId)).thenReturn(null);

        Exception exception = assertThrows(ClientNotFoundException.class, () -> confirmPaymentUseCase.confirm(paymentData));
        assertEquals(String.format("Client with id [%s] not found", clientId), exception.getMessage());
    }

    @Test
    void testConfirmPaymentThrowsExceptionIfPaymentIdDoesNotExist() {
        String paymentId = "456";
        String paymentStatus = null;
        BigDecimal paymentValue = new BigDecimal("100.00");
        List<PaymentItemModel> paymentItems = List.of(new PaymentItemModel(paymentId, paymentValue, paymentStatus));

        String clientId = "123";
        PaymentModel paymentData = new PaymentModel(clientId, paymentItems);

        when(paymentRepository.getPaymentByCliendId(clientId)).thenReturn(paymentData);
        when(paymentRepository.getPaymentItemByPaymentId(paymentId)).thenReturn(null);

        Exception exception = assertThrows(PaymentNotFoundException.class, () -> confirmPaymentUseCase.confirm(paymentData));
        assertEquals(String.format("Payment with id [%s] not found", paymentId), exception.getMessage());
    }

    @Test
    void testConfirmPaymentTotal() throws Exception {
        PaymentItemModel requestItem = new PaymentItemModel("item1", new BigDecimal("100.00"), null);
        PaymentModel request = new PaymentModel("123", List.of(requestItem));

        PaymentItemModel existingItem = new PaymentItemModel("item1", new BigDecimal("100.00"), null);
        PaymentModel existing = new PaymentModel("123", List.of(existingItem));

        when(paymentRepository.getPaymentByCliendId("123")).thenReturn(existing);
        when(paymentRepository.getPaymentItemByPaymentId("item1")).thenReturn(existingItem);

        PaymentModel payment = confirmPaymentUseCase.confirm(request);

        assertEquals("TOTAL", payment.getPaymentItems().getFirst().getPaymentStatus());
        verify(paymentTotalQueue).sendMessage(requestItem);
    }

    @Test
    void testConfirmPaymentPartial() throws Exception {
        PaymentItemModel requestItem = new PaymentItemModel("item1", new BigDecimal("50.00"), null);
        PaymentModel request = new PaymentModel("123", List.of(requestItem));

        PaymentItemModel existingItem = new PaymentItemModel("item1", new BigDecimal("100.00"), null);
        PaymentModel existing = new PaymentModel("123", List.of(existingItem));

        when(paymentRepository.getPaymentByCliendId("123")).thenReturn(existing);
        when(paymentRepository.getPaymentItemByPaymentId("item1")).thenReturn(existingItem);

        PaymentModel payment = confirmPaymentUseCase.confirm(request);
        assertEquals("PARTIAL", payment.getPaymentItems().getFirst().getPaymentStatus());
        verify(paymentPartialQueue).sendMessage(requestItem);
    }

    @Test
    void testConfirmPaymentSurplus() throws Exception {
        PaymentItemModel requestItem = new PaymentItemModel("item1", new BigDecimal("150.00"), null);
        PaymentModel request = new PaymentModel("123", List.of(requestItem));

        PaymentItemModel existingItem = new PaymentItemModel("item1", new BigDecimal("100.00"), null);
        PaymentModel existing = new PaymentModel("123", List.of(existingItem));

        when(paymentRepository.getPaymentByCliendId("123")).thenReturn(existing);
        when(paymentRepository.getPaymentItemByPaymentId("item1")).thenReturn(existingItem);

        PaymentModel payment = confirmPaymentUseCase.confirm(request);
        assertEquals("SURPLUS", payment.getPaymentItems().getFirst().getPaymentStatus());
        verify(paymentSurplusQueue).sendMessage(requestItem);
    }
}