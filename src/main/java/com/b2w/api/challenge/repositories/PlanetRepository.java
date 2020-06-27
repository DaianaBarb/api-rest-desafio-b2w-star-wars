package com.b2w.api.challenge.repositories;

import com.b2w.api.challenge.models.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {
	
	Planet findByNameIgnoreCase(String name);

}
