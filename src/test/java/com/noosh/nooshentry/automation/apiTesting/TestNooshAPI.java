package com.noosh.nooshentry.automation.apiTesting;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import com.noosh.nooshentry.automation.demoSQANoosh.Page;

public class TestNooshAPI extends BaseSeleniumTest {
	
	Page page = new Page(driver); 
	static String playGroundURI, chromeApiClientURI, nooshApiLink;
	static String clientID;
	static String secret, accessToken;
	static String loginName, passwordSP;
	static String request = "http://api.sqa.noosh.com/api/v1/";
	static String requestMethod;
	
	@BeforeTest
	public static void setParameters() throws FileNotFoundException
	{
		playGroundURI = "http://api.sqa.noosh.com/oauthplayground/v1/client";
		chromeApiClientURI = "http://hackst.com/";
		nooshApiLink = "http://api.sqa.noosh.com/api/v1/workgroups";
		clientID = "2aac6c4c04ea4e0cad12f19b73fc3379";
		secret = "65e4507b569b46929dfca072cfdd7b4a";
		loginName = "nobody+sp@noosh.com";
		passwordSP = "17password";
	}
	
	@Test(description="Get Noosh API Playground")
	@Parameters({"Go to playground"})
	public void testGetNooshAPIPlayground() throws InterruptedException
	{		
		driver.get(playGroundURI);
		driver.manage().window().maximize();
		Thread.sleep(1500);		
	}
	
	@Test(description="Set client ID and secret")
	@Parameters({""})
	public void testOauthSettingAndAuthorization() throws InterruptedException
	{
		PlayGround playGround = new PlayGround(driver);
		
		playGround.openOauthSettingTab();
		Thread.sleep(2000);
		playGround.setClientID(clientID);
		playGround.setClientSecret(secret);
		playGround.clickNextBT();
		Thread.sleep(2000);
		playGround.clickAuthorizeBT();
		Thread.sleep(2000);
	}
	
	@Test(description="Get authorization code")
	@Parameters({""})
	public void testStep2AuthorizationTab() throws InterruptedException
	{
		PlayGround playGround = new PlayGround(driver);
		GrantPermission grantPermission = new GrantPermission(driver);
		
		grantPermission.login(loginName, passwordSP);
		Thread.sleep(2000);
		grantPermission.clickGrantPermissionBT();
		Thread.sleep(2000);
		playGround.clickGetAuthorizationCadeForToken();
		Thread.sleep(2000);		
	}
	
	@Test(description="Read access token")
	public void testGetAccessToken() throws AWTException, InterruptedException, HeadlessException, 
	     UnsupportedFlavorException, IOException
	{
		PlayGround playGround = new PlayGround(driver);
		
		accessToken = playGround.getAccessToken();
		System.out.println("Access token: " + playGround.getAccessToken());
		log.info("Access token: " + playGround.getAccessToken());
	}
	
	@Test(description="Get request")
	public void testGetRequest() throws InterruptedException
	{
		PlayGround playGround = new PlayGround(driver);
		
		playGround.clickRequestTab();
		Thread.sleep(2000);
		playGround.setRequestURI(nooshApiLink);
		playGround.clickFetchBT();
		Thread.sleep(2000);
	}
	
	@Test(description="Get Workspace info")
	public void testGetWorkspaceInfo()
	{
		PlayGround playGround = new PlayGround(driver);
		
		System.out.println("Request info: " + playGround.getReuqestInfo());
	}
	
	@Test(description="Get request for Workgoups method")
	public void testGetRequestAPIWorkgroups() throws InterruptedException, AWTException
	{
		Hackst hackst = new Hackst(driver);		
		 
		requestMethod = request + "workgroups?access_token=" + accessToken;
		driver.get(chromeApiClientURI);
		Thread.sleep(2500);
		hackst.setRequestURI(requestMethod);		
		hackst.clickExecuteBT();
		Thread.sleep(2500);
		/*
		System.out.println("First workgroup ID: " + hackst.getFirstGroupID());
		System.out.println("First workgroup: " + hackst.getFirstWorkgroup());
		System.out.println("Second workgroup ID: " + hackst.getSecondGroupID());
		System.out.println("Second workgroup: " + hackst.getSecondWorkgroup());
		System.out.println("Is default workgroup2: " + hackst.getDefault2());
		System.out.println("Is default workgroup1: " + hackst.getDefault1());
		System.out.println("Status code: " + hackst.getStatusCode());
		log.info("First workgroup ID: " + hackst.getFirstGroupID());
		log.info("First workgroup: " + hackst.getFirstWorkgroup());
		log.info("Second workgroup ID: " + hackst.getSecondGroupID());
		log.info("Second workgroup: " + hackst.getSecondWorkgroup());	
		*/	
	}
	
