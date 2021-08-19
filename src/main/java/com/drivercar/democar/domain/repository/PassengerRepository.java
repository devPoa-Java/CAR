package com.drivercar.democar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivercar.democar.domain.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
