package utils;

import config.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

public class BaseTest {

    public static String baseUrl = "http://127.0.0.1:8000";

    public static void auth() {
        WebDriver driver = Driver.getDriver();
        driver.get(baseUrl + "/login");
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        email.sendKeys("daki@daki");
        password.sendKeys("daki");
        email.submit();
    }

    protected void writeResult(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {

                System.out.println("Log Message:: @AfterMethod: Method-" + result.getMethod().getMethodName() + "- has Passed");

            } else if (result.getStatus() == ITestResult.FAILURE) {

                System.out.println("Log Message:: @AfterMethod: Method-" + result.getMethod().getMethodName() + "- has Failed");


            } else if (result.getStatus() == ITestResult.SKIP) {
                System.out.println("Log Message::@AfterMethod: Method-" + result.getMethod().getMethodName() + "- has Skipped");

            }
        } catch (Exception e) {
            System.out.println("\nLog Message::@AfterMethod: Exception caught");
            e.printStackTrace();
        }
    }

}
