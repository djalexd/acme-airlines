package com.github.airlines.services;

import com.github.airlines.dao.AirportDao;
import com.github.airlines.model.Airport;
import com.github.airlines.model.exceptions.NoAirportFoundException;
import com.github.airlines.model.utils.AirportObjs;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.*;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 1:28 PM
 */
public class AirportServiceTest {

    AirportService service;

    AirportDao airportDao;

    @Before
    public void setupMockAirportDao() {
        // Provide a base mock that any tests can setup later.
        airportDao = mock(AirportDao.class);
        // and hook the service - no transactional aspects here, because
        // we're overriding bean construction
        service = new DefaultAirportService(airportDao);
    }

    @After
    public void tearDownServiceAndDao() {
        // Clean up service and dao mock after each test.
        service = null;
        airportDao = null;
    }


    @Test
    public void should_call_dao_listAirports() {
        // when
        // then - call list airports
        service.listAirports(new PageRequest(0, 10));
        // assert - dao was called with same arguments
        verify(airportDao, times(1)).findAll(
            // The argument must match, and the function must be called only once;
            eq(new PageRequest(0, 10))
        );
    }

    @Test(expected = NoAirportFoundException.class)
    public void should_throw_airportNotFoundException_when_invalid_name_while_searching_listFrom() {
        // when
        when(airportDao.findByName(anyString())).thenThrow(new EmptyResultDataAccessException(1));
        // then
        service.listFlightsAirportFrom("no_airport", mock(Pageable.class), DateTime.now());
        // assert - implicit
    }

    @Test(expected = NoAirportFoundException.class)
    public void should_throw_airportNotFoundException_when_invalid_name_while_searching_listTo() {
        // when
        when(airportDao.findByName(anyString())).thenThrow(new EmptyResultDataAccessException(1));
        // then
        service.listFlightsAirportTo("no_airport", mock(Pageable.class), DateTime.now());
        // assert - implicit
    }

    @Test
    public void should_call_dao_with_startOfDay_and_endOfDay_when_listFrom() {
        // when - create an airport
        when(airportDao.findByName("airport")).thenReturn(AirportObjs.anAirport());
        // then
        service.listFlightsAirportFrom("airport", mock(Pageable.class), DateTime.now());
        // assert - verify that right parameters were used
        verify(airportDao, times(1)).findFlightsFromThisAirportOn(
                Mockito.<Pageable>anyObject(),
                Mockito.<Airport>anyObject(),
                eq(DateTime.now().withTimeAtStartOfDay()),
                eq(DateTime.now().plusDays(1).withTimeAtStartOfDay()));
    }

    @Test
    public void should_call_dao_with_startOfDay_and_endOfDay_when_listTo() {
        // when - create an airport
        when(airportDao.findByName("airport")).thenReturn(AirportObjs.anAirport());
        // then
        service.listFlightsAirportTo("airport", mock(Pageable.class), DateTime.now());
        // assert - verify that right parameters were used
        verify(airportDao, times(1)).findFlightsToThisAirportOn(
                Mockito.<Pageable>anyObject(),
                Mockito.<Airport>anyObject(),
                eq(DateTime.now().withTimeAtStartOfDay()),
                eq(DateTime.now().plusDays(1).withTimeAtStartOfDay()));
    }
}
