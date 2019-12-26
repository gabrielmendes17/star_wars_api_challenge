package br.com.challenge.b2w.StarWarsApi.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private int status;
    private String detail;
    private String developerMessage;
}
