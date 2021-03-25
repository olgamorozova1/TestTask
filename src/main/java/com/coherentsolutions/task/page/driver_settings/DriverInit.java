package com.coherentsolutions.task.page.driver_settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverInit {
    public static DriverInit instanceDriver = null;

    private WebDriver driver;

    private DriverInit() {
    }

    public WebDriver openBrowser(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en-US");
                driver = new ChromeDriver(options);
                break;
        }
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
}

    public static DriverInit getInstance() {
        if (instanceDriver == null)
            instanceDriver = new DriverInit();
        return instanceDriver;
    }
}
