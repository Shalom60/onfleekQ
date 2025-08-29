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



public class Change_password {
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
        
        Thread.sleep(5000);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Password & Security\")"))).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Change Password\")"))).click();


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
