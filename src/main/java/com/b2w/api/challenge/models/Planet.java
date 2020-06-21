package com.b2w.api.challenge.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_PLANETA")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotNull
	private String name;

    @NotNull
	private String climate;
    
    @NotNull
	private String terrain;
    
    @NotNull
	private Integer numberOfAppearances;
    
    
    
    
    
    
    
    
}
