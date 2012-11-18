package com.github.airlines.model.utils;

import com.github.airlines.model.Airplane;
import com.github.airlines.model.Company;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 11:40 PM
 */
public class AirplaneObjs {

    public static Airplane anAirplane(Company owningCompany) {
        return new Airplane(owningCompany, DateTime.now(), BigDecimal.ONE, 110);
    }
}
