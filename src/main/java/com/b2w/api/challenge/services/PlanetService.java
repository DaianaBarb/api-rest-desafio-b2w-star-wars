package com.b2w.api.challenge.services;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.models.Planet;

public interface PlanetService {
	
	public ResponseEntity<Planet> save(PlanetDtoRequest planeta);
	public ResponseEntity<Planet> findById(Long id);
	public Page<Planet> findAll(int page, int size);
	public ResponseEntity<Void> delete(Long id);
	public ResponseEntity< Planet> findByName(String name);
	

}
