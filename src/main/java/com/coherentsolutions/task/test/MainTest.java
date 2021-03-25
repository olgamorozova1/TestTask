package com.coherentsolutions.task.test;

import com.coherentsolutions.task.page.pages.FlightDetailsPage;
import com.coherentsolutions.task.page.pages.FlightsStartPage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MainTest extends BaseTest {

    @Test
    void testTask() {
        FlightsStartPage flightsStartPage = new FlightsStartPage(driver);

        flightsStartPage.selectTripType("Round trip");
        flightsStartPage.choosePassengers(1);
        flightsStartPage.chooseClass("Economy");
        flightsStartPage.selectDestination();
        flightsStartPage.selectDates("05.20.2021", "06.25.2021");
        flightsStartPage.pressSearchButton();

        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectNumberOfStops("1 stop or fewer");
        List<String> listOfAirlines = new ArrayList<>();
        listOfAirlines.add("American");
        listOfAirlines.add("Delta");
        flightDetailsPage.selectAirlines(listOfAirlines);
        flightDetailsPage.sortResultsBySelectOption("Price");
        flightDetailsPage.clickShowMoreFlights();
//      For correct please provide default value of currency in your browser, for example: "US Dollar"
        flightDetailsPage.findExpensiveFlightAndProvideNecessaryInfo("Belarusian rubles");
        flightDetailsPage.takeScreenshot();
    }
}
