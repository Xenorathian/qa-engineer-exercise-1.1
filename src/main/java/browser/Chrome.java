package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.util.concurrent.TimeUnit.SECONDS;
public class Chrome {

    static {
        chromedriver().setup();
    }

    public static WebDriver getInstance() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}