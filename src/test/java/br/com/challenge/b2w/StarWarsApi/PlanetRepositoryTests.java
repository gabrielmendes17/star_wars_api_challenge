package br.com.challenge.b2w.StarWarsApi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.challenge.b2w.StarWarsApi.model.Planet;
import br.com.challenge.b2w.StarWarsApi.repository.PlanetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetRepositoryTests {
	
	@Autowired
	private PlanetRepository planetRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void clearBeforeInit() {
		clearDb();
	}
	
	@After
	public void clearAfterInit() {
		clearDb();
	}

	@Test
	public void createShouldPersistData() {
		Planet planet = new Planet(null, "Alderaan", "temperate", "mountains", 1);
		this.planetRepository.save(planet);
		assertThat(planet.getId()).isNotNull();
		assertThat(planet.getName()).isEqualTo("Alderaan");
		assertThat(planet.getClimate()).isEqualTo("temperate");
		assertThat(planet.getTerrain()).isEqualTo("mountains");
		assertThat(planet.getMoviesAppearances()).isEqualTo(1);
	}

	@Test
	public void deleteShouldRemoveData() {
		Planet planet = new Planet(null, "Yavin IV", "temperate, tropical", "jungle, rainforests", 0);
		this.planetRepository.save(planet);
		planetRepository.delete(planet);
		assertThat(planetRepository.findById(planet.getId())).isEmpty();
	}

	@Test
	public void updateShouldChangeAndPersistData() {
		Planet planet = new Planet(null, "Hoth", "frozen", "tundra, ice caves, mountain ranges", 1);
		this.planetRepository.save(planet);
		
		planet.setName("Endor");
		planet.setClimate("temperate");
		planet = this.planetRepository.save(planet);
		
		Optional<Planet> planetDb = this.planetRepository.findById(planet.getId());
		assertNotNull(planetDb);
		assertThat(planetDb.get().getName()).isEqualTo("Endor");
	}

	@Test
	public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
		Planet planetOne = new Planet(null, "Bespin", "temperate", "gas giant", 0);
		Planet planetTwo = new Planet(null, "bespin", "tropical", "gas giant", 0);
		
		this.planetRepository.save(planetOne);
		this.planetRepository.save(planetTwo);
		
		Optional<List<Planet>> planets = planetRepository.findByNameIgnoreCaseContaining("bespin");

		if (planets.isPresent()) {
			assertThat(planets.get().size()).isEqualTo(2);
		}
	}

	@Test
	public void createWhenNameIsNullShouldThrowIllegalArgumentException() {
		thrown.expect(IllegalArgumentException.class);
		this.planetRepository.save(null);
	}
	
	private void clearDb() {
		this.planetRepository.deleteAll();
	}
}
