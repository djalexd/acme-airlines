package com.github.airlines.services;

import com.github.airlines.dao.AirplaneDao;
import com.github.airlines.dao.AirplaneOwningHistoryDao;
import com.github.airlines.model.Airplane;
import com.github.airlines.model.AirplaneOwningHistory;
import com.github.airlines.model.Company;
import com.github.airlines.model.exceptions.NoAirplaneFoundException;
import com.google.common.base.Preconditions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author alex.dobjanschi
 * @since 11/21/12 12:56 AM
 */
@Service
public class DefaultCompanyService implements CompanyService {

    private final AirplaneDao airplaneDao;
    private final AirplaneOwningHistoryDao airplaneOwningHistoryDao;

    @Autowired
    public DefaultCompanyService(AirplaneDao airplaneDao, AirplaneOwningHistoryDao airplaneOwningHistoryDao) {
        this.airplaneDao = Preconditions.checkNotNull(airplaneDao);
        this.airplaneOwningHistoryDao = Preconditions.checkNotNull(airplaneOwningHistoryDao);
    }

    @Override
    public Airplane buyAnAirplane(Integer airplaneId, Company byCompany, DateTime since)
        throws NoAirplaneFoundException {

        Airplane airplane = airplaneDao.findOne(airplaneId);
        if (null == airplane) {
            throw new NoAirplaneFoundException("Unknown Airplane identified by id " + airplaneId);
        }

        // Update the old reference
        final AirplaneOwningHistory lastLog = airplaneOwningHistoryDao.findLastAirplaneOwningLog(airplane);
        if (lastLog != null) {

            // TODO Validate since > lastLog.ownedFrom
            lastLog.setOwnedTo(since);
            airplaneOwningHistoryDao.save(lastLog);

        } else {
            // There is no history yet - don't do anything.
        }

        // Now push a new value of AirplaneOwningHistory
        AirplaneOwningHistory lastOwningLog = new AirplaneOwningHistory(airplane, byCompany, since, null);
        airplaneOwningHistoryDao.save(lastOwningLog);

        airplane.setOwnedBy(byCompany);
        airplane.setOwnedSince(since);
        return airplaneDao.save(airplane);
    }
}
