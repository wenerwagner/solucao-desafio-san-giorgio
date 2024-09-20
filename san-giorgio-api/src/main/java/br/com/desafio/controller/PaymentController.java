package br.com.desafio.controller;

import br.com.desafio.mapper.PaymentMapper;
import br.com.desafio.domain.model.PaymentModel;
import br.com.desafio.domain.usecase.ConfirmPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final ConfirmPaymentUseCase confirmPaymentUseCase;

    @PutMapping(path = "/api/payment")
    public ResponseEntity<Payment> setPayment(@RequestBody Payment request) throws Exception {
        PaymentModel paymentModel = confirmPaymentUseCase.confirm(PaymentMapper.toPaymentModel(request));
        Payment response = PaymentMapper.fromPaymentModel(paymentModel);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
