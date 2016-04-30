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
	
	/**
     * Extracts address information from html page.
     * Structure example:
     * {@code
     * <tr>
	 *     <th width="150">Logradouro/Nome:</th>
	 *     <th width="90">Bairro/Distrito:</th>
	 *     <th width="80">Localidade/UF:</th>
	 *     <th width="50">CEP:</th>
	 * </tr>
	 * <tr>
	 *     <td width="150">Avenida João Naves de Ávila - até 266 - lado par</td>
	 *     <td width="90">Centro</td>
	 *     <td width="80">Uberlândia/MG</td>
	 *     <td width="55">38400-042</td>
	 * </tr>
     * }
     * @param htmlDocument html to parse.
     * @return list of address extracted from html.
     */
	public static List<Address> parse(Document htmlDocument){
		List<Address> result = new ArrayList<>();
		
		if(htmlDocument == null){
			LOGGER.info("No html to parse: empty.");
			return result;
		}
		Elements content = htmlDocument.select("div.ctrlcontent > table.tmptabela tr");
		
		if(content.isEmpty()) {
			LOGGER.info("Especified html node not found.");
			return result;
		}

		content.remove(0);
		for(Element e : content){
			Address address = new Address();
			
			Elements tds = e.select("td");
			LOGGER.debug(tds.get(0).text());
			address.setStreetName(tds.get(0).text());
			LOGGER.debug(tds.get(1).text());
			address.setDistrict(tds.get(1).text());
			LOGGER.debug(tds.get(2).text());
			String[] cityState = tds.get(2).text().split("/");
			address.setCity(cityState[0]);
			if(cityState.length > 1){
				address.setState(cityState[1]);
			}
			LOGGER.debug(tds.get(3).text());
			address.setZipCode(tds.get(3).text());
			result.add(address);
		}
		return result;
	}
}