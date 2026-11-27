package testPackage.pages;

import pages.Duckduckgo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ExcelReader;

import java.time.Duration;



public class DuckduckgoByFirfoxTests {
    WebDriver driver;
    Duckduckgo duckduckgoPage;
    //variable for explicit wait
    WebDriverWait wait;
    String excelPath = "src/test/resources/TestDataExcel.xlsx";
    String sheetName = "Sheet1";
    @BeforeTest
    public void setup() {


// 1. Initialize Firefox Driver directly without options
        driver = new FirefoxDriver();
// 2. Initialize WebDriverWait (sets a maximum timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// 3. Maximize the window
        driver.manage().window().maximize();

// 4. Navigate to DuckDuckGo
        driver.get("https://duckduckgo.com");
        duckduckgoPage = new Duckduckgo(driver);

    }

/*
#4
________________ Moderate ________________
Open Mozilla Firefox
Navigate to [https://duckduckgo.com/]
Search for [TestNG]
Assert that the text of the fourth result is [TestNG Tutorial]
Close Mozilla Firefox

 */
@Test
public void testDuckduckgoSearchTestNG() {
  String searchTerm = ExcelReader.getCellData(excelPath, sheetName, 2, 1);
  String expectedText = ExcelReader.getCellData(excelPath, sheetName, 2, 2);
    // Locate the search input field and enter the search query
    duckduckgoPage.search(searchTerm);

    // Wait for the fourth result to load, then compare its text

    System.out.println("Fourth result text: " + duckduckgoPage.getFourthResultText());
    Assert.assertEquals(duckduckgoPage.getFourthResultText(), expectedText);
}




    @AfterTest
    public void teardown() {
        driver.quit();

    }
}
