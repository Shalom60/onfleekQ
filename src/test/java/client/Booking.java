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
    public void B_Instant_booking_salon() {  //Test instant booking for salon appointment
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));


        WebElement book_now_btn = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.widget.ImageView"));
        book_now_btn.click();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(11)"))).click();


        WebElement service_name = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.EditText")));
        service_name.click();
        service_name.sendKeys("Test");


        WebElement service_dd = driver.findElement(AppiumBy.accessibilityId("Select Service"));
        service_dd.click();

        // tap on select service dropdown
        int service_X = service_dd.getRect().getX() + service_dd.getRect().getWidth() / 2;
        int service_Y = (int) (service_dd.getRect().getY() + service_dd.getRect().getHeight() / 2);

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint1 = new Point(service_X, service_Y);
        var tap1 = new Sequence(finger, 1);
        tap1.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint1.x, tapPoint1.y));
        tap1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap1.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//        driver.perform(Arrays.asList(tap1));


        WebElement select_service = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Gele")));
        select_service.click();

        WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Salon Appointment\nEnjoy professional services in a dedicated setting\")")));
        location.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Select Vendor"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Hair braiding\n0\n1 Reviews\n5 hours 30 minutes\n₦20000\")"))).click();

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
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Send Booking Request"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Awaiting \")")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Awaiting \")")).isDisplayed());


    }

    @Test
    public void C_Instant_booking_home(){  //Test instant booking for salon appointment
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

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Select Vendor"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Hair braiding\n0\n1 Reviews\n5 hours 30 minutes\n₦20000\")"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed"))).click();

        // slider for when you want service
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(53, 1178);
        var end = new Point(395, 1184);
        Sequence swipe = new Sequence(finger, 1);
//        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Send Booking Request"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Awaiting")));
        Assert.assertTrue(driver.findElement(AppiumBy.accessibilityId("Awaiting")).isDisplayed());


    }


    @Test
    public void D_schedule_booking_salon(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

        //click home button
        WebElement home_btn = driver.findElement(AppiumBy.accessibilityId("Home"));
        home_btn.click();

        WebElement click_hair = driver.findElement(AppiumBy.accessibilityId("Hair"));
        click_hair.click();

        WebElement seeAll = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"See all\").instance(0)"));
        seeAll.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Sbeauty\\n7.89 km\\n5.00")));
        WebElement button = driver.findElement(AppiumBy.accessibilityId("Sbeauty\\n7.89 km\\n5.00"));



// Get center coordinates of the vendor to select and tap
        int startX = button.getRect().getX() + button.getRect().getWidth() / 2;
        int startY = button.getRect().getY() + button.getRect().getHeight() / 2;

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(startX, startY);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Book Now\").instance(1)")));
        WebElement book_now_btn = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Book Now\").instance(1)"));
        book_now_btn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"?\nFreelancer\nFor Maximum Availability\")"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("3:00 PM - 3:30 PM"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.ImageView")));
        WebElement select_location = driver.findElement(AppiumBy.className("android.widget.ImageView"));



// Get center coordinates of the location choice to select and tap
        int salon_X = select_location.getRect().getX() + button.getRect().getWidth() / 2;
        int salon_Y = (int) (select_location.getRect().getY() + button.getRect().getHeight() * 0.1);

        final var finger_click = new PointerInput(PointerInput.Kind.TOUCH, "finger_click");
        var clickPoint = new Point(salon_X, salon_Y);
        var click = new Sequence(finger_click, 1);
        click.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        click.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        click.addAction(new Pause(finger, Duration.ofMillis(50)));
        click.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(click));

//        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//        var tapPoint = new Point(617, 1770);
//        var tap = new Sequence(finger, 1);
//        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
//                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
//        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
//        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//        driver.perform(Arrays.asList(tap));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Continue"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed to Payment"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Agree & Proceed"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"PAYSTACK CHECKOUT\")")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PAYSTACK CHECKOUT\")")).isDisplayed());


    }

    @Test
    public void E_schedule_booking_home(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

        //click home button
        WebElement home_btn = driver.findElement(AppiumBy.accessibilityId("Home"));
        home_btn.click();

        WebElement click_hair = driver.findElement(AppiumBy.accessibilityId("Hair"));
        click_hair.click();

        WebElement seeAll = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"See all\").instance(0)"));
        seeAll.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Sbeauty\\n7.89 km\\n5.00")));
        WebElement button = driver.findElement(AppiumBy.accessibilityId("Sbeauty\\n7.89 km\\n5.00"));



// Get center coordinates of the vendor to select and tap
        int startX = button.getRect().getX() + button.getRect().getWidth() / 2;
        int startY = button.getRect().getY() + button.getRect().getHeight() / 2;

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(startX, startY);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"Book Now\").instance(1)")));
        WebElement book_now_btn = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Book Now\").instance(1)"));
        book_now_btn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().description(\"?\nFreelancer\nFor Maximum Availability\")"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("6:00 PM - 6:30 PM"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.widget.ImageView")));
        WebElement select_location = driver.findElement(AppiumBy.className("android.widget.ImageView"));



// Get center coordinates of the location choice to select and tap
        int salon_X = select_location.getRect().getX() + button.getRect().getWidth() / 2;
        int salon_Y = (int) (select_location.getRect().getY() + button.getRect().getHeight() * 0.5);

        final var finger_click = new PointerInput(PointerInput.Kind.TOUCH, "finger_click");
        var clickPoint = new Point(salon_X, salon_Y);
        var click = new Sequence(finger_click, 1);
        click.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        click.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        click.addAction(new Pause(finger, Duration.ofMillis(50)));
        click.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(click));

//        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//        var tapPoint = new Point(617, 1770);
//        var tap = new Sequence(finger, 1);
//        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
//                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
//        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
//        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//        driver.perform(Arrays.asList(tap));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Continue"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Proceed to Payment"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Agree & Proceed"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"PAYSTACK CHECKOUT\")")));
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PAYSTACK CHECKOUT\")")).isDisplayed());


    }


    @AfterTest
    public void close(){
        driver.quit();
    }
}

