package br.com.challenge.b2w.StarWarsApi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.b2w.StarWarsApi.model.Planet;
import br.com.challenge.b2w.StarWarsApi.service.IPlanetService;

@RestController
@RequestMapping("/api/planets")
public class PlanetResource {
	
	@Autowired
	IPlanetService planetService;
	
	
	/*
	 * Find all planets at database.
	 */
	@GetMapping
	public ResponseEntity<List<Planet>> list() {
		return new ResponseEntity<>(this.planetService.findAll(), HttpStatus.OK);
	}

	/*
	 * Creates a new Planet.
	 */
	@PostMapping
	public ResponseEntity<Planet> create(@Valid @RequestBody Planet planet, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.planetService.save(planet));
	}
	
	/*
	 * Search for a Planet in database by id.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		this.planetService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * Search for a Planet at database by id.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Planet> findById(@PathVariable String id) {
			return new ResponseEntity<>(planetService.findById(id), HttpStatus.OK);
	}
	
	/*
	 * Search for a Planet at database by name.
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<Optional<List<Planet>>> findByName(@PathVariable String name) {
			return new ResponseEntity<>(planetService.findByName(name), HttpStatus.OK);
	}

}
