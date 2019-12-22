package br.com.challenge.b2w.StarWarsApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return planetRepository.save(planet);
	}
	
	public void delete(String id) {
		planetRepository.deleteById(id);
	}
	
	@Override
	public Planet findById(String id) {
		return exists(planetRepository.findById(id));
	}
	

	@Override
	public Planet findByName(String name) {
		return exists(planetRepository.findByName(name));
	}

	private Planet exists(Optional<Planet> planeta) {
		return planeta.orElseThrow(() -> new IllegalArgumentException("erro"));
	}
}
