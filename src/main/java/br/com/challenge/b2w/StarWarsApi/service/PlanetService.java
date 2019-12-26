package br.com.challenge.b2w.StarWarsApi.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.challenge.b2w.StarWarsApi.dto.SwapiDto;
import br.com.challenge.b2w.StarWarsApi.exceptionhandler.PlanetDoesNotExistsException;
import br.com.challenge.b2w.StarWarsApi.exceptionhandler.SwapiException;
import br.com.challenge.b2w.StarWarsApi.model.Planet;
import br.com.challenge.b2w.StarWarsApi.repository.PlanetRepository;
import br.com.challenge.b2w.StarWarsApi.utils.MessageUtil;

@Service
public class PlanetService implements IPlanetService {
	
	private static final Logger logger = LogManager.getLogger(PlanetService.class);
	
	@Autowired
	PlanetRepository planetRepository;
	
	@Autowired
	private MessageUtil message;

	@Override
	public List<Planet> findAll() {
		return planetRepository.findAll();
	}

	@Override
	public Planet save(Planet planet) {
		planet.setMoviesAppearances(getMoviesAppearancesFromSwapi(planet));
		return planetRepository.save(planet);
	}

	private int getMoviesAppearancesFromSwapi(Planet planet) {
		
		logger.info("Fechting data from SWAPI.");

		try {
			
			final String uri = "https://swapi.co/api/planets/?search="+planet.getName();
			
			RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
			
			ResponseEntity<SwapiDto> response = restTemplate.getForEntity(uri, SwapiDto.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				return response.getBody().getResults().get(0).getFilms().size();
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SwapiException(this.message.getMessage("message.error.fechting-data.swapi"));
		}
		return 0;
	}
	
	public void delete(String id) {
		planetRepository.deleteById(id);
	}
	
	@Override
	public Planet findById(String id) {
		Optional<Planet> planet = planetRepository.findById(id);
		return planet.orElseThrow(() -> new PlanetDoesNotExistsException(message.getMessage("menssage.error.planet.does-not-exists")));
	}
	

	@Override
	public Optional<List<Planet>> findByName(String name) {
		return planetRepository.findByNameIgnoreCaseContaining(name);
	}
}
