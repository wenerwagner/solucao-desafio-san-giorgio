package br.com.desafio.config;

import br.com.desafio.domain.adapter.PaymentQueue;
import br.com.desafio.domain.repository.PaymentRepository;
import br.com.desafio.domain.usecase.ConfirmPaymentUseCase;
import br.com.desafio.domain.usecase.implementation.ConfirmPaymentUseCaseImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UseCasesConfig {

    private PaymentRepository paymentRepository;
    private PaymentQueue paymentQueue;

    @Bean
    public ConfirmPaymentUseCase confirmPaymentUseCase() {
        return new ConfirmPaymentUseCaseImpl(paymentRepository, paymentQueue, paymentQueue, paymentQueue);
    }
}
