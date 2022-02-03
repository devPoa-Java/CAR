package com.drivercar.democar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivercar.democar.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
