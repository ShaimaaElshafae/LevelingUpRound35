package testPackage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HerokuappTests {
    WebDriver driver;
    @BeforeTest
    public void setup() {
        // Create ChromeOptions to configure browser behavior
        ChromeOptions options = new ChromeOptions();

        // Option A: Start maximized
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);


    }
/*#6
________________ Basic ________________
Open Google Chrome
Navigate to [http://the-internet.herokuapp.com/checkboxes]
Check Checkbox 1
Assert that both Checkboxes are checked
Close Google Chrome
*/

    @Test
    public void testHerokuappCheckboxes() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        // Locate the checkboxes and check them
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/text()[normalize-space()='checkbox 1']/preceding-sibling::input"));
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/text()[normalize-space()='checkbox 2']/preceding-sibling::input"));




        // Check Checkbox 1 if it's not already checked
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }


        // Assert that both checkboxes are checked
        Assert.assertTrue(checkbox1.isSelected() && checkbox2.isSelected()
                , "Checkbox 1 or Checkbox 2 or both are not checked.");

    }

    /* #7
________________ Moderate ________________
Open Google Chrome
Navigate to [https://www.w3schools.com/html/html_tables.asp]
Assert that the Country for the Company [Ernst Handel] is [Austria]
Close Google Chrome
*/
    @Test
    public void testW3SchoolsHtmlTable() {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        // Locate the table and find the country for the company "Ernst Handel"
        WebElement countryCell = driver.findElement(By.xpath("//tr[td[normalize-space()='Ernst Handel']]//td[count(//tr//th[normalize-space()='Country']/preceding-sibling::th)+1]"));
        String country = countryCell.getText();

        // Assert that the country is Austria
        Assert.assertEquals(country, "Austria", "The country for Ernst Handel is not Austria.");
    }

    /*#8
________________ Moderate ________________
Open Google Chrome
Navigate to [http://the-internet.herokuapp.com/upload]
Upload a small image file
Assert that the file was uploaded successfully
Close Google Chrome
*/
    @Test
    public void testHerokuappUpload() {
        driver.get("http://the-internet.herokuapp.com/upload");
        // Locate the file input and upload a small image file
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        fileInput.sendKeys("C:\\Users\\shaim\\OneDrive\\Desktop\\AI TOOLS.png");

        // Locate the upload button and click it
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        // Assert that the file was uploaded successfully
        WebElement successMessage = driver.findElement(By.xpath("//h3[text()='File Uploaded!']"));
        String successMessageText = successMessage.getText();
        Assert.assertEquals(successMessageText, "File Uploaded!", "File upload was not successful.");

    }

    /*#9
________________ Moderate ________________
Open Google Chrome
Navigate to [https://jqueryui.com/resources/demos/droppable/default.html]
Drag [Drag me to my target] and drop it on [Drop here]
Assert that the text has been changed to [Dropped!]
Close Google Chrome
*/
@Test
public void testJQueryUIDragAndDrop() {

        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");
        // Locate the draggable and droppable elements
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, droppable).perform();

        // Assert that the text has been changed to "Dropped!"
        String droppedText = droppable.getText();
        Assert.assertEquals(droppedText, "Dropped!", "The text did not change to 'Dropped!'");
    }
    @AfterTest
    public void teardown() {
        driver.quit();
        System.out.println("Tearing down the test environment...");
    }
}
