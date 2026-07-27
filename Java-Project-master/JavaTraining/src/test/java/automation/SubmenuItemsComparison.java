package automation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

// Local replacement for missing comparison.ArrayListCompare
class ArrayListCompare {
	// Returns list of items that are in expected but not in actual, and vice versa
	public static ArrayList<String> compareLists(ArrayList<String> expected, ArrayList<String> actual) {
		ArrayList<String> unmatched = new ArrayList<>();
		for (String e : expected) {
			if (!actual.contains(e)) {
				unmatched.add("Expected missing: " + e);
			}
		}
		for (String a : actual) {
			if (!expected.contains(a)) {
				unmatched.add("Actual extra: " + a);
			}
		}
		return unmatched;
	}
}

public class SubmenuItemsComparison {
	
	static WebDriver driver =null;
	ArrayList<String> unmatched=null;
	
	///public static void main(String[] args) throws SQLException, IOException, InterruptedException {
	@Test
	public void tc_SubmenuItems_01() throws Exception{
		try{
			
			io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
			//Maximizing browser window using maximize() method
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("url");
			//To Login
			driver.findElement(By.id("TxtEmail")).sendKeys("email");
			driver.findElement(By.id("TxtPassword")).sendKeys("pwd");
			driver.findElement(By.id("BtnLogin")).click();
			//To get the OTP from Database
			String otp= "1242";
			driver.findElement(By.id("txtOTP")).sendKeys(otp);
			driver.findElement(By.id("BtnContinue")).click();
			//To click on Company Name
			driver.findElement(By.linkText("Retail R26.5")).click();
			Thread.sleep(5000);
			//mouseHover(driver.findElement(By.xpath("//a[@id='menu_aBills']")));
			driver.findElement(By.xpath("//a[@id='menu_aBills']")).click();
					
			//Parsing string by comma separate and store individual items into ArrayList object.
			String exp="Add Bill, Enter Bill Data, Approve Bills, Pay Bills, My Recurring Bills, Add Recurring Bill, Search Bills, Bill Inbox";
			ArrayList<String> expectedItems=getOptions(exp);
			System.out.println("Expected Submenu Items: "+expectedItems);
			
			//To retrieve actual sub menu items from the application
			ArrayList<String> actualItems=new ArrayList<>();
			List<WebElement> submenus=driver.findElements(By.xpath("//div[@class='sticky-menu']//li[1]//ul[1]//li"));
			System.out.println("Submenu Items Size: "+submenus.size());
			for(int i=1;i<=submenus.size();i++){
				actualItems.add(driver.findElement(By.xpath("//div[@class='sticky-menu']//li[1]//ul[1]//li["+i+"]//a")).getText());
			}
			System.out.println("Actual Submenu Items: "+actualItems);
			
			//Comparing both actual and expected ArrayLists
			unmatched=ArrayListCompare.compareLists(expectedItems, actualItems);
			if(unmatched.size()>0){
				System.out.println("Unmatched Submenus: "+unmatched);
				throw new Exception("Unmatched Submenus: "+unmatched);
			}
			else
				System.out.println("All the Actual and Expected Submenus are Equal.");
		}
		catch(Exception e){
			throw new Exception("Unmatched Submenus: "+unmatched);
		}
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	// To convert string with comma separated as ArrayList
		public ArrayList<String> getOptions(String str) {
			ArrayList<String> options = new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*")));
			return options;
		}
	
}
