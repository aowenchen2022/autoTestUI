/***********************************************************************
* This class does NGE deployment
* @author Ken Zhang
***********************************************************************/
package com.noosh.nooshentry.automation.demoSQANoosh;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.noosh.nooshentry.automation.base.BaseSeleniumTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class NGEDeploy extends BaseSeleniumTest {  
    @Test
    @Parameters({"url", "loginName", "loginPW"})
    public void ngeDeploy(String url, String loginName, String loginPW) throws InterruptedException {	   
    	 String buildVersion = null;
	     String gitBranch = null;
	     
	     try {
	         Properties prop = new Properties();
	         FileInputStream in = new FileInputStream("C:\\jennifer\\savedParameters.properties");
	         prop.load(in);
	         buildVersion = prop.getProperty("Build_Version");
	         gitBranch = prop.getProperty("Git_Branch");
	         domain = prop.getProperty("domain");
	         System.out.println(domain);
	         in.close();
	     } catch (IOException ioe) {
	    	 System.out.println("Properties file C:\\jennifer\\savedParameters.properties doesn't exist" );
	     }
         Thread.sleep(1500);         
         driver.get(url); 
         driver.manage().window().maximize();  
         WebElement userName = driver.findElement(By.id("user"));
         userName.clear();
         userName.sendKeys(loginName);
         Thread.sleep(1000);
         WebElement pw = driver.findElement(By.id("pass"));
         pw.clear();
         pw.sendKeys(loginPW);
         Thread.sleep(1000);
         driver.findElement(By.className("ui_submit")).click();
         Thread.sleep(3000);             
         driver.switchTo().frame(0);
         driver.findElement(By.className("linkwithicon")).findElement(By.tagName("a")).click();
         Thread.sleep(2000);
         
         System.out.println(domain);
         if (domain.trim().equals("sqa")) {
         List<WebElement> div1s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
         div1s.get(0).findElement(By.tagName("a")).click();
         Thread.sleep(2000);
         driver.switchTo().defaultContent();
         Thread.sleep(2000);
		 
		 for (int j = 0; j < 2; j++) {
		 driver.switchTo().frame(1);
         List<WebElement> gridrow1s = driver.findElements(By.className("ui_grid_row"));         
         WebElement submitBTN = gridrow1s.get(1+j).findElement(By.className("ui_submit"));
         System.out.println("btn text=" + submitBTN.getAttribute("value"));
         List<WebElement> boxes1 = gridrow1s.get(1+j).findElements(By.className("ui_textbox"));
         buildVersion = buildVersion.trim();  
         boxes1.get(0).clear();
         boxes1.get(0).sendKeys(buildVersion);
         Thread.sleep(1000);
         boxes1.get(1).clear();
         boxes1.get(1).sendKeys("y");
         Thread.sleep(1000);
         submitBTN.click();
         driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
         driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click(); 
         Thread.sleep(1000);       
 		 driver.switchTo().defaultContent();
		 }

         driver.switchTo().frame(0);
         List<WebElement> divs = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
         divs.get(1).findElement(By.tagName("a")).click();
         Thread.sleep(2000);
         driver.switchTo().defaultContent();
         Thread.sleep(2000);
         driver.switchTo().frame(1); 
         List<WebElement> gridrows = driver.findElements(By.className("ui_grid_row"));         
         WebElement submitBTN2 = gridrows.get(1).findElement(By.className("ui_submit"));
         System.out.println("btn text=" + submitBTN2.getAttribute("value"));
         List<WebElement> boxes = gridrows.get(1).findElements(By.className("ui_textbox"));
         buildVersion = buildVersion.trim(); 
         boxes.get(0).clear();
         boxes.get(0).sendKeys(buildVersion); 
         Thread.sleep(1000);
         boxes.get(1).clear();
         boxes.get(1).sendKeys("y");
         Thread.sleep(1000);
         submitBTN2.click();
         Thread.sleep(42000);
         driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
         Thread.sleep(5000);             
         /*List<WebElement> gridrows1 = driver.findElements(By.className("ui_grid_row"));  
         WebElement submitBTN1 = gridrows1.get(3).findElement(By.className("ui_submit"));         
         List<WebElement> boxes2 = gridrows1.get(3).findElements(By.className("ui_textbox"));
         boxes2.get(0).clear();
         boxes2.get(0).sendKeys(buildVersion); 
         Thread.sleep(1000);
         boxes2.get(1).clear();
         boxes2.get(1).sendKeys("y");
         Thread.sleep(1000);
         submitBTN1.click();
         Thread.sleep(120000);*/
         driver.switchTo().defaultContent();
		 Thread.sleep(1000);
				for (int i = 0; i < 2; i++) {
		     driver.switchTo().frame(0);
	         List<WebElement> div2s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
	         div2s.get(2).findElement(By.tagName("a")).click();
	         Thread.sleep(2000);
	         driver.switchTo().defaultContent();
	         Thread.sleep(2000);
	         driver.switchTo().frame(1); 
	         List<WebElement> gridrowsNGE = driver.findElements(By.className("ui_grid_row"));         
	         WebElement submitBTNnge = gridrowsNGE.get(3-i).findElement(By.className("ui_submit"));
	         System.out.println("btn text=" + submitBTNnge.getAttribute("value"));
	         List<WebElement> boxesNGE = gridrowsNGE.get(3-i).findElements(By.className("ui_textbox"));
	         buildVersion = buildVersion.trim(); 
	         boxesNGE.get(0).clear();
	         boxesNGE.get(0).sendKeys(buildVersion); 
	         Thread.sleep(1000);
	         boxesNGE.get(1).clear();
	         boxesNGE.get(1).sendKeys("y");
	         Thread.sleep(1000);
	         submitBTNnge.click();
	         Thread.sleep(50000);
	         driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
	         Thread.sleep(5000); 
			 driver.switchTo().defaultContent();
		     Thread.sleep(1000);
		 }
         Thread.sleep(2000);
         driver.switchTo().frame(0);
         List<WebElement> links = driver.findElement(By.tagName("form")).findElements(By.className("linkwithicon"));
         links.get(1).findElement(By.tagName("a")).click();
         Thread.sleep(3000); 
         driver.get("https://selenium263945.sqa.noosh.com/default/html/version.html?v="+buildVersion);
         Thread.sleep(2000);
         List<WebElement> lis = driver.findElement(By.tagName("body")).findElements(By.tagName("li"));
         String version = lis.get(0).getText();
         version = (version.substring(9));
         System.out.println("\nversion="+version);
         String branch = lis.get(1).getText();
         branch = (branch.substring(8));
         gitBranch = gitBranch.trim();
         System.out.println("\nbranch="+branch+"\n");
         Assert.assertEquals(version, buildVersion);
         Thread.sleep(1000);
         Assert.assertEquals(branch, gitBranch);
         Thread.sleep(5000);  
         } else
        	 if (domain.trim().equals("scd")) { 
         
                 List<WebElement> div1s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 div1s.get(0).findElement(By.tagName("a")).click();
                 Thread.sleep(2000);
                 driver.switchTo().defaultContent();
                 
                 Thread.sleep(2000);
                 driver.switchTo().frame(1);
                 List<WebElement> gridrow1s = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTN = gridrow1s.get(3).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTN.getAttribute("value"));
                 List<WebElement> boxes1 = gridrow1s.get(3).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim();  
                 boxes1.get(0).clear();
                 boxes1.get(0).sendKeys(buildVersion);
                 Thread.sleep(1000);
                 boxes1.get(1).clear();
                 boxes1.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTN.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click(); 
                 Thread.sleep(5000);       
                 driver.switchTo().defaultContent();
                 driver.switchTo().frame(0);
                 List<WebElement> divs = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 divs.get(1).findElement(By.tagName("a")).click();
                 Thread.sleep(2000);
                 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                
                 driver.switchTo().frame(1); 
                 List<WebElement> gridrows = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTN2 = gridrows.get(4).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTN2.getAttribute("value"));
                 List<WebElement> boxes = gridrows.get(4).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim(); 
                 boxes.get(0).clear();
                 boxes.get(0).sendKeys(buildVersion); 
                 Thread.sleep(1000);
                 boxes.get(1).clear();
                 boxes.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTN2.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
                 Thread.sleep(5000);            
                 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                 driver.switchTo().frame(0);
               
                 List<WebElement> div2s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 div2s.get(2).findElement(By.tagName("a")).click();
                 Thread.sleep(3000); 
				 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                
                 driver.switchTo().frame(1); 
                 List<WebElement> gridrowsNGE = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTNnge = gridrowsNGE.get(1).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTNnge.getAttribute("value"));
                 List<WebElement> boxesNGE = gridrowsNGE.get(1).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim(); 
                 boxesNGE.get(0).clear();
                 boxesNGE.get(0).sendKeys(buildVersion); 
                 Thread.sleep(1000);
                 boxesNGE.get(1).clear();
                 boxesNGE.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTNnge.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
                 Thread.sleep(5000);            
                 driver.switchTo().defaultContent();
                 driver.get("https://selenium263945.scd.noosh.com/default/html/version.html?v="+buildVersion);
                 Thread.sleep(2000);
                 List<WebElement> lis = driver.findElement(By.tagName("body")).findElements(By.tagName("li"));
                 String version = lis.get(0).getText();
                 version = (version.substring(9));
                 System.out.println("\nversion="+version);
                 String branch = lis.get(1).getText();
                 branch = (branch.substring(8));
                 gitBranch = gitBranch.trim();
                 System.out.println("\nbranch="+branch+"\n");
                 Assert.assertEquals(version, buildVersion);
                 Thread.sleep(1000);
                 Assert.assertEquals(branch, gitBranch);
                 Thread.sleep(5000); 
        		 
        	 }else
				if (domain.trim().equals("scdeu")) { 
         
                 List<WebElement> div1s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 div1s.get(0).findElement(By.tagName("a")).click();
                 Thread.sleep(2000);
                 driver.switchTo().defaultContent();
                 
                 Thread.sleep(2000);
                 driver.switchTo().frame(1);
                 List<WebElement> gridrow1s = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTN = gridrow1s.get(4).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTN.getAttribute("value"));
                 List<WebElement> boxes1 = gridrow1s.get(4).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim();  
                 boxes1.get(0).clear();
                 boxes1.get(0).sendKeys(buildVersion);
                 Thread.sleep(1000);
                 boxes1.get(1).clear();
                 boxes1.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTN.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click(); 
                 Thread.sleep(5000);       
                 driver.switchTo().defaultContent();
                 driver.switchTo().frame(0);
                 List<WebElement> divs = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 divs.get(1).findElement(By.tagName("a")).click();
                 Thread.sleep(2000);
                 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                
                 driver.switchTo().frame(1); 
                 List<WebElement> gridrows = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTN2 = gridrows.get(2).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTN2.getAttribute("value"));
                 List<WebElement> boxes = gridrows.get(2).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim(); 
                 boxes.get(0).clear();
                 boxes.get(0).sendKeys(buildVersion); 
                 Thread.sleep(1000);
                 boxes.get(1).clear();
                 boxes.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTN2.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
                 Thread.sleep(5000);            
                 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                 driver.switchTo().frame(0);
               
                 List<WebElement> div2s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 div2s.get(2).findElement(By.tagName("a")).click();
                 Thread.sleep(3000); 
				 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                
                 driver.switchTo().frame(1); 
                 List<WebElement> gridrowsNGE = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTNnge = gridrowsNGE.get(4).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTNnge.getAttribute("value"));
                 List<WebElement> boxesNGE = gridrowsNGE.get(4).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim(); 
                 boxesNGE.get(0).clear();
                 boxesNGE.get(0).sendKeys(buildVersion); 
                 Thread.sleep(1000);
                 boxesNGE.get(1).clear();
                 boxesNGE.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTNnge.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
                 Thread.sleep(5000);            
                 driver.switchTo().defaultContent();
                 driver.get("https://selenium263945.scdeu.noosh.com/default/html/version.html?v="+buildVersion);
                 Thread.sleep(2000);
                 List<WebElement> lis = driver.findElement(By.tagName("body")).findElements(By.tagName("li"));
                 String version = lis.get(0).getText();
                 version = (version.substring(9));
                 System.out.println("\nversion="+version);
                 String branch = lis.get(1).getText();
                 branch = (branch.substring(8));
                 gitBranch = gitBranch.trim();
                 System.out.println("\nbranch="+branch+"\n");
                 Assert.assertEquals(version, buildVersion);
                 Thread.sleep(1000);
                 Assert.assertEquals(branch, gitBranch);
                 Thread.sleep(5000); 
        		 
        	 }else
				if (domain.trim().equals("sqaeu")) { 
         
                 List<WebElement> div1s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 div1s.get(0).findElement(By.tagName("a")).click();
                 Thread.sleep(2000);
                 driver.switchTo().defaultContent();
                 
                 Thread.sleep(2000);
                 driver.switchTo().frame(1);
                 List<WebElement> gridrow1s = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTN = gridrow1s.get(7).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTN.getAttribute("value"));
                 List<WebElement> boxes1 = gridrow1s.get(7).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim();  
                 boxes1.get(0).clear();
                 boxes1.get(0).sendKeys(buildVersion);
                 Thread.sleep(1000);
                 boxes1.get(1).clear();
                 boxes1.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTN.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click(); 
                 Thread.sleep(5000);       
                 driver.switchTo().defaultContent();
                 driver.switchTo().frame(0);
                 List<WebElement> divs = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 divs.get(1).findElement(By.tagName("a")).click();
                 Thread.sleep(2000);
                 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                
                 driver.switchTo().frame(1); 
                 List<WebElement> gridrows = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTN2 = gridrows.get(3).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTN2.getAttribute("value"));
                 List<WebElement> boxes = gridrows.get(3).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim(); 
                 boxes.get(0).clear();
                 boxes.get(0).sendKeys(buildVersion); 
                 Thread.sleep(1000);
                 boxes.get(1).clear();
                 boxes.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTN2.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
                 Thread.sleep(5000);            
                 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                 driver.switchTo().frame(0);
               
                 List<WebElement> div2s = driver.findElement(By.id("Noosh")).findElements(By.tagName("div"));
                 div2s.get(2).findElement(By.tagName("a")).click();
                 Thread.sleep(3000); 
				 driver.switchTo().defaultContent();
                 Thread.sleep(2000);
                
                 driver.switchTo().frame(1); 
                 List<WebElement> gridrowsNGE = driver.findElements(By.className("ui_grid_row"));         
                 WebElement submitBTNnge = gridrowsNGE.get(5).findElement(By.className("ui_submit"));
                 System.out.println("btn text=" + submitBTNnge.getAttribute("value"));
                 List<WebElement> boxesNGE = gridrowsNGE.get(5).findElements(By.className("ui_textbox"));
                 buildVersion = buildVersion.trim(); 
                 boxesNGE.get(0).clear();
                 boxesNGE.get(0).sendKeys(buildVersion); 
                 Thread.sleep(1000);
                 boxesNGE.get(1).clear();
                 boxesNGE.get(1).sendKeys("y");
                 Thread.sleep(1000);
                 submitBTNnge.click();
                 Thread.sleep(120000);
                 driver.findElement(By.tagName("p")).findElement(By.tagName("a")).click();
                 Thread.sleep(5000);            
                 driver.switchTo().defaultContent();
                 driver.get("https://selenium263945.sqaeu.noosh.com/default/html/version.html?v="+buildVersion);
                 Thread.sleep(2000);
                 List<WebElement> lis = driver.findElement(By.tagName("body")).findElements(By.tagName("li"));
                 String version = lis.get(0).getText();
                 version = (version.substring(9));
                 System.out.println("\nversion="+version);
                 String branch = lis.get(1).getText();
                 branch = (branch.substring(8));
                 gitBranch = gitBranch.trim();
                 System.out.println("\nbranch="+branch+"\n");
                 Assert.assertEquals(version, buildVersion);
                 Thread.sleep(1000);
                 Assert.assertEquals(branch, gitBranch);
                 Thread.sleep(5000); 
        		 
        	 }
         driver.close();
   }
}
   