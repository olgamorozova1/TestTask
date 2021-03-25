package com.coherentsolutions.task.page.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightDetailsPage {
    private WebDriver driver;

    private By stopsButton = By.xpath("//div[@data-label='Stops']//button");
    private By airlinesButton = By.xpath("//div[@data-label='Airlines']//button");
    private By selectAllAirlinesSwitch = By.xpath("//input[@aria-label='Select all airlines']");
    private By sortByButton = By.xpath("//button[@aria-label='Sort by']");
    private By showMoreFlights = By.xpath("//span[contains(@aria-label,'more flights')]");
    private By listOfTickets = By.xpath("//div[@class='mz0jqb taHBqe Qpcsfe']");
    private By numberOfStopsNoneOption = By.xpath("//input[@aria-label='Non-stop only']");
    private By numberOfStopsOneStopOption = By.xpath("//input[@aria-label='1 stop or fewer']");
    private By numberOfStopsTwoStopsOption = By.xpath("//input[@aria-label='2 stops or fewer']");
    private By numberOfStopsAnyOption = By.xpath("//input[@aria-label='Any number of stops']");
    private By airlinesBlock = By.xpath("//div[@class='t4Bkxc']");
    private By sortByPriceOption = By.xpath("//ul[@aria-label='Select the sort order in which solutions are listed.']/li[@data-value='2']");
    private By sortByDepartureTimeOption = By.xpath("//ul[@aria-label='Select the sort order in which solutions are listed.']/li[@data-value='3']");
    private By sortByArrivalTimeOption = By.xpath("//ul[@aria-label='Select the sort order in which solutions are listed.']/li[@data-value='4']");
    private By sortByDurationOption = By.xpath("//ul[@aria-label='Select the sort order in which solutions are listed.']/li[@data-value='5']");
    private By sortByBestOption = By.xpath("//ul[@aria-label='Select the sort order in which solutions are listed.']/li[@data-value='1']");
    private By expandTicketDetailsButton = By.xpath(".//div[@class='xKbyce']");
    private By flightNumberElements = By.xpath(".//span[@class='Xsgmwe QS0io']");

    public FlightDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectNumberOfStops(String numberOfStops) {
        driver.findElement(stopsButton).click();
        switch (numberOfStops) {
            case ("Non-stop only"):
                driver.findElement(numberOfStopsNoneOption).click();
                break;
            case ("1 stop or fewer"):
                driver.findElement(numberOfStopsOneStopOption).click();
                break;
            case ("2 stops or fewer"):
                driver.findElement(numberOfStopsTwoStopsOption).click();
                break;
            default:
                driver.findElement(numberOfStopsAnyOption).click();
                break;
        }
    }

    public void selectAirlines(List<String> listOfAirlines) {
        driver.findElement(airlinesButton).click();
        String isChecked = driver.findElement(selectAllAirlinesSwitch).getAttribute("aria-checked");
        if (isChecked.equals("true")) {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(airlinesBlock));
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            driver.findElement(selectAllAirlinesSwitch).click();
        }
        for (String airline : listOfAirlines) {
            driver.findElement(By.xpath("//input[@value='" + airline + "']")).click();
            /* There is bad approach to use thread.sleep(). I tried to replace it with
            Explicit wait but could not find solution which works.
             */
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sortResultsBySelectOption(String sortByOption) {
        driver.findElement(sortByButton).click();
        switch (sortByOption) {
            case ("Price"):
                driver.findElement(sortByPriceOption).click();
                break;
            case ("Departure time"):
                driver.findElement(sortByDepartureTimeOption).click();
                break;
            case ("Arrival time"):
                driver.findElement(sortByArrivalTimeOption).click();
                break;
            case ("Duration"):
                driver.findElement(sortByDurationOption).click();
                break;
            default:
                driver.findElement(sortByBestOption).click();
                break;
        }
    }

    public void clickShowMoreFlights() {
        while (!driver.findElements(showMoreFlights).isEmpty()) {
            driver.findElement(showMoreFlights).click();
        }
    }

    public void findExpensiveFlightAndProvideNecessaryInfo(String currency) {
        List<WebElement> tickets = driver.findElements(listOfTickets);
        int quantityOfElements = tickets.size();
        WebElement expensiveTicket = tickets.get(quantityOfElements - 2);
        String price = expensiveTicket.findElement(By.xpath(".//span[contains(@aria-label,'" + currency + "')]")).getText();
        System.out.println("Price: " + price);
        expensiveTicket.findElement(expandTicketDetailsButton).click();
        List<WebElement> flightNumbers = expensiveTicket.findElements(flightNumberElements);
        for (WebElement element : flightNumbers) {
            System.out.println("Flight number: " + element.getAttribute("innerText"));
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", expensiveTicket);
    }

    public void takeScreenshot() {
        Date date = new Date();
        String fileName = date.toString().replace(":", "_").replace(" ", "_") + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.moveFile(screenshot, new File("./target/screenshots/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
