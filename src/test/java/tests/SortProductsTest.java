package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SortProductsTest extends BaseTest {

    @Test(priority = 1)
    public void sortByLowestFirst() {
        getHomePage().clickNikeCategoryLink();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getSortPriceSelectButtonWebElement());
        getProductOverviewPage().setLowestFirstSort();
        double flag = 0;
        int counter = 1;
        for (WebElement webElement : getProductOverviewPage().getListOfProductsDetails()) {
            double totalPrice = getProductOverviewPage().getProductPriceWithShippingCosts(webElement, counter);
            assertTrue(totalPrice >= flag);
            flag = totalPrice;
            counter++;
        }
    }

    @Test(priority = 2)
    public void sortByHighestFirst() {
        getHomePage().clickNikeCategoryLink();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getSortPriceSelectButtonWebElement());
        getProductOverviewPage().setHighestFirstSort();
        double flag = Integer.MAX_VALUE;
        int counter = 1;
        for (WebElement webElement : getProductOverviewPage().getListOfProductsDetails()) {
            double totalPrice = getProductOverviewPage().getProductPriceWithShippingCosts(webElement, counter);
            assertTrue(totalPrice <= flag);
            flag = totalPrice;
            counter++;
        }
    }
}
