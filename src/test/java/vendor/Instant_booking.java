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


public class Instant_booking {
	
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
    public void B_accept_instant_booking() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Skip"))).click();

        WebElement login_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Log In")));
        login_btn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter your email\")"))).sendKeys("kovoj66383@calorpg.com");

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")")).sendKeys("Shalom@1234");

        WebElement el6 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\" Sign in\").instance(1)"));
        el6.click();
       
      //add pause, so success modal closes.
        Thread.sleep(2000);
        
        //wait for 5 secs for request to come in
        Thread.sleep(5000);
        
        
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

        WebElement yes_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Yes\")")));
        yes_btn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(31)")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(31)")).isDisplayed());


    }

    @Test
    public void C_send_message(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

        WebElement text_box = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.EditText")));
        text_box.click();

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(276, 660);
        var end = new Point (295, 292);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

        text_box.click();
        text_box.sendKeys("Hello, please make payment.");

        final var fingerd = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint1 = new Point(200, 200);
        var tap1 = new Sequence(fingerd, 1);
        tap1.addAction(fingerd.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint1.x, tapPoint1.y));
        tap1.addAction(fingerd.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap1.addAction(new Pause(fingerd, Duration.ofMillis(50)));
        tap1.addAction(fingerd.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap1));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(27)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Today\")")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Today\")")).isDisplayed());

        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"No\")"));
        el1.click();
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(24)"));
        el2.click();
    }


    @AfterTest
    public void close() {

        driver.quit();
    }
}
