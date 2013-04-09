package com.github.airlines.dao;

import com.github.airlines.model.Airplane;
import com.github.airlines.model.AirplaneOwningHistory;
import com.github.airlines.model.Company;
import org.fest.assertions.api.Assertions;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.math.BigDecimal;

import static com.github.airlines.model.utils.CompanyObjs.*;

/**
 * @author alex.dobjanschi
 * @since 11/25/12 10:10 PM
 */
public class AirplaneOwningHistoryDaoTest extends BaseDaoTest {
    @Autowired
    AirplaneOwningHistoryDao airplaneOwningHistoryDao;
    @Autowired
    AirplaneDao airplaneDao;
    @Autowired
    CompanyDao companyDao;

    // Create an airplane before the transaction
    Airplane airplaneUsedForTest;
    // Create a company
    Company companyForTest;

    @BeforeTransaction
    public void createAirplaneBeforeTransaction() {
        companyForTest = companyDao.save(aCompany());
        airplaneUsedForTest = airplaneDao.save(new Airplane(companyForTest, DateTime.now(), BigDecimal.ZERO, 20));
    }

    @AfterTransaction
    public void tearDownAirplaneAfterTransaction() {
        airplaneDao.delete(airplaneUsedForTest);
        companyDao.delete(companyForTest);
    }

    @Test
    public void should_return_null_for_no_history() {
        // when
        // then
        AirplaneOwningHistory log = airplaneOwningHistoryDao.findLastAirplaneOwningLog(airplaneUsedForTest);
        // assert
        Assertions.assertThat(log).isNull();
    }


    @Test
    public void should_return_the_entry_for_a_single_history_item() {
        // when
        airplaneOwningHistoryDao.save(aLogEntry(DateTime.now().minusDays(1), DateTime.now()));
        // then
        AirplaneOwningHistory log = airplaneOwningHistoryDao.findLastAirplaneOwningLog(airplaneUsedForTest);
        // assert
        Assertions.assertThat(log).isNotNull();
    }


    @Test
    public void should_return_the_last_entry_for_multiple_history_items() {
        // when
        DateTime time1 = DateTime.now().minusDays(20);
        DateTime time2 = DateTime.now().minusDays(10);
        DateTime time3 = DateTime.now().minusDays(1);
        AirplaneOwningHistory log1 = airplaneOwningHistoryDao.save(aLogEntry(time1, time2));
        AirplaneOwningHistory log2 = airplaneOwningHistoryDao.save(aLogEntry(time2, time3));
        AirplaneOwningHistory log3 = airplaneOwningHistoryDao.save(aLogEntry(time3, DateTime.now()));
        // then
        AirplaneOwningHistory log = airplaneOwningHistoryDao.findLastAirplaneOwningLog(airplaneUsedForTest);
        // assert
        Assertions.assertThat(log).isNotNull();
        Assertions.assertThat(log.getId())
                .isEqualTo(log3.getId())
                .isNotEqualTo(log1.getId())
                .isNotEqualTo(log2.getId());
    }

    private AirplaneOwningHistory aLogEntry(DateTime from, DateTime to) {
        return new AirplaneOwningHistory(airplaneUsedForTest, companyForTest, from, to);
    }


}
