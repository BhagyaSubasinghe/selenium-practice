import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class ImageTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demoqa.com/broken");
    }

    // 1. Find number of images
    @Test(priority = 1)
    public void countImages() {

        List<WebElement> images =
                driver.findElements(By.tagName("img"));

        System.out.println("Number of images: " + images.size());

        Assert.assertTrue(images.size() > 0);
    }

    // 2. Get image source
    @Test(priority = 2)
    public void getImageSources() {

        List<WebElement> images =
                driver.findElements(By.tagName("img"));

        for (WebElement image : images) {

            String source = image.getAttribute("src");

            System.out.println("Image Source: " + source);
        }

        Assert.assertTrue(images.size() > 0);
    }

    // 3. Find broken images
    @Test(priority = 3)
    public void findBrokenImages() {

        List<WebElement> images =
                driver.findElements(By.tagName("img"));

        int brokenImages = 0;

        for (WebElement image : images) {

            String source = image.getAttribute("src");

            if (source == null || source.isEmpty()) {
                continue;
            }

            try {

                URL url = new URL(source);

                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("HEAD");

                connection.connect();

                int responseCode =
                        connection.getResponseCode();

                System.out.println(
                        "Image: " + source +
                                " | Response Code: " + responseCode
                );

                if (responseCode >= 400) {

                    brokenImages++;

                    System.out.println("Broken Image Found!");
                }

                connection.disconnect();

            } catch (IOException e) {

                brokenImages++;

                System.out.println(
                        "Could not check image: " + source
                );
            }
        }

        System.out.println(
                "Total Broken Images: " + brokenImages
        );

        Assert.assertTrue(true);
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
