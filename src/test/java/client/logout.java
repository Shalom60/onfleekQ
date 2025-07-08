package  client;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import java.util.Arrays;





public class logout {
    public AndroidDriver driver;
    public WebDriverWait WebDriverWait;

    @BeforeTest
    public void setup() throws MalformedURLException{
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability( "platformName",  "Android");
        dc.setCapability( "appium:automationName",  "uiautomator2");
        dc.setCapability( "appium:uiautomator2ServerLaunchTimeout",  "60000");
        dc.setCapability( "appium:app",  System.getProperty("user.dir")+ "/apps/onfleekq_client.apk");

        driver = new AndroidDriver(new URL(appiumServerUrl), dc);
    }

    @Test
    public void A_login_test(){
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

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

    	        WebElement el9 = driver.findElement(AppiumBy.accessibilityId("Sign In"));
    	        el9.click();

    	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))).click();

    	        // Sign-in pass or fail check
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("What service do you need today?")));
    	        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("What service do you need today?")).isDisplayed());


    }
    @Test
    public void B_logout_test() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));
    
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Profile"))).click();

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(289, 1184);
        var end = new Point (292, 520);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
            PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
            PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
          
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Logout"))).click();

        Thread.sleep(2000);
        
        final var finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        var start1 = new Point(100, 1354);
        var end1 = new Point (640, 1354);
        var swipe1 = new Sequence(finger1, 1);
        swipe1.addAction(finger1.createPointerMove(Duration.ofMillis(0),
            PointerInput.Origin.viewport(), start1.getX(), start1.getY()));
        swipe1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe1.addAction(finger1.createPointerMove(Duration.ofMillis(1000),
            PointerInput.Origin.viewport(), end1.getX(), end1.getY()));
        swipe1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe1));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Let’s Get Started")));
        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("Let’s Get Started")).isDisplayed());


    }

    @AfterTest
    public void close() throws InterruptedException{
    	
    	Thread.sleep(5000);
        driver.quit();
    }
}
