package com.b2w.api.challenge.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.models.Planet;

public interface PlanetService {
	
	public Planet save(PlanetDtoRequest planeta);
	public Optional<Planet> findById(String id);
	public Page<Planet> findAll(int page, int size);
	public Optional<Planet> delete(String id);
	public Optional<Planet> findByName(String name);
	

}
