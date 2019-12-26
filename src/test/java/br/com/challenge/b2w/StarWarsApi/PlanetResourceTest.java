package br.com.challenge.b2w.StarWarsApi;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.DELETE;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.challenge.b2w.StarWarsApi.model.Planet;
import br.com.challenge.b2w.StarWarsApi.repository.PlanetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlanetResourceTest {

	@MockBean
	private PlanetRepository PlanetRepository;
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder();
        }
    }

	@Before
	public void setUp() {
		Optional<Planet> Planet = Optional.of(new Planet("1", "Alderaan", "temperate", "mountains", 0));
        BDDMockito.when(PlanetRepository.findById(Planet.get().getId())).thenReturn(Planet);
	}
	
    @Test
    public void listPlanetsShouldReturnStatusCode200() {
        List<Planet> Planets = asList(new Planet("1", "Endor", "tropical", "mountains", 0), new Planet("2", "Aragorn", "tropical", "mountains", 0));
        BDDMockito.when(PlanetRepository.findAll()).thenReturn(Planets);
        ResponseEntity<String> response = restTemplate.getForEntity("/api/planets/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    
    @Test
    public void getPlanetByIdWhenIdAreCorrectShouldReturnStatusCode200() {
        ResponseEntity<Planet> response = restTemplate.getForEntity("/api/planets/{id}", Planet.class, "1");
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void getPlanetByNameShouldReturnStatusCode200() {
        ResponseEntity<Planet> response = restTemplate.getForEntity("/api/planets/name/{name}", Planet.class, "Alderaan");
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void getPlanetByNameShouldIgnoreCaseAndReturnStatusCode200() {
        ResponseEntity<Planet> response = restTemplate.getForEntity("/api/planets/name/{name}", Planet.class, "alderaan");
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getPlanetByIdWhenPlanetDoesNotExistShouldReturnStatusCode404() {
        ResponseEntity<Planet> response = restTemplate.getForEntity("/api/planets/{id}", Planet.class, "-1");
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void deleteWhenIdExistsShouldReturnStatusCode200() {
        BDDMockito.doNothing().when(PlanetRepository).deleteById("1");
        ResponseEntity<String> exchange = restTemplate.exchange("/api/planets/{id}", DELETE, null, String.class, "1");
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    public void createWhenNameIsNullShouldReturnStatusCode400BadRequest() throws Exception{
        Planet Planet = new Planet("3", null, "tropical", "mountains", 0);
        BDDMockito.when(PlanetRepository.save(Planet)).thenReturn(Planet);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/planets/", Planet, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }
    
    @Test
    public void createShouldPersistDataAndReturnStatusCode201() throws Exception{
        Planet Planet = new Planet("3", "Naboo", "tropical", "mountains", 0);
        BDDMockito.when(PlanetRepository.save(Planet)).thenReturn(Planet);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/planets/", Planet, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }
}
