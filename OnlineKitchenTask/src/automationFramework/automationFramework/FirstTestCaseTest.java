package automationFramework.automationFramework;
import automationFramework.Mamacita;
import java.lang.String;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;

public class FirstTestCaseTest {
	public String url = "https://clubkitchen.at";
	public WebDriver browser;
	
@BeforeTest
public void beforeTest() {
	System.setProperty("webdriver.chrome.driver", "G://chromedriver_win32//chromedriver.exe");

	//Initialize Browser.
	browser = new ChromeDriver();
	
	//Launching App Url
	browser.get(url);

    //Maximize browser
    browser.manage().window().maximize();
}

  @Test
  public void testTask() {
	  try {
	  Mamacita testcase = new Mamacita(browser);
	  String[] values = new String[2];
	  values = testcase.AddToCart();
	  Assert.assertEquals(values[0], values[1],"The Expected Order Price doesnot matched with Extras Price.");
	  }
	  catch (Exception ex) {
       throw new RuntimeException(ex.getMessage());
	  }
  }
  
  @AfterTest
  public void afterTest() {
	//Close browser
	  browser.close();

	  //Quites ChromeDriver.
	  browser.quit();
  }
}
