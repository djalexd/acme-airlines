package com.github.airlines.services;

import com.github.airlines.dao.AirportDao;
import com.github.airlines.dao.FlightDao;
import com.github.airlines.model.Airport;
import com.github.airlines.model.Flight;
import com.github.airlines.model.utils.AirportObjs;
import org.fest.assertions.api.Assertions;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 1:29 PM
 */
public class AirportServiceIT extends AbstractServiceIT {

    @Autowired
    AirportService airportService;

    @Autowired
    AirportDao airportDao;
    @Autowired
    FlightDao flightDao;

    @Test
    public void should_list_airports() {
        // when - create a couple of airports
        airportDao.save(AirportObjs.anAirport());
        airportDao.save(AirportObjs.aDifferentAirport());
        // then
        Page<Airport> airports = airportService.listAirports(new PageRequest(0, 5));
        // assert
        Assertions.assertThat(airports).hasSize(2);
    }

    @Test
    public void should_find_flights_from_airport_when_correct_day() {
        // when - create a couple of airports and a flights
        Airport from = airportDao.save(AirportObjs.anAirport());
        Airport to = airportDao.save(AirportObjs.aDifferentAirport());
        flightDao.save(new Flight(
                from, to,
                aDayIn2010(), Duration.standardMinutes(20)));
        // then - look for that flight in given day.
        Page<Flight> flights = airportService.listFlightsAirportFrom(
                from.getName(), new PageRequest(0, 5), aDayIn2010());
        // assert
        Assertions.assertThat(flights).hasSize(1);
    }

    @Test
    public void should_find_flights_to_airport_when_correct_day() {
        // when - create a couple of airports and a flights
        Airport from = airportDao.save(AirportObjs.anAirport());
        Airport to = airportDao.save(AirportObjs.aDifferentAirport());
        flightDao.save(new Flight(
                from, to,
                aDayIn2010(), Duration.standardMinutes(20)));
        // then - look for that flight in given day.
        Page<Flight> flights = airportService.listFlightsAirportTo(
                to.getName(), new PageRequest(0, 5), aDayIn2010());
        // assert
        Assertions.assertThat(flights).hasSize(1);
    }



    private DateTime aDayIn2010() {
        return DateTime.parse("15/02/2010", DateTimeFormat.forPattern("dd/MM/yyyy"));
    }
}
