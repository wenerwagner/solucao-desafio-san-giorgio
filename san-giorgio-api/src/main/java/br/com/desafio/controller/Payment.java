package br.com.desafio.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("payment_items")
    private List<PaymentItem> paymentItems;
}
