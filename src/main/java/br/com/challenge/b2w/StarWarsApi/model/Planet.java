package br.com.challenge.b2w.StarWarsApi.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "planet")
public class Planet {

    @Id
    private String id;

    @NotNull
    @NotEmpty(message = "Name is required")
    private String name;
    
    @NotNull
    @NotEmpty(message = "Climate is required")
    private String climate;

    @NotNull
    @NotEmpty(message = "Terrain is required")
    private String terrain;

    private Integer moviesAppearances = 0;
	
}