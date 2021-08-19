package com.drivercar.democar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivercar.democar.domain.model.TravelRequest;

public interface TravelRequestRepository  extends JpaRepository<TravelRequest, Long>{

}
