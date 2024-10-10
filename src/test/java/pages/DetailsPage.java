package pages;

import FileUtils.InputAndOutputFileExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.util.Map;

public class DetailsPage extends BasePage{
    static final Logger log = LogManager.getLogger(DetailsPage.class);
    //Elements declaration for the DetailsPage
    By getDetailsPageHeader=By.xpath("//div/h1[@id='questions-title']");
    By getRegId = By.xpath("(//div[@id='vehicleImage']/div)[4]");
    By getManufacturer = By.xpath("(//div[text()='Manufacturer:'])[2]/following-sibling::div");
    By getModel = By.xpath("(//div[text()='Model:'])[2]/following-sibling::div");
    By getYear = By.xpath("(//div[text()='Year:'])[2]/following-sibling::div");
    By getColour = By.xpath("(//div[text()='Colour:'])[2]/following-sibling::div");
    By backButton = By.id("btn-back");
    public boolean verifyDetailsPage() {
        try {
            waitUntilElementVisibile(getDetailsPageHeader);
            String detailsPageHeader = find(getDetailsPageHeader).getText();
            Assert.assertEquals("DetailsPage Header not as expected","Your details", detailsPageHeader);
            return  true;
        } catch (Exception e) {
            log.error("Exception occurred", new Exception("Car details not found"));
            By errorMsg = By.xpath("//div[@class='row']/div/h1");
            Assert.assertTrue("Error message not displayed", find(errorMsg).getText().equalsIgnoreCase("Sorry, we couldn't find your car"));
            driver.navigate().back();
            return false;
        }
    }
    public void validateCarDetails(String registrationNo) {
        Map<String, String> outPutData = InputAndOutputFileExtractor.readCarOutputFileAndPerformEvaluation("car_output.txt",registrationNo);
        String regID= find(getRegId).getText();
        /*If there is a mismatch between the application evaluation values against the output file
         then the error message will be shown in the stacktrace and test wil fail
         Used Junit assertion*/
        Assert.assertEquals("Registration ID is not matching",outPutData.get("VARIANT_REG") , regID);

        String manufacturer = find(getManufacturer).getText();
        Assert.assertEquals("Manufacturer value mismatch", outPutData.get("MAKE"), manufacturer);

        String model = find(getModel).getText();
        Assert.assertEquals("Model mismatch", outPutData.get("MODEL"), model);

        String year = find(getYear).getText();
        Assert.assertEquals("Year mismatch", outPutData.get("YEAR"), year);
        clickByjs(backButton);
    }

    //another way of finding the elements using @FindBy
    //@FindBy(xpath = "//div/h1[@id='questions-title']")
    //private WebElement getDetailsPageHeader;

}
