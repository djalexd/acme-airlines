package com.github.airlines.services;

import com.github.airlines.model.Airport;
import com.github.airlines.model.Flight;
import com.github.airlines.model.exceptions.NoAirportFoundException;
import com.google.common.base.Optional;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author alex.dobjanschi
 * @since 11/17/12 11:35 PM
 */
@Transactional
public interface AirportService {

    /**
     *
     * @param airportName
     * @param paged
     * @param dayMonthYear
     * @return
     * @throws NoAirportFoundException thrown if no airport with given name is found.
     */
    Page<Flight> listFlightsAirportTo(String airportName, Pageable paged, DateTime dayMonthYear)
        throws NoAirportFoundException;

    /**
     *
     * @param airportName
     * @param paged
     * @param dayMonthYear
     * @return
     * @throws NoAirportFoundException thrown if no airport with given name is found.
     */
    Page<Flight> listFlightsAirportFrom(String airportName, Pageable paged, DateTime dayMonthYear)
        throws NoAirportFoundException;

    Page<Airport> listAirports(Pageable pageable);
}
