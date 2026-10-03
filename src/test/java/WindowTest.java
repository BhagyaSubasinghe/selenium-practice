import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class WindowTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(10)
        );

        driver.get(
                "https://leafground.com/window.xhtml"
        );
    }


    // ============================================================
    // TEST 1 - CLICK AND CONFIRM NEW WINDOW OPENS
    // ============================================================

    @Test
    public void testNewWindowOpens() {

        String primaryWindow =
                driver.getWindowHandle();

        driver.findElement(
                By.id("j_idt88:new")
        ).click();

        Set<String> windows =
                driver.getWindowHandles();

        System.out.println(
                "Number of windows: " + windows.size()
        );

        Assert.assertEquals(
                windows.size(),
                2,
                "New window did not open"
        );
    }


    // ============================================================
    // TEST 2 - FIND NUMBER OF OPENED TABS
    // ============================================================

    @Test
    public void testMultipleWindows() {

        driver.findElement(
                By.id("j_idt88:j_idt91")
        ).click();

        Set<String> windows =
                driver.getWindowHandles();

        System.out.println(
                "Number of opened windows: "
                        + windows.size()
        );

        Assert.assertTrue(
                windows.size() > 1,
                "Multiple windows were not opened"
        );
    }


    // ============================================================
    // TEST 3 - CLOSE ALL WINDOWS EXCEPT PRIMARY
    // ============================================================

    @Test
    public void testCloseAllExceptPrimary() {

        String primaryWindow =
                driver.getWindowHandle();


        driver.findElement(
                By.id("j_idt88:j_idt93")
        ).click();


        Set<String> windows =
                driver.getWindowHandles();


        System.out.println(
                "Windows before closing: "
                        + windows.size()
        );


        for (String window : windows) {

            if (!window.equals(primaryWindow)) {

                driver.switchTo().window(window);

                driver.close();
            }
        }


        driver.switchTo().window(primaryWindow);


        Set<String> remainingWindows =
                driver.getWindowHandles();


        System.out.println(
                "Windows after closing: "
                        + remainingWindows.size()
        );


        Assert.assertEquals(
                remainingWindows.size(),
                1,
                "More than one window remains"
        );
    }


    // ============================================================
    // TEST 4 - WAIT FOR 2 NEW TABS TO OPEN
    // ============================================================

    @Test
    public void testOpenWithDelay() {

        driver.findElement(
                By.id("j_idt88:j_idt95")
        ).click();


        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );


        wait.until(
                driver ->
                        driver.getWindowHandles().size() == 3
        );


        Set<String> windows =
                driver.getWindowHandles();


        System.out.println(
                "Number of windows: "
                        + windows.size()
        );


        Assert.assertEquals(
                windows.size(),
                3,
                "Two new tabs did not open"
        );
    }


    // ============================================================
    // CLOSE BROWSER
    // ============================================================

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }
}