package com.b2w.api.challenge.servicesImple;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.exceptions.ResourceNotFoundException;
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
    
    @Value("${apistarwars.url}")
	String urlSearchPlanets;
	
	@Value("${SearchOfName.url}")
	String urlSearchOfName;
	

	@Override
	public Planet save(PlanetDtoRequest planet)  {
		
		Planet findPlanet = repository.findByNameIgnoreCase(planet.getName());
		
		if(findPlanet != null) {
			return findPlanet;
		}
		
		if(this.getNumberOfAppearancesAndCheckName(planet.getName())==0) {
			
			throw new ResourceNotFoundException(" planet name inserted not found ");
		}
		
        findPlanet= planet.turnsToPlanet(this.getNumberOfAppearances(planet.getName()));
		
		return  repository.save(findPlanet);

	}

	@Override
	public Optional<Planet> findById(String id) {
		
		Optional<Planet> planet =repository.findById(id);
		
		
		if(planet.isPresent()) {
			
			planet.get().setNumberOfAppearances(getNumberOfAppearances(planet.get().getName()));
			
			this.repository.save(planet.get());
			
			return Optional.ofNullable(planet.get());}
		
		return planet ;
	}

	@Override
	public Page<Planet> findAll(int page, int size) {
		
        Pageable paging = PageRequest.of(page, size, Sort.by("name").ascending());

		return repository.findAll(paging);
	}

	@Override
	public Optional<Planet> delete(String id) {
		
		Optional<Planet> planet =repository.findById(id);
		
		if(planet.isPresent()) {repository.deleteById(id);}
		
		return planet;
		
		
	}

	@Override
	public Optional<Planet> findByName(String name) {
		
     Optional<Planet> planet =Optional.ofNullable(repository.findByNameIgnoreCase(name));
		
  	if(planet.isPresent()) {return Optional.ofNullable(planet.get());}
		
		return planet;
	}

	
    private int getNumberOfAppearancesAndCheckName(String name)  {
		
	
	
	ReturnApiData listNamesOfPlanets =  restTemplate.getForObject(this.urlSearchPlanets, ReturnApiData.class);
	
	
		
	for(Results resultNames:listNamesOfPlanets.getResults()) {
			
			if(name.toUpperCase().equals(resultNames.getName().toUpperCase()))
			{
                 return  getNumberOfAppearances(name);
			}}
		
	return 0;
	
		
	}
    
    private int getNumberOfAppearances(String name) {
    	
    	ReturnApiData returnn=restTemplate.getForObject(this.urlSearchOfName+name, ReturnApiData.class);
		Results[] result=returnn.getResults();
		return result[0].getFilms().length;	
    }
	
}
