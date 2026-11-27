package testPackage.pages;

import pages.Herokuapp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ExcelReader;

public class HerokuappTests {
    WebDriver driver;
    Herokuapp herokuapp;
    String excelPath = "src/test/resources/TestDataExcel.xlsx";
    String sheetName = "Sheet1";
    @BeforeTest
    public void setup() {
        // Create ChromeOptions to configure browser behavior
        ChromeOptions options = new ChromeOptions();
        // Option A: Start maximized
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        herokuapp=new Herokuapp(driver);
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

        // Assert that both checkboxes are checked
        Assert.assertTrue(herokuapp.clickCheckbox1() && herokuapp.clickCheckbox2()
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
        String expectedCountry = ExcelReader.getCellData(excelPath, sheetName, 4, 2);
        //navigate to the W3Schools HTML Tables page
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        // Assert that the country is Austria
        Assert.assertEquals(herokuapp.getCountry(), expectedCountry, "The country for Ernst Handel is not Austria.");
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
        String expectedUploadMessage = ExcelReader.getCellData(excelPath, sheetName, 5, 2);
        //navigate to the Herokuapp Upload page
        driver.get("http://the-internet.herokuapp.com/upload");

        Assert.assertEquals
                (herokuapp.uploadFile("C:\\Users\\shaim\\OneDrive\\Desktop\\AI TOOLS.png"),
                        expectedUploadMessage, "File upload was not successful.");

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
    // Assert that the text has been changed to "Dropped!"
        Assert.assertEquals(herokuapp.performDragAndDrop(), "Dropped!", "The text did not change to 'Dropped!'");
    }
    @AfterTest
    public void teardown() {
        driver.quit();
        System.out.println("Tearing down the test environment...");
    }
}
