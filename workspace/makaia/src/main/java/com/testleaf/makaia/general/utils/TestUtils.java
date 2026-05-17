package com.testleaf.makaia.general.utils;

import java.util.HashMap;
import java.util.Map;

public class TestUtils {
	
	private static Map<String, String> testData = new HashMap<String, String>();
	
	public static void setTestData(String key, String value) {
		testData.put(key, value);
	}
	
	public static String getTestData(String key) {
		return testData.getOrDefault(key, "Not Found");
	}

}