package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class ClientEditProjectPage extends Page {
	public ClientEditProjectPage(WebDriver driver) {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}
	
	private WebElement jobTabInfo;
	private WebElement cpDraftButton;
	
	public void clickProj() throws InterruptedException {
		jobTabInfo.findElement(By.className("line-project-name")).click();
		Thread.sleep(2000);
	}
	
	public void saveDraft() {
		cpDraftButton.click();
	}
}


