package com.b2w.api.challenge.models;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Retorna {
	public Results[] getResults() {
		return results;
	}

	public void setResults(Results[] results) {
		this.results = results;
	}

	@JsonProperty("results")
	public Results[] results;

	@Override
	public String toString() {
		return "retorna [results=" + Arrays.toString(results) + "]";
	}

}
