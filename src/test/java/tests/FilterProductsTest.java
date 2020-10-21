package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FilterProductsTest extends BaseTest {

    private static final String ALL_FILTERS_BUTTON_XPATH = "//button[text()='All Filters']";
    private static final String FREE_SHIPPING_FILTER = "Free";
    private static final String MAX_PRICE = "100";
    private static final String MIN_PRICE = "50";
    private static final int MAX_PRICE_INTEGER = 100;
    private static final int MIN_PRICE_INTEGER = 50;

    @Test(priority = 1)
    public void checkFilterByMaxPrice() {
        getHomePage().clickAppleCategoryLink();
        getBasePage().waitForPageLoading(30);
        if (getHomePage().existsElement(ALL_FILTERS_BUTTON_XPATH)) {
            getProductOverviewPage().clickAllFiltersButton();
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getFilterByPriceWebElement());
            getProductOverviewPage().clickFilterByPriceButton();
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getMaxPriceInputWebElement());
            getProductOverviewPage().setMaxPriceFilter(MAX_PRICE);
            getProductOverviewPage().clickApplyButton();
            getBasePage().waitForPageLoading(30);
        } else {
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getMaxPriceInputWebElement());
            getProductOverviewPage().setMaxPriceFilter(MAX_PRICE);
        }
        getBasePage().waitForPageLoading(30);
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getFilterAppliedWebElement());
        int counter = 1;
        for (WebElement webElement : getProductOverviewPage().getListOfProductsPrices()) {
            assertTrue(getProductOverviewPage().getProductPriceWithShippingCosts(webElement, counter) < MAX_PRICE_INTEGER);
            counter++;
        }
    }

    @Test(priority = 2)
    public void checkFilterByMinPrice() {
        getHomePage().clickAppleCategoryLink();
        getBasePage().waitForPageLoading(30);
        if (getHomePage().existsElement(ALL_FILTERS_BUTTON_XPATH)) {
            getProductOverviewPage().clickAllFiltersButton();
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getFilterByPriceWebElement());
            getProductOverviewPage().clickFilterByPriceButton();
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getMinPriceInputWebElement());
            getProductOverviewPage().setMaxPriceFilter(MIN_PRICE);
            getProductOverviewPage().clickApplyButton();
        } else {
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getMinPriceInputWebElement());
            getProductOverviewPage().setMinPriceFilter(MIN_PRICE);
        }
        getBasePage().waitForPageLoading(30);
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getFilterAppliedWebElement());
        int counter = 1;
        for (WebElement webElement : getProductOverviewPage().getListOfProductsPrices()) {
            assertTrue(getProductOverviewPage().getProductPriceWithShippingCosts(webElement, counter) > MIN_PRICE_INTEGER);
            counter++;
        }
    }

    @Test(priority = 3)
    public void checkFilterByFreeShipping() {
        getHomePage().clickAppleCategoryLink();
        getBasePage().waitForPageLoading(30);
        if (getHomePage().existsElement(ALL_FILTERS_BUTTON_XPATH)) {
            getProductOverviewPage().clickAllFiltersButton();
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getFilterByDeliveryWebElement());
            getProductOverviewPage().clickFilterByDeliveryButton();
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getAlternativeFilterByFreeShippingWebElement());
            getProductOverviewPage().setAlternativeFilterByFreeShipping();
            getProductOverviewPage().clickApplyButton();
        } else {
            getBasePage().waitForElementVisibility(30, getProductOverviewPage().getFreeShippingCheckBoxFilterWebElement());
            getProductOverviewPage().setFilterByFreeShipping();
        }
        getBasePage().waitForPageLoading(30);
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getFilterAppliedWebElement());
        for (WebElement webElement : getProductOverviewPage().getListOfLogisticsCostsOfProducts()) {
            assertTrue(webElement.getText().contains(FREE_SHIPPING_FILTER));
        }
    }
}
