package TestFramework;
import java.io.Console;
import java.lang.String;
import java.util.concurrent.TimeUnit;
import javax.swing.text.html.parser.DocumentParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.xml.dom.DomXmlParser;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Document;
import Utilities.Utility;
public class Propine {
	//Container variable which is used to store the webDriver runtime instance.
	public WebDriver browser;
	long timeOut = 1000;
	String dateTextArea_CSS = "div > div:nth-child(1) > form > div > input";
	String submitButton_CSS = "div > div:nth-child(1) > form > input";
	String resultTextArea_CSS = "div > div:nth-child(2) > div";
	//Constructor which is used to initialize the webDriver runtime instance.
	public Propine(WebDriver browser) {
		this.browser = browser;
	}
	//Launching the WalletHub.
	public void LaunchPropine(String url)
	{
		browser.get(url);
		System.out.println("Launched Propine application.");
	}
	//Enters the formatted Date and clicks on Submit button and returns the result.
	public String ClickOnDateFormatValidator(String formattedDate)
	{
		browser.findElement(By.cssSelector(dateTextArea_CSS)).sendKeys(formattedDate);
		browser.findElement(By.cssSelector(submitButton_CSS)).click();
		Utilities.Utility.waitForPageLoad(browser, 20000);
		return browser.findElement(By.cssSelector(resultTextArea_CSS)).getText();
	}
	//Validates the Date Parser with the DateFormats.
	public void ValidateDateParser(String scenario,String date,String dateFormats,String separators,String expectedValue,String message)
	{
		
		String testReport = "The Report for the Scenario:"+scenario;
		Document doc = null;
		try
		{
		String[] dateFormat = dateFormats.split(",");
		boolean testResultFlag = true;
		WebDriverWait wait = new WebDriverWait(browser, 1000);
		Utilities.Utility.waitForPageLoad(browser, 10000);
		String testResult = "Passed.";
		doc = Utility.CreateXMLDocument(scenario);
		if(separators == "") {
			for(int i=0;i<dateFormat.length;i++)
			{
				String dateFormatWithOutSeparator = dateFormat[i];
				String formattedDate = Utility.DateFormatter(date, dateFormatWithOutSeparator);
				String actualResult = ClickOnDateFormatValidator(formattedDate);
				boolean condition = actualResult.contains(expectedValue);
				if(testResultFlag)
				{
				   if(condition == false)  testResultFlag = false;
				}
				if(condition == false) testResult = "Failed.";
				else testResult = "Passed.";
				doc = Utility.AddTestResults(doc, scenario,formattedDate, actualResult, expectedValue, testResult);
			 }
		}
		else {
	        	String[] separator = separators.split("separator");
		        for(int i=0;i<separator.length;i++)
		        {
			      for(int j=0;j<dateFormat.length;j++)
			      {
				    String dateFormatWithSeparator = dateFormat[j].replace("separator", separator[i]);
				    String formattedDate = Utility.DateFormatter(date, dateFormatWithSeparator);
				    if(scenario.contains("VerifyDatePickerWithValidDateFormatsWithAllSeparatorsAndString")) formattedDate = message +" "+ formattedDate;
				    String actualResult = ClickOnDateFormatValidator(formattedDate);
				    boolean condition = actualResult.contains(expectedValue);
				    if(testResultFlag)
				    {
				      if(condition == false)  testResultFlag = false;
				    }
				    if(condition == false) testResult = "Failed.";
				    else testResult = "Passed.";
				    doc = Utility.AddTestResults(doc,scenario, formattedDate, actualResult, expectedValue, testResult);
			      }
		        }
		}
		
		if(testResultFlag == false) {
			 doc = Utility.AddTestRunResult(doc, "Failed");
			 Utility.SaveXMLDocument(scenario, doc);
			 System.out.println("Test "+scenario+" completed execution.");
			 Assert.fail("One or more of the Date Format has failed with the expected condition.");
		}
		else {
			  doc = Utility.AddTestRunResult(doc, "Passed");
			  Utility.SaveXMLDocument(scenario, doc);			  
		}
		}
		catch(Exception ex)
		{
			doc = Utility.AddTestRunResult(doc, ex.getMessage());
			Utility.SaveXMLDocument(scenario, doc);
			Assert.fail("TestCase got failed with the exception.");
		}
		
	}
}


