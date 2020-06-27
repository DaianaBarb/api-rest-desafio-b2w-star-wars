package com.b2w.api.challenge.utils;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Results {
	
	@JsonProperty("films")
	public String[]films;
	
	@JsonProperty("name")
    public String name;

	@Override
	public String toString() {
		return "Results [films=" + Arrays.toString(films) + ", name=" + name + "]";
	}

	

	

}
