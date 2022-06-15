/***********************************************************************
* This class tests the functionality for Reports 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import java.io.FileNotFoundException;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.util.Hashtable;
public class NGEreports extends BaseSeleniumTest {  
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
   }

   @AfterTest
   public static void cleanup() {
	   driver.quit();
   }
   
   //test code here
}
    
   
    
   
    
    
    
    
    
   