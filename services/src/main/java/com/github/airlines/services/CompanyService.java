package com.github.airlines.services;

import com.github.airlines.model.Airplane;
import com.github.airlines.model.Company;
import com.github.airlines.model.exceptions.NoAirplaneFoundException;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alex.dobjanschi
 * @since 11/21/12 12:56 AM
 */
@Transactional
public interface CompanyService {

    Airplane buyAnAirplane(Integer airplaneId, Company byCompany, DateTime since)
        throws NoAirplaneFoundException;
}
