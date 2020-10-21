package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductOverviewPage extends BasePage {

    private static final String ITEM_PRICE_XPATH = "//li[]//span[@class='s-item__price']";
    private static final String LOGISTICS_COSTS_XPATH = "//li[]//span[contains(@class,'logisticsCost')]";

    @FindBy(xpath = "//li[1]//h3[contains(@class,'item__title')]")
    private WebElement firstProductFromProductList;

    @FindBy(xpath = "//li[1]//span[@class='s-item__price']")
    private WebElement priceOfFirstProduct;

    @FindBy(xpath = "//button[text()='All Filters']")
    private WebElement allFiltersButton;

    @FindBy(xpath = "//div[contains(@id,'price')]")
    private WebElement filterByPriceButton;

    @FindBy(xpath = "//div[contains(@id,'delivery')]")
    private WebElement filterByDeliveryButton;

    @FindBy(xpath = "//input[contains(@id,'Free')]")
    private WebElement alternativeFilterByFreeShipping;

    @FindBy(xpath = "//button[@aria-label='Apply']")
    private WebElement applyFilterButton;

    @FindBy(xpath = "//input[contains(@aria-label,'Maximum')]")
    private WebElement inputByMaxPrice;

    @FindBy(xpath = "//input[contains(@aria-label,'Minimum')]")
    private WebElement inputByMinPrice;

    @FindBy(xpath = "//input[@aria-label='Free International Shipping']")
    private WebElement freeShippingCheckBoxFilter;

    @FindBy(xpath = "//h2[text()='Buy It Now']")
    private WebElement buyItNowFilter;

    @FindBy(xpath = "//li[contains(@class,'item--applied')]")
    private WebElement filterApplied;

    @FindBy(xpath = "//button[contains(@aria-labelledby,'selected')]")
    private WebElement sortPriceSelectButton;

    @FindBy(xpath = "//span[contains(text(),'lowest')]")
    private WebElement sortPriceLowestFirst;

    @FindBy(xpath = "//span[contains(text(),'highest')]")
    private WebElement sortPriceHighestFirst;

    @FindBy(xpath = "//h3[contains(@class,'item__title')]")
    private List<WebElement> listOfProductTitles;

    @FindBy(xpath = "//li//div[contains(@class,'item__details')]")
    private List<WebElement> listOfProductDetails;

    @FindBy(xpath = "//span[@class='s-item__price']")
    private List<WebElement> listOfProductPrices;

    @FindBy(xpath = "//span[contains(@class,'logisticsCost')]")
    private List<WebElement> listOfLogisticsCostOfProducts;

    public ProductOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickFirstProductFromProductList() {
        firstProductFromProductList.click();
    }

    public void clickBuyItNowFilter() {
        buyItNowFilter.click();
    }

    public void clickAllFiltersButton() {
        allFiltersButton.click();
    }

    public void clickFilterByPriceButton() {
        filterByPriceButton.click();
    }

    public void clickFilterByDeliveryButton() {
        filterByDeliveryButton.click();
    }

    public void clickApplyButton() {
        applyFilterButton.click();
    }

    public void setMaxPriceFilter(String maxPrice) {
        inputByMaxPrice.sendKeys(maxPrice, Keys.ENTER);
    }

    public void setMinPriceFilter(String minPrice) {
        inputByMinPrice.sendKeys(minPrice, Keys.ENTER);
    }

    public void setFilterByFreeShipping() {
        freeShippingCheckBoxFilter.click();
    }

    public void setAlternativeFilterByFreeShipping() {
        alternativeFilterByFreeShipping.click();
    }

    public void setLowestFirstSort() {
        new Actions(driver).release(sortPriceSelectButton).perform();
        waitForElementVisibility(30, sortPriceLowestFirst);
        sortPriceLowestFirst.click();
    }

    public void setHighestFirstSort() {
        new Actions(driver).release(sortPriceSelectButton).perform();
        waitForElementVisibility(30, sortPriceHighestFirst);
        sortPriceHighestFirst.click();
    }

    public WebElement getFreeShippingCheckBoxFilterWebElement() {
        return freeShippingCheckBoxFilter;
    }

    public WebElement getSortPriceSelectButtonWebElement() {
        return sortPriceSelectButton;
    }

    public WebElement getMaxPriceInputWebElement() {
        return inputByMaxPrice;
    }

    public WebElement getMinPriceInputWebElement() {
        return inputByMinPrice;
    }

    public WebElement getBuyItNowFilterWebElement() {
        return buyItNowFilter;
    }

    public WebElement getFilterByPriceWebElement() {
        return filterByPriceButton;
    }

    public WebElement getFilterByDeliveryWebElement() {
        return filterByDeliveryButton;
    }

    public WebElement getAlternativeFilterByFreeShippingWebElement() {
        return alternativeFilterByFreeShipping;
    }

    public WebElement getFilterAppliedWebElement() {
        return filterApplied;
    }

    public List<WebElement> getListOfProductTitles() {
        return listOfProductTitles;
    }

    public List<WebElement> getListOfProductsDetails() {
        return listOfProductDetails;
    }

    public List<WebElement> getListOfProductsPrices() {
        return listOfProductPrices;
    }

    public List<WebElement> getListOfLogisticsCostsOfProducts() {
        return listOfLogisticsCostOfProducts;
    }

    public double getProductPriceWithShippingCosts(WebElement webElement, int number) {
        String price = webElement.findElement(By.xpath(String.valueOf(new StringBuffer(ITEM_PRICE_XPATH).insert(5, number)))).getText().replaceAll("[,]", "");
        String logisticsCost = webElement.findElement(By.xpath(String.valueOf(new StringBuffer(LOGISTICS_COSTS_XPATH).insert(5, number)))).getText().replaceAll("[,]", "");

        double priceDouble = Double.parseDouble(price.substring(price.indexOf("$") + 1, price.indexOf(".") + 3));

        if (logisticsCost.contains("Free") || logisticsCost.contains("not"))
            return priceDouble;
        else
            return priceDouble + Double.parseDouble
                    (logisticsCost.substring(logisticsCost.indexOf("$") + 1, logisticsCost.indexOf(".") + 3));
    }

    public String getProductPrice() {
        return priceOfFirstProduct.getText();
    }

}
