package com.testleaf.makaia.constant.utls;

public enum FilePath {
	
	JSON("src/test/resources/test-data/csv"),
	CSV("src/test/resources/test-data/json"),
	PROPERTIES("src/test/resources/");
	
	private final String filePath;
	
	FilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}

}