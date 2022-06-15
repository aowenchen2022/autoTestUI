package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashboardDemoPage  extends Page
{
	   public DashboardDemoPage(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(driver, this);
	   }
}
