                                                                                                                                                                                                                                                                package com.drivercar.democar.domain.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	
	@GetMapping("/drivers/{id}")
	public Driver findDriver( @PathVariable("id")Long id) {
	 return driverRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/drivers")
	public Driver createDriver(@RequestBody Driver driver) {
		return driverRepository.save(driver);
	}
	
	@PutMapping("/drivers/{id}")
	public Driver fullUpdateDriver(@PathVariable("id") Long id, @RequestBody Driver driver) {
		Driver foundDriver = findDriver(id);
		foundDriver.setDataNascimento(driver.getDataNascimento());
		foundDriver.setNome(driver.getNome());
		return driverRepository.save(foundDriver);	
		
	}
	@PatchMapping("/drivers/{id}")
	public Driver incrementalupdateDriver(@PathVariable("id") Long id, @RequestBody Driver driver) {
		Driver foundDriver = findDriver(id);
		foundDriver.setDataNascimento(Optional.ofNullable(driver.getDataNascimento()).orElse(foundDriver.getDataNascimento()));
		foundDriver.setNome(Optional.ofNullable(driver.getNome()).orElse(foundDriver.getNome()));
		return driverRepository.save(foundDriver);	
		
	}
	
	@DeleteMapping("/drivers/{id}")
	public void deleteDriver(@PathVariable("id") Long id) {
	      driverRepository.delete(findDriver(id));
	}
}


