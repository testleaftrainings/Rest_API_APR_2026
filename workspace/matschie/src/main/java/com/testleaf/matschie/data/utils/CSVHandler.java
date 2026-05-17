package com.testleaf.matschie.data.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import com.testleaf.matschie.constant.utils.FilePath;

public class CSVHandler {
	
	private static List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	private static CSVReaderHeaderAware reader;
	private static Map<String, String> row;
	
	public static Iterator<Map<String, String>> getData(String fileName) {
		try {
			reader = new CSVReaderHeaderAware(new FileReader(FilePath.CSV.getFilePath()+fileName+".csv"));
			while ((row = reader.readMap()) != null) {
				data.add(row);								
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		return data.iterator();
	}

}