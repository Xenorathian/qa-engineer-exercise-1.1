package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    static final long LONG_TIMEOUT_IN_SECONDS = 30;
    static final long SHORT_TIMEOUT_IN_SECONDS = 1;
    WebDriver driver;
    WebDriverWait shortWait;
    WebDriverWait longWait;
}
