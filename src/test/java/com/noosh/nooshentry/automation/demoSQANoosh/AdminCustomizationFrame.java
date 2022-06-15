package com.noosh.nooshentry.automation.demoSQANoosh;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdminCustomizationFrame extends Page
{
   public AdminCustomizationFrame(WebDriver driver)
   {
      super(driver);
      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
   }

   // Images customization elements --------->
   private WebElement headImgDom;
   private WebElement headImgLogoDom; 
   private WebElement headImgPreviewDom; 
   private WebElement spHeaderLogo;    
   private WebElement loginImgPreviewDom;
   
//   @FindBy(xpath="//div[text()='Upload Images']")
   @FindBy(xpath="adminCustomizationFrame.uploadImagesBT")
   private WebElement uploadImagesBT; 
   
   @FindBy(id="LoginImgLogoDom")
   private WebElement loginImgLogoDom;
      
   @FindBy(className="sp-headerRefDash")
   private WebElement headImgDashboardPreview;
   
   @FindBy(linkText="Dashboard Preview")
   private WebElement dashboardPreviewLink;
   
   @FindBy(linkText="Login Preview")
   private WebElement loginPreviewLink;
   
   @FindBy(xpath="adminCustomizationFrame.loginPreview")
   private WebElement loginPreview;
   
   private WebElement spLoginImage;   
   private WebElement saveCustomPicBtn;  
     
   // Login customization elements ---------->
   private WebElement spLoginBoxColorS1Input; 
   private WebElement saveLoginColors;
   
//   @FindBy(xpath="//span[@id='sploginBoxColorDisplay']")
   @FindBy(xpath="adminCustomizationFrame.sploginBoxColorDisplay1")
   private WebElement sploginBoxColorDisplay1;
   
   private WebElement spLoginBoxTextColorInput;  
   private WebElement spLoginBoxTextColorDisplay;
   
   @FindBy(linkText="Login Customization")
   private WebElement loginCustomizationTab;
   
//   @FindBy(xpath="//div[text()='Login Box']")
   @FindBy(xpath="adminCustomizationFrame.loginBoxPreview")
   private WebElement loginBoxPreview;
   
   @CacheLookup
//   @FindBy(xpath="//div[text()='Edit Colors']")
   @FindBy(xpath="adminCustomizationFrame.editColorsBT")
   private WebElement editColorsBT;
   
   // Header customization elements --------->
   private WebElement spHeaderBgColorInput;
   private WebElement spHeaderTextColorInput;
   private WebElement spMenuBgColorInput;
   private WebElement spMenuTextColorInput;
   private WebElement saveHeadColors;
   private WebElement spHeaderBgColorDisplay;
   private WebElement spHeaderTextColorDisplay;
   private WebElement spMenuBgColorDisplay;
   private WebElement spMenuTextColorDisplay;
   
   @FindBy(linkText="Header Customization")
   private WebElement headerCustomizationTab;
   
//   @FindBy(xpath="//div[@class='sp-simple-button custom-headerEdit-button']")
   @FindBy(xpath="adminCustomizationFrame.editBT")
   private WebElement editBT;
   
   /*
    * Images customization --------------->
    */
   public boolean checkLogoImageMain(String imageFileName)
   {	   
	   return checkImageDisplayed(headImgLogoDom, imageFileName);
   } 
   public boolean checkLogoImage(String imageFileName)
   {
	   return checkImageDisplayed(headImgDom, imageFileName);
   }
   public boolean checkLogoImagePreview(String imageFileName)
   {
	   return checkImageDisplayed(headImgPreviewDom, imageFileName);
   } 
   public boolean checkLogoImageDashboardPreview(String imageFileName)
   {
	   return checkImageDisplayed(headImgDashboardPreview, imageFileName);
   }   
   public boolean checkLoginContentImage(String imageFileName)
   {
	   return checkImageDisplayed(loginImgPreviewDom, imageFileName);
   }   
   public boolean checkLoginContantImagePreview(String imageFileName)
   {
	   return checkImageDisplayed(loginImgLogoDom, imageFileName);
   }  
   public void clickDashboardPreviewLink()
   {
	   dashboardPreviewLink.click();
   } 
   public void clickLoginPreviewLink() {
	   loginPreviewLink.click();
   }
   public void clickUploadImagesBT()
   {
	   uploadImagesBT.click();
   } 
   public void uploadHeaderLogo(String headerLogo)
   {
	   uploadFileRelative(spHeaderLogo, headerLogo);
   }  
   public void uploadLoginContentImage(String loginContentImage)
   {
	   uploadFileRelative(spLoginImage, loginContentImage);
   }   
   public void clickSaveImagesBT()
   {
	   saveCustomPicBtn.click();
   }
   
   // images verification for smoke test
   public boolean checkLogoImageCustomTab()
   {
	   return checkImageDisplayedSize(headImgLogoDom);
   }
   public boolean checkLogoImagePreviewTab()
   {
	   return checkImageDisplayedSize(headImgPreviewDom);
   }
   public boolean checkLogoImageSPSite()
   {
	   return checkImageDisplayedSize(headImgDom);
   }
   public boolean checkLoginImagePreviewTab()
   {
	   return checkImageDisplayedSize(loginImgPreviewDom);
   }
   public boolean checkLoginImageImageCustomTab()
   {
	   return checkImageDisplayedSize(loginImgLogoDom);
   }
   public boolean checkLogoImageDashboardPreviewTab()
   {
	   return checkImageDisplayedSize(headImgDashboardPreview);
   }
   
   // check images uploaded
   public boolean verifyNewLogoImageCustomeTab()
   {
	   return checkImageDisplayedSize(headImgLogoDom);
   }
   public boolean verifyNewLogoImagePriviewTab()
   {
	   return checkImageDisplayedSize(headImgPreviewDom);
   }
   public boolean verifyNewLogoImageSPSite()
   {
	   return checkImageDisplayedSize(headImgDom);
   }
   
   // get images source
   public String getLogoImageCustomTab()
   {
	   return headImgLogoDom.getAttribute("src");
   }
   public String getLogoImagePreviewTab()
   {
	   return headImgPreviewDom.getAttribute("src");
   }
   public String getLogoImageSPSite()
   {
	   return headImgDom.getAttribute("src");
   }
   public String getLoginImageImageCustomTab()
   {
	   return loginImgLogoDom.getAttribute("src");
   }
   public String getLoginImagePreviewTab()
   {
	   return loginImgPreviewDom.getAttribute("src");
   }
/*  
   public void clickLoginPreview()
   {
	   loginPreview.click();
   }
*/   
   /*
    * Login customization ---------------->
    */
   public void clickLoginCustomizationTab()
   {
	   loginCustomizationTab.click();
   }     
   public void clickEditColorsBT()
   {
	   editColorsBT.click();
   }   
   public void setLoginBoxColor(String loginBoxColorCode)
   {
	   type(spLoginBoxColorS1Input, loginBoxColorCode);
   }  
   public void clickSaveLoginColorsBT()
   {
	   saveLoginColors.click();
   }   
   // it doesn't use
   public String getColorLoginBoxPreview()
   {
	   return loginBoxPreview.getCssValue("background");
   }   
   public String getColorLoginBox()
   {
	   return sploginBoxColorDisplay1.getText();
   }  
   public void setLoginBoxTextColor(String LoginBoxTextColor)
   {
       type(spLoginBoxTextColorInput, LoginBoxTextColor);
   }     
   public String getColorLoginBoxText()
   {
	   return spLoginBoxTextColorDisplay.getText();
   }
   
   /*
    * Header customization ----------------->
    */
   public void clickHeaderCustomizationTab()
   {
       headerCustomizationTab.click();
   } 
   public void setHeaderBGColor(String headerBGColorCode)
   {
	   type(spHeaderBgColorInput, headerBGColorCode);
   }   
   public void setHeaderTextColor(String headerTextColorCode)
   {
	   type(spHeaderTextColorInput, headerTextColorCode);
   }   
   public void setMenuBGColor(String menuBGColorCode)
   {
	   type(spMenuBgColorInput, menuBGColorCode);
   }
   public void setMenuTextColor(String menuTextColorCode)
   {
	   type(spMenuTextColorInput, menuTextColorCode);
   }
   public void clickSaveHeaderColors()
   {
	   saveHeadColors.click();
   }
   public String getHeaderBGColor()
   {
       return spHeaderBgColorDisplay.getText();
   }
   public String getHeaderTextColor()
   {
	   return spHeaderTextColorDisplay.getText();
   }
   public String getMenuBGColor()
   {
	   return spMenuBgColorDisplay.getText();
   }
   public String getMenuTextColor()
   {
	   return spMenuTextColorDisplay.getText();
   }
   // duplication, eliminate
   public void clickEditBT()
   {
	   editBT.click();
   }
}