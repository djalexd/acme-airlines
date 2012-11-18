package com.github.airlines.model.utils;

import com.github.airlines.model.Company;
import org.joda.time.DateTime;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 11:41 PM
 */
public class CompanyObjs {

    public static Company aCompany() {
        return new Company("ACME", DateTime.now().withYear(2005));
    }
}
