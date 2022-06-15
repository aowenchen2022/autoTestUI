/***********************************************************************
* This class tests the functionality of SP and client 
* @author Jennifer Wu
***********************************************************************/
package com.noosh.nooshentry.automation.nooshOne;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.FileNotFoundException;
import org.testng.annotations.Parameters;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import com.noosh.nooshentry.automation.buyerSite.AddSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.AssignSmartFormPage;
import com.noosh.nooshentry.automation.buyerSite.BrochurePopupWindow;
import com.noosh.nooshentry.automation.buyerSite.ClientLoginPage;
import com.noosh.nooshentry.automation.buyerSite.InvitePage;
import com.noosh.nooshentry.automation.buyerSite.SPHomePage;
import com.noosh.nooshentry.automation.buyerSite.WSPage;
import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import java.awt.AWTException;

public class TestSPClient2 extends BaseSeleniumTest {  
   Page page = new Page(driver); 
   Hashtable<String, String> credentials = new Hashtable<String, String>();
   String currentTime = String.valueOf(System.currentTimeMillis());
   String oldBuyerSiteURI;
   String profileNameSP;
   String spSiteLoginPage;
   //static String baseUrlPro;
   static String baseUrlSmoke;
   static String smokeURL;
   static String clientURL;
   String windowNumber;
   static String baseUrl;
   static String loginUrl;
   static String parentWindowHandle1;
   static String clientName = "Selenium Client";
   static String parentWindowHandle;
   static String projectName;
   
   @BeforeTest
   public static void setParameters(ITestContext context) throws FileNotFoundException {
	   if (domain.trim().equals("spd")) {
		   baseUrl = "https://demo." + companyName + ".com/service/signup";
		   //baseUrlPro = "https://noosh.com/noosh/home/login";
		   baseUrlSmoke = "https://nooshselenium2." + companyName + ".com/service/login";
		   smokeURL = "https://nooshselenium." + companyName + ".com/service/login";
		   clientURL = "https://nooshselenium." + companyName + ".com/autoclient/main";
		   loginUrl = "https://selenium263945." + companyName + ".com/service/login";  
	   } else {
		   baseUrl = "https://demo." + domain + "." + companyName + ".com/service/signup";
		   //baseUrlPro = "http://" + domain + ".noosh.com/noosh/home/login";
		   baseUrlSmoke = "http://nooshselenium2." + domain + "." + companyName + ".com/service/login";
		   smokeURL = "https://nooshselenium." + domain + "." + companyName + ".com/service/login";
		   clientURL = "https://nooshselenium." + domain + "." + companyName + ".com/autoclient/main";
		   loginUrl = "https://selenium263945." + domain + "." + companyName + ".com/service/login";  
	   }
   }
   
   @AfterTest
   public static void cleanup() {
	   driver.quit();
	   log.info("\n    Completed: ***** spClientTest2 *****\n\n");
   }
   
   @Test(description="Exsiting user login")
   @Parameters({"passwordSP", "spEmailSmoke"})
   public void testLoginDemoSqaPageSmoke(String passwordSP, String spEmailSmoke) throws InterruptedException {
	  log.info("Start: testLoginDemoSqaPageSmoke");
	  Thread.sleep(1500);
      driver.get(baseUrlSmoke);
      driver.manage().window().maximize();   
      Thread.sleep(2000);     
      LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
      loginDemoSqa.loginUser(spEmailSmoke, passwordSP); 
      Thread.sleep(3500);
	  spSiteLoginPage = driver.getCurrentUrl();     		
	  log.info("End: testLoginDemoSqaPageSmoke");
	  log.info("--------------------------------------------");
   } 
   
