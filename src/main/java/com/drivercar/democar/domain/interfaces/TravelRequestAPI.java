package com.drivercar.democar.domain.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drivercar.democar.domain.TravelRequestInput;
import com.drivercar.democar.domain.TravelRequestMapper;
import com.drivercar.democar.domain.TravelRequestOutput;
import com.drivercar.democar.domain.TravelService;
import com.drivercar.democar.domain.model.TravelRequest;

@Service
@RestController
@RequestMapping(path = "/travelRequests", produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelRequestAPI {
	@Autowired
	TravelService travelService;
	@Autowired
	TravelRequestMapper mapper;
	
	@PostMapping
	public EntityModel<TravelRequestOutput> makeTravelRequest(@RequestBody TravelRequestInput travelRequestInput) {
		TravelRequest request = travelService.saveTravelRequest(mapper.map(travelRequestInput));
		TravelRequestOutput output =  mapper.map(request);
		return mapper.buildOutputModel(request, output);
	}
	
	@GetMapping("/nearby")
	public List<EntityModel<TravelRequestOutput>> listNearbyRequests(@RequestParam String currentAddress){
		List<TravelRequest> requests = travelService.listNearbyTravelRequests(currentAddress);
		return mapper.buildOutputModel(requests);
		
		
	}

}
