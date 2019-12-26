package br.com.challenge.b2w.StarWarsApi.exceptionhandler;

public class SwapiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SwapiException(String msg) {
        super(msg);
    }
}