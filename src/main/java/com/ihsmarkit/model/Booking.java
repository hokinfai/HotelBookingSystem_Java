package com.ihsmarkit.model;

import java.util.Date;

/*This custom class is defined as the key of the hashmap of controller*/
public class Booking {
	// The Room Number
	private volatile int roomNo;
	// Booking Date
	private volatile Date occupiedDate;

	public Booking(int roomNo, Date occupiedDate) {
		super();
		this.roomNo = roomNo;
		this.occupiedDate = occupiedDate;
	}

	public Date getOccupiedDate() {
		return occupiedDate;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setOccupiedDate(Date occupiedDate) {
		this.occupiedDate = occupiedDate;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	/*
	 * As the room number and booking date are assumed to be unique in this
	 * exercise (composite primary key), we don't wanna have different objects
	 * having the same roomNo and BookingDate. If new booking comes and has the
	 * same value, we have to compare the object and existing objects. No new
	 * object is created if the same roomNo and bookingDate are used. Therefore,
	 * equals() and hashcode() have to be overriden.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (occupiedDate == null) {
			if (other.occupiedDate != null)
				return false;
		} else if (!occupiedDate.equals(other.occupiedDate))
			return false;
		if (roomNo != other.roomNo)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((occupiedDate == null) ? 0 : occupiedDate.hashCode());
		result = prime * result + roomNo;
		return result;
	}

	@Override
	public String toString() {
		return "Booking [roomNo=" + roomNo + ", occupiedDate=" + occupiedDate + "]";
	}

}
