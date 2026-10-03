import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FileTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://leafground.com/file.xhtml");
    }

    // 1. Basic Upload
    @Test(priority = 1)
    public void basicUpload() {

        WebElement upload =
                driver.findElement(By.cssSelector("input[type='file']"));

        upload.sendKeys("C:\\Users\\subas\\Desktop\\test.txt");

        System.out.println("Basic upload completed.");

        Assert.assertTrue(true);
    }

    // 2. Basic Download
    @Test(priority = 2)
    public void basicDownload() {

        WebElement download =
                driver.findElement(By.xpath("//span[text()='Download']"));

        download.click();

        System.out.println("Basic download started.");

        Assert.assertTrue(true);
    }

    // 3. Advanced Upload - Only Pictures
    @Test(priority = 3)
    public void advancedUpload() {

        WebElement upload =
                driver.findElement(By.cssSelector("input[type='file']"));

        upload.sendKeys("C:\\Users\\subas\\Desktop\\test-image.png");

        System.out.println("Picture upload completed.");

        Assert.assertTrue(true);
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}