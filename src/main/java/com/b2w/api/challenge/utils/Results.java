package com.b2w.api.challenge.utils;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Results {
	
	@JsonProperty("films")
	public String[]films;

	public String[] getFilms() {
		return films;
	}

	public void setFilms(String[] films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "Results [films=" + Arrays.toString(films) + "]";
	}

}
