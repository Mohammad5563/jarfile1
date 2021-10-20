package hdfc.web.test;

import com.testsigma.sdk.TestData;
import com.testsigma.sdk.UIIdentifier;
import com.testsigma.sdk.runners.Runner;
import hdfc.web.MyFirstWebNLP;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestWebNLP {
    private Runner runner;
    private ChromeDriver driver;
    @BeforeClass
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "<Chrome Driver Path>");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        runner = new Runner(driver); //Initialie NLP runner
        driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/");
    }
    @Test
    public void validateCountriesCount() throws Exception {
        MyFirstWebNLP nlp = new MyFirstWebNLP();
        nlp.setTestData(new TestData("232"));
        UIIdentifier uiIdentifier = new UIIdentifier("",By.name("Country"));
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
