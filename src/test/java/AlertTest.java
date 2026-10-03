import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://leafground.com/alert.xhtml;jsessionid=node0umf0zgtee3ir1hizk03hq5sri1125841.node0");
    }


    // ============================================================
    // TEST 1 - SIMPLE ALERT
    // ============================================================

    @Test
    public void testSimpleAlert() {

        // Replace this with the ID/XPath of the Simple Alert button
        driver.findElement(
                By.id("j_idt88:j_idt91")
        ).click();


        // Switch to alert
        Alert alert = driver.switchTo().alert();


        // Get alert text
        String actualText = alert.getText();

        System.out.println("Simple Alert Text: " + actualText);


        // Verify alert is displayed
        Assert.assertTrue(
                alert != null,
                "Simple alert was not displayed"
        );


        // Click OK
        alert.accept();
    }


    // ============================================================
    // TEST 2 - CONFIRM ALERT - ACCEPT
    // ============================================================

    @Test
    public void testConfirmAlertAccept() {

        // Replace with Confirm Alert button ID/XPath
        driver.findElement(
                By.id("j_idt88:j_idt93")
        ).click();


        Alert alert = driver.switchTo().alert();


        // Get text
        String actualText = alert.getText();

        System.out.println("Confirm Alert Text: " + actualText);


        // Verify text is not empty
        Assert.assertFalse(
                actualText.isEmpty(),
                "Alert message is empty"
        );


        // Click OK
        alert.accept();
    }


    // ============================================================
    // TEST 3 - CONFIRM ALERT - DISMISS
    // ============================================================

    @Test
    public void testConfirmAlertDismiss() {

        // Replace with Confirm Alert button ID/XPath
        driver.findElement(
                By.id("j_idt88:j_idt106")
        ).click();


        Alert alert = driver.switchTo().alert();


        System.out.println(
                "Confirm Alert Text: " + alert.getText()
        );


        // Click Cancel
        alert.dismiss();
    }


    // ============================================================
    // TEST 4 - PROMPT ALERT
    // ============================================================

    @Test
    public void testPromptAlert() {

        // Replace with Prompt Alert button ID/XPath
        driver.findElement(
                By.id("j_idt88:j_idt104")
        ).click();


        Alert alert = driver.switchTo().alert();


        // Get alert text
        String actualText = alert.getText();

        System.out.println("Prompt Alert Text: " + actualText);


        // Verify alert message
        Assert.assertFalse(
                actualText.isEmpty(),
                "Prompt alert message is empty"
        );


        // Enter text into prompt
        alert.sendKeys("Bhagya");


        // Click OK
        alert.accept();
    }





    // ============================================================
    // CLOSE BROWSER AFTER EVERY TEST
    // ============================================================

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
