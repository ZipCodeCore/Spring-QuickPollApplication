package io.zipcoder.tc_spring_poll_application.controllers;

import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import io.zipcoder.tc_spring_poll_application.utils.ResultCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.controllers
 * author: https://github.com/vvmk
 * date: 4/6/18
 */
@RestController
public class ComputeResultController {

    private VoteRepository voteRepository;

    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);
        VoteResult voteResult = computeResults(allVotes);

        return new ResponseEntity<>(voteResult, HttpStatus.OK);
    }

    private VoteResult computeResults(Iterable<Vote> votes) {
        Iterator<Vote> voterator = votes.iterator();

        ResultCalculator rc = new ResultCalculator();
        while (voterator.hasNext()) {
            Vote v = voterator.next();
            rc.add(v.getOption());
        }
        return rc.calculate();
    }
}
