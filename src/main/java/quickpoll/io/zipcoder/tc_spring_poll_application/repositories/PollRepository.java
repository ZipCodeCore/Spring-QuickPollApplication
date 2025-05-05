package quickpoll.io.zipcoder.tc_spring_poll_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import quickpoll.io.zipcoder.tc_spring_poll_application.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {
}
