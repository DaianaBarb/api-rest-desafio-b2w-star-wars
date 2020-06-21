package com.b2w.api.challenge.servicesImple;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.models.Planet;
import com.b2w.api.challenge.repositories.PlanetRepository;
import com.b2w.api.challenge.services.PlanetService;
import com.b2w.api.challenge.utils.Api;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@Service
public class PlanetServiceImple implements PlanetService {
	
	@Autowired
	PlanetRepository repository;
	
	Api api;

	@Override
	public ResponseEntity<Planet> save(PlanetDtoRequest planeta) {
		
		
Planet planet= planeta.turnsToPlanet(this.getNumberOfAppearances(planeta.getName()));
		
		return new ResponseEntity<Planet> ( repository.save(planet),HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<Planet> findById(Long id) {
		
		Optional<Planet> planet =repository.findById(id);
		
		if(planet.isPresent()) {return new  ResponseEntity<Planet>(planet.get(),HttpStatus.ACCEPTED);}
		
		return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND) ;
	}

	@Override
	public Page<Planet> findAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findAll(paging);
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		
		Optional<Planet> planet =repository.findById(id);
		
		if(planet.isPresent()) {repository.deleteById(id); return new  ResponseEntity<Void>(HttpStatus.ACCEPTED);}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND) ;
	}

	@Override
	public ResponseEntity<Planet> findByName(String name) {
     Optional<Planet> planet =Optional.ofNullable(repository.findByNameIgnoreCase(name));
		
		if(planet.isPresent()) {return new  ResponseEntity<Planet>(planet.get(),HttpStatus.ACCEPTED);}
		
		return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND) ;
	}

	
private int getNumberOfAppearances(String name)  {
		
		JsonObject object;
		try {
			
			object = api.getBuilder( name);
			JsonArray films = object.getAsJsonArray("films");
			
			return films.size();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return 0;
		}
		
		
		
		
		
	}
}
