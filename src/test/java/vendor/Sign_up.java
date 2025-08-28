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
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import java.util.Random;

public class Sign_up {
	
	 public AndroidDriver driver;
	    public WebDriverWait WebDriverWait;

	    @BeforeTest
	    public void setup() throws MalformedURLException{
	    	   UiAutomator2Options options = new UiAutomator2Options()
	                   .setDeviceName("Android")
	                   .setAppPackage("com.onfleekq_vendor")
	                   .setAppActivity("com.onfleekq_vendor.MainActivity");
	           driver = new AndroidDriver(new URL("http://localhost:4723"), options);
	       }	    

	        public static final Random random = new Random();

	        // Generates a random number between 0 (inclusive) and max (exclusive)
	        public static int generateRandomNumber() {
				return random.nextInt();
	        }
	    
	    @Test
	    public void test() throws InterruptedException{
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Skip"))).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Register"))).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Email\")"))).sendKeys("shalom" + generateRandomNumber() + "@mailinator.com");

	        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")")).sendKeys("Shalom@1234");
	       

	        //tap screen to pull down keypad
	        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        var tapPoint = new Point(200, 200);
	        var tap = new Sequence(finger, 1);
	        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
	                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
	        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
	        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	        driver.perform(Arrays.asList(tap));

	        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter your number e.g 07012345678\")")).sendKeys("01234567891");

	        //tap screen to pull down keypad
	        driver.perform(Arrays.asList(tap));

	        WebElement el6 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign Up\")"));
	        el6.click();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Enter OTP\"]")));
	        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Enter OTP\"]")).isDisplayed());


	    }

	    @AfterTest
	    public void close() throws InterruptedException{
	    	
	    	Thread.sleep(3000);
	        driver.quit();
	    }


	
}
