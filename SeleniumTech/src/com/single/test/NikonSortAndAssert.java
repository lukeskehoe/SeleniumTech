package com.single.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NikonSortAndAssert {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String SortValue = "Price: High to Low";
  public String SearchValue ="nikon";
  
 public String assertContainsValue ="nikon";

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.amazon.co.uk/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testNikonSortAndAssert() throws Exception {
   driver.get(baseUrl);
    
   driver.findElement(By.id("twotabsearchtextbox")).sendKeys(SearchValue);
    
   driver.findElement(By.name("site-search")).submit();
    
   new Select(driver.findElement(By.id("sort"))).selectByVisibleText(SortValue);
   
   waitSeconds(3);
  
   driver.findElement(By.xpath("//li[1]/div/div/div/div[2]/div/div/a/h2")).click();
  
   assertEquals(assertContainsValue, driver.findElement(By.cssSelector("#productTitle")).getText());
  
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

//  private boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
  
  public void waitSeconds(int Seconds) throws InterruptedException {
	  
	  TimeUnit.SECONDS.sleep(Seconds);  
  }

//  private boolean isAlertPresent() {
//    try {
//      driver.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }
//
//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
}
