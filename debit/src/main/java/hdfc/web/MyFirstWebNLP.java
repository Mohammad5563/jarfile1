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
@NLP(grammar = "Verify no of options in select element-locator is equal to values-count",
        description = "validates options count in a select drop-down",
        applicationType = ApplicationType.WEB)
public class MyFirstWebNLP extends WebNLP {

  @TestData(reference = "values-count")
  private com.testsigma.sdk.TestData testData;
  @UIIdentifier(reference = "element-locator")
  private com.testsigma.sdk.UIIdentifier uiIdentifier;
  @RunTimeData
  private com.testsigma.sdk.RunTimeData runTimeData;

  @Override
  public com.testsigma.sdk.Result execute() throws NoSuchElementException {
    //Your Awesome code starts here
    logger.info("Initiating execution");
    logger.debug("ui-identifier: "+ this.uiIdentifier.getValue() +" by:"+ this.uiIdentifier.getBy() + ", test-data: "+ this.testData.getValue());
    WebElement element = uiIdentifier.getElement();
    Select selectElement = new Select(element);
    List<WebElement> optionsList = selectElement.getOptions();
    com.testsigma.sdk.Result result = com.testsigma.sdk.Result.SUCCESS;
    try {
      Assert.assertEquals(optionsList.size(), Integer.parseInt(testData.getValue().toString()));
    } catch (AssertionError error) {
      result = com.testsigma.sdk.Result.FAILED;
      logger.warn(" Options List count is::"+optionsList.size()+" but expected is ::"+Integer.parseInt(testData.getValue().toString()));
      setErrorMessage("Count of <option> elements in a given Element "+uiIdentifier.getBy()+"::"+uiIdentifier.getValue()+" is "+optionsList.size()+". But expected count is::"+Integer.parseInt(testData.getValue().toString()));
    }
    setSuccessMessage("Count of <option> elements in a given Element "+uiIdentifier.getBy()+"::"+uiIdentifier.getValue()+" is "+optionsList.size()+" is matching with ::"+Integer.parseInt(testData.getValue().toString()));
    return result;
  }
}