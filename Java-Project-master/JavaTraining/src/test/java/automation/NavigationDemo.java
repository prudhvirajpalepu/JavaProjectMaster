package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class NavigationDemo {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//Navigation Methods
		driver.navigate().to("http://qa.....");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Sign Up")).click();
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
	}

}
