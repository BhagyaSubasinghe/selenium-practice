import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class RadioButtonTest {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // LeafGround Radio button page
        driver.get("https://www.leafground.com/radio.xhtml");

        // -------------------------------------------------------------
        // Task 1: Your most favorite browser (Select 'Chrome')
        // -------------------------------------------------------------
        WebElement chromeRadio = driver.findElement(By.xpath("//h5[text()='Your most favorite browser']/following-sibling::div//label[text()='Chrome']"));
        chromeRadio.click();


        // -------------------------------------------------------------
        // Task 2: Find the default select radio button
        // -------------------------------------------------------------
        WebElement safariBox = driver.findElement(By.xpath("//h5[text()='Find the default select radio button']/following-sibling::div//label[text()='Safari']/preceding-sibling::div[contains(@class,'ui-radiobutton-box')]"));
        boolean isSafariSelected = safariBox.getAttribute("class").contains("ui-state-active");
        System.out.println("Is Safari Selected by default? " + isSafariSelected);


        // -------------------------------------------------------------
        // Task 3: UnSelectable radio button
        // -------------------------------------------------------------
        WebElement cityRadio = driver.findElement(By.xpath("//h5[text()='UnSelectable']/following-sibling::div//label[text()='Chennai']"));
        cityRadio.click();


        // -------------------------------------------------------------
        // Task 4: Select the age group (only if not selected)
        // -------------------------------------------------------------
        WebElement ageBox = driver.findElement(By.xpath("//h5[contains(text(),'Select the age group')]/following-sibling::div//label[text()='21-40 Years']/preceding-sibling::div[contains(@class,'ui-radiobutton-box')]"));

        boolean isAgeSelected = ageBox.getAttribute("class").contains("ui-state-active");

        if (!isAgeSelected) {
            WebElement ageLabel = driver.findElement(By.xpath("//h5[contains(text(),'Select the age group')]/following-sibling::div//label[text()='21-40 Years']"));
            ageLabel.click();
            System.out.println("Age group was not selected. Clicked now.");
        } else {
            System.out.println("Age group (21-40 Years) is already selected.");
        }

        driver.quit();
    }
}
