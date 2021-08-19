package com.drivercar.democar.domain.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivercar.democar.domain.model.TravelRequest;
import com.drivercar.democar.domain.services.TravelService;

@Service
@RestController
@RequestMapping(path = "/travelRequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelRequestAPI {
	@Autowired
	TravelService travelService;
	
	@PostMapping
	public void makeTravelRequest(@RequestBody TravelRequest travelRequest) {
		travelService.savetravelRequest(travelRequest);
	}
	

}
