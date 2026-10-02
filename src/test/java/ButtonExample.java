import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ButtonExample {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. LeafGround Button Page
        driver.get("https://www.leafground.com/button.xhtml");


        // -------------------------------------------------------------
        // Task 01
        // XPath Strategy
        // -------------------------------------------------------------
        WebElement clickBtn = driver.findElement(By.xpath("//button[span[text()='Click']]"));
        clickBtn.click();


        driver.navigate().back();


        // -------------------------------------------------------------
        // Task 02
        // -------------------------------------------------------------
        WebElement disabledBtn = driver.findElement(By.xpath("//button[span[text()='Disabled']]"));
        boolean isDisabled = !disabledBtn.isEnabled(); // isEnabled() Method
        System.out.println("02) Is Button Disabled ? " + isDisabled);


        // -------------------------------------------------------------
        // Task 03

        // -------------------------------------------------------------
        WebElement positionBtn = driver.findElement(By.xpath("//button[span[text()='Submit']]"));
        Point point = positionBtn.getLocation();
        System.out.println("03) Position X: " + point.getX() + " | Position Y: " + point.getY());


        // -------------------------------------------------------------


        // Task 04

        // -------------------------------------------------------------
        WebElement colorBtn = driver.findElement(By.xpath("//button[span[text()='Save']]"));
        String bgColor = colorBtn.getCssValue("background-color"); // getCssValue()
        System.out.println("04) Button Background Color: " + bgColor);


        // Task 05
        WebElement sizeBtn = driver.findElement(By.xpath("(//button[span[text()='Submit']])[2]"));
        Dimension size = sizeBtn.getSize();
        System.out.println("05) Height: " + size.getHeight() + " | Width: " + size.getWidth());



        // -------------------------------------------------------------
        WebElement textBtn = driver.findElement(By.xpath("//button[span[text()='Submit']]"));
        String buttonText = textBtn.getText(); // getText() Method
        System.out.println("06) Button Text : " + buttonText);
        // driver.quit();
    }
}
