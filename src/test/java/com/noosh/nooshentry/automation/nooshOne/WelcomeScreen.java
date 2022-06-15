package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class WelcomeScreen extends Page
{
	public WelcomeScreen(WebDriver driver)
	   {
	       super(driver);
	       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	   } 
	
	private WebElement landingCreateProject;
	private WebElement clientCompanyName;
	private WebElement createProjectProductUl;
	private WebElement createWebpageName;
	private WebElement clientCompayLogo;
	
	@FindBy(name="projectName")
	private WebElement projectName;
	
	@FindBy(xpath="welcomePage.all")
	private WebElement allForm;
	
	@FindBy(xpath="welcomePage.envelope")
	private WebElement envelope;
	
	@FindBy(xpath="welcomePage.createProject")
	private WebElement createProject;	
	
	@FindBy(xpath="welcomePage.createWorkspace")
	private WebElement createWorkspace;	
	
	public void cliclCreateProject() {
		landingCreateProject.click();
	}
	
	public void setProjectName(String project) {
		type(projectName, project);
	}
	
	public void setClientCompanyName(String name) {
		type(clientCompanyName, name);
	}
	
	public void clickAllForm() {
		allForm.click();
	}
	
	public void selectEnvelopeProduct() throws InterruptedException {
	new Actions(driver).moveToElement(createProjectProductUl).build().perform();
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
			,envelope);
	Thread.sleep(500);
	envelope.click();
	}	
	
	public void clickCreateProjectBT() {
		createProject.click();
	}
	
	public void setClientWorkspace(String workspaceName) {
		type(createWebpageName, workspaceName);
	}
	
	public void setClientWorkspaceLogo(String logo) {
		uploadFileRelative(clientCompayLogo, logo);
	}
	
	public void clickCreateWorkspaceBT() {
		createWorkspace.click();
	}
}
