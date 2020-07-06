package com.b2w.api.challenge.models;

import java.io.Serializable;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
//@Table(name = "TB_PLANETA")
@Document(collection = "planet")
public class Planet implements Serializable {
	
	public Planet() {
		
	}

	public Planet(String name, String climate, String ground,Integer numberOfAppearances) {
		
		this.name=name;
		this.climate=climate;
		this.terrain=ground;
		this.numberOfAppearances=numberOfAppearances;
	}

	private static final long serialVersionUID = 1L;

	@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
    @NotNull(message = "the name attribute cannot be null")
    @NotBlank(message = "the name attribute cannot be blank")
	private String name;

    @NotNull(message = "the climate attribute cannot be null")
    @NotBlank(message = "the climate attribute cannot be blank")
	private String climate;
    
    @NotNull(message = "the terrain attribute cannot be null")
    @NotBlank(message = "the terrain attribute cannot be blank")
	private String terrain;
    
    @NotNull(message = "the numberOfAppearances attribute cannot be null")
    @NotBlank(message = "the numberOfAppearances attribute cannot be blank")
	private Integer numberOfAppearances;
    
    
    
    
    
    
    
    
}
