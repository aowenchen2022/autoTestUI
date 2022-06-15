package com.noosh.nooshentry.automation.nooshOne;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class UserProfileFrame extends Page
{

    public UserProfileFrame(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
    
    // Get buyer profile    
    @FindBy(className="bHead-menu-arrow-dropdown")
    private WebElement profileArrowBt;
    
    @FindBy(id="bHead-user-image")
    private WebElement userProfile;

    @FindBy(xpath="buyerProfileInfo.userProfile") 
    private WebElement userProfileItem;
    
    private WebElement profileLoginErrorCheckMSG; 
    private WebElement profileEmailErrorCheckMSG;
    
    // Update Profile elements
    private WebElement headProfileImage;
    private WebElement profileEmailInput;
    private WebElement profileLoginNameInput;
    private WebElement lastNameInput;
    private WebElement topMenuProfile;
    private WebElement topMenuBook;
    
    @FindBy(id="profileUpdate-edit")
    private WebElement editProfileBT;   
                
    @FindBy(id="profileUpdate-save")
    private WebElement saveProfileBT; 
    
    @FindBy(id="profileUpdate-cancel")
    private WebElement cancelEditProfileBT;
    
    // Change profile image
    private WebElement userProfilePicture;
    
    // Reset password
    @CacheLookup
    private WebElement changePasswordBtn;
    
    @FindBy(xpath="userProfileFrame.originalPass")
    private WebElement nowPassword;
    
    @FindBy(xpath="userProfileFrame.passwordOnce")
    private WebElement firstPass;
    
    @FindBy(xpath="userProfileFrame.passwordTwice")
    private WebElement secondPass;
    
    private WebElement originalPassword;
    private WebElement passwordOnce;
    private WebElement passwordTwice;
    private WebElement changePasswordSubmit;
    private WebElement fileProfilePicture;  
    
    /*
     * Update Profile elements
     */
    public void clickProfileUserName() throws InterruptedException {
    	Actions builder = new Actions(driver);
    	topMenuBook.click();
    	Thread.sleep(1000);
    	builder.moveToElement(topMenuBook).build().perform();
    	Thread.sleep(5000);
    	topMenuProfile.click();
    	Thread.sleep(5000);
    }    
    public void clickEditProfileBT() {
    	editProfileBT.click();
    }    
    public void clearLoginNameField() {
    	profileLoginNameInput.clear();
    }
    public void clickSaveProfileBT() {
    	saveProfileBT.click();
    }
    public void clearEmailField() {
    	profileEmailInput.clear();
    }
    // does not use
    public void setLoginName(String newLoginName) throws InterruptedException {
 	    Thread.sleep(200);
 	    profileLoginNameInput.clear();
 	    profileLoginNameInput.sendKeys(newLoginName);
    }
    // does not use
    public void setEmailAddress(String newEmailName) throws InterruptedException {
 	    Thread.sleep(200);
 	    profileEmailInput.clear();
 	    profileEmailInput.sendKeys(newEmailName);
    }
    public void setNewLastName(String newLastName) throws InterruptedException {
 	    Thread.sleep(300);
 	    lastNameInput.clear();
 	    lastNameInput.sendKeys(newLastName);
 //   	type(lastName, newLastName);
    }        
    public void clickCanselProfileEditBT() {
    	cancelEditProfileBT.click();
    }
    
    /*
     * Download new profile image
     */
    public void clickChangProfilePicture(String newProfilePicture) throws AWTException, InterruptedException
    {
  	uploadFileRelative(fileProfilePicture, newProfilePicture);  
  	Thread.sleep(2000);
    //uploadFileRelative(userProfilePicture, newProfilePicture);  //add 11/14
    }
    public String getImageAttribute() {
    	return userProfilePicture.getAttribute("src");
    }
    public String getHeadProfileImageAttribute() {
    	return headProfileImage.getAttribute("src");
    }
    
    /*
     * Reset password
     */
    public void clickResetPassword() {
    	changePasswordBtn.click();
    }
    public void setOriginalPassword(String passwd) {
    	type(originalPassword, passwd);   
    }
    public void setNewPassword(String newPasswd) {
    	type(passwordOnce, newPasswd);  
    }
    public void setConfirmPassword(String newPasswd) {
    	type(passwordTwice, newPasswd);  
    }    
    public void clickChangePasswordBT(){
        changePasswordSubmit.click();
    }
    
    // Get buyer profile popup
    public void getBuyerProfile() throws InterruptedException {
 	   userProfile.click();
    }    
    public void getBuyerProfileIE() throws InterruptedException {
    	profileArrowBt.click();
 	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userProfileItem);	   
    }    
    public String getBuyerLoginName() {
 	   return profileLoginNameInput.getAttribute("value");
    }   
    public boolean getLoginNameError() {
 	   return profileLoginErrorCheckMSG.isDisplayed();
    }    
    public boolean getEmailProfileError() {
 	   return profileEmailErrorCheckMSG.isDisplayed();
    }    
    public String getBuyerEmail() {
 	   return profileEmailInput.getAttribute("value");
    }
}
