package br.com.desafio.database;

import br.com.desafio.domain.repository.PaymentRepository;
import br.com.desafio.domain.model.PaymentItemModel;
import br.com.desafio.domain.model.PaymentModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FakeDatabase implements PaymentRepository {

    private static final String WAITING = "WAITING";

    List<PaymentModel> database = List.of(
            new PaymentModel("1", List.of(
                    new PaymentItemModel("1", BigDecimal.TEN, WAITING),
                    new PaymentItemModel("2", BigDecimal.TWO, WAITING),
                    new PaymentItemModel("3", BigDecimal.ONE, WAITING))
            ),
            new PaymentModel("2", List.of(
                    new PaymentItemModel("4", BigDecimal.TEN, WAITING),
                    new PaymentItemModel("5", BigDecimal.TWO, WAITING),
                    new PaymentItemModel("6", BigDecimal.ONE, WAITING))
            )
    );

    @Override
    public PaymentModel getPaymentByCliendId(String clientId) {
        for (PaymentModel paymentModel : database) {
            if (paymentModel.getClientId().equals(clientId)) {
                return paymentModel;
            }
        }
        return null;
    }

    @Override
    public PaymentItemModel getPaymentItemByPaymentId(String paymentId) {
        for (PaymentModel paymentModel : database) {
            for (PaymentItemModel paymentItemModel : paymentModel.getPaymentItems()) {
                if (paymentItemModel.getPaymentId().equals(paymentId)) {
                    return paymentItemModel;
                }
            }
        }
        return null;
    }
}
