import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalenderTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://jqueryui.com/datepicker/");
    }

    // Test 1 - Select date using sendKeys()
    @Test(priority = 1)
    public void selectDateUsingSendKeys() {

        // Enter the iframe
        driver.switchTo().frame(
                driver.findElement(By.className("demo-frame"))
        );

        // Find date input
        WebElement dateBox =
                driver.findElement(By.id("datepicker"));

        // Enter date
        dateBox.sendKeys("10/20/2026");

        // Get selected date
        String selectedDate =
                dateBox.getAttribute("value");

        System.out.println(
                "Date entered: " + selectedDate
        );

        // Verify
        Assert.assertEquals(
                selectedDate,
                "10/20/2026"
        );

        // Return to main page
        driver.switchTo().defaultContent();
    }


    // Test 2 - Select date from Calendar
    @Test(priority = 2)
    public void selectDateFromCalendar() {

        // Enter the iframe
        driver.switchTo().frame(
                driver.findElement(By.className("demo-frame"))
        );

        // Find date input
        WebElement dateBox =
                driver.findElement(By.id("datepicker"));

        // Open calendar
        dateBox.click();

        // Click 20th day
        WebElement date =
                driver.findElement(
                        By.xpath("//a[text()='20']")
                );

        date.click();

        // Get selected date
        String selectedDate =
                dateBox.getAttribute("value");

        System.out.println(
                "Date selected from calendar: "
                        + selectedDate
        );

        // Verify that a date was selected
        Assert.assertTrue(
                selectedDate.contains("20")
        );

        // Return to main page
        driver.switchTo().defaultContent();
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
