package com.github.airlines.dao;

import com.github.airlines.model.Airplane;
import com.github.airlines.model.Airport;
import com.github.airlines.model.Company;
import com.github.airlines.model.Flight;
import org.fest.assertions.api.Assertions;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import static com.github.airlines.model.utils.AirportObjs.*;
import static com.github.airlines.model.utils.AirplaneObjs.*;
import static com.github.airlines.model.utils.CompanyObjs.*;

/**
 * @author alex.dobjanschi
 * @since 11/17/12 11:49 PM
 */
public class FlightDaoTest extends BaseDaoTest {

    @Autowired
    AirportDao airportDao;

    @Autowired
    FlightDao flightDao;

    @Autowired
    AirplaneDao airplaneDao;
    @Autowired
    CompanyDao companyDao;

    private Airport from;
    private Airport to;

    private Company company;
    private Airplane airplane;

    @BeforeTransaction
    public void setupAirportsBeforeTransaction() {
        company = companyDao.save(aCompany());
        airplane = airplaneDao.save(anAirplane(company));

        // create two airports.
        from = airportDao.save(anAirport());
        to = airportDao.save(anAirport());
    }

    @AfterTransaction
    public void tearDownAirportsAfterTransaction() {
        // Create two airports
        airportDao.delete(from);
        airportDao.delete(to);

        airplaneDao.delete(airplane);
        companyDao.delete(company);
    }


    @Test
    public void should_be_able_to_add_a_flight() {
        // when - implicit setup
        // then - create a flights between these airports
        Flight flight = createAFlight();
        // assert - the entity was saved
        Assertions.assertThat(flight).isNotNull();
        Assertions.assertThat(flightDao.findOne(flight.getId())).isNotNull();
    }

    @Test
    public void should_find_flights_for_valid_whenTo() {
        // when - create a flight
        createAFlight();
        // then
        Page<Flight> flights = flightDao.findFlightsFromToAirports(
                new PageRequest(0, 1),
                from, to, DateTime.now().minusHours(1), DateTime.now().plusHours(1));
        // assert
        Assertions.assertThat(flights).isNotEmpty().hasSize(1);
    }

    @Test
    public void should_not_find_flight_when_invalid_from() {
        // when - create a flight
        createAFlight();
        Airport fromAnother = airportDao.save(anAirport());
        // then
        Page<Flight> flights = flightDao.findFlightsFromToAirports(
                new PageRequest(0, 1),
                fromAnother, to, DateTime.now().minusHours(1), DateTime.now().plusHours(1));
        // assert
        Assertions.assertThat(flights).isEmpty();
    }

    @Test
    public void should_not_find_flight_when_invalid_to() {
        // when - create a flight
        createAFlight();
        Airport toAnother = airportDao.save(anAirport());
        // then
        Page<Flight> flights = flightDao.findFlightsFromToAirports(
                new PageRequest(0, 1),
                from, toAnother, DateTime.now().minusHours(1), DateTime.now().plusHours(1));
        // assert
        Assertions.assertThat(flights).isEmpty();
    }

    @Test
    public void should_not_find_flight_when_invalid_start() {
        // when - create a flight
        createAFlight();
        // then
        Page<Flight> flights = flightDao.findFlightsFromToAirports(
                new PageRequest(0, 1),
                from, to, DateTime.now().plusMinutes(10), DateTime.now().plusHours(1));
        // assert
        Assertions.assertThat(flights).isEmpty();
    }

    @Test
    public void should_not_find_flight_when_invalid_end() {
        // when - create a flight
        createAFlight();
        // then
        Page<Flight> flights = flightDao.findFlightsFromToAirports(
                new PageRequest(0, 1),
                from, to, DateTime.now().minusHours(1), DateTime.now().minusHours(10));
        // assert
        Assertions.assertThat(flights).isEmpty();
    }

    private Flight createAFlight() {
        return flightDao.save(new Flight(airplane, from, to, DateTime.now(), Duration.standardMinutes(60)));
    }
}
