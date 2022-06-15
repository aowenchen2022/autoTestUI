package com.noosh.nooshentry.automation.demoSQANoosh;

//import java.awt.List;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.noosh.nooshentry.automation.selenium.webdriver.NooshWebDriver;

public class CreateNewProject extends Page {	
    public CreateNewProject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
    
    private WebElement glbTopCreateProject;  
    private WebElement customerNameorEmail;
    private WebElement createProjectProductList;
    private WebElement createProjectSearchUser;
    private WebElement projectName;       
    private WebElement projectCreateBtn;  
    private WebElement createProjectTabReview;
    private WebElement createProjectTabReviewHref;
    private WebElement createProjectReviewProduct1;
    private WebElement createProjectReviewProduct2;
    private WebElement createProjectReviewProduct3;
    private WebElement createWorkspaceProjectProductList;
    
    @FindBy(id="customerProjectName")
    private WebElement customerProjectName;   
    
    private WebElement createProjectName;    
    
    @FindBy(xpath="createNewProject.customerRadio")
    private WebElement customerRadio;
    
    @FindBy(xpath="createNewProject.firstSite")
    private WebElement firstSite;
 
    @FindBy(xpath="createNewProject.postcardProject")
    private WebElement postcardProject;
    
    @FindBy(xpath="createNewProject.poduct")
    private WebElement advProduct;
    
    @FindBy(xpath="createNewProject.resizeCorner")
    private WebElement resizeCorner;  
    
    @FindBy(xpath="createNewProject.poductWork")
    private WebElement form;
    
    @FindBy(xpath="createNewProject.envelope")
    private WebElement envelope;
    
    @FindBy(id="createProjectCollaborateUser")
    private WebElement createProjectCollaborateUserBtn;
   
    @FindBy(id="customerNameorEmail")
    private WebElement inviteClientField;
    
    private WebElement workspaceCustomerProjectName;
    
    private WebElement cancelCreateProject;
    private WebElement createProjectProducts;
    private WebElement createWorkspaceProjectProducts;
    
    public void selectWorkSpace() {
    	driver.findElement(By.className("radio-input")).click();
    }
    
    public void cancelGlbProj() throws InterruptedException {
    	if(cancelCreateProject.isDisplayed()) {
    	    cancelCreateProject.click();
    	} else {
    		System.out.println("'Cancel' is missing");
    	}
        Thread.sleep(2500);
    }
    
    public void inputClientField(String client) throws InterruptedException {
    	if(!inviteClientField.isDisplayed()) {
 	    	throw new IllegalStateException("Invite Client User field is missing!");
 	    } else {
 	    	inviteClientField.clear();
 	    	inviteClientField.sendKeys(client);
 	        Thread.sleep(500);
 	        driver.findElement(By.id("createProjectSearchUser")).click();
 	    }
    }
    
    public void clickClientCollaborateBtn() {
    	createProjectCollaborateUserBtn.click();
    }
    
    public void inputClientUser(String clientUser) {
    	WebElement client = driver.findElement(By.id("globalBuyerAutoComplete")).findElement(By.id("token-input-"));
    	client.clear();
    	client.sendKeys(clientUser);
    	client.sendKeys(",");
    }
    
    public void inputProjectName(String projName) {
    	type(projectName, projName);
    }
    
    public void inputGlbProjectName(String projName) {    //10/24 add
    	type(customerProjectName, projName);
    }
    
    public void inputProjectName1(String projName) {
    	customerProjectName.clear();
    	customerProjectName.sendKeys(projName);
    }
    
    public void inputWSProjectName(String projName) {
    	workspaceCustomerProjectName.clear();
    	workspaceCustomerProjectName.sendKeys(projName);
    }
    
    public void inputClientProjectName(String project) {
    	WebElement input = createProjectName.findElement(By.tagName("input"));
    	input.clear();
    	input.sendKeys(project);
    	//type(createProjectName, project);
    }
    // 10/3
    public void clickCreateAndEstimateProjectBT() {
    	projectCreateBtn.click();
    }
    
