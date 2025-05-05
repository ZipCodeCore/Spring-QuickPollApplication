package io.zipcoder.tc_spring_poll_application.utils;

import io.zipcoder.tc_spring_poll_application.domain.Option;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.utils
 * author: https://github.com/vvmk
 * date: 4/6/18
 */
public class ResultCalculatorTest {

    private ResultCalculator rc = new ResultCalculator();
    private Option o1 = new Option();
    private Option o2 = new Option();

    @Before
    public void setup() {
        o1.setId(1L);
        o1.setValue("Kirk");

        o2.setId(2L);
        o2.setValue("Picard");
    }

    @Test
    public void addShouldCountOptions() {
        int expected = rc.size() + 2;

        rc.add(o1);
        rc.add(o2);

        int actual = rc.size();

        assertEquals(expected, actual);
    }

    @Test
    public void addShouldCountDuplicateVotes() {
        int expected = rc.size() + 3;

        rc.add(o1);
        rc.add(o2);
        rc.add(o1);

        int actual = rc.size();

        assertEquals(expected, actual);
    }

    @Test
    public void addShouldTallyVotes() {
        int expected = 3;

        rc.add(o1);
        rc.add(o1);
        rc.add(o1);

        rc.add(o2);
        rc.add(o2);

        int actual = rc.getOptionCountById(o1.getId()).getCount();

        assertEquals(expected, actual);
    }
}