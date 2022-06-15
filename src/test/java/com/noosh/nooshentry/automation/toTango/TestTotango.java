package com.noosh.nooshentry.automation.toTango;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class TestTotango extends BaseSeleniumTest
{
	
    @Test(description = "Login to Totango")
    @Parameters({"email", "password"})
    public void testLoginTotango(String email, String password) throws InterruptedException
    {    	
    	ToTango totango = new ToTango(driver);	
    	
        driver.get("https://app.totango.com/login.html");	       
        driver.manage().window().maximize();
 	    Thread.sleep(1000); 
    	totango.loginToTango(email, password);
 	    Thread.sleep(1500); 
    }	

    @Test(description = "Search account")
    @Parameters({"account"})
    public void testSearchTotango(String account) throws InterruptedException
    {    	
    	ToTango totango = new ToTango(driver);	
    	
    	totango.searchText(account);
 	    Thread.sleep(1500); 
    }	
    
    @Test(description = "Get time activities")
    public void testGetTimeActivities() throws InterruptedException
    {    	
    	ToTango totango = new ToTango(driver);	
    	Page page = new Page(driver);
    	System.out.println("----------------------------------------------- Totango time result ----------------------------------------------------");
    	log.info("----------------------------------------------- Totango time result ----------------------------------------------------");
    	System.out.println("Started: " + totango.getTimeStarted());
    	log.info("Started: " + totango.getTimeStarted());
    	System.out.println("for " + totango.getForTime());
    	log.info("for " + totango.getForTime());
    	page.takeScreenshot1(driver, "totango_");
 	    Thread.sleep(20000); 
    }	
    
    @Test(description = "Logout")
    @Parameters({"email"}) 
    public void testLogout(String userName) throws InterruptedException
    {
    	ToTango totango = new ToTango(driver);	
    	
    	totango.logout(driver, userName);
    }
}
