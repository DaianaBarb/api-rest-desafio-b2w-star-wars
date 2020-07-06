package com.b2w.api.challenge.mock;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.b2w.api.challenge.ChallengeB2wStarWarsApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=ChallengeB2wStarWarsApplication.class)
@AutoConfigureMockMvc
public class PlanetTest {
	 @Autowired
	    private MockMvc mvc;
	    
	    Gson gson = new GsonBuilder().create();
	    
		private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
		  	      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
		
	       @Test
	    public void shouldReturn201SaveLog() throws Exception {
	    
	    	String eventJosn = "{\r\n" + 
	    			"  \"climate\": \"temperado\",\r\n" + 
	    			"  \"name\": \"Alderaan\",\r\n" + 
	    			"  \"terrain\": \"montanhas\"\r\n" + 
	    			"}";
	    	
	    	
	    	MvcResult result= mvc.perform(MockMvcRequestBuilders.post("/v1/api/star-wars")
	                .contentType(APPLICATION_JSON_UTF8).content(eventJosn)).andReturn();
	        int status = result.getResponse().getStatus();
	        assertEquals(201, status);
	    }
	    
	    
	    
	     @Test
		    public void shouldReturn202FindByName() throws Exception {
		    	
		       mvc.perform(MockMvcRequestBuilders.get("/v1/api/star-wars/name?name=Alderaan")).andExpect(status().isAccepted());
		     
		    }
		    
	    @Test
	    public void shouldReturn202FindById() throws Exception {
	    	
	       mvc.perform(MockMvcRequestBuilders.get("/v1/api/star-wars/1")).andExpect(status().isAccepted());
	     
	    }
	    
	    @Test
	    public void shouldReturn200DeleteById() throws Exception {
	      
	        mvc.perform(MockMvcRequestBuilders.delete("/v1/api/star-wars/1"))
	                .andExpect(status().isAccepted());
	    }
	    
}
