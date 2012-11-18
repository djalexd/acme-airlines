package com.github.airlines.services;

import com.github.airlines.dao.AirportDao;
import com.github.airlines.model.Airport;
import com.github.airlines.model.Flight;
import com.github.airlines.model.exceptions.NoAirportFoundException;
import com.google.common.base.Preconditions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 4:37 AM
 */
@Service
public class DefaultAirportService implements AirportService {
    private static final String NO_AIRPORT_WITH_NAME_MSG = "No airport with given name found '%s'";

    private final AirportDao airportDao;

    @Autowired
    public DefaultAirportService(AirportDao airportDao) {
        this.airportDao = Preconditions.checkNotNull(airportDao);
    }

    @Override
    public Page<Flight> listFlightsAirportTo(String airportName, Pageable paged, DateTime dayMonthYear) {
        Preconditions.checkNotNull(airportName, "Airport name must be supplied");
        Preconditions.checkArgument(StringUtils.hasText(airportName), "Airport name must be specified");

        final Airport airport = findAirportByName(airportName);

        // We need to adapt the given 'dayMonthYear' to 2 dates
        final DateTime startOfDay = dayMonthYear.withTimeAtStartOfDay();
        final DateTime endOfDay = dayMonthYear.plusDays(1).withTimeAtStartOfDay();

        return airportDao.findFlightsToThisAirportOn(paged, airport, startOfDay, endOfDay);
    }

    @Override
    public Page<Flight> listFlightsAirportFrom(String airportName, Pageable paged, DateTime dayMonthYear) {
        Preconditions.checkNotNull(airportName, "Airport name must be supplied");
        Preconditions.checkArgument(StringUtils.hasText(airportName), "Airport name must be specified");

        final Airport airport = findAirportByName(airportName);

        // We need to adapt the given 'dayMonthYear' to 2 dates
        final DateTime startOfDay = dayMonthYear.withTimeAtStartOfDay();
        final DateTime endOfDay = dayMonthYear.plusDays(1).withTimeAtStartOfDay();

        return airportDao.findFlightsFromThisAirportOn(paged, airport, startOfDay, endOfDay);
    }

    private Airport findAirportByName(String airportName) {
        final Airport airport;
        try {
            airport = airportDao.findByName(airportName);
        } catch (EmptyResultDataAccessException e) {
            throw new NoAirportFoundException(String.format(NO_AIRPORT_WITH_NAME_MSG, airportName));
        }
        return airport;
    }

    @Override
    public Page<Airport> listAirports(Pageable pageable) {
        return airportDao.findAll(pageable);
    }
}
