package testPackage.Pages;

import Pages.Duckduckgo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;



public class DuckduckgoByFirfoxTests {
    WebDriver driver;
    Duckduckgo duckduckgoPage;
    //variable for explicit wait
    WebDriverWait wait;

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

    // Locate the search input field and enter the search query
    duckduckgoPage.search("TestNG");


   //locate the fourth result link and get its text

    System.out.println("Fourth result text: " + duckduckgoPage.getFourthResultText());


    // Assert that the first result link is as expected
    Assert.assertEquals(duckduckgoPage.getFourthResultText(), "TestNG Tutorial");
}




    @AfterTest
    public void teardown() {
        driver.quit();
        System.out.println("Tearing down the test environment...");
    }
}
