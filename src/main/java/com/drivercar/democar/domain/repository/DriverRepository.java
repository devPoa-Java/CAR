package com.drivercar.democar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivercar.democar.domain.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long>{

}
