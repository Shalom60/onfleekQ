package vendor;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.Point;
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

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;





public class Support {

	  public AndroidDriver driver;
	    public WebDriverWait WebDriverWait;

	    @BeforeTest
	    public void setup() throws MalformedURLException{
	    	   UiAutomator2Options options = new UiAutomator2Options()
	                   .setDeviceName("Android")
	                   .setAppPackage("com.onfleekq_vendor")
	                   .setAppActivity("com.onfleekq_vendor.MainActivity")
	    	           .setAppWaitDuration(Duration.ofSeconds(60)); // ⏳ wait max 60s

	           try {
	               driver = new AndroidDriver(new URL("http://localhost:4723"), options);
	           	
	              
	           } catch (Exception e) {
	           	
	               Assert.fail(" App failed to launch within 60 seconds. Error: " + e.getMessage());
	           }

	           
	       }
				      
	    @Test
	    public void A_send_email_test() throws InterruptedException {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

	         wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Skip"))).click();

	         WebElement login_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Log In")));
	         login_btn.click();

	         wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter your email\")"))).sendKeys("s.halomjoseph815@gmail.com");

	         driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")")).sendKeys("Shalom@1234");

	         WebElement el6 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\" Sign in\").instance(1)"));
	         el6.click();
	        
	       //add pause, so success modal closes.
	         Thread.sleep(2000);
	         
	         wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Profile"))).click();
	        
	         Thread.sleep(3000);
	         //swipe down 
	         final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	         var start = new Point(336, 1161);
	         var end = new Point (378, 200);
	         var swipe = new Sequence(finger, 1);
	         swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
	                 PointerInput.Origin.viewport(), start.getX(), start.getY()));
	         swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	         swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
	                 PointerInput.Origin.viewport(), end.getX(), end.getY()));
	         swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	         driver.perform(Arrays.asList(swipe));

	    	
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Help Center, "))).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"\").instance(3)"))).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Email Subject\")"))).sendKeys("Test");
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Type a message\")"))).sendKeys("Testing support email");
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Send Email"))).click();

	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Chat With Us\")")));
	        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Chat With Us\")")).isDisplayed());

	    }

	    @Test
	    public void chat_with_support_test() throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));     
	        
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"\").instance(1)"))).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Start New Chat"))).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.onfleekq_vendor:id/siq_positive_button"))).click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.onfleekq_vendor:id/siq_time_middle_layout_textview")));
	        Assert.assertTrue(driver.findElement(AppiumBy.id("com.onfleekq_vendor:id/siq_time_middle_layout_textview")).isDisplayed());
	    }

	    @AfterTest
	    public void close(){
	    	driver.quit();
	    	
	    }
}
