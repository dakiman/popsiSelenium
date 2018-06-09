package utils;

import config.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class WaitUtils {
    public static void waitForElementVisibility(By by, int timeout) {
        waitForElement(ExpectedConditions.visibilityOfElementLocated(by), timeout);
    }

    private static void waitForElement(ExpectedCondition<?> condition, int timeout) {
        try {
            newWait(Driver.getDriver(), timeout).until(condition);
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static org.openqa.selenium.support.ui.Wait<WebDriver> newWait(WebDriver driver, int timeout) {
        return new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
    }

}
