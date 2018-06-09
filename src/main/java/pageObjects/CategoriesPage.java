package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.RandomUtils;

import java.util.List;

public class CategoriesPage extends BasePage {
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
    List<WebElement> allCategories;
    @FindBy(xpath = "//button[@id='btn-confirm']")
    WebElement confirmBtn;

    private String title_en_value, title_mk_value, description_en_value, description_mk_value;

    private void generateValues() {
        title_en_value = RandomUtils.randomString(10);
        title_mk_value = RandomUtils.randomString(10);
        description_en_value = RandomUtils.randomString(30);
        description_mk_value = RandomUtils.randomString(30);
    }

    public CategoriesPage populateAndSubmitForm() {
        this.generateValues();

        clearAndSendKeys(title_en, title_en_value);
        clearAndSendKeys(title_mk, title_mk_value);
        clearAndSendKeys(description_en, description_en_value);
        clearAndSendKeys(description_mk, description_mk_value);
        cat_img.sendKeys(resourceDir + "J9chN5s.jpg");

        title_en.submit();

        return this;
    }

    public Boolean categoryWasDeleted() {
        WebElement box = driver.findElement(By.xpath("//div[div/div/text() = '"+title_mk_value+"']"));
        waitElementToBeInvisible(new WebDriverWait(driver, 3), box);
        return !box.isDisplayed();
    }

    public CategoriesPage deleteCurrentCategory() {
        dependableClick(driver, driver.findElement(By.xpath("//div[div/text() = '"+ title_mk_value +"']/button[contains(@class, 'btn-delete')]")));
        dependableClick(driver, confirmBtn);
        return this;
    }

    public CategoriesPage pickRandomCategory() {
        WebElement randomCategory = allCategories.get(RandomUtils.randomInt(0, allCategories.size() - 1));
        dependableClick(driver, randomCategory.findElement(By.xpath("//button[contains(@class, 'btn btn-success')]")));
        return this;
    }

    public Boolean categoryExists() {
        driver.get(baseUrl + "/admin/categories");
        try {
            driver.findElement(By.xpath("//div[text() = '" + title_mk_value + "']"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean confirmSuccessMessage(String msg) {
        return successBox.getText().equals(msg);
    }

}
