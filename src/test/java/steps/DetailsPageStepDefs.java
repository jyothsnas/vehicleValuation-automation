package steps;

import pages.HomePage;
import utils.BaseUtils;

public class DetailsPageStepDefs extends BaseUtils {
    BaseUtils baseUtils;
    HomePage homePage;

    public DetailsPageStepDefs(BaseUtils baseUtils) {
        super();
        this.baseUtils = baseUtils;
        this.homePage = new HomePage();
    }
}