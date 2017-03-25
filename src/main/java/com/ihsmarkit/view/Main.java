package com.ihsmarkit.view;

import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	// The main thread executed here
	public static void main(String args[]) throws ParseException {
		// Multithreading Testing
		try {
			ExecutorService es = Executors.newFixedThreadPool(3);
			for (int i = 0; i < 3; i++) {
				es.submit(new MyRunnable());
			}
			es.shutdown();
			es.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
