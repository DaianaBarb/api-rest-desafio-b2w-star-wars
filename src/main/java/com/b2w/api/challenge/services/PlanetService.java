package com.b2w.api.challenge.services;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.models.Planet;

public interface PlanetService {
	
	 ResponseEntity<Planet> save(PlanetDtoRequest planeta);
	 ResponseEntity<Planet> findById(String id);
	 Page<Planet> findAll(int page, int size);
	 ResponseEntity<Void> delete(String id);
	 ResponseEntity< Planet> findByName(String name);
	

}
