package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        // Launch Chrome Browser
        driver = new ChromeDriver();

        // Maximize Browser
        driver.manage().window().maximize();

        // Navigate to application
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void verifyLogin() {

        // Enter Username
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        // Enter Password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // Click Login Button
        driver.findElement(By.id("submit")).click();

        // Verify successful login
        String expectedTitle = "Logged In Successfully | Practice Test Automation";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);

        System.out.println("Login Successful");
    }

    @AfterMethod
    public void tearDown() {

        // Close Browser
        if (driver != null) {
            driver.quit();
        }
    }
}