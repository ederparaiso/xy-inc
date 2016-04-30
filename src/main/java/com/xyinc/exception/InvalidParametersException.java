package com.xyinc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid address search params.")
public class InvalidParametersException extends RuntimeException {
	private static final long serialVersionUID = 5550378518367823785L;
	private static final Logger LOGGER = LoggerFactory.getLogger(InvalidParametersException.class);
	
	public InvalidParametersException(String param, String value){
		LOGGER.warn(String.format("Address not found for a given search params: %s / %s", param , value));
	}
}