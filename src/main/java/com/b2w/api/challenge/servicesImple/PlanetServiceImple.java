package com.b2w.api.challenge.servicesImple;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public ResponseEntity<Planet> save(PlanetDtoRequest planet) {
		
		Planet planet1 = repository.findByNameIgnoreCase(planet.getName());
		
		if(planet1 != null) {
			return new ResponseEntity<Planet> ( planet1, HttpStatus.BAD_REQUEST);
		}
		
		if(this.getNumberOfAppearances(planet.getName())==0) {
			
			return new ResponseEntity<Planet> (HttpStatus.NOT_ACCEPTABLE);
		}
		
        planet1= planet.turnsToPlanet(this.getNumberOfAppearances(planet.getName()));
		
		return new ResponseEntity<Planet> ( repository.save(planet1),HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<Planet> findById(Long id) {
		
		Optional<Planet> planet =repository.findById(id);
		
		if(planet.isPresent()) {return new  ResponseEntity<Planet>(planet.get(),HttpStatus.ACCEPTED);}
		
		return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND) ;
	}

	@Override
	public Page<Planet> findAll(int page, int size) {
		
        Pageable paging = PageRequest.of(page, size, Sort.by("name").ascending());

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

	String url2= "https://swapi.dev/api/planets/";
	
	

		ReturnApiData listNamesOfPlanets =  restTemplate.getForObject(url2, ReturnApiData.class);
		
		for(Results resultNames:listNamesOfPlanets.getResults()) {
			
			if(name.toUpperCase().equals(resultNames.getName().toUpperCase()))
			{

				ReturnApiData returnn=restTemplate.getForObject(url, ReturnApiData.class);
				Results[] resultFilms=returnn.getResults();
				return resultFilms[0].getFilms().length;	
			}
			
}
		
	return 0;
	
		
	}
	
}
