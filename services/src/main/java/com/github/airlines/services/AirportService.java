package com.github.airlines.services;

import com.github.airlines.model.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alex.dobjanschi
 * @since 11/17/12 11:35 PM
 */
@Transactional
public interface AirportService {

    //Page<Airport> listAirports(Pageable pageable);

}
