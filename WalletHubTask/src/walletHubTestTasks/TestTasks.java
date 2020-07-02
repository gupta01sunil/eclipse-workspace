package walletHubTestTasks;
import java.lang.String;
import org.testng.annotations.*;
import TestFramework.Facebook;
import TestFramework.WalletHub;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import org.w3c.dom.Element;
import Utilities.Utility;
public class TestTasks {
	
	public WebDriver browser;
	
    @BeforeTest
    public void beforeTest() {
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-notifications");
       options.addArguments("disable-popup-blocking");
       DesiredCapabilities capabilities = DesiredCapabilities.chrome();
       capabilities.setCapability(ChromeOptions.CAPABILITY, options);
       System.setProperty("webdriver.chrome.driver", ".//WebDrivers//chromedriver.exe");

	   //Initialize Browser.
	   browser = new ChromeDriver(capabilities);

       //Maximize browser
       browser.manage().window().maximize();
    }

    @Test
    public void Scenario1() {
	   try {
		     Element eElement = (Element) Utility.getNodeData("Scenario1");
		     System.out.println("Test Scenario1 started execution.");
		     Facebook fc = new Facebook(browser);
	         fc.LaunchFacebook(Utility.getData(eElement, "FBUrl"));
	         fc.LogIn(Utility.getData(eElement, "UserName"),Utility.getData(eElement, "Password"));
	         fc.CreatePost(Utility.getData(eElement, "Post"));
	         fc.AssertPost(Utility.getData(eElement, "Post"));
	         fc.LogOut();
	         System.out.println("Test Scenario1 completed execution.");
	   }
	   catch (Exception ex) {
          throw new RuntimeException(ex.getMessage());
	   }
    }
    
    @Test
    public void Scenario2() {
    	try {
    		Element eElement = (Element) Utility.getNodeData("Scenario2");
    		System.out.println("Started Scenario2.");
    		WalletHub wH = new WalletHub(browser);
    		wH.LaunchWalletHub(Utility.getData(eElement, "WalletHubUrl"));
    		wH.ReviewRating(Utility.getData(eElement, "ReviewMessage"));
    		wH.LogInto_AfterReview_WalletHub(Utility.getData(eElement, "UserName"),Utility.getData(eElement, "Password"));
    		wH.ConfirmReviewFromProfile(Utility.getData(eElement, "ProfileUrl"),Utility.getData(eElement, "ReviewMessage"),Utility.getData(eElement, "ReviewerName"),Utility.getData(eElement, "ReviewRating"));
    		System.out.println("Completed Scenario2.");
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
