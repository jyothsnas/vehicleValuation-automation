package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Random;

public class HomePage extends BasePage {
    static final Logger log = LogManager.getLogger(HomePage.class);
    String url = "https://www.webuyanycar.com";
    //Elements declaration for the HomePage
    By verifyHomePageText = By.xpath("//header/div/div/div/a[@class='logo-link']/img");
    By inputCarRegId = By.xpath("//div/input[@id='vehicleReg']");
    By inputMileage = By.xpath("//div/input[@id='Mileage']");
    By getMyCarVal = By.xpath("//div/button[@id='btn-go']");

    public void visitHomePage(){
        log.info("HomePage error logs:");
        visit(url);
        acceptCookies();
    }
    public void acceptCookies(){
        By accCookies = By.xpath("//div/div[@class='banner-actions-container']/button[@id='onetrust-accept-btn-handler']");
        waitUntilElementVisibile(accCookies);
        click(accCookies);
    }
    public void enterCarDetails(String registrationNumber) {
        waitUntilElementVisibile(inputCarRegId);
        find(inputCarRegId).clear();
        enterText(inputCarRegId, registrationNumber);
        String mileage = String.valueOf(new Random().nextInt(10000));
        find(inputMileage).clear();
        enterText(inputMileage, mileage);
        click(getMyCarVal);
    }
    public boolean verifyHomepage() {
        boolean result;
        try {
            waitUntilElementVisibile(verifyHomePageText);
            String homePageText= find(verifyHomePageText).getAttribute("alt");
            result = find(verifyHomePageText).isDisplayed();
            } catch (Exception e) {
            log.error("Exception occurred", new Exception("Captcha message"));
            result = false;
        }
        return result;
    }
}