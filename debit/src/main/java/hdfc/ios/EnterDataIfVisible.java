package hdfc.ios;


import com.testsigma.sdk.IOSNLP;
import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.annotation.NLP;
import com.testsigma.sdk.annotation.TestData;
import com.testsigma.sdk.annotation.UIIdentifier;
import io.appium.java_client.ios.IOSDriver;
import lombok.Data;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Data
@NLP(grammar = "Enter test-data in element ui-identifier if visible", applicationType = ApplicationType.IOS)
public class EnterDataIfVisible extends IOSNLP {

  @TestData(reference = "test-data")
  private com.testsigma.sdk.TestData testData;
  @UIIdentifier(reference = "ui-identifier")
  private com.testsigma.sdk.UIIdentifier uiIdentifier;

  @Override
  protected com.testsigma.sdk.Result execute() throws NoSuchElementException {
    //Your Awesome code starts here
    com.testsigma.sdk.Result result = com.testsigma.sdk.Result.SUCCESS;
    logger.info("Initiating execution");
    logger.debug("ui-identifier: "+ this.uiIdentifier.getValue() +" by:"+ this.uiIdentifier.getBy() + ", test-data: "+ this.testData.getValue());
    IOSDriver iosDriver = (IOSDriver)this.driver;
    WebElement element = uiIdentifier.getElement();//Alternate way iosDriver.findElement(uiIdentifier.getBy())
    if(element.isDisplayed()){
      logger.info("Element is displayed, entering data");
      element.sendKeys(testData.getValue().toString());
      setSuccessMessage(String.format("Successfully entered %s on element with %s::%s", testData.getValue().toString(), uiIdentifier.getBy(), uiIdentifier.getValue()));
    }else{
      result = com.testsigma.sdk.Result.FAILED;
      logger.warn("Element with locator %s is not visible ::"+uiIdentifier.getBy()+"::"+uiIdentifier.getValue());
      setErrorMessage(String.format("Element with locator %s is not visible",uiIdentifier.getBy()));
    }
    return result;
  }
}