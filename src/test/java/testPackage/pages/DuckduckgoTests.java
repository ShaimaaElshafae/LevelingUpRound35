package testPackage.pages;

import pages.Duckduckgo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ExcelReader;


public class DuckduckgoTests {
    WebDriver driver;
    Duckduckgo duckduckgoPage;
    String excelPath = "src/test/resources/TestDataExcel.xlsx";
    String sheetName = "Sheet1";

    @BeforeTest
    public void setup() {
        // Create ChromeOptions to configure browser behavior
        ChromeOptions options = new ChromeOptions();

        // Option A: Start maximized
        options.addArguments("--start-maximized");

        driver= new ChromeDriver(options);
        driver.get("https://duckduckgo.com");

        duckduckgoPage = new Duckduckgo(driver);
    }

/*
#1
________________ Basic ________________
Open Google Chrome
Navigate to [https://duckduckgo.com/]
Assert that the page title is [Google]
Close Google Chrome

 */
    @Test
    public void testDuckduckgoTitle() {

        String assertion = ExcelReader.getCellData(excelPath, sheetName, 1, 2);
        System.out.println("Page title: " + duckduckgoPage.getPageTitle());
        Assert.assertEquals(duckduckgoPage.getPageTitle(), assertion, "Page title does not match expected value.");
    }

    /*
    #2
________________ Basic ________________
Open Google Chrome
Navigate to [https://duckduckgo.com/]
Assert that the DuckDuckGo logo is displayed
Close Google Chrome

     */
    @Test
    public void testDuckduckgoLogo() {

        // Implementation for testing DuckDuckGo logo display
        System.out.println("Is DuckDuckGo logo displayed: " + duckduckgoPage.isLogoDisplayed ());
        Assert.assertTrue(duckduckgoPage.isLogoDisplayed (), "DuckDuckGo logo is not displayed.");
    }

    /*
    #3
________________ Basic ________________
Open Google Chrome
Navigate to [https://duckduckgo.com/]
Search for [Selenium WebDriver]
Assert that the link of the first result is [https://www.selenium.dev/documentation/webdriver/]
Close Google Chrome
 */
    @Test
    public void testDuckduckgoSearch() {
        String searchTerm = ExcelReader.getCellData(excelPath, sheetName, 2, 1);
        String expectedLink = ExcelReader.getCellData(excelPath, sheetName, 2, 2);
        duckduckgoPage.search(searchTerm);

        System.out.println("First result link: " + duckduckgoPage.getFirstResultLink());
        // Assert that the first result link is as expected
        Assert.assertEquals(duckduckgoPage.getFirstResultLink(), expectedLink);
    }

    /*
   5
________________ Moderate ________________
Open Google Chrome
Navigate to [https://duckduckgo.com/]
Search for [Cucumber IO]
Assert that the link of the second result contains [https://www.linkedin.com]
Close Google Chrome
*/

    @Test
    public void testDuckduckgoSearchCucumberIO() {
       String   searchTerm = ExcelReader.getCellData(excelPath, sheetName, 3, 1);
       String   expectedLink = ExcelReader.getCellData(excelPath, sheetName, 3, 2);

        duckduckgoPage.search(searchTerm);


        // Locate the second result link and get its href attribute

        System.out.println("Second result link: " + duckduckgoPage.getSecondResultLink());

        // Assert that the second result link contains the expected URL
        Assert.assertTrue(duckduckgoPage.getSecondResultLink().contains(expectedLink), "Second result link does not contain the expected URL.");
    }



    @AfterTest
    public void teardown() {
        driver.quit();
        System.out.println("Tearing down the test environment...");
    }
}
