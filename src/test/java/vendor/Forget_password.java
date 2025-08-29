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

public class Forget_password {
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
    public void forget_password_test(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));




        //tap screen to pull down keypad
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(200, 200);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Skip"))).click();

        WebElement login_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Log In")));
        login_btn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Forgot Password?\")"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.EditText"))).sendKeys("shallynize@gmail.com");

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Send Code\")")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Confirm Code sent to your\")")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Confirm Code sent to your\")")).isDisplayed());

    }



    @AfterTest
    public void close(){
        driver.quit();
    }


}
