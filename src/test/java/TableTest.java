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

public class TableTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");
    }


    // 1. Find number of rows
    @Test(priority = 1)
    public void findNumberOfRows() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> rows =
                table.findElements(By.tagName("tr"));

        System.out.println("Number of rows: " + rows.size());

        Assert.assertTrue(rows.size() > 0);
    }


    // 2. Find number of columns
    @Test(priority = 2)
    public void findNumberOfColumns() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> columns =
                table.findElements(By.xpath(".//thead/tr/th"));

        System.out.println(
                "Number of columns: " + columns.size()
        );

        Assert.assertTrue(columns.size() > 0);
    }


    // 3. Retrieve specific row and column data
    @Test(priority = 3)
    public void getSpecificCellData() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> rows =
                table.findElements(By.xpath(".//tbody/tr"));

        // Get first row
        WebElement firstRow = rows.get(0);

        // Get cells of first row
        List<WebElement> cells =
                firstRow.findElements(By.tagName("td"));

        System.out.println(
                "First row data:"
        );

        for (WebElement cell : cells) {

            System.out.println(cell.getText());
        }

        Assert.assertTrue(cells.size() > 0);
    }


    // 4. Retrieve all table data
    @Test(priority = 4)
    public void getAllTableData() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> rows =
                table.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            for (WebElement cell : cells) {

                System.out.print(
                        cell.getText() + " | "
                );
            }

            System.out.println();
        }

        Assert.assertTrue(rows.size() > 0);
    }


    // 5. Print ID and Name columns only
    @Test(priority = 5)
    public void printIdAndName() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> rows =
                table.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            String id =
                    cells.get(0).getText();

            String name =
                    cells.get(1).getText();

            System.out.println(
                    "ID: " + id +
                            " | Name: " + name
            );
        }

        Assert.assertTrue(rows.size() > 0);
    }


    // 5.1 Find price related to Product 3
    @Test(priority = 6)
    public void findProduct3Price() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> rows =
                table.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            String productName =
                    cells.get(1).getText();

            if (productName.equals("Product 3")) {

                String price =
                        cells.get(2).getText();

                System.out.println(
                        "Product 3 Price: " + price
                );

                Assert.assertEquals(
                        productName,
                        "Product 3"
                );

                break;
            }
        }
    }


    // 6. Select all checkboxes
    @Test(priority = 7)
    public void selectAllCheckboxes() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> checkboxes =
                table.findElements(
                        By.cssSelector(
                                "input[type='checkbox']"
                        )
                );

        for (WebElement checkbox : checkboxes) {

            if (!checkbox.isSelected()) {

                checkbox.click();
            }
        }

        System.out.println(
                "All checkboxes selected."
        );

        Assert.assertTrue(checkboxes.size() > 0);
    }


    // 7. Select one checkbox
    @Test(priority = 8)
    public void selectOneCheckbox() {

        WebElement table =
                driver.findElement(By.id("productTable"));

        List<WebElement> rows =
                table.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            String productName =
                    cells.get(1).getText();

            if (productName.equals("Product 3")) {

                WebElement checkbox =
                        cells.get(3).findElement(
                                By.cssSelector(
                                        "input[type='checkbox']"
                                )
                        );

                checkbox.click();

                System.out.println(
                        "Product 3 checkbox selected."
                );

                Assert.assertTrue(
                        checkbox.isSelected()
                );

                break;
            }
        }
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
