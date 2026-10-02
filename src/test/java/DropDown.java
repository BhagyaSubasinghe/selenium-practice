import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class DropDown {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to LeafGround Select page
        driver.get("https://www.leafground.com/select.xhtml");

        // -------------------------------------------------------------
        // 1.1) Ways of selecting values in Basic dropdown
        // -------------------------------------------------------------
        // Locate basic HTML <select> element
        WebElement basicDropdownElem = driver.findElement(By.xpath("//select[@class='ui-selectonemenu']"));
        Select basicSelect = new Select(basicDropdownElem);

        // Method 1: By visible text
        basicSelect.selectByVisibleText("Puppeteer");

        // Method 2: By index (0-based)
        basicSelect.selectByIndex(1);

        // Method 3: By value attribute
        // basicSelect.selectByValue("value_attribute_here");


        // -------------------------------------------------------------
        // 1.2) Get the number of dropdown options
        // -------------------------------------------------------------
        // Fetch all options into a List and get total count
        List<WebElement> optionsList = basicSelect.getOptions();
        int totalOptions = optionsList.size();
        System.out.println("1.2) Total Dropdown Options: " + totalOptions);


        // -------------------------------------------------------------
        // 1.3) Using sendKeys to select a dropdown value
        // -------------------------------------------------------------
        // Direct value selection using sendKeys without Select class
        basicDropdownElem.sendKeys("Selenium");


        // -------------------------------------------------------------
        // 1.4) Selecting value in a Bootstrap dropdown
        // -------------------------------------------------------------
        // Step 1: Click and open the custom/Bootstrap dropdown
        WebElement countryDropdown = driver.findElement(By.xpath("//label[@id='j_idt87:country_label']"));
        countryDropdown.click();

        // Step 2: Click the desired option from the expanded list
        WebElement optionToSelect = driver.findElement(By.xpath("//li[@data-label='India']"));
        optionToSelect.click();


        // -------------------------------------------------------------
        // 2) Google Search - Pick a value from suggestions
        // -------------------------------------------------------------
        driver.get("https://www.google.com");

        // Type search query
        WebElement googleSearchBox = driver.findElement(By.name("q"));
        googleSearchBox.sendKeys("Selenium");

        Thread.sleep(2000); // Wait for suggestions to populate

        // Store all auto-suggestions in a List
        List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@role='listbox']//li//span"));

        // Iterate and click the target suggestion
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase("selenium webdriver")) {
                suggestion.click();
                break;
            }
        }


        // -------------------------------------------------------------
        // 3) Handle Hidden Auto Suggestions Dropdown using DOM Debugger
        // -------------------------------------------------------------
        /*
         * DOM Debugger Trick steps:
         * 1. Open DevTools (F12) in Chrome and go to the 'Sources' tab.
         * 2. Type in the search box to reveal suggestions, then press F8 (or Ctrl + \) to freeze the DOM.
         * 3. Inspect the frozen element to capture its exact XPath.
         */

        // Click using the located XPath:
        // driver.findElement(By.xpath("//div[contains(@class,'hidden-item') and text()='Target']")).click();

        // driver.quit();
    }
}
