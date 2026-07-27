package automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class switchWindows {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://the-internet.herokuapp.com");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement element = driver.findElement(By.linkText("Multiple Windows"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
      //      WebElement element2 = driver.findElement(By.xpath("//*[@id='content']/ul/li[33]/a"));
      //      element.click();
            String mainWindowHandle = driver.getWindowHandle();
            System.out.println("Main Window Handle: " + mainWindowHandle);
            driver.findElement(By.linkText("Click Here")).click();
            System.out.println("New window opened.");
            // Switch to the new window and perform actions
            for (String windowHandle : driver.getWindowHandles()) {
                System.out.println("Window Handle: " + windowHandle);
                if (!windowHandle.equals(mainWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("Switched to new window: " + driver.getTitle());
                    driver.close();
                }
            }
            driver.switchTo().window(mainWindowHandle); 
            driver.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
