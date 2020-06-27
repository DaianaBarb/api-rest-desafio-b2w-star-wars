package com.b2w.api.challenge.utils;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReturnApiData {
	

	@JsonProperty("results")
	public Results[] results;

	@Override
	public String toString() {
		return "retorna [results=" + Arrays.toString(results) + "]";
	}

}
