package com.github.airlines.model;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.persistence.*;
import org.hibernate.annotations.Type;


/**
 * @author alex.dobjanschi
 * @since 11/17/12 11:38 PM
 */
@Entity
@Table(name = "flights")
public class Flight {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Integer id;

    @ManyToOne(targetEntity = Airport.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fromAirportId")
    Airport fromAirport;

    @ManyToOne(targetEntity = Airport.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "toAirportId")
    Airport toAirport;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column
    DateTime fromTimeGMT;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDuration")
    @Column
    Duration durationInMinutes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "airplaneId")
    Airplane airplane;

    public Flight() {}
    public Flight(Integer id, Airplane airplane, Airport fromAirport, Airport toAirport, DateTime fromTimeGMT, Duration durationInMinutes) {
        this.id = id;
        this.airplane = airplane;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.fromTimeGMT = fromTimeGMT;
        this.durationInMinutes = durationInMinutes;
    }

    public Flight(Airplane airplane, Airport fromAirport, Airport toAirport, DateTime fromTimeGMT, Duration durationInMinutes) {
        this.airplane = airplane;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.fromTimeGMT = fromTimeGMT;
        this.durationInMinutes = durationInMinutes;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public DateTime getFromTimeGMT() {
        return fromTimeGMT;
    }

    public void setFromTimeGMT(DateTime fromTimeGMT) {
        this.fromTimeGMT = fromTimeGMT;
    }

    public Duration getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Duration durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airplane=" + airplane +
                ", fromAirport=" + fromAirport +
                ", toAirport=" + toAirport +
                ", fromTimeGMT=" + fromTimeGMT +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
