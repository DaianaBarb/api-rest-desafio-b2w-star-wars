package com.b2w.api.challenge.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resourceName) {
		super("Resource:"+resourceName+"Not found");
	}
}
