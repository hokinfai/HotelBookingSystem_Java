package com.ihsmarkit.controller;

import java.util.Date;

public interface BookingManager {
	/**
	 * Return true if there is no booking for the given room on the date,
	 * otherwise false
	 */
	public boolean isRoomAvailable(Integer room, Date date);

	/**
	 * Add a booking for the given guest in the given room on the given date. If
	 * the room is not available, throw a suitable Exception.
	 */
	public void addBooking(String guest, Integer room, Date date);

	/**
	 * Return a list of all the available room numbers for the given date
	 */
	public Iterable<Integer> getAvailableRooms(Date date);
}
