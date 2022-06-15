package com.noosh.nooshentry.automation.util;

import java.io.File;

import org.testng.annotations.Test;

public class UtilsTools {
	
//	@Test
	public void test(){
		System.out.println(UtilsTools.class.getClassLoader().getResource("uploadFile/tt.txt").getPath());

	}
		
	public static void deleteScreenshotFiles() {
		String path = System.getProperty("user.dir") + "\\test-output";
		System.out.println("path="+path);
		
		File folder = new File(path);
		// added 6/3
		File[] listOfFiles = folder.listFiles();
		for(File file: listOfFiles) {
			if (file.isFile() && file.getName().startsWith("onboardMessage"))
				file.delete();
		}
	}
	//	endsWith(unicID + ".png"))
}
