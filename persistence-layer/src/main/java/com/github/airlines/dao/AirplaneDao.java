package com.github.airlines.dao;

import com.github.airlines.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alex.dobjanschi
 * @since 11/18/12 11:39 PM
 */
@Repository
public interface AirplaneDao extends JpaRepository<Airplane, Integer> {

}
