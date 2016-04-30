package com.xyinc.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyinc.exception.AddressNotFoundException;
import com.xyinc.exception.InvalidParametersException;
import com.xyinc.model.Address;
import com.xyinc.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	// /address?zipCode={zipCode}
	@RequestMapping(value = "/address", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
    public List<Address> findByZipCode(@RequestParam(required = true) final String zipCode) {
		if(StringUtils.isBlank(zipCode) || !StringUtils.isNumeric(zipCode)){ 
			throw new InvalidParametersException("zipCode", zipCode);
		}
		List<Address> result = addressService.findByZipCode(zipCode);
		if(result.isEmpty()){ 
			throw new AddressNotFoundException(zipCode);
		}
        return result;
    }
	
	// /address/zipcode?streetname={streetName}
	@RequestMapping(value = "/address/zipcode", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
    public List<Address> findByStreetName(@RequestParam(required = true) final String streetName) {
		List<Address> result = addressService.findByZipCode(streetName);
		if(result.isEmpty()) throw new AddressNotFoundException(streetName);
        return result;
    }

}