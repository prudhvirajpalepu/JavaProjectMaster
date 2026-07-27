package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UploadFile {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.manage().window().maximize();
       
    }

    @Test(enabled = false)
    public void NavigateTouploadFile() {
        try {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeScript("document.getElementById('file-upload').scrollIntoView(true);");
            WebElement element = driver.findElement(By.linkText("File Upload"));
            element.click();
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking on File Upload link: " + e.getMessage());
        }
    }

    @Test(enabled = true)
    public void verifyFileUpload() {
        try {
            WebElement fileInput = driver.findElement(By.id("file-upload"));
            String filePath = "D:/Java-Project-master/Java-Project-master/JavaTraining/src/test/java/test.txt";
            fileInput.sendKeys(filePath);
            driver.findElement(By.id("file-submit")).click();
            System.out.println("File has been successfully uploaded...");
        } catch (Exception e) {
            System.out.println("Exception occurred while uploading the file: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
