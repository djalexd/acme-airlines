package com.github.airlines.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Package protected base class, used for tests.
 *
 * @author alex.dobjanschi
 * @since 11/17/12 11:49 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-entityManager.xml", "classpath:daos.xml"
})
@Transactional(value = "txManager")
abstract class BaseDaoTest {


}
