package com.github.airlines.services;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base abstract service infrastructure, used for tests.
 *
 * <p>Database objects persisted throughout tests are automatically rolled-back
 * at the end of test!</p>
 *
 * @author alex.dobjanschi
 * @since 11/18/12 2:10 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/services.xml"
})
@Transactional(value = "txManager")
abstract class AbstractServiceIT {
}
