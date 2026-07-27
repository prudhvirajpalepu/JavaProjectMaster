package automation;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class brokenImages {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://the-internet.herokuapp.com/broken_images");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            List<WebElement> images = driver.findElements(By.tagName("img"));
            int brokenImageCount = 0;

            for (WebElement image : images) {
                String imageUrl = image.getAttribute("src");
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode >= 400) {
                        System.out.println("Broken Image: " + imageUrl);
                        brokenImageCount++;
                    }
                }
            }

            System.out.println("Total Broken Images: " + brokenImageCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        //    driver.quit();
        }
    }
}
