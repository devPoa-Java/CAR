package com.drivercar.democar.domain.interfaces;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.drivercar.democar.domain.model.Driver;
import com.drivercar.democar.domain.repository.DriverRepository;

@Service
@RestController
@RequestMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class DriverAPI {
	@Autowired
	DriverRepository driverRepository;
	
	@GetMapping("/drivers")
	public List<Driver> listDrivers(){
		return driverRepository.findAll();
	}/*Recurso lista toda de motorista*/
	
	@GetMapping("/drivers/{id}")
	public Driver findDriver( @PathVariable("id")Long id) {
	 return driverRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}/*Recurso motorista especifico*/
}
