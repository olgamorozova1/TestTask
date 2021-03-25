package com.coherentsolutions.task.page.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FlightsStartPage {
    private WebDriver driver;

    private By tripTypeButton = By.xpath("//button[contains(@aria-label,'Ticket type')]");
    private By tripTypeRoundTripOption = By.xpath("(//ul[@aria-label='Select your ticket type.']/li[@data-value='1'])[1]");
    private By tripTypeOneWayOption = By.xpath("(//ul[@aria-label='Select your ticket type.']/li[@data-value='2'])[1]");
    private By tripTypeMultiCityOption = By.xpath("(//ul[@aria-label='Select your ticket type.']/li[@data-value='3'])[1]");
    private By destinationFromField = By.xpath("//input[@aria-labelledby='i7']");
    private By getDestinationFromFieldAfterClick = By.xpath("//input[@aria-describedby='i8']");
    private By destinationFromOptionToSelect = By.xpath("//li[@aria-label='San Francisco, California, USA']");
    private By destinationToField = By.xpath("(//input)[3]");
    private By destinationToFieldAfterClick = By.xpath("//input[@aria-describedby='i11']");
    private By destinationToOptionToSelect = By.xpath("//li[@aria-label='New York, USA']//div[contains(text(), 'City in New York State')]");
    private By dateFromField = By.xpath("(//input)[5]");
    private By dateToField = By.xpath("(//input)[6]");
    private By searchButton = By.xpath("//button[@jsname='vLv7Lb']");
    private By passengersCountButton = By.xpath("//button[contains(@aria-label,'passenger')]");
    private By numberOfAdultPassengers = By.xpath("//div[contains(@aria-label,'Number of adult passengers')]");
    private By addAdultPassengerButton = By.xpath("//button[@aria-label='Add adult']");
    private By doneButton = By.xpath("//button[@class='VfPpkd-LgbsSe ksBjEc lKxP2d bRx3h']/preceding-sibling::button");
    private By seatingClassButton = By.xpath("//button[contains(@aria-label,'Seating class')]");
    private By seatingClassPremiumEconomyOption = By.xpath("//ul[@aria-label='Select your preferred seating class.']/li[@data-value=\"2\"]");
    private By seatingClassBusinessOption = By.xpath("//ul[@aria-label='Select your preferred seating class.']/li[@data-value=\"3\"]");
    private By seatingClassFirstOption = By.xpath("//ul[@aria-label='Select your preferred seating class.']/li[@data-value=\"4\"]");
    private By seatingClassDefaultOption = By.xpath("//ul[@aria-label='Select your preferred seating class.']/li[@data-value=\"1\"]");

    public FlightsStartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectTripType(String tripType) {
        driver.findElement(tripTypeButton).click();
        switch (tripType) {
            case ("One way"):
                driver.findElement(tripTypeOneWayOption).click();
                break;
            case ("Multi-city"):
                driver.findElement(tripTypeMultiCityOption).click();
                break;
            default:
                driver.findElement(tripTypeRoundTripOption).click();
                break;
        }
    }

    public void selectDestination() {
        driver.findElement(destinationFromField).click();
        driver.findElement(getDestinationFromFieldAfterClick).sendKeys(Keys.CONTROL + "a");
        driver.findElement(getDestinationFromFieldAfterClick).sendKeys(Keys.DELETE);
        ;
        driver.findElement(getDestinationFromFieldAfterClick).sendKeys("San Francisco");
        driver.findElement(destinationFromOptionToSelect).click();

        driver.findElement(destinationToField).click();
        driver.findElement(destinationToFieldAfterClick).clear();
        driver.findElement(destinationToFieldAfterClick).sendKeys("New York");
        driver.findElement(destinationToOptionToSelect).click();
    }

    public void selectDates(String departureDate, String returnDate) {
        driver.findElement(dateFromField).sendKeys(departureDate);
        driver.findElement(dateToField).sendKeys(returnDate);
    }

    public void choosePassengers(int desiredNumberOfPassengers) {
        driver.findElement(passengersCountButton).click();
        String numberOfPassengersString = driver.findElement(numberOfAdultPassengers).getAttribute("aria-valuenow");
        int numberOfPassengersInt = Integer.parseInt(numberOfPassengersString);
        if (numberOfPassengersInt != desiredNumberOfPassengers) {
            for (int i = 1; i <= desiredNumberOfPassengers; i++) {
                driver.findElement(addAdultPassengerButton).click();
            }
        }
        driver.findElement(doneButton).click();
    }

    public void chooseClass(String classType) {
        driver.findElement(seatingClassButton).click();
        switch (classType) {
            case ("Premium economy"):
                driver.findElement(seatingClassPremiumEconomyOption).click();
                break;
            case ("Business"):
                driver.findElement(seatingClassBusinessOption).click();
                break;
            case ("First"):
                driver.findElement(seatingClassFirstOption).click();
                break;
            default:
                driver.findElement(seatingClassDefaultOption).click();
                break;
        }
    }

    public void pressSearchButton() {
        driver.findElement(searchButton).click();
    }
}
