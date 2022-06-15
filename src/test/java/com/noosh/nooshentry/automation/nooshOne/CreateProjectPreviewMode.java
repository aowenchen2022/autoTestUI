package com.noosh.nooshentry.automation.nooshOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CreateProjectPreviewMode extends Page
{
    public CreateProjectPreviewMode(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
    
    @FindBy(xpath="createProjectPreviewMode.brochureLink")
    private WebElement brochureLink;
    
    @FindBy(xpath="createProjectPreviewMode.projectNameInput")
    private WebElement projectNameInput;
    
    @FindBy(className="ui-datepicker-trigger")
    private WebElement complationDateCal;
    
    @FindBy(linkText="10")
    private WebElement complationDate;
    
    public void clickBrochureLink()
    {
    	brochureLink.click();
    }
    public void setProjectName(String projectName)
    {
    	type(projectNameInput, projectName);
    }
    public void callCalendar()
    {
    	complationDateCal.click();
    }
    public void setComplationDate()
    {
    	complationDate.click();
    }
}
