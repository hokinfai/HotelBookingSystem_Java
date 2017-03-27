package com.ihsmarkit.view;

import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	// The main thread executed here
	public static void main(String args[]) throws ParseException {
		// Multithreading Testing
		String[] abc = { "alan", "banana", "cat", "dog", "eat", "fat", "glass", "hat", "idea", "jack", "alan", "banana",
				"cat", "dog", "eat", "fat", "glass", "hat", "idea", "jack" };
		try {
			ExecutorService es = Executors.newFixedThreadPool(2);
			for (int i = 0; i < 2; i++) {
				es.submit(new MyRunnable(abc[i % 10]));
			}
			es.shutdown();
			es.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
