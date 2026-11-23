package testPackage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class DuckduckgoTests {
    WebDriver driver;
    @BeforeTest
    public void setup() {
        // Create ChromeOptions to configure browser behavior
        ChromeOptions options = new ChromeOptions();

        // Option A: Start maximized
        options.addArguments("--start-maximized");

        driver= new ChromeDriver(options);
        driver.get("https://duckduckgo.com");

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
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        Assert.assertEquals(title, "Google", "Page title does not match expected value.");
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

        By locatorDuckduckgoLogo = By.xpath("//img[@alt='DuckDuckGo']");
        boolean isLogoDisplayed = driver.findElement(locatorDuckduckgoLogo).isDisplayed();
        System.out.println("Is DuckDuckGo logo displayed: " + isLogoDisplayed);
        Assert.assertTrue(isLogoDisplayed, "DuckDuckGo logo is not displayed.");
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

        // Locate the search input field and enter the search query
        driver.findElement(By.name("q")).sendKeys("Selenium WebDriver"+ Keys.ENTER);


        // Locate the first result link and get its href attribute
        By locatorFirstResultLink = By.xpath("(//a[@data-testid='result-extras-url-link'])[1]");
        String firstResultLink = driver.findElement(locatorFirstResultLink).getAttribute("href");
        System.out.println("First result link: " + firstResultLink);

        // Assert that the first result link is as expected
        Assert.assertEquals(firstResultLink, "https://www.selenium.dev/documentation/webdriver/");
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

        // Locate the search input field and enter the search query
        driver.findElement(By.name("q")).sendKeys("Cucumber IO"+ Keys.ENTER);


        // Locate the second result link and get its href attribute
        By locatorSecondResultLink = By.xpath("(//a[@data-testid='result-extras-url-link'])[2]");
        String secondResultLink = driver.findElement(locatorSecondResultLink).getAttribute("href");
        System.out.println("Second result link: " + secondResultLink);

        // Assert that the second result link contains the expected URL
        Assert.assertEquals(secondResultLink, "https://www.linkedin.com");
    }



    @AfterTest
    public void teardown() {
        driver.quit();
        System.out.println("Tearing down the test environment...");
    }
}
