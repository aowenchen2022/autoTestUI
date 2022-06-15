package com.noosh.nooshentry.automation.buyerSite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class ClientCreateProjectPage extends Page {
	public ClientCreateProjectPage(WebDriver driver) {
	     super(driver);
	     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}
	
	private WebElement cancel;
	private WebElement cpNextStep;
	private WebElement cpDraftButton;
	
	@FindBy(id="cpAddProducts")
	private WebElement addSFBtn;
	
	@FindBy(id="cpNextStep")
	private WebElement createProjBtn;
	
	public void cancelProj() {
		cancel.findElement(By.className("sp-createProject-cancel"));
	}
	
	public void clickNext() {
		cpNextStep.click();
	}
	
	public void addSmartFormBtn() {
		addSFBtn.click();
	}
	
	public void clickCreateProjBtn() {
		createProjBtn.click();
	}
	
	public void saveDraft() throws InterruptedException {
		if(cpDraftButton.isDisplayed()){
			cpDraftButton.click();
			Thread.sleep(5000);
		} else {
			System.out.println("Save Draft btn is missing.");
		}
	}
}

