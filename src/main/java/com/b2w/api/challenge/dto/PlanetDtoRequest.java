package com.b2w.api.challenge.dto;

import com.b2w.api.challenge.models.Planet;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PlanetDtoRequest {
	
	private String name;
	
	private String climate;
	
	private String ground;
	
	
	public Planet turnsToPlanet(Integer numberOfAppearances) {
		return new Planet(this.name,this.climate,this.ground,numberOfAppearances);
		
	}
}
