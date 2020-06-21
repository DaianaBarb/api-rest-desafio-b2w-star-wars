package com.b2w.api.challenge.controls;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.b2w.api.challenge.dto.PlanetDtoRequest;
import com.b2w.api.challenge.models.Planet;
import com.b2w.api.challenge.services.PlanetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/v1/api/star-wars")
@Api(value = "Desafio-B2w-Star-Wars")

public class PlanetController {
	
	    @Autowired
	    PlanetService service;

	    @PostMapping()
	    @ApiOperation(value = "Creates a new planet")
	    @ApiResponse(code = 201, message = 	"New planet successfully created.", response = String.class)
	    
	    public ResponseEntity<Planet> savePlanet(@Valid @RequestBody PlanetDtoRequest planet){
		 return service.save(planet);
	 }
	   
	     @GetMapping("/{id}")
		 @ApiResponses(value = {
		 @ApiResponse(code = 200, message = 	"returner planet successfully", response = String.class),
	     @ApiResponse(code = 204, message = 	"Does not contain planet", response = String.class)		})
		 @ApiOperation(value = "return planet by id")
	     
	     public ResponseEntity<Planet> findById(@Valid @PathVariable("id") Long id){
	    	 return service.findById(id);
	    	 
	     }
	     
	     @ApiResponses(value = {
	     @ApiResponse(code = 200, message = "Deleted planet successfully", response = String.class),
	     @ApiResponse(code = 404, message = 	"planet not found", response = String.class)					})
	     @ApiOperation(value="Deletes a planet")
	     @DeleteMapping("/{id}")
	     
	     public ResponseEntity<Void> deleteById(@Valid @PathVariable("id") Long id){
	    	 
	    	return service.delete(id); 
	     }
	     
	     @ApiResponses(value = {
	     @ApiResponse(code = 200, message = 	"returner planets successfully", response = String.class),
	     @ApiResponse(code = 204, message = 	"Does not contain planets", response = String.class)		})
	     @GetMapping("/name")
	     @ApiOperation(value = "Returns an ordered list of planets")
	     
	     public ResponseEntity<Planet> findByName(@Valid @RequestParam(required= true) String name){
	    	 
	    	 return service.findByName(name);
	     }
	     
	     @ApiResponses(value = {
	     @ApiResponse(code = 200, message = 	"returner planets successfully", response = String.class),
	     @ApiResponse(code = 204, message = 	"Does not contain planets", response = String.class)		})
	     @GetMapping()
	     @ApiOperation(value = "Returns an ordered list of planets")
	     
	     public ResponseEntity<Page<Planet>> getAll(
	    		 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
	    	     @RequestParam(value = "size", required = false, defaultValue = "10") int size){
	    	 
	    	 return new ResponseEntity<Page<Planet>>(service.findAll(page, size),HttpStatus.ACCEPTED);
	     }

}
