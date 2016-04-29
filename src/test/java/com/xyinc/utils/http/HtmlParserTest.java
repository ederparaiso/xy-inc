package com.xyinc.utils.http;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;

import com.xyinc.model.Address;

public class HtmlParserTest {
	
	@Test
	public void parsingEmptyResponseShouldReturnEmptyAddressList() throws IOException{
		File file = new File("src/test/resources/mockResultEmpty.html");
		String html = FileUtils.readFileToString(file);
		List<Address> parsedResult = HtmlParser.parse(Jsoup.parse(html));
		Assert.assertTrue(parsedResult.isEmpty());
	}
	
	@Test
	public void parsingSingleAddresResponseShouldReturnAddressListOfSize1() throws IOException{
		File file = new File("src/test/resources/mockResultSingleElement.html");
		String html = FileUtils.readFileToString(file);
		List<Address> parsedResult = HtmlParser.parse(Jsoup.parse(html));
		Assert.assertEquals(1, parsedResult.size());
	}
	
	@Test
	public void parsingTwoAddresResponseShouldReturnAddressListOfSize2() throws IOException{
		File file = new File("src/test/resources/mockResultList.html");
		String html = FileUtils.readFileToString(file);
		List<Address> parsedResult = HtmlParser.parse(Jsoup.parse(html));
		Assert.assertEquals(2, parsedResult.size());
	}
	
	@Test
	public void parsingResponseWithAllAddressDetailsShouldReturnAddressWithAllParameters() throws IOException{
		File file = new File("src/test/resources/mockResultDefaultAddress.html");
		String html = FileUtils.readFileToString(file);
		List<Address> parsedResult = HtmlParser.parse(Jsoup.parse(html));
		Assert.assertEquals(1, parsedResult.size());
		Assert.assertEquals("Rua Arlindo Teixeira", parsedResult.get(0).getStreetName());
		Assert.assertEquals("Martins", parsedResult.get(0).getDistrict());
		Assert.assertEquals("Uberlândia", parsedResult.get(0).getCity());
		Assert.assertEquals("MG", parsedResult.get(0).getState());
		Assert.assertEquals("38400-352", parsedResult.get(0).getZipCode());
	}
}
