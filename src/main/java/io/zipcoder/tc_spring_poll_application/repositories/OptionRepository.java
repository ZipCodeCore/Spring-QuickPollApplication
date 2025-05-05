package io.zipcoder.tc_spring_poll_application.repositories;

import io.zipcoder.tc_spring_poll_application.domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long>{
    //these are DAOs pr Data Access Objects they provide abstraction for interacting with data stores
    // ypu have usually one repository per domain object
}
