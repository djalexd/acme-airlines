package com.github.airlines.dao;

import com.github.airlines.model.FlightInfoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alex.dobjanschi
 * @since 11/21/12 12:55 AM
 */
public interface FlightInfoHistoryDao extends JpaRepository<FlightInfoHistory, Integer> {
}
