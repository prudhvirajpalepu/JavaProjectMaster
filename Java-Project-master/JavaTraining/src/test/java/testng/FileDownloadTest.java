package testng;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileDownloadTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Folder where the file will be downloaded
    private final String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";

    // Expected file name (change according to the file you download)
    private final String expectedFileName = "upload-demo.txt";

    @BeforeClass
    public void setup() {
        // Create downloads folder if it doesn't exist
        File downloadDir = new File(downloadPath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }

        // Chrome preferences to auto-download without popup
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        // options.addArguments("--headless=new"); // optional

        // WebDriverManager (recommended)
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Open the download page
        driver.get("https://the-internet.herokuapp.com/download");
    }

    @Test
    public void verifyFileDownload() throws InterruptedException {

        // Click on the file you want to download
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText(expectedFileName))).click();

        // Wait until the file is fully downloaded
        File downloadedFile = waitForFileToDownload(expectedFileName, 30);

        // ========== Assertions ==========
        Assert.assertTrue(downloadedFile.exists(), 
                "Downloaded file does not exist!");

        Assert.assertTrue(downloadedFile.length() > 0, 
                "Downloaded file is empty!");

        Assert.assertEquals(downloadedFile.getName(), expectedFileName, 
                "Downloaded file name is incorrect!");

        System.out.println("File downloaded successfully: " + downloadedFile.getAbsolutePath());
        System.out.println("File size: " + downloadedFile.length() + " bytes");
    }

    /**
     * Waits until the file appears in the download folder
     */
    private File waitForFileToDownload(String fileName, int timeoutInSeconds) throws InterruptedException {
        File file = new File(downloadPath + File.separator + fileName);
        int elapsed = 0;

        while (elapsed < timeoutInSeconds) {
            if (file.exists() && file.length() > 0) {
                // Extra check: make sure download is complete (no .crdownload)
                File tempFile = new File(downloadPath + File.separator + fileName + ".crdownload");
                if (!tempFile.exists()) {
                    return file;
                }
            }
            Thread.sleep(1000);
            elapsed++;
        }
        return file; // return even if not found (assertion will fail)
    }

    @AfterClass
    public void tearDown() {
        // Optional: delete the downloaded file after test
        File file = new File(downloadPath + File.separator + expectedFileName);
        if (file.exists()) {
            file.delete();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}