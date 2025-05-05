package io.zipcoder.tc_spring_poll_application.repositories;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {
    //these are DAOs pr Data Access Objects they provide abstraction for interacting with data stores
    // ypu have usually one repository per domain object
}
