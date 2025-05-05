package io.zipcoder.tc_spring_poll_application.repositories;
import org.springframework.data.repository.CrudRepository;
import io.zipcoder.tc_spring_poll_application.domain.Poll;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long>{
}