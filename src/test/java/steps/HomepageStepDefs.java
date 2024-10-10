package steps;

import FileUtils.InputAndOutputFileExtractor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DetailsPage;
import pages.HomePage;
import utils.BaseUtils;

import java.util.List;

public class HomepageStepDefs extends BaseUtils {
    BaseUtils baseUtils;
    HomePage homePage;
    DetailsPage detailsPage;
    public HomepageStepDefs(BaseUtils baseUtils) {
        super();
        this.baseUtils = baseUtils;
        this.homePage =new HomePage();
        this.detailsPage = new DetailsPage();
    }
    @Given("user loads the application")
    public void userLoadTheApplication(){
        homePage.visitHomePage();
        Assert.assertTrue(homePage.verifyHomepage());
    }
    @Given("the user reads the carInput text file")
    public void reads_inputFile(){
        //file is read in another step
    }
    @When("the user reads the registration number from the input file")
    public void reads_reg_mileage(){
        //file is read in another step
    }
    @Then("user enters the registration number and mileage on the home page")
    public void enter_inputs(){

    }
    @Then("user clicks on evaluate button")
    public void clicks_On_evaluate_button(){

    }
    @Then("the user evaluates the details from the application with the output text file")
    public void user_evaluates_details() {
        homePage.verifyHomepage();
        List<String> registrationNumbers = InputAndOutputFileExtractor.extractRegistrationNumbers("car_input.txt");
        for (int i= 0; i<registrationNumbers.size(); i++) {
            String registrionNo = registrationNumbers.get(i);
            homePage.enterCarDetails(registrionNo);
            if(detailsPage.verifyDetailsPage()){
                detailsPage.validateCarDetails(registrionNo.replace(" ", ""));
            }
        }

    }
}