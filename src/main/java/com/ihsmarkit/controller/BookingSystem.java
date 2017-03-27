package com.ihsmarkit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ihsmarkit.exception.DuplicateBookingException;
import com.ihsmarkit.model.Booking;

public class BookingSystem implements BookingManager {
	// Declare the number of rooms in the hotel
	int[] rooms;

	/*
	 * This HashMap is running multithreading environment, therefore
	 * concurrentHashMap is used. (ps. ConcurrentHashMap performs better then
	 * HashTable). Besides this, The Map acts as a database among all the
	 * created objects, so we need to use static variable. PS. It is possible to
	 * use "nested HashMap" instead of using "Booking" object as the key.
	 */

	static Map<Booking, String> bookingDatabase = new ConcurrentHashMap<Booking, String>();

	public BookingSystem() {
		/*
		 * set the room number in the hotel. Developers can easily add more
		 * items into this array to increase the number of rooms
		 */
		rooms = new int[] { 101, 102, 201, 203 };
	}

	/*
	 * This methods is marked as synchronized as they are going to see the
	 * modified data or modify the bookingDatabase.
	 */
	public boolean isRoomAvailable(Integer room, Date date) {
		if (bookingDatabase.containsKey(new Booking(room, date))) {
			return false;
		}
		return true;
	}

	/*
	 * this method needs to be synchronized if more than one threads will be
	 * able to change/modify the critical section (adding booking into the
	 * concurrenthashmap).
	 */
	public void addBooking(String guest, Integer room, Date date) {
		boolean available = isRoomAvailable(room, date);
		if (available) {
			synchronized (bookingDatabase) {
				bookingDatabase.put(new Booking(room, date), guest);
				System.out.println(Thread.currentThread().getName() + "\t" + guest + " Room booked Successfully");
			}
		} else {
			try {
				throw new DuplicateBookingException(
						Thread.currentThread().getName() + "\t" + guest + " Room booked already");
			} catch (DuplicateBookingException e) {
				System.err.println(e);
			}
		}
	}

	public void printGuest(Integer room, Date date) {
		System.out.println(bookingDatabase.get(new Booking(room, date)));
	}

	/*
	 * This method does not need to mark to be synchronized block as it is just
	 * simply reading data from the array (room number).
	 */
	public Iterable<Integer> getAvailableRooms(Date date) {
		List<Integer> availableRoom = new ArrayList<Integer>();
		for (int i = 0; i < rooms.length; i++) {
			if (isRoomAvailable(rooms[i], date)) {
				availableRoom.add(rooms[i]);
			}
		}
		return availableRoom;
	}

}
