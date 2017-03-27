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
	 * This methods is going to see if the room is still available
	 */
	public boolean isRoomAvailable(Integer room, Date date) {
		if (bookingDatabase.containsKey(new Booking(room, date))) {
			return false;
		}
		return true;
	}

	/*
	 * put operation of concurrentHashMap is not locked, which means while one
	 * thread is putting value, other thread's get() call can still return null
	 * resulting in one thread overriding value inserted by other thread. Using
	 * synchronized block will make it thread-safe but that will only make the
	 * code single threaded. ConcurrentHashMap provides putIfAbsent(key, value)
	 * which does same thing but atomically and thus eliminates above race
	 * condition.
	 */
	public void addBooking(String guest, Integer room, Date date) {
		Booking newBooking = new Booking(room, date);
		if (bookingDatabase.get(newBooking) == null) {
			bookingDatabase.putIfAbsent(newBooking, guest);
		} else {
			try {
				throw new DuplicateBookingException(
						Thread.currentThread().getName() + "\t" + guest + " Room booked already");
			} catch (DuplicateBookingException e) {
				System.err.println(e);
			}
		}
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
