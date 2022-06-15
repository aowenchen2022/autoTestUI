package com.noosh.nooshentry.automation.jsErrorCollector;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.noosh.nooshentry.automation.base.BaseSeleniumTest;

public class JavaScriptLog {

	protected static Logger logError = Logger.getLogger(BaseSeleniumTest.class.getName());
	
	public static void logJSError(WebDriver driver, WebDriver buyerDriver) throws Exception {
		/*            */  
		List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
        System.out.println("### start displaying JS errors for SP site");
        logError.info("### start displaying JS errors for SP site ###");
        for(int i = 0; i < jsErrors.size(); i++) {
            System.out.println(jsErrors.get(i).getErrorMessage());
            logError.info(jsErrors.get(i).getErrorMessage());
            System.out.println(jsErrors.get(i).getLineNumber());
            logError.info(jsErrors.get(i).getLineNumber());
            System.out.println(jsErrors.get(i).getSourceName());
            logError.info(jsErrors.get(i).getSourceName());
        }
        System.out.println("### end displaying JS errors for SP site");	
        logError.info("### end displaying JS errors for SP site ###");
	}
}
