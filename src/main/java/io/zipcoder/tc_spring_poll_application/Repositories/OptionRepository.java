package io.zipcoder.tc_spring_poll_application.Repositories;

import io.zipcoder.tc_spring_poll_application.Domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
