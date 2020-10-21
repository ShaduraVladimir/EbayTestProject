package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='https://www.ebay.com/']")
    private WebElement ebayLabel;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement searchInput;

    @FindBy(xpath = "//h3[text()='Nike']")
    private WebElement nikeCategoryLink;

    @FindBy(xpath = "//h3[text()='Apple']")
    private WebElement appleCategoryLink;

    @FindBy(xpath = "//a[contains(@title,'shopping cart')]")
    private WebElement shoppingCart;

    @FindBy(xpath = "//i[contains(@id,'cart')]")
    private WebElement amountOfProductsInCart;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchByKeyword(String keyword) {
        searchInput.sendKeys(keyword, Keys.ENTER);
    }

    public void clickEbayLabel() {
        ebayLabel.click();
    }

    public void clickNikeCategoryLink() {
        nikeCategoryLink.click();
    }

    public void clickAppleCategoryLink() {
        appleCategoryLink.click();
    }

    public void goToShoppingCartPage() {
        shoppingCart.click();
    }

    public WebElement getShoppingCartWebElement() {
        return shoppingCart;
    }

    public WebElement getAmountOfProductsInCartWebElement() {
        return amountOfProductsInCart;
    }

    public String getAmountOfProductsInCart() {
        return amountOfProductsInCart.getText();
    }

    public boolean isShoppingCartEmpty() {
        if (shoppingCart.getAttribute("title").equals("Your shopping cart"))
            return true;
        else
            return false;
    }

    public boolean existsElement(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }


}
