package io.zipcoder.tc_spring_poll_application.Repositories;

import com.sun.xml.internal.bind.v2.model.core.ID;
import io.zipcoder.tc_spring_poll_application.Domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
