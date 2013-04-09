package com.github.airlines.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.annotation.Generated;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 5:17 PM
 */
@Entity
@Table(name = "airplanes")
public class Airplane {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owned_by_company_id")
    Company ownedBy;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(nullable = false)
    DateTime ownedSince;

    @Column
    BigDecimal mileage;

    @Column(nullable = false)
    Integer passengerCapacity;

    public Airplane() {}
    public Airplane(Company ownedBy, DateTime ownedSince, BigDecimal mileage, Integer passengerCapacity) {
        this.ownedBy = ownedBy;
        this.ownedSince = ownedSince;
        this.mileage = mileage;
        this.passengerCapacity = passengerCapacity;
    }
}
