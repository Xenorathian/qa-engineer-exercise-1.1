package com.statista.exercises;

import browser.Chrome;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.CompanySearchPage;

import java.util.List;
import java.util.Objects;

import static data.Constants.*;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class CompanySeleniumApplicationTest {
	private WebDriver driver;

	@BeforeEach
	void setUp() {
		driver = Chrome.getInstance();
		driver.get(BASE_URL + URL_PATH);
	}

	@Test
	void searchForStatistaTest() {
		//Given
		String targetCompanyName = "Statista GmbH";
		int numberOfResultsToCheck = 5;

		CompanySearchPage searchPage = new CompanySearchPage(driver);
		searchPage.acceptCookieConsent();

		//When
		searchPage.searchFor("statista");

		//Then
		List<String> companyNames = searchPage.getCompanyNames(numberOfResultsToCheck);
		long numberOfCompanyResults = companyNames.stream()
				.filter(result -> Objects.equals(result, targetCompanyName))
				.count();
		assertFalse(numberOfCompanyResults == 0 && companyNames.size() < numberOfResultsToCheck,
				String.format("Result Set smaller than requested size. \n" +
						"Expected at least %s, Total found: %s", numberOfResultsToCheck, companyNames.size()));
		assertTrue(numberOfCompanyResults > 0,
				String.format("Company (%s) not found in %s results. \nCompanies found: %s",
						targetCompanyName, numberOfResultsToCheck, companyNames));

	}

	@AfterEach
	void tearDown() {
		if (nonNull(driver))
			driver.close();
	}
}
