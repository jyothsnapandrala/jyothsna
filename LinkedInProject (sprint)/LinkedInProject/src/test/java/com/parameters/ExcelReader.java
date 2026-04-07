package com.parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    private static final String FILE_PATH = System.getProperty("user.dir") + "//src//test//resources//Exceldata//Data.xlsx";
    private static XSSFWorkbook workbook;

    private static XSSFSheet getSheet() throws IOException {
        if (workbook == null) {
            try (FileInputStream fis = new FileInputStream(new File(FILE_PATH))) {
                workbook = new XSSFWorkbook(fis);
            }
        }
        return workbook.getSheetAt(0);
    }

    public static String getUrl() throws IOException {
        XSSFSheet sheet = getSheet();
        return sheet.getRow(1).getCell(0).getStringCellValue();
    }

    public static String getfieldName() throws IOException {
        XSSFSheet sheet = getSheet();
        return sheet.getRow(3).getCell(0).getStringCellValue();
    }
    public static String getheadline() throws IOException {
    	XSSFSheet sheet = getSheet();
        return sheet.getRow(5).getCell(0).getStringCellValue();

   }

   //-----------------------------------------------sheet2----------------------------------------------------------
//    private static XSSFSheet getSheet1() throws IOException {
//        if (workbook == null) {
//            try (FileInputStream fis = new FileInputStream(new File(FILE_PATH))) {
//                workbook = new XSSFWorkbook(fis);
//            }
//        }
//        return workbook.getSheetAt(1);
//    }
//
//    public static String[] getheadline() throws IOException {
//        XSSFSheet sheet = getSheet1();
//        String[] headlines=new String[3];
//        for(int i=0;i<3;i++) {
//        	headlines[i]=sheet.getRow(i).getCell(0).getStringCellValue();
//        	
//        }
//        return headlines;
//    }
//
//   
 
}
