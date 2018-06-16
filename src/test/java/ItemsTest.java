import config.Driver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.ItemsPage;
import utils.BaseTest;

public class ItemsTest extends BaseTest {
    ItemsPage itemsPage = new ItemsPage();

    @BeforeTest
    public void setup() {
        auth();
    }

    @Test(priority = 1)
    void itemCanBeEdited() {
        itemsPage.navigateTo("/admin/items");
        itemsPage.
                editRandomItem().
                populateAndSubmitEditForm();
        Assert.assertTrue(itemsPage.confirmSuccessMessage("Податоците се подесени."));
        Assert.assertTrue(itemsPage.itemExists());
    }

    @Test
    void itemShouldGetCreated() {
        itemsPage.navigateTo("/admin/items/create");
        itemsPage.populateAndSubmitForm();
        Assert.assertTrue(itemsPage.confirmSuccessMessage("Успешно додадено."));
        Assert.assertTrue(itemsPage.itemExists());
    }


    @Test
    public void itemCanBeDeleted() {
        itemsPage.navigateTo("/admin/items/create");
        itemsPage.populateAndSubmitForm();
        Assert.assertTrue(itemsPage.itemExists());
        itemsPage.deleteCurrentItem();
        Assert.assertTrue(itemsPage.itemWasDeleted());
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
