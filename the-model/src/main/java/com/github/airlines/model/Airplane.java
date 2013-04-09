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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Company ownedBy) {
        this.ownedBy = ownedBy;
    }

    public DateTime getOwnedSince() {
        return ownedSince;
    }

    public void setOwnedSince(DateTime ownedSince) {
        this.ownedSince = ownedSince;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", ownedBy=" + ownedBy +
                ", ownedSince=" + ownedSince +
                ", mileage=" + mileage +
                ", passengerCapacity=" + passengerCapacity +
                '}';
    }
}
