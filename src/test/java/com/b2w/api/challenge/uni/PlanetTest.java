package com.b2w.api.challenge.uni;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.b2w.api.challenge.ChallengeB2wStarWarsApplication;
import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.models.Planet;
import com.b2w.api.challenge.repositories.PlanetRepository;
import com.b2w.api.challenge.services.PlanetService;





@RunWith(SpringRunner.class)
@SpringBootTest(classes=ChallengeB2wStarWarsApplication.class)

public class PlanetTest {
	
	@Autowired
	private PlanetService service;
	
	@Autowired
	private PlanetRepository repository;
	
	
	 @Test
	   	public void getShouldPlanetFindByName() {
	    	
		 PlanetDtoRequest dto= new  PlanetDtoRequest();
		    dto.setName("Tatooine");
			dto.setClimate("arid");
			dto.setTerrain("desert");
			
			
	       	this.service.save(dto);
	   		
 	   List <ResponseEntity<Planet>> planets = new ArrayList<>();
 	   planets.add(this.service.findByName("Tatooine"));
	   	   
	   	   Assertions.assertThat(planets.size()).isEqualTo(1);
	       	
	 }
	
	 @Test
	 public void ConvertToPlanet() {
		 PlanetDtoRequest dto= new  PlanetDtoRequest();
		dto.setName("Tatooine");
		dto.setClimate("arid");
		dto.setTerrain("desert");
		Planet planet= dto.turnsToPlanet(4);
		assertEquals(planet.getName(), "Tatooine");
		assertEquals(planet.getClimate(),"arid");
		assertEquals(planet.getTerrain(), "desert");
		 
	 }
	
	 @Test
		public void createShouldPersistData()
	    {
		
		 PlanetDtoRequest dto= new  PlanetDtoRequest();
		    dto.setName("Tatooine");
			dto.setClimate("arid");
			dto.setTerrain("desert");

			
			Assertions.assertThat(this.service.save(dto).getBody().getNumberOfAppearances()).isEqualTo(5);
			Assertions.assertThat(dto.getName()).isEqualTo("Tatooine");
			Assertions.assertThat(dto.getClimate()).isEqualTo("arid");
			Assertions.assertThat(dto.getTerrain()).isEqualTo("desert");

			
		}
	
	 @Test
		public void deleteShouldRemoveData() {
		 Planet planet = new  Planet();
		    planet .setName("Tatooine");
			planet .setClimate("arid");
			planet .setTerrain("desert");
			planet.setNumberOfAppearances(3);
			this.repository.save(planet );
			this.repository.deleteById(planet.getId());
			
			Assertions.assertThat(this.repository.findById(planet.getId())).isEmpty();
		}
	 
	 @Test
	   	public void getShouldGetAllData() {
	    	
		 PlanetDtoRequest dto= new  PlanetDtoRequest();
		    dto.setName("Tatooine");
			dto.setClimate("arid");
			dto.setTerrain("desert");
			
			
	       	this.service.save(dto);
	   	   List<Planet> planets = repository.findAll();
	   	   
	   	   Assertions.assertThat(planets.size()).isEqualTo(2);
	   		
	   	}
	 @Test
	   	public void saveTwoIdenticalPlanetsGetShould400() {
	    	
		 PlanetDtoRequest dto= new  PlanetDtoRequest();
		    dto.setName("Tatooine");
			dto.setClimate("arid");
			dto.setTerrain("desert");
			
			 PlanetDtoRequest dto2= new  PlanetDtoRequest();
			    dto2.setName("Tatooine");
				dto2.setClimate("arid");
				dto2.setTerrain("desert");
	       	this.service.save(dto);
	   		
	   	  
	   	  Assertions.assertThat(this.service.save(dto2)).isEqualTo(new ResponseEntity<Planet> ( HttpStatus.BAD_REQUEST));
	   	
	   		
	   	}
	 @Test
	   	public void 
	   	savePlanetThatDoesNotExistInTheApiGetShould406() {
	    	
		 PlanetDtoRequest dto= new  PlanetDtoRequest();
		    dto.setName("Teste");
			dto.setClimate("arid");
			dto.setTerrain("desert");
			
	       	
	   		
	   	  
	   	  Assertions.assertThat(this.service.save(dto)).isEqualTo(new ResponseEntity<Planet> (HttpStatus.NOT_ACCEPTABLE));
	   	
	   		
	   	
}
	 @Test
	 public void searchByIdGetShould202() {
		 PlanetDtoRequest dto= new  PlanetDtoRequest();
		    dto.setName("Alderaan");
			dto.setClimate("arid");
			dto.setTerrain("desert");
			ResponseEntity<Planet> planet=this.service.save(dto);
			
			Assertions.assertThat(this.service.findById(planet.getBody().getId()));
		
	 }
	
}