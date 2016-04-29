package com.xyinc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xyinc.model.Address;
import com.xyinc.service.AddressService;
import com.xyinc.utils.http.HtmlParser;
import com.xyinc.utils.http.HttpClient;

@Service
public class AddressServiceImpl implements AddressService{
	//TODO colocar em arquivo de properties
	private static final String ZIP_CODE_SEARCH_URL = "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm";
	private static Map<String, String> resultadoBuscaCepEnderecoDefaultParams;
	static{
		resultadoBuscaCepEnderecoDefaultParams = new HashMap<>();
		resultadoBuscaCepEnderecoDefaultParams.put("semelhante", "N");
		resultadoBuscaCepEnderecoDefaultParams.put("tipoCEP","ALL");
	}
	
	@Override
	public List<Address> findByZipCode(String zipCode) {
		Map<String, String> params = new HashMap<>();
		params.putAll(resultadoBuscaCepEnderecoDefaultParams);
		params.put("relaxation", zipCode);
		System.out.println(params);
		return HtmlParser.parse(HttpClient.doPost(ZIP_CODE_SEARCH_URL, params));
	}

	@Override
	public List<Address> findByStreetName(String streetName) {
		Map<String, String> params = new HashMap<>();
		params.putAll(resultadoBuscaCepEnderecoDefaultParams);
		params.put("relaxation", streetName);
		return HtmlParser.parse(HttpClient.doPost(ZIP_CODE_SEARCH_URL, params));
	}
	
}
