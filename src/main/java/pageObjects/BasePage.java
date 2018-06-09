package pageObjects;

import config.Driver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

class BasePage {
    public WebDriver driver = Driver.getDriver();
    String baseUrl = "http://127.0.0.1:8000";
    String resourceDir = "G:\\Daki\\";

    public BasePage() {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);
    }

    public static void waitElementToBeInvisible(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitElementToBeVisible(WebDriverWait wait, WebDriver driver, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitElementsToBeVisible(WebDriverWait wait, WebDriver driver, List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void dependableClick(WebDriver d, WebElement element) {
        final int MAXIMUM_WAIT_TIME = 10;
        final int MAX_STALE_ELEMENT_RETRIES = 5;

        WebDriverWait wait = new WebDriverWait(d, MAXIMUM_WAIT_TIME);
        int retries = 0;
        while (true) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                return;
            } catch (StaleElementReferenceException e) {
                if (retries < MAX_STALE_ELEMENT_RETRIES) {
                    retries++;
                    continue;
                } else {
                    throw e;
                }
            }
        }
    }

    public static void dependableWrite(WebDriver d, WebElement element, String write) {
        final int MAXIMUM_WAIT_TIME = 10;
        final int MAX_STALE_ELEMENT_RETRIES = 5;

        WebDriverWait wait = new WebDriverWait(d, MAXIMUM_WAIT_TIME);
        int retries = 0;
        while (true) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
                element.sendKeys(write);
                return;
            } catch (StaleElementReferenceException e) {
                if (retries < MAX_STALE_ELEMENT_RETRIES) {
                    retries++;
                    continue;
                } else {
                    throw e;
                }
            }
        }
    }

    public static void dependableWait(WebDriver d, WebElement element) {
        final int MAXIMUM_WAIT_TIME = 10;
        final int MAX_STALE_ELEMENT_RETRIES = 5;

        WebDriverWait wait = new WebDriverWait(d, MAXIMUM_WAIT_TIME);
        int retries = 0;
        while (true) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return;
            } catch (StaleElementReferenceException e) {
                if (retries < MAX_STALE_ELEMENT_RETRIES) {
                    retries++;
                    continue;
                } else {
                    throw e;
                }
            }
        }
    }

    public void navigateTo(String URL) {
        driver.get(baseUrl + URL);
    }

    public void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

}
