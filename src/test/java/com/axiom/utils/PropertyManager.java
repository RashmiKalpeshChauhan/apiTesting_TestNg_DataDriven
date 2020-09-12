package com.axiom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	InputStream inputStream;
	protected static Properties props;

	public String readPropertyData(String parameter) throws IOException {
		String propFileName="config.properties";
		props =new Properties();
		inputStream=getClass().getClassLoader().getResourceAsStream(propFileName);
		props.load(inputStream);
		return props.getProperty(parameter);	
	}
}
