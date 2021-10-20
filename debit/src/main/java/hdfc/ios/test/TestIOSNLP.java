package hdfc.ios.test;


import com.testsigma.sdk.TestData;
import com.testsigma.sdk.UIIdentifier;
import com.testsigma.sdk.runners.Runner;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import hdfc.ios.EnterDataIfVisible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class TestIOSNLP {
    private Runner runner;
    private IOSDriver driver;

    @BeforeClass
    public void setup() throws Exception {
        //Make sure to start Appium server
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 Max");

        capabilities.setCapability("app", "<IPA FILE PATH>");

        IOSDriver<WebElement> driver = new IOSDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.launchApp();
        runner = new Runner(driver); //Initialie NLP runner

    }

    @Test
    public void enterUserName() throws Exception {
        EnterDataIfVisible nlp = new EnterDataIfVisible();
        nlp.setTestData(new TestData("testUser"));
        UIIdentifier uiIdentifier = new UIIdentifier("", By.xpath("//XCUIElementTypeTextField[@id='userName']"));
        nlp.setUiIdentifier(uiIdentifier);
        runner.run(nlp);
    }

    @AfterClass
    public void teardown() {
        if (runner != null) {
            runner.quit();
        }
    }
}
