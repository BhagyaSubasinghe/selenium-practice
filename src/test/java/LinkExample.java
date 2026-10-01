import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;


public class LinkExample {

    WebDriver driver;

    @BeforeMethod
    public void openLinkTestPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://leafground.com/");

    }

    @Test
    public void LinkTests() {
        //01) Take me to dashboard

        WebElement homeLink = driver.findElement(By.linkText ("Go To Dashboard"));

        homeLink.click();
        driver.navigate().back();

        //02) find my destination

        WebElement wheretoGo = driver.findElement(By.partialLinkText("Find my URL"));
        String path = wheretoGo.getAttribute("href");

        System.out.println("This Link is Going to :" + path);


        //03)Am I broken link
        WebElement brokenLink = driver.findElement(By.linkText("Broken"));
        brokenLink.click();

        String title = driver.getTitle();

        if (title.contains("404")){
            System.out.println("This Link is broken");
        }else {
            System.out.println("This Link is not broken");
        }

        //04)duplicate link
        homeLink.click();

        //05)count page link
        List<WebElement>fullPageLinks = driver.findElements(By.tagName("a"));
        int pagelINKCount = fullPageLinks.size();

        System.out.println("Count of full page Links: " + pagelINKCount);

        //06)count layout link
        WebElement layoutElement = driver.findElement(By.className("layout-main-content"));

        List<WebElement> countLayoutLinks = layoutElement.findElements(By.tagName("a"));

        System.out.println("count of layout links : " +countLayoutLinks.size());

    }


    
}
