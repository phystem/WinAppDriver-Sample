/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import io.appium.java_client.windows.WindowsDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author 394173
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotePadTest {

    static WindowsDriver driver;

    @BeforeClass
    public static void initDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
        capabilities.setCapability("deviceName", "Windows10");
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void quitDriver() {
        driver.closeApp();
        driver.findElementByName("Don't Save").click();
        driver.quit();
    }

    @Test
    public void test1() {
        WebElement element = driver.findElementByClassName("Edit");
        element.sendKeys("This is some text");
        element.sendKeys(Keys.ENTER);
        element.sendKeys("Time is");
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.F5);
    }

    @Test
    public void test2() throws IOException {
        driver.findElementByName("Edit").click();
        driver.findElementByName("Select All").click();
        File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(ss, new File("Np.png"));
    }
}
