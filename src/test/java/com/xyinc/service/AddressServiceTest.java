package com.xyinc.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.xyinc.model.Address;
import com.xyinc.service.impl.AddressServiceImpl;

public class AddressServiceTest {
	@InjectMocks
	private AddressServiceImpl service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service.setUrl("http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm");
	}

	@Test
	public void searchUnexistingZipCodeReturnsEmptyAddressList(){
		List<Address> result = service.findByZipCode("12345678");
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void searchZipCodeWithASingleAddressReturnsListOfSize1(){
		List<Address> result = service.findByZipCode("38400352");
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void searchZipCodeWithMultipleAddressReturnsListOfAddress(){
		List<Address> result = service.findByZipCode("38400444");
		Assert.assertTrue(result.size() > 1);
	}
	
	@Test
	public void searchExistingZipCodeReturnsAllAddressData() {
		List<Address> result = service.findByZipCode("38400352");
		Assert.assertEquals(1, result.size());
		Assert.assertEquals("Rua Arlindo Teixeira ", result.get(0).getStreetName());
		Assert.assertEquals("Martins ", result.get(0).getDistrict());
		Assert.assertEquals("Uberlândia", result.get(0).getCity());
		Assert.assertEquals("MG", result.get(0).getState());
		Assert.assertEquals("38400-352", result.get(0).getZipCode());
	}
	
	@Test
	public void searchUnexistingAddressReturnsEmptyAddressList(){
		List<Address> result = service.findByStreetName("rua de teste 123");
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void searchAddressWithASingleZipCodeReturnsListOfSize1(){
		List<Address> result = service.findByStreetName("Rua Bernardo Cupertino Martins");
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void searchAddressWithMultipleZipCodeReturnsListOfAddress(){
		List<Address> result = service.findByStreetName("Avenida Olegário Maciel");
		Assert.assertTrue(result.size() > 1);
	}
	
	@Test
	public void searchExistingAddressCodeReturnsAllAddressData() {
		List<Address> result = service.findByStreetName("Rua Arlindo Teixeira Uberlândia");
		Assert.assertEquals(1, result.size());
		Assert.assertEquals("Rua Arlindo Teixeira ", result.get(0).getStreetName());
		Assert.assertEquals("Martins ", result.get(0).getDistrict());
		Assert.assertEquals("Uberlândia", result.get(0).getCity());
		Assert.assertEquals("MG", result.get(0).getState());
		Assert.assertEquals("38400-352", result.get(0).getZipCode());
	}
}