   @Test(description="SP Account - Go to Account Info")
   public void testAccountSubmenuLink() throws InterruptedException {
	   log.info("Start: testAccountSubmenuLink");
	   Thread.sleep(2000);
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.clickAdmin();
	   Thread.sleep(2500);
	   log.info("End: testAccountSubmenuLink");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - update info button")
   public void testAccountUpdateInfoBtn() throws InterruptedException {
	   log.info("Start: testAccountUpdateInfoBtn");
	   AccountFrame accountFrame = new AccountFrame(driver);
	   Thread.sleep(1500);
	   accountFrame.clickUpdatInfo();
	   Thread.sleep(2000);
	   log.info("End: testAccountUpdateInfoBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - Update Account Info")
   @Parameters({"companyNameSP", "addressSP"})
   public void testAccountUpdateFieldsInput(String companyNameSP, String addressSP) throws InterruptedException {
	   log.info("Start: testAccountUpdateFieldsInput");
	   AccountFrame accountFrame = new AccountFrame(driver);
	   accountFrame.setCompanyName(companyNameSP);
	   Thread.sleep(1000);
	   accountFrame.setAddress(addressSP);
	   Thread.sleep(1000);  
	   log.info("End: testAccountUpdateFieldsInput");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - Update Account Info cancel")
   public void testAccountUpdateCancelBtn() throws InterruptedException {
	   log.info("Start: testAccountUpdateCancelBtn");
	   AccountFrame accountFrame = new AccountFrame(driver);
	   accountFrame.clickCancelUpdateAccountInfoBT(); 
	   Thread.sleep(2500);
	   log.info("End: testAccountUpdateCancelBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - Update Account Info")
   @Parameters({"companyNameSP", "addressSP", "citySP", "stateSP", "zipSP"})
   public void testAccountUpdateInfo(String companyNameSP, String addressSP, String citySP, 
		                            String stateSP, String zipSP) throws InterruptedException {
	   log.info("Start: testAccountUpdateInfo");
	   Thread.sleep(1500);	     
	   AccountFrame accountFrame = new AccountFrame(driver);	   
	   Thread.sleep(1500);	  	   
	   accountFrame.clickUpdatInfo();  
	   Thread.sleep(2000);
	   accountFrame.setCompanyName(companyNameSP);
	   accountFrame.setAddress(addressSP);
	   Thread.sleep(800);	   
	   accountFrame.setCity(citySP);
	   Thread.sleep(800);
	   accountFrame.setState(stateSP);
	   Thread.sleep(800);
	   accountFrame.setZip(zipSP);
	   Thread.sleep(1000); 
	   log.info("End: testAccountUpdateInfo");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - update info save")
   public void testAccountUpdateInfoSaveBtn() throws InterruptedException {
	   log.info("Start: testAccountUpdateInfoSaveBtn");
	   AccountFrame accountFrame = new AccountFrame(driver);
	   accountFrame.clickSaveUpdateAccountInfoBT();
	   Thread.sleep(2500);
	   log.info("End: testAccountUpdateInfoSaveBtn");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - update info button")
   public void testAccountUpdateInfoBtnAgain() throws InterruptedException {
	   testAccountUpdateInfoBtn();
   }
   
   @Test(description="SP Account - update back info")
   @Parameters({"originalCompanyNameSP", "originalAddressSP", "originalCitySP", "originalStateSP"})
   public void testAccountUpdateInfoBack(String originalCompanyNameSP, String originalAddressSP, 
		                  String originalCitySP, String originalStateSP) throws InterruptedException {
	   log.info("Start: testAccountUpdateInfoBack");
	   AccountFrame accountFrame = new AccountFrame(driver);
	   accountFrame.setCompanyName(originalCompanyNameSP);
	   Thread.sleep(800);
	   accountFrame.setAddress(originalAddressSP);
	   Thread.sleep(800);
	   accountFrame.setCity(originalCitySP);
	   Thread.sleep(800);
	   accountFrame.setState(originalStateSP);
	   Thread.sleep(1000);
	   log.info("End: testAccountUpdateInfoBack");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="SP Account - update info save")
   public void testAccountUpdateInfoSaveBtn2ndTime() throws InterruptedException {
	   testAccountUpdateInfoSaveBtn();
   }
   
   @Test(description="Customize default product")
   @Parameters({"dashboardDemoTitle", "profileTitle", "passwordSP", "validDemoUserEmail"}) 
   public void testDashboardDemoPage(String dashboardDemoTitle, String profileTitle, String passwordSP, 
		                    String validDemoUserEmail) throws InterruptedException {
	  log.info("Start: testDashboardDemoPage");	  
	  Thread.sleep(1500);	  
	  CommonUtils commonUtils = new CommonUtils(driver);
	  commonUtils.clickSF();
      Thread.sleep(1500);
	  log.info("End: testDashboardDemoPage");
	  log.info("--------------------------------------------");
   }

   @Test(description = "Customize default product - Select customer product")
   @Parameters({"manageProductDemoTitle"})
   public void testManageProducts(String manageProductDemoTitle) throws InterruptedException {
	  log.info("Start: testManageProducts");
      ManageProductsPage manageProducts = new ManageProductsPage(driver);
      spSiteLoginPage = driver.getCurrentUrl(); 
	  if(spSiteLoginPage.contains("sdm"))
		  //manageProducts.getCustomizeProductPopupSDM(); 
		  manageProducts.getCustomizeProductPopup();
	  else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("qa2") || spSiteLoginPage.contains("scd"))   
		  manageProducts.getCustomizeProductPopup();
	  else
		  manageProducts.getCustommizeProductPopupSPD();  
	  log.info("End: testManageProducts");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="Customize default product - Enter product info")
   @Parameters({"editProductTitle", "productName", "fileImageName", "textBoxName",
	   "textBoxText", "dateFieldText", "newTabName"})
   public void testEditProductPopup(String editProductTitle, String productName,String fileImageName, 
		    String textBoxName, String textBoxText, String dateFieldText, String newTabName) throws InterruptedException {
	  log.info("Start: testEditProductPopup");
      EditProductPopup editProduct = new EditProductPopup(driver);      
      editProduct.setProductName(productName+unicID);   
      Thread.sleep(800);
      editProduct.uploadProductIcon(fileImageName);	 
      Thread.sleep(1000);
      editProduct.getCustomizationTab();  
      Thread.sleep(1000);
      log.info("End: testEditProductPopup");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="Customize default product - Enter product info")
   @Parameters({"editProductTitle", "productName", "fileImageName", "textBoxName",
	   "textBoxText", "dateFieldText", "newTabName"})
   public void testEditProductPopup1(String editProductTitle, String productName,String fileImageName, 
		    String textBoxName, String textBoxText, String dateFieldText, String newTabName) throws InterruptedException {
	  log.info("Start: testEditProductPopup1");
      EditProductPopup editProduct = new EditProductPopup(driver);     
      editProduct.getNewTab();  
      Thread.sleep(1000);
      editProduct.setNewTabName(newTabName);    
      Thread.sleep(1000);
      editProduct.saveCustomization(); 
      Thread.sleep(1000);
	  log.info("End: testEditProductPopup1");
	  log.info("--------------------------------------------");
   }
   
   @Test(description="Create New Product in Advanced Product Editor - get Empty product")
   public void testGetNewProductAdvancedEditor() throws InterruptedException {
	   log.info("Start: testGetNewProductAdvancedEditor");
	   ManageProductsPage managerProductsPage = new ManageProductsPage(driver);
	   NewHomePage newHomePage = new NewHomePage(driver);	   
	   Thread.sleep(2000);	   
	   newHomePage.clickGearSubmenuItem(driver, "/service/product/specs"); 
	   Thread.sleep(1600);	
	   managerProductsPage.clickCreateNewProductBT();
	   Thread.sleep(5000);
	   managerProductsPage.clickEmptyProduct();
	   Thread.sleep(2500);
	   log.info("End: testGetNewProductAdvancedEditor");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create New Product in Advanced Product Editor - Basic Information")
   @Parameters({"productNameAdv", "productImage"})
   public void testAdvancedEditorBasicInfo(String productNameAdv, String productImage) throws InterruptedException {
	   log.info("Start: testAdvancedEditorBasicInfo");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);	   
	   Thread.sleep(1000);	   	   
	   advancedEditor.putProductName(productNameAdv + unicID);
	   Thread.sleep(1000);
	   advancedEditor.uploadProductImage(productImage);
	   Thread.sleep(1500);
	   log.info("End: testAdvancedEditorBasicInfo");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create New Product in Advanced Product Editor - Customization Tab - Text Box")
   public void testCustomTabAdvancEditorTextBox() throws InterruptedException {
	   log.info("Start: testCustomTabAdvancEditorTextBox");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   advancedEditor.clickCustomizationTab();
	   Thread.sleep(1000);	   
	   advancedEditor.dragAndDropTextArea();
	   Thread.sleep(1200);
	   advancedEditor.clickPencilTextBox();
	   Thread.sleep(1000);	   
	   advancedEditor.selectRequired();
	   Thread.sleep(1000);
	   advancedEditor.clickSaveBT();
	   log.info("End: testCustomTabAdvancEditorTextBox");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create New Product in Advanced Product Editor - Customization Tab - Date")
   public void testCustomTabAdvancEditorDate() throws InterruptedException {
	   log.info("Start: testCustomTabAdvancEditorDate");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);
	   Thread.sleep(1000);	   
	   advancedEditor.dragAndDropDate();
	   Thread.sleep(1200);	   
	   advancedEditor.clickPencilDate();
	   Thread.sleep(1000);	   
	   advancedEditor.selectNotShownOnClientView();
	   advancedEditor.clickSaveBT();
	   log.info("End: testCustomTabAdvancEditorDate");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create New Product in Advanced Product Editor - Customization Tab - Number")
   @Parameters({"defaultValue"}) 
   public void testCustomTabAdvancEditorNumber(String defaultValue) throws InterruptedException {
	   log.info("Start: testCustomTabAdvancEditorNumber");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);	   
	   Thread.sleep(1000);	   
	   advancedEditor.dragAndDropName();
	   Thread.sleep(1200);	   
	   advancedEditor.clickPencilNumber();
	   Thread.sleep(1000);	   
	   advancedEditor.putDefaultValue(defaultValue);	   
	   advancedEditor.selectReadOnly();
	   advancedEditor.clickSaveBT();
	   Thread.sleep(1500);
	   log.info("End: testCustomTabAdvancEditorNumber");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create New Product in Advanced Product Editor - Customization Tab - Image and Dimentions")
   public void testCustomTabAdvancEditorAnotherElements() throws InterruptedException {
	   log.info("Start: testCustomTabAdvancEditorAnotherElements");
	   AdvancedEditor advancedEditor = new AdvancedEditor(driver);	   
	   Thread.sleep(1000);	   
	   advancedEditor.dragAndDropImage();
	   Thread.sleep(1500);	   
	   advancedEditor.dragAndDropDimentions();
	   Thread.sleep(2500);   
	   log.info("End: testCustomTabAdvancEditorAnotherElements");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Go to home page")
   public void testGetHomePage() throws InterruptedException {
	   log.info("Start: testGetHomePage");
	   MainMenuPage menuPage = new MainMenuPage(driver);	   
	   menuPage.clickProjectMenu();
	   Thread.sleep(3000);
	   log.info("End: testGetHomePage");
	   log.info("--------------------------------------------");
   }    
   
   @Test(description="Create new WS")
   public void testGetWSPage() throws InterruptedException {
	   log.info("Start: testGetWSPage");
	   WSPage wsPage = new WSPage(driver);
	   wsPage.clickWS(driver, "/service/site/siteList");   
	   Thread.sleep(3500);
	   log.info("End: testGetWSPage");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create new WS")
   public void testCreateWS() throws InterruptedException {
	   log.info("Start: testCreateWS");
	   WSPage wsPage = new WSPage(driver);
	   wsPage.clickGlbWS();
	   Thread.sleep(3500);
	   log.info("End: testCreateWS");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create new WS")
   public void testCreateNewWSCancel() throws InterruptedException {
	   log.info("Start: testCreateNewWSCancel");
	   clientName = clientName + unicID;
	   System.out.println("clientName =" + clientName);
	   WSPage wsPage = new WSPage(driver);
	   wsPage.inpputClientName(clientName);
	   Thread.sleep(1000);
	   wsPage.cancelWS();
	   Thread.sleep(3000);
	   log.info("End: testCreateNewWSCancel");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Create new WS")
   public void testCreateNewWS() throws InterruptedException {
	   log.info("Start: testCreateNewWS");
	   WSPage wsPage = new WSPage(driver);	  
	   testCreateWS();
	   wsPage.inpputClientName(clientName);
	   Thread.sleep(1000);
	   wsPage.submitWS();
	   Thread.sleep(8500);
	   log.info("End: testCreateNewWS");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Go to client view")
   public void testSPViewClientView() throws InterruptedException {
	   log.info("Start: testSPViewClientView");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.goToClientView();
	   Thread.sleep(5000);
	   log.info("End: testSPViewClientView");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Go back to SP view")
   public void testGoBackSPView() throws InterruptedException {
	   log.info("Start: testGoBackSPView");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   commonUtils.goBackSPView();
	   Thread.sleep(5000);
	   log.info("End: testGoBackSPView");
	   log.info("--------------------------------------------");
   }
   
   @Test(description="Edit client site 2")
   public void testSPEditClientSite2() throws InterruptedException {
	   log.info("Start: testSPEditClientSite2");
	   CommonUtils commonUtils = new CommonUtils(driver);
	   Thread.sleep(1000);
	   commonUtils.testOpenExpandWS();
	   Thread.sleep(1500);
	   AssignSmartFormPage smartFormPage = new AssignSmartFormPage(driver);
	   smartFormPage.clickSmartFormTB();
	   smartFormPage.clickAssignSmartForm();
	   smartFormPage.assignSmartForm();
	   smartFormPage.clickSmartFormBtn(); 
	   driver.findElement(By.id("glbDashboard")).click();
	   Thread.sleep(5000);
	   log.info("End: testSPEditClientSite2");
	   log.info("--------------------------------------------");
   }
   
   
   @Test(description="Existing SP click global create project")
   public void testExistingSPClickGlbCreateProj() throws InterruptedException {
	   log.info("Start: testExistingSPClickGlbCreateProj");
	   SPHomePage sPHomePage = new SPHomePage(driver);
	   sPHomePage.clickGlbCreateProj();    
	   Thread.sleep(3000);
	   log.info("End: testExistingSPClickGlbCreateProj");
 	   log.info("--------------------------------------------"); 
   }
  
   @Test(description="Existing SP cancel global create project")
   public void testExistingSPCancelCreateProj() throws InterruptedException {
	   log.info("Start: testExistingSPCancelCreateProj");
	   CreateNewProject createNewProject = new CreateNewProject(driver);
	   createNewProject.cancelGlbProj();   
	   Thread.sleep(3000);
	   log.info("End: testExistingSPCancelCreateProj");
 	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP click global create project again")
   public void testExistingSPClickGlbCreateProjAgain() throws InterruptedException {
	   testExistingSPClickGlbCreateProj();
   }
   
   @Test(description="Existing SP input project name")
   @Parameters({"productName1", "projectNameField"})
   public void testExistingSPInputProjectName(String productName1, String projectNameField) throws InterruptedException {
	   log.info("Start: testExistingSPInputProjectName");
	   productName1 = productName1 + unicID + " test product";  
	   projectName = productName1;
	   CreateNewProject newProject = new CreateNewProject(driver); 
	   newProject.inputGlbProjectName(productName1);   
	   Thread.sleep(1000);
	   try {
	      AssertJUnit.assertTrue(Page.isTextPresent(projectNameField, driver));	      
	   } catch(Throwable e) {
		  System.out.println("Project Name field is missing!\n");
		  log.info("Project Name field is missing!");
	   }
	   log.info("End: testExistingSPInputProjectName");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP select client or workspace")
   @Parameters({"clientOrWorkspace"})
   public void testExistingSPSelectWorkspace(String clientOrWorkspace) throws InterruptedException {
	   log.info("Start: testExistingSPSelectWorkspace");
	   CreateNewProject createNewProject = new CreateNewProject(driver);
	   createNewProject.selectWorkSpace();
       Thread.sleep(1000);
       try {
    	   AssertJUnit.assertTrue(Page.isTextPresent(clientOrWorkspace, driver));
       } catch(Throwable e) {
    	   System.out.println("Client or Workspace field is missing!\n");
    	   log.info("Client or Workspace field is missing!");
       }
       log.info("End: testExistingSPSelectWorkspace");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP invite client user collaborate")
   @Parameters({"clientUser"})
   public void testExistingSPInviteClientCollaborateBtn(String clientUser) throws InterruptedException {
	   log.info("Start: testExistingSPInviteClientCollaborateBtn");
	   CreateNewProject createNewProject = new CreateNewProject(driver);
	   createNewProject.inputClientUser(clientUser);     
	   Thread.sleep(1000);
	   log.info("End: testExistingSPInviteClientCollaborateBtn");
	   log.info("--------------------------------------------"); 
   }
   
   @Test(description="Existing SP select a smart form")
   @Parameters({"clientOrWorkspace"})
   public void testExistingSPSelectSmartForm(String clientOrWorkspace) throws InterruptedException {
	    log.info("Start: testExistingSPSelectSmartForm");
	    //try {
	   // 	AssertJUnit.assertTrue(Page.isTextPresent(clientOrWorkspace, driver));
	   // } catch(Throwable e) {
	   // 	System.out.println("Client or Workspace field is missing!\n");
	   // 	log.info("Client or Workspace field is missing!");
	   // }
	    WebElement startElement = driver.findElement(By.id("createProjectProductList"));
	    List<WebElement> img = startElement.findElements(By.tagName("img"));
	    spSiteLoginPage = driver.getCurrentUrl(); 
	    if(spSiteLoginPage.contains("sdm"))
	 	    img.get(1).click(); 	         
	    else if(spSiteLoginPage.contains("sqa") || spSiteLoginPage.contains("scd") || spSiteLoginPage.contains("qa2"))   
		    img.get(1).click();   
	    else
	    	img.get(0).click();
	    Thread.sleep(3000);
	    log.info("End: testExistingSPSelectSmartForm");
	    log.info("--------------------------------------------"); 
   } 
    
   @Test(description="Existing SP Create Project - step 2")
   @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
 	   "specDescrSP", "fileForProject11"})
   public void testExistingSPcreateProjectWizard(String projNameSP, String descriptionNameSP,
 		   String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
 		   String fileForProject11) throws InterruptedException, AWTException {
    
 	   log.info("Start: testExistingSPcreateProjectWizard");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   Thread.sleep(2000);   	    
 	   brochurePopup.callCalendar();
 	   brochurePopup.setNextMonth();
 	   brochurePopup.setComplationDate(); 
 	   Thread.sleep(3000); 	   
 	   /*below added 10/29 
 	   List<WebElement> textareas = driver.findElements(By.tagName("textarea"));
 	   for (WebElement textarea : textareas) {
 		   String projDesName = textarea.getAttribute("name");
 		   if (projDesName != null && projDesName.equalsIgnoreCase("stD9TcfE_project_description"));
 		       textarea.clear();
 		       textarea.sendKeys("This is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\nThis is a selenium\nautomation project.\n\n   END");
 		       break;
 	   }
 	   Thread.sleep(2000);
 	   above added 10/29 */ 	   
 	   brochurePopup.getNextTab();	 
 	   Thread.sleep(2000);
 	   //brochurePopup.putDescriptionName(descriptionNameSP);
 	   driver.findElement(By.id("cpPreviousStep")).click();  
 	   Thread.sleep(3000);
 	   brochurePopup.getNextTab();           
 	   Thread.sleep(3000);
 	   List<WebElement> inputs = driver.findElements(By.tagName("input"));
 	   for (WebElement input : inputs) {
 		   String name = input.getAttribute("reportingname");
 		   if (name != null && name.equalsIgnoreCase("QUANTITY1")) {
 			   input.clear();
 			   input.sendKeys(quantSP);
 		   }
 		   if (name != null && name.equalsIgnoreCase("QUANTITY2")) {
 			   input.clear();
 			   input.sendKeys(quantSP3);
 			   break;
 		   }
 	   }
 	   Thread.sleep(3000); 	    	  
 	   log.info("End: testExistingSPcreateProjectWizard");
 	   log.info("--------------------------------------------"); 
    }
   
    @Test(description="add smart form")
    @Parameters({"quantSP", "quantSP2"})
    public void testExistingSPAddSmartFormPage(String quantSP, String quantSP2) throws InterruptedException {
 	   log.info("Start: testExistingSPAddSmartFormPage");
 	   BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
 	   AddSmartFormPage addSmartFormPage = new AddSmartFormPage(driver);
 	   brochurePopup.getReviewAndSubmitTab();  
	   Thread.sleep(3000);
 	   addSmartFormPage.clickAddSmart();    
 	   Thread.sleep(1000);
 	   addSmartFormPage.clickSmartForm2();
 	   Thread.sleep(1000);
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(3000);
 	   addSmartFormPage.clickAddSmart(); 	   
 	   addSmartFormPage.clickSmartForm();
 	   addSmartFormPage.inputOneQuantity(quantSP);
 	   Thread.sleep(2000); 	   
 	   brochurePopup.getReviewAndSubmitTab();
 	   Thread.sleep(5000);
 	   /*
 	   if(spSiteLoginPage.contains("sdm")) {
	       Alert alert = driver.switchTo().alert();
	       alert.accept();
	       Thread.sleep(6000);
	   }
	   */
 	   log.info("End: testExistingSPAddSmartFormPage");
 	   log.info("--------------------------------------------"); 
    }
   
    @Test(description="Existing SP Create Project - step 3")
    @Parameters({"projNameSP", "descriptionNameSP", "sku", "referenceNumber", "quantSP", "quantSP2", "quantSP3", 
 	   "specDescrSP", "fileForProject11"})
    public void testExistingSPcreateProjectWizard2(String projNameSP, String descriptionNameSP,
 		String sku, String referenceNumber, String quantSP, String quantSP2, String quantSP3, String specDescrSP,
 		String fileForProject11) throws InterruptedException, AWTException {
 	    log.info("Start: testExistingSPcreateProjectWizard2");
 	    BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver); 	   
 	    Thread.sleep(3500);  
        brochurePopup.clickCreateBT(); 
        Thread.sleep(5000); 
 	    log.info("End: testExistingSPcreateProjectWizard2");
 	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="SP edit project")
    public void testSPClickEditProjBtn() throws InterruptedException {
    	log.info("Start: testSPClickEditProjBtn");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	Thread.sleep(3000);
    	//WebElement allProject = driver.findElement(By.id("homeSiteListView")).findElement(By.className("home-site-line-body"));
    	List<WebElement> projects = driver.findElements(By.tagName("span"));
	 	for (WebElement project : projects) {
	 		 if (project != null) {
	 			  String projectTitle = project.getText();
	 			  if ((projectTitle != null) && !projectTitle.isEmpty()) {
	 			  }
	 			  if (projectTitle.equals(projectName)) {
	 				  brochurePopup.clickEditProjectBtn();
	 				  Thread.sleep(3000);
	 				  break;
	 			  }
	 		  }
	 	 }
  	    Thread.sleep(3000);
    	log.info("End: testSPClickEditProjBtn");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="SP click cancel button")
    public void testSPClickCancelBtn() throws InterruptedException {
    	log.info("Start: testSPClickCancelBtn");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	brochurePopup.clickCancelProjectBtn();
    	Thread.sleep(3000);
  	    log.info("End: testSPClickCancelBtn");
	    log.info("--------------------------------------------");
    }
    
    @Test(description="SP edit project")
    public void testSPClickEditProjBtn2ndTime() throws InterruptedException {
    	testSPClickEditProjBtn();
    }    
    
    @Test(description="SP change project name")
    public void testSPChangeProjName() throws InterruptedException {
    	log.info("Start: testSPChangeProjName");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	brochurePopup.updateProjectName(projectName);
    	Thread.sleep(2000);
    	brochurePopup.updateProjectStatus(); 
    	Thread.sleep(3000);
    	log.info("End: testSPChangeProjName");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="SP click update button")
    public void testSPClickUpdateBtn() throws InterruptedException {
    	log.info("Start: testSPClickUpdateBtn");
    	BrochurePopupWindow brochurePopup = new BrochurePopupWindow(driver);
    	brochurePopup.clickUpdateProjectBtn();
    	Thread.sleep(3000);
    	log.info("End: testSPClickUpdateBtn");
  	    log.info("--------------------------------------------");
    }
    
    //10/2/2014 temp comment out due to it doesn't work. will work back
    @Test(description="test switch to projectlist view icon")
    public void testSwitchToProjectListView() throws InterruptedException {
    	log.info("Start: testSwitchToProjectListView");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.switchToProjectsView();
    	Thread.sleep(2000);
    	log.info("End: testSwitchToProjectListView");
  	    log.info("--------------------------------------------"); 
    }
    
    @Test(description="test switch to client view icon")
    public void testSwitchToClientView() throws InterruptedException {
    	log.info("Start: testSwitchToClientView");
    	SPHomePage sPHomePage = new SPHomePage(driver);
    	sPHomePage.switchToClientView();
    	Thread.sleep(3000);
    	log.info("End: testSwitchToClientView");
  	    log.info("--------------------------------------------"); 
    }    
    
    @Test(description="Logout SP site") 
    public void testLogoutDemoSite() throws InterruptedException {
 	    log.info("Start: testLogoutDemoSite");
 	    Thread.sleep(7000);
        CommonUtils commonUtils = new CommonUtils(driver);
        commonUtils.logoutUser();
 	    Thread.sleep(2000); 	
 	    log.info("End: testLogoutDemoSite");
 	    log.info("--------------------------------------------");
    }      
    
    @Test(description="Login different existing sp")
    @Parameters({"email", "passwordSP"})
    public void testLoginExistingSP(String email, String passwordSP) throws InterruptedException {
        log.info("Start: testLoginExistingSP");
        Thread.sleep(1500);
        driver.get(loginUrl);   
        Thread.sleep(2000);          
        LoginDemoSqaPage loginDemoSqa = new LoginDemoSqaPage(driver);     
        loginDemoSqa.loginUser(email, passwordSP); 
        Thread.sleep(2000);
  	    spSiteLoginPage = driver.getCurrentUrl();  	  
  	    log.info("End: testLoginExistingSP");
  	    log.info("--------------------------------------------");
    }
    
    @Test(description="Glb invite client user 1")
    public void testGlbInviteClient() throws InterruptedException {
    	log.info("Start: testGlbInviteClient");
    	WebElement glbDashBoard = driver.findElement(By.id("glbDashboard"));
    	if(!glbDashBoard.isDisplayed()) {
    		throw new IllegalStateException("The Home link is missing!");
    	} else {
    		glbDashBoard.click();
    	}
 	    Thread.sleep(1500);
 	    InvitePage invitePage = new InvitePage(driver);
 	    invitePage.clickGlbInvite();
 	    Thread.sleep(3000);
        log.info("End: testGlbInviteClient");
        log.info("--------------------------------------------");
    }
    
    @Test(description="Glb invite tab")
    public void testGlbInviteClientUserTab() throws InterruptedException {
    	log.info("Start: testGlbInviteClientUserTab");
    	InvitePage invitePage = new InvitePage(driver);
    	invitePage.clickClientUsersTab();
    	Thread.sleep(1000);
    	log.info("End: testGlbInviteClientUserTab");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Glb invite: client user field")
    @Parameters({"clientEmailTestWS"})
    public void testGlbInviteClientField(String clientEmailTestWS) throws InterruptedException {
    	log.info("Start: testGlbInviteClientField");
    	InvitePage invitePage = new InvitePage(driver);
    	invitePage.inputClient1(clientEmailTestWS);
    	Thread.sleep(1500);
    	log.info("End: testGlbInviteClientField");
    	log.info("--------------------------------------------");
    }
 
    @Test(description="Glb invite: select a workspace")
    @Parameters({"userddd"})
    public void testGlbInviteClientWS(String userddd) throws InterruptedException {
    	log.info("Start: testGlbInviteClientWS");
    	InvitePage invitePage = new InvitePage(driver);
    	invitePage.selectWS(userddd);
 	    Thread.sleep(1000);
    	log.info("End: testGlbInviteClientWS");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Glb invite: send Invitation")
    public void testGlbInviteClientSendEnvitations() throws InterruptedException {
    	log.info("Start: testGlbInviteClientSendEnvitations");
    	InvitePage invitePage = new InvitePage(driver);
    	invitePage.sendClientInvitation();
    	//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
 	    Thread.sleep(38000);
    	log.info("End: testGlbInviteClientSendEnvitations");
    	log.info("--------------------------------------------");
    }
    
    @Test(description="Logout")
    public void testLogout() throws InterruptedException {
    	log.info("Start: testLogout");
    	CommonUtils commonUtils = new CommonUtils(driver);
 	    //commonUtils.spLogout();
    	log.info("End: testLogout");
        log.info("--------------------------------------------");
    }
    
    @Test(description="Check gmail login preconditions")
    public void testGmailLoginPreCon() throws InterruptedException {
 	      Thread.sleep(500);
 	 	  WebElement gmailSignIn = null;
 	 	  try {
 	 	      gmailSignIn = driver.findElement(By.id("gmail-sign-in"));    
 	 	  } catch (Exception e) {
 	 		  // do nothing
 	 	  }
 		  if (gmailSignIn != null) {
 			 gmailSignIn.click();
 			 Thread.sleep(800);
 		  } 
    }
    
    @Test(description="Login to Gmail")
    @Parameters({"gmailLoginPW", "gmail"})
    public void testLoginToGmail(String gmailLoginPW, String gmail) throws InterruptedException {
  	  log.info("Start: testLoginToGmail");
  	  Thread.sleep(800);
  	  driver.get("http://www.gmail.com");
  	  //testGmailLoginPreCon();  
  	  GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
  	  Thread.sleep(2000);
  	  gmailLoginPage.enterEmail(gmail);
  	  Thread.sleep(1000);
  	  gmailLoginPage.enterPassword(gmailLoginPW);
  	  Thread.sleep(1000);
  	  gmailLoginPage.check();
 	  gmailLoginPage.signIn();
 	  Thread.sleep(1500);
 	  /*
 	  try {
	      skipPhoneNum = driver.findElement(By.id("skip-link"));
	  } catch (Exception e) {
		  //do nothing
	  }
 	  if (skipPhoneNum != null) {
 	     skipPhoneNum.click();
	     Thread.sleep(1000);
	     Alert alert = driver.switchTo().alert();
	 	 alert.dismiss();
	 	 Thread.sleep(500);
	  }
	  */
  	  log.info("End: testLoginToGmail");
  	  log.info("--------------------------------------------");
    }
    
    @Test(description="Client checks email")
    public void testClientCheckEmail() throws InterruptedException {
 	   log.info("Start: testClientCheckEmail");
 	   Thread.sleep(3500);
 	   List<WebElement> emails = driver.findElements(By.tagName("span"));
 	   for (WebElement email : emails) {
 	 	  if (email != null) {
 	 		  String emailTitle = email.getText();
 	 		 if (emailTitle.indexOf("invites you to collaborate") >= 0) {
 	 			  email.click();
 	 			  break;
 	 		  }
 	 	  }
 	   }
 	   Thread.sleep(2000);
 	   WebElement acceptInv = driver.findElement(By.linkText("Accept Invitation and Login"));
 	   if(!acceptInv.isDisplayed()) {
 		   throw new IllegalStateException("The accept invitation link is missing!");
 	   } else {
 		   acceptInv.click();
 	   } 	   
 	   Thread.sleep(2000);  
  	   log.info("End: testClientCheckEmail");
  	   log.info("--------------------------------------------");
    }
    
    @Test(description="Client login")
    @Parameters({"passwordBuyer"})
    public void testClientLogin(String passwordBuyer) throws InterruptedException {
    	log.info("Start: testClientLogin");
    	parentWindowHandle = driver.getWindowHandle();
    	Set<String> windows = driver.getWindowHandles();
    	for (String window : windows) {
    		if (!window.equals(parentWindowHandle)) {
    			driver.switchTo().window(window);
    			break;
    		}
    	}
    	ClientLoginPage clientLoginPage = new ClientLoginPage(driver);
    	Thread.sleep(1000);
    	clientLoginPage.clientLoginUser1(passwordBuyer);
    	Thread.sleep(2000);
    	log.info("End: testClientLogin");
   	    log.info("--------------------------------------------");
    }
    
    @Test(description="Client logout account")
    public void testClientLogout() throws InterruptedException {
    	log.info("Start: testClientLogout");
    	Thread.sleep(3500);
        CommonUtils commonUtils = new CommonUtils(driver);
 	    commonUtils.testClientLogout1();
   	    Thread.sleep(2000);
    	log.info("End: testClientLogout");
   	    log.info("--------------------------------------------");
    }
    
    @Test(description="Cliet logout gmail")
    @Parameters({"gmailLoginPW"})
    public void testClientLogoutGmail(String gmailLoginPW) throws InterruptedException {
    	  log.info("Start: testClientLogoutGmail");
    	  Thread.sleep(2000);
    	  driver.get("https://mail.google.com/mail/?logout"); 
    	  Thread.sleep(2000);    	  
    	  log.info("End:testClientLogoutGmail");
    	  log.info("--------------------------------------------");
     }
}   
    