package com.ihsmarkit.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.ihsmarkit.controller.BookingManager;
import com.ihsmarkit.controller.BookingSystem;

/*
 * This class is intended to test if the booking system is thread safe 
 */
public class MyRunnable implements Runnable {
	BookingManager bm = new BookingSystem();
	Date today;
	Date tmr;

	public void run() {
		System.out.println("Checking today");
		try {
			// Part I
			String threadName = Thread.currentThread().getName();
			tmr = new SimpleDateFormat("yyyy-MM-dd").parse("2012-05-05");
			today = new SimpleDateFormat("yyyy-MM-dd").parse("2012-05-04");
			System.out.println(threadName + " 101(1) " + bm.isRoomAvailable(101, today) + " " + today);
			bm.addBooking("Smith", 101, today);
			System.out.println(threadName + " 101(2) " + bm.isRoomAvailable(101, today) + " " + today);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Part II
		Iterable<Integer> avRoom = bm.getAvailableRooms(today);
		Iterator<Integer> it = avRoom.iterator();
		while (it.hasNext()) {
			System.out.println(Thread.currentThread().getName() + " " + it.next());
		}
	}
}
