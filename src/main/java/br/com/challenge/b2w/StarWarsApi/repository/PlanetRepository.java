package br.com.challenge.b2w.StarWarsApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.challenge.b2w.StarWarsApi.model.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String>{
	Optional<List<Planet>> findByNameIgnoreCaseContaining(String name);
}
