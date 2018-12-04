package com.multple.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ResultsAndAssert {
  private WebDriver driver;
  public String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String SortValue = "Price: High to Low";

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.amazon.co.uk/";
    
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSelect2ndResultsAndAssert() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("nikon"); // search  by nikon
    driver.findElement(By.name("site-search")).submit();
   
	new Select(driver.findElement(By.id("sort"))).selectByVisibleText(SortValue); // sort by price high to low
    waitSeconds(3);
    driver.findElement(By.xpath("//li[1]/div/div/div/div[2]/div/div/a/h2")).click();
  
    driver.findElement(By.id("//div[31]/div/div/div[2]/p")).getText().contains("Error");
  
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public void waitSeconds(int Seconds) throws InterruptedException {
	  
	  TimeUnit.SECONDS.sleep(Seconds);  
  }
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
