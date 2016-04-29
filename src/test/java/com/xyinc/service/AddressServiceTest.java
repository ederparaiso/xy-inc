package com.xyinc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.xyinc.model.Address;
import com.xyinc.service.impl.AddressServiceImpl;
import com.xyinc.utils.http.HtmlParser;

public class AddressServiceTest {
	@Mock
	private AddressServiceImpl service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findByZipCodeReturnsOneAddress(){
		List<Address> result = service.findByZipCode("38400-352");
		Assert.assertEquals(1, result.size());
		Assert.assertEquals("Rua Arlindo Teixeira", result.get(0).getStreetName());
		Assert.assertEquals("Martins", result.get(0).getDistrict());
		Assert.assertEquals("Uberlândia", result.get(0).getCity());
		Assert.assertEquals("MG", result.get(0).getState());
		Assert.assertEquals("38400-352", result.get(0).getZipCode());
	}
}
