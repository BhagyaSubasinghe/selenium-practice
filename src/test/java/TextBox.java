import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TextBox {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // LeafGround Input Page
        driver.get("https://www.leafground.com/input.xhtml");

        // -------------------------------------------------------------
        // 01) Type your name
        // -------------------------------------------------------------
        WebElement nameField = driver.findElement(By.id("j_idt88:name"));
        nameField.sendKeys("Kamal Perera");


        // -------------------------------------------------------------
        // 02) Append Country to this City
        // -------------------------------------------------------------
        WebElement appendField = driver.findElement(By.id("j_idt88:j_idt91"));
        appendField.sendKeys(" - Sri Lanka");


        // -------------------------------------------------------------
        // 03) Verify if text box is disabled
        // -------------------------------------------------------------
        WebElement disabledField = driver.findElement(By.id("j_idt88:j_idt93"));
        boolean isDisabled = !disabledField.isEnabled();

        if (isDisabled) {
            System.out.println("03) Text box is Disabled - TEST PASSED");
        } else {
            System.out.println("03) Text box is Enabled - TEST FAILED");
        }


        // -------------------------------------------------------------
        // 04) Clear the typed text
        // -------------------------------------------------------------
        WebElement clearField = driver.findElement(By.id("j_idt88:j_idt95"));
        clearField.clear();


        // -------------------------------------------------------------
        // 05) Retrieve the typed text
        // -------------------------------------------------------------
        WebElement retrieveField = driver.findElement(By.id("j_idt88:j_idt97"));
        String typedText = retrieveField.getAttribute("value");
        System.out.println("05) Retrieved text: " + typedText);


        // -------------------------------------------------------------
        // 06) Type email and Tab. Confirm control moved to next element
        // -------------------------------------------------------------
        WebElement emailField = driver.findElement(By.id("j_idt88:new-given-name"));
        emailField.sendKeys("test@example.com" + Keys.TAB);


        WebElement activeElement = driver.switchTo().activeElement();


        if (!activeElement.equals(emailField)) {
            System.out.println("06) Control moved to next element - TEST PASSED");
        } else {
            System.out.println("06) Control did not move - TEST FAILED");
        }

        // driver.quit();
    }
}

