package io.zipcoder.tc_spring_poll_application.controller;

import dtos.OptionCount;
import dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.domain.Option;
import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.OptionRepository;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ComputeResultController {
    private VoteRepository voteRepository;

    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/computeresult")
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        List <OptionCount> optionsCountList = new ArrayList<>();
        Map<Long, Long> optionCounts;
        Iterable<Vote> allVotes;

        allVotes = voteRepository.findVotesByPoll(pollId);
        optionCounts= StreamSupport.stream(allVotes.spliterator(), false)
                .collect(Collectors.groupingBy(v -> v.getOption().getId(), Collectors.counting()));
        ArrayList<Long> keys = new ArrayList<Long>(optionCounts.keySet());
        for (int i = 0; i < optionCounts.size(); i++) {
             optionsCountList.add(new OptionCount(keys.get(i), optionCounts.get(keys.get(i)).intValue()));
        }

        VoteResult voteResult = new VoteResult(countAllVotes(allVotes), optionsCountList);
            //TODO: Implement algorithm to count votes
            return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
        }

    public Integer countAllVotes (Iterable<Vote> allVotes){
        Long count = StreamSupport.stream(allVotes.spliterator(), false).count();
        return count.intValue();
        }

}





