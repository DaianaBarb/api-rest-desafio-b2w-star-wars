package com.b2w.api.challenge.servicesImple;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.models.Planet;
import com.b2w.api.challenge.repositories.PlanetRepository;
import com.b2w.api.challenge.services.PlanetService;
import com.b2w.api.challenge.utils.Results;
import com.b2w.api.challenge.utils.ReturnApiData;



@Service
public class PlanetServiceImple implements PlanetService {
	
	@Autowired
	PlanetRepository repository;

    @Autowired
    RestTemplate restTemplate;

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
		
	String url ="https://swapi.dev/api/planets/?search="+name;
	
	ReturnApiData returnn=restTemplate.getForObject(url, ReturnApiData.class);
	Results[] result=returnn.getResults();
	return result[0].getFilms().length;
			
		
	}
}
