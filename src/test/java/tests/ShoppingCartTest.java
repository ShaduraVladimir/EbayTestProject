package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseTest {

    private static final String EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART = "1";
    private static final String EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_CHANGE_QUANTITY = "2";


    @Test(priority = 1)
    public void addToCartProduct() {
        getHomePage().clickAppleCategoryLink();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getProductOverviewPage().clickFirstProductFromProductList();
        getProductPage().clickAddToCartButton();
        getBasePage().waitForElementVisibility(30, getHomePage().getAmountOfProductsInCartWebElement());
        Assert.assertEquals(getHomePage().getAmountOfProductsInCart(), EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART);
    }

    @Test(priority = 2)
    public void changeQuantityOfProduct() {
        getHomePage().clickAppleCategoryLink();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getProductOverviewPage().clickFirstProductFromProductList();
        getBasePage().waitForElementVisibility(30, getProductPage().getAddToCartWebElement());
        getProductPage().clickAddToCartButton();
        getBasePage().waitForElementVisibility(30, getHomePage().getShoppingCartWebElement());
        getHomePage().goToShoppingCartPage();
        getBasePage().waitForElementVisibility(30, getShoppingCartPage().getQuantitySelectorWebElement());
        getShoppingCartPage().setQuantityOfProducts(EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_CHANGE_QUANTITY);
        getBasePage().refreshPage();
        getBasePage().waitForElementVisibility(30, getHomePage().getAmountOfProductsInCartWebElement());
        Assert.assertEquals
                (getHomePage().getAmountOfProductsInCart(), EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_CHANGE_QUANTITY);
    }

    @Test(priority = 3)
    public void removeProductFromShoppingCart() {
        getHomePage().clickAppleCategoryLink();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        getProductOverviewPage().clickFirstProductFromProductList();
        getBasePage().waitForElementVisibility(30, getProductPage().getAddToCartWebElement());
        getProductPage().clickAddToCartButton();
        getBasePage().waitForElementVisibility(30, getHomePage().getShoppingCartWebElement());
        getHomePage().goToShoppingCartPage();
        getBasePage().waitForElementVisibility(30, getShoppingCartPage().getRemoveButtonWebElement());
        getShoppingCartPage().clickRemoveProductFromCartButton();
        getHomePage().clickEbayLabel();
        getBasePage().waitForElementVisibility(30, getHomePage().getShoppingCartWebElement());
        Assert.assertTrue(getHomePage().isShoppingCartEmpty());
    }

    @Test(priority = 4)
    public void checkShoppingCartDisplayedCorrectPriceOfProduct() {
        getHomePage().clickAppleCategoryLink();
        getBasePage().waitForElementVisibility(30, getProductOverviewPage().getBuyItNowFilterWebElement());
        getProductOverviewPage().clickBuyItNowFilter();
        String expectedProductPrice = getProductOverviewPage().getProductPrice();
        getProductOverviewPage().clickFirstProductFromProductList();
        getBasePage().waitForElementVisibility(30, getProductPage().getAddToCartWebElement());
        getProductPage().clickAddToCartButton();
        getBasePage().waitForElementVisibility(30, getHomePage().getShoppingCartWebElement());
        getHomePage().goToShoppingCartPage();
        getBasePage().waitForElementVisibility(30, getShoppingCartPage().getPriceDetails());
        String actualProductPrice = getShoppingCartPage().getAdditionalPrice();
        String actualProductPriceInCurrentCurrency = getShoppingCartPage().getItemPrice();
        String actualTotalPrice = getShoppingCartPage().getTotalPrice();
        Assert.assertEquals(actualProductPrice.contains(expectedProductPrice),
                actualProductPriceInCurrentCurrency.contains(actualTotalPrice));
    }
}
