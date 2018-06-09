package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private static WebDriver driver;
//    String baseUrl = "http://127.0.0.1:8000";
//    String resourceDir = "G:\\Daki\\";

    private static WebDriver createNewDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return createNewDriver();
    }

    public static void closeDriver() {
        driver.quit();
    }
}
