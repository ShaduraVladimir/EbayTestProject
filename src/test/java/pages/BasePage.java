package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementVisibility(long timeOut, WebElement webElement) {
        try {
            new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(webElement));
        } catch (StaleElementReferenceException exception) {
            new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(webElement));
        }
    }

    public void waitForPageLoading(long timeOut) {
        driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}
