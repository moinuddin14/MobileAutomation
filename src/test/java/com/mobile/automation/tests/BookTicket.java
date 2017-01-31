package com.mobile.automation.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Khaja on 1/31/17.
 */
public class BookTicket {

    DesiredCapabilities capabilities;
    AppiumDriver<MobileElement> driver;
    String apkPath = System.getProperty("user.dir")+"/resources/com.bt.bms_5.0.13.apk";
    WebDriverWait wait;
    String actualMovie;
    String expectedMovie = "Wajah Tum Ho";
    String actualTime = "06:00 PM";

    @Test
    public void forMovieWajahTumHo(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", apkPath);
        capabilities.setCapability("newCommandTimeout", 60);
        capabilities.setCapability("appPackage", "com.bt.bms");
        capabilities.setCapability("appActivity", "com.movie.bms.splash.views.activities.SplashScreenActivity");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.findElementById("com.bt.bms:id/language_english").click();
        driver.findElementById("com.bt.bms:id/language_text_view_label").click();
        driver.findElementById("com.bt.bms:id/launcher_tv_for_skip").click();
        driver.findElementById("com.bt.bms:id/btn_positive").click();
        ((AndroidDriver)driver).findElementByAndroidUIAutomator("new UiSelector().text(\"Hyderabad\")").click();

        Dimension screenSize = driver.manage().window().getSize();
        int height = screenSize.height;
        int width = screenSize.width;

        int startX = width/2;
        int startY = (int)(height*0.80);
        int endX = startX;
        int endY = (int)(height*0.20);
        boolean flag = true;
        while(flag){
            List<MobileElement> elements = ((AndroidDriver)driver).findElementsByAndroidUIAutomator("new UiSelector()." +
                    "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\")");
            for(MobileElement element:elements){
                if(element.getAttribute("text").equalsIgnoreCase(expectedMovie)){
                    ((AndroidDriver)driver).findElementByAndroidUIAutomator("new UiSelector()." +
                            "resourceId(\"com.bt.bms:id/nowshowing_card_view_text_movie_name\").text(\"Wajah Tum Ho\")").
                            click();
                    flag=false;
                    break;
                }
            }
            driver.swipe(startX, startY, endX, endY, 3000);

        }
        System.out.println("Out of WHILE Loop!");
        //wait = new WebDriverWait(driver, 90);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.bt.bms:id/panel_movie_details_activity_crew")));
        driver.swipe(startX, endX, endX, startY, 3000);
        driver.swipe(startX, endX, endX, startY, 3000);

        actualMovie = driver.findElementById("com.bt.bms:id/movie_details_activity_text_movie_name").getAttribute("text");
        Assert.assertEquals(expectedMovie, actualMovie);
        ((AndroidDriver)driver).findElementByAndroidUIAutomator("new UiSelector().text(\"Book Tickets\")").click();

        //System.out.println(driver.findElementsByClassName("android.support.v7.app.ActionBar$Tab").get(0).getText());

        List<MobileElement> dates = driver.findElementByClassName("android.widget.LinearLayout").findElementsByClassName("android.widget.RelativeLayout");
        System.out.println("Current number of days available is: "+ dates.size());
        for(MobileElement date:dates){

            String day = date.findElementById("com.bt.bms:id/show_time_tab_day").getText();
            String dateInMonth = date.findElementById("com.bt.bms:id/show_time_tab_date").getText();
            System.out.println("Day is : "+day+" and date is: "+dateInMonth);
            break;
        }
        List<MobileElement> times = ((AndroidDriver)driver).findElementsById("com.bt.bms:id/show_movie_timing_text");
        for(MobileElement time:times){
            int i = 1;
            System.out.println("Show number "+i+" playing at "+time.getText());
            i++;
            if(time.getText().equalsIgnoreCase("06:00 PM")){
                time.click();
                break;
            }
        }
        driver.findElementById("com.bt.bms:id/yes_bt").click();
        List<MobileElement> seats = driver.findElementsById("com.bt.bms:id/txvQuantity");
        for(MobileElement seat:seats){
            if (seat.getText().equalsIgnoreCase("3")) {
                System.out.println("The number of tickets selected is "+seat.getText());
                seat.click();
                break;
            }
        }
        driver.findElementById("com.bt.bms:id/tv_pick_your_seat").click();
    }
}