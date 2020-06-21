package com.b2w.api.challenge.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.b2w.api.challenge.models.Planet;

@Repository
@Transactional
public interface PlanetRepository extends JpaRepository<Planet, Long> {
	
	public Planet findByNameIgnoreCase(String name);

}
