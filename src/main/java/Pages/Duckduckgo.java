package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Duckduckgo {
    private final WebDriver driver;
    private  final WebDriverWait  wait;


    public  Duckduckgo(WebDriver driver){

        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By locatorDuckduckgoLogo = By.xpath("//section[1]/a");
    private final By locatorSearchInput = By.name("q");
    private final By locatorFirstResultLink = By.xpath("(//a[@data-testid='result-extras-url-link'])[1]");
    private final By locatorSecondResultLink = By.xpath("(//a[@data-testid='result-extras-url-link'])[2]");
    private final By locatorFourthResultText = By.xpath("(//span[@class='EKtkFWMYpwzMKOYr0GYm LQVY1Jpkk8nyJ6HBWKAk'])[4]");
    // get Title of the page
    public String getPageTitle()
    {
        return driver.getTitle();
    }
    //check if the DuckDuckGo logo is displayed
    public boolean isLogoDisplayed (){

         return wait.until(driver1 -> driver1.findElement(locatorDuckduckgoLogo)).isDisplayed();

    }

     public  void search(String query) {
         wait.until(driver -> driver.findElement(locatorSearchInput)).sendKeys(query + Keys.ENTER);

     }
    // Locate the first result link and get its href attribute
    public String getFirstResultLink() {
        return wait.until(driver -> driver.findElement(locatorFirstResultLink).getAttribute("href"));
    }
    // Locate the second result link and get its href attribute
    public String getSecondResultLink() {
        return wait.until(driver -> driver.findElement(locatorSecondResultLink).getAttribute("href"));
    }
    // Locate the fourth result text and get its value
    public String getFourthResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locatorFourthResultText)).getText();
    }

}

