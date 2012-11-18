package com.github.airlines.dao;

import com.github.airlines.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 11:44 PM
 */
public interface CompanyDao extends JpaRepository<Company, Integer> {
}
