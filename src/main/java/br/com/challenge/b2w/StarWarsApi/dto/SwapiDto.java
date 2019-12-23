package br.com.challenge.b2w.StarWarsApi.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties()
public class SwapiDto implements Serializable {

	private static final long serialVersionUID = 1L;

	List<PlanetDto> results;

	public List<PlanetDto> getResults() {
		return results;
	}

	public void setResults(List<PlanetDto> results) {
		this.results = results;
	}

	public SwapiDto() {
		super();
	}
	
}