    // 10/17
    public void inputCustomerProjectName(String projName) {
    	type(customerProjectName, projName);
    }
    
    //10/17
    public void selectClient() {
    	WebElement client = driver.findElement(By.className("radio-input"));
    	client.click();
    } 
    
    public void clicklCreateProjectBT() {
    	glbTopCreateProject.click();
    }
    
    public void enterValidClientEmail(String email) {
    	type(customerNameorEmail, email);
    }
    public void clickFindBT() {
    	createProjectSearchUser.click();
    }
    public void selectCustomer() {
    	customerRadio.click();	
    }
    public void selectCertainSite() {
    	firstSite.click();
    }
    public void clickPostcard() {
    	postcardProject.click();
    }
    public void clickAdvPoduct(NooshWebDriver driver, String product) {
    	driver.findElementByXPath("createNewProject.poduct", "0", product).click();
    }
    public void resizePopup() {   	
       new Actions(driver).dragAndDropBy(resizeCorner, 25, 105).build().perform();
    }
    
	public void selectAdvForm(NooshWebDriver driver, String projectName) throws InterruptedException {
	new Actions(driver).moveToElement(createProjectProductList).build().perform();
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
			, envelope);
	Thread.sleep(500);
	driver.findElementByXPath("createNewProject.poduct", "0", projectName);
	}
	
	public void verifySF(String sfname) {
		WebElement sf = createProjectTabReview.findElement(By.id("createProjectReviewProduct2"));
		String smartForm = sf.findElement(By.className("legend-replaced")).getText();
		Assert.assertEquals(sfname, smartForm);
	}
	
	public void goToReviewTab() {
		createProjectTabReviewHref.click();
	}
	
	public void verifySFs(String smartF1, String smartF2) throws InterruptedException {
		WebElement sForm1 = createProjectReviewProduct1.findElement(By.className("legend-replaced"));
		WebElement sForm2= createProjectReviewProduct2.findElement(By.className("legend-replaced"));
		String sf1 = sForm1.getText();
		Assert.assertEquals(smartF1, sf1);
		Thread.sleep(1000);		
		String sf2 = sForm2.getText();		
		Assert.assertEquals(smartF2, sf2);
		Thread.sleep(2000);
	}
	
	public void verifyAddedSFs(String smartF1, String smartF2) throws InterruptedException {
		WebElement sForm1= createProjectReviewProduct2.findElement(By.className("legend-replaced"));		
		WebElement sForm2= createProjectReviewProduct3.findElement(By.className("legend-replaced"));
		String sf1 = sForm1.getText();
		Assert.assertEquals(smartF1, sf1);
		Thread.sleep(1000);		
		String sf2 = sForm2.getText();
		Assert.assertEquals(smartF2, sf2);
		Thread.sleep(1000);
	}
	
	public void addSF() {
		driver.findElement(By.className("ui-dialog-buttonset")).findElement(By.tagName("a")).click();
	}
	
	public void verifySFText(String spec) throws InterruptedException {
		String sf = createProjectProducts.findElement(By.className("create-project-product-list-header")).getText();
		Assert.assertEquals(spec, sf);
		Thread.sleep(1000);
	}
	
	public void verifySFHint(String text) throws InterruptedException {
		String hint = createProjectProducts.findElement(By.className("pls-spec-label-1")).getText();
		Assert.assertEquals(text, hint);
		Thread.sleep(1000);
	}
	
	public void verfifySearchSF(String sf) throws InterruptedException {
		WebElement input = createProjectProducts.findElement(By.tagName("input"));
		input.clear();
		input.sendKeys(sf);
		Thread.sleep(1000);
		String res = createProjectProducts.findElement(By.className("product-list-nothing")).getText();
		Assert.assertEquals("No Matches Found", res);
		Thread.sleep(1500);
	}
	
	public void verfifySearchSF(String sf, String sfFullName) throws InterruptedException {
		String imgName = createProjectProductList.findElement(By.tagName("p")).getText();
		WebElement input = createProjectProducts.findElement(By.tagName("input"));
		input.clear();
		input.sendKeys(sf);
		Thread.sleep(1000);
		List<WebElement> images = driver.findElements(By.className("scroll-list-imageContainer"));
		if(images.size() == 1) {
			Assert.assertEquals(sfFullName, imgName);
		} 
		Thread.sleep(1500);
	}
	
	public void verfifySearchSF(String sf, String sfFullName, String sfFullName2, String sfFullName3, String sfFullName4) throws InterruptedException {
		List<WebElement> ps = createProjectProductList.findElements(By.tagName("p"));
		String imgName = ps.get(0).getText();		
		String imgName2 = ps.get(1).getText();
		String imgName3 = ps.get(2).getText();
		String imgName4 = ps.get(3).getText();
		WebElement input = createProjectProducts.findElement(By.tagName("input"));
		input.clear();
		input.sendKeys(sf);
		Thread.sleep(1000);
		List<WebElement> images = driver.findElements(By.className("scroll-list-imageContainer"));
		if(images.size() == 4) {
			Assert.assertEquals(sfFullName, imgName);
			Assert.assertEquals(sfFullName2, imgName2);
			Assert.assertEquals(sfFullName3, imgName3);
			Assert.assertEquals(sfFullName4, imgName4);
		}
		Thread.sleep(1500);
	}
	
	public void verifySFTextWS(String spec) throws InterruptedException {
		String sf = createWorkspaceProjectProducts.findElement(By.className("create-project-product-list-header")).getText();
		Assert.assertEquals(spec, sf);
		Thread.sleep(1000);
	}
	
	public void verfySFHintWS(String text) throws InterruptedException {
		String hint = createWorkspaceProjectProducts.findElement(By.className("pls-spec-label-1")).getText();
		Assert.assertEquals(text, hint);
		Thread.sleep(1000);
	}
	
	public void verfifySearchSFWS(String sf, String sfFullName) throws InterruptedException {
		String imgName = createWorkspaceProjectProductList.findElement(By.tagName("p")).getText();
		WebElement input = createWorkspaceProjectProducts.findElement(By.tagName("input"));
		input.clear();
		input.sendKeys(sf);
		Thread.sleep(1000);
		List<WebElement> images = driver.findElements(By.className("scroll-list-imageContainer"));
		if(images.size() == 1) {
			Assert.assertEquals(sfFullName, imgName);
		} 
		Thread.sleep(1500);
	}
	
	public void verfifySearchSFWS(String sf, String sfFullName, String sfFullName2, String sfFullName3, String sfFullName4) throws InterruptedException {
		List<WebElement> ps = createWorkspaceProjectProductList.findElements(By.tagName("p"));
		String imgName = ps.get(0).getText();		
		String imgName2 = ps.get(1).getText();
		System.out.println("imgName2="+imgName2);
		String imgName3 = ps.get(2).getText();
		String imgName4 = ps.get(3).getText();
		WebElement input = createWorkspaceProjectProducts.findElement(By.tagName("input"));
		input.clear();
		input.sendKeys(sf);
		Thread.sleep(1000);
		List<WebElement> images = driver.findElements(By.className("scroll-list-imageContainer"));
		if(images.size() == 4) {
			Assert.assertEquals(sfFullName, imgName);
			Assert.assertEquals(sfFullName2, imgName2);
			Assert.assertEquals(sfFullName3, imgName3);
			Assert.assertEquals(sfFullName4, imgName4);
		}
		Thread.sleep(1500);
	}
	
	public void verfifySearchSFWS(String sf) throws InterruptedException {
		WebElement input = createWorkspaceProjectProducts.findElement(By.tagName("input"));
		input.clear();
		input.sendKeys(sf);
		Thread.sleep(1000);
		String res = createWorkspaceProjectProducts.findElement(By.className("product-list-nothing")).getText();
		Assert.assertEquals("No Matches Found", res);
		Thread.sleep(1500);
	}
	
}
