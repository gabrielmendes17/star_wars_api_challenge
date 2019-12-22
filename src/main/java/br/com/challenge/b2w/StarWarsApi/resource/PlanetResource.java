package br.com.challenge.b2w.StarWarsApi.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class PlanetResource {
	
	@GetMapping(path = "/planets")
	public String listPlanets() {
		final String uri = "https://swapi.co/api/planets/";
		
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		}

	    System.out.println(response);
	    
	    return "";
	}

}
