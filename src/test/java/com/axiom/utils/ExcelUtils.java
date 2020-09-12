package com.axiom.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import groovyjarjarasm.asm.tree.TryCatchBlockNode;

public class ExcelUtils {
	public static List<HashMap<String,String>>readSheet(String fileName, String sheetName) throws IOException{
		XSSFWorkbook workBook=null;
		XSSFSheet sheet=null;
		DataFormatter formatter=null;
		FileInputStream file=null;
		try {
			file=new FileInputStream(fileName);
			workBook=new XSSFWorkbook(file);
			sheet=workBook.getSheet(sheetName);
			formatter=new DataFormatter();
			int rows= sheet.getPhysicalNumberOfRows();
			XSSFRow row=sheet.getRow(0);
			int cols=row.getLastCellNum();
			List<String>headers=new ArrayList<String>();
			for (int i = 0; i < cols; i++) {
				headers.add(row.getCell(i).getStringCellValue());
			}
			//Adding data
			List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
			for (int i = 1; i < rows;i++) {
				HashMap<String,String>data=new HashMap<String, String>();
				row=sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					data.put(headers.get(j).trim(), formatter.formatCellValue(row.getCell(j)));
					//data.put(headers.get(j).trim(), formatter.formatCellValue(row.getCell(j)));
				}
				list.add(data);
			}
			return list;
			
		} finally {
			if(workBook !=null) {
				try {
					workBook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		String proj=System.getProperty("user.dir");
		String excelPath=proj+"\\src\\main\\resources\\EmployeeData.xlsx";
		readSheet(excelPath, "sheet1");
		System.out.println("this ");
	}	

}
