package io.zipcoder.tc_spring_poll_application.Controller;

import Dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.Domain.Vote;
import io.zipcoder.tc_spring_poll_application.Repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;

@Entity
public class ComputeResultController {
    private VoteRepository voteRepository;

    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);

        //TODO: Implement algorithm to count votes
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

}
