package com.b2w.api.desafio.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2w.api.desafio.services.PlanetaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/v1/api/star-wars")
@Api(value = "Desafio-B2w-Star-Wars")

public class PlanetaController {
	
	 @Autowired
	 PlanetaService service;

}
