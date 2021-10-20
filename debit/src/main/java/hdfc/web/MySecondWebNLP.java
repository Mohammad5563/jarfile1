package hdfc.web;

import com.testsigma.sdk.WebNLP;
import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.annotation.NLP;
import com.testsigma.sdk.annotation.TestData;
import com.testsigma.sdk.annotation.UIIdentifier;
import com.testsigma.sdk.annotation.RunTimeData;
import lombok.Data;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

@Data
@NLP(grammar = "Store the text from element-locator in values-count variable",
        description = "Stores run time data",
        applicationType = ApplicationType.WEB)
public class MySecondWebNLP extends WebNLP {

  @TestData(reference = "values-count")
  private com.testsigma.sdk.TestData testData;
  @UIIdentifier(reference = "element-locator")
  private com.testsigma.sdk.UIIdentifier uiIdentifier;
  @RunTimeData
  private com.testsigma.sdk.RunTimeData runTimeData;

  @Override
  public com.testsigma.sdk.Result execute() throws NoSuchElementException {
    // Try use of run time data
    com.testsigma.sdk.Result result = com.testsigma.sdk.Result.SUCCESS;
    logger.info("Initiating execution");
    logger.debug("ui-identifier: "+ this.uiIdentifier.getValue() +" by:"+ this.uiIdentifier.getBy() + ", test-data: "+ this.testData.getValue());
    WebElement element = uiIdentifier.getElement();
    runTimeData = new com.testsigma.sdk.RunTimeData();
    runTimeData.setValue(element.getText());
    runTimeData.setKey(testData.getValue().toString());
    setSuccessMessage("Successfully stored "+element.getText()+" into ::"+testData.getValue());
    return result;
  }
}