package automation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownTest {
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Dropdown")).click();
		WebElement element=driver.findElement(By.xpath("//select[@id='dropdown']"));
		Select obj=new Select(element);
		obj.selectByVisibleText("Option 2");
		System.out.println(obj.getFirstSelectedOption().getText());
		List<WebElement> options= obj.getOptions();
		
		//Retrieving options using for-each loop
		// for(WebElement el:options)
		// 	System.out.println(el.getText());
		
		//Using for loop and get() method
		for(int i=0;i<options.size();i++){
			System.out.println(options.get(i).getText());
		}
		
		
		//Using ListIterator cursor
		/*ListIterator<WebElement> list=options.listIterator();
		while(list.hasNext()){
			System.out.println(list.next().getText());
		}*/
		
		
		//Using Iterator
		/*System.out.println("Retrieving options Using Iterator cursor");
		Iterator<WebElement> list=options.iterator();
		while(list.hasNext()){
			System.out.println(list.next().getText());
		}*/
		
		driver.close();
	}

}
