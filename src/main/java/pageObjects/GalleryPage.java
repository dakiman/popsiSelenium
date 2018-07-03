package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.RandomUtils;

import java.util.List;

public class GalleryPage extends BasePage {
    @FindBy(name = "car_img")
    WebElement imageInput;
    @FindBy(xpath = "//button[contains(@class, 'submit-btn')]")
    WebElement submitBtn;
    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    WebElement successBox;
    @FindBy(xpath = "//div[contains(@class, 'active-pictures')]/div[contains(@class, 'col-md-3')]")
    List<WebElement> activePictures;
    @FindBy(xpath = "//button[@id='btn-confirm']")
    WebElement confirmBtn;
    @FindBy(xpath = "//button[contains(@class, 'submit-order-btn')]")
    WebElement saveOrderButton;

    public GalleryPage addImage() {
        clearAndSendKeys(imageInput, resourceDir + "J9chN5s.jpg");
        dependableClick(driver, submitBtn);
        return this;
    }

    public GalleryPage moveRandomImageToFront() {
        WebElement randomImage = activePictures.get(0);
        WebElement prependBtn = randomImage.findElement(By.xpath("//button[contains(@class, 'prepend-btn')]"));
        prependBtn.click();
//        dependableClick(driver, prependBtn);
        return this;
    }

    public GalleryPage moveRandomImageToBack() {
        WebElement randomImage = activePictures.get(activePictures.size() - 2);
        WebElement appendBtn = randomImage.findElement(By.xpath("//button[contains(@class, 'append-btn')]"));
        appendBtn.click();
//        dependableClick(driver, appendBtn);
        return this;
    }

    public GalleryPage saveOrder() {
        dependableClick(driver, saveOrderButton);
        return this;
    }

    public Boolean confirmSuccessMessage(String msg) {
        return successBox.getText().equals(msg);
    }

}
