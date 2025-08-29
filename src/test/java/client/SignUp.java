package  client;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
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
import java.util.Random;


public class SignUp {
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

        
    }
    
    public static final Random random = new Random();

    // Generates a random number between 0 (inclusive) and max (exclusive)
    public static int generateRandomNumber() {
		return random.nextInt();
    }
    
    @Test
    public void test(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(20000));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)")));
        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)"));
        el1.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)")));
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"));
        el2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)")));
        WebElement el3 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"));
        el3.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.ImageView")));
        WebElement el4 = driver.findElement(AppiumBy.className("android.widget.ImageView"));
        el4.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Let’s Get Started")));
        WebElement el5 = driver.findElement(AppiumBy.accessibilityId("Let’s Get Started"));
        el5.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
        WebElement el6 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"));
        el6.click();
        el6.sendKeys("Shalom");

        WebElement el7 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));
        el7.click();
        el7.sendKeys("Joseph");

        WebElement el8 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)"));
        el8.click();
        el8.sendKeys("shalom" + generateRandomNumber() + "@mailinator.com");

        WebElement el9 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)"));
        el9.click();
        el9.sendKeys("07035624380");

        WebElement el10 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)"));
        el10.click();
        el10.sendKeys("Shalom@1234");
        
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(332, 89);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
            PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
            
        WebElement sex = driver.findElement(AppiumBy.accessibilityId("Male"));
        sex.click();

        WebElement el11 = driver.findElement(AppiumBy.className("android.widget.CheckBox"));
        el11.click();

        WebElement el12 = driver.findElement(AppiumBy.accessibilityId("Sign Up"));
        el12.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Verify OTP")));
        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("Verify OTP")).isDisplayed());

        var packageName = driver.executeScript("mobile: getCurrentPackage");
        var caps = driver.getSessionId();

    }

    @AfterTest
    public void close() throws InterruptedException{
    	
	  Thread.sleep(5000);
      if (driver != null) driver.quit();
    }
}


