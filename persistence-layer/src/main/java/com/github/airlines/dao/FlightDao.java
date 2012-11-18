package com.github.airlines.dao;

import com.github.airlines.model.Airport;
import com.github.airlines.model.Flight;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author alex.dobjanschi
 * @since 11/17/12 11:46 PM
 */
@Repository
@Transactional
public interface FlightDao extends JpaRepository<Flight, Integer> {

    @Query(name = "findFlightsFromToAirports")
    @Transactional(readOnly = true)
    Page<Flight> findFlightsFromToAirports(
            Pageable paged, Airport fromAirport, Airport toAirport,
            DateTime startingWithDate, DateTime endingToDate);
}
