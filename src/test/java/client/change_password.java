package client;

import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.testng.annotations.Test;

public class change_password {
	 public AndroidDriver driver;
	    public WebDriverWait WebDriverWait;

	    @BeforeTest
	    public void setup() throws MalformedURLException, URISyntaxException{
	        UiAutomator2Options options = new UiAutomator2Options()
				      .setDeviceName("Android")
				      .setAppPackage("com.onfleekq.client")
				      .setAppActivity("com.onfleekq.client.MainActivity")
				      .setAppWaitDuration(Duration.ofSeconds(60)); // ⏳ wait max 60s

	        try {
	            driver = new AndroidDriver(new URL("http://localhost:4723"), options);
	        	
	           
	        } catch (Exception e) {
	        	
	            Assert.fail(" App failed to launch within 60 seconds. Error: " + e.getMessage());
	        }

	//driver = new AndroidDriver(new URL("http://localhost:4723"), options);
	        
	    }
	    //tap function method
	    public void tapAtPoint(int x, int y) {
	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence tap = new Sequence(finger, 1);
	        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
	        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
	        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	        driver.perform(Arrays.asList(tap));
	    }

	    @Test
	    public void test() throws InterruptedException{
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

	// Sign-In Test
	    	// Sign-In
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)"))).click();


	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"))).click();


	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"))).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Sign-in "))).click();


	        WebElement el6 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"));
	        el6.click();
	        el6.sendKeys("shalomjsph@gmail.com");

	        WebElement el8 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));
	        el8.click();
	        el8.sendKeys("Shalom@1234");
	        
	        tapAtPoint(598, 516);

	        WebElement el9 = driver.findElement(AppiumBy.accessibilityId("Sign In"));
	        el9.click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Profile"))).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Password & Security"))).click();
	       
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Change Password"))).click();
	        
	        WebElement el4 =  wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
	        el4.click();
	        el4.sendKeys("Shalom@1234");
	        
	        tapAtPoint(598, 516);
	        
	        WebElement el5 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));
	        el5.click();
	        el5.sendKeys("Shalom@12345");

	        tapAtPoint(598, 516);
	            
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Change Password"))).click();
	     
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Close"))).click();
	        
 wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Change Password"))).click();
	        
	        WebElement CPW =  wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
	        CPW.click();
	        CPW.sendKeys("Shalom@12345");
	        
	        tapAtPoint(598, 516);
	        
	        WebElement NPW = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));
	        NPW.click();
	        NPW.sendKeys("Shalom@1234");

	        tapAtPoint(598, 516);
	            
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Change Password"))).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Success"))).click();	     
	        
	        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("Success")).isDisplayed());
	        
	      }

	    @AfterTest
	    public void close() throws InterruptedException{
	    	
	    	Thread.sleep(5000);
	        driver.quit();
	    }

}
