package br.com.challenge.b2w.StarWarsApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.challenge.b2w.StarWarsApi.dto.SwapiDto;
import br.com.challenge.b2w.StarWarsApi.model.Planet;
import br.com.challenge.b2w.StarWarsApi.repository.PlanetRepository;

@Service
public class PlanetService implements IPlanetService {
	
	@Autowired
	PlanetRepository planetRepository;

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
		final String uri = "https://swapi.co/api/planets/?search="+planet.getName();

		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

		ResponseEntity<SwapiDto> response = restTemplate.getForEntity(uri, SwapiDto.class);
		
		return response.getBody().getResults().get(0).getFilms().size();
	}
	
	public void delete(String id) {
		planetRepository.deleteById(id);
	}
	
	@Override
	public Planet findById(String id) {
		return planetRepository.findById(id).get();
	}
	

	@Override
	public Planet findByName(String name) {
		return planetRepository.findByName(name).get();
	}
}
