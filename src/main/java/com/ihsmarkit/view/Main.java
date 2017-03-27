package com.ihsmarkit.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ihsmarkit.controller.BookingSystem;

public class Main {
	// The main thread executed here
	public static void main(String args[]) throws ParseException {
		// Multithreading Testing
		String[] abc = { "alan", "banana", "cat", "dog", "eat", "fat", "glass", "hat", "idea", "jack", "kirana", "lim",
				"m&m", "nail", "sugar", "alan", "banana", "cat", "dog", "eat", "fat", "glass", "hat", "idea", "jack",
				"kirana", "lim", "m&m", "nail", "sugar" };
		try {
			ExecutorService es = Executors.newFixedThreadPool(30);
			for (int i = 0; i < 30; i++) {
				es.submit(new MyRunnable(abc[i]));
			}
			es.shutdown();
			es.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
