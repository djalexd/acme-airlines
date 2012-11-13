package com.github.airlines.dao;

import com.github.airlines.model.Airport;
import com.github.airlines.model.DetailedPosition;
import org.fest.assertions.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author alex.dobjanschi
 * @since 11/11/12 9:24 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-entityManager.xml", "classpath:daos.xml"
})
/*
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
*/
@Transactional(value = "txManager")
public class AirportDaoTest {

    @Autowired
    AirportDao airportDao;

/*
    @Before
    public void setupAirportDao() throws Exception {
        airportDao = new AirportDaoJPA(factory.createEntityManager());
    }

    @After
    public void tearDownAirportDao() {
        airportDao = null;
    }
*/

    @Ignore
    @Test(expected = NullPointerException.class)
    public void should_throw_NPE_for_null_name() {
        // when
        // then
        airportDao.findByName(null);
        // assert - built-in
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void should_throw_illegalArgumentException_for_empty_name() {
        // when
        // then
        airportDao.findByName("");
        // assert - built-in
    }


    @Test
    public void should_find_airport_by_name_when_valid() {
        // when
        final Airport airport = new Airport("Some awesome airport", somePosition());
        airportDao.save(airport);

        // then
        final Airport found = airportDao.findByName("Some awesome airport");
        // assert
        // TODO
    }

    @Test
    public void should_find_airport_in_range() {
        // when
        airportDao.save(new Airport("Fiji", fijiPosition()));
        // then
        List<Airport> airports = airportDao.findAirportsInRange(
                fijiPosition().getLatitude(),
                fijiPosition().getLongitude(), 2.0);
        // assert
        Assertions.assertThat(airports).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    public void should_not_find_airport_when_looking_out_of_range() {
        // when
        airportDao.save(new Airport("Fiji", fijiPosition()));
        // then
        List<Airport> airports = airportDao.findAirportsInRange(BigDecimal.ZERO, BigDecimal.ONE, 7.0);
        // assert
        Assertions.assertThat(airports).isNotNull().isEmpty();
    }




    private DetailedPosition somePosition() {
        return new DetailedPosition(BigDecimal.valueOf(15), BigDecimal.valueOf(37));
    }

    private DetailedPosition fijiPosition() {
        return new DetailedPosition(BigDecimal.valueOf(10), BigDecimal.valueOf(25.0));
    }


}
