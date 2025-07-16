package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;


public class Forgot_password {
    public AndroidDriver driver;
    public WebDriverWait WebDriverWait;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability( "platformName",  "Android");
        dc.setCapability( "appium:automationName",  "uiautomator2");
        dc.setCapability( "appium:uiautomator2ServerLaunchTimeout",  "60000");
        dc.setCapability( "appium:app",  System.getProperty("user.dir")+ "/apps/onfleekq_client.apk");

        driver = new AndroidDriver(new URL(appiumServerUrl), dc);
    }

    @Test
    public void test() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

// Sign-In Test
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Sign-in ")));
        WebElement el4 = driver.findElement(AppiumBy.accessibilityId("Sign-in "));
        el4.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Forgot Password?")));
        WebElement el_forgot = driver.findElement(AppiumBy.accessibilityId("Forgot Password?"));
        el_forgot.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.EditText")));
        WebElement el_text = driver.findElement(AppiumBy.className("android.widget.EditText"));
        el_text.click();
        el_text.sendKeys("shalomjsph@gmail.com");
        
        
        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(3)"));
        el1.click();

        WebElement el_proceed = driver.findElement(AppiumBy.accessibilityId("Proceed"));
        el_proceed.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'OTP has been sent to')]")));
        
        Thread.sleep(3000);
        
        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'OTP has been sent to')]")).isDisplayed());
        
      }

    @AfterTest
    public void close() throws InterruptedException{
    	
    	Thread.sleep(5000);
        driver.quit();
    }

}
