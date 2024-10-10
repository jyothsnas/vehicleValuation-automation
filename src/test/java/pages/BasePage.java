package pages;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage extends BaseUtils{
    public void waitUntilElementVisibile(By by){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void visit(String URL){
        driver.get(URL);
    }
    public WebElement find(By by){
        return driver.findElement(by);
    }
    public void enterText(By locator, String value) {
        try {
            WebElement input = driver.findElement(locator);
            input.clear();
            input.sendKeys(value);
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement input = driver.findElement(locator);
            input.clear();
            input.sendKeys(value);
        }
    }
    public void scrollBottomOfPage(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView[false];", element);
    }
    public void click(By by){
        WebElement element = driver.findElement(by);
        element.click();
    }
   public void clickByjs(By by){
       WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
       wait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement ele = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor)  driver;
        executor.executeScript("arguments[0].click();", ele);
   }
   public void scrollIntoView(WebElement element){
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",element);
       WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
       wait.until(ExpectedConditions.visibilityOf(element));
   }
   //Handling Captcha
    public void validateCaptcha() throws IOException, TesseractException {
        File src = driver.findElement(By.xpath("//div/img[1]")).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/captcha.png";
        FileHandler.copy(src, new File(path));
        ITesseract image = new Tesseract();
        String imageText = image.doOCR(new File(path));
    }
    public List<WebElement>
    findAll(By by){
        return  driver.findElements(by);
    }

    public void selectBy(By by, String visibleText) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(visibleText);
    }

    public void selectByIndex(By by, String optionIndex) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(Integer.parseInt(optionIndex));
    }
}