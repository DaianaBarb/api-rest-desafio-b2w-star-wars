package com.b2w.api.challenge.repositories;


//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.b2w.api.challenge.models.Planet;

@Repository
@Transactional
public interface PlanetRepository extends MongoRepository<Planet, String> {
	
	Planet findByNameIgnoreCase(String name);

}
