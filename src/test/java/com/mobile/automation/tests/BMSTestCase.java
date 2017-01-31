package com.mobile.automation.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Khaja on 1/27/17.
 */
public class BMSTestCase {

    static DesiredCapabilities capabilities;
    static AppiumDriver<MobileElement> driver;
    static String movieName = "Wajah Tum Ho";

    public static void main(String[] args) {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        capabilities.setCapability("app", "/Users/Khaja/Documents/TheMaestro/themaestro/resources/com.bt.bms_5.0.13.apk");
        capabilities.setCapability("appPackage", "com.bt.bms");
        capabilities.setCapability("appActivity", "com.movie.bms.splash.views.activities.SplashScreenActivity");
        capabilities.setCapability("clearSystemFiles", true);
        capabilities.setCapability("autoAcceptAlerts", true);

        try {
            //driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("com.bt.bms:id/language_text_view_label")).click();
        //driver.findElementById("com.bt.bms:id/language_text_view_label").click();
        //driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.bt.bms:id/language_text_view_label\")").click();
        //driver.findElementByPartialLinkText("Let's Get Started").click();

        driver.findElementById("com.bt.bms:id/launcher_tv_for_skip").click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //driver.switchTo().alert().accept();

        //Wait<?> wait = new WebDriverWait(driver, 60);
        //wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        //wait.until();

        //WaitClass.waitFor(driver, By.id("com.bt.bms:id/btn_positive"), 60);
        //System.out.println(driver.switchTo().alert().getText());
        //driver.switchTo().alert().accept();

        driver.findElement(By.id("com.bt.bms:id/btn_positive")).click();
        //WaitClass.waitFor(driver, By.partialLinkText("Hyderabad"), 60);
        //driver.findElement(By.partialLinkText("Hyderabad")).click();
        //WaitClass.waitFor(driver, MobileBy.linkText("Hyderabad"), 60);
        driver.findElements(By.id("com.bt.bms:id/polular_location_recyclerview_adapter_rl")).get(3).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(driver.findElementByAndroidUIAutomator("new UiScrollable().scrollTextIntoView(\"Gautamiputra Satakarni\")").getText());

        Dimension screenSize = driver.manage().window().getSize();
        int height = screenSize.height;
        int width = screenSize.width;

        int startX = width/2;
        int startY = (int)(height*0.80);
        int endX = startX;
        int endY = (int)(height*0.20);
        //System.out.println(driver.getPageSource());

        /*while(!driver.findElementByAndroidUIAutomator
                ("new UiScrollable(new UiSelector()." +
                        "resourceId(\"com.bt.bms:id/nowshowing_recycler_view\")." +
                        "scrollable(true).instance(0)).scrollIntoView(new UiSelector()." +
                        "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\")." +
                        "text(\"Wajah Tum Ho\"))").isDisplayed()){
                driver.swipe(startX, startY, endX, endY, 3000);
            driver.findElementByAndroidUIAutomator
                        ("new UiScrollable(new UiSelector()." +
                                "resourceId(\"com.bt.bms:id/nowshowing_recycler_view\")." +
                                "scrollable(true).instance(0)).scrollIntoView(new UiSelector()." +
                                "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\")." +
                                "text(\"Kaabil\"))").click();
        }*/

        int movies = ((AndroidDriver)driver).findElementsByAndroidUIAutomator("new UiSelector()." +
                "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\")").size();
        System.out.println("Total number of movies displayed: "+movies);

        boolean flag = true;
        while(flag){
            List<MobileElement> elements = ((AndroidDriver)driver).findElementsByAndroidUIAutomator("new UiSelector()." +
                    "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\")");
            for(MobileElement element:elements){
                if(element.getAttribute("text").equalsIgnoreCase("Wajah Tum Ho")){
                    ((AndroidDriver)driver).findElementByAndroidUIAutomator("new UiSelector()." +
                            "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\").text(\"Wajah Tum Ho\")").
                            click();
                    flag=false;
                    break;
                }
            }
            driver.swipe(startX, startY, endX, endY, 3000);
            /*String movieName = driver.findElementByAndroidUIAutomator("new UiSelector()." +
                    "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\")").getAttribute("text");
            System.out.println(movieName);
            if(movieName.equalsIgnoreCase("Wajah Tum Ho")){
                driver.findElementByAndroidUIAutomator("new UiSelector()." +
                        "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\").text(\"Wajah Tum Ho\")").
                        click();
                break;
            }*/
        }
        System.out.println("Out of WHILE Loop!");

        /*if(driver.findElementByAndroidUIAutomator("new UiSelector().description(\"description\")").isDisplayed()){
            driver.findElementByAndroidUIAutomator("new UiSelector().description(\"description\")").click();
        }*/
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.bt.bms:id/nowshowing_recycler_view\").scrollable(true).instance(0)).scrollForward(10)");
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.bt.bms:id/nowshowing_recycler_view\").scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\").text(\"Kaabil\"))").click();
        //driver.swipe(2, 2, 6, 6, 2);
        /*int boxes = driver.findElements(By.id("com.bt.bms:id/nowshowing_card_view_rel_movie_text_view")).size();
        System.out.println("Number of movies currently playing: "+boxes);

        List<MobileElement> elements = driver.findElements(By.id("com.bt.bms:id/nowshowing_card_view_rel_movie_text_view"));

        for(MobileElement element:elements){
            movieName = element.findElement(By.id("com.bt.bms:id/nowshowing_card_view_text_movie_name")).getText();
            System.out.println(movieName);
            if(movieName.equalsIgnoreCase("Raees")){
                System.out.println("Trying to click on the movie name "+movieName);
                element.findElement(By.id("com.bt.bms:id/movie_details_activity_btn_book")).click();
            }
        }*/

        try {
            IOSDriver driver1 = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}