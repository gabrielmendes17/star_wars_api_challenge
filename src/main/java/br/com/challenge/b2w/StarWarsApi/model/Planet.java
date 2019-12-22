package br.com.challenge.b2w.StarWarsApi.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planeta")
public class Planet {

    @Id
    private String id;

    @NotNull
    @NotEmpty(message = "Nomé é obrigatório")
    private String name;
    
    @NotNull
    @NotEmpty(message = "Clima é obrigatório")
    private String climate;

    @NotNull
    @NotEmpty(message = "Terreno é obrigatório")
    private String terrain;

    private Integer moviesAppearances = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public Planet() {
		super();
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getMoviesAppearances() {
		return moviesAppearances;
	}

	public void setMoviesAppearances(Integer moviesAppearances) {
		this.moviesAppearances = moviesAppearances;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}