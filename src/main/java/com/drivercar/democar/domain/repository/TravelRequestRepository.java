package com.drivercar.democar.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivercar.democar.domain.TravelRequestStatus;
import com.drivercar.democar.domain.model.TravelRequest;

public interface TravelRequestRepository  extends JpaRepository<TravelRequest, Long>{
	List<TravelRequest> findByStatus(TravelRequestStatus status);

}
