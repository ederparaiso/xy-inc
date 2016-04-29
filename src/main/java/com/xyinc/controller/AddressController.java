package com.xyinc.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.xyinc.model.Address;
import com.xyinc.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	// /address?zipCode={zipCode}
	@RequestMapping(value = "/address", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
    public List<Address> findByZipCode(@RequestParam(required = true) final String zipCode) {
        return addressService.findByZipCode(zipCode);
    }
	
	// /address/zipcode?streetname={streetName}
	@RequestMapping(value = "/address/zipcode", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
    public List<Address> findByStreetName(@RequestParam(required = true) final String streetName) {
        return addressService.findByStreetName(streetName);
    }

}