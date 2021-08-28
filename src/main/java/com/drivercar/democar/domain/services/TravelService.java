package com.drivercar.democar.domain.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.drivercar.democar.domain.TravelRequestStatus;
import com.drivercar.democar.domain.model.TravelRequest;
import com.drivercar.democar.domain.repository.TravelRequestRepository;

@Component
public class TravelService {
	@Autowired
	TravelRequestRepository travelRequestRepository;
	
	public TravelRequest savetravelRequest(TravelRequest travelRequest) {
		travelRequest.setStatus(TravelRequestStatus.CREATED);
		travelRequest.setCreationDate(new Date());
		
		
		return travelRequestRepository.save(travelRequest);
		
	}

}
