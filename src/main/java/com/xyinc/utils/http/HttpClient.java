package com.xyinc.utils.http;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
	
	public static Document doPost(final String url, final Map<String, String> params){
    	Document response;
    	try {
    		response = Jsoup.connect(url)
					  .data(params)
					  .post();
    		
			return response;
			
		} catch (IOException e) {
			LOGGER.error("Error during request.", e);
			return null;
		}
    }
}
