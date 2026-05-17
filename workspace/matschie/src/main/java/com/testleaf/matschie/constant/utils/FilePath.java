package com.testleaf.matschie.constant.utils;

public enum FilePath {
	
	JSON("src/test/resources/test-data/json/"),
	CSV("src/test/resources/test-data/csv/");
	
	private final String filePath;
	
	FilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}

}