package vendor;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.*;

public class PerformanceLog {

    public AndroidDriver driver;
    public PerformanceLog logger;


    @BeforeTest
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android")
                .setAppPackage("com.onfleekq_vendor")
                .setAppActivity("com.onfleekq_vendor.MainActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723"), options);
    }

    @Test
    public void logPerformance() throws Exception{
        System.out.println("🔍 Logging performance for: " + "com.onfleekq_vendor");

        sleep(9000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(60000));
        
        // Terminate the app (cold stop)
        driver.terminateApp("com.onfleekq_vendor");

        long startTime = System.currentTimeMillis();

        // Relaunch the app
        driver.activateApp("com.onfleekq_vendor");


       // Wait for a specific element that marks full load 
        WebElement Onboarding = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Skip\")")));

        long endTime = System.currentTimeMillis();
        long launchTime = endTime - startTime;

        System.out.println("🚀 App Cold Start Launch Time: " + launchTime + " ms");



        //For warm start
        driver.runAppInBackground(Duration.ofSeconds(2));

        long startTime1 = System.currentTimeMillis();

        // Wait for a specific element that marks full load (e.g., Home screen title)
        WebElement Onboarding1 = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Skip\")")));

        long endTime1 = System.currentTimeMillis();
        long launchTime1 = endTime1 - startTime1;

        System.out.println("🚀 App Warm Start Launch Time: " + launchTime1 + " ms");

        Assert.assertTrue(Onboarding1.isDisplayed(), "Element should be visible after launch");



        //all log methods
//        logLaunchTime();
        logMemoryUsage("com.onfleekq_vendor");
        logCpuUsage("com.onfleekq_vendor");
        logFPS("com.onfleekq_vendor");
        logBatteryUsage("com.onfleekq_vendor");

    }

    // method for memory usage
    private void logMemoryUsage(String packageName) {
        System.out.println("📦 Memory usage for " + packageName);
        try {
            System.out.println("\n📦 Memory Usage:");
            String command = "adb shell dumpsys meminfo " + packageName;
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("TOTAL") || line.contains("Java Heap") ||
                        line.contains("Native Heap") || line.contains("Graphics") ||
                        line.contains("Private Other") || line.contains("System")) {
                    System.out.println(line.trim());
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("❌ Error fetching memory info: " + e.getMessage());
        }
    }

    private void logCpuUsage(String packageName) {
        System.out.println("⚙️ CPU usage for " + packageName);

        try {
            String command = "adb shell top -n 1 | findstr com.onfleekq_ve+"; //onfleekq is shortened cos of output width
            Process process = Runtime.getRuntime().exec("cmd /c " + command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                System.out.println("📈 CPU Usage: " + parts[8] + "%");
                System.out.println("🧠 Memory Usage: " + parts[9] + "%");
                System.out.println("⏱️ Time Used: " + parts[10]);
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("❌ Error fetching CPU usage: " + e.getMessage());
        }
    }
    public void logFPS(String packageName) {
        try {
            System.out.println("🎮 Checking FPS using gfxinfo...");
            String command = "adb shell dumpsys gfxinfo " + packageName;
            Process process = Runtime.getRuntime().exec("cmd /c " + command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean capture = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("** Graphics info for pid")) {
                    capture = true; // Start capturing after reaching the correct section
                }

                if (capture) {
                    if (line.contains("Total frames rendered:")
                            || line.contains("Janky frames:")
                            || line.contains("50th percentile:")
                            || line.contains("90th percentile:")
                            || line.contains("95th percentile:")
                            || line.contains("99th percentile:")
                            || line.contains("Number Missed Vsync")
                            || line.contains("Number High input latency")
                            || line.contains("Number Slow UI thread")
                            || line.contains("Number Frame deadline missed")) {
                        System.out.println(line.trim());
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("❌ Error fetching FPS: " + e.getMessage());
        }
    }

    public String getAppUid(String packageName) {
        String uid = "";
        try {
            String command = "adb shell dumpsys package " + packageName;
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("userId=")) {
                    uid = line.split("=")[1].trim();
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uid;
    }
    public void logBatteryUsage(String packageName) {
            try {
            System.out.println("🔋 Checking battery usage...");
            String uid = getAppUid("com.onfleekq_vendor");
            if (uid.isEmpty()) {
                System.err.println("Could not determine UID for package: " + packageName);

            }

            Process process = Runtime.getRuntime().exec("adb shell dumpsys batterystats");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            boolean found = false;
            String line;
            String uid_ = uid.substring(uid.length() - 3);  // "197" extract the last digit of uid
            System.out.println(uid_);
            while ((line = reader.readLine()) != null) {
                if (line.contains(uid_)) {
                    System.out.println(line.trim());
                    continue;
                }

                if (found) {
                    if (line.isEmpty() || line.startsWith("uid ")) break;
                    //appStats.add(line);
                    System.out.println("line is empty");
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void close() throws InterruptedException {
       driver.quit();
    }
}


