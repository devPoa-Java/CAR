package com.drivercar.democar.domain;

import java.util.Date;

public class TravelRequestOutput{

	    Long Id;
	    String origin;
		String destination;
		TravelRequestStatus status;
		Date creationDate;
		
		public TravelRequestOutput() {
			
		}
		

		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
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
		public TravelRequestStatus getStatus() {
			return status;
		}
		public void setStatus(TravelRequestStatus status) {
			this.status = status;
		}
		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
			TravelRequestOutput other = (TravelRequestOutput) obj;
			if (Id == null) {
				if (other.Id != null)
					return false;
			} else if (!Id.equals(other.Id))
				return false;
			return true;
		}	

}
