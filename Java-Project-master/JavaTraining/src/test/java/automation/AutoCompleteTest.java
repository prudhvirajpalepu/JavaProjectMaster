package automation;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoCompleteTest {
	public static void main(String[] args) throws  IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://qa....");
		driver.manage().window().maximize();
		//To Login
		driver.findElement(By.id("TxtEmail")).sendKeys("emailid");
		driver.findElement(By.id("TxtPassword")).sendKeys("test@1234");
		driver.findElement(By.id("BtnLogin")).click();

		//To get the OTP from Database
		String otp= "1242";
		driver.findElement(By.id("txtOTP")).sendKeys(otp);
		driver.findElement(By.id("BtnContinue")).click();
		//To click on Company Name
		driver.findElement(By.linkText("Client solutions")).click();

		driver.findElement(By.id("cpBody_divqlbillentry")).click();
		driver.findElement(By.id("cpBody_btnBeginDEWithoutImg")).click();

		//Handling Auto suggest/complete dropdown 
		driver.findElement(By.id("cpBody_ddlAutoCompExt_txt_comb")).sendKeys("ven");
		List<WebElement> list=driver.findElements(By.xpath("//ul[@id='cpBody_ddlAutoCompExt_ul_comb']/li"));
		
		//Using for loop with get() method
		for(int i=0;i<list.size();i++){
			String exp="client vendor-01";
			String actual=list.get(i).getText();
			if(exp.equals(actual)){
				list.get(i).click();
				break;
			}
		}
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		Thread.sleep(3000);
		String text=alert.getText();
		System.out.println(text);
		alert.accept();
		alert.dismiss();
		
	}

}