	@Test(description="Verification data for Workgroups method")
	@Parameters({"workgroupID2", "workgroupID", "workgroupName2", "workgroupName", "isDefaultWG1", "isDefaultWG2"})
	public void testVerificationAPIWorkgroups(String workgroupID2, String workgroupID, String workgroupName2, 
			String workgroupName, String isDefaultWG1, String isDefaultWG2) throws InterruptedException, AWTException
	{
		Hackst hackst = new Hackst(driver);	
		
		if(hackst.getStatusCode().trim().equals("200")) {
			log.info("Status code for workgroups method is: " + hackst.getStatusCode());
			try{
				AssertJUnit.assertEquals(workgroupID, hackst.getFirstGroupID().trim());
				log.info("Workgroups - first workgroup ID retrieved and match");
			} catch(Throwable e) {
				System.out.println("Workgroups - first workgroup ID does not match");
				log.info("Workgroups - first workgroup ID does not match");
				log.info("Actual first workgroup ID is: " + hackst.getFirstGroupID());
			}
			try{
				AssertJUnit.assertEquals(workgroupID2, hackst.getSecondGroupID().trim());
				log.info("Workgroups - second workgroup ID retrieved and match");
			} catch(Throwable e) {
				System.out.println("Workgroups - second workgroup ID does not match");
				log.info("Workgroups - second workgroup ID does not match");
				log.info("Actual second workgroup ID is: " + hackst.getSecondGroupID());
			}
			try{
				AssertJUnit.assertEquals('"' + workgroupName + '"', hackst.getFirstWorkgroup().trim());
				log.info("Workgroups - first workgroup name retrieved and match");
			} catch(Throwable e) {
				System.out.println("Workgroups - first workgroup name does not match");
				log.info("Workgroups - first workgroup name does not match");
				log.info("Actual first workgroup name is: " + hackst.getFirstWorkgroup());
			}
			try{
				AssertJUnit.assertEquals('"' + workgroupName2 + '"', hackst.getSecondWorkgroup());
				log.info("Workgroups - second workgroup name retrieved and match");
			} catch(Throwable e) {
				System.out.println("Workgroups - second workgroup name does not match");
				log.info("Workgroups - second workgroup name does not match");
				log.info("Actual second workgroup name is: " + hackst.getSecondWorkgroup());
			}
			try{
				AssertJUnit.assertEquals(isDefaultWG1, hackst.getDefault1());
				log.info("Workgroups - is_default for first workgroup retrieved and match");
			} catch(Throwable e) {
				System.out.println("Workgroups - is_default for first workgroup does not match");
				log.info("Workgroups - is_default for first workgroup does not match");
				log.info("Actual is_default for first workgroup is: " + hackst.getDefault1());
			}
			try{
				AssertJUnit.assertEquals(isDefaultWG2, hackst.getDefault2());
				log.info("Workgroups - is_default for second workgroup retrieved and match");
			} catch(Throwable e) {
				System.out.println("Workgroups - is_default for second workgroup does not match");
				log.info("Workgroups - is_default for second workgroup does not match");
				log.info("Actual is_default for second workgroup is: " + hackst.getDefault2());
			}
		} else {
			log.info("Actual status code for Workgrops method is : " + hackst.getStatusCode());
		}
		Thread.sleep(1500);
	}	
	
	@Test(description="Get new request")
	public void testGetNewRequest()
	{
		Hackst hackst = new Hackst(driver);
		
		hackst.clickEditBT();
	}
		
	@Test(description="Get request for Projects method")
	public void testGetRequestAPIProjects() throws InterruptedException, AWTException
	{
		Hackst hackst = new Hackst(driver);	
		
		requestMethod = request + "workgroups/5096272/projects?access_token=" + accessToken;
//		driver.get(chromeApiClientURI);
		Thread.sleep(2500);
		hackst.setRequestURI(requestMethod);		
		hackst.clickExecuteBT();
		Thread.sleep(2500);
	}
	
	@Test(description="Verification data for Projects method")
	@Parameters({"clientAccount", "projectId", "projectName"})
	public void testVerificationAPIProjects(String clientAccount, String projectId, 
			String projectName) throws InterruptedException, AWTException
	{
		Hackst hackst = new Hackst(driver);	
		
		if(hackst.getStatusCode().trim().equals("200")) {
			log.info("Status code for projects method is: " + hackst.getStatusCode());
			/*
			System.out.println("Client account: " + hackst.getProjectAccountName());
			System.out.println("Project ID: " + hackst.getProjectID());
			System.out.println("Project name: " + hackst.getProjectName());
			*/
			try{
				AssertJUnit.assertEquals('"' + clientAccount + '"', hackst.getProjectAccountName().trim());
				log.info("Projects - projects client account retrieved and match");
			} catch(Throwable e) {
				System.out.println("Projects - projects client account does not match");
				log.info("Projects - projects client account does not match");
				log.info("Actual projects client account is: " + hackst.getProjectAccountName());
			}
			try{
				AssertJUnit.assertEquals(projectId, hackst.getProjectID().trim());
				log.info("Projects - project ID retrieved and match");
			} catch(Throwable e) {
				System.out.println("Projects - project ID does not match");
				log.info("Projects - project ID does not match");
				log.info("Actual project ID is: " + hackst.getProjectID());
			}
			try{
				AssertJUnit.assertEquals('"' + projectName + '"', hackst.getProjectName().trim());
				log.info("Projects - project name retrieved and match");
			} catch(Throwable e) {
				System.out.println("Projects - project name does not match");
				log.info("Projects - projects name does not match");
				log.info("Actual project name is: " + hackst.getProjectName());
			}
		} else {
			log.info("Actual status code for Projects method is : " + hackst.getStatusCode());
		}
		Thread.sleep(2500);
	}
}
