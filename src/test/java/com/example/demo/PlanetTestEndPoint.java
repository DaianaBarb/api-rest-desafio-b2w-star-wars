package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.b2w.api.challenge.ChallengeB2wStarWarsApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=ChallengeB2wStarWarsApplication.class)
@AutoConfigureMockMvc
public class PlanetTestEndPoint {
	 @Autowired
	    private MockMvc mvc;
	    
	    Gson gson = new GsonBuilder().create();
	    

}
