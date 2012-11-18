package com.github.airlines.model.utils;

import com.github.airlines.model.Airport;
import com.github.airlines.model.DetailedPosition;

import java.math.BigDecimal;

/**
 *
 * @author alex.dobjanschi
 * @since 11/17/12 11:54 PM
 */
public class AirportObjs {

    public static Airport anAirport() {
        return new Airport("Mars", someStaticLocation());
    }

    public static Airport aDifferentAirport() {
        return new Airport("Venus", someRandomLocation());
    }


    private static DetailedPosition someStaticLocation() {
        return new DetailedPosition(BigDecimal.ONE, BigDecimal.valueOf(15));
    }

    private static DetailedPosition someRandomLocation() {
        return new DetailedPosition(
                BigDecimal.valueOf(Math.random() * 1024),
                BigDecimal.valueOf(Math.random() * 1024));
    }
}
