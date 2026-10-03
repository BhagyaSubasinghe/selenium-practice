import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FrameTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://leafground.com/frame.xhtml");
    }


    // 1. Click Me inside the frame
    @Test(priority = 1)
    public void testSimpleFrame() {

        // Switch to the first frame
        driver.switchTo().frame(0);

        // Find the button
        WebElement button = driver.findElement(By.id("Click"));

        // Click the button
        button.click();

        // Get the changed button text
        String text = button.getText();

        System.out.println("Button Text: " + text);

        // Verify the text after clicking
        Assert.assertEquals(text, "Hurray! You Clicked Me.");

        // Return to main page
        driver.switchTo().defaultContent();
    }


    // 2. Count the number of frames
    @Test(priority = 2)
    public void testFrameCount() {

        List<WebElement> frames =
                driver.findElements(By.tagName("iframe"));

        int frameCount = frames.size();

        System.out.println("Number of Frames: " + frameCount);

        // LeafGround currently has 3 frames
        Assert.assertEquals(frameCount, 3);

        System.out.println("Frame count test passed.");
    }


    // 3. Click Me inside the nested frame
    @Test(priority = 3)
    public void testNestedFrame() {

        // Switch to outer frame
        driver.switchTo().frame(2);

        System.out.println("Inside outer frame.");

        // Find inner frame
        WebElement innerFrame =
                driver.findElement(By.id("frame2"));

        // Switch to inner frame
        driver.switchTo().frame(innerFrame);

        System.out.println("Inside inner frame.");

        // Find and click button
        WebElement button =
                driver.findElement(By.id("Click"));

        button.click();

        // Get changed button text
        String text = button.getText();

        System.out.println("Nested Frame Button Text: " + text);

        // Verify the text
        Assert.assertEquals(text, "Hurray! You Clicked Me.");

        // Return to main page
        driver.switchTo().defaultContent();
    }


    // 4. Demonstrate parentFrame()
    @Test(priority = 4)
    public void testParentFrame() {

        // Switch to outer frame
        driver.switchTo().frame(2);

        System.out.println("Inside outer frame.");

        // Switch to inner frame
        WebElement innerFrame =
                driver.findElement(By.id("frame2"));

        driver.switchTo().frame(innerFrame);

        System.out.println("Inside inner frame.");

        // Move one level back to outer frame
        driver.switchTo().parentFrame();

        System.out.println("Returned to outer frame.");

        // Return to main page
        driver.switchTo().defaultContent();

        System.out.println("Returned to main page.");

        Assert.assertTrue(true);
    }


    // 5. Demonstrate getText()
    @Test(priority = 5)
    public void testGetText() {

        // Switch to first frame
        driver.switchTo().frame(0);

        // Find button
        WebElement button =
                driver.findElement(By.id("Click"));

        // Get button text before clicking
        String text = button.getText();

        System.out.println("getText() Result: " + text);

        // Verify text
        Assert.assertEquals(text, "Click Me");

        // Return to main page
        driver.switchTo().defaultContent();
    }


    // 6. Demonstrate getAttribute()
    @Test(priority = 6)
    public void testGetAttribute() {

        // Switch to first frame
        driver.switchTo().frame(0);

        // Find button
        WebElement button =
                driver.findElement(By.id("Click"));

        // Get button ID
        String id = button.getAttribute("id");

        System.out.println("Button ID: " + id);

        // Verify ID
        Assert.assertEquals(id, "Click");

        // Return to main page
        driver.switchTo().defaultContent();
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}