package io.zipcoder.tc_spring_poll_application.Controller;

import dtos.OptionCount;
import dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.Repositories.VoteRepository;
import io.zipcoder.tc_spring_poll_application.domain.Option;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

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
        //now we are creating a map of option id and total vote count
        Map<Long, Integer> map = new HashMap<>();
        Long optionId;
        for (Vote vote : allVotes) {
            optionId = vote.getOption().getId();
            int updateCount = 0;
            if (!map.containsKey(optionId))
                map.put(optionId, 1);
            else {
                updateCount = map.get(optionId) + 1;
                map.put(optionId, updateCount);
            }
        }
        Set<OptionCount> optionCounts = new HashSet<>();
        for (Long id : map.keySet()) {
            optionCounts.add(new OptionCount(id, map.get(id)));
        }
        voteResult.setResults(optionCounts);
        voteResult.setTotalVotes(voteResult.getTotalVotes());




    //TODO: Implement algorithm to count votes
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}