package com.github.airlines.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 5:26 PM
 */
@Entity
@Table(name = "airplane_own_history")
public class AirplaneOwningHistory {

    @SequenceGenerator(name = "sequence_airplane_history", allocationSize = 8)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Integer id;

    @ManyToOne
    Airplane airplane;

    @ManyToOne
    Company company;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column
    DateTime ownedFrom;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column
    DateTime ownedTo;

    public AirplaneOwningHistory() {}
    public AirplaneOwningHistory(Airplane airplane, Company company, DateTime ownedFrom, DateTime ownedTo) {
        this.airplane = airplane;
        this.company = company;
        this.ownedFrom = ownedFrom;
        this.ownedTo = ownedTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DateTime getOwnedFrom() {
        return ownedFrom;
    }

    public void setOwnedFrom(DateTime ownedFrom) {
        this.ownedFrom = ownedFrom;
    }

    public DateTime getOwnedTo() {
        return ownedTo;
    }

    public void setOwnedTo(DateTime ownedTo) {
        this.ownedTo = ownedTo;
    }
}
