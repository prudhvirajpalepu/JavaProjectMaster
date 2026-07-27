package automation;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUpload {
	public static void main(String[] args) throws IOException, InterruptedException {
//		String exePath = System.getProperty("user.dir")+"//chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/");
		driver.manage().window().maximize();

		driver.findElement(By.linkText("File Upload")).click();
		Thread.sleep(3000);

		// Wait for page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Locate the file input element
	//	WebElement fileInput = wait.until(
     //           ExpectedConditions.presenceOfElementLocated(By.id("file-upload")));	
		WebElement fileInput = driver.findElement(By.id("file-upload"));

	 	Thread.sleep(5000);

		String filePath = "D:/Java-Project-master/Java-Project-master/JavaTraining/src/test/java/test.txt";

		// Upload the file using sendKeys()
            fileInput.sendKeys(filePath);
		Thread.sleep(5000);
		driver.findElement(By.id("file-submit")).click();
		System.out.println("File has been successfully uploaded...");
		
	}

}
