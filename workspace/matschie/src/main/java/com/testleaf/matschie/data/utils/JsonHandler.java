package com.testleaf.matschie.data.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testleaf.matschie.constant.utils.FilePath;

public class JsonHandler {
	
	private static Gson gson = new Gson();
	private static List<Map<String, String>> data;
	
	public static Iterator<Map<String, String>> getData(String fileName) {		
		try (FileReader reader = new FileReader(FilePath.JSON.getFilePath()+fileName+".json")) {           
            Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
            data = gson.fromJson(reader, type);            
        } catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return data.iterator();
	}

}