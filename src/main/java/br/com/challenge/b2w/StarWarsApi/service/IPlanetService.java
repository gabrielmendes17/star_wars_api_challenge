package br.com.challenge.b2w.StarWarsApi.service;

import java.util.List;

import br.com.challenge.b2w.StarWarsApi.model.Planet;

public interface IPlanetService {
	
	List<Planet> findAll();

	Planet save(Planet planet);

	void delete(String id);

	Planet findById(String id);

	Planet findByName(String name);

}
