package com.testleaf.makaia.general.utils;

import com.github.javafaker.Faker;

public class FakerData {
	
	private static Faker faker = new Faker();
	
	public static String generateRandomName() {
		return faker.name().firstName();
	}

}