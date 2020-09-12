package com.axiom.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataGenerators {
@DataProvider(name="Excel")
public static Object[][] testDataGenerator() throws IOException{
	String proj=System.getProperty("user.dir");
	String excelPath=proj+"\\src\\main\\resources\\EmployeeData.xlsx";
	ExcelUtils elcelHelper= new ExcelUtils();
	List<HashMap<String,String>> dataFromFile=elcelHelper.readSheet(excelPath, "sheet1");
	Object[] [] data=new Object[dataFromFile.size()][1];
	for(int i=0;i<data.length;i++) {
		data[i][0]=dataFromFile.get(i);		
	}
	return data;
}
}
