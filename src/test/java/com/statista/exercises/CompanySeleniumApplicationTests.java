package com.statista.exercises;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
class CompanySeleniumApplicationTests {

	private static final File recordFolder = new File("./recordings");

	@Container
	private static BrowserWebDriverContainer<?> chromeContainer = new BrowserWebDriverContainer<>()
			.withCapabilities(new ChromeOptions())
			.withRecordingMode(
					BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
					recordFolder,
					VncRecordingContainer.VncRecordingFormat.MP4
			);
}
