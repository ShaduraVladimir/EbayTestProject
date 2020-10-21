package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage extends BasePage {

    private static final String ITEM_PRICE_XPATH = "//div[@class='item-price']//span[contains(text(),' ')]";
    private static final String ADDITIONAL_PRICE_XPATH = "//div[@class='additional-prices']//span[contains(text(),' ')]";

    @FindBy(xpath = "//select[contains(@class,'listbox__control')]")
    private WebElement quantitySelector;

    @FindBy(xpath = "//button[contains(@data-test-id,'remove-item')]")
    private WebElement removeItemButton;

    @FindBy(xpath = "//div[@class='price-details']")
    private WebElement priceDetails;

    @FindBy(xpath = "//div[@data-test-id='SUBTOTAL']//span[contains(text(),' ')]")
    private WebElement totalPrice;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String getItemPrice() {
        return priceDetails.findElement(By.xpath(ITEM_PRICE_XPATH)).getText();
    }

    public String getAdditionalPrice() {
        return priceDetails.findElement(By.xpath(ADDITIONAL_PRICE_XPATH)).getText();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public WebElement getQuantitySelectorWebElement() {
        return quantitySelector;
    }

    public WebElement getRemoveButtonWebElement() {
        return removeItemButton;
    }

    public WebElement getPriceDetails() {
        return priceDetails;
    }

    public void setQuantityOfProducts(String quantity) {
        new Select(quantitySelector).selectByValue(quantity);
    }

    public void clickRemoveProductFromCartButton() {
        removeItemButton.click();
    }

}

