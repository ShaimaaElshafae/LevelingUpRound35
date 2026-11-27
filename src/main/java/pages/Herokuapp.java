package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Herokuapp {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public Herokuapp    (WebDriver driver) {
        // Constructor
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // Locate the checkboxes
    private final By checkbox1 = By.xpath("//form[@id='checkboxes']/text()[normalize-space()='checkbox 1']/preceding-sibling::input");
    private final By checkbox2 = By.xpath("//form[@id='checkboxes']/text()[normalize-space()='checkbox 2']/preceding-sibling::input");

    // Locate the table and find the country for the company "Ernst Handel"
    private final By countryCell = By.xpath("//tr[td[normalize-space()='Ernst Handel']]//td[count(//tr//th[normalize-space()='Country']/preceding-sibling::th)+1]");
    //locate the file input and upload button
    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By successMessage = By.xpath("//h3[text()='File Uploaded!']");
    // Locate the draggable and droppable elements
    private final By droppable = By.id("droppable");
    private final By draggable = By.id("draggable");

    public boolean clickCheckbox1() {
        if (!driver.findElement(checkbox1).isSelected()) {
            wait.until(driver -> driver.findElement(checkbox1)).click();
        }
        return driver.findElement(checkbox1).isSelected();
    }
    public boolean clickCheckbox2() {
        if (!driver.findElement(checkbox2).isSelected()) {
            wait.until(driver -> driver.findElement(checkbox2)).click();
        }
        return driver.findElement(checkbox2).isSelected();
    }

    public String getCountry() {
        return wait.until(driver -> driver.findElement(countryCell)).getText();
    }


public String uploadFile(String filePath) {
        wait.until(driver -> driver.findElement(fileInput)).sendKeys(filePath);
        wait.until(driver -> driver.findElement(uploadButton)).click();
        return wait.until(driver -> driver.findElement(successMessage)).getText();
    }

    public String performDragAndDrop() {


        Actions actions = new Actions(driver);
        actions.dragAndDrop(wait.until(driver -> driver.findElement(draggable)), wait.until(driver -> driver.findElement(droppable))).perform();
        return wait.until(driver ->
                driver.findElement(droppable))
                .getText();
    }


}
