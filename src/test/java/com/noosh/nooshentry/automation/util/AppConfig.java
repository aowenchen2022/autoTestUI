package com.noosh.nooshentry.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class AppConfig {
	
   private static Logger logger = Logger.getLogger(AppConfig.class);
   private static AppConfig appConfig = null;	
   private String browser = "Chrome";	
   private String buyerBrowser = "FireFox";	
   private String classpath="";	
   private String domain = "qa2";
   private String locale = "en_US";
   private String companyName = "noosh";
   private static String customUI = "defaultUI";		
   private Properties appProp = new Properties();	
   private Properties instProp = new Properties();	
   private Properties xpathProps = new Properties();	

   private AppConfig() {
		
     /* String sysbrowser = System.getProperty("selenium.browser");		
      String sysbrowserBuyer = System.getProperty("selenium.browser.buyer");	
      String sysdomain = System.getProperty("selenium.domain");
      String syslocale = System.getProperty("selenium.locale");
      String syscompanyName = System.getProperty("selenium.companyname"); */
	      String sysbrowser = "Chrome";		
	      String sysbrowserBuyer = "Chrome";	
	      String sysdomain = "cloudqa";
	      String syscompanyName = "noosh";
	      String syslocale = "en_US";
      
      if (sysbrowser != null && !"".equals(sysbrowser.trim())) {			
         browser = sysbrowser;		
      }		
      if (syscompanyName != null && !"".equals(syscompanyName.trim())) {			
    	  companyName = syscompanyName;		
       }	
      if (sysbrowserBuyer != null && !"".equals(sysbrowserBuyer.trim())) {			
         buyerBrowser = sysbrowserBuyer;		
      }		
      String sysCustomUI = System.getProperty("selenium.customUI");		
      if (sysCustomUI != null && !"".equals(sysCustomUI.trim())) {			
         customUI = sysCustomUI;		
      }		
      if (sysdomain != null && !"".equals(sysdomain.trim())) {			
          domain = sysdomain;		
      }	
      
      if (syslocale != null && !"".equals(syslocale.trim())) {			
          locale = syslocale;		
      }	
      InputStream in = null;		
      try {			
          File fileOld = new File(AppConfig.class.getClassLoader().getResource("application.properties").getPath()); 			
          //File fileOld = new File(AppConfig.class.getResource("application.properties").getPath());
          classpath =  fileOld.getParent();			 
          in = AppConfig.class.getClassLoader().getResourceAsStream(					
                    "application.properties");			
          appProp.load(in);			
          in.close();		
          String instance = System.getProperty("selenium.instance");			
          if(instance == null){				
             instance = "sqa";			
          }			
          String instanceConf = "conf" + File.separator					
             + instance + ".properties";			
          in = AppConfig.class.getClassLoader().getResourceAsStream(					
             instanceConf);			
             instProp.load(in);			
             in.close();			
             String xpathRootPath = AppConfig.class.getClassLoader()				
                .getResource("xpath").getFile()					
                + File.separator					
                + browser					
                + File.separator					
                + customUI					
                + File.separator;			
           xpathResolver(new File(xpathRootPath), xpathProps);		
       } catch (IOException e) {			
          logger.error(e.getStackTrace());			
          throw new RuntimeException(e);		
          } finally {			
            try {				
               if (in != null) {					
                  in.close();				
               }			
          } catch (IOException e) {				
             logger.error(e.getStackTrace());				
             throw new RuntimeException(e);			
          }		
     }	
 }

   /**
	 
   * Load all xpath Properties	 
   *	 
   * @param xpathRepo	 
   * @param xpathProps	 
   */
	
   private void xpathResolver(File xpathRepo, Properties xpathProps) {		
      if (xpathRepo.isDirectory()) {			
         File[] listFiles = xpathRepo.listFiles();			
         for (File file : listFiles) {				
            if (file.isDirectory()) {					
                xpathResolver(file, xpathProps);				
         } else {					
            InputStream in = null;					
            try {						
                in = new FileInputStream(file);						
                xpathProps.load(in);					
            } catch (IOException e) {						
                logger.error(e.getStackTrace());						
                throw new RuntimeException(e);					
            } finally {						
         try {							
             if (in != null) {								
                in.close();
             }						
         } catch (IOException e) {							
            logger.error(e.getStackTrace());							
            throw new RuntimeException(e);						
         }					
      }				
   }			
   }		
  }	
}

   private static AppConfig getInstance() {		
      if (appConfig == null) {			
         appConfig = new AppConfig();		
       }		
       return appConfig;	
   }
   private String getInstProp(String key) {		
       return instProp.getProperty(key);	
   }

   public String getClasspath() {		
      return classpath;	
   }

   private String getAppProp(String key) {		
      return appProp.getProperty(key);	
   }	

   private String getXpathProp(String key) {		
      return xpathProps.getProperty(key);	
   }

   public static String getXpath(String key) {		
     return getInstance().getXpathProp(key);	
   }	

   public static String getXpath(String key, String index, String replace) {		
      String xpath = getInstance().getXpathProp(key);		
      String replaceIndex = "{" + index + "}"; 		
      return StringUtils.replace(xpath, replaceIndex, replace);	
   }

   public static String getJVMClasspath() {		
      return getInstance().getClasspath();	         
   }
	
   public static String getSeleniumRootURL() {		
      return getInstance().getInstProp("selenium.root.url");	
   }
	
   public static String getSeleniumWaitForPageLoadTime() {		
      return getInstance().getInstProp("selenium.pageload.time");	
   }	

   public static String getSeleniumWaitForPageLoadLongTime() {		
      return getInstance().getInstProp("selenium.pageload.longTime");	
   }	

   public static String getSPLoginName() {		
      return getInstance().getInstProp("selenium.serviceProvider.loginName");	
   }	

   public static String getSPLoginPWD() {		
      return getInstance().getInstProp("selenium.serviceProvider.password");	
   }

   public static String getBuyerLoginName() {		
      return getInstance().getInstProp("selenium.buyer.loginName");	
   }

   public static String getBuyerLoginPWD() {		
      return getInstance().getInstProp("selenium.buyer.password");	
   }

   public static int getSeleniumServerPort() {		
      return Integer				
          .valueOf(getInstance().getAppProp("selenium.server.port"));	
   }

   public static String getSeleniumServerHost() {		
      return getInstance().getAppProp("selenium.server.host");	
   }

   public static String getSeleniumBrowserStartCommand() {		
      return getInstance().getAppProp(				
         "selenium.browser.startCommand." + getInstance().getThisBrowser());	
   }
	
   public static String getBrowser(){		
      return getInstance().getThisBrowser();	
   }
	
   public static String getBuyerBrowser(){		
      return getInstance().getThisBuyerBrowser();	
   }	
   
   public static String getDomain(){
	  return getInstance().getThisDomain();
   }
   
   public static String getCompanyName(){
		  return getInstance().getThisCompanyName();
   }
   
   public static String getLocale(){
		  return getInstance().getThisLocale();
	   }
   
   public String getThisBuyerBrowser() {		
      return buyerBrowser;	
   }		     

   public String getThisBrowser() {		
      return browser;	
   }
   
   public String getThisCompanyName() {
		  return companyName;
   }
   
   public String getThisDomain() {
	  return domain;
   }
   
   public String getThisLocale() {
	  return locale;
  }
}
