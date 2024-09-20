package br.com.desafio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomErrorMessage {
    private String title;
    private Integer status;
    private String message;
}
