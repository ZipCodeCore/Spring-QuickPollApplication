package io.zipcoder.tc_spring_poll_application.dtos;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import io.zipcoder.tc_spring_poll_application.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import java.util.ArrayList;

@RestController
public class ComputeResultController {
    @Autowired
    private VoteRepository voteRepository;

    private PollRepository pollRepository;


    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);
        io.zipcoder.tc_spring_poll_application.domain.Poll poll = pollRepository.findOne(pollId);
        voteResult.setResults(new java.util.ArrayList<OptionCount>());
        for (Option o : poll.getOptions()) {
            OptionCount optionCount = new OptionCount();
            optionCount.setOptionId(o.getId());
            optionCount.setCount(Math.toIntExact(((ArrayList<Vote>) allVotes).stream().filter(v -> v.getOption().equals(o)).count()));
            voteResult.getResults().add(optionCount);
        }

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }




}
