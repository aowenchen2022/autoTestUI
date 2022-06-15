package com.noosh.nooshentry.automation.buyerSite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterBuyerPage extends Page
{
   public RegisterBuyerPage(WebDriver driver)
   {
       super(driver);
       PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
   }
   
   @FindBy(id="firstName")
   private WebElement buyerFirstName;
   
   @FindBy(id="lastName")
   private WebElement buyerLastName;
   
   @FindBy(css="input#password.required")
   private WebElement password;
   
   @FindBy(id="phoneNumber")
   private WebElement phoneBuyer; 
   
   @FindBy(css="a.tosTrigger b")
   private WebElement agreement;
   
   @FindBy(id="okayTos")
   private WebElement acceptBT;
   
   private WebElement sliderButton;
   
   @FindBy(id="signup_b")
   private WebElement submitBT;
   
   public void buyerCredential(String firstNameBuyer, String lastNameBuyer,
      String passwordBuyer, String phoneNumberBuyer)
   {
      buyerFirstName.clear();
      buyerFirstName.sendKeys(firstNameBuyer);
      buyerLastName.clear();
      buyerLastName.sendKeys(lastNameBuyer);
      password.clear();
      password.sendKeys(passwordBuyer);
      phoneBuyer.clear();
      phoneBuyer.sendKeys(phoneNumberBuyer);
   }
   
   public void getAgreementPopup()
   {
      agreement.click();
//      return new AgreementPopup(driver);
   }
   
   public void acceptAgreement()
   {
      acceptBT.click();
   }
   
   public void moveSlider()
   {
      new Actions(driver).dragAndDropBy(sliderButton, 180, 0).build().perform();
   }
   
   public void signupRegister()
   {
      submitBT.click();
   }
}
