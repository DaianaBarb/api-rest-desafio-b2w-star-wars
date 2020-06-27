package com.b2w.api.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "planet")
public class Planet {
	

	public Planet(String name, String climate, String ground,Integer numberOfAppearances) {
		
		this.name=name;
		this.climate=climate;
		this.terrain=ground;
		this.numberOfAppearances=numberOfAppearances;
	}

	@Id
 	private String id;
	
    @NotNull
	private String name;

    @NotNull
	private String climate;
    
    @NotNull
	private String terrain;
    
    @NotNull
	private Integer numberOfAppearances;
    

    
    
    
    
    
}
