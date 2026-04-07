package com.parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	private Properties properties;
	
	public PropertiesReader() {

		properties = new Properties();

		try (FileInputStream fis = new FileInputStream("src/test/resources/Config.properties")) {

			properties.load(fis);

		} catch (IOException e) {
 
			e.printStackTrace();

		}

	}

	public String getProperty(String key) {

		return properties.getProperty(key);

	}
}