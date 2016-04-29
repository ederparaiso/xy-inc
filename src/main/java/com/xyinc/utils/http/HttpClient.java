package com.xyinc.utils.http;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpClient {
	
	public static Document doPost(final String url, final Map<String, String> params){
    	Document response;
    	try {
    		System.out.println(params);
    		response = Jsoup.connect(url)
					  .data(params)
					  .post();
//    		System.out.println(response);
			return response;
			
		} catch (IOException e) {
			// TODO inserir logger
			e.printStackTrace();
			return null;
		}
    }
}
