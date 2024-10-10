package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BaseUtils;

public class Hooks extends BaseUtils {
    BaseUtils baseUtils;
    @Before
    public void setup(io.cucumber.java.Scenario scenario){
        System.out.println("Hooks setUp method");
        WebDriverManager.chromedriver().setup();
        //another way of initialising Chrome browser
//        System.setProperty("webdriver.chrome.driver",  "src/test/drivers/chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Hooks tearDown");
            final byte[] screenshot;
            try {
                screenshot = ((TakesScreenshot) baseUtils.driver).getScreenshotAs(OutputType.BYTES);
            } catch (WebDriverException e) {
                throw new RuntimeException(e);
            }
            scenario.attach(screenshot, "image/png", "error screenshot message");
        }
            driver.quit();
    }
}