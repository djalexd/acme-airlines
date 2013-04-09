package com.github.airlines.dao;

import com.github.airlines.model.Airplane;
import com.github.airlines.model.AirplaneOwningHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author alex.dobjanschi
 * @since 11/21/12 12:54 AM
 */
public interface AirplaneOwningHistoryDao extends JpaRepository<AirplaneOwningHistory, Integer> {

    @Query(name = "findLastAirplaneOwningLog")
    AirplaneOwningHistory findLastAirplaneOwningLog(Airplane airplane);
}
