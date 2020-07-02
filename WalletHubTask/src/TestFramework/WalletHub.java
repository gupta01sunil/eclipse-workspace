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

public class WalletHub {
	//Container variable which is used to store the webDriver runtime instance.
	public WebDriver browser;
	long timeOut = 1000;
	String loginTab_AfterReview_CSS = "#join-light > form > div > nav > ul > li:nth-child(2)";
	String emailAddress_AfterReview_CSS = "#join-light > form > div > div.ng-animate-enabled.basic-trans.enter > input";
	String password_AfterReview_CSS = "#join-light > form > div > div:nth-child(3) > input";
	String login_AfterReview_CSS = "#join-light > form > div > div.btns > button";
	String reviews_XPath = "//*[@id='scroller']/main/div[1]/nav/div[2]/a[2]/span[2]";
	String review_Rating_CSS = "#reviews-section > div.review-stat-box > div.review-action.ng-enter-element > review-star > div > svg:nth-child(4)";
	String review_ProductSelection_Dropdown_CSS = "#reviews-section > modal-dialog > div > div > write-review > div > ng-dropdown > div > span";
	String review_Product_HealthInsurance_CSS = "#reviews-section > modal-dialog > div > div > write-review > div > ng-dropdown > div > ul > li:nth-child(2)";
	String writeReview_CSS = "#reviews-section > modal-dialog > div > div > write-review > div > div.android > textarea";
	String submit_CSS = "div.sbn-action.semi-bold-font.btn.fixed-w-c.tall";
	String productPage_CSS = "#scroller > main > div.profile-header-module.profile-comp-header > div.profile-info > div.info.text-select > h1";
	String confirmreview_text_XPath = "//*[@id='scroller']/main/div/div/div[2]/div/div[2]/p";
	String productLink_Profile_CSS = "#scroller > main > div.pr-content > div > section > div > div > div > div.pr-rec-texts-container > a";
	String reviewerName_CSS = "#reviews-section > section > article:nth-child(1) > div.rvtab-ci-top.text-select > div.rvtab-ci-author.semi-bold-font > span.rvtab-ci-name";
	String rating_CSS = "#reviews-section > section > article:nth-child(1) > review-star";
	String reviewDescription_CSS = "#reviews-section > section > article:nth-child(1) > div.rvtab-ci-content.with-links.text-select";
	//Constructor which is used to initialize the webDriver runtime instance.
	public WalletHub(WebDriver browser) {
		this.browser = browser;
	}
	//Launching the WalletHub.
	public void LaunchWalletHub(String url)
	{
		browser.get(url);
		System.out.println("Launched WalletHub application.");
	}
	//Loginto the wallethub after creating the review.
	public void LogInto_AfterReview_WalletHub(String userName,String password)
	{
		WebDriverWait wait = new WebDriverWait(browser, 150);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loginTab_AfterReview_CSS)));
		browser.findElement(By.cssSelector(loginTab_AfterReview_CSS)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(emailAddress_AfterReview_CSS)));
		browser.findElement(By.cssSelector(emailAddress_AfterReview_CSS)).sendKeys(userName);
		browser.findElement(By.cssSelector(password_AfterReview_CSS)).sendKeys(password);
		browser.findElement(By.cssSelector(login_AfterReview_CSS)).click();
		Utilities.Utility.waitForPageLoad(browser, 10000);
		System.out.println("Logged into the application after providing a review to the product.");
	}
	
	//Review Rating.
	public void ReviewRating(String reviewMessage)
	{
		WebDriverWait wait = new WebDriverWait(browser, 200);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(reviews_XPath)));
		browser.findElement(By.xpath(reviews_XPath)).click();
		Actions mouse = new Actions(browser);	
		mouse.moveToElement(browser.findElement(By.cssSelector(review_Rating_CSS))).perform();
		mouse.click(browser.findElement(By.cssSelector(review_Rating_CSS))).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(review_ProductSelection_Dropdown_CSS)));
		browser.findElement(By.cssSelector(review_ProductSelection_Dropdown_CSS)).click();
		browser.findElement(By.cssSelector(review_Product_HealthInsurance_CSS)).click();		
		browser.findElement(By.cssSelector(writeReview_CSS)).sendKeys(reviewMessage);
		browser.findElement(By.cssSelector(submit_CSS)).click();
		Utilities.Utility.waitForPageLoad(browser, 10000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productPage_CSS)));
		System.out.println("Completed writing review to a product and submitted.");
		
	}
		
	//Locate to the Profile and confirms the review rating.
	public void ConfirmReviewFromProfile(String profileUrl,String reviewMessage,String reviewerNameExptd,String reviewerRatingExptd)
	{
		WebDriverWait wait = new WebDriverWait(browser, 1000);
		browser.get(profileUrl);
		Utilities.Utility.waitForPageLoad(browser, 10000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productLink_Profile_CSS)));
		browser.findElement(By.cssSelector(productLink_Profile_CSS)).click();
		Utilities.Utility.waitForPageLoad(browser, 5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(reviewerName_CSS)));
		Actions mouse = new Actions(browser);
		mouse.moveToElement(browser.findElement(By.cssSelector(reviewerName_CSS))).perform();
		String reviewerName = browser.findElement(By.cssSelector(reviewerName_CSS)).getText();
		Assert.assertEquals(reviewerName,reviewerNameExptd,"The Reviewer Name doesnot matching.");
		String reviewerRating = browser.findElement(By.cssSelector(rating_CSS)).getAttribute("ng-reflect-rating");
		Assert.assertEquals(reviewerRating,reviewerRatingExptd,"The Rating doesnot matching.");
		String reviewDescription = browser.findElement(By.cssSelector(reviewDescription_CSS)).getText();
		Assert.assertEquals(reviewDescription,reviewMessage,"The Rating Description doesnot matching.");
		System.out.println("Completed reviewing the product review on the profile page.");
	}			
}
