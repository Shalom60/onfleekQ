package vendor;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
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



public class Change_password {
    public AndroidDriver driver;
    public WebDriverWait WebDriverWait;

    @BeforeTest
    public void setup() throws MalformedURLException{
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability( "platformName",  "Android");
        dc.setCapability( "appium:automationName",  "uiautomator2");
        dc.setCapability( "appium:app",  System.getProperty("user.dir")+ "/apps/onfleekq_vendor.apk");

        driver = new AndroidDriver(new URL(appiumServerUrl), dc);

    }

    @Test
    public void change_password_test() throws InterruptedException {
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
        //swipe down to see logout button
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

        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Password & Security\")"));
        el1.click();

        WebElement el2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Change Password\")")));
        el2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter your current password\")"))).sendKeys("Shalom@1234");

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter your new password\")"))).sendKeys("Shalom@12345");

        WebElement save_btn = driver.findElement(AppiumBy.accessibilityId("Save"));
        save_btn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter your current password\")"))).sendKeys("Shalom@12345");

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter your new password\")"))).sendKeys("Shalom@1234");

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Save"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(16)")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(16)")).isDisplayed());

    }



    @AfterTest
    public void close(){
    	driver.quit();
    }
}
