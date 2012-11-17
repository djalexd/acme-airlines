package com.github.airlines.dao;

import com.github.airlines.model.Airport;
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
    Airport byName(@Param("airportName") String name);

    /**
     * Finds all airports in a given circle - by specifying the circle position and range.
     * @param latitude
     * @param longitude
     * @param range
     * @return
     */
    @Transactional(readOnly = true)
    @Query(name = "findAirportsInRange")
    List<Airport> determineAirportsInRange(BigDecimal latitude, BigDecimal longitude, Double range);
}
