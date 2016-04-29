package com.xyinc.utils.http;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyinc.model.Address;

public class HtmlParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlParser.class);
	
	public static List<Address> parse(Document htmlDocument){
		LOGGER.info("TESTE");
		List<Address> result = new ArrayList<>();
		
		if(htmlDocument == null){
			//TODO log warn
			return result;
		}
		Elements content = htmlDocument.select("div.ctrlcontent > table.tmptabela tr");
		
		if(content.isEmpty()) {
			//TODO inserir logger
			System.out.println("vazio");
			return result;
		}
		System.out.println("ok");
		/*
		 * Exemplo de retorno:
		 * Logradouro/Nome: 	 Bairro/Distrito:  Localidade/UF: 	 CEP:
    	 * Rua Arlindo Teixeira  Martins  	       Uberlândia/MG 	 38400-352
         *                                         Monte Carmelo/MG  38500-000
		 * */
		content.remove(0);
		for(Element e : content){
			Address address = new Address();
			
			Elements tds = e.select("td");
			System.out.println(tds.get(0).text());
			address.setStreetName(tds.get(0).text());
			System.out.println(tds.get(1).text());
			address.setDistrict(tds.get(1).text());
			System.out.println(tds.get(2).text());
			String[] cityState = tds.get(2).text().split("/");
			address.setCity(cityState[0]);
			if(cityState.length > 1){
				address.setState(cityState[1]);
			}
			System.out.println(tds.get(3).text());
			address.setZipCode(tds.get(3).text());
			System.out.println("---------\n");
			result.add(address);
		}
		return result;
	}
}