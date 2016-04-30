package com.xyinc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xyinc.model.Address;
import com.xyinc.service.AddressService;
import com.xyinc.utils.http.HtmlParser;
import com.xyinc.utils.http.HttpClient;

@Service
public class AddressServiceImpl implements AddressService{
	@Value("${address.search.url}")
	private String zipCodeSearchUrl;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
	private static Map<String, String> resultadoBuscaCepEnderecoDefaultParams;
	static{
		resultadoBuscaCepEnderecoDefaultParams = new HashMap<>();
		resultadoBuscaCepEnderecoDefaultParams.put("semelhante", "N");
		resultadoBuscaCepEnderecoDefaultParams.put("tipoCEP","ALL");
	}
	
	public void setUrl(String url) {
		zipCodeSearchUrl = url;
	}
	
	@Override
	public List<Address> findByZipCode(String zipCode) {
		Map<String, String> params = new HashMap<>();
		params.putAll(resultadoBuscaCepEnderecoDefaultParams);
		params.put("relaxation", zipCode);
		
		LOGGER.debug("service params: " + params.toString());
		return HtmlParser.parse(HttpClient.doPost(zipCodeSearchUrl, params));
	}

	@Override
	public List<Address> findByStreetName(String streetName) {
		Map<String, String> params = new HashMap<>();
		params.putAll(resultadoBuscaCepEnderecoDefaultParams);
		params.put("relaxation", streetName);
		
		LOGGER.debug("service params: " + params.toString());
		return HtmlParser.parse(HttpClient.doPost(zipCodeSearchUrl, params));
	}
	
}
