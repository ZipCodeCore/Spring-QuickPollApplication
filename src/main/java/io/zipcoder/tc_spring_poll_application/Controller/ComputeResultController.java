package io.zipcoder.tc_spring_poll_application.Controller;

import dtos.OptionCount;
import dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
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
        OptionCount optionCount;
        Integer voteCount = 0;
        Map<Long, OptionCount> todos = new HashMap<>();
        for(Vote i : allVotes){
            voteCount++;

            optionCount = todos.get(i.getOption().getId());
            if (optionCount == null){
                optionCount = new OptionCount();
                optionCount.setOptionId(i.getOption().getId());
                todos.put(i.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(voteCount);
        voteResult.setResults(todos.values());
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

}
