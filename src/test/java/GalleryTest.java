import config.Driver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.GalleryPage;
import utils.BaseTest;

public class GalleryTest extends BaseTest {
    GalleryPage galleryPage = new GalleryPage();

    @BeforeTest
    public void setup() { auth(); }

    @Test
    void imageCanBeAdded() {
        galleryPage.navigateTo("/admin/gallery/create");
        galleryPage.addImage();
        Assert.assertTrue(galleryPage.confirmSuccessMessage("Сликата е успешно додадена!"));
    }

    @Test
    void orderShouldGetChanged() {
        galleryPage.navigateTo("/admin/gallery");
        galleryPage.
                moveRandomImageToBack().
                moveRandomImageToFront().
                saveOrder();
        Assert.assertTrue(galleryPage.confirmSuccessMessage("Успешно променет распоред"));
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        writeResult(result);
    }

    @AfterClass
    public void close() {
        Driver.closeDriver();
    }
}
