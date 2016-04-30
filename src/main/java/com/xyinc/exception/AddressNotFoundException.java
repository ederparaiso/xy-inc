package com.xyinc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Address not found.")
public class AddressNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7371056287934021731L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressNotFoundException.class);
	
	public AddressNotFoundException(String searchParams){
		LOGGER.warn("Address not found for a given search params: " + searchParams);
	}
}