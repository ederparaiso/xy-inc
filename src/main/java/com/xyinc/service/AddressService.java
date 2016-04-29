package com.xyinc.service;

import java.util.List;

import com.xyinc.model.Address;

public interface AddressService {
	public List<Address> findByZipCode(final String zipCode);
	
	public List<Address> findByStreetName(final String streetName);
}