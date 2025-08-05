package  client;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;

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

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;




public class Booking {
    public AndroidDriver driver;
    public WebDriverWait WebDriverWait;

    @BeforeTest
    public void setup() throws MalformedURLException, URISyntaxException{
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability( "platformName",  "Android");
        dc.setCapability( "appium:automationName",  "uiautomator2");
        dc.setCapability( "appium:uiautomator2ServerLaunchTimeout",  "60000");
        dc.setCapability( "appium:app",  System.getProperty("user.dir")+ "/apps/onfleekq_client.apk");

        driver = new AndroidDriver(new URI(appiumServerUrl).toURL(), dc);
        
    }

    @Test
    public void A_login_test() {
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
    public void B_Instant_booking_salon() throws InterruptedException {  //Test instant booking for salon appointment
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

        Thread.sleep(15000);

        WebElement book_now_btn = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.widget.ImageView"));
        book_now_btn.click();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(11)"))).click();


        WebElement service_name = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.EditText")));
        service_name.click();
        service_name.sendKeys("Test");
        
        Thread.sleep(15000);


        WebElement service_dd = driver.findElement(AppiumBy.accessibilityId("Select Service"));
        service_dd.click();

        Thread.sleep(15000);

        WebElement select_service = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Gele")));
        select_service.click();
        
        Thread.sleep(15000);

        WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Salon Appointment\nEnjoy professional services in a dedicated setting\")")));
        location.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed"))).click();
        
        Thread.sleep(15000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Select Vendor"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Test')]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed"))).click();

        // slider for when you want service
        final var finger_time = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(53, 1178);
        var end = new Point(395, 1184);
        Sequence swipe = new Sequence(finger_time, 1);
//        var swipe = new Sequence(finger, 1); 
        swipe.addAction(finger_time.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger_time.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger_time.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger_time.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Send Booking Request"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Awaiting \")")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Awaiting \")")).isDisplayed());


    }

    @Test
    public void C_Instant_booking_home() throws InterruptedException{  //Test instant booking for salon appointment
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

        //go back to preferred location screen
        WebElement return_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")));
        return_btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();

        WebElement location = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Home Appointment\nBringing services to the comfort of your home\")"));
        location.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed"))).click();
        
        Thread.sleep(15000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Select Vendor"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Test')]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed"))).click();

        // slider for when you want service
        final var finger_time = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(53, 1178);
        var end = new Point(395, 1184);
        Sequence swipe = new Sequence(finger_time, 1);
//        var swipe = new Sequence(finger, 1); 
        swipe.addAction(finger_time.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger_time.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger_time.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger_time.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Send Booking Request"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Awaiting \")")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Awaiting \")")).isDisplayed());


    }

    //reusable method for tap function
    public void tapAtPoint(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    //For this test always have an 8pm time slot for wednesday
    @Test
    public void D_schedule_booking_home() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));
        
        WebElement return_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")));
        return_btn.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();

        //click home button
        WebElement home_btn = driver.findElement(AppiumBy.accessibilityId("Home"));
        home_btn.click();
        
        Thread.sleep(10000);
        
        WebElement el8 = driver.findElement(AppiumBy.accessibilityId("Male grooming"));
      el8.click();
      WebElement el9 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)"));
      el9.click();
      
      Thread.sleep(10000);
      
      tapAtPoint(138, 200);
      
      Thread.sleep(1000);
      
      tapAtPoint(327, 1143);
      
      Thread.sleep(1000);
      
      tapAtPoint(185, 1161);
      
      Thread.sleep(1000);
      
      tapAtPoint(139, 1257);
      tapAtPoint(324, 1152);
      
      Thread.sleep(10000);

      
     
      tapAtPoint(652, 1443);

      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Book Service"))).click();
      
      Thread.sleep(10000);
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.ImageView"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'W')]"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("8:00 PM"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
      
      Thread.sleep(10000);
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed to Payment"))).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Agree & Proceed"))).click();

      Thread.sleep(3000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Pay')]"))).click();

        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Pay')]")).isDisplayed());


    }
    
//    @Test
//    public void E_schedule_booking_salon() throws InterruptedException{
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));
//        
//        WebElement return_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")));
//        return_btn.click();
//        
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Dismiss')]"))).click();
//        
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Decline')]"))).click();
//        
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)"))).click();
//
//        //click home button
//        WebElement home_btn = driver.findElement(AppiumBy.accessibilityId("Home"));
//        home_btn.click();
//        
//        Thread.sleep(10000);
//        
//        WebElement el8 = driver.findElement(AppiumBy.accessibilityId("Male grooming"));
//      el8.click();
//      WebElement el9 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)"));
//      el9.click();
//      
//      Thread.sleep(10000);
//      
//      tapAtPoint(138, 200);
//      
//      Thread.sleep(1000);
//      
//      tapAtPoint(327, 1143);
//      
//      Thread.sleep(1000);
//      
//      tapAtPoint(185, 1161);
//      
//      Thread.sleep(1000);
//      
//      tapAtPoint(139, 1257);
//      tapAtPoint(324, 1152);
//      
//      Thread.sleep(10000);
//
//      
//     
//      tapAtPoint(652, 1443);
//
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Book Service"))).click();
//      
//      Thread.sleep(10000);
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.ImageView"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'W')]"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("9:00 PM"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();
//      
//      Thread.sleep(10000);
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed to Payment"))).click();
//      
//      wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Agree & Proceed"))).click();
//
//      Thread.sleep(3000);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Pay')]"))).click();
//
//        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Pay')]")).isDisplayed());
//   }


    @AfterTest
    public void close() throws InterruptedException{
    	
    	Thread.sleep(5000);
        driver.quit();
    }
}

