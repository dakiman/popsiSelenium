package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.RandomUtils;

import java.util.List;

public class NewsPage extends BasePage {
    @FindBy(name = "title_en")
    WebElement title_en;
    @FindBy(name = "title_mk")
    WebElement title_mk;
    @FindBy(name = "description_en")
    WebElement description_en;
    @FindBy(name = "description_mk")
    WebElement description_mk;
    @FindBy(name = "cat_img")
    WebElement cat_img;
    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    WebElement successBox;
    @FindBy(xpath = "//div[contains(@class, 'col-md-2 panel text-center m-x-2')]")
    List<WebElement> allNews;
    @FindBy(xpath = "//button[@id='btn-confirm']")
    WebElement confirmBtn;

    private String title_en_value, title_mk_value, description_en_value, description_mk_value;


    private void generateValues() {
        title_en_value = RandomUtils.randomString(10);
        title_mk_value = RandomUtils.randomString(10);
        description_en_value = RandomUtils.randomString(30);
        description_mk_value = RandomUtils.randomString(30);
    }

    public NewsPage populateAndSubmitForm() {
        this.generateValues();

        clearAndSendKeys(title_en, title_en_value);
        clearAndSendKeys(title_mk, title_mk_value);
        clearAndSendKeys(description_en, description_en_value);
        clearAndSendKeys(description_mk, description_mk_value);
        cat_img.sendKeys(resourceDir + "J9chN5s.jpg");

        title_en.submit();

        return this;
    }

    public NewsPage populateAndSubmitEditForm() {
        this.generateValues();

        clearAndSendKeys(title_en, title_en_value);
        clearAndSendKeys(title_mk, title_mk_value);
        clearAndSendKeys(description_en, description_en_value);
        clearAndSendKeys(description_mk, description_mk_value);

        title_en.submit();

        return this;
    }

    public Boolean newsExist() {
        navigateTo("/admin/news");
        try {
            driver.findElement(By.xpath("//div[text() = '" + title_mk_value + "']"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NewsPage deleteCurrentNews() {
        dependableClick(driver, driver.findElement(By.xpath("//div[div/text() = '" + title_mk_value + "']/button[contains(@class, 'btn-delete')]")));
        dependableClick(driver, confirmBtn);
        return this;
    }

    public Boolean newsWasDeleted() {
        WebElement box = driver.findElement(By.xpath("//div[div/div/text() = '" + title_mk_value + "']"));
        waitElementToBeInvisible(new WebDriverWait(driver, 3), box);
        return !box.isDisplayed();
    }

    public NewsPage editRandomNews() {
        WebElement randomItem = allNews.get(RandomUtils.randomInt(0, allNews.size() - 1));
        dependableClick(driver, randomItem.findElement(By.xpath("//button[contains(@class, 'btn btn-success')]")));

        return this;
    }

    public Boolean confirmSuccessMessage(String msg) {
        return successBox.getText().equals(msg);
    }


}
