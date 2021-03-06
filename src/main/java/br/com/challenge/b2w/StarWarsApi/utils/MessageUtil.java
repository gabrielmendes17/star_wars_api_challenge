package br.com.challenge.b2w.StarWarsApi.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageUtil {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String msg, String... values) {
		Locale.setDefault(new Locale("pt", "BR"));
		return messageSource.getMessage(msg, values, Locale.getDefault());
	}
}