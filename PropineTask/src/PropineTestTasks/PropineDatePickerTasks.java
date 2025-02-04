package PropineTestTasks;
import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.*;
import TestFramework.Propine;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import Utilities.Utility;
public class PropineDatePickerTasks {
	public WebDriver browser;
	
    @BeforeTest
    public void beforeTest() {
    	
       System.setProperty("webdriver.chrome.driver", ".//WebDrivers//chromedriver.exe");
	   //Initialize Browser.
	   browser = new ChromeDriver();
       //Maximize browser
       browser.manage().window().maximize();
    }

    @Test
    public void VerifyDatePickerWithValidDateFormatsAndValidSeparators() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario1");
		     System.out.println("Test VerifyDatePickerWithValidDateFormatsAndValidSeparators started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithValidDateFormatsAndValidSeparators", Utility.getData(eElement, "ValidDate"), Utility.getData(eElement, "DateFormats"),Utility.getData(eElement, "Separators"), Utility.getData(eElement, "ExpectedResult"),"");	         
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithValidDateFormatsAndInValidSeparators() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario2");
		     System.out.println("Test VerifyDatePickerWithValidDateFormatsAndInValidSeparators started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithValidDateFormatsAndInValidSeparators", Utility.getData(eElement, "ValidDate"), Utility.getData(eElement, "DateFormats"),Utility.getData(eElement, "Separators"), Utility.getData(eElement, "ExpectedResult"),"");	         
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithValidDateFormatsAndValidSeparatorsWith00Date() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario1");
		     System.out.println("Test VerifyDatePickerWithValidDateFormatsAndValidSeparatorsWith00Date started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithValidDateFormatsAndValidSeparatorsWith00Date", Utility.getData(eElement, "InvalidDate00"), Utility.getData(eElement, "DateFormats"),Utility.getData(eElement, "Separators"), Utility.getData(eElement, "ExpectedResultForInvalid"),"");	        
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithValidDateFormatsAndValidSeparatorsWith32Date() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario1");
		     System.out.println("Test VerifyDatePickerWithValidDateFormatsAndValidSeparatorsWith32Date started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithValidDateFormatsAndValidSeparatorsWith32Date", Utility.getData(eElement, "InvalidDate32"), Utility.getData(eElement, "DateFormats"),Utility.getData(eElement, "Separators"), Utility.getData(eElement, "ExpectedResultForInvalid"),"");	         
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithInValidDateFormatsWithoutYearAndAllSeparators() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario3");
		     System.out.println("Test VerifyDatePickerWithInValidDateFormatsWithoutYearAndAllSeparators started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithInValidDateFormatsWithoutYearAndAllSeparators", Utility.getData(eElement, "ValidDate"), Utility.getData(eElement, "DateFormats"),Utility.getData(eElement, "Separators"), Utility.getData(eElement, "ExpectedResult"),"");	         
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithInValidDateFormatsWithoutDateAndAllSeparators() {
	   try {
		   
		     Element eElement = (Element) Utility.getNodeData("Scenario4");
		     System.out.println("Test VerifyDatePickerWithInValidDateFormatsWithoutDateAndAllSeparators started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithInValidDateFormatsWithoutDateAndAllSeparators", Utility.getData(eElement, "ValidDate"), Utility.getData(eElement, "DateFormats"),Utility.getData(eElement, "Separators"), Utility.getData(eElement, "ExpectedResult"),"");	         
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithValidDateFormatsWithAllSeparatorsAndString() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario5");
		     System.out.println("Test VerifyDatePickerWithValidDateFormatsWithAllSeparatorsAndString started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithValidDateFormatsWithAllSeparatorsAndString", Utility.getData(eElement, "ValidDate"), Utility.getData(eElement, "DateFormats"),Utility.getData(eElement, "Separators"), Utility.getData(eElement, "ExpectedResult"),Utility.getData(eElement, "ShippingMessage"));	         
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithOnlyDateOrMonthOrYear() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario5");
		     System.out.println("Test VerifyDatePickerWithInvalidMonthAndValidYear started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     String actualResult = dPicker.ClickOnDateFormatValidator(Utility.getData(eElement, "InvalidDate"));
		     boolean result = actualResult.contains(Utility.getData(eElement, "ExpectedResult"));
		     String testResult = "Passed.";
		     Document doc = Utility.CreateXMLDocument("VerifyDatePickerWithInvalidMonthAndValidYear");
		     if(result == false) testResult = "Failed.";
		     doc = Utility.AddTestResults(doc,"VerifyDatePickerWithInvalidMonthAndValidYear",Utility.getData(eElement, "InvalidDate"), actualResult, Utility.getData(eElement, "ExpectedResult"), testResult);
		     if(result == false) doc = Utility.AddTestRunResult(doc, "Failed");
		     else doc = Utility.AddTestRunResult(doc, "Passed");
		     Utility.SaveXMLDocument("VerifyDatePickerWithInvalidMonthAndValidYear", doc);
		     System.out.println("Test VerifyDatePickerWithInvalidMonthAndValidYear completed execution.");
		     if(result == false) Assert.fail("One or more of the Date Format has failed with the expected condition.");
	   } 
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void VerifyDatePickerWithIndividualDateFormatsWithoutSeparators() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario5");
		     System.out.println("Test VerifyDatePickerWithIndividualDateFormatsWithoutSeparators started execution.");
		     Propine dPicker = new Propine(browser);
		     dPicker.LaunchPropine(Utility.getData(eElement, "PropineUrl"));
		     dPicker.ValidateDateParser("VerifyDatePickerWithIndividualDateFormatsWithoutSeparators", Utility.getData(eElement, "InvalidFormatsDate"), Utility.getData(eElement, "InvalidFormats"),"", Utility.getData(eElement, "ExpectedResult"),"");	         
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    @AfterTest
    public void afterTest() {
    	browser.manage().deleteAllCookies();
    	//Close browser
	    browser.close();
	    //Quits ChromeDriver.
	    browser.quit();
    }
}
