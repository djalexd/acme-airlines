package com.github.airlines.model;

import org.hibernate.annotations.Type;
import org.joda.time.Duration;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Custom many to many relation between flights and airplanes.
 *
 * @author alex.dobjanschi
 * @since 11/18/12 5:16 PM
 */
public class FlightInfoHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @Column
    Airplane airplane;

    @Column
    Flight flight;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDuration")
    @Column
    Duration flightTimeInMinutes;
}
