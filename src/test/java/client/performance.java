package client;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.*;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class performance {
	public AndroidDriver driver;
    public WebDriverWait WebDriverWait;
	
	@BeforeTest
    public void setup() throws IOException, InterruptedException, URISyntaxException {
	
        UiAutomator2Options options = new UiAutomator2Options()
        		.setDeviceName("onfleekq")
                .setAppPackage("com.onfleekq.client")
                .setAppActivity("com.onfleekq.client.MainActivity")
                .setNewCommandTimeout(Duration.ofSeconds(300));
        

        driver = new AndroidDriver(new URL("http://localhost:4723"), options);

    }
	 @Test
	    public void A_cold_start_LaunchTime() throws Exception {

	        // Terminate the app (cold stop)
	        driver.terminateApp("com.onfleekq.client");

	        long startTime = System.currentTimeMillis();

	        // Relaunch the app
	        driver.activateApp("com.onfleekq.client");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

	       // Wait for a specific element that marks full load 
	        WebElement Onboarding = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)")));

	        long endTime = System.currentTimeMillis();
	        long launchTime = endTime - startTime;

	        System.out.println("🚀 App Cold Start Launch Time: " + launchTime + " ms");

	        Assert.assertTrue(Onboarding.isDisplayed(), "Element should be visible after launch");
	    }

	    @Test
	    public void B_warm_start_LaunchTime() throws Exception {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));

	        //For warm start
	        driver.runAppInBackground(Duration.ofSeconds(2));

	        long startTime = System.currentTimeMillis();

	        // Wait for a specific element that marks full load
	        WebElement Onboarding = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)")));

	        long endTime = System.currentTimeMillis();
	        long launchTime = endTime - startTime;

	        System.out.println("🚀 App Warm Start Launch Time: " + launchTime + " ms");

	        Assert.assertTrue(Onboarding.isDisplayed(), "Element should be visible after launch");

	        // Log memory usage
	        logMemoryUsage("com.onfleekq.client");
	    }

	    public void logMemoryUsage(String packageName) {
	        try {
	            System.out.println("\n📊 Fetching memory usage for package: " + packageName);
	            String command = "adb shell dumpsys meminfo " + packageName;
	            Process process = Runtime.getRuntime().exec(command);

	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;

	            boolean found = false;
	            while ((line = reader.readLine()) != null) {
	                // Print key memory info lines
	                if (line.contains("TOTAL") || line.contains("Java Heap") || line.contains("Native Heap") ||
	                        line.contains("Graphics") || line.contains("Private Other") || line.contains("System")) {
	                    System.out.println(line.trim());
	                    found = true;
	                }
	            }

	            if (!found) {
	                System.out.println("⚠️ No memory usage data found. Is the app running and package name correct?");
	            }

	            reader.close();
	        } catch (Exception e) {
	            System.err.println("❌ Error fetching memory info: " + e.getMessage());
	        }
	    }

	    @AfterTest
	    public void close() throws InterruptedException {

	        Thread.sleep(500);
	        driver.quit();
	    }
}
