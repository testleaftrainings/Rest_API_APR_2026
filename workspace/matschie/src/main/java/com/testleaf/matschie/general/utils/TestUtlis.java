package com.testleaf.matschie.general.utils;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtlis {
	
	private static Map<String, Object> testData = new HashMap<>();
	
	public static void setTestData(String key, Object value) {
		testData.put(key, value);
	}
	
	public static void setTestContext(String key, Object value) {
		testData.put(key, value);
	}
	
	public static RequestSpecification getRequestSpecification() {
		return (RequestSpecification) testData.get("requestSpec");
	}
	
	public static void setResponse(Response value) {
		testData.put("response", value);
	}
	
	public static String getTestData(String key) {
		return (String) testData.getOrDefault(key, "Not Found");
	}
	
	public static Response getResponse() {
		return (Response) testData.getOrDefault("response", "Not Found");
	}

}