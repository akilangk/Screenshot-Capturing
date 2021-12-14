package captureScreenShot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CaptureScreenShot {
    WebDriver driver;

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public File filePathForScreenShot() {
        String userWorkingDirectory = System.getProperty("user.dir");
        String pathSeparator = System.getProperty("file.separator");
        return new File(userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" +
                pathSeparator + "java" + pathSeparator + "captureScreenShot" + pathSeparator
                + "screenShots" + pathSeparator + "screenshot_" + generateRandomNumber() + ".png");
    }

    public void openUrl() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/css/css_tooltip.asp");
    }

    public void captureScreenShot() {
        try {
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, filePathForScreenShot());
            driver.quit();
            System.out.println("Screenshot Captured.");
        } catch (IOException exception) {
            System.out.println("Check the path of the file..");
        }
    }

    public static void main(String[] args) {
        CaptureScreenShot run = new CaptureScreenShot();
        run.openUrl();
        run.captureScreenShot();
    }
}
