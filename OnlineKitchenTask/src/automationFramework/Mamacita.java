package automationFramework;
import java.lang.String;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
public class Mamacita {
public WebDriver browser;
	public Mamacita(WebDriver browser) {
		this.browser = browser;
	}
	
   public String[] AddToCart()
   {
     String[] values = new String[2];
     String mamacitaClient = "/html/body/div/section/div/div/div/div/div[10]/section/div[2]/div/a";
     WebDriverWait wait = new WebDriverWait(browser, 30);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mamacitaClient)));
     JavascriptExecutor js = (JavascriptExecutor) browser;
     //Finds the Brand Mamacita and clicks on the brand.
     js.executeScript("document.querySelector(\"body > div > section > div > div > div > div > div:nth-child(10) > section > div.emotion--element.col-1.row-1.start-col-1.start-row-1.col-xs-5.start-col-xs-2.row-xs-5.start-row-xs-10.col-s-5.start-col-s-2.row-s-5.start-row-s-10.col-m-5.start-col-m-1.row-m-16.start-row-m-8.col-l-6.start-col-l-1.row-l-7.start-row-l-10.col-xl-5.start-col-xl-2.row-xl-6.start-row-xl-10 > div > a\").click()");
     
     
     wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address-input")));
     //Inputs address
     browser.findElement(By.id("address-input")).sendKeys("Semperstraﬂe 44, 1180 Wien, Austria");

     wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn--honest blattgold--form-banner-submit")));
     //clicks on the button.
     browser.findElement(By.className("btn--honest blattgold--form-banner-submit")).click();

     //copies the product price.
     values[0] = browser.findElement(By.className("price--default is--nowrap")).getText();

     wait.until(ExpectedConditions.presenceOfElementLocated(By.className("buybox--button ")));
     //Clicks on the AddToCart.
     browser.findElement(By.className("buybox--button ")).click();

     wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dish-price--total")));
     //Get the product price in Extras.
     values[1] = browser.findElement(By.id("dish-price--total")).getText();

     return values;
    }
}