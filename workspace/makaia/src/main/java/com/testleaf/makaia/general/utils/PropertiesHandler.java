package com.testleaf.makaia.general.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.testleaf.makaia.constant.utls.FilePath;

public class PropertiesHandler {
	
	private static Properties properties;

	public static String config(String key) {
		properties = new Properties();
		String value = null;
		try {
			properties.load(new FileInputStream(new File(FilePath.PROPERTIES.getFilePath()+"config.properties")));
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String secret(String key) {
		properties = new Properties();
		String value = null;
		try {
			properties.load(new FileInputStream(new File(FilePath.PROPERTIES.getFilePath()+"secret.properties")));
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(
					"Unable to found "+FilePath.PROPERTIES.getFilePath()+" \"secret.properties\" file in the mentioned location.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}