package com.noosh.nooshentry.automation.demoSQANoosh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.jsErrorCollector.JavaScriptError;
//import com.noosh.nooshentry.automation.nooshPro.SimpleDateFormat;
import com.noosh.nooshentry.automation.util.AppConfig;

public class Page extends BaseSeleniumTest
{
	
   protected final WebDriver driver;
   public WebElement myElement;
   
   public Page(WebDriver driver){
       this.driver=driver;
   }
   
   public static String getXpath(String key) {
	   return AppConfig.getXpath(key);
   }
   
   public String getText(WebElement element){
	   return element.getText();
   }
   
   public boolean isElementPresent(WebDriver driver, String text) {  
	   if(ExpectedConditions.invisibilityOfElementLocated(By.linkText(text))!=null) {
		   System.out.println("Overview visible");
	      return true; 	      
	   } else {
		   System.out.println("Overview not visible");
	      return false; 
	   }
   }
   
   public boolean checkWebElementText(WebElement element, String text){
	  if (text.equals(getText(element)))
	      return true;
	      throw new IllegalStateException("This is not " + driver.getTitle() +
	            " page");
   }  
	public void selectEnvelopeProduct(WebElement frame, WebElement element) throws InterruptedException {
	new Actions(driver).moveToElement(frame).build().perform();
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
			,element);
	Thread.sleep(500);
	element.click();
	}	
   
   public String getTitle(WebDriver driver){
      return driver.getTitle();
   }
   
   public boolean checkPageTitle(WebDriver driver, String title){
      if (title.equals(driver.getTitle()))
         return true;
         throw new IllegalStateException("This is not " + driver.getTitle() +
               " page");
   }
     
   public static boolean isTextPresent(String what, WebDriver driver) {
       try {
               driver.findElement(By.tagName("body")).getText().contains(what);
               return true;
           } catch (NoSuchElementException e) {
               System.out.println("Page does not contain text: " + what);    
               return false;
             }
   }     
   
   public boolean isElementPresent(WebElement element){
	   try {
		   element.isDisplayed();
		   return true;
	   } catch (NoSuchElementException e){
		   System.out.println(element + " not dispalyed");
		   return false;
	   }
   }
   
   public void type(WebElement element, String text){
	   element.clear();
	   element.sendKeys(text);
   }

   public void moveWebElement(WebElement sourceElement, int xMoving, int yMoving){
      new Actions(driver).dragAndDropBy(sourceElement, xMoving, yMoving).build().perform();
   }
   
   public void dragAndDropElement(WebElement source, WebElement target){
	   new Actions(driver).dragAndDrop(source, target).build().perform();
   }
   
   public void takeScreenshot(WebDriver driver, String fileName){
      File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      try {
         FileUtils.copyFile(scrFile1, new File(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }   
   
   public void takeScreenshot1(WebDriver driver, String fileName){
	      File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      try {
	         FileUtils.copyFile(scrFile1, new File(System.getProperty("user.dir") + "\\test-output\\" + fileName + unicID + ".png"));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }      

	public void uploadFile(WebElement element, String fileName){
	      File file = new File(fileName);
	      element.sendKeys(file.getAbsolutePath());
	}
	
	public void uploadFileRelative(WebElement element, String filePath){
		File file = new File(Page.class.getClassLoader().getResource(filePath).getPath());
		element.sendKeys(file.getAbsolutePath());
	}
	
	public String getDomainName(String companyName, long unicID){
		return (companyName + unicID).trim().replaceAll("\\s", "").toLowerCase();
	}
	
	public void clickSubMenuItem(WebElement menuItem, WebElement subMenuItem) throws InterruptedException
	{
	    new Actions(driver).moveToElement(menuItem).build().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    subMenuItem.click();
	}
	// does not work for IE
	public void clickSubMenuItemIEE(WebElement menuItem, WebElement subMenuItem) {
		menuItem.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		subMenuItem.click();
	}
	
	public void clickSubMenuItemIE(WebElement menuItem, WebElement subMenuItem, String locator) throws InterruptedException {
	    new Actions(driver).moveToElement(menuItem).build().perform();
	    waitForElement(locator);
	}
	
	public boolean checkImageDisplayed(WebElement element, String imageFileName) {
		if(element.getAttribute("src").endsWith(imageFileName))
			return element.isDisplayed();
		else return false;
	}	
	public boolean checkImageDisplayedSize(WebElement element) {
		
		Dimension dimension = element.getSize();		
		if(element.isDisplayed() && dimension.width > 0 && dimension.height > 0)
			return true;
		else return false;
	}
	String getPopupWindowHandle(WebDriver driver, WebElement link) {

	    Set<String> beforePopup = driver.getWindowHandles();

	    link.click();
	    Set<String> afterPopup = driver.getWindowHandles();
	    afterPopup.removeAll(beforePopup);
	    if(afterPopup.size() == 1) {
	        return (String)afterPopup.toArray()[0];
	    }
	    return null;
	}
	public void doubleClickElement(WebElement element){
		new Actions(driver).doubleClick(element).perform();
	}
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
	public void uploadFileModalWindow(String fileName) throws AWTException {
		File file = new File(Page.class.getClassLoader().getResource(fileName).getPath());
		fileName = file.getAbsolutePath();
		setClipboardData(fileName);
	}
	
   //native key strokes for CTRL, V and ENTER keys
	public void robotUpload() throws InterruptedException, AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);		
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void robotCopy() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL); // Copy
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public void robotPaste() throws AWTException {
		Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public String getClipBoardContent() throws HeadlessException, UnsupportedFlavorException, IOException {
		return (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor); 
	}
	
	public void robotCloseAppWindow() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);  
		robot.keyPress(KeyEvent.VK_F4);  
		robot.keyRelease(KeyEvent.VK_F4);
		robot.keyRelease(KeyEvent.VK_ALT);  
	}
	
	public void robotScrollElement() {		
	}
	
	public static void robotClickEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public void closeModalPopup() throws AWTException{
		Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER); 
	}
	public String getUnicID() throws FileNotFoundException{
		String unicIDNum = null;
		File file = new File("C:\\tmp\\unicIDNumber.txt");	      
		Scanner input = new Scanner(file);	      
		while (input.hasNext())
		{
		   unicIDNum = input.next();	         
		   System.out.println("unicIDNum: " + unicIDNum);
		}	      
		   input.close();
		return unicIDNum;
	}
	public void moveField(WebElement button)
	{
	    new Actions(driver).dragAndDropBy(button, 200, 0).build().perform();
	} 
	
    public static void logJSError(int jsErrorInd, WebDriver driver, Logger log)
    {    	
		List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);

        if (jsErrorInd < jsErrors.size())
        {
        int i;
           for(i = jsErrorInd; i < jsErrors.size(); i++) {
              System.out.println(jsErrors.get(i).getErrorMessage());
              log.info(jsErrors.get(i).getErrorMessage());
              System.out.println(jsErrors.get(i).getLineNumber());
              log.info(jsErrors.get(i).getLineNumber());
              System.out.println(jsErrors.get(i).getSourceName());
              log.info(jsErrors.get(i).getSourceName());
           }
           BaseSeleniumTest.jsErrorIndex = i;
        }
 //       return BaseSeleniumTest.jsErrorIndex;
    }
    
    public static void jsErrorReporter(WebDriver driver, int errorIndex, String message)
    {
        Page.logJSError(jsErrorIndex, driver, log);
        if (jsErrorIndex > errorIndex){
          System.out.println(message);
          log.info(message);        
        }
    }
    
    public static void getCurrentDate() {
    	 Date date = new Date(System.currentTimeMillis());
    	 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
    	 String s = formatter.format(date);	
    }
    
    public void openFileExplorer() throws IOException {
    	File file = new File ("C:\\Users\\jennifer\\Pictures");
    	Desktop desktop = Desktop.getDesktop();
    	desktop.open(file);    	
    }
    
    public void mouseMove() throws AWTException {
        Robot robot = new Robot();

        robot.mouseMove(1050, 756);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(950, 600);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    
    public void downloadFile() throws InterruptedException, AWTException {	   
 	   Thread.sleep(1600);
 	   closeModalPopup();
 	   Thread.sleep(1300);
 	   closeModalPopup();
 	   Thread.sleep(1400);
 	   robotCloseAppWindow();
 	   Thread.sleep(2500);
    }
    
    public void waitForElement(String locator) {
	   WebDriverWait wait = new WebDriverWait(driver, 10);
	   WebElement element = wait.until(
	           ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
	   element.click();
    }
    
    public void getPageURI(int deletedChars, String uriEnd) {
    	String spSiteLoginPage;
		spSiteLoginPage = driver.getCurrentUrl();
		spSiteLoginPage = spSiteLoginPage.substring(0, spSiteLoginPage.length() - deletedChars);
		System.out.println("Home page url:" + spSiteLoginPage);
		driver.get(spSiteLoginPage + uriEnd);
    }

}
