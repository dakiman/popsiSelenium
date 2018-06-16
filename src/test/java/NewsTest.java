import config.Driver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.NewsPage;
import utils.BaseTest;

public class NewsTest extends BaseTest {

    NewsPage newsPage = new NewsPage();

    @BeforeTest
    public void setup() {
        auth();
    }

    @Test
    void newsCanBeCreated() {
        newsPage.navigateTo("/admin/news/create");
        newsPage.populateAndSubmitForm();
        Assert.assertTrue(newsPage.confirmSuccessMessage("Успешно додадено."));
        Assert.assertTrue(newsPage.newsExist());
    }

    @Test
    void newsCanBeEdited() {
        newsPage.navigateTo("/admin/news");
        newsPage.
                editRandomNews().
                populateAndSubmitEditForm();
        Assert.assertTrue(newsPage.confirmSuccessMessage("Податоците се подесени."));
        Assert.assertTrue(newsPage.newsExist());
    }

    @Test
    void newsCanBeDeleted() {
        newsPage.navigateTo("/admin/news/create");
        newsPage.populateAndSubmitForm();
        Assert.assertTrue(newsPage.newsExist());
        newsPage.deleteCurrentNews();
        Assert.assertTrue(newsPage.newsWasDeleted());
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
