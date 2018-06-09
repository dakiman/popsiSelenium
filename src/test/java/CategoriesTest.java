import config.Driver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;
import pageObjects.CategoriesPage;
import utils.BaseTest;

public class CategoriesTest extends BaseTest {

    CategoriesPage catPage = new CategoriesPage();

    @BeforeTest
    public void setup() {
        auth();
    }


    @Test(priority = 1)
    public void categoryCanBeEdited() {
        catPage.navigateTo("/admin/categories");
        catPage.
                pickRandomCategory().
                populateAndSubmitForm();
        Assert.assertTrue(catPage.confirmSuccessMessage("Податоците се подесени."));
        Assert.assertTrue(catPage.categoryExists());
    }

    @Test
    public void categoryShouldGetCreated() {
        catPage.navigateTo("/admin/categories/create");
        catPage.populateAndSubmitForm();
        Assert.assertTrue(catPage.confirmSuccessMessage("Успешно додадено."));
        Assert.assertTrue(catPage.categoryExists());
    }

    @Test
    public void categoryCanBeDeleted() {
        catPage.navigateTo("/admin/categories/create");
        catPage.populateAndSubmitForm();
        Assert.assertTrue(catPage.categoryExists());
        catPage.deleteCurrentCategory();
        Assert.assertTrue(catPage.categoryWasDeleted());
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
