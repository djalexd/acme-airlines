package com.github.airlines.dao;

import com.github.airlines.model.Airport;
import com.github.airlines.model.Flight;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author alex.dobjanschi
 * @since 11/11/12 9:16 PM
 */
@Repository
@Transactional
public interface AirportDao extends JpaRepository<Airport, Integer> {

    /**
     * Finds an airport by name.
     * @param name
     * @return
     * @throws NullPointerException if given name if <code>null</code>.
     * @throws IllegalArgumentException if given name is <code>empty</code>.
     */
    @Transactional(readOnly = true)
    @Query(name = "findAirportByName")
    Airport findByName(@Param("airportName") String name);

    /**
     * Finds all airports in a given circle - by specifying the circle position and range.
     * @param latitude
     * @param longitude
     * @param range
     * @return
     */
    @Transactional(readOnly = true)
    @Query(name = "findAirportsInRange")
    List<Airport> findAirportsInRange(BigDecimal latitude, BigDecimal longitude, Double range);


    /**
     * Find all flights that take off from this airport.
     * @param paged
     * @param airport
     * @param dayMonthYearStart
     * @param dayMonthYearEnd
     * @return
     */
    @Transactional(readOnly = true)
    @Query(name = "findFlightsFromThisAirportOn")
    Page<Flight> findFlightsFromThisAirportOn(Pageable paged, Airport airport, DateTime dayMonthYearStart, DateTime dayMonthYearEnd);

    /**
     * Find flights that land on this airport on a given day.
     * @param paged
     * @param airport
     * @param dayMonthYearStart
     * @param dayMonthYearEnd
     * @return
     */
    @Transactional(readOnly = true)
    @Query(name = "findFlightsToThisAirportOn")
    Page<Flight> findFlightsToThisAirportOn(Pageable paged, Airport airport, DateTime dayMonthYearStart, DateTime dayMonthYearEnd);
}
