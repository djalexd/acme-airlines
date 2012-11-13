package com.github.airlines.dao;

import com.github.airlines.model.Airport;
import com.github.airlines.model.DetailedPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    @Query("select a from Airport a where name = :airportName")
    @Transactional(readOnly = true)
    Airport findByName(@Param("airportName") String name);


    /**
     * Finds all airports in a given circle - by specifying the circle position and range.
     * @param range
     * @return
     */
    @Query("select a from Airport a where (a.position.latitude - ?1) * (a.position.latitude - ?1) + (a.position.longitude - ?2) * (a.position.longitude - ?2) < (?3 * ?3)")
    @Transactional(readOnly = true)
    List<Airport> findAirportsInRange(BigDecimal latitude, BigDecimal longitude, Double range);
}
