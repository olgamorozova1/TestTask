package com.coherentsolutions.task.test;

import com.coherentsolutions.task.page.driver_settings.BrowserType;
import com.coherentsolutions.task.page.driver_settings.DriverInit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public static WebDriver driver;

    @BeforeAll
    static void setUp() {
        DriverInit driverInit = DriverInit.getInstance();
        driver = driverInit.openBrowser(BrowserType.CHROME);
    }

    @BeforeEach
    void openPage() {
        driver.get("https://www.google.com/travel/flights");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
