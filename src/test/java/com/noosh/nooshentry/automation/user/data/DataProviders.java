package com.noosh.nooshentry.automation.user.data;

import java.io.File;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	static File file = new File("C:\\tmp\\Data.xls");
	static HandleExcelTable excelData = new HandleExcelTable();
	
	@DataProvider(name = "TestCase5.1")
	public static Object[][] dataDownloadLoginPageImages() throws Exception{
	       Object[][] retObjArr = excelData.getTableArray(file.getAbsolutePath(), "5.x Test cases", "imageFile");
	       return(retObjArr);
	}
	
	@DataProvider(name = "TestCase5.2")
	public static Object[][] dataLoginPageCustomization() throws Exception{
	       Object[][] retObjArr = excelData.getTableArray(file.getAbsolutePath(), "5.x Test cases", "loginBoxColors");
	       return(retObjArr);
	}
	
	@DataProvider(name = "TestCase5.3")
	public static Object[][] dataHeaderCustomization() throws Exception{
	       Object[][] retObjArr = excelData.getTableArray(file.getAbsolutePath(), "5.x Test cases", "headerColors");
	       return(retObjArr);
	}

	@DataProvider(name = "TestCase6.1")
	public static Object[][] dataProfileInfoUpdate() throws Exception{
	       Object[][] retObjArr = excelData.getTableArray(file.getAbsolutePath(), "6.x Test cases", "lastName");
	       return(retObjArr);
	}
	
	@DataProvider(name = "TestCase6.2")
	public static Object[][] dataDownloadNewProfileImage() throws Exception{
	       Object[][] retObjArr = excelData.getTableArray(file.getAbsolutePath(), "6.x Test cases", "newProfileImege");
	       return(retObjArr);
	}

	@DataProvider(name = "TestCase6.3")
	public static Object[][] dataResetPassword() throws Exception{
	       Object[][] retObjArr = excelData.getTableArray(file.getAbsolutePath(), "6.x Test cases", "resetPassword");
	       return(retObjArr);
	}
	
	@DataProvider(name = "TestCase8.1")
	public static Object[][] dataCreateSite() throws Exception{
	       Object[][] retObjArr = excelData.getTableArray(file.getAbsolutePath(), "8.x Test cases", "newSite");
	       return(retObjArr);
	}
}

