package com.drivercar.democar.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.drivercar.democar.domain.interfaces.PassengerAPI;
import com.drivercar.democar.domain.model.Passenger;
import com.drivercar.democar.domain.model.TravelRequest;
import com.drivercar.democar.domain.repository.PassengerRepository;

@Component
public class TravelRequestMapper{
	@Autowired
	private PassengerRepository passengerRepository;
	
	
	public TravelRequest map(TravelRequestInput input) {
		Passenger passenger = passengerRepository.findById(input.getPassengerId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
				
				TravelRequest travelRequest = new TravelRequest();
				travelRequest.setOrigin(input.getOrigin());
				travelRequest.setDestination(input.getDestination());
				travelRequest.setPassenger(passenger);				
				return travelRequest;
	}
				
	public TravelRequestOutput map(TravelRequest travelRequest){
		TravelRequestOutput travelRequestOutput = new TravelRequestOutput();
		travelRequest.setCreationDate(travelRequest.getCreationDate());
		travelRequest.setDestination(travelRequest.getDestination());
		travelRequest.setId(travelRequest.getId());
		travelRequest.setOrigin(travelRequest.getOrigin());
		travelRequest.setStatus(travelRequest.getStatus());
		
		return travelRequestOutput;
		
		
	}
	
	public EntityModel<TravelRequestOutput> buildOutputModel(TravelRequest travelRequest, TravelRequestOutput output){
	    EntityModel<TravelRequestOutput> model = new  EntityModel<>(output);
		Link passengerLink = WebMvcLinkBuilder
				.linkTo(PassengerAPI.class)
				.slash(travelRequest.getPassenger().getId())
				.withRel("passenger")
				.withTitle(travelRequest.getPassenger().getNome());
		model.add(passengerLink);
		
		return model;
	
	}
}
