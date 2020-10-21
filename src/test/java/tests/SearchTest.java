package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest {

    private static final String SEARCH_KEYWORD = "nike";

    @Test(priority = 1)
    public void checkThatSearchResultsContainsSearchWord() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        for (WebElement webElement : getProductOverviewPage().getListOfProductTitles()) {
            assertTrue(webElement.getText().toLowerCase().contains(SEARCH_KEYWORD));
        }
    }
}
