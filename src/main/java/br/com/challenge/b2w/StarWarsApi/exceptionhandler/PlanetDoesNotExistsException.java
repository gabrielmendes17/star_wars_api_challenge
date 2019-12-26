package br.com.challenge.b2w.StarWarsApi.exceptionhandler;

public class PlanetDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlanetDoesNotExistsException(String msg) {
		super(msg);
	}
}


