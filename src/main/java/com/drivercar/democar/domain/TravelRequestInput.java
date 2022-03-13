package com.drivercar.democar.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TravelRequestInput {
    @NotNull(message = "O campo passengerId não pode ser nulo")
	Long passengerId;
    @NotEmpty(message = "O campo origin não pode estar em branco")
  	String origin;
    @NotEmpty(message = "O campo destination não pode estar em branco")
	String destination;
	public Long getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passengerId == null) ? 0 : passengerId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TravelRequestInput other = (TravelRequestInput) obj;
		if (passengerId == null) {
			if (other.passengerId != null)
				return false;
		} else if (!passengerId.equals(other.passengerId))
			return false;
		return true;
	}

}
