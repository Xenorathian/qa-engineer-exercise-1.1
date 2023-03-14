package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CompanySearchPage extends BasePage {

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement cookieConsentButton;

    @FindBy(css = "input.companySearchTagInput__input")
    private WebElement companySearchBar;

    @FindBy(css = "td.companyResults__td--companyName")
    private List<WebElement> nameResultList;

    @FindBy(css = "table.companyResults__table")
    private WebElement resultsTable;

    public CompanySearchPage(WebDriver driver) {
        this.driver = driver;
        this.shortWait = new WebDriverWait(driver, SHORT_TIMEOUT_IN_SECONDS);
        this.longWait = new WebDriverWait(driver, LONG_TIMEOUT_IN_SECONDS);
        initElements(driver, this);
        if (!companySearchBar.isDisplayed()) {
            throw new IllegalStateException("Company search page is not showing a search bar. " +
                    "Page maybe incorrect. Current URL is: " + driver.getCurrentUrl());
        }
    }

    public void acceptCookieConsent() {
        cookieConsentButton.click();
        longWait.until(invisibilityOf(cookieConsentButton));
    }

    public void searchFor(String searchTerm) {
        companySearchBar.sendKeys(searchTerm);
        companySearchBar.sendKeys(Keys.ENTER);
        initElements(driver, this);
        ExpectedCondition<Boolean> resultTableIsBlurred = attributeContains(resultsTable, "class", "blurred");
        try {
            shortWait.until(resultTableIsBlurred);
        } catch (TimeoutException exception) {
            // Ignoring for short wait
        }
        longWait.until(not(resultTableIsBlurred));
    }

    public List<String> getCompanyNames(int numberOfCompanies) {
        return nameResultList.stream()
                .limit(numberOfCompanies)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}