package TestFramework;
import java.lang.String;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import Utilities.Utility;

public class Facebook {
	//Container variable which is used to store the webDriver runtime instance.
	public WebDriver browser;
	long timeOut = 1000;
	String userName_ID = "email";
	String password_ID = "pass";
	String logIn_ID = "loginbutton";
	String writeMessage_CSS = "._3en1._480e.navigationFocus._23pl";
	String writePost_CSS = "div._1mf._1mj";
	String post_CSS = "._1mf7._4r1q._4jy0._4jy3._4jy1._51sy.selected._42ft";
	String createdPost_CSS = "._5_jv._58jw";
	String navigation_ID = "userNavigationLabel";
	String logout_CSS = "._54nc";
	//Constructor which is used to initialize the webDriver runtime instance.
	public Facebook(WebDriver browser) {
		this.browser = browser;
	}
	//Launch Facebook Url.
	public void LaunchFacebook(String url)
	{
		browser.get(url);
		System.out.println("Facebook page launched.");
	}
	//Loginto the facebook.
    public void LogIn(String userName,String password)
    {
	   browser.findElement(By.id(userName_ID)).sendKeys(userName);
	   browser.findElement(By.id(password_ID)).sendKeys(password);
	   browser.findElement(By.id(logIn_ID)).click();
	   Utility.waitForPageLoad(browser, 10000);
	   System.out.println("LoggedInto Facebook.");
    }
    
    //Create a Post.
    public void CreatePost(String message)
    {
       WebDriverWait wait = new WebDriverWait(browser, 150);
       Actions mouse = new Actions(browser);
       wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(writeMessage_CSS)));
       mouse.click(browser.findElement(By.cssSelector(writeMessage_CSS))).perform();
       wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(writePost_CSS)));
 	   browser.findElement(By.cssSelector(writePost_CSS)).sendKeys(message);
 	   wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(post_CSS)));
 	   browser.findElement(By.cssSelector(post_CSS)).click();
 	   Utility.waitForPageLoad(browser, 10000);
 	   System.out.println("Created Post.");
    }
    
    //Logout Facebook.
    public void LogOut()
    {
    	WebDriverWait wait = new WebDriverWait(browser, 130);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id(navigation_ID)));
    	browser.findElement(By.id(navigation_ID)).click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(logout_CSS)));
    	browser.findElement(By.cssSelector(logout_CSS)).click();
    	System.out.println("Logged out from the Facebook.");
    }
    //Assert the Created post.
    public void AssertPost(String message)
    {
    	WebDriverWait wait = new WebDriverWait(browser, 130);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(createdPost_CSS)));
    	String createdPost = browser.findElement(By.cssSelector(createdPost_CSS)).getText();
    	Assert.assertEquals(createdPost, message,"The Expected Post doesnot matched with the Actual Post.");
    	System.out.println("Completed validation on the post.");
    }
    
}
